package com.powere2e.sco.service.impl.accessoryintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedTotalDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedTotalService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTotal;
/**
 * 辅料报价-总报价业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTotalServiceImpl extends ServiceImpl implements AccessoryQuotedTotalService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7474428679867341683L;
	private AccessoryQuotedTotalDao accessoryQuotedTotalDao;
	public static AccessoryQuotedTotalService getInstance(){
		return (AccessoryQuotedTotalService)ConfigFactory.getInstance().getBean("accessoryQuotedTotalService");
	}
	//获得辅料报价-总报价DAO实例
	public AccessoryQuotedTotalDao getAccessoryQuotedTotalDao() {
		return accessoryQuotedTotalDao;
	}
	//设置辅料报价-总报价DAO实例
	public void setAccessoryQuotedTotalDao(AccessoryQuotedTotalDao accessoryQuotedTotalDao) {
		this.accessoryQuotedTotalDao = accessoryQuotedTotalDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedTotal> listAccessoryQuotedTotal(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedTotalDao().listAccessoryQuotedTotal(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedTotal(AccessoryQuotedTotal accessoryQuotedTotal){
		this.getAccessoryQuotedTotalDao().insertAccessoryQuotedTotal(accessoryQuotedTotal.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedTotal(String enquiryCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryQuotedTotalDao().deleteAccessoryQuotedTotal(map);
	}
	//删除
		@Override
		public void deleteAccessoryQuotedTotalFromQuotedCode(String quotedCode){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("quotedCode", quotedCode);
			this.getAccessoryQuotedTotalDao().deleteAccessoryQuotedTotalFromQuotedCode(map);
		}
	//修改
	@Override
	public void updateAccessoryQuotedTotal(AccessoryQuotedTotal accessoryQuotedTotal){
		this.getAccessoryQuotedTotalDao().updateAccessoryQuotedTotal(accessoryQuotedTotal.toMap());
	}
	//加载一个辅料报价-总报价
	@Override
	public AccessoryQuotedTotal loadAccessoryQuotedTotal(String enquiryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		return this.getAccessoryQuotedTotalDao().loadAccessoryQuotedTotal(map);
	}
}