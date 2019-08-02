package com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRole;
/**
 * 商品定性角色DAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public interface MerchandiseDxRoleDao extends Dao {
	/**
	 * 商品定性角色查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品定性角色列表
	 */
	public List<MerchandiseDxRole> listMerchandiseDxRole(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个商品定性角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseDxRole loadMerchandiseDxRole(Map<String,Object> map);
	/**
	 * 添加商品定性角色
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseDxRole(Map<String, Object> map);
	/**
	 * 删除商品定性角色
	 *
	 * @param map 
	 *				必须参数id为要删除的商品定性角色id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseDxRole(Map<String, Object> map);
	/**
	 * 根据商品定性角色名称加载一个商品定性角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseDxRole loadMerchandiseDxRoleByroleName(Map<String,Object> map);
	/**
	 * 查询该商品定性角色是否存在与商品表
	 * @param map
	 * @return
	 */
	public Integer searchMerchandise(Map<String,Object> map);
}