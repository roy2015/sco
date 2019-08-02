package com.powere2e.sco.dao.impl.datamaintenance.gradecontroldata.suppliervisitfactory;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactoryDao;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;
/**
 * 供应商年度巡厂得分DAO接口的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class SupplierVisitFactoryDaoImpl extends DaoImpl implements SupplierVisitFactoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 665780635126496209L;
	//查询
	@Override
	public List<SupplierVisitFactory> listSupplierVisitFactory(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierVisitFactoryDao.class, "searchSupplierVisitFactory", map,pageInfo);
	}
	//添加
	@Override
	public void insertSupplierVisitFactory(Map<String, Object> map){
		this.insert(SupplierVisitFactoryDao.class, "saveSupplierVisitFactory", map);
	}
	//修改
	@Override
	public void updateSupplierVisitFactory(Map<String, Object> map){
		this.update(SupplierVisitFactoryDao.class, "updateSupplierVisitFactory", map);
	}
}