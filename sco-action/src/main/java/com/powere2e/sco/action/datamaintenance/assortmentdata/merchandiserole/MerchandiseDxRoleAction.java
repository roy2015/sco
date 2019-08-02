package com.powere2e.sco.action.datamaintenance.assortmentdata.merchandiserole;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRoleService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDxRole;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 商品定性角色的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDxRoleAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7411998398033944328L;
	private MerchandiseDxRoleService merchandiseDxRoleService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseDxRoleService = (MerchandiseDxRoleService) ConfigFactory.getInstance().getBean("merchandiseDxRoleService");
	}

	/**
	 * 角色下拉列表维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole")
	public void doMerchandiseRoleGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRole/merchandiseRoleGrid.ftl");
	}

	/**
	 * 商品定性角色列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole")
	public void doListMerchandiseDxRole() throws Exception {
		Map<String, Object> map = getMerchandiseDxRole().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<MerchandiseDxRole> list = merchandiseDxRoleService.listMerchandiseDxRole(map, null);
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个商品定性角色
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole")
	public void doLoadMerchandiseDxRole() throws Exception {
		MerchandiseDxRole merchandiseDxRole = this.merchandiseDxRoleService.loadMerchandiseDxRole(asString("roleCode"));
		this.forwardData(true, merchandiseDxRole, null);
	}

	/**
	 * 获取当前商品定性名称是否存在
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMerchandiseDxRoleByRoleName(String roleName) throws Exception {
		boolean erification = false;
		MerchandiseDxRole merchandiseDxRole = merchandiseDxRoleService.loadMerchandiseDxRoleByroleName(roleName);
		if (merchandiseDxRole == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加商品定性角色界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dx.insert")
	public void doShowInsertMerchandiseDxRoleForm() throws Exception {
		MerchandiseDxRole merchandiseDxRole = new MerchandiseDxRole();
		this.putObject("merchandiseDxRole", merchandiseDxRole);
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRole/merchandiseDxRoleForm");
	}

	/**
	 * 添加商品定性角色
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dx.insert")
	public void doInsertMerchandiseDxRole() throws Exception {
		MerchandiseDxRole merchandiseDxRole = getMerchandiseDxRole();
		boolean erification = doListMerchandiseDxRoleByRoleName(merchandiseDxRole.getRoleName());
		String msg = "该定性角色名称已存在，不可新增";
		if (erification == true) {
			msg = this.getText("public.success");
			merchandiseDxRole.setRoleCode(SequenceFactory.getInstance().nextID("merchandise_dx_role"));
			merchandiseDxRole.setCreated(new Date());
			User loginUser = PowerUtils.getCurrentUser();
			merchandiseDxRole.setCreateby(loginUser.getLoginName());
			merchandiseDxRoleService.insertMerchandiseDxRole(merchandiseDxRole);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获取当前商品定性名称是否存在于商品
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMerchandise(String roleCode) throws Exception {
		boolean erification = false;
		String[] role = roleCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = merchandiseDxRoleService.serachMerchandise(role[i]);
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
	 * 删除商品定性角色
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dx.delete")
	public void doDeleteMerchandiseDxRole() throws Exception {
		boolean erification = doListMerchandise(asString("roleCode"));
		String msg = "系统中目前有商品属于该定性角色，不可将该角色从下拉列表中删除";
		if (erification == true) {
			msg = this.getText("public.success");
			merchandiseDxRoleService.deleteMerchandiseDxRole(asString("roleCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseDxRole getMerchandiseDxRole() throws Exception {
		MerchandiseDxRole merchandiseDxRole = new MerchandiseDxRole();
		this.asBean(merchandiseDxRole);
		return merchandiseDxRole;
	}
}
