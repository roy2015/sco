package com.powere2e.sco.interfaces.service.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
/**
 * 申请报告(辅料OA)Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public interface ApplicationReportAccessoryService extends Service {
	/**
	 * 申请报告(辅料OA)查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回申请报告(辅料OA)列表
	 */
	public List<ApplicationReportAccessory> listApplicationReportAccessory(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个申请报告(辅料OA)
	 *
	 * @param map
	 *				
	 * @return
	 */
	public ApplicationReportAccessory loadApplicationReportAccessory(String applicationCode);
	/**
	 * 添加申请报告(辅料OA)
	 *
	 * @param map
	 *				
	 */
	public void insertApplicationReportAccessory  (ApplicationReportAccessory applicationReportAccessory) ;
	/**
	 * 删除申请报告(辅料OA)
	 *
	 * @param map 
	 *				必须参数id为要删除的申请报告(辅料OA)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationReportAccessory(String applicationCode);
	/**
	 * 修改申请报告(辅料OA)
	 *
	 * @param map 
	 *				必须参数id为要修改申请报告(辅料OA)的id号，不能为数组
	 */
	public void updateApplicationReportAccessory(ApplicationReportAccessory applicationReportAccessory);
}