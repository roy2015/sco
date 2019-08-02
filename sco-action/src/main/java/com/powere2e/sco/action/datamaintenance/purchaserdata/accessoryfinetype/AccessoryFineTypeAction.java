package com.powere2e.sco.action.datamaintenance.purchaserdata.accessoryfinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype.AccessoryFineType;

/**
 * 辅料细分类维护的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class AccessoryFineTypeAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2084252955481149887L;
	private AccessoryFineTypeService accessoryFineTypeService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		accessoryFineTypeService = (AccessoryFineTypeService) ConfigFactory.getInstance().getBean("accessoryFineTypeService");
	}

	/**
	 * 辅料细分类下拉维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType")
	public void doMerchandiseFineTypeGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/purchaserData/accessoryFineType/accessoryFineTypeGrid.ftl");
	}

	/**
	 * 辅料细分类维护列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType")
	public void doListAccessoryFineType() throws Exception {
		Map<String, Object> map = getAccessoryFineType().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<AccessoryFineType> list = accessoryFineTypeService.listAccessoryFineType(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个辅料细分类维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType")
	public void doLoadAccessoryFineType() throws Exception {
		AccessoryFineType accessoryFineType = this.accessoryFineTypeService.loadAccessoryFineType(asString("fineTypeCode"));
		this.forwardData(true, accessoryFineType, null);
	}

	/**
	 * 添加辅料细分类维护界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType.insert")
	public void doShowInsertAccessoryFineTypeForm() throws Exception {
		AccessoryFineType accessoryFineType = new AccessoryFineType();
		this.putObject("accessoryFineType", accessoryFineType);
		this.forwardPage("sco/dataMaintenance/purchaserData/accessoryFineType/accessoryFineTypeForm.ftl");
	}
	/**
	 * 查询商品细分类维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListAccessoryFineTypeByInfo(String finetypeName) throws Exception {
		boolean erification = false;
		AccessoryFineType accessoryFineType = this.accessoryFineTypeService.loadAccessoryFineType(finetypeName);
		if (accessoryFineType == null) {
			erification = true;
		}
		return erification;
	}
	/**
	 * 添加辅料细分类维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType.insert")
	public void doInsertAccessoryFineType() throws Exception {
		AccessoryFineType accessoryFineType = getAccessoryFineType();
		boolean erification = this.doListAccessoryFineTypeByInfo(accessoryFineType.getFineTypeName());
		String msg = "该辅料细分类名称已存在，不可新增";
		if (erification == true) {
			msg=this.getText("public.success");
			accessoryFineTypeService.insertAccessoryFineType(accessoryFineType);
		}
		this.forwardData(erification, null, msg);
	}
	/**
	 * 获取当前细分类ID是否存在于辅料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMerchandiseFineType(String finetypeCode) throws Exception {
		boolean erification = false;
		String[] role = finetypeCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = accessoryFineTypeService.serachAccessoryFineType(role[i]);
			if (count <= 0) {
				erification = true;
			} else {
				erification = false;
				break;
			}
		}
		return erification;
	}
	/**
	 * 删除辅料细分类维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.accessoryFineType.delete")
	public void doDeleteAccessoryFineType() throws Exception {
		boolean erification = doListMerchandiseFineType(asString("fineTypeCode"));
		String msg = "系统中目前有辅料意向品属于该细分类，不可将该细分类从下拉列表中删除。";
		if (erification == true) {
			msg=this.getText("public.success");
			accessoryFineTypeService.deleteAccessoryFineType(asString("fineTypeCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private AccessoryFineType getAccessoryFineType() throws Exception {
		AccessoryFineType accessoryFineType = new AccessoryFineType();
		this.asBean(accessoryFineType);
		return accessoryFineType;
	}
}
