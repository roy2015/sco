package com.powere2e.sco.action.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallType;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 原料小类的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialSmallTypeAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3301672080420989044L;
	private MaterialSmallTypeService materialSmallTypeService;
	private User loginUser = PowerUtils.getCurrentUser();

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		materialSmallTypeService = (MaterialSmallTypeService) ConfigFactory.getInstance().getBean("materialSmallTypeService");
	}

	/**
	 * 原料小类列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doListMaterialSmallType() throws Exception {
		Map<String, Object> map = getMaterialSmallType().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<MaterialSmallType> list = materialSmallTypeService.listMaterialSmallType(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个原料小类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doLoadMaterialSmallType() throws Exception {
		MaterialSmallType materialSmallType = this.materialSmallTypeService.loadMaterialSmallType(asString("materialSmallTypeCode"),null);
		this.forwardData(true, materialSmallType, null);
	}

	/**
	 * 添加原料小类界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doShowInsertMaterialSmallTypeForm() throws Exception {
		MaterialSmallType materialSmallType = new MaterialSmallType();
		this.putObject("materialSmallType", materialSmallType);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialSmallTypeAddForm.ftl");
	}

	/**
	 * 修改原料小类名称界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doShowUpdateMaterialSmallTypeNameForm() throws Exception {
		MaterialSmallType materialSmallType = materialSmallTypeService.loadMaterialSmallTypeCode(asString("materialSmallTypeCode"));
		this.putObject("materialSmallType", materialSmallType);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialSmallTypeEditNameForm.ftl");
	}

	/**
	 * 修改原料小类所属界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doShowUpdateMaterialSmallTypeBigForm() throws Exception {
		MaterialSmallType materialSmallType = materialSmallTypeService.loadMaterialSmallTypeCode(asString("materialSmallTypeCode"));
		this.putObject("materialSmallType", materialSmallType);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialSmallTypeEditBigForm.ftl");
	}

	/**
	 * 查询原料小类
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListMaterialBigTypeByInfo(String materialSmallTypeName,String materialBigTypeCode) throws Exception {
		boolean erification = false;
		MaterialSmallType materialSmallType = materialSmallTypeService.loadMaterialSmallType(materialSmallTypeName,materialBigTypeCode);
		if (materialSmallType == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加原料小类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doInsertMaterialSmallType() throws Exception {
		MaterialSmallType materialSmallType = getMaterialSmallType();
		boolean erification = this.doListMaterialBigTypeByInfo(materialSmallType.getMaterialSmallTypeName(),materialSmallType.getMaterialBigTypeCode());
		String msg = "该小类已存在，不可重复添加";
		if (erification == true) {
			msg = this.getText("public.success");
			materialSmallType.setCreateby(loginUser.getLoginName());
			materialSmallTypeService.insertMaterialSmallType(materialSmallType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改原料小类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doUpdateMaterialSmallTypeName() throws Exception {
		MaterialSmallType materialSmallType = getMaterialSmallType();
		boolean erification = this.doListMaterialBigTypeByInfo(materialSmallType.getMaterialSmallTypeName(),materialSmallType.getMaterialBigTypeCode());
		String msg = "新名称与现有原料小类名称重复，不可使用此名称";
		if (erification == true) {
			msg = this.getText("public.success");
			materialSmallTypeService.updateMaterialSmallType(materialSmallType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改原料小类
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doUpdateMaterialSmallTypeBig() throws Exception {
		MaterialSmallType materialSmallType = getMaterialSmallType();
		boolean erification = this.doListMaterialBigTypeByInfo(materialSmallType.getMaterialSmallTypeName(),materialSmallType.getMaterialBigTypeCode());
		String msg = "新所属大类已有该原料小类";
		if(erification==true){
			msg = this.getText("public.success");
			materialSmallTypeService.updateMaterialSmallType(materialSmallType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获取当前原料小类ID是否存在于原料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMaterial(String materialSmallTypeCode) throws Exception {
		boolean erification = false;
		String[] role = materialSmallTypeCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = materialSmallTypeService.serachMaterial(role[i]);
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
	 * 删除原料小类
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.delete")
	public void doDeleteMaterialSmallType() throws Exception {
		boolean erification = doListMaterial(asString("materialSmallTypeCode"));
		String msg = "该原料小类下包含公示网站原料，不可删除";
		if (erification == true) {
			msg = this.getText("public.success");
			materialSmallTypeService.deleteMaterialSmallType(asString("materialSmallTypeCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MaterialSmallType getMaterialSmallType() throws Exception {
		MaterialSmallType materialSmallType = new MaterialSmallType();
		this.asBean(materialSmallType);
		return materialSmallType;
	}
}
