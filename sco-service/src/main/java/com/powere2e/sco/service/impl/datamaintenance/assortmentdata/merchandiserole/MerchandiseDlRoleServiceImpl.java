package com.powere2e.sco.service.impl.datamaintenance.assortmentdata.merchandiserole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRoleDao;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRoleService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRole;

/**
 * 商品定量角色业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDlRoleServiceImpl extends ServiceImpl implements MerchandiseDlRoleService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5865610749433790632L;
	private MerchandiseDlRoleDao merchandiseDlRoleDao;

	public static MerchandiseDlRoleService getInstance() {
		return (MerchandiseDlRoleService) ConfigFactory.getInstance().getBean("merchandiseDlRoleService");
	}

	// 获得商品定量角色DAO实例
	public MerchandiseDlRoleDao getMerchandiseDlRoleDao() {
		return merchandiseDlRoleDao;
	}

	// 设置商品定量角色DAO实例
	public void setMerchandiseDlRoleDao(MerchandiseDlRoleDao merchandiseDlRoleDao) {
		this.merchandiseDlRoleDao = merchandiseDlRoleDao;
	}

	// 查询
	@Override
	public List<MerchandiseDlRole> listMerchandiseDlRole(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseDlRoleDao().listMerchandiseDlRole(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseDlRole(MerchandiseDlRole merchandiseDlRole) {
		this.getMerchandiseDlRoleDao().insertMerchandiseDlRole(merchandiseDlRole.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandiseDlRole(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode.split(","));
		this.getMerchandiseDlRoleDao().deleteMerchandiseDlRole(map);
	}

	// 加载一个商品定量角色
	@Override
	public MerchandiseDlRole loadMerchandiseDlRole(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		return this.getMerchandiseDlRoleDao().loadMerchandiseDlRole(map);
	}

	// 加载一个商品定量角色
	@Override
	public MerchandiseDlRole loadMerchandiseDlRoleByroleName(String roleName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		return this.getMerchandiseDlRoleDao().loadMerchandiseDlRoleByroleName(map);
	}

	// 查询该商品定量角色是否存在于商品
	@Override
	public Integer serachMerchandise(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		return this.getMerchandiseDlRoleDao().searchMerchandise(map);
	}
}