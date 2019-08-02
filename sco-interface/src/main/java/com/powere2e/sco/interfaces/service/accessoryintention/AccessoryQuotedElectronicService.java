package com.powere2e.sco.interfaces.service.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
/**
 * 辅料报价单-电子版Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public interface AccessoryQuotedElectronicService extends Service {
	/**
	 * 辅料报价单-电子版查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料报价单-电子版列表
	 */
	public List<AccessoryQuotedElectronic> listAccessoryQuotedElectronic(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅料报价单-电子版
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryQuotedElectronic loadAccessoryQuotedElectronic(String quotedCode);
	/**
	 * 根据ID号加载一个辅料报价单-电子版
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryQuotedElectronic loadAccessoryQuotedElectronicFor(String quotedCode);
	
	/**
	 * 添加辅料报价单-电子版
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryQuotedElectronic(AccessoryQuotedElectronic accessoryQuotedElectronic);
	/**
	 * 删除辅料报价单-电子版
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料报价单-电子版id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryQuotedElectronic(String quotedCode);
	/**
	 * 修改辅料报价单-电子版
	 *
	 * @param map 
	 *				必须参数id为要修改辅料报价单-电子版的id号，不能为数组
	 */
	public void updateAccessoryQuotedElectronic(AccessoryQuotedElectronic accessoryQuotedElectronic);
}