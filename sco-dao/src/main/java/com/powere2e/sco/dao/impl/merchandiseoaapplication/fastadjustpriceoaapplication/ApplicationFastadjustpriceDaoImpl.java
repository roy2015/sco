package com.powere2e.sco.dao.impl.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;

/**
 * 申请报告(快速调价)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationFastadjustpriceDaoImpl extends DaoImpl implements ApplicationFastadjustpriceDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3002410345462321817L;

	// 查询没有报告的商品
	@Override
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseFastadjustprice(Map<String, Object> map)  throws Exception{
		return this.query(ApplicationFastadjustpriceDao.class, "queryNotHaveReportMerchandiseFastadjustprice", map, null);
	}

	// 查询
	@Override
	public List<ApplicationFastadjustprice> listApplicationFastadjustprice(Map<String, Object> map, PageInfo pageInfo)  throws Exception{
		return this.query(ApplicationFastadjustpriceDao.class, "searchApplicationFastadjustprice", map, pageInfo);
	}

	// 添加
	@Override
	public void insertApplicationFastadjustprice(Map<String, Object> map)  throws Exception{
		this.insert(ApplicationFastadjustpriceDao.class, "saveApplicationFastadjustprice", map);
	}

	// 根据申请单号和、申请文件名称、商品编号和供应商编号删除
	@Override
	public void deleteApplicationFastadjustprice(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationFastadjustpriceDao.class, "deleteApplicationFastadjustprice", map);
	}

	// 装载一个申请报告(快速调价)
	@Override
	public ApplicationFastadjustprice loadApplicationFastadjustprice(Map<String, Object> map)  throws Exception{
		return (ApplicationFastadjustprice) this.get(ApplicationFastadjustpriceDao.class, "searchApplicationFastadjustprice", map);
	}

	// 查询申请文件的列表
	@Override
	public List<ApplicationFastadjustprice> listApplicationFileMerchandise(Map<String, Object> map, PageInfo pageInfo)  throws Exception{
		return this.query(ApplicationFastadjustpriceDao.class, "listApplicationFileMerchandise", map, pageInfo);
	}

	// 查询申请文件
	@Override
	public List<ApplicationFastadjustprice> listApplicationFiles(Map<String, Object> map, PageInfo pageInfo)  throws Exception{
		return this.query(ApplicationFastadjustpriceDao.class, "listApplicationFiles", map, pageInfo);
	}

	// 根据申请单号删除申请报告(快速调价)
	@Override
	public void deleteFastadjustpriceByApplicationCode(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationFastadjustpriceDao.class, "deleteFastadjustpriceByApplicationCode", map);
	}

	//根据报告编号删除申请文件
	@Override
	public void deleteApplicationFiles(Map<String, Object> map)  throws Exception{
		this.delete(ApplicationFastadjustpriceDao.class, "deleteApplicationFiles", map);
	}

}