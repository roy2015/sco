package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryAccessoryDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryAccessoryService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
/**
 * 询价单原材料业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryAccessoryServiceImpl extends ServiceImpl implements AccessoryEnquiryAccessoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439446113030059451L;
	private AccessoryEnquiryAccessoryDao accessoryEnquiryAccessoryDao;
	public static AccessoryEnquiryAccessoryService getInstance(){
		return (AccessoryEnquiryAccessoryService)ConfigFactory.getInstance().getBean("accessoryEnquiryAccessoryService");
	}
	//获得询价单原材料DAO实例
	public AccessoryEnquiryAccessoryDao getAccessoryEnquiryAccessoryDao() {
		return accessoryEnquiryAccessoryDao;
	}
	//设置询价单原材料DAO实例
	public void setAccessoryEnquiryAccessoryDao(AccessoryEnquiryAccessoryDao accessoryEnquiryAccessoryDao) {
		this.accessoryEnquiryAccessoryDao = accessoryEnquiryAccessoryDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryAccessory> listAccessoryEnquiryAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryAccessoryDao().listAccessoryEnquiryAccessory(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryAccessory(AccessoryEnquiryAccessory accessoryEnquiryAccessory){
		this.getAccessoryEnquiryAccessoryDao().insertAccessoryEnquiryAccessory(accessoryEnquiryAccessory.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryAccessory(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryAccessoryDao().deleteAccessoryEnquiryAccessory(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryAccessory(AccessoryEnquiryAccessory accessoryEnquiryAccessory){
		this.getAccessoryEnquiryAccessoryDao().updateAccessoryEnquiryAccessory(accessoryEnquiryAccessory.toMap());
	}
	//加载一个询价单原材料
	@Override
	public AccessoryEnquiryAccessory loadAccessoryEnquiryAccessory(String accessoryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accessoryCode", accessoryCode);
		return this.getAccessoryEnquiryAccessoryDao().loadAccessoryEnquiryAccessory(map);
	}
}