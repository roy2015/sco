package com.powere2e.sco.service.impl.suppliercomprehensiveanalysis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisDao;
import com.powere2e.sco.interfaces.service.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysis;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisForm;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisSet;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 采购委员会OA申请类的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class SupplierComprehensiveAnalysisServiceImpl extends ServiceImpl implements SupplierComprehensiveAnalysisService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5497243415785782818L;

	private SupplierComprehensiveAnalysisDao supplierComprehensiveAnalysisDao;

	// 获得报价单DAO实例
	public SupplierComprehensiveAnalysisDao getSupplierComprehensiveAnalysisDao() {
		return supplierComprehensiveAnalysisDao;
	}

	// 设置报价单DAO实例
	public void setSupplierComprehensiveAnalysisDao(SupplierComprehensiveAnalysisDao supplierComprehensiveAnalysisDao) {
		this.supplierComprehensiveAnalysisDao = supplierComprehensiveAnalysisDao;
	}

	// 采购委员会竞价单OA申请意向品列表
	@Override
	public List<SupplierComprehensiveAnalysis> listSupplierComprehensiveAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return supplierComprehensiveAnalysisDao.listSupplierComprehensiveAnalysis(map, pageInfo);
	}

	@Override
	public List<SupplierComprehensiveAnalysisForm> listSupplierComprehensiveAnalysisForm(Map<String, Object> map, PageInfo pageInfo) {
		List<SupplierComprehensiveAnalysisForm> lin = supplierComprehensiveAnalysisDao.listSupplierComprehensiveAnalysisForm(map, pageInfo);
		/*
		 * BigDecimal bd0 = new BigDecimal(2221000.555); BigDecimal bd1 = new
		 * BigDecimal(1000);
		 * 
		 * String str0 = bd0 + ""; String str1 = bd1 + "";
		 * 
		 * lin.get(0).setXsl(bd0); lin.get(1).setXsl(bd1);
		 * 
		 * lin.get(0).setXsje(bd0); lin.get(1).setXsje(bd1);
		 * 
		 * lin.get(0).setMle(bd0); lin.get(1).setMle(bd1);
		 * 
		 * lin.get(0).setXslzball(str0+"%"); lin.get(1).setXslzball(str1+"%");
		 * 
		 * lin.get(0).setXsjezball(str0+"%"); lin.get(1).setXsjezball(str1+"%");
		 * 
		 * lin.get(0).setMlezball(str0+"%"); lin.get(1).setMlezball(str1+"%");
		 * 
		 * lin.get(0).setXslzbxfl(str0+"%");lin.get(1).setXslzbxfl(str1+"%");
		 * lin.get(0).setXslzbmxl(str0+"%");lin.get(1).setXslzbmxl(str1+"%");
		 * lin.get(0).setXsjezbmxl(str0+"%");lin.get(1).setXsjezbmxl(str1+"%");
		 * 
		 * lin.get(0).setMlezbxfl(str0+"%");lin.get(1).setMlezbxfl(str1+"%");
		 * 
		 * lin.get(0).setMlezbmxl(str0+"%");lin.get(1).setMlezbmxl(str1+"%");
		 * lin.get(0).setPsdxl(bd0);lin.get(1).setPsdxl(bd1);
		 * lin.get(0).setPsdxsje(bd0);lin.get(1).setPsdxsje(bd1);
		 * lin.get(0).setPsdml(bd0);lin.get(1).setPsdml(bd1);
		 * lin.get(0).setQxs(bd0);lin.get(1).setQxs(bd1);
		 * lin.get(0).setQxdt(bd0);lin.get(1).setQxdt(bd1);
		 * lin.get(0).setXsdt(bd0);lin.get(1).setXsdt(bd1);
		 * lin.get(0).setHyd(str0+"%");lin.get(1).setHyd(str1+"%");
		 * lin.get(0).setAmds(bd0);lin.get(1).setAmds(bd1);
		 * lin.get(0).setXsjezbxfl(str0+"%");lin.get(1).setXsjezbxfl(str1+"%");
		 * lin.get(0).setBmds(bd0);lin.get(1).setBmds(bd1);
		 * lin.get(0).setCmds(bd0);lin.get(1).setCmds(bd1);
		 * lin.get(0).setDmds(bd0);lin.get(1).setDmds(bd1);
		 * lin.get(0).setJhl(bd0);lin.get(1).setJhl(bd1);
		 * lin.get(0).setJhe(bd0);lin.get(1).setJhe(bd1);
		 * lin.get(0).setDdjsl(str0+"%");lin.get(1).setDdjsl(str1+"%");
		 * lin.get(0).setDdmzl(str0+"%");lin.get(1).setDdmzl(str1+"%");
		 * lin.get(0).setRbjssl(bd0);lin.get(1).setRbjssl(bd1);
		 * lin.get(0).setRbjscs(bd0);lin.get(1).setRbjscs(bd1);
		 * lin.get(0).setGyssjkc(bd0);lin.get(1).setGyssjkc(bd1);
		 * lin.get(0).setGysaqkc(bd0);lin.get(1).setGysaqkc(bd1);
		 * lin.get(0).setZlxj(bd0);lin.get(1).setZlxj(bd1);
		 * lin.get(0).setZhcs(bd0);lin.get(1).setZhcs(bd1);
		 * lin.get(0).setZhsl(bd0);lin.get(1).setZhsl(bd1);
		 * lin.get(0).setZhskugs(bd0);lin.get(1).setZhskugs(bd1);
		 * lin.get(0).setCjbhgcs(bd0);lin.get(1).setCjbhgcs(bd1);
		 * lin.get(0).setCjbhgskus(bd0);lin.get(1).setCjbhgskus(bd1);
		 * lin.get(0).setRchgl(str0);lin.get(1).setRchgl(str1);
		 * lin.get(0).setGysndqwyks(bd0);lin.get(1).setGysndqwyks(bd1);
		 * lin.get(0).setGysxcpf(bd0);lin.get(1).setGysxcpf(bd1);
		 * lin.get(0).setGyskpdf(bd0);lin.get(1).setGyskpdf(bd1);
		 * lin.get(0).setGyskpdfph(bd0);lin.get(1).setGyskpdfph(bd1);
		 * lin.get(0).setGysyxps(bd0);lin.get(1).setGysyxps(bd1);
		 */
		
		return lin;
	}

	@Override
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableInfo", paraMap.get("tableInfo"));
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("totalCostAnalogyAnalysis/supplierCostAnalogyAnalysis.ftl", "shareFunctionAnalysis/suppliercomprehensiveanalysis".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.SSA.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("SupplierComprehensiveAnalysisServiceImpl.saveSearchDataForm删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public void exportExcel(List<SupplierComprehensiveAnalysisForm> list, SupplierComprehensiveAnalysisSet supplierComprehensiveAnalysisSet, ServletOutputStream out) {
		// Workbook wb = new XSSFWorkbook();
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("供应商综合分析结果");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle strStyleCenter = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		ExcelUtils.setAlign(strStyleCenter, "left", "center", false);
		CellStyle strStyleRight = ExcelUtils.getDefaultStringStyle(wb);// 字符串居中靠左样式
		ExcelUtils.setAlign(strStyleRight, "right", "center", false);
		CellStyle strStyleCenterCenter = ExcelUtils.getDefaultStringStyle(wb);// 合并居中样式
		ExcelUtils.setAlign(strStyleCenterCenter, "center", "center", false);
		for (int i = 0; i < 50; i++) {
			sheet.setColumnWidth(i, 80 * 100);
		}
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 11);
		firstRowStyle.setWrapText(true);
		strStyle.setWrapText(true);
		strStyleCenter.setWrapText(true);
		int cellLine = 0;// 第几列
		Cell cell = row.createCell(0);
		cell.setCellValue("数据时间范围");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("供应商编号");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("供应商名称");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("占地面积");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("车间面积");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("年总产值");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		cell = row.createCell(cellLine);
		cell.setCellValue("企业总人数");
		cell.setCellStyle(firstRowStyle);
		cellLine++;
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售量(公斤)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsje())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售金额(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getMle())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("毛利额(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzball())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售量占比(占所有商品)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezball())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售金额占比(占所有商品)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezball())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("毛利额占比(占所有商品)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzbxfl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售量占比(占小分类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezbxfl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售金额占比(占小分类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezbxfl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("毛利额占比(占小分类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzbmxl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售量占比(占明细类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezbmxl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售金额占比(占明细类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezbmxl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("毛利额占比(占明细类)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdxl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("PSD销量(公斤)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdxsje())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("PSD销售金额(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdml())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("PSD毛利(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getQxs())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("权限数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getQxdt())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("权限店天");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getXsdt())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("销售店天");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getHyd())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("活跃度");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getAbcdmds())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("A门店数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue("B门店数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue("C门店数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue("D门店数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getJhl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("进货量(公斤)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getJhe())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("进货额(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getDdjsl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("订单及时率");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getDdmzl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("订单满足率");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getRbjssl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("让步接收数量(箱)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getRbjscs())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("让步接收次数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGyssjkc())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商实际库存(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGysaqkc())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商安全库存(元)");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getZlxj())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("质量星级");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getZhcs())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("召回次数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		/*if ("Y".equals(supplierComprehensiveAnalysisSet.getZhsl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("召回数量");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}*/
		if ("Y".equals(supplierComprehensiveAnalysisSet.getZhskugs())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("召回SKU个数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getCjbhgcs())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("抽检不合格次数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getCjbhgskus())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("抽检不合格SKU数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		/*if ("Y".equals(supplierComprehensiveAnalysisSet.getRchgl())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("入厂合格率");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}*/
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGysndqwyks())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商年度千万元客诉");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGysxcpf())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商巡厂评分");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGyskpdf())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商考评得分");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGyskpdfph())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商考评得分排行");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		if ("Y".equals(supplierComprehensiveAnalysisSet.getGysyxps())) {
			cell = row.createCell(cellLine);
			cell.setCellValue("供应商意向品数");
			cell.setCellStyle(firstRowStyle);
			cellLine++;
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			cellLine = 0;// 第几列
			cell = row.createCell(0);
			cell.setCellValue(list.get(i).getSjsjfw());
			cell.setCellStyle(strStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(list.get(i).getSupplierCode());
			cell.setCellStyle(strStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(list.get(i).getSupplierName());
			cell.setCellStyle(strStyle);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(formatNumber(list.get(i).getZdmj()));
			cell.setCellStyle(strStyleRight);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(formatNumber(list.get(i).getCjmj()));
			cell.setCellStyle(strStyleRight);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(formatNumber(list.get(i).getNzcz()));
			cell.setCellStyle(strStyleRight);
			cellLine++;
			cell = row.createCell(cellLine);
			cell.setCellValue(formatNumber(list.get(i).getQyzrs()).replace(".00", ""));
			cell.setCellStyle(strStyleRight);
			cellLine++;
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getXsl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsje())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getXsje()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getMle())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getMle()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzball())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXslzball()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezball())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXsjezball()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezball())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getMlezball()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzbxfl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXslzbxfl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezbxfl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXsjezbxfl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezbxfl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getMlezbxfl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXslzbmxl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXslzbmxl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsjezbmxl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getXsjezbmxl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getMlezbmxl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getMlezbmxl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdxl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getPsdxl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdxsje())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getPsdxsje()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getPsdml())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getPsdml()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getQxs())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getQxs()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getQxdt())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getQxdt()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getXsdt())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getXsdt()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getHyd())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getHyd(), 0));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getAbcdmds())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getAmds()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getBmds()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getCmds()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getDmds()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getJhl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getJhl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getJhe())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber2(list.get(i).getJhe()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getDdjsl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent(list.get(i).getDdjsl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getDdmzl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(StrUtils.formatterStrPercent((list.get(i).getDdmzl())));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getRbjssl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getRbjssl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getRbjscs())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getRbjscs()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGyssjkc())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGyssjkc()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGysaqkc())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGysaqkc()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getZlxj())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getZlxj()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getZhcs())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getZhcs()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getZhsl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getZhsl()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getZhskugs())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getZhskugs()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getCjbhgcs())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getCjbhgcs()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getCjbhgskus())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getCjbhgskus()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getRchgl())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(list.get(i).getRchgl());
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGysndqwyks())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGysndqwyks()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGysxcpf())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGysxcpf()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGyskpdf())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGyskpdf()));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGyskpdfph())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGyskpdfph()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
			if ("Y".equals(supplierComprehensiveAnalysisSet.getGysyxps())) {
				cell = row.createCell(cellLine);
				cell.setCellValue(formatNumber(list.get(i).getGysyxps()).replace(".00", ""));
				cell.setCellStyle(strStyleRight);
				cellLine++;
			}
		}

		try {
			wb.write(out);
		} catch (IOException e) {
			throw new EscmException("public.upload.io.error", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.flush();
				out.close();
			} catch (IOException e) {
				throw new EscmException("public.upload.io.error", new String[] { e.getMessage() });
			}
		}

	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * 
	 */
	private String  formatNumber(BigDecimal num)  {
		DecimalFormat df =new DecimalFormat("#,##0.00;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP); 
		if(num!=null){
			return df.format(num);
		}
		return "";
	}
	private String  formatNumber2(BigDecimal num)  {
		DecimalFormat df =new DecimalFormat("#,##0;(#)");
		df.setRoundingMode(RoundingMode.HALF_UP); 
		if(num!=null){
			return df.format(num);
		}
		return "";
	}
}