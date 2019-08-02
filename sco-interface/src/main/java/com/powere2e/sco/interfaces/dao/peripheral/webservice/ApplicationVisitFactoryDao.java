package com.powere2e.sco.interfaces.dao.peripheral.webservice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactory;
/**
 * 商品巡厂申请单DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public interface ApplicationVisitFactoryDao extends Dao {
	/**
	 * 商品巡厂申请单查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品巡厂申请单列表
	 */
	public List<ApplicationVisitFactory> listApplicationVisitFactory(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个商品巡厂申请单
	 *
	 * @param map
	 *				
	 * @return
	 */
	public ApplicationVisitFactory loadApplicationVisitFactory(Map<String,Object> map);
	/**
	 * 添加商品巡厂申请单
	 *
	 * @param map
	 *				
	 */
	public void insertApplicationVisitFactory(Map<String, Object> map);
	/**
	 * 删除商品巡厂申请单
	 *
	 * @param map 
	 *				必须参数id为要删除的商品巡厂申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationVisitFactory(Map<String, Object> map);
	/**
	 * 修改商品巡厂申请单
	 *
	 * @param map 
	 *				必须参数id为要修改商品巡厂申请单的id号，不能为数组
	 */
	public void updateApplicationVisitFactory(Map<String, Object> map);
}