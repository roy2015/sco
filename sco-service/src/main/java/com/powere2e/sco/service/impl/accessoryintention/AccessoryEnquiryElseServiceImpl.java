package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryElseDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryElseService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
/**
 * 询价单其他要求业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月30日
 */
public class AccessoryEnquiryElseServiceImpl extends ServiceImpl implements AccessoryEnquiryElseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2637402608989434459L;
	private AccessoryEnquiryElseDao accessoryEnquiryElseDao;
	public static AccessoryEnquiryElseService getInstance(){
		return (AccessoryEnquiryElseService)ConfigFactory.getInstance().getBean("accessoryEnquiryElseService");
	}
	//获得询价单其他要求DAO实例
	public AccessoryEnquiryElseDao getAccessoryEnquiryElseDao() {
		return accessoryEnquiryElseDao;
	}
	//设置询价单其他要求DAO实例
	public void setAccessoryEnquiryElseDao(AccessoryEnquiryElseDao accessoryEnquiryElseDao) {
		this.accessoryEnquiryElseDao = accessoryEnquiryElseDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiryElse> listAccessoryEnquiryElse(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryElseDao().listAccessoryEnquiryElse(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiryElse(AccessoryEnquiryElse accessoryEnquiryElse){
		this.getAccessoryEnquiryElseDao().insertAccessoryEnquiryElse(accessoryEnquiryElse.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiryElse(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryElseDao().deleteAccessoryEnquiryElse(map);
	}
	//修改
	@Override
	public void updateAccessoryEnquiryElse(AccessoryEnquiryElse accessoryEnquiryElse){
		this.getAccessoryEnquiryElseDao().updateAccessoryEnquiryElse(accessoryEnquiryElse.toMap());
	}
	//加载一个询价单其他要求
	@Override
	public AccessoryEnquiryElse loadAccessoryEnquiryElse(String elseCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("elseCode", elseCode);
		return this.getAccessoryEnquiryElseDao().loadAccessoryEnquiryElse(map);
	}
}