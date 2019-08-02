package com.powere2e.sco.dao.impl.datamaintenance.assortmentdata.merchandiserole;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRoleDao;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRole;
/**
 * 商品定性角色DAO接口的实现
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDxRoleDaoImpl extends DaoImpl implements MerchandiseDxRoleDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4083918347545154545L;
	//查询
	@Override
	public List<MerchandiseDxRole> listMerchandiseDxRole(Map<String, Object> map,PageInfo pageInfo){
		return this.query(MerchandiseDxRoleDao.class, "searchMerchandiseDxRole", map,pageInfo);
	}
	//添加
	@Override
	public void insertMerchandiseDxRole(Map<String, Object> map){
		this.insert(MerchandiseDxRoleDao.class, "saveMerchandiseDxRole", map);
	}
	//删除
	@Override
	public void deleteMerchandiseDxRole(Map<String, Object> map){
		this.delete(MerchandiseDxRoleDao.class, "deleteMerchandiseDxRole", map);
	}
	//装载一个商品定性角色
	@Override
	public MerchandiseDxRole loadMerchandiseDxRole(Map<String, Object> map) {
		return (MerchandiseDxRole)this.get(MerchandiseDxRoleDao.class, "searchMerchandiseDxRole", map);
	}
	//装载一个商品定性角色
	@Override
	public MerchandiseDxRole loadMerchandiseDxRoleByroleName(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (MerchandiseDxRole)this.get(MerchandiseDxRoleDao.class, "searchMerchandiseDxRoleByroleName", map);
	}
	@Override
	public Integer searchMerchandise(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(MerchandiseDxRoleDao.class, "searchMerchandise", map);
	}
}