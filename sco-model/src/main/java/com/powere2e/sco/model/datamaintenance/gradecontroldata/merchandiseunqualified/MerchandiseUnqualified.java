package com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品抽检不合格记录实体类
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public class MerchandiseUnqualified extends Merchandise {

	private static final long serialVersionUID = -2466757087447215878L;
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private Date spotCheckDate;// 抽检日期
	private Date created;// 创建时间
	private String createby;// 创建人
	private Date updated;// 更新时间
	private String updateby;// 更新人
	private Date spotCheckBatch;// 抽检批次
	private Date minSpotCheckDate;// 开始抽检日期
	private Date maxSpotCheckDate;// 结束抽检日期
	private String remarks;// 备注

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@JSON(format = "yyyy-MM-dd")
	public Date getSpotCheckDate() {
		return spotCheckDate;
	}

	// 设置
	public void setSpotCheckDate(Date spotCheckDate) {
		this.spotCheckDate = spotCheckDate;
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

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getSpotCheckBatch() {
		return spotCheckBatch;
	}

	// 设置
	public void setSpotCheckBatch(Date spotCheckBatch) {
		this.spotCheckBatch = spotCheckBatch;
	}

	// 获取
	public Date getMinSpotCheckDate() {
		return minSpotCheckDate;
	}

	// 设置
	public void setMinSpotCheckDate(Date minSpotCheckDate) {
		this.minSpotCheckDate = minSpotCheckDate;
	}

	// 获取
	public Date getMaxSpotCheckDate() {
		return maxSpotCheckDate;
	}

	// 设置
	public void setMaxSpotCheckDate(Date maxSpotCheckDate) {
		this.maxSpotCheckDate = maxSpotCheckDate;
	}

	public MerchandiseUnqualified(String merchandiseCode, String supplierCode, Date spotCheckDate, Date spotCheckBatch, String remarks) {
		super();
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
		this.spotCheckDate = spotCheckDate;
		this.spotCheckBatch = spotCheckBatch;
		this.remarks = remarks;
	}

	public MerchandiseUnqualified() {
		super();
	}
}