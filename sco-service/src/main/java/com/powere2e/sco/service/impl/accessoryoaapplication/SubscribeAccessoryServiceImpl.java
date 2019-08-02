package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.SubscribeAccessoryDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.SubscribeAccessoryService;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
/**
 * 申购产品信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class SubscribeAccessoryServiceImpl extends ServiceImpl implements SubscribeAccessoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8596134597647386448L;
	private SubscribeAccessoryDao subscribeAccessoryDao;
	public static SubscribeAccessoryService getInstance(){
		return (SubscribeAccessoryService)ConfigFactory.getInstance().getBean("subscribeAccessoryService");
	}
	//获得申购产品信息DAO实例
	public SubscribeAccessoryDao getSubscribeAccessoryDao() {
		return subscribeAccessoryDao;
	}
	//设置申购产品信息DAO实例
	public void setSubscribeAccessoryDao(SubscribeAccessoryDao subscribeAccessoryDao) {
		this.subscribeAccessoryDao = subscribeAccessoryDao;
	}
	//查询
	@Override
	public List<SubscribeAccessory> listSubscribeAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.getSubscribeAccessoryDao().listSubscribeAccessory(map,pageInfo);
	}
	//添加
	@Override
	public void insertSubscribeAccessory(SubscribeAccessory subscribeAccessory){
		this.getSubscribeAccessoryDao().insertSubscribeAccessory(subscribeAccessory.toMap());
	}
	//删除
	@Override
	public void deleteSubscribeAccessory(String applicationCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getSubscribeAccessoryDao().deleteSubscribeAccessory(map);
	}
	//修改
	@Override
	public void updateSubscribeAccessory(SubscribeAccessory subscribeAccessory){
		this.getSubscribeAccessoryDao().updateSubscribeAccessory(subscribeAccessory.toMap());
	}
	//加载一个申购产品信息
	@Override
	public SubscribeAccessory loadSubscribeAccessory(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getSubscribeAccessoryDao().loadSubscribeAccessory(map);
	}
}