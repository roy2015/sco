package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.DhInfoDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.DhInfoService;
import com.powere2e.sco.model.accessoryoaapplication.DhInfo;
/**
 * 大货信息业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月29日
 */
public class DhInfoServiceImpl extends ServiceImpl implements DhInfoService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 782334218035882891L;
	private DhInfoDao dhInfoDao;
	public static DhInfoService getInstance(){
		return (DhInfoService)ConfigFactory.getInstance().getBean("dhInfoService");
	}
	//获得大货信息DAO实例
	public DhInfoDao getDhInfoDao() {
		return dhInfoDao;
	}
	//设置大货信息DAO实例
	public void setDhInfoDao(DhInfoDao dhInfoDao) {
		this.dhInfoDao = dhInfoDao;
	}
	//查询
	@Override
	public List<DhInfo> listDhInfo(Map<String, Object> map,PageInfo pageInfo){
		return this.getDhInfoDao().listDhInfo(map,pageInfo);
	}
	//添加
	@Override
	public void insertDhInfo(DhInfo dhInfo){
		this.getDhInfoDao().insertDhInfo(dhInfo.toMap());
	}
	//删除
	@Override
	public void deleteDhInfo(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		this.getDhInfoDao().deleteDhInfo(map);
	}
	//修改
	@Override
	public void updateDhInfo(DhInfo dhInfo){
		this.getDhInfoDao().updateDhInfo(dhInfo.toMap());
	}
	//加载一个大货信息
	@Override
	public DhInfo loadDhInfo(String intentionCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		return this.getDhInfoDao().loadDhInfo(map);
	}
	@Override
	public void deleteDhInfoFromCode(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getDhInfoDao().deleteDhInfo(map);
	}
}