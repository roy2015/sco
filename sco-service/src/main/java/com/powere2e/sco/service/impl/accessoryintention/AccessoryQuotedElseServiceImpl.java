package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedElseDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElseService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;
/**
 * 辅料报价-其他成本业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElseServiceImpl extends ServiceImpl implements AccessoryQuotedElseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9119343978485331071L;
	private AccessoryQuotedElseDao accessoryQuotedElseDao;
	public static AccessoryQuotedElseService getInstance(){
		return (AccessoryQuotedElseService)ConfigFactory.getInstance().getBean("accessoryQuotedElseService");
	}
	//获得辅料报价-其他成本DAO实例
	public AccessoryQuotedElseDao getAccessoryQuotedElseDao() {
		return accessoryQuotedElseDao;
	}
	//设置辅料报价-其他成本DAO实例
	public void setAccessoryQuotedElseDao(AccessoryQuotedElseDao accessoryQuotedElseDao) {
		this.accessoryQuotedElseDao = accessoryQuotedElseDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedElse> listAccessoryQuotedElse(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedElseDao().listAccessoryQuotedElse(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedElse(AccessoryQuotedElse accessoryQuotedElse){
		this.getAccessoryQuotedElseDao().insertAccessoryQuotedElse(accessoryQuotedElse.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedElse(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedElseDao().deleteAccessoryQuotedElse(map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedElse(AccessoryQuotedElse accessoryQuotedElse){
		this.getAccessoryQuotedElseDao().updateAccessoryQuotedElse(accessoryQuotedElse.toMap());
	}
	//加载一个辅料报价-其他成本
	@Override
	public AccessoryQuotedElse loadAccessoryQuotedElse(String enquiryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		return this.getAccessoryQuotedElseDao().loadAccessoryQuotedElse(map);
	}
}