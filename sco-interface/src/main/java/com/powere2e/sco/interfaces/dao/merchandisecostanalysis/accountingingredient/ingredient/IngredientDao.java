package com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.ingredient;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品投料表DAO接口
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public interface IngredientDao extends Dao {
	/**
	 * 商品投料表查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品投料表列表
	 */
	public List<Ingredient> listIngredient(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个商品投料表
	 *
	 * @param map
	 *
	 * @return
	 */
	public Ingredient loadIngredient(Map<String, Object> map);

	/**
	 * 添加商品投料表
	 *
	 * @param map
	 *
	 */
	public void insertIngredient(Map<String, Object> map);

	/**
	 * 删除商品投料表
	 *
	 * @param map
	 *            必须参数id为要删除的商品投料表id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteIngredient(Map<String, Object> map);

	/**
	 * 修改商品投料表
	 *
	 * @param map
	 *            必须参数id为要修改商品投料表的id号，不能为数组
	 */
	public void updateIngredient(Map<String, Object> map);

	/**
	 * 商品投料表查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品投料表列表
	 */
	public List<IngredientItem> listIngredientItem(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品投料表
	 *
	 * @param map
	 *
	 */
	public void insertIngredientItem(Map<String, Object> map);

	/**
	 * 删除商品投料表
	 *
	 * @param map
	 *            必须参数id为要删除的商品投料表id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteIngredientItem(Map<String, Object> map);
}