package com.powere2e.sco.interfaces.service.peripheral.webservice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesign;
/**
 * 商品包装设计申请单Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public interface ApplicationPackageDesignService extends Service {
	/**
	 * 商品包装设计申请单查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品包装设计申请单列表
	 */
	public List<ApplicationPackageDesign> listApplicationPackageDesign(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个商品包装设计申请单
	 *
	 * @param map
	 *				
	 * @return
	 */
	public ApplicationPackageDesign loadApplicationPackageDesign(String applicationPdCode);
	/**
	 * 添加商品包装设计申请单
	 *
	 * @param map
	 *				
	 */
	public void insertApplicationPackageDesign(ApplicationPackageDesign applicationPackageDesign);
	/**
	 * 删除商品包装设计申请单
	 *
	 * @param map 
	 *				必须参数id为要删除的商品包装设计申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationPackageDesign(String applicationPdCode);
	/**
	 * 修改商品包装设计申请单
	 *
	 * @param map 
	 *				必须参数id为要修改商品包装设计申请单的id号，不能为数组
	 */
	public void updateApplicationPackageDesign(ApplicationPackageDesign applicationPackageDesign);
}