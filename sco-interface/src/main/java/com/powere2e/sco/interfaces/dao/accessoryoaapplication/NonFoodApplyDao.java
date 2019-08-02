package com.powere2e.sco.interfaces.dao.accessoryoaapplication;


import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.NonFoodApply;
/**
 * 采购委员会OA申请DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public interface NonFoodApplyDao extends Dao {
	
	/**
	 * 采购委员会竞价单OA申请意向品列表
	 */
	public List<NonFoodApply> listNonFoodApplyIntentionApplication(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 根据报价单编号获得采购委员会竞价单OA申请意向品
	 */
	public NonFoodApply loadNonFoodApplyIntentionApplication(Map<String, Object> map);
	/**
	 *更新关联表
	 */
	public void updateIntentionSupplierAccessory(Map<String, Object> map);
}