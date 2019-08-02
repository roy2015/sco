package com.powere2e.sco.service.impl.merchandiseoaapplication.fastimportoaapplication;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImport;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastimportoaapplication.wlinfo.FastImportWlInfoServiceImpl;

/**
 * 商品快速引进 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class ApplicationFastImportServiceImpl extends ServiceImpl implements
		ApplicationFastImportService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7872229621695882667L;
	private ApplicationFastImportDao fastImportApplicationDao;

	/**
	 * 供其他业务层调用
	 * 
	 * @return 商品快速引进 业务类实例
	 */
	public static ApplicationFastImportService getInstance() {
		return (ApplicationFastImportService) ConfigFactory.getInstance()
				.getBean("fastImportApplicationService");
	}

	public ApplicationFastImportDao getFastImportApplicationDao() {
		return fastImportApplicationDao;
	}

	public void setFastImportApplicationDao(
			ApplicationFastImportDao fastImportApplicationDao) {
		this.fastImportApplicationDao = fastImportApplicationDao;
	}

	@Override
	public String completeDelFastImport(String applicationCodes) {
		StringBuilder delIntSup = new StringBuilder();
		//校验包装设计申请和巡厂申请状态
		String appCodes = this.generateApplicationMerStatus(applicationCodes, delIntSup);
		
		String noDel = "";//不可删除的
		if (appCodes != null) {
			String[] appCodeArr = appCodes.split("\\|");
			applicationCodes = appCodeArr[0];//可删除的申请单
			if (appCodeArr.length > 1) noDel = appCodeArr[1]; 
		}
		if (StringUtils.isNotBlank(applicationCodes)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCodes", applicationCodes);
			boolean ifBatch = applicationCodes.contains("'");
			if (ifBatch) {//是否批量
				map.put("ifBatch", ifBatch);
			}
			try {
				//1.上传文件
				ApplicationFastadjustpriceServiceImpl.getInstance()
						.completeDeleteFastadjustpriceByApplicationCode(map);
				
				//2.sap物料号 、地区价格
				FastImportWlInfoServiceImpl.getInstance().completeDeleteWlInfo(
						applicationCodes, delIntSup.toString(), null, null);

				MerchandiseOaApplicationService merOaService = MerchandiseOaApplicationServiceImpl.getInstance();
				//3.删除商品巡厂申请单
				merOaService.deleteApplicationVisitFactory(map);
				merOaService.deleteApplicationVisitFactoryM(map);
				//4.商品包装设计申
				merOaService.deleteApplicationPackageDesign(map);
				merOaService.deleteApplicationPackageDesignM(map);
			} catch (Exception e) {
				e.printStackTrace();
				throw new EscmException("删除快速申请单时出错!");
			}
		}
		return noDel;
	}

	/**
	 * 校验申请单状态
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @param oaType
	 *            审批类型
	 * @param intentionAndSupplierCodes
	 *            意向品编号和供应商编号组
	 */
	@Override
	public void validateApplicationStatus(String applicationCode,
			String oaType, String intentionAndSupplierCodes) {
		/*String receiptInfo;
		try {
			receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance()
					.getIntentionOaApplicationReceiptInfo(applicationCode,
							intentionAndSupplierCodes, oaType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
		}
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equalsIgnoreCase(receiptInfo)) {
			throw new EscmException("勾选的数据做了不同的OA申请，不能进行后续操作");
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString().equalsIgnoreCase(receiptInfo)) {
			throw new EscmException("选择的数据不是\"草稿\"或\"驳回\"状态，无法修改");
		}*/
	}

	/**
	 * 删除审批单时验证商品(包装设计申请和巡厂申请)状态
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @return 可删除的+'|'+不可删除的
	 */
	private String generateApplicationMerStatus(String applicationCode, StringBuilder delIntSup) {
	Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		map.put("vpStatus", BusinessConstants.VisitApplicationStatus.BH.toString());//巡厂申请、包装设计申请状态'驳回'的字符
		List<String> list = this.fastImportApplicationDao.listApplicationMerStatus(map);//不可删除的申请单
		StringBuilder del = new StringBuilder();
		StringBuilder noDel = new StringBuilder();
		if (list != null) {//所选部分审批单不能删除
			String[] appCodeArr = applicationCode.split(",");
			for (String str : appCodeArr) {
				if (list.contains(str)) {
					if(noDel.length() > 0){
						noDel.append(",");
					}
					noDel.append(str);//不可删除
				} else {
					if(del.length() > 0){
						del.append(",");
					}
					del.append(str);//可删除
				}
			}
		} else {//所选审批单均可删除
			return "";
		}
		return this.filterNotCGApp(del, noDel, delIntSup);
	}
	
	private String filterNotCGApp(StringBuilder del,
			StringBuilder noDel, StringBuilder delIntSup) {
	Map<String, Object> paraMap = new HashMap<String, Object>();
		if(StringUtils.isBlank(del)) return "".concat("|").concat(noDel.toString());
		paraMap.put("applicationCode", del);
		paraMap.put("oaAppStatus", BusinessConstants.ApplicationStatus.CG.toString());
		//草稿状态及对应的意向品+供应商
		List<ApplicationFastImport> list = this.fastImportApplicationDao.listApplicationAndMer(paraMap);
		StringBuilder rsDel = new StringBuilder();//用于存储最后需要删除的申请单号 
		for (ApplicationFastImport ai : list) {
			if(rsDel.indexOf(ai.getApplicationCode()) == -1){//如若已添加,则本次不添加
				if (rsDel.length() > 0){
					rsDel.append(",");
				}
				rsDel.append(ai.getApplicationCode());
			}
			if (delIntSup.length() > 0){//但是意向品+供应商需要添加
				delIntSup.append(",");
			}
			delIntSup.append(ai.getIntSuppcode());
		}
		
		String[] tmpD = del.toString().split(",");//未过滤非草稿状态的审批单号
		for (String str : tmpD) {
			if (rsDel.indexOf(str) == -1){//不包含的则是不能删除的
				if (rsDel.length() > 0) {
					noDel.append(",");
				} 
				noDel.append(str);
			}
		}
		return (rsDel.append("|").append(noDel)).toString();
	}
	
	@Override
	public void exportFastImportToExcel(List<MerchandiseIntention> list,
			ServletOutputStream out) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品快速引进OA申请列表");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		CellStyle redStrStyle = ExcelUtils.getDefaultStringStyle(wb);  //getRedStringStyle(wb);// 红色样式
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		strStyle.setFont(font);
		amtStyle.setFont(font);
		redStrStyle.setFont(font);
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet, strStyle, redStrStyle, amtStyle);
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常",
					new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常",
						new String[] { e.getMessage() });
			}
		}
	}

	/**
	 * 创建excel表头(第一行和第二行)
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 * @param strStyle
	 *            字符串样式
	 * @param amtStyle 
	 */
	private void fillHeaderCell(Workbook wb, Sheet sheet) {
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		CellStyle leftSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		Font font = wb.createFont();
		font.setFontName("微软雅黑");
		leftSencondRowStyle.setFont(font);
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_CENTER);
		leftSencondRowStyle.setWrapText(true);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		rightSencondRowStyle.setFont(font);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_CENTER);
		rightSencondRowStyle.setWrapText(true);
		int i = 0;// 从第零列(第一列)开始

		// 第一行
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(i);
		firstRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SCO申请单号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("意向品名称");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SAP物料号 ");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

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
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准总天数");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际总天数");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("新品引进申请单状态");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("新品引进申请审批环节");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("巡厂申请审批状态");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("巡厂申请审批环节");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计申请审批状态");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计申请审批环节");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("OA新品引进申请单创建人");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("样品收集日期");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("试吃通过日期");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r试吃通过日-样品收集日");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数 \r试吃通过日-样品收集日");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("巡厂申请提交日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r 巡厂申请日-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r 巡厂申请日-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("巡厂申请审批通过日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r巡厂申请报批完成日-巡厂申请提交日");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r巡厂申请报批完成日-巡厂申请提交日");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("品控实际巡厂日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r实际巡厂日-巡厂申请");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r实际巡厂日-巡厂申请");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("新品引进申请提交日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数 \r新品申请提交-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r新品申请提交-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("新品引进审批通过日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r引进报告通过-引进报告申请");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r引进报告通过-引进报告申请");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
		cell.setCellValue("物料主数据申请审批通过日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数 \r主数据申请通过-引进报告通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数 \r主数据申请通过-引进报告通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计申请提交日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r 包装设计申请-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r 包装设计申请-试吃通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计审批通过日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数 \r包装设计申请-包装设计通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数 \r包装设计申请-包装设计通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计初稿确认日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r设计初稿确认-包装设计通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r设计初稿确认-包装设计通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("包装设计完稿通过日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r设计完稿确认-主数据申请通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (85 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r设计完稿确认-主数据申请通过");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r设计完稿确认-设计初稿确认");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r设计完稿确认-设计初稿确认");
		
		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("新品发放日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("标准天数\r新品发放日-设计完稿确认");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际天数\r新品发放日-设计完稿确认  ");
	}

	/**
	 * 用数据填充单元格
	 * 
	 * @param list
	 *            数据
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 * @param strStyle
	 *            字符串样式
	 * @param redStrStyle
	 *            红色字符串样式
	 */
	private void fillDataCell(List<MerchandiseIntention> list, Workbook wb,
			Sheet sheet, CellStyle strStyle, CellStyle redStrStyle, CellStyle amtStyle) {
		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, i, list.get(i), strStyle, redStrStyle, amtStyle);
		}
	}

	/**
	 * 为excel一行的数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param rowIndex
	 *            当前行
	 * @param merchandiseIntention
	 *            意向品实例
	 * @param strStyle
	 *            字符串样式
	 * @param redStrStyle
	 *            红色字符串样式
	 */
	private void writeOneRowDate(Sheet sheet, int rowIndex,
			MerchandiseIntention mi, CellStyle strStyle, 
			CellStyle redStrStyle, CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 1);

		int j = 0;
		// SCO申请单号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getApplicationCode());

		// 意向品编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getIntentionCode());

		// 意向品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getIntentionName());

		// SAP物料号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getMerchandiseCode());

		// 供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getIntentionSupplierCode());

		// 供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getIntentionSupplierName());

		// 商品定性角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getDxRoleName());

		// 商品定量角色
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getDlRoleName());

		// 采购部门
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getPurchaseDepartmentsName());

		// 是否定量装
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getRationedName());

		// 采购类型
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getPurchaseTypeName());

		// 销售方式
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getSaleType());

		// 中分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getCentreTypeName());

		// 小分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getSmallTypeName());

		// 明细类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getDetailTypeName());

		// 细分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getFineTypeName());

		// 标准总天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzDay() == null ? "" : mi.getBzDay().toString());
				
		// 实际总天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getSjDay() == null ? "" : mi.getSjDay().toString());
				
		// 快速引进申请单状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getApplicationStatusName());

		// 快速引进申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getApplicationLink());
		
		// 巡厂申请审批状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getVisitApplicationStatusName());

		// 巡厂申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getVisitApplicationLink());
		
		// 包装设计申请审批状态
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getPackageApplicationStatusName());

		// 包装设计申请审批环节
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getPackageApplicationLink());
	
		// OA快速引进申请单创建人
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(mi.getCreateUserName());

		// 样品收集日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date created = mi.getCreated();
		if (created != null) {
			String createdStr = DateUtils.formatDateToStr(created,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(createdStr);
		}

		// 试吃通过日期
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date foretastePassDate = mi.getForetastePassDate();
		if (foretastePassDate != null) {
			String foretastePassDateStr = DateUtils.formatDateToStr(
					foretastePassDate, Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(foretastePassDateStr);
		}

		// 试吃通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getSctgBzDay() == null ? "" : mi.getSctgBzDay().toString());
		
		// 试吃通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getSctgSjDay() == null ? "" : mi.getSctgSjDay().toString());
		
		// 巡厂申请提交日期
		cell = row.createCell(j++);
		Integer xcsqtjStatus = mi.getXcsqtjStatus();
		if (xcsqtjStatus != null && 1 == xcsqtjStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date xcsqtjDate = mi.getXcsqtjDate();
		if (xcsqtjDate != null) {
			String xcsqtjDateStr = DateUtils.formatDateToStr(xcsqtjDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(xcsqtjDateStr);
		}

		// 巡厂申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXcsqtjBzDay() == null  ? "" : mi.getXcsqtjBzDay().toString());
		
		// 巡厂申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXcsqtjSjDay() == null ? "" : mi.getXcsqtjSjDay().toString());
		
		// 巡厂申请审批通过日期  
		cell = row.createCell(j++);
		Integer xcsqshtgStatus = mi.getXcsqshtgStatus();
		if (xcsqshtgStatus != null && 1 == xcsqshtgStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date xcsqshtgDate = mi.getXcsqshtgDate();
		if (xcsqshtgDate != null) {
			String xcsqtjDateStr = DateUtils.formatDateToStr(xcsqshtgDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(xcsqtjDateStr);
		}

		// 巡厂申请审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXcsqshtgBzDay() == null ? "" : mi.getXcsqshtgBzDay().toString());
		
		// 巡厂申请审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXcsqshtgSjDay() == null ? "" : mi.getXcsqshtgSjDay().toString());
		
		//品控实际巡厂日期
		cell = row.createCell(j++);
		Integer pksjxcStatus = mi.getPksjxcStatus();
		if (pksjxcStatus != null && 1 == pksjxcStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date pksjxcDate = mi.getPksjxcDate();
		if (pksjxcDate != null) {
			String pksjxcDateStr = DateUtils.formatDateToStr(pksjxcDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(pksjxcDateStr);
		}

		// 品控实际巡厂标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getPksjxcBzDay() == null ? "" : mi.getPksjxcBzDay().toString());
		
		// 品控实际巡厂实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getPksjxcSjDay() == null ? "" : mi.getPksjxcSjDay().toString());
				
		//快速引进申请提交日期
		cell = row.createCell(j++);
		Integer xpyjbgtjStatus = mi.getXpyjbgtjStatus();
		if (xpyjbgtjStatus != null && 1 == xpyjbgtjStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date xpyjbgtjDate = mi.getXpyjbgtjDate();
		if (xpyjbgtjDate != null) {
			String xpyjbgtjDateStr = DateUtils.formatDateToStr(xpyjbgtjDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(xpyjbgtjDateStr);
		}

		// 快速引进申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpyjbgtjBzDay() == null ? "" : mi.getXpyjbgtjBzDay().toString());
		
		// 快速引进申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpyjbgtjSjDay() == null ? "" : mi.getXpyjbgtjSjDay().toString());
		
		//快速引进审批通过日期
		cell = row.createCell(j++);
		Integer xpyjbgsptgStatus = mi.getXpyjbgsptgStatus();
		if (xpyjbgsptgStatus != null && 1 == xpyjbgsptgStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date xpyjbgsptgDate = mi.getXpyjbgsptgDate();
		if (xpyjbgsptgDate != null) {
			String xpyjbgsptgDateStr = DateUtils.formatDateToStr(
					xpyjbgsptgDate, Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(xpyjbgsptgDateStr);
		}

		// 快速引进审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpyjbgsptgBzDay() == null ? "" : mi.getXpyjbgsptgBzDay().toString());
		
		// 快速引进审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpyjbgsptgSjDay() == null ? "" : mi.getXpyjbgsptgSjDay().toString());
		
		//物料主数据申请审批通过日期
		cell = row.createCell(j++);
		Integer zsjsqsptgStatus = mi.getZsjsqsptgStatus();
		if (zsjsqsptgStatus != null && 1 == zsjsqsptgStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date zsjsqsptgDate = mi.getZsjsqsptgDate();
		if (zsjsqsptgDate != null) {
			String zsjsqsptgDateStr = DateUtils.formatDateToStr(zsjsqsptgDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(zsjsqsptgDateStr);
		}
		
		// 物料主数据申请审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getZsjsqsptgBzDay() == null ? "" : mi.getZsjsqsptgBzDay().toString());
		
		// 物料主数据申请审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getZsjsqsptgSjDay() == null ? "" : mi.getZsjsqsptgSjDay().toString());
		
		//包装设计申请提交日期 
		cell = row.createCell(j++);
		Integer bzsjsqStatus = mi.getBzsjsqStatus();
		if (bzsjsqStatus != null && 1 == bzsjsqStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date bzsjsqDate = mi.getBzsjsqDate();
		if (bzsjsqDate != null) {
			String bzsjsqDateStr = DateUtils.formatDateToStr(bzsjsqDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(bzsjsqDateStr);
		}

		// 包装设计申请提交标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjsqBzDay() == null ? "" : mi.getBzsjsqBzDay().toString());

		// 包装设计申请提交实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjsqSjDay() == null ? "" : mi.getBzsjsqSjDay().toString());

		//包装设计审批通过日期
		cell = row.createCell(j++);
		Integer bzsjsqwcStatus = mi.getBzsjsqwcStatus();
		if (bzsjsqwcStatus != null && 1 == bzsjsqwcStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date bzsjsqwcDate = mi.getBzsjsqwcDate();
		if (bzsjsqwcDate != null) {
			String bzsjsqwcDateStr = DateUtils.formatDateToStr(bzsjsqwcDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(bzsjsqwcDateStr);
		}

		// 包装设计审批通过标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjsqwcBzDay() == null ? "" : mi.getBzsjsqwcBzDay().toString());

		// 包装设计审批通过实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjsqwcSjDay() == null ? "" : mi.getBzsjsqwcSjDay().toString());
		
		//包装设计初稿确认日期 
		cell = row.createCell(j++);
		Integer bzsjcgqrStatus = mi.getBzsjcgqrStatus();
		if (bzsjcgqrStatus != null && 1 == bzsjcgqrStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date bzsjcgqrDate = mi.getBzsjcgqrDate();
		if (bzsjcgqrDate != null) {
			String bzsjcgqrDateStr = DateUtils.formatDateToStr(bzsjcgqrDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(bzsjcgqrDateStr);
		}

		// 包装设计初稿确认标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjcgqrBzDay() == null ? "" : mi.getBzsjcgqrBzDay().toString());

		// 包装设计初稿确认实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getBzsjcgqrSjDay() == null ? "" : mi.getBzsjcgqrSjDay().toString());

		//包装设计完稿通过日期
		cell = row.createCell(j++);
		Integer bzsjwgtgStatus = mi.getBzsjwgtgStatus();
		if (bzsjwgtgStatus != null && 1 == bzsjwgtgStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date bzsjwgtgDate = mi.getBzsjwgtgDate();
		if (bzsjwgtgDate != null) {
			String bzsjwgtgDateStr = DateUtils.formatDateToStr(bzsjwgtgDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(bzsjwgtgDateStr);
		}

		// 距离物料主数据申请审批通过日期标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getJlbzsjcgqrBzDay() == null ? "" : mi.getJlbzsjcgqrBzDay().toString());

		// 距离物料主数据申请审批通过日期实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getJlbzsjcgqrSjDay() == null ? "" : mi.getJlbzsjcgqrSjDay().toString());

		// 距离包装设计初稿确认日期标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getJlwlzsjsqsptgBzDay() == null ? "" : mi.getJlwlzsjsqsptgBzDay().toString());

		// 距离包装设计初稿确认日期实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getJlwlzsjsqsptgSjDay() == null ? "" : mi.getJlwlzsjsqsptgSjDay().toString());

		//新品发放日期 
		cell = row.createCell(j++);
		Integer xpffStatus = mi.getXpffStatus();
		if (xpffStatus != null && 1 == xpffStatus) {
			cell.setCellStyle(redStrStyle);
		} else {
			cell.setCellStyle(strStyle);
		}
		Date xpffDate = mi.getXpffDate();
		if (xpffDate != null) {
			String xpffDateStr = DateUtils.formatDateToStr(xpffDate,
					Constant.DATA_INTEFACE_DATEFORMATE);
			cell.setCellValue(xpffDateStr);
		}
		
		// 新品发放标准天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpffBzDay() == null ? "" : mi.getXpffBzDay().toString());

		// 新品发放实际天数
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(mi.getXpffSjDay() == null ? "" : mi.getXpffSjDay().toString());
	}

}