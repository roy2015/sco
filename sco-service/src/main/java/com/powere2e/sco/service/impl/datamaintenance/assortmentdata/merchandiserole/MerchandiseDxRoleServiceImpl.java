package com.powere2e.sco.service.impl.datamaintenance.assortmentdata.merchandiserole;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRoleDao;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRoleService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRole;

/**
 * 商品定性角色业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDxRoleServiceImpl extends ServiceImpl implements MerchandiseDxRoleService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5082067662884377385L;
	private MerchandiseDxRoleDao merchandiseDxRoleDao;

	public static MerchandiseDxRoleService getInstance() {
		return (MerchandiseDxRoleService) ConfigFactory.getInstance().getBean("merchandiseDxRoleService");
	}

	// 获得商品定性角色DAO实例
	public MerchandiseDxRoleDao getMerchandiseDxRoleDao() {
		return merchandiseDxRoleDao;
	}

	// 设置商品定性角色DAO实例
	public void setMerchandiseDxRoleDao(MerchandiseDxRoleDao merchandiseDxRoleDao) {
		this.merchandiseDxRoleDao = merchandiseDxRoleDao;
	}

	// 查询
	@Override
	public List<MerchandiseDxRole> listMerchandiseDxRole(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandiseDxRoleDao().listMerchandiseDxRole(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseDxRole(MerchandiseDxRole merchandiseDxRole) {
		this.getMerchandiseDxRoleDao().insertMerchandiseDxRole(merchandiseDxRole.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandiseDxRole(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode.split(","));
		this.getMerchandiseDxRoleDao().deleteMerchandiseDxRole(map);
	}

	// 加载一个商品定性角色
	@Override
	public MerchandiseDxRole loadMerchandiseDxRole(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		return this.getMerchandiseDxRoleDao().loadMerchandiseDxRole(map);
	}

	// 加载一个商品定性角色
	@Override
	public MerchandiseDxRole loadMerchandiseDxRoleByroleName(String roleName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", roleName);
		return this.getMerchandiseDxRoleDao().loadMerchandiseDxRoleByroleName(map);
	}

	// 查询该商品定量角色是否存在于商品
	@Override
	public Integer serachMerchandise(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		return this.getMerchandiseDxRoleDao().searchMerchandise(map);
	}
}