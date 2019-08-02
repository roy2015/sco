package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.SubscribeAccessoryDao;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
/**
 * 申购产品信息DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class SubscribeAccessoryDaoImpl extends DaoImpl implements SubscribeAccessoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2680358508368300962L;
	//查询
	@Override
	public List<SubscribeAccessory> listSubscribeAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SubscribeAccessoryDao.class, "searchSubscribeAccessory", map,pageInfo);
	}
	//添加
	@Override
	public void insertSubscribeAccessory(Map<String, Object> map){
		this.insert(SubscribeAccessoryDao.class, "saveSubscribeAccessory", map);
	}
	//删除
	@Override
	public void deleteSubscribeAccessory(Map<String, Object> map){
		this.delete(SubscribeAccessoryDao.class, "deleteSubscribeAccessory", map);
	}
	//修改
	@Override
	public void updateSubscribeAccessory(Map<String, Object> map){
		this.update(SubscribeAccessoryDao.class, "updateSubscribeAccessory", map);
	}
	//装载一个申购产品信息
	@Override
	public SubscribeAccessory loadSubscribeAccessory(Map<String, Object> map) {
		return (SubscribeAccessory)this.get(SubscribeAccessoryDao.class, "searchSubscribeAccessory", map);
	}
}