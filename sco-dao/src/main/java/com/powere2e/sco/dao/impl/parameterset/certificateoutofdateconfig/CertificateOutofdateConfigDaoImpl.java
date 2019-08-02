package com.powere2e.sco.dao.impl.parameterset.certificateoutofdateconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.parameterset.certificateoutofdateconfig.CertificateOutofdateConfigDao;
import com.powere2e.sco.model.parameterset.certificateoutofdateconfig.CertificateOutofdateConfig;
/**
 * 证件过期提醒设置DAO接口的实现
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月3日
 */
public class CertificateOutofdateConfigDaoImpl extends DaoImpl implements CertificateOutofdateConfigDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2480737623524850426L;
	//查询
	@Override
	public List<CertificateOutofdateConfig> listCertificateOutofdateConfig(Map<String, Object> map,PageInfo pageInfo){
		return this.query(CertificateOutofdateConfigDao.class, "searchCertificateOutofdateConfig", map,pageInfo);
	}
	//添加
	@Override
	public void insertCertificateOutofdateConfig(Map<String, Object> map){
		this.insert(CertificateOutofdateConfigDao.class, "saveCertificateOutofdateConfig", map);
	}
	//删除
	@Override
	public void deleteCertificateOutofdateConfig(Map<String, Object> map){
		this.delete(CertificateOutofdateConfigDao.class, "deleteCertificateOutofdateConfig", map);
	}
	//修改
	@Override
	public void updateCertificateOutofdateConfig(Map<String, Object> map){
		this.update(CertificateOutofdateConfigDao.class, "updateCertificateOutofdateConfig", map);
	}
	//装载一个证件过期提醒设置
	@Override
	public CertificateOutofdateConfig loadCertificateOutofdateConfig(Map<String, Object> map) {
		return (CertificateOutofdateConfig)this.get(CertificateOutofdateConfigDao.class, "loadCertificateOutofdateConfig", map);
	}
	//根据提醒日期查询一个证件过期提醒设置
	@Override
	public CertificateOutofdateConfig listCertificateOutofdateConfig(Map<String, Object> map) {
		return (CertificateOutofdateConfig)this.get(CertificateOutofdateConfigDao.class, "searchCertificateOutofdateConfigByOutofdate", map);
	}
}