package com.powere2e.sco.service.impl.peripheral.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationVisitFactoryDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.ApplicationVisitFactoryService;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactory;
/**
 * 商品巡厂申请单业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationVisitFactoryServiceImpl extends ServiceImpl implements ApplicationVisitFactoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8680950062534072475L;
	private ApplicationVisitFactoryDao applicationVisitFactoryDao;
	public static ApplicationVisitFactoryService getInstance(){
		return (ApplicationVisitFactoryService)ConfigFactory.getInstance().getBean("applicationVisitFactoryService");
	}
	//获得商品巡厂申请单DAO实例
	public ApplicationVisitFactoryDao getApplicationVisitFactoryDao() {
		return applicationVisitFactoryDao;
	}
	//设置商品巡厂申请单DAO实例
	public void setApplicationVisitFactoryDao(ApplicationVisitFactoryDao applicationVisitFactoryDao) {
		this.applicationVisitFactoryDao = applicationVisitFactoryDao;
	}
	//查询
	@Override
	public List<ApplicationVisitFactory> listApplicationVisitFactory(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationVisitFactoryDao().listApplicationVisitFactory(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationVisitFactory(ApplicationVisitFactory applicationVisitFactory){
		this.getApplicationVisitFactoryDao().insertApplicationVisitFactory(applicationVisitFactory.toMap());
	}
	//删除
	@Override
	public void deleteApplicationVisitFactory(String applicationVfCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationVfCode", applicationVfCode);
		this.getApplicationVisitFactoryDao().deleteApplicationVisitFactory(map);
	}
	//修改
	@Override
	public void updateApplicationVisitFactory(ApplicationVisitFactory applicationVisitFactory){
		this.getApplicationVisitFactoryDao().updateApplicationVisitFactory(applicationVisitFactory.toMap());
	}
	//加载一个商品巡厂申请单
	@Override
	public ApplicationVisitFactory loadApplicationVisitFactory(String applicationVfCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationVfCode", applicationVfCode);
		return this.getApplicationVisitFactoryDao().loadApplicationVisitFactory(map);
	}
}