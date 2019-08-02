package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;

/**
 * 申请报告(调价)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportAdjustpriceDaoImpl extends DaoImpl implements ApplicationReportAdjustpriceDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4347050816455491439L;

	//查询没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseAdjustprice(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportAdjustpriceDao.class, "queryNotHaveReportMerchandiseAdjustprice", map, null);
	}

	// 查询
	@Override
	public List<ApplicationReportAdjustprice> listApplicationReportAdjustprice(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportAdjustpriceDao.class, "listApplicationReportAdjustprice", map, null);
	}

	// 查询报告从商品中带入的信息(正常调价)
	@Override
	public List<ApplicationReportAdjustprice> queryReportOfAdjustprice(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationReportAdjustpriceDao.class, "queryReportOfAdjustprice", map, null);
	}

	// 装载一个申请报告(调价)
	@Override
	public ApplicationReportAdjustprice loadApplicationReportAdjustprice(Map<String, Object> map)  throws Exception{
		return (ApplicationReportAdjustprice) this.get(ApplicationReportAdjustpriceDao.class, "loadApplicationReportAdjustprice", map);
	}

	// 添加
	@Override
	public void insertApplicationReportAdjustprice(Map<String, Object> map)  throws Exception{
		this.insert(ApplicationReportAdjustpriceDao.class, "saveApplicationReportAdjustprice", map);
	}

	// 删除
	@Override
	public void deleteApplicationReportAdjustprice(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationReportAdjustpriceDao.class, "deleteApplicationReportAdjustprice", map);
	}

	// 修改
	@Override
	public void updateApplicationReportAdjustprice(Map<String, Object> map)  throws Exception{
		this.update(ApplicationReportAdjustpriceDao.class, "updateApplicationReportAdjustprice", map);
	}

	@Override
	public void deleteReportAdjustpriceByApplicationCode(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationReportAdjustpriceDao.class, "deleteReportAdjustpriceByApplicationCode", map);
	}
}