package com.powere2e.sco.interfaces.service.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.DhInfo;
/**
 * 大货信息Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月29日
 */
public interface DhInfoService extends Service {
	/**
	 * 大货信息查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回大货信息列表
	 */
	public List<DhInfo> listDhInfo(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个大货信息
	 *
	 * @param map
	 *				
	 * @return
	 */
	public DhInfo loadDhInfo(String intentionCode);
	/**
	 * 添加大货信息
	 *
	 * @param map
	 *				
	 */
	public void insertDhInfo(DhInfo dhInfo);
	/**
	 * 删除大货信息
	 *
	 * @param map 
	 *				必须参数id为要删除的大货信息id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteDhInfo(String id);
	/**
	 * 删除大货信息
	 *
	 * @param map 
	 *				必须参数id为要删除的大货信息id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteDhInfoFromCode(String applicationCode);
	/**
	 * 修改大货信息
	 *
	 * @param map 
	 *				必须参数id为要修改大货信息的id号，不能为数组
	 */
	public void updateDhInfo(DhInfo dhInfo);
}