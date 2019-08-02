package com.powere2e.sco.dao.impl.datamaintenance.assortmentdata.merchandiserole;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRoleDao;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRole;
/**
 * 商品定性角色DAO接口的实现
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDlRoleDaoImpl extends DaoImpl implements MerchandiseDlRoleDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7314155906871905410L;
	//查询
	@Override
	public List<MerchandiseDlRole> listMerchandiseDlRole(Map<String, Object> map,PageInfo pageInfo){
		return this.query(MerchandiseDlRoleDao.class, "searchMerchandiseDlRole", map,pageInfo);
	}
	//添加
	@Override
	public void insertMerchandiseDlRole(Map<String, Object> map){
		this.insert(MerchandiseDlRoleDao.class, "saveMerchandiseDlRole", map);
	}
	//删除
	@Override
	public void deleteMerchandiseDlRole(Map<String, Object> map){
		this.delete(MerchandiseDlRoleDao.class, "deleteMerchandiseDlRole", map);
	}
	//装载一个商品定性角色
	@Override
	public MerchandiseDlRole loadMerchandiseDlRole(Map<String, Object> map) {
		return (MerchandiseDlRole)this.get(MerchandiseDlRoleDao.class, "searchMerchandiseDlRole", map);
	}
	//装载一个商品定性角色
	@Override
	public MerchandiseDlRole loadMerchandiseDlRoleByroleName(
			Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (MerchandiseDlRole)this.get(MerchandiseDlRoleDao.class, "searchMerchandiseDlRoleByroleName", map);
	}
	@Override
	public Integer searchMerchandise(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(MerchandiseDlRoleDao.class, "searchMerchandise", map);
	}
}