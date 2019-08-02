package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.signedquantity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.signedquantity.SignedQuantityDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.signedquantity.SignedQuantityService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity.SignedQuantity;
import com.powere2e.sco.model.masterdata.MerchandiseReceipt;
import com.powere2e.sco.service.impl.masterdata.MerchandiseReceiptServiceImpl;

/**
 * 签量数据维护Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public class SignedQuantityServiceImpl extends ServiceImpl implements
		SignedQuantityService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8706563568612476313L;
	private SignedQuantityDao signedQuantityDao;

	// 获取signedQuantity Dao实例
	public SignedQuantityDao getSignedQuantityDao() {
		return signedQuantityDao;
	}

	// 设置signedQuantity Dao实例
	public void setSignedQuantityDao(SignedQuantityDao signedQuantityDao) {
		this.signedQuantityDao = signedQuantityDao;
	}

	@Override
	public List<SignedQuantity> listSignedQuantity(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.signedQuantityDao.listSignedQuantity(map, pageInfo);
	}

	@Override
	public SignedQuantity searchSignedQtyMain(Map<String, Object> map) {
		return this.signedQuantityDao.searchSignedQtyMain(map);
	}

	@Override
	public List<SignedQuantity> listSignedQtyDetail(Map<String, Object> map) {
		return this.signedQuantityDao.listSignedQtyDetail(map);
	}
	
	@Override
	public void updateSignedQtyMain(Map<String, Object> map){
		this.signedQuantityDao.updateSignedQtyMain(map);
	}
	
	@Override
	public String completeInsertSignedQuantity(Map<String, Object> validMap,
			SignedQuantity signedQuantity, List<SignedQuantity> list) {
		// 1.校验重复
		String msg = this.validateSignedQuantity(validMap);
		if (!StringUtils.isBlank(msg)) return msg;
		
		signedQuantity.setQlStatus(BusinessConstants.signedQuantityType.A.toString());
		// 2.插入数据(总体、明细)
		this.saveSignedQuantityQl(signedQuantity);
		this.saveSignedQuantityDetail(list);
		// 3.计算相关数据
		String merchandiseCodes = validMap.get("merchandiseCodes").toString();
		String supplierCodes = validMap.get("supplierCodes").toString();
		this.completeCalculateTodaySignedQtyByMerRec(merchandiseCodes, supplierCodes);
		return "";
	}

	@Override
	public void completeGqSignedQty(String oldCode,
			SignedQuantity signedQuantity, List<SignedQuantity> list) {
		//1.修改原签量单状态
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("qlCode", "'"+oldCode+"'");
		map.put("gqCode", signedQuantity.getQlCode());//对应的改签单号
		map.put("qlStatus", BusinessConstants.signedQuantityType.D.toString());//已改签
		this.updateSignedQtyMain(map);

		// 2.插入数据(总体、明细)
		this.saveSignedQuantityQl(signedQuantity);
		this.saveSignedQuantityDetail(list);
		
		// 3.计算
		this.completeCalculateTodaySignedQtyByMerRec(signedQuantity.getMerchandiseCode(), signedQuantity.getSupplierCode());
	}
	
	@Override
	public String completeUpdateSignedQty(Map<String, Object> validMap, SignedQuantity pubData,
			List<SignedQuantity> list) {
		Map<String, Object> map = pubData.toMap(); 
		//0.校验数据
		validMap.put("otherCondition", "UPPER(ql.ql_code) not in ('"+ pubData.getQlCode() +"')"); //编辑验证需过滤掉自己
		String msg = this.validateSignedQuantity(validMap);
		if (!StringUtils.isBlank(msg)) return msg;
		
		map.put("qlCode", "'"+pubData.getQlCode()+"'");
		//1.更新主信息
		this.signedQuantityDao.updateSignedQtyMain(map);
		//2.删除明细信息(已删除的商品)
		map.put("merchandiseCodes", pubData.getMerchandiseCode());
		this.signedQuantityDao.deleteSignedQtyDetail(map);
		//3.新增或修改明细信息
		map.put("list", list);
		this.signedQuantityDao.insOrUpdSignedQty(map);
		//4.计算
		String merchandiseCodes = validMap.get("merchandiseCodes").toString();
		String supplierCodes = validMap.get("supplierCodes").toString();
		this.completeCalculateTodaySignedQtyByMerRec(merchandiseCodes, supplierCodes);
		return "";
	}
	
	@Override
	public void deleteSignedQuantity(String qlCode) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("qlCode", qlCode);
		//1.需删除的所有相关的签量
		qlCode = this.signedQuantityDao.searchDelQlCode(map);//查询该链路的所有签量编号
		map.put("qlCode", qlCode);
		//2.所影响到所有的商品编号
		List<String> merSupCodes = this.signedQuantityDao.listMerCodeAndSuppCodeByQlCode(map);
		//3.删除签量主信息
		this.signedQuantityDao.deleteSignedQtyMain(map);
		//4.删除签量明细数据
		this.signedQuantityDao.deleteSignedQtyDetail(map);
		//5.计算
		for (String msCode : merSupCodes) {
			String msArr[] = msCode.split(",");
			this.completeCalculateTodaySignedQtyByMerRec("'" + msArr[0] + "'", "'" + msArr[1] + "'");
		}
	}
	
	@Override
	public void completeTerSignedQuantity(String qlCode) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("qlCode", qlCode);
		//1.所影响到所有的商品编号
		List<String> merSupCodes = this.signedQuantityDao.listMerCodeAndSuppCodeByQlCode(map);
		map.put("terStatus", BusinessConstants.signedQuantityType.T.toString());
		//2.更新状态
		this.updateSignedQtyMain(map);
		//3.计算
		for (String msCode : merSupCodes) {
			String msArr[] = msCode.split(",");
			this.completeCalculateTodaySignedQtyByMerRec("'" + msArr[0] + "'", "'" + msArr[1] + "'");
		}
	}
	
	@Override
	public void completeRevokerTerSignedQuantity(String qlCode) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("qlCode", qlCode);
		//1.所影响到所有的商品编号
		List<String> merSupCodes = this.signedQuantityDao.listMerCodeAndSuppCodeByQlCode(map);
		map.put("revokerStatus", "revokerStatus");
		//2.更改状态
		this.updateSignedQtyMain(map);
		//3.计算
		for (String msCode : merSupCodes) {
			String msArr[] = msCode.split(",");
			this.completeCalculateTodaySignedQtyByMerRec("'" + msArr[0] + "'", "'" + msArr[1] + "'");
		}
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		List<SignedQuantity> list = this.listSignedQuantity(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("签量数据列表");

		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet);
		try {
			wb.write(out);
		} catch (IOException e) {
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
	 * 保存签量数据整体信息
	 * 
	 * @param signedQuantity
	 *            签量整体信息
	 */
	private void saveSignedQuantityQl(SignedQuantity signedQuantity) {
		this.signedQuantityDao.insertSignedQuantityQl(signedQuantity.toMap());
	}
	
	/**
	 * 检验当前添加签量数据的是否有重复
	 * 
	 * @param list
	 */
	private String validateSignedQuantity(Map<String, Object> map) {
		if (this.listSignedQuantity(map, null).size() > 0) {//此处merchandiseCodes为大写查询
			return "所选商品中已存在相同开始日期的签量记录，不可重复添加！";
		}
		return null;
	}

	/**
	 * 保存新增的签量数据
	 * 
	 * @param list
	 *            签量list
	 */
	private void saveSignedQuantityDetail(List<SignedQuantity> list) {
		// 批量插入
		Map<String, Object> map = new HashMap<String, Object>();
		// List分批插入(每批100条)
		int listSize = list.size();
		int maxCount = listSize / Constant.BATCH_SIZE;
		int i = 0;
		for (; i < maxCount; i++) {
			int toIndex = (i + 1) * Constant.BATCH_SIZE > listSize ? listSize
					: (i + 1) * Constant.BATCH_SIZE;
			map.put("list", list.subList(i * Constant.BATCH_SIZE, toIndex));
			this.signedQuantityDao.insertSignedQuantityDetail(map);
		}
		// 防止遗漏数据
		int currentIndex = i * Constant.BATCH_SIZE;
		if (currentIndex < list.size()) {
			map.put("list", list.subList(currentIndex, list.size()));
			this.signedQuantityDao.insertSignedQuantityDetail(map);
		}
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
	private void fillDataCell(List<SignedQuantity> list, Workbook wb,
			Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 日期样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		for (int i = 0; i < list.size(); i++) {
			this.writeOneRowData(sheet, i, list.get(i), strStyle, dateStyle,
					amtStyle);
		}
	}

	/**
	 * Excel写入一行数据
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
	private void writeOneRowData(Sheet sheet, Integer rowIndex,
			SignedQuantity signedQty, CellStyle strStyle, CellStyle dateStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getQlCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date arrivalDate = signedQty.getQlCreated();
		if (arrivalDate != null)
			cell.setCellValue(DateUtils.formatDateToStr(arrivalDate, Constant.DATA_INTEFACE_DATEFORMATE));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getMerchandiseName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getSupplierName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getDxRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getDlRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getCentreName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getSmallName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getDetailName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getFineName());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal returnQty = signedQty.getxPrice();
		if (returnQty != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(returnQty, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date qlStarted = signedQty.getQlStartDate();
		if (qlStarted != null)
			cell.setCellValue(DateUtils.formatDateToStr(qlStarted, Constant.DATA_INTEFACE_DATEFORMATE));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal untaxedAmt = signedQty.getQlPrice();
		if (untaxedAmt != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(untaxedAmt, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal qlCount = signedQty.getQlCount();
		if (qlCount != null)
			cell.setCellValue(DecimalFormatUtils.formatBigDecimal(qlCount, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal proCount = signedQty.getProCount();
		if (proCount != null)
			cell.setCellValue(DecimalFormatUtils.formatBigDecimal(proCount, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal proPercent = signedQty.getProPercent();
		if (proPercent != null) 
			cell.setCellValue(DecimalFormatUtils.formatBigPercent(proPercent));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date finshDate = signedQty.getFinshDate();
		if (finshDate != null) cell.setCellValue(DateUtils.formatDateToStr(finshDate, Constant.DATA_INTEFACE_DATEFORMATE));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal beyondCount = signedQty.getBeyondCount();
		if (beyondCount != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(beyondCount, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getQlStatus());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getIsSatisfyBeforeQl());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getGqCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getQlCreateby());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(signedQty.getRemarks());

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
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("签量记录");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 25; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:Y1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量单号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量单创建日期");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("X001进价（元/kg）");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量开始日期");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量价格（元/kg）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签订数量（kg）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("已完成量（kg）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("已完成百分比");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量满足日期");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("超出量（kg）");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量单状态");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("是否优先满足开始日期较早的签量记录");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("改签单号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("签量人");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("备注");
	}

	/**
	 * 定时任务：计算签量数据[查询当天的数据] 
	 * 	<li>统计退货数据[即收获的数量为负数]时会有问题</li>
	 */
	@Override
	public void completeQuartzCalculateQty() {
		this.completeCalculateTodaySignedQtyByMerRec(null, null);
	}

	// 测试方法
	/*private static Date calDate = null;
	private void changeDate() {
		if (calDate == null) {
			calDate = DateUtils.getCalendar().getTime();
		} else {
			calDate.setDate(calDate.getDate() + 1);
		}
	}*/
	/**
	 * 计算签量数据
	 * 	<li>统计退货数据[即收获的数量为负数]时会有问题</li>
	 * 
	 * @param merchandiseCodes
	 *            商品编号
	 * @param supplierCodes 
	 */
	private void completeCalculateTodaySignedQtyByMerRec(String merchandiseCodes, String supplierCodes) {
		//设置时间区间
		Map<String, Object> map = new HashMap<String, Object>(); 
		Date[] dateArr = null;
		Boolean flag = StringUtils.isBlank(merchandiseCodes);//是否为定时任务 
		
		if(flag) {
			dateArr = DateUtils.getYesterdayArea();		//定时任务(不需要重新计算所有)
			map.put("realityReceiptDate", dateArr[0]);
			
			//测试方法
			/*changeDate();
			map.put("realityReceiptDate", calDate);*/
		} else {										//非定时任务(需要重新计算所有)
			map.put("merchandiseCodes", merchandiseCodes.toUpperCase());
			map.put("supplierCodes", supplierCodes.toUpperCase());
		}
		
		// 1.查询当天收货信息表
//		<!--签量变更  2016/3/29 统计签量完成情况时统计负数的收货记录 -->
		map.put("ifQty", true);//不统计退货的数据 
		List<MerchandiseReceipt> merRecList = MerchandiseReceiptServiceImpl
				.getInstance().listSumTodayMerchandiseReceipt(map);
		if(merRecList.isEmpty()) return ;
		
		//用于存储已被重置统计数量的签量
		List<String> clearList = new ArrayList<String>(); 
		for (MerchandiseReceipt mr : merRecList) {
			map.put("merchandiseCodes", "'" + (mr.getMerchandiseCode() == null ? "" : mr.getMerchandiseCode()).toUpperCase() + "'");
			map.put("supplierCodes", "'" + (mr.getSupplierCode() == null ? "" : mr.getSupplierCode()).toUpperCase() + "'");
			map.put("qlStartDate", mr.getRealityReceiptDate());
			
			//2.查询签量表
			List <SignedQuantity> qtyList = this.signedQuantityDao.listCalculateSignedQuantity(map);
			BigDecimal receiveCount = mr.getReceiptRationed();
			if (qtyList.isEmpty() || receiveCount == null || receiveCount.compareTo(BigDecimal.ZERO) == 0) continue ;
			
			//3.获取"不满足上一条签量"的数据的下标
			int j = this.getNotStatisBeforeQlIndex(qtyList, 0);
			//需将签量已统计的数据清零   --非定时任务(需要重新计算所有)
			if (!flag) {
				this.resetSignedQtyCalculate(qtyList, clearList);
			}
			//4.根据3中的下标开始计算
			Date RealityReceiptDate = (mr.getRealityReceiptDate() == null ? dateArr[0] : mr.getRealityReceiptDate());
			this.quartzCalculateList(qtyList, j, receiveCount, RealityReceiptDate);//定时(无需重新计算)
			//5.将该商品的统计量保存数据库
			if (flag) {	//定时任务,只需要更新受影响的即可
				this.updateSignedQtyCalculate(qtyList.subList(0, j + 1));
			} else {	//--非定时任务(需要重新计算所有,清零的需要更新到数据库)
				this.updateSignedQtyCalculate(qtyList);
			}
		}
	}

	/**
	 * 获取"不满足前一条签量"的数据
	 * 
	 * @param list
	 *            签量数据
	 * @param index
	 *            下标
	 * @return 不满足前一条签量的数据下标
	 */
	private int getNotStatisBeforeQlIndex(List<SignedQuantity> list, int index) {
		if (index == list.size() - 1 ) return index;
		if ("N".equalsIgnoreCase(list.get(index).getIsSatisfyBeforeQl())) { 		//查询出"不在满足前一条签量"为N的数据，如果没有那就是第一条
			return index;
		} else {
			return getNotStatisBeforeQlIndex(list, index + 1 );
		}
	}

	/**
	 * 计算各个签量完成情况
	 * 
	 * @param list
	 *            签量单
	 * @param index
	 *            下标
	 * @param receiveCount
	 *            收货单数量
	 * @param finishDate
	 *            完成日期
	 */
	private void quartzCalculateList(List<SignedQuantity> list, int index,
			BigDecimal receiveCount, Date finishDate) {
		if (index == -1) return;
		SignedQuantity sg = list.get(index);
		BigDecimal qlCount = sg.getQlCount();// 签订数量
		BigDecimal proCount = sg.getProCount();// 已完成量
		
		BigDecimal remainCount = receiveCount.add(proCount).subtract(qlCount);//剩下的
		int flag = remainCount.compareTo(BigDecimal.ZERO);//是否有多余的数量
		
		if(flag != -1) {//有多于的数量或者刚好用完
			if (sg.getFinshDate() == null) {//若之前的商品数据就已经满足了签量数量
				sg.setFinshDate(finishDate);
				sg.setProCount(qlCount);//已完成量
				sg.setProPercent(new BigDecimal(1));//已完成百分比100%
			}
			if(index == 0) {//只有一个签量单
				BigDecimal byCount = (sg.getBeyondCount() == null ? BigDecimal.ZERO: sg.getBeyondCount());
				sg.setBeyondCount(byCount.add(remainCount));
			} else if (index != 0 && flag == 1) {//有多个签量单并且多余
				this.quartzCalculateList(list, index - 1, remainCount, finishDate);
			}
		} else {//不够用
			sg.setProCount(proCount.add(receiveCount));//已完成量
			BigDecimal proPercent = sg.getProCount().divide(qlCount, 4, BigDecimal.ROUND_HALF_UP); 
			sg.setProPercent(proPercent.setScale(4, BigDecimal.ROUND_HALF_UP));//已完成百分比
		}
	}

	/**
	 * 重置签量单统计数据
	 * 
	 * @param qtyList
	 *            签量数据
	 * @param clearList
	 *            用于记录重置的签量数据
	 */
	private void resetSignedQtyCalculate(List<SignedQuantity> qtyList, List<String> clearList) {
		for (int i = 0; i < qtyList.size(); i++) {
			String qlCode = qtyList.get(i).getQlCode();
			if(!clearList.contains(qlCode)){
				qtyList.get(i).setProCount(BigDecimal.ZERO);
				qtyList.get(i).setProPercent(BigDecimal.ZERO);
				qtyList.get(i).setFinshDate(null);
				qtyList.get(i).setBeyondCount(BigDecimal.ZERO);
				clearList.add(qlCode);
			}
		}
	}
	
	/**
	 * 更新签量统计
	 * 
	 * @param list
	 *            需更新的签量数据
	 */
	public void updateSignedQtyCalculate(List<SignedQuantity> list) {
		for (SignedQuantity sg : list) {
			this.signedQuantityDao.updateSignedQtyCalculate(sg.toMap());
		}
		/* 当数据中包含的数据类型不一样，会报错(比如数据中的Date类型有具体值和NULL)
		// List分批更新(每批100条) 
		int listSize = list.size();
		int maxCount = listSize / Constant.BATCH_SIZE;
		int i = 0;
		for (; i < maxCount; i++) {
			int toIndex = (i + 1) * Constant.BATCH_SIZE > listSize ? listSize
					: (i + 1) * Constant.BATCH_SIZE;
			map.put("list", list.subList(i * Constant.BATCH_SIZE, toIndex));
			this.signedQuantityDao.updateSignedQtyCalculate(map);
		}
		// 防止遗漏数据
		int currentIndex = i * Constant.BATCH_SIZE;
		if (currentIndex < list.size()) {
			map.put("list", list.subList(currentIndex, list.size()));
			this.signedQuantityDao.updateSignedQtyCalculate(map);
		}*/
	}
	
}
