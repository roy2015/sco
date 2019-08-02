package com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiserecycle;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 商品回收记录实体类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseRecycle extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -945857037002373997L;
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private String merchandiseCode;//商品编号
	private String supplierCode;//供应商编号
	private Date recycleDate;//回收日期
	private String recycleCount;//回收数量

	private String merchandiseName;//商品名称
	private String supplierName;//供应商名称
	private String centreTypeCode;//中分类编号
	private String centreName;//中分类
	private String smallTypeCode;//小分类编号
	private String smallName;//小分类
	private String detailTypeCode;//明细类编号
	private String detailName;//明细类
	private String fineTypeCode;//细分类编号
	private String fineName;//细分类
	private String dlRoleCode;//商品定量编号
	private String dlRoleName;//商品定量角色
	private String dxRoleCode;//商品定性编号
	private String dxRoleName;//商品定性角色
	
	
	
	
	public MerchandiseRecycle() {
	}
	
	
	
	public MerchandiseRecycle(String merchandiseCode, String supplierCode, Date recycleDate, String recycleCount) {
		super();
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
		this.recycleDate = recycleDate;
		this.recycleCount = recycleCount;
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
	
	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
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
	@JSON(format="yyyy-MM-dd")
	public Date getRecycleDate() {
		return recycleDate;
	}
	// 设置
	public void setRecycleDate(Date recycleDate) {
		this.recycleDate = recycleDate;
	}
	
	// 获取
	public String getRecycleCount() {
		return recycleCount;
	}
	// 设置
	public void setRecycleCount(String recycleCount) {
		this.recycleCount = recycleCount;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}



	public String getCentreTypeCode() {
		return centreTypeCode;
	}



	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}



	public String getCentreName() {
		return centreName;
	}



	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}



	public String getSmallTypeCode() {
		return smallTypeCode;
	}



	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}



	public String getSmallName() {
		return smallName;
	}



	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}



	public String getDetailTypeCode() {
		return detailTypeCode;
	}



	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}



	public String getDetailName() {
		return detailName;
	}



	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}



	public String getFineTypeCode() {
		return fineTypeCode;
	}



	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}



	public String getFineName() {
		return fineName;
	}



	public void setFineName(String fineName) {
		this.fineName = fineName;
	}



	public String getDlRoleCode() {
		return dlRoleCode;
	}



	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}



	public String getDlRoleName() {
		return dlRoleName;
	}



	public void setDlRoleName(String dlRoleName) {
		this.dlRoleName = dlRoleName;
	}



	public String getDxRoleCode() {
		return dxRoleCode;
	}



	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
	}



	public String getDxRoleName() {
		return dxRoleName;
	}



	public void setDxRoleName(String dxRoleName) {
		this.dxRoleName = dxRoleName;
	}
	
	
}