package com.powere2e.sco.dao.impl.datamaintenance.gradecontroldata.suppliercomplaints;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaintsDao;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaints;
/**
 * 供应商年度千万元客诉DAO接口的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierComplaintsDaoImpl extends DaoImpl implements SupplierComplaintsDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5449486746297515555L;
	//查询
	@Override
	public List<SupplierComplaints> listSupplierComplaints(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierComplaintsDao.class, "searchSupplierComplaints", map,pageInfo);
	}
	//添加
	@Override
	public void insertSupplierComplaints(Map<String, Object> map){
		this.insert(SupplierComplaintsDao.class, "saveSupplierComplaints", map);
	}
	//修改
	@Override
	public void updateSupplierComplaints(Map<String, Object> map){
		this.update(SupplierComplaintsDao.class, "updateSupplierComplaints", map);
	}
}