package com.powere2e.sco.model.accessoryoaapplication;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 申请报告(辅料OA)实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class ApplicationReportAccessory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 836680244410910416L;
	private String applicationCode;//
	private String cause;//
	private String gzMoney;//
	private String accumulativeYear;//
	private String ljMoney;//
	private String content;//
	private String departments;//
	private String responsiblePerson;//
	private Date subscribeBillDate;//
	private Date auditDate;//
	private Date requirementsDwDate;//
	private String activityDqDate;//
	private String emergency;//
	private String haveornotSubscribeBill;//
	private String isslItem;//
	private String dxhtgz;//
	private String cglj;//
	private String syfw;//
	private String opinion;//
	private String purchaseName;//
	private String purchaseCount;//
	private String purchasePrice;//
	private String purchaseDepartment;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private String projectManager;
	private String projectBudget;
	private String path;

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	// 获取
	public String getCause() {
		return cause;
	}

	// 设置
	public void setCause(String cause) {
		this.cause = cause;
	}

	// 获取
	public String getGzMoney() {
		return gzMoney;
	}

	// 设置
	public void setGzMoney(String gzMoney) {
		this.gzMoney = gzMoney;
	}

	// 获取
	public String getAccumulativeYear() {
		return accumulativeYear;
	}

	// 设置
	public void setAccumulativeYear(String accumulativeYear) {
		this.accumulativeYear = accumulativeYear;
	}

	// 获取
	public String getLjMoney() {
		return ljMoney;
	}

	// 设置
	public void setLjMoney(String ljMoney) {
		this.ljMoney = ljMoney;
	}

	// 获取
	public String getContent() {
		return content;
	}

	// 设置
	public void setContent(String content) {
		this.content = content;
	}

	// 获取
	public String getDepartments() {
		return departments;
	}

	// 设置
	public void setDepartments(String departments) {
		this.departments = departments;
	}

	// 获取
	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	// 设置
	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSubscribeBillDate() {
		return subscribeBillDate;
	}

	// 设置
	public void setSubscribeBillDate(Date subscribeBillDate) {
		this.subscribeBillDate = subscribeBillDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getAuditDate() {
		return auditDate;
	}

	// 设置
	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getRequirementsDwDate() {
		return requirementsDwDate;
	}

	// 设置
	public void setRequirementsDwDate(Date requirementsDwDate) {
		this.requirementsDwDate = requirementsDwDate;
	}

	// 获取
	public String getActivityDqDate() {
		return activityDqDate;
	}

	// 设置
	public void setActivityDqDate(String activityDqDate) {
		this.activityDqDate = activityDqDate;
	}

	// 获取
	public String getEmergency() {
		return emergency;
	}

	// 设置
	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	// 获取
	public String getHaveornotSubscribeBill() {
		return haveornotSubscribeBill;
	}

	// 设置
	public void setHaveornotSubscribeBill(String haveornotSubscribeBill) {
		this.haveornotSubscribeBill = haveornotSubscribeBill;
	}

	// 获取
	public String getIsslItem() {
		return isslItem;
	}

	// 设置
	public void setIsslItem(String isslItem) {
		this.isslItem = isslItem;
	}

	// 获取
	public String getDxhtgz() {
		return dxhtgz;
	}

	// 设置
	public void setDxhtgz(String dxhtgz) {
		this.dxhtgz = dxhtgz;
	}

	// 获取
	public String getCglj() {
		return cglj;
	}

	// 设置
	public void setCglj(String cglj) {
		this.cglj = cglj;
	}

	// 获取
	public String getSyfw() {
		return syfw;
	}

	// 设置
	public void setSyfw(String syfw) {
		this.syfw = syfw;
	}

	// 获取
	public String getOpinion() {
		return opinion;
	}

	// 设置
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	// 获取
	public String getPurchaseName() {
		return purchaseName;
	}

	// 设置
	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	// 获取
	public String getPurchaseCount() {
		return purchaseCount;
	}

	// 设置
	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	// 获取
	public String getPurchasePrice() {
		return purchasePrice;
	}

	// 设置
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	// 获取
	public String getPurchaseDepartment() {
		return purchaseDepartment;
	}

	// 设置
	public void setPurchaseDepartment(String purchaseDepartment) {
		this.purchaseDepartment = purchaseDepartment;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudget(String projectBudget) {
		this.projectBudget = projectBudget;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}