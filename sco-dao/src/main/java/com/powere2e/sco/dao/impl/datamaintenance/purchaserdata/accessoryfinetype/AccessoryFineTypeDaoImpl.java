package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.accessoryfinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineTypeDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineType;
/**
 * 辅助细分类维护DAO接口的实现
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class AccessoryFineTypeDaoImpl extends DaoImpl implements AccessoryFineTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3662875040685335429L;
	//查询
	@Override
	public List<AccessoryFineType> listAccessoryFineType(Map<String, Object> map,PageInfo pageInfo){
		return this.query(AccessoryFineTypeDao.class, "searchAccessoryFineType", map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryFineType(Map<String, Object> map){
		this.insert(AccessoryFineTypeDao.class, "saveAccessoryFineType", map);
	}
	//删除
	@Override
	public void deleteAccessoryFineType(Map<String, Object> map){
		this.delete(AccessoryFineTypeDao.class, "deleteAccessoryFineType", map);
	}
	//装载一个辅助细分类维护
	@Override
	public AccessoryFineType loadAccessoryFineType(Map<String, Object> map) {
		return (AccessoryFineType)this.get(AccessoryFineTypeDao.class, "searchAccessoryFineType", map);
	}
	//获取辅料中存在一个辅料细分类的数量
	@Override
	public Integer searchAccessoryFineType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(AccessoryFineTypeDao.class, "searchAccessoryIntention", map);
	}
}