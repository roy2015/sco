package com.powere2e.sco.service.impl.datamaintenance.gradecontroldata.merchandiserecycle;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycleDao;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycleService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycle;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;

/**
 * 商品回收记录业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseRecycleServiceImpl extends ServiceImpl implements MerchandiseRecycleService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2173222090499704181L;
	private MerchandiseRecycleDao merchandiseRecycleDao;

	public static MerchandiseRecycleService getInstance() {
		return (MerchandiseRecycleService) ConfigFactory.getInstance().getBean("merchandiseRecycleService");
	}

	// 获得商品回收记录DAO实例
	public MerchandiseRecycleDao getMerchandiseRecycleDao() {
		return merchandiseRecycleDao;
	}

	// 设置商品回收记录DAO实例
	public void setMerchandiseRecycleDao(MerchandiseRecycleDao merchandiseRecycleDao) {
		this.merchandiseRecycleDao = merchandiseRecycleDao;
	}

	// 查询
	@Override
	public List<MerchandiseRecycle> listMerchandiseRecycle(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseRecycleDao().listMerchandiseRecycle(map, pageInfo);
	}

	// 上传
	@Override
	public String completeImportConcessionReceiveData(File file) {
		// 商品集合
		List<String> mList = new ArrayList<>();
		// 供应商集合
		List<String> sList = new ArrayList<>();
		// 查询所有商品和供应商
		Map<String, Object> map = new HashMap<String, Object>();
		List<Merchandise> merchandiseSupplierCode = MerchandiseServiceImpl.getInstance().listMerchandise(map, null);
		for (Merchandise temp : merchandiseSupplierCode) {
			sList.add(temp.getSupplierCode());
			mList.add(temp.getMerchandiseCode());
		}
		// 用于存储消息
		String msg = "";
		if (mList.isEmpty())
			return "SAP中目前没有商品数据,请先同步商品主数据";
		// 用于存储excel中的数据
		List<MerchandiseRecycle> dataList = new ArrayList<MerchandiseRecycle>();
		try {
			dataList = this.parseExcel(file, mList, sList, dataList);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		if (msg.length() > 0) {// 有错误消息
			return msg.toString();
		} else if (dataList.isEmpty()) {// 文件中无数据
			return "请在文件中填写需上传数据";
		} else {
			try
			{
			this.insertMerchandiseRecycleData(dataList);
			} catch (Exception e) {
				return "您填入的数据不合法,请检查数据的格式或者长度";
			}
		}
		return null;
	}

	/**
	 * 解析Excel
	 * 
	 * @param file
	 *            上传的文件
	 * @param mList
	 *            商品编码集合
	 * @param sList
	 *            供应商编码集合
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<MerchandiseRecycle> parseExcel(File file, final List<String> mList, final List<String> sList, final List<MerchandiseRecycle> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (int idx = 2; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			MerchandiseRecycle merchandiseRecycle = this.processRow(dataList, idx, rowData, mList, sList, sb);
			if (sb.length() == 0 && merchandiseRecycle != null) {
				dataList.add(merchandiseRecycle);
			}
		}
		// 检测是否有错误
		if (sb.length() > 0) {
			throw new EscmException(sb.toString());
		}
		return dataList;
	}

	/**
	 * 处理excel一行的数据
	 * 
	 * @param idx
	 *            当前行数
	 * @param rowData
	 *            当前行数据
	 * @param mList
	 *            商品编码集合
	 * @param sList
	 *            供应商编码集合
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private MerchandiseRecycle processRow(final List<MerchandiseRecycle> dataList, int idx, ExcelRow rowData, List<String> mList, List<String> sList, StringBuilder msg) {
		StringBuilder sb = new StringBuilder();
		// 校验商品编号列数据
		String merchandiseCode = null;
		merchandiseCode = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if (StringUtils.isEmpty(merchandiseCode)) {
			sb.append("第" + idx + "行A列:商品编号不可为空<br>");
		} else {
			if (!mList.contains(merchandiseCode)) {
				sb.append("第" + idx + "行A列:商品编号" + merchandiseCode + "在SAP中不存在<br>");
			}
		}

		// 校验供应商编号列数据
		String supplierCode = null;
		supplierCode = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
		if (StringUtils.isEmpty(supplierCode)) {
			sb.append("第" + idx + "行B列:供应商编号不可为空<br>");
		}
		if (!sList.contains(supplierCode)) {
			sb.append("第" + idx + "行B列:该供应商编号与SAP中该物料的供应商编号不符<br>");
		}

		// 校验召回日期列数据
		Date recycleDate = null;
		try {
			if (StringUtils.isEmpty(StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C")))) {
				sb.append("第" + idx + "行C列:召回日期不可为空<br>");
			} else {
				recycleDate = rowData.getDateCellValue("C", Constant.DATA_INTEFACE_DATEFORMATE);
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行C列:召回日期格式不符，格式应为YYYY-MM-DD<br>");
		}

		// 校验召回数量列数据
		String recycleCount = null;
		if (StringUtils.isEmpty(StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D")))) {
			sb.append("第" + idx + "行D列:召回数量不可为空<br>");
		}
		recycleCount = rowData.getStringCellValue("D");
		
		boolean flag = false;
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i) == null) {
				continue;
			}
			if (mList.get(i).equals(merchandiseCode)&&sList.get(i).equals(supplierCode)) {
				flag = true;
				break;
			}else{
				flag = false;
			}
		}
		if (!flag) {
			sb.append("第" + idx + "行:物料"+merchandiseCode+"与供应商"+supplierCode+"关系不符<br>");
		}
		
		for (MerchandiseRecycle temp : dataList) {
			if (temp.getSupplierCode().equals(supplierCode) && temp.getMerchandiseCode().equals(merchandiseCode) && temp.getRecycleDate().equals(recycleDate)) {
				sb.append("表中存在相同商品编号、供应商编号及召回日期的记录，请检查<br>");
			}
		}

		if (StringUtils.isEmpty(merchandiseCode) && StringUtils.isEmpty(supplierCode) && null == recycleDate && null == recycleCount) {
			return null;
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new MerchandiseRecycle(merchandiseCode, supplierCode, recycleDate, recycleCount);
	}

	/**
	 * 新增让步接收数据
	 * 
	 * @param dataList
	 */
	private void insertMerchandiseRecycleData(List<MerchandiseRecycle> list) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<MerchandiseRecycle> insertList = new ArrayList<MerchandiseRecycle>();
		for (MerchandiseRecycle temp : list) {
			m.clear();
			m.put("SearcherchandiseCode", temp.getMerchandiseCode());
			m.put("SearchSupplierCode", temp.getSupplierCode());
			m.put("SearchRecycleDate", temp.getRecycleDate());
			List<MerchandiseRecycle> c = this.merchandiseRecycleDao.listMerchandiseRecycle(m, null);
			if (c != null && c.size() != 0) {
				this.merchandiseRecycleDao.updateMerchandiseRecycle(temp.toMap());
			} else {
				insertList.add(temp);
			}
		}
		if (insertList.size() != 0) {
			m.clear();
			m.put("insertList", insertList);
			this.merchandiseRecycleDao.insertMerchandiseRecycle(m);
		}
	}
}