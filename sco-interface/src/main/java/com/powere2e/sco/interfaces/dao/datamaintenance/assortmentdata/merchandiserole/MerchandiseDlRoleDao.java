package com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRole;
/**
 * 商品定量角色DAO接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public interface MerchandiseDlRoleDao extends Dao {
	/**
	 * 商品定量角色查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品定量角色列表
	 */
	public List<MerchandiseDlRole> listMerchandiseDlRole(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个商品定量角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseDlRole loadMerchandiseDlRole(Map<String,Object> map);
	/**
	 * 添加商品定量角色
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseDlRole(Map<String, Object> map);
	/**
	 * 删除商品定量角色
	 *
	 * @param map 
	 *				必须参数id为要删除的商品定性角色id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseDlRole(Map<String, Object> map);
	/**
	 * 根据商品定性角色名称加载一个商品定性角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseDlRole loadMerchandiseDlRoleByroleName(Map<String,Object> map);
	/**
	 * 查询商品表中是否存在商品定性角色
	 * @param map
	 * @return
	 */
	public Integer searchMerchandise(Map<String,Object> map);
}