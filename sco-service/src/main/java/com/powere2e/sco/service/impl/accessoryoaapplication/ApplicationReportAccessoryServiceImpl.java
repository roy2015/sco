package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.ApplicationReportAccessoryDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationReportAccessoryService;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
/**
 * 申请报告(辅料OA)业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class ApplicationReportAccessoryServiceImpl extends ServiceImpl implements ApplicationReportAccessoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5731063580822656409L;
	private ApplicationReportAccessoryDao applicationReportAccessoryDao;
	public static ApplicationReportAccessoryService getInstance(){
		return (ApplicationReportAccessoryService)ConfigFactory.getInstance().getBean("applicationReportAccessoryService");
	}
	//获得申请报告(辅料OA)DAO实例
	public ApplicationReportAccessoryDao getApplicationReportAccessoryDao() {
		return applicationReportAccessoryDao;
	}
	//设置申请报告(辅料OA)DAO实例
	public void setApplicationReportAccessoryDao(ApplicationReportAccessoryDao applicationReportAccessoryDao) {
		this.applicationReportAccessoryDao = applicationReportAccessoryDao;
	}
	//查询
	@Override
	public List<ApplicationReportAccessory> listApplicationReportAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationReportAccessoryDao().listApplicationReportAccessory(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationReportAccessory(ApplicationReportAccessory applicationReportAccessory){
		this.getApplicationReportAccessoryDao().insertApplicationReportAccessory(applicationReportAccessory.toMap());
	}
	//删除
	@Override
	public void deleteApplicationReportAccessory(String applicationCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getApplicationReportAccessoryDao().deleteApplicationReportAccessory(map);
	}
	//修改
	@Override
	public void updateApplicationReportAccessory(ApplicationReportAccessory applicationReportAccessory){
		this.getApplicationReportAccessoryDao().updateApplicationReportAccessory(applicationReportAccessory.toMap());
	}
	//加载一个申请报告(辅料OA)
	@Override
	public ApplicationReportAccessory loadApplicationReportAccessory(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getApplicationReportAccessoryDao().loadApplicationReportAccessory(map);
	}
}