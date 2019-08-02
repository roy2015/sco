package com.powere2e.sco.service.impl.merchandiseintention;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.merchandiseintention.ForetasteFeedbackDao;
import com.powere2e.sco.interfaces.dao.merchandiseintention.MerchandiseIntentionDao;
import com.powere2e.sco.interfaces.dao.merchandiseintention.MerchandiseQuotedDao;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;

/**
 * 商品意向品业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseIntentionServiceImpl extends ServiceImpl implements MerchandiseIntentionService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1898963804653668435L;
	private MerchandiseIntentionDao merchandiseIntentionDao;
	private MerchandiseQuotedDao merchandiseQuotedDao;
	private ForetasteFeedbackDao foretasteFeedbackDao;
	private Font font;

	public static MerchandiseIntentionService getInstance() {
		return (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionService");
	}

	// 查询
	@Override
	public List<MerchandiseIntention> listMerchandiseIntention(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return merchandiseIntentionDao.listMerchandiseIntention(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseIntention(MerchandiseIntention merchandiseIntention) throws Exception {
		merchandiseIntentionDao.insertMerchandiseIntention(merchandiseIntention.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandiseIntention(String intentionCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		// 删除一个意向品需要删除该意向品、对应的报价单、试吃反馈和已关联的供应商
		merchandiseIntentionDao.deleteMerchandiseIntention(map);
		merchandiseQuotedDao.deleteMerchandiseQuotedById(map);
		foretasteFeedbackDao.deleteForetasteFeedback(map);
		merchandiseIntentionDao.deleteIntentionSupplierMerchandise(map);
	}

	// 修改
	@Override
	public void updateMerchandiseIntention(MerchandiseIntention merchandiseIntention) throws Exception {
		merchandiseIntentionDao.updateMerchandiseIntention(merchandiseIntention.toMap());
	}

	// 加载一个商品意向品
	@Override
	public MerchandiseIntention loadMerchandiseIntention(String intentionCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		return merchandiseIntentionDao.loadMerchandiseIntention(map);
	}

	// 已关联的供应商列表
	@Override
	public List<IntentionSupplierMerchandise> listIntentionSupplierMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return merchandiseIntentionDao.listIntentionSupplierMerchandise(map, pageInfo);
	}

	// 新增关联的供应商
	@Override
	public void insertIntentionSupplierMerchandise(Map<String, Object> map) throws Exception {
		merchandiseIntentionDao.insertIntentionSupplierMerchandise(map);
	}

	// 删除关联的供应商
	@Override
	public void deleteIntentionSupplierMerchandise(Map<String, Object> map) throws Exception {
		merchandiseIntentionDao.deleteIntentionSupplierMerchandise(map);
	}

	// 意向供应商列表
	@Override
	public List<MerchandiseIntentionSupplier> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return merchandiseIntentionDao.listIntentionSupplier(map, pageInfo);
	}

	// 新增意向供应商
	@Override
	public void insertIntentionSupplier(Map<String, Object> map) throws Exception {
		merchandiseIntentionDao.insertIntentionSupplier(map);
	}

	// 判断供应商为正式供应商还是意向供应商
	@Override
	public MerchandiseIntentionSupplier findSupplierIsTrue(String supplierCode) throws Exception {
		return merchandiseIntentionDao.findSupplierIsTrue(supplierCode);
	}

	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		try {
			List<MerchandiseIntention> list = this.merchandiseIntentionDao.listMerchandiseIntention(map, null);
			SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
			Sheet sheet = wb.createSheet("意向品列表");
			font = wb.createFont();
			font.setFontName("微软雅黑");
			// 1. 创建excel表头(第一行和第二行)
			this.fillHeaderCell(wb, sheet);
			// 2.用数据填充单元格
			if (!list.isEmpty())
				this.fillDataCell(list, wb, sheet);
				wb.write(out);
			} catch (Exception e) {
				throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
			} finally {
				try {
					if (out != null)
						out.close();
				} catch (IOException e) {
					throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
				}
			}
	}
	
	/**
	 * 创建excel表头(第一行和第二行)
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            sheet
	 */
	private void fillHeaderCell(Workbook wb, Sheet sheet) {
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		CellStyle leftSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		leftSencondRowStyle.setFont(font);
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_LEFT);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		rightSencondRowStyle.setFont(font);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		int i = 0;// 从第零列(第一列)开始

		// 第一行
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(i);
		firstRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品名称");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商/意向供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商/意向供应商名称 ");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SAP物料号 ");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购部门");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否定量装");
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购类型");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售方式");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品创建日期");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品创建人");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("最后一次报价日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("最后一次试吃日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("最后一次试吃评分");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("最后一次试吃反馈");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否试吃通过");
	}
	
	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            数据
	 */
	private void fillDataCell(List<MerchandiseIntention> list, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		strStyle.setFont(font);
		
		CellStyle intStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		intStyle.setFont(font);
		intStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		intStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0"));
		
		CellStyle perStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		perStyle.setFont(font);
		perStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		perStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
		
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		amtStyle.setFont(font);
		amtStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		amtStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null) {
				continue;
			}
			writeOneRowDate(sheet, i, list.get(i), strStyle, amtStyle, perStyle, intStyle);
		}
	}

	/**
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowIndex
	 *            当前行
	 * @param list
	 *            数据list
	 * @param strStyle
	 *            字符串数据格式
	 * @param dateStyle
	 *            时间日期格式
	 * @param amtStyle
	 *            金额、数量格式
	 *@param perStyle
	 *            百分比格式
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, MerchandiseIntention merchandiseIntention, CellStyle strStyle,  CellStyle amtStyle, CellStyle perStyle,CellStyle intStyle) {
		Row row = sheet.createRow(rowIndex + 1);

		int j = 0;
		// 意向品编号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionCode());
		
		// 意向品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionName());
		
		// 供应商/意向供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionSupplierCode());
		
		// 供应商/意向供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIntentionSupplierName());
		
		// SAP物料号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getMerchandiseCode());
		
		// 采购部门
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPurchaseDepartmentsName());
		
		// 是否定量装
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getRationedName());
		
		// 采购类型
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getPurchaseTypeName());
		
		// 销售方式
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSaleType());
		
		// 中分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getCentreTypeName());
		
		// 小分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getSmallTypeName());
		
		// 明细类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getDetailTypeName());
		
		// 细分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getFineTypeName());
		
		// 意向品创建日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getCreated()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getCreated(), "yyyy-MM-dd"));
		
		// 意向品创建人
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getCreateUserName());
		
		// 最后一次报价日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getQuotedDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getQuotedDate(), "yyyy-MM-dd"));
		
		// 最后一次试吃日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getForetasteDate()==null?"":DateUtils.formatDateToStr(merchandiseIntention.getForetasteDate(), "yyyy-MM-dd"));

		// 最后一次试吃评分
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		BigDecimal foretasteGrade = merchandiseIntention.getForetasteGrade();
		if (foretasteGrade != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(foretasteGrade, "#,##0.00"));
		}
		
		// 最后一次试吃反馈
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getEvaluate());
		
		// 是否试吃通过
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(merchandiseIntention.getIsForetastePass());
	}
	
	@Override
	public MerchandiseIntentionSupplier findSupplierNameIsExists(String intentionSupplierName) throws Exception {
		return merchandiseIntentionDao.findSupplierNameIsExists(intentionSupplierName);
	}

	// 根据供应商编号判断该供应商在关联表中是否存在
	@Override
	public IntentionSupplierMerchandise findSupplierMerchandiseIsExists(Map<String, Object> map) throws Exception {
		return merchandiseIntentionDao.findSupplierMerchandiseIsExists(map);
	}

	@Override
	public List<MerchandiseIntention> findApplicationByIntentionCodes(Map<String, Object> map) throws Exception {
		return merchandiseIntentionDao.findApplicationByIntentionCodes(map);
	}

	// 获得商品意向品DAO实例
	public MerchandiseIntentionDao getMerchandiseIntentionDao() {
		return merchandiseIntentionDao;
	}

	// 设置商品意向品DAO实例
	public void setMerchandiseIntentionDao(MerchandiseIntentionDao merchandiseIntentionDao) {
		this.merchandiseIntentionDao = merchandiseIntentionDao;
	}

	public MerchandiseQuotedDao getMerchandiseQuotedDao() {
		return merchandiseQuotedDao;
	}

	public void setMerchandiseQuotedDao(MerchandiseQuotedDao merchandiseQuotedDao) {
		this.merchandiseQuotedDao = merchandiseQuotedDao;
	}

	public ForetasteFeedbackDao getForetasteFeedbackDao() {
		return foretasteFeedbackDao;
	}

	public void setForetasteFeedbackDao(ForetasteFeedbackDao foretasteFeedbackDao) {
		this.foretasteFeedbackDao = foretasteFeedbackDao;
	}
}