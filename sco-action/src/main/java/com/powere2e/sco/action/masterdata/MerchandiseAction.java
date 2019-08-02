package com.powere2e.sco.action.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseService;
import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 主商品数据WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月22日
 * @version 1.0
 */
public class MerchandiseAction extends UploadUtils {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6202538109598978227L;
	private MerchandiseService mdService;
	/**
	 * 设置需要的类
	 */
	@Override
	protected void beforeBuild() {
		mdService = (MerchandiseService) ConfigFactory.getInstance().getBean(
				"merchandiseService");
	}
	/**
	 * 角色维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandise")
	public void doMerchandiseGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRoleMaintenance/merchandiseRoleGrid.ftl");
	}
	/**
	 * 商品数据查询
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandise")
	public void doListMerchandise() throws Exception {
		Map<String, Object> map=this.getMerchanise().toMap();
		List<Merchandise> list = this.mdService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 根据ID号获一个商品角色
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datemaintenance.assortmentdata.merchandise")
	public void doLoadMerchandise() throws Exception{
		Merchandise merchandise=this.mdService.loadMerchandise(asString("roleCode"));
		this.forwardData(true,merchandise, null);
	}
	
	/**
	 * 商品数据查询(供其他模块调用)
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMerchandiseByParam() throws Exception {
		Map<String, Object> map=this.getMerchanise().toMap();
		List<Merchandise> list = this.mdService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Merchandise getMerchanise() throws Exception {
		Merchandise merchandise = new Merchandise();
		this.asBean(merchandise);
		return merchandise;
	}
	
}
