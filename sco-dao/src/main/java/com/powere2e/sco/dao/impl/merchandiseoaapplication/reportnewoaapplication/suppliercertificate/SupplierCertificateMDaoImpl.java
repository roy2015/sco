package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateMDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;

/**
 * 供应商证件DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public class SupplierCertificateMDaoImpl extends DaoImpl implements SupplierCertificateMDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9208813525945202864L;

	// 查询
	@Override
	public List<SupplierCertificateM> listSupplierCertificateM(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierCertificateMDao.class, "searchSupplierCertificateM", map, pageInfo);
	}

	// 装载一个供应商证件
	@Override
	public SupplierCertificateM loadSupplierCertificateM(Map<String, Object> map) {
		return (SupplierCertificateM) this.get(SupplierCertificateMDao.class, "searchSupplierCertificateM", map);
	}
	//添加供应商证件
	@Override
	public void insertSupplierCertificateM(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.insert(SupplierCertificateMDao.class, "saveSupplierCertificateM", map);
	}
	//删除一个供应商证件
	@Override
	public void deleteSupplierCertificateMs(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.delete(SupplierCertificateMDao.class, "deleteSupplierCertificateMs", map);
	}
}