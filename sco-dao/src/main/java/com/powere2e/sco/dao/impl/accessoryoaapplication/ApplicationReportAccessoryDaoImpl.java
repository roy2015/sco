package com.powere2e.sco.dao.impl.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.ApplicationReportAccessoryDao;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
/**
 * 申请报告(辅料OA)DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class ApplicationReportAccessoryDaoImpl extends DaoImpl implements ApplicationReportAccessoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -723191473838770070L;
	//查询
	@Override
	public List<ApplicationReportAccessory> listApplicationReportAccessory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ApplicationReportAccessoryDao.class, "searchApplicationReportAccessory", map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationReportAccessory(Map<String, Object> map){
		this.insert(ApplicationReportAccessoryDao.class, "saveApplicationReportAccessory", map);
	}
	//删除
	@Override
	public void deleteApplicationReportAccessory(Map<String, Object> map){
		this.delete(ApplicationReportAccessoryDao.class, "deleteApplicationReportAccessory", map);
	}
	//修改
	@Override
	public void updateApplicationReportAccessory(Map<String, Object> map){
		this.update(ApplicationReportAccessoryDao.class, "updateApplicationReportAccessory", map);
	}
	//装载一个申请报告(辅料OA)
	@Override
	public ApplicationReportAccessory loadApplicationReportAccessory(Map<String, Object> map) {
		return (ApplicationReportAccessory)this.get(ApplicationReportAccessoryDao.class, "searchApplicationReportAccessory", map);
	}
}