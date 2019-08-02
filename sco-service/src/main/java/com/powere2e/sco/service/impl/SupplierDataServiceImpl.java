package com.powere2e.sco.service.impl;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.SupplierDataDao;
import com.powere2e.sco.interfaces.service.SupplierDataService;
import com.powere2e.security.model.Option;

/**
 * 查询供应商数据列表
 * 	@author Joyce.li
 *  @since 2015年3月20日 上午10:27:34
 *  @version 1.0
 */
public class SupplierDataServiceImpl extends ServiceImpl implements SupplierDataService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1898963804653668435L;
	private SupplierDataDao supplierDataDao;

	public static SupplierDataService getInstance() {
		return (SupplierDataService) ConfigFactory.getInstance().getBean("supplierDataService");
	}

	public SupplierDataDao getSupplierDataDao() {
		return supplierDataDao;
	}

	public void setSupplierDataDao(SupplierDataDao supplierDataDao) {
		this.supplierDataDao = supplierDataDao;
	}

	// 查询供应商
	@Override
	public List<Option> listAllSupplier(Map<String, Object> map,PageInfo pageInfo) {
		return supplierDataDao.listAllSupplier(map,pageInfo);
	}
	// 查询供应商
		@Override
		public List<Option> listAllSupplierFl(Map<String, Object> map,PageInfo pageInfo) {
			return supplierDataDao.listAllSupplierFl(map,pageInfo);
		}

	@Override
	public List<Option> listMasterSupplier(Map<String,Object> map, PageInfo pageInfo) {
		return supplierDataDao.listMasterSupplier(map, pageInfo);
	}

}