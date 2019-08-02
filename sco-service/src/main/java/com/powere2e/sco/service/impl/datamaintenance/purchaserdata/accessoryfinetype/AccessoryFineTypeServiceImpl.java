package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.accessoryfinetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineTypeDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineType;

/**
 * 辅助细分类维护业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class AccessoryFineTypeServiceImpl extends ServiceImpl implements AccessoryFineTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5073621724105901977L;
	private AccessoryFineTypeDao accessoryFineTypeDao;

	public static AccessoryFineTypeService getInstance() {
		return (AccessoryFineTypeService) ConfigFactory.getInstance().getBean("accessoryFineTypeService");
	}

	// 获得辅助细分类维护DAO实例
	public AccessoryFineTypeDao getAccessoryFineTypeDao() {
		return accessoryFineTypeDao;
	}

	// 设置辅助细分类维护DAO实例
	public void setAccessoryFineTypeDao(AccessoryFineTypeDao accessoryFineTypeDao) {
		this.accessoryFineTypeDao = accessoryFineTypeDao;
	}

	// 查询
	@Override
	public List<AccessoryFineType> listAccessoryFineType(Map<String, Object> map, PageInfo pageInfo) {
		return this.getAccessoryFineTypeDao().listAccessoryFineType(map, pageInfo);
	}

	// 添加
	@Override
	public void insertAccessoryFineType(AccessoryFineType accessoryFineType) {
		this.getAccessoryFineTypeDao().insertAccessoryFineType(accessoryFineType.toMap());
	}

	// 删除
	@Override
	public void deleteAccessoryFineType(String fineTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeCode", fineTypeCode.split(","));
		this.getAccessoryFineTypeDao().deleteAccessoryFineType(map);
	}

	// 加载一个辅助细分类维护
	@Override
	public AccessoryFineType loadAccessoryFineType(String fineTypeName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeName", fineTypeName);
		return this.getAccessoryFineTypeDao().loadAccessoryFineType(map);
	}

	// 获取辅料中存在该细分类的数量
	@Override
	public Integer serachAccessoryFineType(String fineTypeCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeCode", fineTypeCode);
		return this.accessoryFineTypeDao.searchAccessoryFineType(map);
	}
}