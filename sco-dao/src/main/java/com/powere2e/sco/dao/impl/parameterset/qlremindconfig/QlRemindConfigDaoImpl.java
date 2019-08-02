package com.powere2e.sco.dao.impl.parameterset.qlremindconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.parameterset.qlremindconfig.QlRemindConfigDao;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
/**
 * 签量提醒设置DAO接口的实现
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public class QlRemindConfigDaoImpl extends DaoImpl implements QlRemindConfigDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 297830519028876204L;
	//查询
	@Override
	public List<QlRemindConfig> listQlRemindConfig(Map<String, Object> map,PageInfo pageInfo){
		return this.query(QlRemindConfigDao.class, "searchQlRemindConfig", map,pageInfo);
	}
	//添加
	@Override
	public void insertQlRemindConfig(Map<String, Object> map){
		this.insert(QlRemindConfigDao.class, "saveQlRemindConfig", map);
	}
	//删除
	@Override
	public void deleteQlRemindConfig(Map<String, Object> map){
		this.delete(QlRemindConfigDao.class, "deleteQlRemindConfig", map);
	}
	//修改
	@Override
	public void updateQlRemindConfig(Map<String, Object> map){
		this.update(QlRemindConfigDao.class, "updateQlRemindConfig", map);
	}
	//装载一个签量提醒设置
	@Override
	public QlRemindConfig loadQlRemindConfig(Map<String, Object> map) {
		return (QlRemindConfig)this.get(QlRemindConfigDao.class, "searchQlRemindConfig", map);
	}
}