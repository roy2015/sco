package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.accessoryfinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineType;
/**
 * 辅助细分类维护Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface AccessoryFineTypeService extends Service {
	/**
	 * 辅助细分类维护查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅助细分类维护列表
	 */
	public List<AccessoryFineType> listAccessoryFineType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅助细分类维护
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryFineType loadAccessoryFineType(String fineTypeCode);
	/**
	 * 添加辅助细分类维护
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryFineType(AccessoryFineType accessoryFineType);
	/**
	 * 删除辅助细分类维护
	 *
	 * @param map 
	 *				必须参数id为要删除的辅助细分类维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryFineType(String fineTypeCode);
	/**
	 * 查询该细分类是否存在于辅料
	 * @param roleCode
	 * @return
	 */
	public Integer serachAccessoryFineType(String fineTypeCode);
}