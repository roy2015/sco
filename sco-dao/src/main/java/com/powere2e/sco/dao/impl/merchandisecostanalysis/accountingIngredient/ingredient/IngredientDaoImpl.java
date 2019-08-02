package com.powere2e.sco.dao.impl.merchandisecostanalysis.accountingIngredient.ingredient;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.ingredient.IngredientDao;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品投料表DAO接口的实现
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public class IngredientDaoImpl extends DaoImpl implements IngredientDao {

	private static final long serialVersionUID = 4597785741572336526L;

	// 查询
	@Override
	public List<Ingredient> listIngredient(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(IngredientDao.class, "searchIngredient", map, pageInfo);
	}
	
	// 装载一个商品投料表
	@Override
	public Ingredient loadIngredient(Map<String, Object> map) {
		return (Ingredient) this.get(IngredientDao.class, "loadIngredient", map);
	}

	// 添加
	@Override
	public void insertIngredient(Map<String, Object> map) {
		this.insert(IngredientDao.class, "saveIngredient", map);
	}

	// 删除
	@Override
	public void deleteIngredient(Map<String, Object> map) {
		this.delete(IngredientDao.class, "deleteIngredient", map);
	}

	// 修改
	@Override
	public void updateIngredient(Map<String, Object> map) {
		this.update(IngredientDao.class, "updateIngredient", map);
	}

	// 查询
	@Override
	public List<IngredientItem> listIngredientItem(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(IngredientDao.class, "searchIngredientItem", map, pageInfo);
	}

	// 添加
	@Override
	public void insertIngredientItem(Map<String, Object> map) {
		this.insert(IngredientDao.class, "saveIngredientItem", map);
	}

	// 删除
	@Override
	public void deleteIngredientItem(Map<String, Object> map) {
		this.delete(IngredientDao.class, "deleteIngredientItem", map);
	}
}