package com.powere2e.sco.service.impl.datamaintenance.gradecontroldata.suppliercomplaints;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
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
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaintsDao;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaintsService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaints;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;

/**
 * 供应商年度千万元客诉业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierComplaintsServiceImpl extends ServiceImpl implements SupplierComplaintsService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5590447722502897907L;
	private SupplierComplaintsDao supplierComplaintsDao;
	private DecimalFormat df = new DecimalFormat("0.00");

	public static SupplierComplaintsService getInstance() {
		return (SupplierComplaintsService) ConfigFactory.getInstance().getBean("supplierComplaintsService");
	}

	// 获得供应商年度千万元客诉DAO实例
	public SupplierComplaintsDao getSupplierComplaintsDao() {
		return supplierComplaintsDao;
	}

	// 设置供应商年度千万元客诉DAO实例
	public void setSupplierComplaintsDao(SupplierComplaintsDao supplierComplaintsDao) {
		this.supplierComplaintsDao = supplierComplaintsDao;
	}

	// 查询
	@Override
	public List<SupplierComplaints> listSupplierComplaints(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierComplaintsDao().listSupplierComplaints(map, pageInfo);
	}

	// 上传
	@Override
	public String completeImportSupplierComplaintsData(File file, String year) {

		Map<String, Object> smap = new HashMap<String, Object>();
		// 查询SAP中供应商的Code
		List<String> sList = SupplierServiceImpl.getInstance().listSupplierCode(smap);
		// 用于存储消息
		String msg = "";
		// 用于存储excel中的数据
		List<SupplierComplaints> dataList = new ArrayList<SupplierComplaints>();
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
			this.insertOrupdateSupplierComplaintsData(dataList);
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
	 *            年度
	 * @param sList
	 *            供应商质量星级中供应商的Code
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<SupplierComplaints> parseExcel(File file, String year, final List<String> sList, final List<String> supplierCodes, final List<SupplierComplaints> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (int idx = 2; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			SupplierComplaints sq = this.processRow(idx, rowData, sList, supplierCodes, sb, year);
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
	 *            SAP中供应商Code
	 * @param msg
	 *            错误信息
	 * @param year
	 *            年度
	 * @return
	 */
	private SupplierComplaints processRow(int idx, ExcelRow rowData, List<String> sList, List<String> supplierCodes, StringBuilder msg, String year) {
		StringBuilder sb = new StringBuilder();
		// 校验A列数据
		String dataA = null;
			dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
			if (StringUtils.isEmpty(dataA)) {
				sb.append("第" + idx + "行A列:供应商编号不可为空<br>");
			} else {
				// 供应商编号列是否包含SAP中不存在的供应商编号
				if (!sList.contains(dataA)) {
					sb.append("第" + idx + "行A列:供应商编号" + dataA + "不存在<br>");
				}
				// 存在相同的供应商编号
				if (supplierCodes.contains(dataA)) {
					sb.append("第" + idx + "行A列:表中存在相同供应商编号，请检查<br>");
				}
			}

		// 校验B列数据
		BigDecimal dataB = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("B"))) {
				sb.append("第" + idx + "行B列:供应商年度千万元客诉不可为空<br>");
			} else {
				dataB = rowData.getBigDecimalCellValue("B", 3, RoundingMode.UP);
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行B列:供应商年度千万元客诉列包含非数字<br>");
			dataB = null;
		}
		if (StringUtils.isEmpty(dataA) && null == dataB) {
			return null;
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new SupplierComplaints(dataA, BigDecimal.valueOf(Double.valueOf(year)), (BigDecimal.valueOf(Double.valueOf(df.format(dataB)))));
	}

	/**
	 * 新增或修改供应商年度千万元客诉数据
	 * 
	 * @param dataList
	 */
	private void insertOrupdateSupplierComplaintsData(List<SupplierComplaints> list) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<SupplierComplaints> insertList = new ArrayList<SupplierComplaints>();
		for (SupplierComplaints temp : list) {
			m.clear();
			m.put("searchSupplierCode", temp.getSupplierCode());
			m.put("searchComplaintsYear", temp.getComplaintsYear());
			List<SupplierComplaints> c = this.supplierComplaintsDao.listSupplierComplaints(m, null);
			if (c != null && c.size() != 0) {
				this.supplierComplaintsDao.updateSupplierComplaints(temp.toMap());
			} else {
				insertList.add(temp);
			}
		}
		if (insertList.size()!=0) {
			m.clear();
			m.put("insertList", insertList);
			this.supplierComplaintsDao.insertSupplierComplaints(m);
		}
	}
}