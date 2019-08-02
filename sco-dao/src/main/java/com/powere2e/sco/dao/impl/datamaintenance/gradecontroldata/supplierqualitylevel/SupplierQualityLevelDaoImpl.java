package com.powere2e.sco.dao.impl.datamaintenance.gradecontroldata.supplierqualitylevel;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevelDao;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevel;
/**
 * 供应商质量星级DAO接口的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierQualityLevelDaoImpl extends DaoImpl implements SupplierQualityLevelDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2123730048889585106L;
	//查询
	@Override
	public List<SupplierQualityLevel> listSupplierQualityLevel(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierQualityLevelDao.class, "searchSupplierQualityLevel", map,pageInfo);
	}
	//添加
	@Override
	public void insertSupplierQualityLevel(Map<String, Object> map){
		this.insert(SupplierQualityLevelDao.class, "saveSupplierQualityLevel", map);
	}
	//修改
	@Override
	public void updateSupplierQualityLevel(Map<String, Object> map){
		this.update(SupplierQualityLevelDao.class, "updateSupplierQualityLevel", map);
	}
}