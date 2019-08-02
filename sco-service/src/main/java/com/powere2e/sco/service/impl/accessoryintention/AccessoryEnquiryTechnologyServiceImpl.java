package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryTechnologyDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryTechnologyService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
/**
 * 询价单原材料业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryTechnologyServiceImpl extends ServiceImpl implements AccessoryEnquiryTechnologyService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8803180086586627682L;
	private AccessoryEnquiryTechnologyDao accessoryEnquiryTechnologyDao;
	public static AccessoryEnquiryTechnologyService getInstance(){
		return (AccessoryEnquiryTechnologyService)ConfigFactory.getInstance().getBean("accessoryEnquiryTechnologyService");
	}
	//获得询价单原材料DAO实例
	public AccessoryEnquiryTechnologyDao getAccessoryEnquiryTechnologyDao() {
		return accessoryEnquiryTechnologyDao;
	}
	//设置询价单原材料DAO实例
	public void setAccessoryEnquiryTechnologyDao(AccessoryEnquiryTechnologyDao accessoryEnquiryTechnologyDao) {
		this.accessoryEnquiryTechnologyDao = accessoryEnquiryTechnologyDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryTechnology> listAccessoryEnquiryTechnology(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryTechnologyDao().listAccessoryEnquiryTechnology(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryTechnology(AccessoryEnquiryTechnology accessoryEnquiryTechnology){
		this.getAccessoryEnquiryTechnologyDao().insertAccessoryEnquiryTechnology(accessoryEnquiryTechnology.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryTechnology(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryTechnologyDao().deleteAccessoryEnquiryTechnology(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryTechnology(AccessoryEnquiryTechnology accessoryEnquiryTechnology){
		this.getAccessoryEnquiryTechnologyDao().updateAccessoryEnquiryTechnology(accessoryEnquiryTechnology.toMap());
	}
	//加载一个询价单原材料
	@Override
	public AccessoryEnquiryTechnology loadAccessoryEnquiryTechnology(String technologyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("technologyCode", technologyCode);
		return this.getAccessoryEnquiryTechnologyDao().loadAccessoryEnquiryTechnology(map);
	}
}