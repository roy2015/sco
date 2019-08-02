package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryMaterialDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryMaterialService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
/**
 * 询价单原材料业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryMaterialServiceImpl extends ServiceImpl implements AccessoryEnquiryMaterialService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7113612545367766115L;
	private AccessoryEnquiryMaterialDao accessoryEnquiryMaterialDao;
	public static AccessoryEnquiryMaterialService getInstance(){
		return (AccessoryEnquiryMaterialService)ConfigFactory.getInstance().getBean("accessoryEnquiryMaterialService");
	}
	//获得询价单原材料DAO实例
	public AccessoryEnquiryMaterialDao getAccessoryEnquiryMaterialDao() {
		return accessoryEnquiryMaterialDao;
	}
	//设置询价单原材料DAO实例
	public void setAccessoryEnquiryMaterialDao(AccessoryEnquiryMaterialDao accessoryEnquiryMaterialDao) {
		this.accessoryEnquiryMaterialDao = accessoryEnquiryMaterialDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryMaterial> listAccessoryEnquiryMaterial(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryMaterialDao().listAccessoryEnquiryMaterial(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryMaterial(AccessoryEnquiryMaterial accessoryEnquiryMaterial){
		this.getAccessoryEnquiryMaterialDao().insertAccessoryEnquiryMaterial(accessoryEnquiryMaterial.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryMaterial(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryMaterialDao().deleteAccessoryEnquiryMaterial(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryMaterial(AccessoryEnquiryMaterial accessoryEnquiryMaterial){
		this.getAccessoryEnquiryMaterialDao().updateAccessoryEnquiryMaterial(accessoryEnquiryMaterial.toMap());
	}
	//加载一个询价单原材料
	@Override
	public AccessoryEnquiryMaterial loadAccessoryEnquiryMaterial(String materialCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialCode", materialCode);
		return this.getAccessoryEnquiryMaterialDao().loadAccessoryEnquiryMaterial(map);
	}
}