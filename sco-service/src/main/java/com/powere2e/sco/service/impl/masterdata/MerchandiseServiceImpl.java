package com.powere2e.sco.service.impl.masterdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseDao;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseService;
import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品查询Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月22日
 * @version 1.0
 */
public class MerchandiseServiceImpl extends ServiceImpl implements
		MerchandiseService {
	
	private static final long serialVersionUID = -964184845108630490L;
	private MerchandiseDao merchandiseDao;

	// 获取Merchandise Dao实例
	public MerchandiseDao getMerchandiseDao() {
		return merchandiseDao;
	}

	// 设置Merchandise Dao实例
	public void setMerchandiseDao(MerchandiseDao merchandiseDao) {
		this.merchandiseDao = merchandiseDao;
	}

	/**
	 * 方便其他模块调用
	 * 
	 * @return Service实例
	 */
	public static MerchandiseService getInstance() {
		return (MerchandiseService) ConfigFactory.getInstance().getBean(
				"merchandiseService");
	}

	@Override
	public List<Merchandise> listMerchandise(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.merchandiseDao.listMerchandise(map, pageInfo);
	}

	@Override
	public List<String> listMerchandiseStorageForm() {
		return this.merchandiseDao.listMerchandiseStorageForm();
	}

	@Override
	public List<String> listMerchandiseCode(Map<String, Object> map) {
		return this.merchandiseDao.listMerchandiseCode(map);
	}

	// 加载一个商品角色
	@Override
	public Merchandise loadMerchandise(String roleCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleCode", roleCode);
		return this.merchandiseDao.loadMerchandise(map);
	}
	// 加载一个商品角色
		@Override
		public Merchandise loadMerchandise(String roleCode,String supplierCode) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleCode", roleCode);
			map.put("supplierCode", supplierCode);
			return this.merchandiseDao.loadMerchandise(map);
		}

}