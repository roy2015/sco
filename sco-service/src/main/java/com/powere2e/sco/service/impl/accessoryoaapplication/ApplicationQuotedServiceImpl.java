package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.ApplicationQuotedDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.ApplicationQuotedService;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;
/**
 * 报价单业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月30日
 */
public class ApplicationQuotedServiceImpl extends ServiceImpl implements ApplicationQuotedService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7209325582597736398L;
	private ApplicationQuotedDao applicationQuotedDao;
	public static ApplicationQuotedService getInstance(){
		return (ApplicationQuotedService)ConfigFactory.getInstance().getBean("applicationQuotedService");
	}
	//获得报价单DAO实例
	public ApplicationQuotedDao getApplicationQuotedDao() {
		return applicationQuotedDao;
	}
	//设置报价单DAO实例
	public void setApplicationQuotedDao(ApplicationQuotedDao applicationQuotedDao) {
		this.applicationQuotedDao = applicationQuotedDao;
	}
	//查询
	@Override
	public List<ApplicationQuoted> listApplicationQuoted(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationQuotedDao().listApplicationQuoted(map,pageInfo);
	}
	//查询
	@Override
	public List<ApplicationQuoted> listApplicationQuotedForDhInfo(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationQuotedDao().listApplicationQuotedForDhInfo(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationQuoted(ApplicationQuoted applicationQuoted){
		this.getApplicationQuotedDao().insertApplicationQuoted(applicationQuoted.toMap());
	}
	//删除
	@Override
	public void deleteApplicationQuoted(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getApplicationQuotedDao().deleteApplicationQuoted(map);
	}
	//删除
		@Override
		public void deleteApplicationQuotedFromCode(String applicationCode){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCode", applicationCode);
			this.getApplicationQuotedDao().deleteApplicationQuoted(map);
		}
	//修改
	@Override
	public void updateApplicationQuoted(ApplicationQuoted applicationQuoted){
		this.getApplicationQuotedDao().updateApplicationQuoted(applicationQuoted.toMap());
	}
	//加载一个报价单
	@Override
	public ApplicationQuoted loadApplicationQuoted(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return this.getApplicationQuotedDao().loadApplicationQuoted(map);
	}
}