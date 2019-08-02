package com.powere2e.sco.dao.impl.masterdata;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.masterdata.SupplierDao;
import com.powere2e.sco.model.masterdata.Supplier;
/**
 * 供应商DAO接口的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierDaoImpl extends DaoImpl implements SupplierDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7450213946264086014L;
	//查询
	@Override
	public List<Supplier> listSupplier(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierDao.class, "searchSupplier", map,pageInfo);
	}
	//添加
	@Override
	public void insertSupplier(Map<String, Object> map){
		this.insert(SupplierDao.class, "saveSupplier", map);
	}
	//删除
	@Override
	public void deleteSupplier(Map<String, Object> map){
		this.delete(SupplierDao.class, "deleteSupplier", map);
	}
	//修改
	@Override
	public void updateSupplier(Map<String, Object> map){
		this.update(SupplierDao.class, "updateSupplier", map);
	}
	//装载一个供应商
	@Override
	public Supplier loadSupplier(Map<String, Object> map) {
		return (Supplier)this.get(SupplierDao.class, "loadSupplier", map);
	}
	//装载一个意向供应商
	@Override
	public Supplier loadIntentionSupplier(Map<String, Object> map) {
		return (Supplier)this.get(SupplierDao.class, "loadIntentionSupplier", map);
	}
	@Override
	public List<String> listSupplierCode(Map<String, Object> map) {
		return this.query(SupplierDao.class, "searchSupplierCode",map,null);
	}
}