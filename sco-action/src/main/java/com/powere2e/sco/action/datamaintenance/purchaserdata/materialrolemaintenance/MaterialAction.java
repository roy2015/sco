package com.powere2e.sco.action.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 原料的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8211469991115474800L;
	private MaterialService materialService;
	private User loginUser = PowerUtils.getCurrentUser();

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		materialService = (MaterialService) ConfigFactory.getInstance().getBean("materialService");
	}

	/**
	 * 商品细分类下拉维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doMaterialGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialGrid.ftl");
	}

	/**
	 * 原料列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doListMaterial() throws Exception {
		Map<String, Object> map = getMaterial().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<Material> list = materialService.listMaterial(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个原料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material")
	public void doLoadMaterial() throws Exception {
		Material material = this.materialService.loadMaterial(asString("materialCode"));
		this.forwardData(true, material, null);
	}

	/**
	 * 添加原料界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doShowInsertMaterialForm() throws Exception {
		Material material = new Material();
		this.putObject("material", material);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialForm.ftl");
	}

	/**
	 * 修改原料界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doShowUpdateMaterialForm() throws Exception {
		Material material = materialService.loadMaterial(asString("materialCode"));
		this.putObject("material", material);
		this.forwardPage("sco/dataMaintenance/purchaserData/materialRoleMaintenance/materialForm.ftl");
	}

	/**
	 * 添加原料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.insert")
	public void doInsertMaterial() throws Exception {
		Material material = getMaterial();
		boolean erification = false;
		String msg = "";
		String info = "";
		Map<String, Object>  materialSearch= new HashMap<String, Object>();
		materialSearch=material.toMap();
		materialSearch.put("priceRegion", "");
		List<Material> materialList = materialService.listMaterial(materialSearch, null);
		if (materialList != null && materialList.size() > 0) {// 判断原料是否存在
			materialSearch.put("priceRegionInsert", material.getMaterialRegionName());
			materialList = materialService.listMaterial(materialSearch, null);
			if (materialList == null || materialList.size() == 0) {// 在原料存在的情况下，地区存不存在
				// 新增原料对应地区
				info = "2";
			} else {
				msg = "该公示网站原料信息已存在,不可重复添加";
				// 不做任何操作
				info = "3";
			}
		} else {
			// 执行所有操作（添加原料，地区）
			info = "1";
		}
		if (info != "3") {
			material.setCreateby(loginUser.getLoginName());
			erification = true;
			// 创建地区Map
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("materialRegionName", material.getMaterialRegionName());
			map.put("websiteUrl", material.getWebsiteUrl());
			map.put("createby", loginUser.getLoginName());
			if (info == "1") {
				// 添加原料
				materialService.insertMaterial(material);
			}
			Material materialC = materialService.loadchMaterial(material);
			map.put("materialCode", materialC.getMaterialCode());
			materialService.insertMaterialRegion(map);
			msg = this.getText("public.success");
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改原料
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.edit")
	public void doUpdateMaterial() throws Exception {
		materialService.updateMaterial(getMaterial());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除原料
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.purchaserdata.material.delete")
	public void doDeleteMaterial() throws Exception {
		materialService.deleteMaterial(asString("materialCode"));
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Material getMaterial() throws Exception {
		Material material = new Material();
		this.asBean(material);
		return material;
	}
}
