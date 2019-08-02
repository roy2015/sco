package com.powere2e.sco.interfaces.service.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
/**
 * 询价单其他要求Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月30日
 */
public interface AccessoryEnquiryElseService extends Service {
	/**
	 * 询价单其他要求查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回询价单其他要求列表
	 */
	public List<AccessoryEnquiryElse> listAccessoryEnquiryElse(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个询价单其他要求
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryEnquiryElse loadAccessoryEnquiryElse(String elseCode);
	/**
	 * 添加询价单其他要求
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryEnquiryElse(AccessoryEnquiryElse accessoryEnquiryElse);
	/**
	 * 删除询价单其他要求
	 *
	 * @param map 
	 *				必须参数id为要删除的询价单其他要求id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryEnquiryElse(String enquiryCode);
	/**
	 * 修改询价单其他要求
	 *
	 * @param map 
	 *				必须参数id为要修改询价单其他要求的id号，不能为数组
	 */
	public void updateAccessoryEnquiryElse(AccessoryEnquiryElse accessoryEnquiryElse);
}