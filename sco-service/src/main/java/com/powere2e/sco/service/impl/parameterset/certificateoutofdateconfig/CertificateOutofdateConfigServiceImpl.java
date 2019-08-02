package com.powere2e.sco.service.impl.parameterset.certificateoutofdateconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.parameterset.certificateoutofdateconfig.CertificateOutofdateConfigDao;
import com.powere2e.sco.interfaces.service.parameterset.certificateoutofdateconfig.CertificateOutofdateConfigService;
import com.powere2e.sco.model.parameterset.certificateoutofdateconfig.CertificateOutofdateConfig;

/**
 * 证件过期提醒设置业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月3日
 */
public class CertificateOutofdateConfigServiceImpl extends ServiceImpl implements CertificateOutofdateConfigService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7703104745660987902L;
	private CertificateOutofdateConfigDao certificateOutofdateConfigDao;

	public static CertificateOutofdateConfigService getInstance() {
		return (CertificateOutofdateConfigService) ConfigFactory.getInstance().getBean("certificateOutofdateConfigService");
	}

	// 获得证件过期提醒设置DAO实例
	public CertificateOutofdateConfigDao getCertificateOutofdateConfigDao() {
		return certificateOutofdateConfigDao;
	}

	// 设置证件过期提醒设置DAO实例
	public void setCertificateOutofdateConfigDao(CertificateOutofdateConfigDao certificateOutofdateConfigDao) {
		this.certificateOutofdateConfigDao = certificateOutofdateConfigDao;
	}

	// 查询
	@Override
	public List<CertificateOutofdateConfig> listCertificateOutofdateConfig(Map<String, Object> map, PageInfo pageInfo) {
		return this.getCertificateOutofdateConfigDao().listCertificateOutofdateConfig(map, pageInfo);
	}

	// 添加
	@Override
	public void insertCertificateOutofdateConfig(CertificateOutofdateConfig certificateOutofdateConfig) {
		this.getCertificateOutofdateConfigDao().insertCertificateOutofdateConfig(certificateOutofdateConfig.toMap());
	}

	// 删除
	@Override
	public void deleteCertificateOutofdateConfig(String configCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode.split(","));
		this.getCertificateOutofdateConfigDao().deleteCertificateOutofdateConfig(map);
	}

	// 修改
	@Override
	public void updateCertificateOutofdateConfig(CertificateOutofdateConfig certificateOutofdateConfig) {
		this.getCertificateOutofdateConfigDao().updateCertificateOutofdateConfig(certificateOutofdateConfig.toMap());
	}

	// 加载一个证件过期提醒设置
	@Override
	public CertificateOutofdateConfig loadCertificateOutofdateConfig(String configCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode);
		return this.getCertificateOutofdateConfigDao().loadCertificateOutofdateConfig(map);
	}

	// 查询一个证件过期提醒设置
	@Override
	public CertificateOutofdateConfig listCertificateOutofdateConfig(Integer outofdate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("outofdate", outofdate);
		return this.getCertificateOutofdateConfigDao().listCertificateOutofdateConfig(map);
	}
}