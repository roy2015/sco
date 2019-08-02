package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedPackingDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedPackingService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedPacking;
/**
 * 辅料报价-内外包装信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedPackingServiceImpl extends ServiceImpl implements AccessoryQuotedPackingService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8565733560443931374L;
	private AccessoryQuotedPackingDao accessoryQuotedPackingDao;
	public static AccessoryQuotedPackingService getInstance(){
		return (AccessoryQuotedPackingService)ConfigFactory.getInstance().getBean("accessoryQuotedPackingService");
	}
	//获得辅料报价-内外包装信息DAO实例
	public AccessoryQuotedPackingDao getAccessoryQuotedPackingDao() {
		return accessoryQuotedPackingDao;
	}
	//设置辅料报价-内外包装信息DAO实例
	public void setAccessoryQuotedPackingDao(AccessoryQuotedPackingDao accessoryQuotedPackingDao) {
		this.accessoryQuotedPackingDao = accessoryQuotedPackingDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedPacking> listAccessoryQuotedPacking(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedPackingDao().listAccessoryQuotedPacking(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedPacking(AccessoryQuotedPacking accessoryQuotedPacking){
		this.getAccessoryQuotedPackingDao().insertAccessoryQuotedPacking(accessoryQuotedPacking.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedPacking(String quotedCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedPackingDao().deleteAccessoryQuotedPacking(map);
	}
	//修改
	@Override
	public void updateAccessoryQuotedPacking(AccessoryQuotedPacking accessoryQuotedPacking){
		this.getAccessoryQuotedPackingDao().updateAccessoryQuotedPacking(accessoryQuotedPacking.toMap());
	}
	//加载一个辅料报价-内外包装信息
	@Override
	public AccessoryQuotedPacking loadAccessoryQuotedPacking(String packingCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("packingCode", packingCode);
		return this.getAccessoryQuotedPackingDao().loadAccessoryQuotedPacking(map);
	}
}