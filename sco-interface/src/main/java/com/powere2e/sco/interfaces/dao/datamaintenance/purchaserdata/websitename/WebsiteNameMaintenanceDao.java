package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.websitename;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitename.Website;
import com.powere2e.security.model.Option;

/**
 * 公示网站名称维护DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface WebsiteNameMaintenanceDao extends Dao {
	/**
	 * 公示网站名称维护查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回公示网站名称维护列表
	 */
	public List<Website> listWebsite(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个公示网站名称维护
	 *
	 * @param map
	 *
	 * @return
	 */
	public Website loadWebsite(Map<String, Object> map);

	/**
	 * 添加公示网站名称维护
	 *
	 * @param map
	 *
	 */
	public void insertWebsite(Map<String, Object> map);

	/**
	 * 删除公示网站名称维护
	 *
	 * @param map
	 *            必须参数id为要删除的公示网站名称维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteWebsite(Map<String, Object> map);

	/**
	 * 查询原料表中是否存在网站名称
	 * 
	 * @param map
	 * @return
	 */
	public Integer searchMaterial(Map<String, Object> map);

	/**
	 * 网站名称查询
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listQualitative();

	/**
	 * 查询最后一次价格维护信息
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<Website> listMaxPriceUpdateDate(Map<String, Object> map, PageInfo pageInfo);
}