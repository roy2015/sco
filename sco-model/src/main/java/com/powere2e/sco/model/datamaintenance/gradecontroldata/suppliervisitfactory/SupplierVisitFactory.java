package com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory;
import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 供应商年度巡厂得分实体类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class SupplierVisitFactory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6376626034572100109L;
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private BigDecimal floorArea;//占地面积(平方米)
	private BigDecimal plantArea;//车间面积(平方米)
	private Integer enterpriseCountPeople;//企业总人数
	private BigDecimal yearTotalOutput;//年总产值(千万)
	private Date visitFactoryDate;//巡厂日期
	private BigDecimal fullScore;//满分
	private BigDecimal qualifiedScore;//合格分
	private BigDecimal score;//得分
	private String visitFactoryOpinion;//巡厂意见
	private String visitFactoryPrincipal;//巡厂负责人
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	
	
	public SupplierVisitFactory() {
		
	}
	
	

	public SupplierVisitFactory(String supplierCode, BigDecimal floorArea, BigDecimal plantArea, Integer enterpriseCountPeople, BigDecimal yearTotalOutput, Date visitFactoryDate, BigDecimal fullScore,
			BigDecimal qualifiedScore, BigDecimal score, String visitFactoryOpinion, String visitFactoryPrincipal) {
		super();
		this.supplierCode = supplierCode;
		this.floorArea = floorArea;
		this.plantArea = plantArea;
		this.enterpriseCountPeople = enterpriseCountPeople;
		this.yearTotalOutput = yearTotalOutput;
		this.visitFactoryDate = visitFactoryDate;
		this.fullScore = fullScore;
		this.qualifiedScore = qualifiedScore;
		this.score = score;
		this.visitFactoryOpinion = visitFactoryOpinion;
		this.visitFactoryPrincipal = visitFactoryPrincipal;
	}



	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}
	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	// 获取
	public String getSupplierName() {
		return supplierName;
	}
	// 设置
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	// 获取
	public BigDecimal getFloorArea() {
		return floorArea;
	}
	// 设置
	public void setFloorArea(BigDecimal floorArea) {
		this.floorArea = floorArea;
	}
	
	// 获取
	public BigDecimal getPlantArea() {
		return plantArea;
	}
	// 设置
	public void setPlantArea(BigDecimal plantArea) {
		this.plantArea = plantArea;
	}
	
	// 获取
	public Integer getEnterpriseCountPeople() {
		return enterpriseCountPeople;
	}
	// 设置
	public void setEnterpriseCountPeople(Integer enterpriseCountPeople) {
		this.enterpriseCountPeople = enterpriseCountPeople;
	}
	
	// 获取
	public BigDecimal getYearTotalOutput() {
		return yearTotalOutput;
	}
	// 设置
	public void setYearTotalOutput(BigDecimal yearTotalOutput) {
		this.yearTotalOutput = yearTotalOutput;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getVisitFactoryDate() {
		return visitFactoryDate;
	}
	// 设置
	public void setVisitFactoryDate(Date visitFactoryDate) {
		this.visitFactoryDate = visitFactoryDate;
	}
	
	// 获取
	public BigDecimal getFullScore() {
		return fullScore;
	}
	// 设置
	public void setFullScore(BigDecimal fullScore) {
		this.fullScore = fullScore;
	}
	
	// 获取
	public BigDecimal getQualifiedScore() {
		return qualifiedScore;
	}
	// 设置
	public void setQualifiedScore(BigDecimal qualifiedScore) {
		this.qualifiedScore = qualifiedScore;
	}
	
	// 获取
	public BigDecimal getScore() {
		return score;
	}
	// 设置
	public void setScore(BigDecimal score) {
		this.score = score;
	}
	
	// 获取
	public String getVisitFactoryOpinion() {
		return visitFactoryOpinion;
	}
	// 设置
	public void setVisitFactoryOpinion(String visitFactoryOpinion) {
		this.visitFactoryOpinion = visitFactoryOpinion;
	}
	
	// 获取
	public String getVisitFactoryPrincipal() {
		return visitFactoryPrincipal;
	}
	// 设置
	public void setVisitFactoryPrincipal(String visitFactoryPrincipal) {
		this.visitFactoryPrincipal = visitFactoryPrincipal;
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