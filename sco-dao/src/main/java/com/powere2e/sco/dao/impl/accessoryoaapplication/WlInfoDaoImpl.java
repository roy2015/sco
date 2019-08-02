package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.WlInfoDao;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
/**
 * 物料信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年5月13日
 */
public class WlInfoDaoImpl extends DaoImpl implements WlInfoDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -213921667490621081L;
	//查询
	@Override
	public List<WlInfo> listWlInfo(Map<String, Object> map,PageInfo pageInfo){
		return this.query(WlInfoDao.class, "searchWlInfo", map,pageInfo);
	}
	//添加
	@Override
	public void insertWlInfo(Map<String, Object> map){
		this.insert(WlInfoDao.class, "saveWlInfo", map);
	}
	//删除
	@Override
	public void deleteWlInfo(Map<String, Object> map){
		this.delete(WlInfoDao.class, "deleteWlInfo", map);
	}
	//修改
	@Override
	public void updateWlInfo(Map<String, Object> map){
		this.update(WlInfoDao.class, "updateWlInfo", map);
	}
	//装载一个物料信息
	@Override
	public WlInfo loadWlInfo(Map<String, Object> map) {
		return (WlInfo)this.get(WlInfoDao.class, "searchWlInfo", map);
	}
}