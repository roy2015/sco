package com.powere2e.sco.service.impl.datamaintenance.gradecontroldata.supplierqualitylevel;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevelDao;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevelService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevel;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;

/**
 * 供应商质量星级业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierQualityLevelServiceImpl extends ServiceImpl implements SupplierQualityLevelService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 786741679776014276L;
	private SupplierQualityLevelDao supplierQualityLevelDao;

	public static SupplierQualityLevelService getInstance() {
		return (SupplierQualityLevelService) ConfigFactory.getInstance().getBean("supplierQualityLevelService");
	}

	// 获得供应商质量星级DAO实例
	public SupplierQualityLevelDao getSupplierQualityLevelDao() {
		return supplierQualityLevelDao;
	}

	// 设置供应商质量星级DAO实例
	public void setSupplierQualityLevelDao(SupplierQualityLevelDao supplierQualityLevelDao) {
		this.supplierQualityLevelDao = supplierQualityLevelDao;
	}

	// 查询
	@Override
	public List<SupplierQualityLevel> listSupplierQualityLevel(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierQualityLevelDao().listSupplierQualityLevel(map, pageInfo);
	}

	// 上传
	@Override
	public String completeImportSupplierQualityLevelData(File file, String year) {

		Map<String, Object> smap = new HashMap<String, Object>();
		// 查询SAP中供应商的Code
		List<String> sList = SupplierServiceImpl.getInstance().listSupplierCode(smap);
		// 用于存储消息
		String msg = "";
		// 用于存储excel中的数据
		List<SupplierQualityLevel> dataList = new ArrayList<SupplierQualityLevel>();
		// 暂存Excel中的供应商编码集合
		List<String> supplierCodes = new ArrayList<String>();
		try {
			dataList = this.parseExcel(file, year, sList, supplierCodes, dataList);
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
			this.insertOrupdateSupplierQualityLevelData(dataList);
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
	 * @param year
	 *            填写的年度
	 * @param sList
	 *            供应商质量星级中供应商的Code
	 * @param dataList
	 *            Excel中的数据
	 * @param supplierCodes
	 *            Excel中的供应商编码集合
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<SupplierQualityLevel> parseExcel(File file, String year, final List<String> sList, final List<String> supplierCodes, final List<SupplierQualityLevel> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (int idx = 2; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			SupplierQualityLevel sq = this.processRow(idx, rowData, sList, supplierCodes, sb, year);
			// 如果以存在该数据则覆盖,以最新记录为准
			if (sb.length() == 0 && sq != null) {
				supplierCodes.add(sq.getSupplierCode());
				dataList.add(sq);
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
	 * @param sList
	 *            供应商主数据
	 * @param supplierCodes
	 *            Excel中的供应商编码集合
	 * @param msg
	 *            错误信息
	 * @param year
	 *            年度
	 * @return
	 */
	private SupplierQualityLevel processRow(int idx, ExcelRow rowData, List<String> sList, List<String> supplierCodes, StringBuilder msg, String year) {
		StringBuilder sb = new StringBuilder();

		// 校验A列数据
		String dataA = null;
		dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if (StringUtils.isEmpty(dataA)) {
			sb.append("第" + idx + "行A列:供应商编号不可为空<br>");
		} else {
			// 供应商编号列是否包含SAP中不存在的供应商编号
			if (!sList.contains(dataA.toString())) {
				sb.append("第" + idx + "行A列:供应商编号" + dataA + "不存在<br>");
			}
			// 存在相同的供应商编号
			if (supplierCodes.contains(dataA.toString())) {
				sb.append("第" + idx + "行A列:表中存在相同供应商编号，请检查<br>");
			}
		}

		// 校验B列数据
		BigDecimal dataB = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("B"))) {
				sb.append("第" + idx + "行B列:供应商质量星级不可为空<br>");
			} else {
				dataB = BigDecimal.valueOf(rowData.getDoubleCellValue("B"));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行B列:供应商质量星级列包含非数字<br>");
		}
		if (StringUtils.isEmpty(dataA) && null == dataB) {
			return null;
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new SupplierQualityLevel(dataA, Integer.valueOf(year), dataB);
	}

	/**
	 * 新增或修改年度质量星级数据
	 * 
	 * @param dataList
	 */
	private void insertOrupdateSupplierQualityLevelData(List<SupplierQualityLevel> list) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<SupplierQualityLevel> insertList = new ArrayList<SupplierQualityLevel>();
		for (SupplierQualityLevel temp : list) {
			m.clear();
			m.put("searchSupplierCode", temp.getSupplierCode());
			m.put("searchComplaintsYear", temp.getQualityLevelYear());
			List<SupplierQualityLevel> c = this.supplierQualityLevelDao.listSupplierQualityLevel(m, null);
			if (c != null && c.size() != 0) {
				this.supplierQualityLevelDao.updateSupplierQualityLevel(temp.toMap());
			} else {
				insertList.add(temp);
			}
		}
		if (insertList.size()!=0) {
			m.clear();
			m.put("insertList", insertList);
			this.supplierQualityLevelDao.insertSupplierQualityLevel(m);
		}
	}
}