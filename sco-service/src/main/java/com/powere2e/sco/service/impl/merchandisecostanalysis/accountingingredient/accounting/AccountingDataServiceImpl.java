package com.powere2e.sco.service.impl.merchandisecostanalysis.accountingingredient.accounting;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.Workbook;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.frame.commons.common.StringHelper;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.sco.common.service.BusinessConstants.MaterialType;
import com.powere2e.sco.common.service.BusinessConstants.MerchandiseNPackag;
import com.powere2e.sco.common.service.BusinessConstants.MerchandiseWPackag;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.XLSUtil;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDataDao;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.accounting.AccountingDataService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 核算表内包装业务类的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月17日
 */
@SuppressWarnings("unused")
public class AccountingDataServiceImpl extends ServiceImpl implements AccountingDataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7185545931328127112L;
	private AccountingDataDao accountingDataDao;
	// 存储核算编号
	private String accountingCode = "";
	private double zlsubtotalValue;
	private double flsubtotalValue;
	private double nwpackagsubtotalValue;
	private double wastagesubtotalValue;
	private double iTotalcostValue;
	private double yieldValue;
	private String merchandiseCode;
	private String supplierCode;
	private String npackagType;
	private String wpackagType;
	private Row row = null;
	private Map<String, Object> resultData = new HashMap<>();
	private DecimalFormat df = new DecimalFormat("0.000");
	
	public AccountingDataDao getAccountingDataDao() {
		return accountingDataDao;
	}

	public void setAccountingDataDao(AccountingDataDao accountingDataDao) {
		this.accountingDataDao = accountingDataDao;
	}

	public static AccountingDataService getInstance() {
		return (AccountingDataService) ConfigFactory.getInstance().getBean("accountingDataService");
	}
	
	public static void main(String[] args) {
		File file = new File("D:\\椰丝棉花巧克力-老品新上-莱达林20151026.xlsx");
		AccountingDataServiceImpl.getInstance().completeImportAccountingData(file);
	}

	@Override
	public String completeImportAccountingData(File file) {
		String msg = "";
		XSSFSheet sheet = null;
		zlsubtotalValue = 0;
		flsubtotalValue = 0;
		nwpackagsubtotalValue = 0;
		wastagesubtotalValue = 0;
		iTotalcostValue = 0;
		yieldValue = 0;
		try {
			// 创建Excel文件处理器
			
			XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
			// 循环读取sheet
			// 投料总表与核算总表
			deleteInfo(workbook);
			accountingCode = MasterDataTypeServiceImpl.getInstance().nextID("S_ACCOUNTING");
			for (int i = 0; i < workbook.getNumberOfSheets(); i++) {// 获取每个Sheet表
				sheet = workbook.getSheetAt(i);
				switch (i) {
				case 0:
					// 投料明细表
					parseIngredientItemSheet(sheet);
					break;
				case 1:
					// 投料总表和核算总表
					parseIngredientAndAccountingSheet(sheet);
					break;
				case 2:
					// 核算地区表
					parseAccountingRegionSheet(sheet);
					break;
				case 3:
					// 核算表内包装
					parseAccountingNpackagSheet(sheet);
					break;
				case 4:
					// 核算表外包装
					parseAccountingWpackagSheet(sheet);
					break;
				case 5:
					// 核算表损耗类型
					parseAccountingWastageSheet(sheet);
					break;
				case 6:
					// 核算成本项
					parseAccountingCostItemSheet(sheet);
					break;
				case 7:
					// 核算表水电煤~人工
					parseAccountingEctSheet(sheet);
					break;
				case 8:
					// 核算表管理
					parseAccountingManageSheet(sheet);
					break;
				case 9:
					// 核算表运输
					parseAccountingFreightSheet(sheet);
					break;
				case 10:
					// 核算表税收
					parseAccountingTaxSheet(sheet);
					break;
				case 11:
					// 核算表利润
					parseAccountingProfitSheet(sheet);
					break;
				case 12:
					// 核算表其他成本小计
					parseElseSubtotalSheet(sheet);
					break;
				case 13:
					// 核算表总价
					parseAggregateSheet(sheet);
					break;
				}
			}
		} catch (ParseException e) {
			if (sheet == null) {
				msg = "请选择版本为07以上的Excel";
			}else{
			msg = "表" + sheet.getSheetName() + "中有错误的类型,上传文件失败!!";
			}
		} catch (Exception e) {
			if (sheet == null) {
				msg = "请选择版本为07以上的Excel";
			}else{
			msg = "表" + sheet.getSheetName() + "中有错误,上传文件失败!!";
			}
		}
		if (msg.length() > 0) {// 有错误消息
			return msg.toString();
		}
		return null;
	}

	/**
	 * 插入之前先删除对应的数据
	 * 
	 * @param workbook
	 */
	private void deleteInfo(XSSFWorkbook workbook) {
		// 获取商品编号
		merchandiseCode =workbook.getSheetAt(1).getRow(3).getCell(1).toString();
		merchandiseCode = merchandiseCode.contains(".") == true?merchandiseCode.substring(0, merchandiseCode.indexOf(".")):merchandiseCode;
		// 获取供应商编号
		supplierCode = workbook.getSheetAt(1).getRow(3).getCell(2).toString();
		supplierCode = supplierCode.contains(".") == true?supplierCode.substring(0, supplierCode.indexOf(".")):supplierCode;
		//获取报价日期
		Date quotedDate = workbook.getSheetAt(1).getRow(3).getCell(4).getDateCellValue();
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("merchandiseCode", merchandiseCode);
		rowData.put("supplierCode", supplierCode);
		rowData.put("quotedDate", quotedDate);
		// 查询是否存在商品编号的记录
		List<String> accountingCodeList = this.accountingDataDao.searchAccountingCode(rowData);
		rowData.clear();
		// 如果存在记录就删除
		if (accountingCodeList != null) {
			if (accountingCodeList.size() != 0) {
				rowData.put("accountingCode", accountingCodeList.get(0));
				this.deleteAllAccounting(rowData);
			}
		}
	}

	/**
	 * 
	 * 解析投料明细表
	 * 
	 * @param sheet
	 *            投料明细表
	 */
	private void parseIngredientItemSheet(Sheet sheet) {
		double inputCountSum = 0.000;
		double inputCostSum = 0.000;
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,11)) continue;
			//计算总投入量
			if (!isNullCell(row.getCell(6))) {
				inputCountSum += row.getCell(6).getNumericCellValue();
			}
			//计算总投入成本
			if (!isNullCell(row.getCell(7))) {
				inputCostSum += row.getCell(7).getNumericCellValue();
			}
		}
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,11))continue;
			resultData = parseIngredientItemRow(row, inputCountSum,inputCostSum);
			resultData.put("ingredientCode", accountingCode);
			this.insertIngredientItem(resultData);
		}
	}

	/**
	 * 处理投料明细标的单行数据
	 * 
	 * @param row
	 *            行
	 * @param inputCountSum
	 *            所有行的投入量之和
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseIngredientItemRow(Row row, Double inputCountSum ,Double inputCostSum) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("materialType", getCellValue(row.getCell(0)).toString().equals(MaterialType.ZL.getTypeName())?"ZL":"FL");
		rowData.put("materialName", getCellValue(row.getCell(1)));
		rowData.put("materialOrigin", getCellValue(row.getCell(2)));
		rowData.put("materialBrand", getCellValue(row.getCell(3)));
		rowData.put("materialLevelSpecification", getCellValue(row.getCell(4)));
		rowData.put("purchasePrice", getCellValue(row.getCell(5)));
		rowData.put("inputCount", getCellValue(row.getCell(6)));
		rowData.put("inputCost", getCellValue(row.getCell(7)));
		rowData.put("avgCost", getCellValue(row.getCell(8)));
		if (!isNullCell(row.getCell(8))) {
			iTotalcostValue += row.getCell(8).getNumericCellValue();
		}
		rowData.put("moisture", getCellValue(row.getCell(9)));
		rowData.put("remarks", getCellValue(row.getCell(10)));
		String inputCountProportion = "0.000";
		if (!isNullCell(row.getCell(6))) {
			try {
				if (inputCountSum == 0) {
					inputCountProportion = "0.000";
				}else{
					inputCountProportion = getPercentumValue(row.getCell(6).getNumericCellValue() / inputCountSum);
				}
			} catch (Exception e) {
				inputCountProportion = "0.000";
			}
		}
		rowData.put("inputCountProportion", inputCountProportion);
		String inputCostProportion = "0.000";
		if (!isNullCell(row.getCell(7))) {
			try {
				if (inputCostSum == 0) {
					inputCostProportion = "0.000";
				}else{
					inputCostProportion = getPercentumValue(row.getCell(7).getNumericCellValue() / inputCostSum);
				}
			} catch (Exception e) {
				inputCostProportion = "0.000";
			}
		}
		rowData.put("inputCostProportion", inputCostProportion);
		//累加主料小计
		if (MaterialType.ZL.getTypeName().equals(row.getCell(0).toString())) {
			if (!isNullCell(row.getCell(7))) {
				zlsubtotalValue += row.getCell(7).getNumericCellValue();
			}
		}
		//累加辅料小计
		if (MaterialType.FL.getTypeName().equals(row.getCell(0).toString())) {
			if (!isNullCell(row.getCell(7))) {
				flsubtotalValue += row.getCell(7).getNumericCellValue();
			}
		}
		return rowData;
	}

	/**
	 * 解析投料总表和核算总表
	 * 
	 * @param sheet
	 *            投料总表和核算总表
	 * @throws ParseException
	 *             转化异常
	 */
	private void parseIngredientAndAccountingSheet(Sheet sheet) throws ParseException {
		// 核算总表的数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", accountingCode); //核算表编号
		map.put("inlandImport", "INLAND"); //核算表类型
		map.put("merchandiseCode", merchandiseCode);// 商品编号
		map.put("supplierCode", supplierCode);// 供应商编号
		map.put("quotedCurrency", getCellValue(sheet.getRow(3).getCell(3)));// 报价币种
		map.put("quotedDate", sheet.getRow(3).getCell(4).getDateCellValue());// 报价日期
		map.put("quantity", getCellValue(sheet.getRow(3).getCell(5)));// 报价量
		map.put("units", getCellValue(sheet.getRow(3).getCell(6)));// 报价单位
		this.insertAccounting(map);
		map.clear();
		// 投料总表的数据
		map.put("ingredientCode", accountingCode);
		map.put("merchandiseCode", merchandiseCode);
		map.put("supplierCode", supplierCode);
		map.put("productCount", getCellValue(sheet.getRow(1).getCell(3)));
		map.put("yield", getPercentumValue(sheet.getRow(1).getCell(4)).toString());
		map.put("moisture", getCellValue(sheet.getRow(1).getCell(5)).toString());
		if (!isNullCell(sheet.getRow(1).getCell(4))) {
			yieldValue = sheet.getRow(1).getCell(4).getNumericCellValue();
		}
		this.insertIngredient(map);
	}

	/**
	 * 
	 * 解析核算表地区表
	 * 
	 * @param sheet
	 *            核算表地区表
	 */
	private void parseAccountingRegionSheet(Sheet sheet) {
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,1)) continue;
			resultData = parseAccountingRegionRow(row);
			resultData.put("accountingCode", accountingCode);
			this.insertAccountingRegion(resultData);
		}
	}

	/**
	 * 处理核算表地区的单行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingRegionRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("region", getCellValue(row.getCell(0)));
		return rowData;
	}

	/**
	 * 处理核算表成本项
	 * 
	 * @param sheet
	 *            核算表成本项表
	 */
	private void parseAccountingCostItemSheet(Sheet sheet) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("accountingCode", accountingCode);
		rowData.put("yieldValue", getPercentumValue(yieldValue));
		rowData.put("yieldRemarks", getCellValue(sheet.getRow(1).getCell(2)));
		rowData.put("zlsubtotalValue", df.format(zlsubtotalValue));
		rowData.put("zlsubtotalRemarks", getCellValue(sheet.getRow(2).getCell(2)));
		rowData.put("flsubtotalValue", df.format(flsubtotalValue));
		rowData.put("flsubtotalRemarks", getCellValue(sheet.getRow(3).getCell(2)));
		rowData.put("iTotalcostValue", df.format(iTotalcostValue));
		rowData.put("iTotalcostRemarks", getCellValue(sheet.getRow(4).getCell(2)));
		rowData.put("mTotalcostValue", getCellValue(sheet.getRow(5).getCell(1)));
		rowData.put("mTotalcostRemarks", getCellValue(sheet.getRow(5).getCell(2)));
		rowData.put("packagproportionValue", getPercentumValue(sheet.getRow(6).getCell(1)));
		rowData.put("packagproportionRemarks", getCellValue(sheet.getRow(6).getCell(2)));
		String deductptcostValue = "";
		if (!isNullCell(sheet.getRow(5).getCell(1)) && !isNullCell(sheet.getRow(6).getCell(1))) {
			deductptcostValue = df.format(sheet.getRow(5).getCell(1).getNumericCellValue()
					* (1 - sheet.getRow(6).getCell(1).getNumericCellValue()));
		}
		rowData.put("deductptcostValue", df.format(Double.valueOf(deductptcostValue)));
		rowData.put("deductptcostRemarks", getCellValue(sheet.getRow(7).getCell(2)));
		rowData.put("nwpackagsubtotalValue", df.format(nwpackagsubtotalValue));
		rowData.put("nwpackagsubtotalRemarks", getCellValue(sheet.getRow(8).getCell(2)));
		rowData.put("wastagesubtotalValue", df.format(wastagesubtotalValue));
		rowData.put("wastagesubtotalRemarks", getCellValue(sheet.getRow(9).getCell(2)));
		this.insertAccountingCostItem(rowData);
	}

	/**
	 * 解析核算表内包装表
	 * 
	 * @param sheet
	 *            核算表内包装表
	 */
	private void parseAccountingNpackagSheet(Sheet sheet) {
		Row row = null;
		int length = 0;
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,12)) continue;
				resultData = parseAccountingNpackagRow(row);
				resultData.put("accountingCode", accountingCode);
				this.insertAccountingNpackag(resultData);
		}
	}

	/**
	 * 处理核算表内包装的单行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingNpackagRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		if (MerchandiseNPackag.FHD_JM.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "FHD_JM";
		}
		if (MerchandiseNPackag.FHD_LSM.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "FHD_LSM";
		}
		if (MerchandiseNPackag.FHD_ZD.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "FHD_ZD";
		}
		if (MerchandiseNPackag.ST.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "ST";
		}
		if (MerchandiseNPackag.TYJ.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "TYJ";
		}
		if (MerchandiseNPackag.NDD.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "NDD";
		}
		if (MerchandiseNPackag.BQ.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "BQ";
		}
		if (MerchandiseNPackag.ZZL.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "ZZL";
		}
		if (MerchandiseNPackag.ELSE.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			npackagType = "ELSE";
		}
		rowData.put("npackagType", npackagType);
		rowData.put("elseName", getCellValue(row.getCell(1)));
		rowData.put("price", getCellValue(row.getCell(2)));
		if (!isNullCell(row.getCell(2))) {
			nwpackagsubtotalValue += row.getCell(2).getNumericCellValue();
		}
		rowData.put("texture", getCellValue(row.getCell(3)));
		String kgPrice = "0.000";
		if (MerchandiseNPackag.FHD_ZD.getTypeName().equals(getCellValue(row.getCell(0)).toString())) {
			if (!isNullCell(row.getCell(8)) && !isNullCell(row.getCell(7))) {
				try {
					kgPrice = df.format(row.getCell(8).getNumericCellValue() / row.getCell(7).getNumericCellValue() * 1000);
				} catch (Exception e) {
					kgPrice = "0.000";
				}
			} else {
				kgPrice = "0.000";
			}
		}else{
			kgPrice = getCellValue(row.getCell(4)).toString();
		}
		rowData.put("kgPrice", kgPrice);
		rowData.put("weightProportion", getPercentumValue(row.getCell(5)));
		rowData.put("materialSize", getCellValue(row.getCell(6)));
		rowData.put("weight", getCellValue(row.getCell(7)));
		rowData.put("unitsPrice", getCellValue(row.getCell(8)));
		rowData.put("quantity",getCellValue(row.getCell(9)));
		rowData.put("technologyRequirements", getCellValue(row.getCell(10)));
		rowData.put("remarks", getCellValue(row.getCell(11)));
		return rowData;
	}

	/**
	 * 解析核算表外包装表
	 * 
	 * @param sheet
	 *            核算表外包装表
	 */
	private void parseAccountingWpackagSheet(Sheet sheet) {
		Row row = null;
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,11)) continue;
				resultData = parseAccountingWpackagRow(row);
				resultData.put("accountingCode", accountingCode);
				this.insertAccountingWpackag(resultData);
		}
	}

	/**
	 * 处理核算表外包装的单行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingWpackagRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		if (MerchandiseWPackag.FXD.getTypeName().equals(getCellValue(row.getCell(0)))) {
			wpackagType = "FXD";
		}
		if (MerchandiseWPackag.ZX.getTypeName().equals(getCellValue(row.getCell(0)))) {
			wpackagType = "ZX";	
		}
		if (MerchandiseWPackag.ELSE.getTypeName().equals(getCellValue(row.getCell(0)))) {
			wpackagType = "ELSE";
		}
		rowData.put("wpackagType", wpackagType);
		rowData.put("elseName", getCellValue(row.getCell(1)));
		rowData.put("price", getCellValue(row.getCell(2)));
		if (!isNullCell(row.getCell(2))) {
			nwpackagsubtotalValue += row.getCell(2).getNumericCellValue();
		}
		rowData.put("unitsPrice", getCellValue(row.getCell(3)));
		rowData.put("useQuantity", getCellValue(row.getCell(4)));
		rowData.put("texture", getCellValue(row.getCell(5)));
		double length = 0;
		try {
			length = row.getCell(6).getNumericCellValue();
		} catch (Exception e) {
			length = 0;
		}
		double width = 0;
		try {
			width = row.getCell(7).getNumericCellValue();
		} catch (Exception e) {
			width = 0;
		}
		double height = 0;
		try {
			height = row.getCell(8).getNumericCellValue();
		} catch (Exception e) {
			height = 0;
		}
		rowData.put("length", df.format(length));
		rowData.put("width", df.format(width));
		rowData.put("height", df.format(height));
		rowData.put("specification", getCellValue(row.getCell(9)));
		rowData.put("remarks", getCellValue(row.getCell(10)));
		// 纸箱用料面积（㎡）
		String area = "";
		// 纸箱用料单价（元/㎡）
		String ylUnitsPrice = "";
		if (MerchandiseWPackag.ZX.getTypeName().equals(getCellValue(row.getCell(0)))) {
			// 纸箱用料面积（㎡）：系统自动计算，用户不可修改，计算公式为（长+宽+5）X (宽+高+3）*2÷10000
			try {
				area = df.format((length + width + 5) * (width + height + 3) * 2 / 10000);
			} catch (Exception e) {
				area = "0.000";
			}
			// 纸箱用料单价（元/㎡）：系统自动计算，用户不可修改，计算公式为单价（元/只）÷纸箱用料面积
			if (row.getCell(3).getCellType() != Cell.CELL_TYPE_BLANK) {
				try {
					ylUnitsPrice = df.format(row.getCell(3).getNumericCellValue() / ((length + width + 5) * (width + height + 3) * 2 / 10000));
				} catch (Exception e) {
					ylUnitsPrice = "0.000";
				}
			}
		}
		rowData.put("area", area);
		rowData.put("ylUnitsPrice", ylUnitsPrice);
		return rowData;
	}

	/**
	 * 解析核算表损耗类型表
	 * 
	 * @param sheet
	 *            核算表损耗类型表
	 */
	private void parseAccountingWastageSheet(Sheet sheet) {
		Row row = null;
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRow(row,3)) continue;
				resultData = parseAccountingWastageRow(row);
				resultData.put("accountingCode", accountingCode);
				this.insertAccountingWastage(resultData);
		}
	}

	/**
	 * 处理核算表损耗类型表的单行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingWastageRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("wastageType", getCellValue(row.getCell(0)));
		rowData.put("price", getCellValue(row.getCell(1)));
		rowData.put("remarks", getCellValue(row.getCell(2)));
		if (row.getCell(1).getCellType() != Cell.CELL_TYPE_BLANK) {
			wastagesubtotalValue += row.getCell(1).getNumericCellValue();
		}
		return rowData;
	}

	/**
	 * 解析核算表水电煤~管理表
	 * 
	 * @param sheet
	 *            核算表水电煤~管理表
	 */
	private void parseAccountingEctSheet(Sheet sheet) {
		// 水电煤
		Map<String, Object> map = new HashMap<>();
		map.put("accountingCode", accountingCode);
		map.put("price", getCellValue(sheet.getRow(1).getCell(1)));
		map.put("water", getCellValue(sheet.getRow(1).getCell(2)));
		map.put("electricity", getCellValue(sheet.getRow(1).getCell(3)));
		map.put("gas", getCellValue(sheet.getRow(1).getCell(4)));
		map.put("coal", getCellValue(sheet.getRow(1).getCell(5)));
		map.put("oil", getCellValue(sheet.getRow(1).getCell(6)));
		map.put("remarks", getCellValue(sheet.getRow(1).getCell(7)));
		Double total = 0D;
		//计算公式为：(耗水元/t成品+耗电元/t成品+耗气元/t成品+耗煤元/t成品+耗油元/t成品)/1000
		try {
			total=(sheet.getRow(1).getCell(2).getNumericCellValue()+sheet.getRow(1).getCell(3).getNumericCellValue()+sheet.getRow(1).getCell(4).getNumericCellValue()+
					sheet.getRow(1).getCell(5).getNumericCellValue()+sheet.getRow(1).getCell(6).getNumericCellValue())/1000;
		} catch (Exception e) {
			total = 0.000;
		}
		map.put("total", df.format(total));
		this.insertAccountingWec(map);
		map.clear();
		// 核算表设备折旧及维护
		map.put("accountingCode", accountingCode);
		map.put("price", getCellValue(sheet.getRow(3).getCell(1)));
		map.put("totalPrice", getCellValue(sheet.getRow(3).getCell(2)));
		map.put("ageLimit", getCellValue(sheet.getRow(3).getCell(3)));
		map.put("depreciation", getCellValue(sheet.getRow(3).getCell(4)));
		map.put("capacity", getCellValue(sheet.getRow(3).getCell(5)));
		map.put("remarks", getCellValue(sheet.getRow(3).getCell(6)));
		this.insertAccountingSbzjwh(map);
		map.clear();
		// 核算表人工
		map.put("accountingCode", accountingCode);
		map.put("price", getCellValue(sheet.getRow(5).getCell(1)));
		map.put("manpowerCount", getCellValue(sheet.getRow(5).getCell(2)));
		map.put("avgWage", getCellValue(sheet.getRow(5).getCell(3)));
		map.put("monthCapacity", getCellValue(sheet.getRow(5).getCell(4)));
		//map.put("jtpc", getCellValue(sheet.getRow(5).getCell(5)));
		map.put("remarks", getCellValue(sheet.getRow(5).getCell(6)));
		// 每kg成品元/kg：系统自动计算，用户不可修改。计算公式为：平均工资*人数÷月产量+均摊偏差
		double unitsWage = 0.000;
		if (!isNullCell(sheet.getRow(5).getCell(2)) && !isNullCell(sheet.getRow(5).getCell(3))
				&& !isNullCell(sheet.getRow(5).getCell(4)) && !isNullCell(sheet.getRow(5).getCell(5))) {
			try {
				if (sheet.getRow(5).getCell(4).getNumericCellValue() == 0) {
					unitsWage = 0+sheet.getRow(5).getCell(5).getNumericCellValue();
				}else{
					unitsWage = sheet.getRow(5).getCell(2).getNumericCellValue() * sheet.getRow(5).getCell(2).getNumericCellValue()
							/ sheet.getRow(5).getCell(4).getNumericCellValue() + sheet.getRow(5).getCell(5).getNumericCellValue();
				}
			} catch (Exception e) {
				unitsWage = 0.000;
			}
		}
		map.put("unitsWage", Double.isNaN(unitsWage)?0.000:unitsWage);
		this.insertAccountingManpower(map);
	}
	
	/**
	 * 解析核算表管理和核算表管理各地区表
	 * 
	 * @param sheet
	 *            核算表管理和核算表管理各地区表
	 */
	private void parseAccountingManageSheet(Sheet sheet) {
		Map<String, Object> rowData = new HashMap<String, Object>();
		// 核算表管理
		rowData.put("accountingCode", accountingCode);
		rowData.put("price", getCellValue(sheet.getRow(1).getCell(1)));
		rowData.put("remarks", getCellValue(sheet.getRow(1).getCell(2)));
		this.insertAccountingManage(rowData);
		rowData.clear();
		// 核算表管理各地区
		for (int rowIndex = 3; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,2)) continue;
			    rowData = parseAccountingManageRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingManageRegion(rowData);
		}
	}

	/**
	 * 核算表管理地区
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingManageRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("region", getCellValue(row.getCell(0)));
		rowData.put("proportion", getPercentumValue(row.getCell(1)));
		return rowData;
	}

	/**
	 * 解析核算表运输备注和核算表运输各地区表
	 * 
	 * @param sheet
	 *            核算表运输备注和核算表运输各地区表
	 */
	private void parseAccountingFreightSheet(Sheet sheet) {
		Map<String, Object> rowData = new HashMap<String, Object>();
		// 核算表运输备注
		rowData.put("accountingCode", accountingCode);
		rowData.put("unitsCost", getCellValue(sheet.getRow(0).getCell(1)));
		rowData.put("unitsPrice", getCellValue(sheet.getRow(0).getCell(3)));
		rowData.put("remarks", getCellValue(sheet.getRow(0).getCell(5)));
		this.insertAccountingFreight(rowData);
		rowData.clear();
		// 核算表运输各地区
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			Row row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,3)) continue;
			rowData = parseAccountingFreightRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingFreightRegion(rowData);
		}
	}

	/**
	 * 核算表运输各地区表
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingFreightRow(Row row) {
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("region", getCellValue(row.getCell(0)));
		rowData.put("price", getCellValue(row.getCell(1)));
		rowData.put("sumKm", getCellValue(row.getCell(2)));
		return rowData;
	}

	/**
	 * 解析核算表税收和核算表税收各地区表
	 * 
	 * @param sheet
	 *            核算表税收和核算表税收各地区表
	 */
	private void parseAccountingTaxSheet(Sheet sheet) {
		Row row = null;
		// 核算表税收
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("accountingCode", accountingCode);
		rowData.put("taxRate", getPercentumValue(sheet.getRow(0).getCell(1)));
		rowData.put("remarks", getCellValue(sheet.getRow(0).getCell(3)));
		this.insertAccountingTax(rowData);
		rowData.clear();
		// 核算表税收各地区
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,3)) continue;
			    rowData = parseAccountingTaxRegionRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingTaxRegion(rowData);
		}
	}

	/**
	 * 处理核算表税收和核算表税收各地区表中的一行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingTaxRegionRow(Row row) {
		Map<String, Object> taxRegionData = new HashMap<>();
		taxRegionData.put("region", getCellValue(row.getCell(0)));
		taxRegionData.put("price", getCellValue(row.getCell(1)));
		taxRegionData.put("proportion", getPercentumValue(row.getCell(2)));
		return taxRegionData;
	}

	/**
	 * 解析核算表利润和核算表利润各地区表
	 * 
	 * @param sheet
	 *            核算表利润和核算表利润各地区表
	 */
	private void parseAccountingProfitSheet(Sheet sheet) {
		Row row = null;
		// 核算表利润
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("accountingCode", accountingCode);
		rowData.put("remarks", getCellValue(sheet.getRow(0).getCell(1)));
		this.insertAccountingProfit(rowData);
		rowData.clear();
		// 核算表利润各地区
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,3)) continue;
				rowData = parseAccountingProfitRegionRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingProfitRegion(rowData);
		}
	}

	/**
	 * 处理核算表利润和核算表利润各地区表中的一行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAccountingProfitRegionRow(Row row) {
		Map<String, Object> profitRegionData = new HashMap<>();
		profitRegionData.put("region", getCellValue(row.getCell(0)));
		profitRegionData.put("price", getCellValue(row.getCell(1)));
		profitRegionData.put("proportion", getPercentumValue(row.getCell(2)));
		return profitRegionData;
	}

	/**
	 * 解析核算表其他成本小计和其他成本小计各地区表
	 * 
	 * @param sheet
	 */
	private void parseElseSubtotalSheet(Sheet sheet) {
		Row row = null;
		// 核算表其他成本小计
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("accountingCode", accountingCode);
		rowData.put("remarks", getCellValue(sheet.getRow(0).getCell(1)));
		this.insertAccountingElsesubtotal(rowData);
		rowData.clear();
		// 核算表其他成本小计各地区
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,2)) continue;
				rowData = parseElseSubtotalRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingElsesubtotalRegion(rowData);
		}
	}

	/**
	 * 处理核算表其他成本小计和其他成本小计各地区表中的一行数据
	 * 
	 * @param row
	 * @return
	 */
	private Map<String, Object> parseElseSubtotalRow(Row row) {
		Map<String, Object> elseSubtotalData = new HashMap<>();
		elseSubtotalData.put("region", getCellValue(row.getCell(0)));
		elseSubtotalData.put("subtotal", getCellValue(row.getCell(1)));
		return elseSubtotalData;
	}

	/**
	 * 解析核算表总价和总价各地区表
	 * 
	 * @param sheet
	 */
	private void parseAggregateSheet(Sheet sheet) {
		Row row = null;
		// 核算表总价
		Map<String, Object> rowData = new HashMap<>();
		rowData.put("accountingCode", accountingCode);
		rowData.put("remarks", getCellValue(sheet.getRow(0).getCell(1)));
		this.insertAccountingAggregate(rowData);
		rowData.clear();
		// 核算表总价各地区
		for (int rowIndex = 2; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			row = sheet.getRow(rowIndex);
			if (isNullRegionRow(row,2)) continue;
				rowData = parseAggregateRow(row);
				rowData.put("accountingCode", accountingCode);
				this.insertAccountingAggregateRegion(rowData);
		}
	}

	/**
	 * 处理核算表利润和核算表利润各地区表中的一行数据
	 * 
	 * @param row
	 *            行
	 * @return 行的数据 键/值对的
	 */
	private Map<String, Object> parseAggregateRow(Row row) {
		Map<String, Object> aggregateData = new HashMap<>();
		aggregateData.put("region", getCellValue(row.getCell(0)));
		aggregateData.put("sumPrice", getCellValue(row.getCell(1)));
		return aggregateData;
	}

	@Override
	public void insertIngredientItem(Map<String, Object> map) {
		this.accountingDataDao.insertIngredientItem(map);

	}

	@Override
	public void insertIngredient(Map<String, Object> map) {
		this.accountingDataDao.insertIngredient(map);

	}

	@Override
	public void insertAccounting(Map<String, Object> map) {
		this.accountingDataDao.insertAccounting(map);

	}

	@Override
	public void insertAccountingRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingRegion(map);

	}

	@Override
	public void insertAccountingCostItem(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingCostItem(map);

	}

	@Override
	public void insertAccountingNpackag(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingNpackag(map);

	}

	@Override
	public void insertAccountingWpackag(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingWpackag(map);

	}

	@Override
	public void insertAccountingWastage(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingWastage(map);

	}

	@Override
	public void insertAccountingWec(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingWec(map);

	}

	@Override
	public void insertAccountingSbzjwh(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingSbzjwh(map);

	}

	@Override
	public void insertAccountingManpower(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingManpower(map);

	}

	@Override
	public void insertAccountingManage(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingManage(map);

	}

	@Override
	public void insertAccountingManageRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingManageRegion(map);

	}

	@Override
	public void insertAccountingFreight(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingFreight(map);

	}

	@Override
	public void insertAccountingFreightRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingFreightRegion(map);

	}

	@Override
	public void insertAccountingTax(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingTax(map);

	}

	@Override
	public void insertAccountingTaxRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingTaxRegion(map);

	}

	@Override
	public void insertAccountingProfit(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingProfit(map);

	}

	@Override
	public void insertAccountingProfitRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingProfitRegion(map);

	}

	@Override
	public void insertAccountingAggregate(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingAggregate(map);

	}

	@Override
	public void insertAccountingAggregateRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingAggregateRegion(map);

	}

	@Override
	public void insertAccountingElsesubtotal(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingElsesubtotal(map);

	}

	@Override
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map) {
		this.accountingDataDao.insertAccountingElsesubtotalRegion(map);

	}

	@Override
	public void deleteAllAccounting(Map<String, Object> map) {
		this.accountingDataDao.deleteIngredient(map);
		this.accountingDataDao.deleteIngredientItem(map);
		this.accountingDataDao.deleteAccounting(map);
		this.accountingDataDao.deleteAccountingRegion(map);
		this.accountingDataDao.deleteAccountingCostItem(map);
		this.accountingDataDao.deleteAccountingNpackag(map);
		this.accountingDataDao.deleteAccountingWpackag(map);
		this.accountingDataDao.deleteAccountingWastage(map);
		this.accountingDataDao.deleteAccountingWec(map);
		this.accountingDataDao.deleteAccountingSbzjwh(map);
		this.accountingDataDao.deleteAccountingManpower(map);
		this.accountingDataDao.deleteAccountingManage(map);
		this.accountingDataDao.deleteAccountingManageRegion(map);
		this.accountingDataDao.deleteAccountingFreight(map);
		this.accountingDataDao.deleteAccountingFreightRegion(map);
		this.accountingDataDao.deleteAccountingTax(map);
		this.accountingDataDao.deleteAccountingTaxRegion(map);
		this.accountingDataDao.deleteAccountingProfit(map);
		this.accountingDataDao.deleteAccountingProfitRegion(map);
		this.accountingDataDao.deleteAccountingAggregate(map);
		this.accountingDataDao.deleteAccountingAggregateRegion(map);
		this.accountingDataDao.deleteAccountingElsesubtotal(map);
		this.accountingDataDao.deleteAccountingElsesubtotalRegion(map);
	}

	/**
	 * 判断是否是空行
	 * 
	 * @param row 要判断的行
	 * @return true 是 ;false 否
	 */
	private boolean isNullRow(Row row,int lastNum) {
		int nullNum = 0;
		if (row == null) {
			return true;
		}
		for (int i = 0; i < lastNum; i++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				nullNum++;
			} else {
				if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					nullNum++;
				}
			}
		}
		if (nullNum >= (lastNum==1?lastNum:lastNum-1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断包含地区的sheet是否空行
	 * 
	 * @param row 行
	 * @param lastNum 最后一个单元格的位置
	 * @return true  是 ;false  不是
	 */
	private boolean isNullRegionRow(Row row,int lastNum){
		int nullNum = 0;
		if (row == null) {
			return true;
		}
		String region = getCellValue(row.getCell(0)).toString();
		if (StringUtils.isBlank(region)) {
			return true;
		}
		for (int i = 1; i < lastNum; i++) {
			Cell cell = row.getCell(i);
			if (cell == null) {
				nullNum++;
			} else {
				if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
					nullNum++;
				}
			}
		}
		if (nullNum == (lastNum-1)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 获取Cell的值
	 * 
	 * @param cell 单元格
	 * @return 值
	 */
	private Object getCellValue(Cell cell) {
		Object value = "";
		try {
			if (cell == null) {
				return value;
			}
			switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
				case Cell.CELL_TYPE_FORMULA:
					try {
						value = df.format(cell.getNumericCellValue());
					} catch (IllegalStateException e) {
						value = cell.getStringCellValue();
					}
					break;
				case Cell.CELL_TYPE_NUMERIC:
					value = df.format(cell.getNumericCellValue());
					break;
				default:
					value = "";
					break;
			}
		} catch (Exception e) {
			value = "";
		}
		return value;
	}
	
	/**
	 * 判读是否是空的单元格
	 * 
	 * @param cell 单元格
	 * @return true 是;false 否
	 */
	private boolean isNullCell(Cell cell){
		if (cell == null) {
			return true;
		}else{
			if (cell.getCellType()==Cell.CELL_TYPE_BLANK) {
				return true;
			}else{
				return false;
			}
		}
	}
	
	/**
	 * 获取百分比的值
	 * 
	 * @param obj 要获取的对象
	 * @return 值
	 */
	private String getPercentumValue(Object obj){
		try {
			return df.format(Double.valueOf(obj.toString())*100);
		} catch (Exception e) {
			return "0.000";
		}
	}
}