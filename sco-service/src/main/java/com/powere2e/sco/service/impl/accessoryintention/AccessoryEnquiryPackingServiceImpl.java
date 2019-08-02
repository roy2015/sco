package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryPackingDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryPackingService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
/**
 * 询价单原材料业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryPackingServiceImpl extends ServiceImpl implements AccessoryEnquiryPackingService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8231488756387472995L;
	private AccessoryEnquiryPackingDao accessoryEnquiryPackingDao;
	public static AccessoryEnquiryPackingService getInstance(){
		return (AccessoryEnquiryPackingService)ConfigFactory.getInstance().getBean("accessoryEnquiryPackingService");
	}
	//获得询价单原材料DAO实例
	public AccessoryEnquiryPackingDao getAccessoryEnquiryPackingDao() {
		return accessoryEnquiryPackingDao;
	}
	//设置询价单原材料DAO实例
	public void setAccessoryEnquiryPackingDao(AccessoryEnquiryPackingDao accessoryEnquiryPackingDao) {
		this.accessoryEnquiryPackingDao = accessoryEnquiryPackingDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryPacking> listAccessoryEnquiryPacking(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryPackingDao().listAccessoryEnquiryPacking(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryPacking(AccessoryEnquiryPacking accessoryEnquiryPacking){
		this.getAccessoryEnquiryPackingDao().insertAccessoryEnquiryPacking(accessoryEnquiryPacking.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryPacking(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryPackingDao().deleteAccessoryEnquiryPacking(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryPacking(AccessoryEnquiryPacking accessoryEnquiryPacking){
		this.getAccessoryEnquiryPackingDao().updateAccessoryEnquiryPacking(accessoryEnquiryPacking.toMap());
	}
	//加载一个询价单原材料
	@Override
	public AccessoryEnquiryPacking loadAccessoryEnquiryPacking(String packingCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("packingCode", packingCode);
		return this.getAccessoryEnquiryPackingDao().loadAccessoryEnquiryPacking(map);
	}
}