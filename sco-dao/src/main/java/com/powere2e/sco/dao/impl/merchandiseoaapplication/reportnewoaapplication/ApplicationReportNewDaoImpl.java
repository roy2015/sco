package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNewDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;

/**
 * 申请报告(新品引进)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportNewDaoImpl extends DaoImpl implements ApplicationReportNewDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 49127146832543263L;

	// 没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseNew(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportNewDao.class, "queryNotHaveReportMerchandiseNew", map, null);
	}

	// 查询
	@Override
	public List<ApplicationReportNew> listApplicationReportNew(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportNewDao.class, "listApplicationReportNew", map, null);
	}

	// 查询申请报告从意向品带入的信息(新品引进)
	@Override
	public List<ApplicationReportNew> queryReportOfNew(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportNewDao.class, "queryReportofNew", map, null);
	}

	// 装载一个申请报告(新品引进)
	@Override
	public ApplicationReportNew loadApplicationReportNew(Map<String, Object> map)  throws Exception{
		return (ApplicationReportNew) this.get(ApplicationReportNewDao.class, "loadApplicationReportNew", map);
	}

	// 添加
	@Override
	public void insertApplicationReportNew(Map<String, Object> map)  throws Exception{
		this.insert(ApplicationReportNewDao.class, "saveApplicationReportNew", map);
	}

	// 删除
	@Override
	public void deleteApplicationReportNew(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationReportNewDao.class, "deleteApplicationReportNew", map);
	}

	// 修改
	@Override
	public void updateApplicationReportNew(Map<String, Object> map)  throws Exception{
		this.update(ApplicationReportNewDao.class, "updateApplicationReportNew", map);
	}

	// 根据申请单号删除申请报告(新品引进)
	@Override
	public void deleteReportNewByApplicationCode(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationReportNewDao.class, "deleteReportNewByApplicationCode", map);
	}

	@Override
	public void deleteCheckStandardNewByApplicationCode(Map<String, Object> map) {
		this.delete(ApplicationReportNewDao.class, "deleteCheckStandardNewByApplicationCode", map);
	}

}