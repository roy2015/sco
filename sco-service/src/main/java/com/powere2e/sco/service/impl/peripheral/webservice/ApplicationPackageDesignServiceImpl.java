package com.powere2e.sco.service.impl.peripheral.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationPackageDesignDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.ApplicationPackageDesignService;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesign;
/**
 * 商品包装设计申请单业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationPackageDesignServiceImpl extends ServiceImpl implements ApplicationPackageDesignService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8143043333548418464L;
	private ApplicationPackageDesignDao applicationPackageDesignDao;
	public static ApplicationPackageDesignService getInstance(){
		return (ApplicationPackageDesignService)ConfigFactory.getInstance().getBean("applicationPackageDesignService");
	}
	//获得商品包装设计申请单DAO实例
	public ApplicationPackageDesignDao getApplicationPackageDesignDao() {
		return applicationPackageDesignDao;
	}
	//设置商品包装设计申请单DAO实例
	public void setApplicationPackageDesignDao(ApplicationPackageDesignDao applicationPackageDesignDao) {
		this.applicationPackageDesignDao = applicationPackageDesignDao;
	}
	//查询
	@Override
	public List<ApplicationPackageDesign> listApplicationPackageDesign(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationPackageDesignDao().listApplicationPackageDesign(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationPackageDesign(ApplicationPackageDesign applicationPackageDesign){
		this.getApplicationPackageDesignDao().insertApplicationPackageDesign(applicationPackageDesign.toMap());
	}
	//删除
	@Override
	public void deleteApplicationPackageDesign(String applicationPdCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationPdCode", applicationPdCode);
		this.getApplicationPackageDesignDao().deleteApplicationPackageDesign(map);
	}
	//修改
	@Override
	public void updateApplicationPackageDesign(ApplicationPackageDesign applicationPackageDesign){
		this.getApplicationPackageDesignDao().updateApplicationPackageDesign(applicationPackageDesign.toMap());
	}
	//加载一个商品包装设计申请单
	@Override
	public ApplicationPackageDesign loadApplicationPackageDesign(String applicationPdCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationPdCode", applicationPdCode);
		return this.getApplicationPackageDesignDao().loadApplicationPackageDesign(map);
	}
}