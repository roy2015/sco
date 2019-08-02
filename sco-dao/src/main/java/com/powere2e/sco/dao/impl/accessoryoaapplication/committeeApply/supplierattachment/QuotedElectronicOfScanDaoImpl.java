package com.powere2e.sco.dao.impl.accessoryoaapplication.committeeApply.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanDao;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;

/**
 * 关联辅料报价单-电子和扫描--辅料OADAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public class QuotedElectronicOfScanDaoImpl extends DaoImpl implements QuotedElectronicOfScanDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 866061527066989902L;

	// 查询
	@Override
	public List<QuotedElectronicOfScan> listQuotedElectronicOfScan(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(QuotedElectronicOfScanDao.class, "searchQuotedElectronicOfScan", map, pageInfo);
	}

	// 查询附件个数
	@Override
	public List<String> searchCount(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(QuotedElectronicOfScanDao.class, "searchCount", map, pageInfo);
	}

	// 添加
	@Override
	public void insertQuotedElectronicOfScan(Map<String, Object> map) {
		this.insert(QuotedElectronicOfScanDao.class, "saveQuotedElectronicOfScan", map);
	}

	// 删除
	@Override
	public void deleteQuotedElectronicOfScan(Map<String, Object> map) {
		this.delete(QuotedElectronicOfScanDao.class, "deleteQuotedElectronicOfScan", map);
	}

	// 删除--根据oa申请单号
	@Override
	public void deleteQuotedElectronicOfScanByCode(Map<String, Object> map) {
		this.delete(QuotedElectronicOfScanDao.class, "deleteQuotedElectronicOfScanByCode", map);
	}

	// 修改
	@Override
	public void updateQuotedElectronicOfScan(Map<String, Object> map) {
		this.update(QuotedElectronicOfScanDao.class, "updateQuotedElectronicOfScan", map);
	}

	// 装载一个关联辅料报价单-电子和扫描--辅料OA
	@Override
	public QuotedElectronicOfScan loadQuotedElectronicOfScan(Map<String, Object> map) {
		return (QuotedElectronicOfScan) this.get(QuotedElectronicOfScanDao.class, "searchQuotedElectronicOfScan", map);
	}
}