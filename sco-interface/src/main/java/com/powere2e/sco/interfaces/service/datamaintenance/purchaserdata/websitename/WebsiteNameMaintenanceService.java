package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitename;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitename.Website;
import com.powere2e.security.model.Option;
/**
 * 公示网站名称维护Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface WebsiteNameMaintenanceService extends Service {
	/**
	 * 公示网站名称维护查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回公示网站名称维护列表
	 */
	public List<Website> listWebsite(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个公示网站名称维护
	 *
	 * @param map
	 *				
	 * @return
	 */
	public Website loadWebsite(String websiteCode);
	/**
	 * 添加公示网站名称维护
	 *
	 * @param map
	 *				
	 */
	public void insertWebsite(Website website);
	/**
	 * 删除公示网站名称维护
	 *
	 * @param map 
	 *				必须参数id为要删除的公示网站名称维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteWebsite(String websiteCode);
	/**
	 * 查询原料表中是否存在该网站名称
	 * @param websiteCode
	 * @return
	 */
	public Integer serachMaterial(String websiteCode);
	/**
	 * 获取下拉列表
	 * @return
	 */
	public List<Option> listWebsiteInfo();
}