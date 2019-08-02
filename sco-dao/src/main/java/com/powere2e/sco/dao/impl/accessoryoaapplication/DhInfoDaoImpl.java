package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.DhInfoDao;
import com.powere2e.sco.model.accessoryoaapplication.DhInfo;
/**
 * 大货信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月29日
 */
public class DhInfoDaoImpl extends DaoImpl implements DhInfoDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2165419931073898547L;
	//查询
	@Override
	public List<DhInfo> listDhInfo(Map<String, Object> map,PageInfo pageInfo){
		return this.query(DhInfoDao.class, "searchDhInfo", map,pageInfo);
	}
	//添加
	@Override
	public void insertDhInfo(Map<String, Object> map){
		this.insert(DhInfoDao.class, "saveDhInfo", map);
	}
	//删除
	@Override
	public void deleteDhInfo(Map<String, Object> map){
		this.delete(DhInfoDao.class, "deleteDhInfo", map);
	}
	//修改
	@Override
	public void updateDhInfo(Map<String, Object> map){
		this.update(DhInfoDao.class, "updateDhInfo", map);
	}
	//装载一个大货信息
	@Override
	public DhInfo loadDhInfo(Map<String, Object> map) {
		return (DhInfo)this.get(DhInfoDao.class, "searchDhInfo", map);
	}
}