package com.powere2e.sco.interfaces.service.parameterset.certificateoutofdateconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.parameterset.certificateoutofdateconfig.CertificateOutofdateConfig;
/**
 * 证件过期提醒设置Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月3日
 */
public interface CertificateOutofdateConfigService extends Service {
	/**
	 * 证件过期提醒设置查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回证件过期提醒设置列表
	 */
	public List<CertificateOutofdateConfig> listCertificateOutofdateConfig(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个证件过期提醒设置
	 *
	 * @param map
	 *				
	 * @return
	 */
	public CertificateOutofdateConfig loadCertificateOutofdateConfig(String configCode);
	/**
	 * 添加证件过期提醒设置
	 *
	 * @param map
	 *				
	 */
	public void insertCertificateOutofdateConfig(CertificateOutofdateConfig certificateOutofdateConfig);
	/**
	 * 删除证件过期提醒设置
	 *
	 * @param map 
	 *				必须参数id为要删除的证件过期提醒设置id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCertificateOutofdateConfig(String configCode);
	/**
	 * 修改证件过期提醒设置
	 *
	 * @param map 
	 *				必须参数id为要修改证件过期提醒设置的id号，不能为数组
	 */
	public void updateCertificateOutofdateConfig(CertificateOutofdateConfig certificateOutofdateConfig);
	/**
	 * 修改证件过期提醒设置
	 *
	 * @param map 
	 *				必须参数id为要修改证件过期提醒设置的id号，不能为数组
	 */
	public CertificateOutofdateConfig listCertificateOutofdateConfig(Integer outofdate);
}