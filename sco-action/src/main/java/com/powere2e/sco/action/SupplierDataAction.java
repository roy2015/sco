package com.powere2e.sco.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.SupplierDataService;
import com.powere2e.security.model.Option;

/**
 *  供应商action
 *  @author Joyce.li
 *  @since 2015年3月20日 上午10:31:56
 *  @version 1.0
 */
public class SupplierDataAction extends WorkAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5163620339872494109L;
	private SupplierDataService supplierDataService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierDataService=(SupplierDataService)ConfigFactory.getInstance().getBean("supplierDataService");
	}
	
	/**
	 * 商品意向品列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.merchandiseIntention")
	public void doListAllSupplier() throws Exception
	{ 
		Map<String,Object> map=new HashMap<String,Object>();
		String id=this.asString("id");
		String text=this.asString("text");
		map.put("supplierCode", id);
		map.put("supplierName", text);
		
		List<Option> list=supplierDataService.listAllSupplier(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 辅料供应商列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doListAllSupplierFl() throws Exception
	{
		Map<String,Object> map=new HashMap<String,Object>();
		String id=this.asString("id");
		String text=this.asString("text");
		map.put("supplierCode", id);
		map.put("supplierName", text);
			
		List<Option> list=supplierDataService.listAllSupplierFl(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 查询主数据(SAP)中的所有供应商
	 * Gavillen --2015/04/12
	 */
	@Authority(privilege="com.powere2e.sco")
	public void doListMasterSupplier() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("supplierCode", this.asString("id"));
		map.put("supplierName", this.asString("text"));
		List<Option> list = supplierDataService.listMasterSupplier(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
}
