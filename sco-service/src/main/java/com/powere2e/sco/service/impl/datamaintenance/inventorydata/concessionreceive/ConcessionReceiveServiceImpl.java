package com.powere2e.sco.service.impl.datamaintenance.inventorydata.concessionreceive;

import java.io.File;
import java.math.BigDecimal;
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
import com.powere2e.sco.interfaces.dao.datamaintenance.inventorydata.concessionreceive.ConcessionReceiveDao;
import com.powere2e.sco.interfaces.service.datamaintenance.inventorydata.concessionreceive.ConcessionReceiveService;
import com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive.ConcessionReceive;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;

/**
 * 让步接收业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public class ConcessionReceiveServiceImpl extends ServiceImpl implements ConcessionReceiveService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6579431942452802824L;
	private ConcessionReceiveDao concessionReceiveDao;

	public static ConcessionReceiveService getInstance() {
		return (ConcessionReceiveService) ConfigFactory.getInstance().getBean("concessionReceiveService");
	}

	// 获得让步接收DAO实例
	public ConcessionReceiveDao getConcessionReceiveDao() {
		return concessionReceiveDao;
	}

	// 设置让步接收DAO实例
	public void setConcessionReceiveDao(ConcessionReceiveDao concessionReceiveDao) {
		this.concessionReceiveDao = concessionReceiveDao;
	}

	// 查询
	@Override
	public List<ConcessionReceive> listConcessionReceive(Map<String, Object> map, PageInfo pageInfo) {
		return this.getConcessionReceiveDao().listConcessionReceive(map, pageInfo);
	}

	// 上传
	@Override
	public String completeImportConcessionReceiveData(File file) {
		List<String> mList = new ArrayList<>();
		// 查询商品和供应商Code
		Map<String, Object> smap = new HashMap<String, Object>();
		List<String> sList = new ArrayList<>();
		List<Merchandise> merchandiseSupplierCode = MerchandiseServiceImpl.getInstance().listMerchandise(smap, null);
		for (Merchandise temp : merchandiseSupplierCode) {
			sList.add(temp.getSupplierCode());
			mList.add(temp.getMerchandiseCode());
		}
		// 用于存储消息
		String msg = "";
		if (mList.isEmpty())
			return "SAP中目前没有商品数据,请先同步商品主数据";
		// 用于存储excel中的数据
		List<ConcessionReceive> dataList = new ArrayList<ConcessionReceive>();
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
			try {
				this.insertConcessionReceiveData(dataList);
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
	 *            商品编码
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<ConcessionReceive> parseExcel(File file, final List<String> mList, final List<String> sList, final List<ConcessionReceive> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (int idx = 2; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			ConcessionReceive cr = this.processRow(idx, rowData, mList, sList, sb,dataList);
			if (sb.length() == 0 && cr != null) {
				dataList.add(cr);
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
	 *            商品主数据
	 * @param sList
	 *            供应商主数据
	 * @param sb
	 *            错误信息
	 * @param dataList
	 *          Excel中解析出来的数据           
	 * @return 一行的一个对象
	 */
	private ConcessionReceive processRow(int idx, ExcelRow rowData, List<String> mList, List<String> sList, StringBuilder msg,List<ConcessionReceive> dataList) {
		StringBuilder sb = new StringBuilder();
		// 校验A列数据
		String dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if (StringUtils.isEmpty(dataA)) {
			sb.append("第" + idx + "行A列:物料号不可为空<br>");
		} else {
			if (!mList.contains(dataA)) {
				sb.append("第" + idx + "行A列:物料号" + dataA + "在SAP中不存在<br>");
			}
		}
		// 校验B列数据
		String dataB = null;
		dataB = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));

		// 校验C列数据
		String dataC = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
			try {
				if (StringUtils.isBlank(dataC)) {
					dataC = "";
				} else {
					Integer.valueOf(dataC);
				}
			} catch (Exception e) {
				sb.append("第" + idx + "行C列:箱规只可填入数字<br>");
			}

		// 校验D列数据
		String dataD = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D"));

		// 校验E列数据
		String dataE = null;
		dataE = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("E"));
		if (StringUtils.isEmpty(dataE)) {
			sb.append("第" + idx + "行E列:供应商编码不可为空<br>");
		} else {
			if (!sList.contains(dataE)) {
				sb.append("第" + idx + "行E列:该供应商编码与SAP中该物料的供应商编码不符<br>");
			}
		}

		// 校验F列数据
		String dataF = null;
		dataF = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("F"));

		// 校验G列数据
		Integer dataG = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("G"))) {
				sb.append("第" + idx + "行G列:让步接收数量不可为空<br>");
			} else {
				dataG = rowData.getIntegerCellValue("G");
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行G列:让步接收数量只可填入数字<br>");
		}

		// 校验H列数据
		Date dataH = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("H"))) {
				sb.append("第" + idx + "行H列:申请日期不可为空<br>");
			} else {
				dataH = rowData.getDateCellValue("H", Constant.DATA_INTEFACE_DATEFORMATE);
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行H列:申请日期格式不符，格式应为YYYY-MM-DD<br>");
		}
		// 校验I列数据
		Integer dataI = null;
		try {
			dataI = rowData.getIntegerCellValue("I");
		} catch (Exception e) {
			sb.append("第" + idx + "行I列:赠品数量只可填入数字<br>");
		}

		// 校验J列数据
		BigDecimal dataJ = null;
		try {
			dataJ = BigDecimal.valueOf(rowData.getDoubleCellValue("J"));
		} catch (Exception e) {
			dataJ = null;
		}

		// 校验K列数据
		BigDecimal dataK = null;
		try {
			dataK = BigDecimal.valueOf(rowData.getDoubleCellValue("K"));
		} catch (Exception e) {
			dataK = null;
		}

		String dataL = null;
		dataL = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("L"));
		
		boolean flag = false;
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i) == null) {
				continue;
			}

			if (mList.get(i).equals(dataA)&&sList.get(i).equals(dataE)) {
				flag = true;
				break;
			}else{
				flag = false;
			}
		}
		if (!flag) {
			sb.append("第" + idx + "行:物料"+dataA+"与供应商"+dataE+"关系不符<br>");
		}
		for (ConcessionReceive temp : dataList) {
			if (temp.getMerchandiseCode().equals(dataA) && temp.getSupplierCode().equals(dataE) && temp.getApplicationDate().equals(dataH)) {
				sb.append("表中存在相同物料号、供应商编码及申请日期的记录，请检查<br>");
				break;
			}
		}
		
		if (StringUtils.isEmpty(dataA) && StringUtils.isEmpty(dataB) && StringUtils.isEmpty(dataC) && StringUtils.isEmpty(dataD) && StringUtils.isEmpty(dataE) && StringUtils.isEmpty(dataF)
				&& null == dataG && null == dataH && null == dataI && null == dataJ && null == dataK && StringUtils.isEmpty(dataL)) {
			return null;
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new ConcessionReceive(dataA, dataB, dataC, dataD, dataE, dataF, dataG, dataH, dataI, dataJ, dataK, dataL);
	}

	/**
	 * 新增让步接收数据
	 * 
	 * @param dataList
	 */
	private void insertConcessionReceiveData(List<ConcessionReceive> list) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<ConcessionReceive> insertList = new ArrayList<ConcessionReceive>();
		for (ConcessionReceive temp : list) {
			m.clear();
			m.put("SearchMerchandiseCode", temp.getMerchandiseCode());
			m.put("SearchSupplierCode", temp.getSupplierCode());
			m.put("SearchApplicationDate", temp.getApplicationDate());
			List<ConcessionReceive> c = this.concessionReceiveDao.listConcessionReceive(m, null);
			if (c != null && c.size() != 0) {
				this.concessionReceiveDao.updateConcessionReceive(temp.toMap());
			} else {
				insertList.add(temp);
			}
		}
		if (insertList.size() != 0) {
			m.clear();
			m.put("insertList", insertList);
			this.concessionReceiveDao.insertConcessionReceive(m);
		}
	}
}