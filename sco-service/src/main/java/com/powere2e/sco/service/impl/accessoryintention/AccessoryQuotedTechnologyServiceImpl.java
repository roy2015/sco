package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedTechnologyDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedTechnologyService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTechnology;
/**
 * 辅料报价-工艺信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTechnologyServiceImpl extends ServiceImpl implements AccessoryQuotedTechnologyService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2534401834570660350L;
	private AccessoryQuotedTechnologyDao accessoryQuotedTechnologyDao;
	public static AccessoryQuotedTechnologyService getInstance(){
		return (AccessoryQuotedTechnologyService)ConfigFactory.getInstance().getBean("accessoryQuotedTechnologyService");
	}
	//获得辅料报价-工艺信息DAO实例
	public AccessoryQuotedTechnologyDao getAccessoryQuotedTechnologyDao() {
		return accessoryQuotedTechnologyDao;
	}
	//设置辅料报价-工艺信息DAO实例
	public void setAccessoryQuotedTechnologyDao(AccessoryQuotedTechnologyDao accessoryQuotedTechnologyDao) {
		this.accessoryQuotedTechnologyDao = accessoryQuotedTechnologyDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedTechnology> listAccessoryQuotedTechnology(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedTechnologyDao().listAccessoryQuotedTechnology(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedTechnology(AccessoryQuotedTechnology accessoryQuotedTechnology){
		this.getAccessoryQuotedTechnologyDao().insertAccessoryQuotedTechnology(accessoryQuotedTechnology.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedTechnology(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedTechnologyDao().deleteAccessoryQuotedTechnology(map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedTechnology(AccessoryQuotedTechnology accessoryQuotedTechnology){
		this.getAccessoryQuotedTechnologyDao().updateAccessoryQuotedTechnology(accessoryQuotedTechnology.toMap());
	}
	//加载一个辅料报价-工艺信息
	@Override
	public AccessoryQuotedTechnology loadAccessoryQuotedTechnology(String technologyCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("technologyCode", technologyCode);
		return this.getAccessoryQuotedTechnologyDao().loadAccessoryQuotedTechnology(map);
	}
}