package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.relevancematerialandwebsite;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;

/**
 * 关联商品原料与公示网站 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月13日
 */
public interface RelevanceMaterialAndWebsiteService extends Service {
	
	/**
	 * 查询关联商品原料与公示网站
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回列表
	 */
	public List<RelevanceMaterialAndWebsite> listRelevanceMaterialAndWebsite(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 关联商品原料与公示网站
	 * 
	 * @param map
	 *            商品原料与公示网站关联实例
	 * 
	 */
	public void insertRelevanceMaterialAndWebsite(RelevanceMaterialAndWebsite relevanceMaterialAndWebsite);

	/**
	 * 取消关联商品原料与公示网站
	 * 
	 * @param map
	 *            必须参数id为要删除的id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteRelevanceMaterialAndWebsite(String ids);

	/**
	 * 导出到Excel
	 * 
	 * @param list
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportDataToExcel(List<RelevanceMaterialAndWebsite> list, ServletOutputStream out);

	/**
	 * 暂不关联公示网站
	 * 
	 * @param inCode
	 *            投料编号
	 * @param matCode
	 *            原料编号
	 */
	public void completeNotLinkMaterialAndWebsite(String inCode, String matCode);
	
}