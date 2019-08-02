package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.WlInfoDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.WlInfoService;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
/**
 * 物料信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年5月13日
 */
public class WlInfoServiceImpl extends ServiceImpl implements WlInfoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7746558613014655092L;
	private WlInfoDao wlInfoDao;
	public static WlInfoService getInstance(){
		return (WlInfoService)ConfigFactory.getInstance().getBean("wlInfoService");
	}
	//获得物料信息DAO实例
	public WlInfoDao getWlInfoDao() {
		return wlInfoDao;
	}
	//设置物料信息DAO实例
	public void setWlInfoDao(WlInfoDao wlInfoDao) {
		this.wlInfoDao = wlInfoDao;
	}
	//查询
	@Override
	public List<WlInfo> listWlInfo(Map<String, Object> map,PageInfo pageInfo){
		return this.getWlInfoDao().listWlInfo(map,pageInfo);
	}
	//添加
	@Override
	public void insertWlInfo(WlInfo wlInfo){
		this.getWlInfoDao().insertWlInfo(wlInfo.toMap());
	}
	//删除
	@Override
	public void deleteWlInfo(String applicationCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getWlInfoDao().deleteWlInfo(map);
	}
	//修改
	@Override
	public void updateWlInfo(WlInfo wlInfo){
		this.getWlInfoDao().updateWlInfo(wlInfo.toMap());
	}
	//加载一个物料信息
	@Override
	public WlInfo loadWlInfo(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getWlInfoDao().loadWlInfo(map);
	}
}