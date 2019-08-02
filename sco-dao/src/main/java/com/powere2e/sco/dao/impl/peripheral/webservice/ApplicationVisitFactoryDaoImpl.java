package com.powere2e.sco.dao.impl.peripheral.webservice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationVisitFactoryDao;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactory;
/**
 * 商品巡厂申请单DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationVisitFactoryDaoImpl extends DaoImpl implements ApplicationVisitFactoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -602196144387631247L;
	//查询
	@Override
	public List<ApplicationVisitFactory> listApplicationVisitFactory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ApplicationVisitFactoryDao.class, "searchApplicationVisitFactory", map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationVisitFactory(Map<String, Object> map){
		this.insert(ApplicationVisitFactoryDao.class, "saveApplicationVisitFactory", map);
	}
	//删除
	@Override
	public void deleteApplicationVisitFactory(Map<String, Object> map){
		this.delete(ApplicationVisitFactoryDao.class, "deleteApplicationVisitFactory", map);
	}
	//修改
	@Override
	public void updateApplicationVisitFactory(Map<String, Object> map){
		this.update(ApplicationVisitFactoryDao.class, "updateApplicationVisitFactory", map);
	}
	//装载一个商品巡厂申请单
	@Override
	public ApplicationVisitFactory loadApplicationVisitFactory(Map<String, Object> map) {
		return (ApplicationVisitFactory)this.get(ApplicationVisitFactoryDao.class, "searchApplicationVisitFactory", map);
	}
}