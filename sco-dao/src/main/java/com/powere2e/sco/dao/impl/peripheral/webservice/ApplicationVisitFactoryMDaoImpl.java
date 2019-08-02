package com.powere2e.sco.dao.impl.peripheral.webservice;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationVisitFactoryMDao;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactoryM;
/**
 * 商品包装设计申请单DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationVisitFactoryMDaoImpl extends DaoImpl implements ApplicationVisitFactoryMDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1162856640426794940L;
	//查询
	@Override
	public List<ApplicationVisitFactoryM> listApplicationVisitFactoryM(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ApplicationVisitFactoryMDao.class, "searchApplicationVisitFactoryM", map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationVisitFactoryM(Map<String, Object> map){
		this.insert(ApplicationVisitFactoryMDao.class, "saveApplicationVisitFactoryM", map);
	}
	//删除
	@Override
	public void deleteApplicationVisitFactoryM(Map<String, Object> map){
		this.delete(ApplicationVisitFactoryMDao.class, "deleteApplicationVisitFactoryM", map);
	}
	//修改
	@Override
	public void updateApplicationVisitFactoryM(Map<String, Object> map){
		this.update(ApplicationVisitFactoryMDao.class, "updateApplicationVisitFactoryM", map);
	}
	//装载一个商品包装设计申请单
	@Override
	public ApplicationVisitFactoryM loadApplicationVisitFactoryM(Map<String, Object> map) {
		return (ApplicationVisitFactoryM)this.get(ApplicationVisitFactoryMDao.class, "searchApplicationVisitFactoryM", map);
	}
}