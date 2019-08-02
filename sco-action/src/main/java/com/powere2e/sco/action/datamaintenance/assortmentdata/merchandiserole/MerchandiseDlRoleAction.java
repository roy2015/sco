package com.powere2e.sco.action.datamaintenance.assortmentdata.merchandiserole;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.server.sequence.SequenceFactory;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRoleService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole.MerchandiseDlRole;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 商品定性角色的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDlRoleAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3422310516380942729L;
	private MerchandiseDlRoleService merchandiseDlRoleService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseDlRoleService = (MerchandiseDlRoleService) ConfigFactory.getInstance().getBean("merchandiseDlRoleService");
	}

	/**
	 * 商品定量角色列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole")
	public void doListMerchandiseDlRole() throws Exception {
		Map<String, Object> map = getMerchandiseDlRole().toMap();
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<MerchandiseDlRole> list = merchandiseDlRoleService.listMerchandiseDlRole(map, null);
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个商品定量角色
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole")
	public void doLoadMerchandiseDlRole() throws Exception {
		MerchandiseDlRole merchandiseDlRole = merchandiseDlRoleService.loadMerchandiseDlRole(asString("roleCode"));
		this.forwardData(true, merchandiseDlRole, null);
	}

	/**
	 * 添加商品定量角色界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dl.insert")
	public void doShowInsertMerchandiseDlRoleForm() throws Exception {
		MerchandiseDlRole merchandiseDlRole = new MerchandiseDlRole();
		this.putObject("merchandiseDlRole", merchandiseDlRole);
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRole/merchandiseDlRoleForm.ftl");
	}

	/**
	 * 获取当前商品定量名称是否存在
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMerchandiseDlRoleByRoleName(String roleName) throws Exception {
		boolean erification = false;
		MerchandiseDlRole merchandiseDlRole = merchandiseDlRoleService.loadMerchandiseDlRoleByroleName(roleName);
		if (merchandiseDlRole == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加商品定量角色
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dl.insert")
	public void doInsertMerchandiseDlRole() throws Exception {
		MerchandiseDlRole merchandiseDlRole = getMerchandiseDlRole();
		boolean erification = doListMerchandiseDlRoleByRoleName(merchandiseDlRole.getRoleName());
		String msg = "该定量角色名称已存在，不可新增";
		if (erification == true) {
			msg = this.getText("public.success");
			merchandiseDlRole.setRoleCode(SequenceFactory.getInstance().nextID("merchandise_dl_role"));
			merchandiseDlRole.setCreated(new Date());
			User loginUser = PowerUtils.getCurrentUser();
			merchandiseDlRole.setCreateby(loginUser.getLoginName());
			merchandiseDlRoleService.insertMerchandiseDlRole(merchandiseDlRole);
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
			Integer count = merchandiseDlRoleService.serachMerchandise(role[i]);
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
	 * 删除商品定量角色
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.dl.delete")
	public void doDeleteMerchandiseDlRole() throws Exception {
		boolean erification = doListMerchandise(asString("roleCode"));
		String msg = "系统中目前有商品属于该定量角色，不可将该角色从下拉列表中删除";
		if (erification == true) {
			msg = this.getText("public.success");
			merchandiseDlRoleService.deleteMerchandiseDlRole(asString("roleCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseDlRole getMerchandiseDlRole() throws Exception {
		MerchandiseDlRole merchandiseDlRole = new MerchandiseDlRole();
		this.asBean(merchandiseDlRole);
		return merchandiseDlRole;
	}
}
