package com.powere2e.sco.action.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.masterdata.SupplierService;
import com.powere2e.sco.model.masterdata.Supplier;

/**
 * 供应商的WEB请求响应类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierAction extends WorkAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6289350109281791055L;
	private SupplierService supplierService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierService=(SupplierService)ConfigFactory.getInstance().getBean("supplierManager");
	}
	/**
	 * 供应商列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints")
	public void doListSupplier() throws Exception
	{
		Map<String,Object> map=getSupplier().toMap();
			map.put("syncDate", this.asString("syncDate"));
		List<Supplier> list=supplierService.listSupplier(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 根据ID号获一个供应商
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints")
	public void doLoadSupplier() throws Exception{
		Supplier supplier=this.supplierService.loadSupplier(asString("id"));
		this.forwardData(true,supplier, null);
	}
	
	/**
	 * 添加供应商界面
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.insert")
	public void doShowInsertSupplierForm() throws Exception {
		Supplier supplier = new Supplier();
		this.putObject("supplier", supplier);
		this.forwardPage("supplier/supplierForm");
	}
	/**
	 * 修改供应商界面
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.edit")
	public void doShowUpdateSupplierForm() throws Exception {
		Supplier supplier = supplierService.loadSupplier(asString("id"));
		this.putObject("supplier", supplier);
		this.forwardPage("supplier/supplierForm");
	}
	
	
	/**
	 * 添加供应商
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.insert")
	public void doInsertSupplier() throws Exception {
		Supplier supplier=getSupplier();
		supplier.setSupplierCode(SequenceFactory.getInstance().nextID("supplierCode"));
		supplierService.insertSupplier(supplier);
		this.forwardData(true, null, this.getText("public.success"));
	}
	
	/**
	 * 修改供应商
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.edit")
	public void doUpdateSupplier() throws Exception {
		supplierService.updateSupplier(getSupplier());
		this.forwardData(true, null, this.getText("public.success"));
	}
	
	
	/**
	 * 删除供应商
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.delete")
	public void doDeleteSupplier() throws Exception{
		supplierService.deleteSupplier(asString("id"));
		this.forwardData(true, null,this.getText("public.success"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private Supplier getSupplier() throws Exception{
		Supplier supplier=new Supplier();
		this.asBean(supplier);
		return supplier;
	}
}
