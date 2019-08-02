package com.powere2e.sco.service.impl.merchandisecostanalysis.accountingingredient.ingredient;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.service.BusinessConstants.MaterialType;
import com.powere2e.sco.common.service.BusinessConstants.ProcurementDepartments;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDao;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.ingredient.IngredientDao;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.ingredient.IngredientService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.Accounting;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品投料表业务类的实现
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public class IngredientServiceImpl extends ServiceImpl implements IngredientService {

	private static final long serialVersionUID = 4814331878993235820L;
	private IngredientDao ingredientDao;
	private AccountingDao accountingDao;
	private MasterDataTypeService masterDataTypeService;

	public IngredientDao getIngredientDao() {
		return ingredientDao;
	}

	public void setIngredientDao(IngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}

	public AccountingDao getAccountingDao() {
		return accountingDao;
	}

	public void setAccountingDao(AccountingDao accountingDao) {
		this.accountingDao = accountingDao;
	}

	public MasterDataTypeService getMasterDataTypeService() {
		return masterDataTypeService;
	}

	public void setMasterDataTypeService(MasterDataTypeService masterDataTypeService) {
		this.masterDataTypeService = masterDataTypeService;
	}

	// 查询
	@Override
	public List<Ingredient> listIngredient(Map<String, Object> map, PageInfo pageInfo) {
		return this.getIngredientDao().listIngredient(map, pageInfo);
	}

	// 添加
	@Override
	public void insertIngredient(Ingredient ingredient) {
		this.getIngredientDao().insertIngredient(ingredient.toMap());
	}

	// 删除
	@Override
	public void deleteIngredient(String ingredientCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ingredientCode", ingredientCode);
		this.getIngredientDao().deleteIngredient(map);
	}

	// 修改
	@Override
	public void updateIngredient(Ingredient ingredient) {
		this.getIngredientDao().updateIngredient(ingredient.toMap());
	}

	// 加载一个核算表-总价各地区
	@Override
	public Ingredient loadIngredient(Map<String, Object> map) {
		return this.getIngredientDao().loadIngredient(map);
	}

	// 查询
	@Override
	public List<IngredientItem> listIngredientItem(Map<String, Object> map, PageInfo pageInfo) {
		return this.ingredientDao.listIngredientItem(map, pageInfo);
	}

	// 添加
	@Override
	public void insertIngredientItem(Map<String, Object> map) {
		this.ingredientDao.insertIngredientItem(map);
	}

	// 删除
	@Override
	public void deleteIngredientItem(Map<String, Object> map) {
		this.ingredientDao.deleteIngredientItem(map);
	}

	@Override
	public String uploadIngredientItem(File file, Ingredient ingredient) {
		// 存储错误信息
		StringBuilder msg = new StringBuilder();
		try {
			SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
			Accounting accounting = new Accounting();
			// 投料表明细
			List<IngredientItem> ingredientItemList = new ArrayList<IngredientItem>();
			// 产品生成量
			BigDecimal productCount = null;
			// 成品含水率
			String ingredientMoisture = null;
			// 得率
			BigDecimal yield = null;
			// 投入量总计
			BigDecimal inputCountSum = new BigDecimal(0);
			// 投入成本总计
			BigDecimal inputCostSum = new BigDecimal(0);
			// 循环读取Excel每一行数据
			for (int i = 1; i <= handler.getLastRowNum(); i++) {
				// 读取Excel第i行记录
				ExcelRow rowData = handler.getExcelRow(i);

				// 验证模版格式是否正确
				if (i == 1) {
					if (StringUtils.countMatches(rowData.getStringCellValue("A"), "原材料投料表") == 0) {
						msg.append("该电子投料表格式不正确，请从系统中下载正确的模版并填写");
						break;
					} else {
						continue;
					}
				}
				if (i == 2) {
					if (StringUtils.countMatches(rowData.getStringCellValue("A"), "原材料类型") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("B"), "原材料名称") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("C"), "原料产地") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("D"), "原料品牌") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("E"), "原料等级与规格") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("F"), "原料采购价格") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("G"), "原料投入量") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("H"), "原料投入成本") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("I"), "平均成品原料成本") == 0 
							|| StringUtils.countMatches(rowData.getStringCellValue("J"), "含水率") == 0) {
						msg.append("该电子投料表格式不正确，请从系统中下载正确的模版并填写");
						break;
					} else {
						continue;
					}
				}

				// 原料类型
				String materialType = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
				// 判断是否为主料和辅料
				if (materialType.equals(MaterialType.ZL.getTypeName())) {
					materialType = MaterialType.ZL.toString();
				} else if (materialType.equals(MaterialType.FL.getTypeName())) {
					materialType = MaterialType.FL.toString();
				} else if (StringUtils.countMatches(materialType, "合计") > 0) {
					continue;
				} else if (StringUtils.countMatches(materialType, "产品生成量") > 0) {
					try {
						productCount = rowData.getBigDecimalCellValue("B", 2, RoundingMode.HALF_UP);
					} catch (Exception e) {
						msg.append("第" + i + "行B列:产品生成量请填入数字类型<br>");
					}
					continue;
				} else if (StringUtils.countMatches(materialType, "成品含水率") > 0) {
					try {
						ingredientMoisture = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
					} catch (Exception e) {
						msg.append("第" + i + "行B列:成品含水率请填入数字类型<br>");
					}
					continue;
				} else if (StringUtils.countMatches(materialType, "得率") > 0) {
					try {
						yield = rowData.getBigDecimalCellValue("B", 2, RoundingMode.HALF_UP);
					} catch (Exception e) {
						msg.append("第" + i + "行B列:得率请填入数字类型<br>");
					}
					continue;
				} else {
					msg.append("第" + i + "行A列:内容不符合模版格式<br>");
					continue;
				}

				// 原料名称
				String materialName = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
				if (StringUtils.isEmpty(materialName)) {
					msg.append("第" + i + "行B列:原料名称不可为空<br>");
				} else {
					if (StrUtils.getLength(materialName) > 30) {
						msg.append("第" + i + "行B列:原料名称字符过长<br>");
					}
				}

				// 原材料产地
				String materialOrigin = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
				if (!StringUtils.isEmpty(materialOrigin) && StrUtils.getLength(materialOrigin) > 100) {
					msg.append("第" + i + "行C列:原材料产地字符过长<br>");
				}

				// 原材料品牌
				String materialBrand = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D"));
				if (!StringUtils.isEmpty(materialBrand) && StrUtils.getLength(materialBrand) > 100) {
					msg.append("第" + i + "行D列:原材料品牌字符过长<br>");
				}

				// 原材料等级与规格
				String materialLevelSpecification = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("E"));
				if (!StringUtils.isEmpty(materialLevelSpecification) && StrUtils.getLength(materialLevelSpecification) > 100) {
					msg.append("第" + i + "行E列:原材料等级与规格字符过长<br>");
				}

				// 采购价格
				BigDecimal purchasePrice = null;
				try {
					purchasePrice = rowData.getBigDecimalCellValue("F", 4, RoundingMode.HALF_UP);
					if (purchasePrice == null) {
						msg.append("第" + i + "行F列:采购价格不可为空<br>");
					} else {
						purchasePrice = purchasePrice.setScale(4, RoundingMode.HALF_UP);
					}
				} catch (Exception e) {
					msg.append("第" + i + "行F列:采购价格请填入数字类型<br>");
				}

				// 投入量
				BigDecimal inputCount = null;
				try {
					inputCount = rowData.getBigDecimalCellValue("G", 4, RoundingMode.HALF_UP);
					if (inputCount == null) {
						msg.append("第" + i + "行G列:投入量不可为空<br>");
					} else {
						inputCount = inputCount.setScale(4, RoundingMode.HALF_UP);
						inputCountSum = inputCountSum.add(inputCount);
					}
				} catch (Exception e) {
					msg.append("第" + i + "行G列:投入量请填入数字类型<br>");
				}

				// 投入成本
				BigDecimal inputCost = null;
				try {
					inputCost = rowData.getBigDecimalCellValue("H", 4, RoundingMode.HALF_UP);
					if (inputCost == null) {
						msg.append("第" + i + "行H列:投入成本不可为空<br>");
					} else {
						inputCost = inputCost.setScale(4, RoundingMode.HALF_UP);
						inputCostSum = inputCostSum.add(inputCost);
					}
				} catch (Exception e) {
					msg.append("第" + i + "行H列:投入成本请填入数字类型<br>");
				}

				// 平均成品成本
				BigDecimal avgCost = null;
				try {
					avgCost = rowData.getBigDecimalCellValue("I", 4, RoundingMode.HALF_UP);
					if (avgCost == null) {
						msg.append("第" + i + "行I列:平均成品成本不可为空<br>");
					} else {
						avgCost = avgCost.setScale(4, RoundingMode.HALF_UP);
					}
				} catch (Exception e) {
					msg.append("第" + i + "行I列:平均成品成本请填入数字类型<br>");
				}

				// 含水率
				String moisture = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("J"));
				if (StrUtils.getLength(materialName) > 30) {
					msg.append("第" + i + "行J列:含水率字符过长<br>");
				}
				
				if (StringUtils.isEmpty(msg.toString())) {
					IngredientItem ingredientItem = new IngredientItem();
					ingredientItem.setIngredientCode(ingredient.getIngredientCode());
					ingredientItem.setMaterialType(materialType);
					ingredientItem.setMaterialName(materialName);
					ingredientItem.setMaterialOrigin(materialOrigin);
					ingredientItem.setMaterialBrand(materialBrand);
					ingredientItem.setMaterialLevelSpecification(materialLevelSpecification);
					ingredientItem.setPurchasePrice(purchasePrice);
					ingredientItem.setInputCount(inputCount);
					ingredientItem.setInputCost(inputCost);
					ingredientItem.setAvgCost(avgCost);
					ingredientItem.setMoisture(moisture);
					ingredientItemList.add(ingredientItem);
				}
			}

			if (StringUtils.isEmpty(msg.toString()) && ingredientItemList.size() == 0) {
				// msg.setLength(0);
				msg.append("投料表明细不能为空!");
			} else if (StringUtils.isEmpty(msg.toString()) && ingredientItemList.size() > 0) {
				ingredient.setProductCount(productCount);
				ingredient.setYield(yield);
				ingredient.setMoisture(ingredientMoisture);
				Map<String, Object> map = new HashMap<String, Object>();
				if (StringUtils.isEmpty(ingredient.getIngredientCode())) {
					// 获取投料编号
					ingredient.setIngredientCode(masterDataTypeService.nextID("S_ACCOUNTING"));
					accounting.setAccountingCode(ingredient.getIngredientCode());
					accounting.setInlandImport(ProcurementDepartments.INLAND.toString());
					accounting.setIntentionCode(ingredient.getIntentionCode());
					accounting.setIntentionSupplierCode(ingredient.getIntentionSupplierCode());
					accounting.setMerchandiseCode(ingredient.getMerchandiseCode());
					accounting.setSupplierCode(ingredient.getSupplierCode());
					accounting.setInlandImport(ingredient.getInlandImport());
					map.put("accounting", accounting);
					accountingDao.insertAccounting(map);
				} else {
					map.put("ingredientCode", ingredient.getIngredientCode());
					this.ingredientDao.deleteIngredient(map);
					this.ingredientDao.deleteIngredientItem(map);
				}
				// 投入量占比 计算并赋值
				for (IngredientItem ingredientItem : ingredientItemList) {
					// 投料编号赋值
					ingredientItem.setIngredientCode(ingredient.getIngredientCode());
					// 投入量占比计算并赋值
					if (null == ingredientItem.getInputCount() || ingredientItem.getInputCount().compareTo(BigDecimal.ZERO) == 0) {
						ingredientItem.setInputCountProportion(new BigDecimal(0));
					} else {
						ingredientItem.setInputCountProportion(ingredientItem.getInputCount().multiply(new BigDecimal(100)).divide(inputCountSum, 2, BigDecimal.ROUND_HALF_UP));
					}
					// 投入成本占比计算并赋值
					if (null == ingredientItem.getInputCost() || ingredientItem.getInputCost().compareTo(BigDecimal.ZERO) == 0) {
						ingredientItem.setInputCostProportion(new BigDecimal(0));
					} else {
						ingredientItem.setInputCostProportion(ingredientItem.getInputCost().multiply(new BigDecimal(100)).divide(inputCostSum, 2, BigDecimal.ROUND_HALF_UP));
					}
				}
				map.put("ingredient", ingredient);
				map.put("ingredientItemList", ingredientItemList);
				this.ingredientDao.insertIngredient(map);
				this.ingredientDao.insertIngredientItem(map);
				msg.append("导入投料表成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "导入投料表发生异常";
		}
		return msg.toString();
	}
}