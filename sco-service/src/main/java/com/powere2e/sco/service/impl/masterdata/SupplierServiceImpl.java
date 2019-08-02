package com.powere2e.sco.service.impl.masterdata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.service.masterdata.SupplierService;
import com.powere2e.sco.interfaces.dao.masterdata.SupplierDao;
import com.powere2e.sco.model.masterdata.Supplier;
/**
 * 供应商业务类的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierServiceImpl extends ServiceImpl implements SupplierService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7454573202719460879L;
	private SupplierDao supplierDao;
	public static SupplierService getInstance(){
		return (SupplierService)ConfigFactory.getInstance().getBean("supplierManager");
	}
	//获得供应商DAO实例
	public SupplierDao getSupplierDao() {
		return supplierDao;
	}
	//设置供应商DAO实例
	public void setSupplierDao(SupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}
	//查询
	@Override
	public List<Supplier> listSupplier(Map<String, Object> map,PageInfo pageInfo){
		return this.getSupplierDao().listSupplier(map,pageInfo);
	}
	//添加
	@Override
	public void insertSupplier(Supplier supplier){
		this.getSupplierDao().insertSupplier(supplier.toMap());
	}
	//删除
	@Override
	public void deleteSupplier(String id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		this.getSupplierDao().deleteSupplier(map);
	}
	//修改
	@Override
	public void updateSupplier(Supplier supplier){
		this.getSupplierDao().updateSupplier(supplier.toMap());
	}
	//加载一个供应商
	@Override
	public Supplier loadSupplier(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return this.getSupplierDao().loadSupplier(map);
	}
	//加载一个意向供应商
		@Override
		public Supplier loadIntentionSupplier(String id) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			return this.getSupplierDao().loadIntentionSupplier(map);
		}
	@Override
	public List<String> listSupplierCode(Map<String, Object> map) {
		return this.getSupplierDao().listSupplierCode(map);
	}
}