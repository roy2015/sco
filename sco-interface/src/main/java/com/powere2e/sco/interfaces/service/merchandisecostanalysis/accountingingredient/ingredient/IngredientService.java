package com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.ingredient;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品投料表Service接口
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public interface IngredientService extends Service {
	/**
	 * 投料表查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回投料表列表
	 */
	public List<Ingredient> listIngredient(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个投料表
	 *
	 * @param map
	 *
	 * @return
	 */
	public Ingredient loadIngredient(Map<String, Object> map);

	/**
	 * 添加投料表
	 *
	 * @param map
	 *
	 */
	public void insertIngredient(Ingredient ingredient);

	/**
	 * 删除投料表
	 *
	 * @param map
	 *            必须参数id为要删除的投料表id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteIngredient(String ingredientCode);

	/**
	 * 修改投料表
	 *
	 * @param map
	 *            必须参数id为要修改投料表的id号，不能为数组
	 */
	public void updateIngredient(Ingredient ingredient);

	/**
	 * 投料表明细查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回投料表明细
	 */
	public List<IngredientItem> listIngredientItem(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加投料表明细
	 *
	 * @param map
	 *
	 */
	public void insertIngredientItem(Map<String, Object> map);

	/**
	 * 删除投料表明细
	 *
	 * @param map
	 *            必须参数id为要删除的投料表明细id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteIngredientItem(Map<String, Object> map);

	/**
	 * 上传投料表
	 * 
	 * @param file
	 * @return
	 */
	public String uploadIngredientItem(File file, Ingredient ingredient);
}