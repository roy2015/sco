package com.powere2e.sco.service.impl.peripheral.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationVisitFactoryMDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.ApplicationVisitFactoryMService;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactoryM;
/**
 * 商品包装设计申请单业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationVisitFactoryMServiceImpl extends ServiceImpl implements ApplicationVisitFactoryMService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6653338327930606666L;
	private ApplicationVisitFactoryMDao applicationVisitFactoryMDao;
	public static ApplicationVisitFactoryMService getInstance(){
		return (ApplicationVisitFactoryMService)ConfigFactory.getInstance().getBean("applicationVisitFactoryMService");
	}
	//获得商品包装设计申请单DAO实例
	public ApplicationVisitFactoryMDao getApplicationVisitFactoryMDao() {
		return applicationVisitFactoryMDao;
	}
	//设置商品包装设计申请单DAO实例
	public void setApplicationVisitFactoryMDao(ApplicationVisitFactoryMDao applicationVisitFactoryMDao) {
		this.applicationVisitFactoryMDao = applicationVisitFactoryMDao;
	}
	//查询
	@Override
	public List<ApplicationVisitFactoryM> listApplicationVisitFactoryM(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationVisitFactoryMDao().listApplicationVisitFactoryM(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationVisitFactoryM(ApplicationVisitFactoryM applicationVisitFactoryM){
		this.getApplicationVisitFactoryMDao().insertApplicationVisitFactoryM(applicationVisitFactoryM.toMap());
	}
	//删除
	@Override
	public void deleteApplicationVisitFactoryM(String applicationVfCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationVfCode", applicationVfCode);
		this.getApplicationVisitFactoryMDao().deleteApplicationVisitFactoryM(map);
	}
	//修改
	@Override
	public void updateApplicationVisitFactoryM(ApplicationVisitFactoryM applicationVisitFactoryM){
		this.getApplicationVisitFactoryMDao().updateApplicationVisitFactoryM(applicationVisitFactoryM.toMap());
	}
	//加载一个商品包装设计申请单
	@Override
	public ApplicationVisitFactoryM loadApplicationVisitFactoryM(String applicationVfCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationVfCode", applicationVfCode);
		return this.getApplicationVisitFactoryMDao().loadApplicationVisitFactoryM(map);
	}
}