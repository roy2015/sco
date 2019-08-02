package com.powere2e.sco.action.merchandisecostanalysis.accountingingredient.ingredient;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.ingredient.IngredientService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品投料表的WEB请求响应类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public class IngredientAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8545216038709778461L;
	private IngredientService ingredientService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		ingredientService = (IngredientService) ConfigFactory.getInstance().getBean("ingredientService");
	}

	/**
	 * 商品投料表列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doListIngredientItem() throws Exception {
		Map<String, Object> map = getIngredient().toMap();
		List<IngredientItem> list = ingredientService.listIngredientItem(map, null);
		this.forwardData(true, list, null);
	}

	/**
	 * 添加商品投料表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doInsertIngredientItem() throws Exception {
		Ingredient ingredient = getIngredient();
		ingredient.setIngredientCode(SequenceFactory.getInstance().nextID("ingredient_item"));
		ingredientService.insertIngredient(ingredient);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除商品投料表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doDeleteIngredientItem() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ingredientCode", asString("ingredientCode"));
		ingredientService.deleteIngredientItem(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 下载商品投料表模版
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doDownloadIngredientItemTemplate() throws Exception {
		this.forwardDownload("excel/sco/merchandiseCostAnalysis/accountingIngredient/ingredient/ingredientItemTemplate.xlsx", ExcelUtils.getEncodeFileName("投料表_" + DateUtils.formateDateTime() + ".xlsx"));
	}

	/**
	 * 显示上传投料表界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowUploadIngredientItemForm() {
		this.putObject("ingredientCode", this.asString("ingredientCode"));
		this.putObject("intentionCode", this.asString("intentionCode"));
		this.putObject("intentionSupplierCode", this.asString("intentionSupplierCode"));
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/ingredient/ingredientItemUploadForm.ftl");
	}

	/**
	 * 导入投料表Excel
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doUploadIngredientItem() {
		String msg;
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
			msg = ingredientService.uploadIngredientItem(file, this.getIngredient());
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		this.forwardData(true, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Ingredient getIngredient() throws Exception {
		Ingredient ingredient = new Ingredient();
		this.asBean(ingredient);
		return ingredient;
	}
}
