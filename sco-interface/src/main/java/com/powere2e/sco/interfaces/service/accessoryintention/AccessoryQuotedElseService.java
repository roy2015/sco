package com.powere2e.sco.interfaces.service.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;
/**
 * 辅料报价-其他成本Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public interface AccessoryQuotedElseService extends Service {
	/**
	 * 辅料报价-其他成本查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料报价-其他成本列表
	 */
	public List<AccessoryQuotedElse> listAccessoryQuotedElse(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅料报价-其他成本
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryQuotedElse loadAccessoryQuotedElse(String enquiryCode);
	/**
	 * 添加辅料报价-其他成本
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryQuotedElse(AccessoryQuotedElse accessoryQuotedElse);
	/**
	 * 删除辅料报价-其他成本
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料报价-其他成本id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryQuotedElse(String quotedCode);
	/**
	 * 修改辅料报价-其他成本
	 *
	 * @param map 
	 *				必须参数id为要修改辅料报价-其他成本的id号，不能为数组
	 */
	public void updateAccessoryQuotedElse(AccessoryQuotedElse accessoryQuotedElse);
}