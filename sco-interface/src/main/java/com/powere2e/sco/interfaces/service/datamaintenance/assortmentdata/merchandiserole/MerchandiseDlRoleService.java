package com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandiserole;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRole;
/**
 * 商品定量角色Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public interface MerchandiseDlRoleService extends Service {
	/**
	 * 商品定量角色查询
	 
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
	public MerchandiseDlRole loadMerchandiseDlRole(String roleCode);
	/**
	 * 根据商品定量名称加载一个商品定量角色
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseDlRole loadMerchandiseDlRoleByroleName(String roleName);
	/**
	 * 添加商品定量角色
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseDlRole(MerchandiseDlRole merchandiseDlRole);
	/**
	 * 删除商品定量角色
	 *
	 * @param map 
	 *				必须参数id为要删除的商品定量角色id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseDlRole(String roleCode);
	/**
	 * 查询该商品定量角色是否存在与商品
	 * @param roleCode
	 * @return
	 */
	public Integer serachMerchandise(String roleCode);
}