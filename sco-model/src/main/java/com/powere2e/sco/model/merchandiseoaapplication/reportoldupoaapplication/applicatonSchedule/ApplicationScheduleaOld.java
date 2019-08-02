package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.applicatonSchedule;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 老品新上进度信息 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月25日
 */
public class ApplicationScheduleaOld extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573448153888148037L;
	private String applicationCode;// 申请单号
	private String merchandiseCode;// 意向品/商品编号
	private String merchandiseName;// 意向品/商品名称
	private String supplierCode;// 供应商编号(供应商/意向供应商编号)
	private String supplierName;// (供应商/意向供应商名称)

	private Date xcsqDate;// 巡厂申请日期
	private Date pkxcDate;// 品控巡厂日期
	private Date bzsjsqDate;// 包装设计申请日期
	private Date yjbgtjDate;// 引进报告提交日期
	private Date yjbgwcDate;// 引进报告完成日期
	private Date zsjsqDate;// 主数据申请日期
	private Date zsjsqwcDate;// 主数据申请完成日期
	private Date htqdDate;// 合同签订日期
	private Date bbtgDate;// 八标提供日期
	private Date qdgpDate;// 取得光盘日期
	private Date gyssdgpDate;// 供应商收到光盘日期
	private Date gzysqrDate;// 包装印刷确认日期
	private Date dyqrDate;// 大样确认日期
	private Date spdddcDate;// 商品到达大仓日期
	private Date ssDate;// 上市日期
	private String remarks;// 备注

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getXcsqDate() {
		return xcsqDate;
	}

	public void setXcsqDate(Date xcsqDate) {
		this.xcsqDate = xcsqDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getPkxcDate() {
		return pkxcDate;
	}

	public void setPkxcDate(Date pkxcDate) {
		this.pkxcDate = pkxcDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getBzsjsqDate() {
		return bzsjsqDate;
	}

	public void setBzsjsqDate(Date bzsjsqDate) {
		this.bzsjsqDate = bzsjsqDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getYjbgtjDate() {
		return yjbgtjDate;
	}

	public void setYjbgtjDate(Date yjbgtjDate) {
		this.yjbgtjDate = yjbgtjDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getYjbgwcDate() {
		return yjbgwcDate;
	}

	public void setYjbgwcDate(Date yjbgwcDate) {
		this.yjbgwcDate = yjbgwcDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getZsjsqDate() {
		return zsjsqDate;
	}

	public void setZsjsqDate(Date zsjsqDate) {
		this.zsjsqDate = zsjsqDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getZsjsqwcDate() {
		return zsjsqwcDate;
	}

	public void setZsjsqwcDate(Date zsjsqwcDate) {
		this.zsjsqwcDate = zsjsqwcDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getHtqdDate() {
		return htqdDate;
	}

	public void setHtqdDate(Date htqdDate) {
		this.htqdDate = htqdDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getBbtgDate() {
		return bbtgDate;
	}

	public void setBbtgDate(Date bbtgDate) {
		this.bbtgDate = bbtgDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getQdgpDate() {
		return qdgpDate;
	}

	public void setQdgpDate(Date qdgpDate) {
		this.qdgpDate = qdgpDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getGyssdgpDate() {
		return gyssdgpDate;
	}

	public void setGyssdgpDate(Date gyssdgpDate) {
		this.gyssdgpDate = gyssdgpDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getGzysqrDate() {
		return gzysqrDate;
	}

	public void setGzysqrDate(Date gzysqrDate) {
		this.gzysqrDate = gzysqrDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getDyqrDate() {
		return dyqrDate;
	}

	public void setDyqrDate(Date dyqrDate) {
		this.dyqrDate = dyqrDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getSpdddcDate() {
		return spdddcDate;
	}

	public void setSpdddcDate(Date spdddcDate) {
		this.spdddcDate = spdddcDate;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getSsDate() {
		return ssDate;
	}

	public void setSsDate(Date ssDate) {
		this.ssDate = ssDate;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}