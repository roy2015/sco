package com.powere2e.sco.action.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigType;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 原料大类的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialBigTypeAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8649909491639623073L;
	private MaterialBigTypeService materialBigTypeService;
	private User loginUser = PowerUtils.getCurrentUser();
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		materialBigTypeService = (MaterialBigTypeService) ConfigFactory.getInstance().getBean("materialBigTypeService");
	}

	/**
	 * 原料大类列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doListMaterialBigType() throws Exception {
		Map<String, Object> map = getMaterialBigType().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<MaterialBigType> list = materialBigTypeService.listMaterialBigType(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个原料大类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doLoadMaterialBigType() throws Exception {
		MaterialBigType materialBigType = this.materialBigTypeService.loadMaterialBigType(asString("materialBigTypeCode"));
		this.forwardData(true, materialBigType, null);
	}

	/**
	 * 添加原料大类界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doShowInsertMaterialBigTypeForm() throws Exception {
		MaterialBigType materialBigType = new MaterialBigType();
		this.putObject("materialBigType", materialBigType);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialBigTypeAddForm.ftl");
	}

	/**
	 * 修改原料大类界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doShowUpdateMaterialBigTypeForm() throws Exception {
		MaterialBigType materialBigType = materialBigTypeService.loadMaterialBigTypeByCode(asString("materialBigTypeCode"));
		this.putObject("materialBigType", materialBigType);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialBigTypeEditForm.ftl");
	}

	/**
	 * 查询原料大类
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListMaterialBigTypeByInfo(String materialBigTypeName) throws Exception {
		boolean erification = false;
		MaterialBigType materialBigType = materialBigTypeService.loadMaterialBigType(materialBigTypeName);
		if (materialBigType == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加原料大类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doInsertMaterialBigType() throws Exception {
		MaterialBigType materialBigType = getMaterialBigType();
		boolean erification = this.doListMaterialBigTypeByInfo(materialBigType.getMaterialBigTypeName());
		String msg = "该大类名称已存在，不可重复添加";
		if (erification == true) {
			materialBigType.setCreateby(loginUser.getLoginName());
			msg = this.getText("public.success");
			materialBigTypeService.insertMaterialBigType(materialBigType);
		}
		this.forwardData(erification, null, msg);
	}
	
	/**
	 * 修改原料大类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doUpdateMaterialBigType() throws Exception {
		MaterialBigType materialBigType = getMaterialBigType();
		boolean erification = this.doListMaterialBigTypeByInfo(materialBigType.getMaterialBigTypeName());
		String msg = "新名称与现有大类名称重复，不可使用此名称";
		if (erification == true) {
			msg = this.getText("public.success");
			materialBigType.setUpdateby(loginUser.getLoginName());
			materialBigTypeService.updateMaterialBigType(materialBigType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获取当前原料大类ID是否存在于原料小类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMaterialSmallType(String materialBigTypeCode) throws Exception {
		boolean erification = false;
		String[] role = materialBigTypeCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = materialBigTypeService.serachMaterialSmallType(role[i]);
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
	 * 删除原料大类
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.delete")
	public void doDeleteMaterialBigType() throws Exception {
		boolean erification = doListMaterialSmallType(asString("materialBigTypeCode"));
		String msg = "该原料大类下包含原料小类，不可删除";
		if (erification == true) {
			msg = this.getText("public.success");
			materialBigTypeService.deleteMaterialBigType(asString("materialBigTypeCode"));
		}
		this.forwardData(erification, null,msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MaterialBigType getMaterialBigType() throws Exception {
		MaterialBigType materialBigType = new MaterialBigType();
		this.asBean(materialBigType);
		return materialBigType;
	}
}
