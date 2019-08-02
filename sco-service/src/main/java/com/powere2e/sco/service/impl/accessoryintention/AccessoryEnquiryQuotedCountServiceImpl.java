package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryQuotedCountDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryQuotedCountService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
/**
 * 询价单原材料业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryQuotedCountServiceImpl extends ServiceImpl implements AccessoryEnquiryQuotedCountService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8153670923292665037L;
	private AccessoryEnquiryQuotedCountDao accessoryEnquiryQuotedCountDao;
	public static AccessoryEnquiryQuotedCountService getInstance(){
		return (AccessoryEnquiryQuotedCountService)ConfigFactory.getInstance().getBean("accessoryEnquiryQuotedCountService");
	}
	//获得询价单原材料DAO实例
	public AccessoryEnquiryQuotedCountDao getAccessoryEnquiryQuotedCountDao() {
		return accessoryEnquiryQuotedCountDao;
	}
	//设置询价单原材料DAO实例
	public void setAccessoryEnquiryQuotedCountDao(AccessoryEnquiryQuotedCountDao accessoryEnquiryQuotedCountDao) {
		this.accessoryEnquiryQuotedCountDao = accessoryEnquiryQuotedCountDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryQuotedCount> listAccessoryEnquiryQuotedCount(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryQuotedCountDao().listAccessoryEnquiryQuotedCount(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryQuotedCount(AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount){
		this.getAccessoryEnquiryQuotedCountDao().insertAccessoryEnquiryQuotedCount(accessoryEnquiryQuotedCount.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryQuotedCount(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryQuotedCountDao().deleteAccessoryEnquiryQuotedCount(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryQuotedCount(AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount){
		this.getAccessoryEnquiryQuotedCountDao().updateAccessoryEnquiryQuotedCount(accessoryEnquiryQuotedCount.toMap());
	}
	//加载一个询价单原材料
	@Override
	public AccessoryEnquiryQuotedCount loadAccessoryEnquiryQuotedCount(String enquiryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		return this.getAccessoryEnquiryQuotedCountDao().loadAccessoryEnquiryQuotedCount(map);
	}
}