package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportoldupoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldupDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;

/**
 * 申请报告(老品新上)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportOldupDaoImpl extends DaoImpl implements ApplicationReportOldupDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6488093264285737409L;

	// 查询没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseOldup(Map<String, Object> map) throws Exception {
		return this.query(ApplicationReportOldupDao.class, "queryNotHaveReportMerchandiseOldup", map, null);
	}

	// 查询老品新上报告
	@Override
	public List<ApplicationReportOldup> listApplicationReportOldup(Map<String, Object> map) throws Exception {
		return this.query(ApplicationReportOldupDao.class, "listApplicationReportOldup", map, null);
	}

	// 查询报告从商品中带入的信息(老品新上)
	@Override
	public List<ApplicationReportOldup> queryReportOfOldup(Map<String, Object> map) throws Exception {
		return this.query(ApplicationReportOldupDao.class, "queryReportOfOldup", map, null);
	}

	// 添加
	@Override
	public void insertApplicationReportOldup(Map<String, Object> map) throws Exception {
		this.insert(ApplicationReportOldupDao.class, "saveApplicationReportOldup", map);
	}

	// 删除
	@Override
	public void deleteApplicationReportOldup(Map<String, Object> map) throws Exception {
		this.delete(ApplicationReportOldupDao.class, "deleteApplicationReportOldup", map);
	}

	// 修改
	@Override
	public void updateApplicationReportOldup(Map<String, Object> map) throws Exception {
		this.update(ApplicationReportOldupDao.class, "updateApplicationReportOldup", map);
	}

	// 装载一个申请报告(老品新上)
	@Override
	public ApplicationReportOldup loadApplicationReportOldup(Map<String, Object> map) throws Exception {
		return (ApplicationReportOldup) this.get(ApplicationReportOldupDao.class, "loadApplicationReportOldup", map);
	}

	// 根据申请单号删除申请报告(老品新上)
	@Override
	public void deleteReportOldupByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(ApplicationReportOldupDao.class, "deleteReportOldupByApplicationCode", map);
	}

}