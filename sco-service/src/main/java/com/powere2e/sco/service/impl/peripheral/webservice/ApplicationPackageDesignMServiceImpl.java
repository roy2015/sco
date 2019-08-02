package com.powere2e.sco.service.impl.peripheral.webservice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationPackageDesignMDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.ApplicationPackageDesignMService;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesignM;
/**
 * 商品包装设计申请单(商品)业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationPackageDesignMServiceImpl extends ServiceImpl implements ApplicationPackageDesignMService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8325899846267385547L;
	private ApplicationPackageDesignMDao applicationPackageDesignMDao;
	public static ApplicationPackageDesignMService getInstance(){
		return (ApplicationPackageDesignMService)ConfigFactory.getInstance().getBean("applicationPackageDesignMService");
	}
	//获得商品包装设计申请单(商品)DAO实例
	public ApplicationPackageDesignMDao getApplicationPackageDesignMDao() {
		return applicationPackageDesignMDao;
	}
	//设置商品包装设计申请单(商品)DAO实例
	public void setApplicationPackageDesignMDao(ApplicationPackageDesignMDao applicationPackageDesignMDao) {
		this.applicationPackageDesignMDao = applicationPackageDesignMDao;
	}
	//查询
	@Override
	public List<ApplicationPackageDesignM> listApplicationPackageDesignM(Map<String, Object> map,PageInfo pageInfo){
		return this.getApplicationPackageDesignMDao().listApplicationPackageDesignM(map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationPackageDesignM(ApplicationPackageDesignM applicationPackageDesignM){
		this.getApplicationPackageDesignMDao().insertApplicationPackageDesignM(applicationPackageDesignM.toMap());
	}
	//删除
	@Override
	public void deleteApplicationPackageDesignM(String applicationPdCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationPdCode", applicationPdCode);
		this.getApplicationPackageDesignMDao().deleteApplicationPackageDesignM(map);
	}
	//修改
	@Override
	public void updateApplicationPackageDesignM(ApplicationPackageDesignM applicationPackageDesignM){
		this.getApplicationPackageDesignMDao().updateApplicationPackageDesignM(applicationPackageDesignM.toMap());
	}
	//加载一个商品包装设计申请单(商品)
	@Override
	public ApplicationPackageDesignM loadApplicationPackageDesignM(String applicationPdCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationPdCode", applicationPdCode);
		return this.getApplicationPackageDesignMDao().loadApplicationPackageDesignM(map);
	}
}