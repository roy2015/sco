package com.powere2e.sco.model.datamaintenance.assortmentdata.merchandiserole;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 商品定性角色实体类
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月10日
 */
public class MerchandiseDxRole extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -252837352284974868L;
	private String roleCode;//角色编号
	private String roleName;//角色名称
	private Date created;//创建日期
	private String createby;//创建人
	private Date updated;//更新日期
	private String updateby;//更新人

	
	// 获取
	public String getRoleCode() {
		return roleCode;
	}
	// 设置
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	
	// 获取
	public String getRoleName() {
		return roleName;
	}
	// 设置
	public void setRoleName(String roleName) {
		this.roleName = roleName.trim();
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}
	// 设置
	public void setCreated(Date created) {
		this.created = created;
	}
	
	// 获取
	public String getCreateby() {
		return createby;
	}
	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}
	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	// 获取
	public String getUpdateby() {
		return updateby;
	}
	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}