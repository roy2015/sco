package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedAccessoryDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedAccessoryService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedAccessory;
/**
 * 辅料报价-辅料信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedAccessoryServiceImpl extends ServiceImpl implements AccessoryQuotedAccessoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5236814010617803887L;
	private AccessoryQuotedAccessoryDao accessoryQuotedAccessoryDao;
	public static AccessoryQuotedAccessoryService getInstance(){
		return (AccessoryQuotedAccessoryService)ConfigFactory.getInstance().getBean("accessoryQuotedAccessoryService");
	}
	//获得辅料报价-辅料信息DAO实例
	public AccessoryQuotedAccessoryDao getAccessoryQuotedAccessoryDao() {
		return accessoryQuotedAccessoryDao;
	}
	//设置辅料报价-辅料信息DAO实例
	public void setAccessoryQuotedAccessoryDao(AccessoryQuotedAccessoryDao accessoryQuotedAccessoryDao) {
		this.accessoryQuotedAccessoryDao = accessoryQuotedAccessoryDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedAccessory> listAccessoryQuotedAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedAccessoryDao().listAccessoryQuotedAccessory(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedAccessory(AccessoryQuotedAccessory accessoryQuotedAccessory){
		this.getAccessoryQuotedAccessoryDao().insertAccessoryQuotedAccessory(accessoryQuotedAccessory.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedAccessory(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedAccessoryDao().deleteAccessoryQuotedAccessory(map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedAccessory(AccessoryQuotedAccessory accessoryQuotedAccessory){
		this.getAccessoryQuotedAccessoryDao().updateAccessoryQuotedAccessory(accessoryQuotedAccessory.toMap());
	}
	//加载一个辅料报价-辅料信息
	@Override
	public AccessoryQuotedAccessory loadAccessoryQuotedAccessory(String accessoryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accessoryCode", accessoryCode);
		return this.getAccessoryQuotedAccessoryDao().loadAccessoryQuotedAccessory(map);
	}
}