package com.powere2e.sco.service.impl.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualifiedDao;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualifiedService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualified;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;

/**
 * 商品抽检不合格记录业务类的实现
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public class MerchandiseUnqualifiedServiceImpl extends ServiceImpl implements MerchandiseUnqualifiedService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6393116508731379843L;
	private MerchandiseUnqualifiedDao merchandiseUnqualifiedDao;

	public static MerchandiseUnqualifiedService getInstance() {
		return (MerchandiseUnqualifiedService) ConfigFactory.getInstance().getBean("merchandiseUnqualifiedManager");
	}

	// 获得商品抽检不合格记录DAO实例
	public MerchandiseUnqualifiedDao getMerchandiseUnqualifiedDao() {
		return merchandiseUnqualifiedDao;
	}

	// 设置商品抽检不合格记录DAO实例
	public void setMerchandiseUnqualifiedDao(MerchandiseUnqualifiedDao merchandiseUnqualifiedDao) {
		this.merchandiseUnqualifiedDao = merchandiseUnqualifiedDao;
	}

	// 查询
	@Override
	public List<MerchandiseUnqualified> listMerchandiseUnqualified(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseUnqualifiedDao().listMerchandiseUnqualified(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseUnqualified(MerchandiseUnqualified merchandiseUnqualified) {
		this.getMerchandiseUnqualifiedDao().insertMerchandiseUnqualified(merchandiseUnqualified.toMap());
	}

	// 查询一条记录，以便更新
	public MerchandiseUnqualified MerchandiseUnqualifiedData(Map<String, Object> map) {
		MerchandiseUnqualified merchandiseUnqualified = merchandiseUnqualifiedDao.searchMerchandiseUnqualified(map);
		return merchandiseUnqualified;
	}

	// 上传Excel中商品不合格记录模块
	public String completeImportMerchandiseUnqualified(File file) {
		// 查询商品Code和供应商Code
		List<String> mList = new ArrayList<>();
		List<String> sList = new ArrayList<>();
		Map<String, Object> smap = new HashMap<String, Object>();
		List<Merchandise> merchandise = MerchandiseServiceImpl.getInstance().listMerchandise(smap, null);
		if (merchandise.size() > 0) {
			for (Merchandise temp : merchandise) {
				if (temp != null) {
					sList.add(temp.getSupplierCode());
					mList.add(temp.getMerchandiseCode());
				}
			}
		}
		// 用于存储消息
		String msg = "";
		if (mList.isEmpty()) {
			return "SAP中目前没有商品数据,请先同步商品主数据";
		}
		// 用于存储excel中的数据
		List<MerchandiseUnqualified> dataList = new ArrayList<MerchandiseUnqualified>();
		try {
			dataList = this.parseExcel(file, mList, sList, dataList);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		if (msg.length() > 0) {// 有错误消息
			return msg.toString();
		} else if (dataList.isEmpty()) {// Excel文件中无数据
			return "请在文件中填写需上传数据";
		} else {
			// 首先把Excel当中解析出来数据进行相互比对，如果前面与后面的重复了，删除前面的记录，保留后面的记录
			for (int i = 0; i < dataList.size(); i++) {
				for (int j = i + 1; j < dataList.size(); j++) {
					if (dataList.get(i).getMerchandiseCode() != null && dataList.get(j).getMerchandiseCode() != null
							&& dataList.get(i).getMerchandiseCode().equals(dataList.get(j).getMerchandiseCode())) {
						if (dataList.get(i).getSupplierCode() != null && dataList.get(j).getSupplierCode() != null && dataList.get(i).getSupplierCode().equals(dataList.get(j).getSupplierCode())) {
							if (dataList.get(i).getSpotCheckDate() != null && dataList.get(j).getSpotCheckDate() != null
									&& dataList.get(i).getSpotCheckDate().equals(dataList.get(j).getSpotCheckDate())) {
								return "表中存在相同商品编号、供应商编号及抽检日期的记录，请检查";
							}
						}
					}
				}
			}
			// 遍历集合，遍历一个，查询一次，如果返回了一个对象，则执行更新“抽检批次”字段，实现覆盖功能
			for (int i = 0; i < dataList.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("merchandiseCode", dataList.get(i).getMerchandiseCode());
				map.put("supplierCode", dataList.get(i).getSupplierCode());
				map.put("spotCheckDate", dataList.get(i).getSpotCheckDate());
				map.put("spotCheckBatch", dataList.get(i).getSpotCheckBatch());
				map.put("remarks", dataList.get(i).getRemarks());
				MerchandiseUnqualified merchandiseUnqualified = MerchandiseUnqualifiedData(map);// 查询
				if (merchandiseUnqualified != null) {
					if (dataList.get(i).getMerchandiseCode() != null && merchandiseUnqualified.getMerchandiseCode() != null
							&& dataList.get(i).getMerchandiseCode().equals(merchandiseUnqualified.getMerchandiseCode())) {
						if (dataList.get(i).getSupplierCode() != null && merchandiseUnqualified.getSupplierCode() != null
								&& dataList.get(i).getSupplierCode().equals(merchandiseUnqualified.getSupplierCode())) {
							if (dataList.get(i).getSpotCheckDate() != null && merchandiseUnqualified.getSpotCheckDate() != null
									&& dataList.get(i).getSpotCheckDate().equals(merchandiseUnqualified.getSpotCheckDate())) {
								if (dataList.get(i).getSpotCheckBatch() != null && merchandiseUnqualified.getSpotCheckBatch() != null
										&& dataList.get(i).getSpotCheckBatch().equals(merchandiseUnqualified.getSpotCheckBatch())) {
									merchandiseUnqualifiedDao.updateMerchandiseUnqualified(map);
									dataList.remove(i);
									i--;
								}
							}
						}
					}
				}
			}
			// 然后批量插入
			Map<String, Object> map = new HashMap<String, Object>();
			// List分批插入(每批100条)
			int dataListSize = dataList.size();
			int maxCount = dataListSize / Constant.BATCH_SIZE;
			int i = 0;
			for (; i < maxCount; i++) {
				int toIndex = (i + 1) * Constant.BATCH_SIZE > dataListSize ? dataListSize : (i + 1) * Constant.BATCH_SIZE;
				map.put("list", dataList.subList(i * Constant.BATCH_SIZE, toIndex));
				this.merchandiseUnqualifiedDao.insertMerchandiseUnqualified(map);
			}
			// 防止遗漏数据
			int currentIndex = i * Constant.BATCH_SIZE;
			if (currentIndex < dataList.size()) {
				map.put("list", dataList.subList(currentIndex, dataList.size()));
				this.merchandiseUnqualifiedDao.insertMerchandiseUnqualified(map);
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
	 * @param sList
	 *            供应商编码
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<MerchandiseUnqualified> parseExcel(File file, final List<String> mList, final List<String> sList, final List<MerchandiseUnqualified> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		// 设置表格开始行号
		int idx = 2;
		// 存储错误信息
		StringBuilder sb = new StringBuilder();
		// 循环处理Excel中的每一行数据
		for (; idx <= handler.getLastRowNum(); idx++) {
			ExcelRow rowData = handler.getExcelRow(idx);
			MerchandiseUnqualified cr = this.processRow(idx, rowData, mList, sList, sb);
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
	 * @param msg
	 *            错误信息
	 * @return
	 * @throws ParseException
	 */
	private MerchandiseUnqualified processRow(int idx, ExcelRow rowData, List<String> mList, List<String> sList, StringBuilder msg) throws ParseException {
		StringBuilder sb = new StringBuilder();
		// 校验A列数据--商品编号
		String dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if (dataA == null || dataA.length() == 0) {
			sb.append("第" + idx + "行A列:商品编号不可为空<br>");
		} else {
			if (!mList.contains(dataA)) {
				sb.append("第" + idx + "行A列:商品编号" + dataA + "在SAP中不存在<br>");
			}
		}
		// 校验B列数据--供应商编号
		String dataB = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
		if (dataB == null || dataB.length() == 0) {
			sb.append("第" + idx + "行B列:供应商编号不可为空<br>");
		} else {
			if (!sList.contains(dataB)) {
				sb.append("第" + idx + "行B列:该供应商编号" + dataB + "在SAP中不存在<br>");
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCode", dataA);
		map.put("supplierCode", dataB);
		List<MerchandiseUnqualified> list = this.merchandiseUnqualifiedDao.listMerchandise(map, null);
		if (list.size() == 0) {
			sb.append("第" + idx + "行:供应商编码与SAP中该物料号对应的供应商编码需要一致<br>");
		}
		// 校验C列数据--抽检日期
		String dataC_spotCheckDate = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
		Date dataC = null;
		String regEx = "[0-9]{4}+[-]+[0-9]{2}+[-]+[0-9]{2}";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (dataC_spotCheckDate == null || dataC_spotCheckDate.length() == 0) {
			sb.append("第" + idx + "行C列:抽检日期不可为空<br>");
		} else {
			if (dataC_spotCheckDate.matches(regEx)) {
				dataC = sdf.parse(dataC_spotCheckDate);
			} else {
				sb.append("第" + idx + "行C列:抽检日期格式不符，格式应为YYYY-MM-DD<br>");
			}
		}
		// 校验D列数据--抽检批次
		String dataD_spotCheckDate = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D"));
		Date dataD = null;
		if (dataD_spotCheckDate == null || dataD_spotCheckDate.length() == 0) {
			sb.append("第" + idx + "行D列:抽检批次不可为空<br>");
		} else {
			if (dataD_spotCheckDate.matches(regEx)) {
				dataD = sdf.parse(dataD_spotCheckDate);
			} else {
				sb.append("第" + idx + "行D列:抽检批次格式不符，格式应为YYYY-MM-DD<br>");
			}
		}
		// 校验已经被编辑过的某一行的单元格却又把这行数据删除掉，返回null
		if ((dataA != null && "".equals(dataA)) && (dataB != null && "".equals(dataB)) && dataC == null && (dataD != null && "".equals(dataD))) {
			return null;
		}
		// 把StringBuilder sb里的所有“单个错误信息”数据还原为字符串，再添加到StringBuilder msg里
		msg.append(sb.toString());
		// 如果有“单个错误信息”则返回null
		if (sb.length() > 0) {
			return null;
		}
		// Excel表中A列数据是商品编号
		String merchandiseCode = dataA;
		// Excel表中B列数据是供应商编号
		String supplierCode = dataB;
		// Excel表中C列数据是抽检日期
		Date spotCheckDate = dataC;
		// Excel表中D列数据是抽检批次
		Date spotCheckBatch = dataD;
		// Excel表中F列数据时备注
		String remarks = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("E"));
		// 如果以上都没有出现任何错误，返回一个MerchandiseUnqualified对象
		return new MerchandiseUnqualified(merchandiseCode, supplierCode, spotCheckDate, spotCheckBatch, remarks);
	}
}