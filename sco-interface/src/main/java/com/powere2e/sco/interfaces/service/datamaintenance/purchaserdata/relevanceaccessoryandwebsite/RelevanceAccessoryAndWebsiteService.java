package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevanceaccessoryandwebsite;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevanceaccessoryandwebsite.RelevanceAccessoryAndWebsite;

/**
 * 关联辅料与公示网站 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月17日
 */
public interface RelevanceAccessoryAndWebsiteService extends Service {
	
	/**
	 * 查询关联的辅料与公示网站
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回列表
	 */
	public List<RelevanceAccessoryAndWebsite> listRelevanceAccessoryAndWebsite(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 关联商品原料与公示网站
	 * 
	 * @param relevanceAccessoryAndWebsite
	 *            商品原料与公示网站关联实例
	 * 
	 */
	public void insertRelevanceAccessoryAndWebsite(RelevanceAccessoryAndWebsite relevanceAccessoryAndWebsite);

	/**
	 * 取消关联商品原料与公示网站
	 * 
	 * @param map
	 *            必须参数id为要删除的id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteRelevanceAccessoryAndWebsite(String ids);

	/**
	 * 导出到Excel
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportDataToExcel(Map<String, Object> map, ServletOutputStream out);
	
}