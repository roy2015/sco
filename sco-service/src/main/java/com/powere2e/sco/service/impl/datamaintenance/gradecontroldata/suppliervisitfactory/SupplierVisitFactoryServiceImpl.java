package com.powere2e.sco.service.impl.datamaintenance.gradecontroldata.suppliervisitfactory;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
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
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactoryDao;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactoryService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;

/**
 * 供应商年度巡厂得分业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class SupplierVisitFactoryServiceImpl extends ServiceImpl implements SupplierVisitFactoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1930884861521850910L;
	private SupplierVisitFactoryDao supplierVisitFactoryDao;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	public static SupplierVisitFactoryService getInstance() {
		return (SupplierVisitFactoryService) ConfigFactory.getInstance().getBean("supplierVisitFactoryService");
	}

	// 获得供应商年度巡厂得分DAO实例
	public SupplierVisitFactoryDao getSupplierVisitFactoryDao() {
		return supplierVisitFactoryDao;
	}

	// 设置供应商年度巡厂得分DAO实例
	public void setSupplierVisitFactoryDao(SupplierVisitFactoryDao supplierVisitFactoryDao) {
		this.supplierVisitFactoryDao = supplierVisitFactoryDao;
	}

	// 查询
	@Override
	public List<SupplierVisitFactory> listSupplierVisitFactory(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSupplierVisitFactoryDao().listSupplierVisitFactory(map, pageInfo);
	}

	// 上传
	@Override
	public String completeImportSupplierVisitFactoryData(File file) {

		Map<String, Object> smap = new HashMap<String, Object>();
		// 查询SAP中供应商的Code
		List<String> sList = SupplierServiceImpl.getInstance().listSupplierCode(smap);
		// 用于存储消息
		String msg = "";
		// 用于存储excel中的数据
		List<SupplierVisitFactory> dataList = new ArrayList<SupplierVisitFactory>();
		try {
			dataList = this.parseExcel(file, sList, dataList);
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
			this.insertOrupdateSupplierVisitFactoryData(dataList);
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
	 * @param sList
	 *            SAP中供应商的Code
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的让步接收数据
	 * @throws Exception
	 */
	private List<SupplierVisitFactory> parseExcel(File file, final List<String> sList, final List<SupplierVisitFactory> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (int idx = 2; idx <= handler.getLastRowNum(); idx++) {
			// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			SupplierVisitFactory supplierVisitFactory = this.processRow(dataList, idx, rowData, sList, sb);
			// 如果以存在该数据则覆盖,以最新记录为准
			if (sb.length() == 0 && null != supplierVisitFactory) {
				dataList.add(supplierVisitFactory);
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
	 *            供应商编号集合
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private SupplierVisitFactory processRow(final List<SupplierVisitFactory> dataList, int idx, ExcelRow rowData, List<String> sList, StringBuilder msg) {
		StringBuilder sb = new StringBuilder();
		// 校验A列数据
		String dataA = null;
		dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if (StringUtils.isEmpty(dataA)) {
			sb.append("第" + idx + "行A列:供应商编号不可为空<br>");
		} else {
			// 供应商编号列是否包含SAP中不存在的供应商编号
			if (!sList.contains(dataA)) {
				sb.append("第" + idx + "行A列:供应商编号" + dataA + "在SAP中不存在<br>");
			}
		}

		// 校验B列数据
		BigDecimal dataB = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("B"))) {
				sb.append("第" + idx + "行B列:占地面积不可为空<br>");
			} else {
				dataB = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("B"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行B列:占地面积只可填入数字<br>");
			dataB = null;
		}

		// 校验C列数据
		BigDecimal dataC = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("C"))) {
				sb.append("第" + idx + "行C列:车间面积不可为空<br>");
			} else {
				dataC = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("C"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行C列:车间面积只可填入数字<br>");
		}

		// 校验D列数据
		Integer dataD = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("D"))) {
				sb.append("第" + idx + "行D列:企业总人数不可为空<br>");
			} else {
				dataD = Integer.valueOf(StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D")));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行D列:企业总人数只可填入数<br>");
		}

		// 校验E列数据
		BigDecimal dataE = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("E"))) {
				sb.append("第" + idx + "行E列:年总产值不可为空<br>");
			} else {
				dataE = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("E"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行E列:年总产值只可填入数<br>");
		}

		// 校验F列数据
		Date dataF = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("F"))) {
				sb.append("第" + idx + "行F列:巡厂日期不可为空<br>");
			} else {
				dataF = rowData.getDateCellValue("F", Constant.DATA_INTEFACE_DATEFORMATE);
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行F列:巡厂日期格式有误，日期格式应为YYYY-MM-DD<br>");
		}

		// 校验G列数据
		BigDecimal dataG = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("G"))) {
				sb.append("第" + idx + "行G列:满分不可为空<br>");
			} else {
				dataG = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("G"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行G列:满分只可填入数字<br>");
		}

		// 校验H列数据
		BigDecimal dataH = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("H"))) {
				sb.append("第" + idx + "行H列:合格分不可为空<br>");
			} else {
				dataH = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("H"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行H列:合格分只可填入数字<br>");
		}

		// 校验I列数据
		BigDecimal dataI = null;
		try {
			if (StringUtils.isBlank(rowData.getStringCellValue("I"))) {
				sb.append("第" + idx + "行I列:供应商巡厂得分不可为空<br>");
			} else {
				dataI = BigDecimal.valueOf(Double.valueOf(df.format(rowData.getDoubleCellValue("I"))));
			}
		} catch (Exception e) {
			sb.append("第" + idx + "行I列:供应商巡厂得分只可填入数字<br>");
		}

		// 校验J列数据
		String dataJ = null;
		try {
			dataJ = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("J"));
		} catch (Exception e) {
			dataJ = null;
		}

		// 校验K列数据
		String dataK = null;
		dataK = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("K"));
		if (StringUtils.isEmpty(dataK)) {
			sb.append("第" + idx + "行K列:巡检负责人不可为空<br>");
		}

		for (SupplierVisitFactory temp : dataList) {
			if (temp.getSupplierCode().equals(dataA) && temp.getVisitFactoryDate().equals(dataF)) {
				sb.append("表中存在相同供应商编号及巡厂日期的记录，请检查<br>");
				break;
			}
		}
		if (StringUtils.isEmpty(dataA) && null == dataB && null == dataC && null == dataD && null == dataE && null == dataF && null == dataG && null == dataH && null == dataI
				&& StringUtils.isEmpty(dataJ) && StringUtils.isEmpty(dataK)) {
			return null;
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new SupplierVisitFactory(dataA, dataB, dataC, dataD, dataE, dataF, dataG, dataH, dataI, dataJ, dataK);
	}

	/**
	 * 新增或修改供应商年度千万元客诉数据
	 * 
	 * @param dataList
	 */
	private void insertOrupdateSupplierVisitFactoryData(List<SupplierVisitFactory> list) {
		Map<String, Object> m = new HashMap<String, Object>();
		List<SupplierVisitFactory> insertList = new ArrayList<SupplierVisitFactory>();
		for (SupplierVisitFactory temp : list) {
			m.clear();
			m.put("searchSupplierCode", temp.getSupplierCode());
			m.put("searchVisitFactoryDate", temp.getVisitFactoryDate());
			List<SupplierVisitFactory> c = this.supplierVisitFactoryDao.listSupplierVisitFactory(m, null);
			if (c != null && c.size() != 0) {
				this.supplierVisitFactoryDao.updateSupplierVisitFactory(temp.toMap());
			} else {
				insertList.add(temp);
			}
		}
		if (insertList.size()!=0) {
			m.clear();
			m.put("insertList", insertList);
			this.supplierVisitFactoryDao.insertSupplierVisitFactory(m);
		}
	}
}