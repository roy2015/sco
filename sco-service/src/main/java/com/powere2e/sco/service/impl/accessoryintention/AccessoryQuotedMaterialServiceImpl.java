package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedMaterialDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedMaterialService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedMaterial;
/**
 * 辅料报价-原材料信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedMaterialServiceImpl extends ServiceImpl implements AccessoryQuotedMaterialService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -259177864020260694L;
	private AccessoryQuotedMaterialDao accessoryQuotedMaterialDao;
	public static AccessoryQuotedMaterialService getInstance(){
		return (AccessoryQuotedMaterialService)ConfigFactory.getInstance().getBean("accessoryQuotedMaterialService");
	}
	//获得辅料报价-原材料信息DAO实例
	public AccessoryQuotedMaterialDao getAccessoryQuotedMaterialDao() {
		return accessoryQuotedMaterialDao;
	}
	//设置辅料报价-原材料信息DAO实例
	public void setAccessoryQuotedMaterialDao(AccessoryQuotedMaterialDao accessoryQuotedMaterialDao) {
		this.accessoryQuotedMaterialDao = accessoryQuotedMaterialDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedMaterial> listAccessoryQuotedMaterial(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedMaterialDao().listAccessoryQuotedMaterial(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedMaterial(AccessoryQuotedMaterial accessoryQuotedMaterial){
		this.getAccessoryQuotedMaterialDao().insertAccessoryQuotedMaterial(accessoryQuotedMaterial.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedMaterial(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedMaterialDao().deleteAccessoryQuotedMaterial(map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedMaterial(AccessoryQuotedMaterial accessoryQuotedMaterial){
		this.getAccessoryQuotedMaterialDao().updateAccessoryQuotedMaterial(accessoryQuotedMaterial.toMap());
	}
	//加载一个辅料报价-原材料信息
	@Override
	public AccessoryQuotedMaterial loadAccessoryQuotedMaterial(String materialCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialCode", materialCode);
		return this.getAccessoryQuotedMaterialDao().loadAccessoryQuotedMaterial(map);
	}
}