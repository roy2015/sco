package com.powere2e.sco.service.impl.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateTableDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateTableService;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateScore;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 供应商考评表业务类的实现
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public class SupplierEvaluateTableServiceImpl extends ServiceImpl implements SupplierEvaluateTableService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4030537605472324800L;
	private SupplierEvaluateTableDao supplierEvaluateTableDao;

	public static SupplierEvaluateTableService getInstance() {
		return (SupplierEvaluateTableService) ConfigFactory.getInstance().getBean("supplierEvaluateTableManager");
	}

	// 获得供应商考评表DAO实例
	public SupplierEvaluateTableDao getSupplierEvaluateTableDao() {
		return supplierEvaluateTableDao;
	}

	// 设置供应商考评表DAO实例
	public void setSupplierEvaluateTableDao(SupplierEvaluateTableDao supplierEvaluateTableDao) {
		this.supplierEvaluateTableDao = supplierEvaluateTableDao;
	}

	// 发布供应商考评表
	@Override
	public void insertSupplierEvaluateTable(List<SupplierEvaluateScore> list) {
		// 根据考评模板编号查出考评项编号，有几个考评项编号对应的模板就有几个考评项
		List<String> sei = this.getSupplierEvaluateTableDao().listSupplierEvaluateItem(list.get(0).toMap(), null);
		for (int i = 0; i < list.size(); i++) {
			// 考评表编号，几个供应商就有几个考评表编号
			String evaluateTableCode = MasterDataTypeServiceImpl.getInstance().nextID("S_SUPPLIER_EVALUATE");
			SupplierEvaluateScore ses = new SupplierEvaluateScore();
			for (int j = 0; j < sei.size(); j++) {
				ses.setTemplateCode(list.get(i).getTemplateCode());// 考评模板编号
				ses.setEvaluateTableCode(evaluateTableCode);// 考评表编号与供应商编号对应
				ses.setSupplierCode(list.get(i).getSupplierCode());// 考评表编号与供应商编号对应
				ses.setEvaluateItemCode(sei.get(j));// 考核项编号
				ses.setEvaluateItemScore(0);// 默认发布时的考评表的分数为0
				// 插入供应商考评分数－－考评表中的一个小项
				this.getSupplierEvaluateTableDao().insertSupplierEvaluateTable(ses.toMap());
			}
			// 插入供应商考评
			this.getSupplierEvaluateTableDao().insertSupplierEvaluate(ses.toMap());
		}
	}

	// 查询考评表/查询考评表模板
	@Override
	public List<SupplierEvaluateScore> listSupplierEvaluateTemplate(Map<String, Object> map, PageInfo pageInfo) {
		List<SupplierEvaluateScore> list = this.getSupplierEvaluateTableDao().listSupplierEvaluateTable(map, pageInfo);
		for (SupplierEvaluateTemplate l : list) {
			String str = l.getStatus();
			if (str.equals("CG")) {
				l.setStatus("草稿");
			} else if (str.equals("YFB")) {
				l.setStatus("已发布");
			} else if (str.equals("YGB")) {
				l.setStatus("已关闭");
			}
		}
		return list;
	}

	// 查询供应商
	@Override
	public List<Supplier> listSupplier(Map<String, Object> map, PageInfo pageInfo) {
		List<Supplier> list = (List<Supplier>) this.getSupplierEvaluateTableDao().listSupplier(map, pageInfo);
		return list;
	}

	// 根据供应商编号查询考评表编号，看是否已经发布过考评表
	@Override
	public List<SupplierEvaluateScore> listSupplierCode(Map<String, Object> map) {
		return this.getSupplierEvaluateTableDao().listSupplierCode(map);
	}

	// 查询考评表详情
	@Override
	public List<SupplierEvaluateScore> loadSupplierEvaluateTableItem(Map<String, Object> map) {
		List<SupplierEvaluateScore> list = this.getSupplierEvaluateTableDao().selectSupplierEvaluateScoreByEvaluateTableCode(map);
		if (list.size() > 0) {
			SupplierEvaluateScore supplierEvaluateScore = this.getSupplierEvaluateTableDao().selectSumScore(map);
			list.get(0).setScoreTotal(supplierEvaluateScore.getScoreTotal());
		}
		return list;
	}

	// 给供应商考评表打分
	@Override
	public void updateSupplierEvaluateScoreByEvaluateItemCode(Map<String, Object> map) {
		this.getSupplierEvaluateTableDao().updateSupplierEvaluateScoreByEvaluateItemCode(map);
	}

	// 导出execl
	@Override
	public void exportSupplierEvaluateTable(Map<String, Object> map, ServletOutputStream out) {
		List<SupplierEvaluateScore> list = this.loadSupplierEvaluateTableItem(map);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("供应商考评表详情");
		// 创建表头
		fillHeaderCell(wb, sheet, list.get(0).getTemplateType().equals("FL"));
		// 1.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCell(list, wb, sheet);
		}
		try {
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
	private void fillHeaderCell(Workbook wb, Sheet sheet, boolean bool) {
		// 第一行
		Row row = sheet.createRow(2);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("评分项");
		cell.setCellStyle(firstRowStyle);
		if (!bool) {
			for (int i = 1; i < 7; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "A3:G3");
		} else {
			for (int i = 1; i < 6; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "A3:F3");
		}
		// 第一行
		row = sheet.createRow(3);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(0);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("评分项名称");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("评分依据");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("评分标准");

		if (!bool) {
			cell = row.createCell(i);
			cell.setCellStyle(sencondRowStyle);
			sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
			cell.setCellValue("打分部门");
		}

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权重(%)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("分值");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权重分");

	}

	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            单据明细list
	 */
	private void fillDataCell(List<SupplierEvaluateScore> list, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		double sumScore = 0;
		for (int i = 0; i < list.size(); i++) {
			sumScore += list.get(i).getScore();
		}
		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, i, list.get(i), strStyle, dateStyle, amtStyle, sumScore);
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
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, SupplierEvaluateScore msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, double sumScore) {
		if (rowIndex == 0) {
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("考评表名称:" + msd.getTemplateName());
			for (int i = 1; i < 2; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "A1:B1");

			cell = row.createCell(2);
			cell.setCellStyle(strStyle);
			cell.setCellValue("考核周期:" + DateUtils.formatDateToStr(msd.getEvaluateStartDate(), "yyyy-MM-dd"));
			for (int i = 3; i < 4; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "C1:D1");

			cell = row.createCell(4);
			cell.setCellStyle(strStyle);
			cell.setCellValue("至:" + DateUtils.formatDateToStr(msd.getEvaluateEndDate(), "yyyy-MM-dd"));
			for (int i = 5; i < 6; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "E1:F1");

			row = sheet.createRow(1);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("供应商编号:" + msd.getSupplierCode());
			for (int i = 1; i < 2; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "A2:B2");

			cell = row.createCell(2);
			cell.setCellStyle(strStyle);
			cell.setCellValue("供应商名称:" + msd.getSupplierName());
			for (int i = 3; i < 4; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "C2:D2");

			cell = row.createCell(4);
			cell.setCellStyle(strStyle);
			cell.setCellValue("总分(总权重分):" + new DecimalFormat("#,##0.00;(#)").format(sumScore));
			for (int i = 5; i < 6; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(strStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "E2:F2");

		}
		int j = 0;
		Row row = sheet.createRow(rowIndex + 4);
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getEvaluateItemName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getAccording());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getStandard());

		if (!msd.getTemplateType().equals("FL")) {
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getDepartments());
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00;(#)").format(msd.getWeight()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00;(#)").format(msd.getEvaluateItemScore()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00;(#)").format(msd.getScore()));
	}
}
