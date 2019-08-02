package com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo;

import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

/**
 * 申请进度信息(商品OA)2
 * 
 * @author Gavillen.Zhou
 * @since 2015年12月29日
 * @version 1.0
 */
public class ApplicationScheduleM2 extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8604513178653917701L;

	private String applicationCode;// 申请单号
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private Date created;// 样品收集日期(意向品创建时间)
	private Date qryxpDate;// 试吃通过时间(SCO确认意向品日期)
	private Integer sctgBzDay;// 试吃通过标准天数
	private Integer sctgSjDay;// 试吃通过实际天数
	
	private Date xcsqtjDate;// 巡厂申请提交日期
	private Integer xcsqtjBzDay;// 巡厂申请提交标准天数
	private Integer xcsqtjSjDay;// 巡厂申请提交实际天数
	private String xcsqtjStatus;// 巡厂申请提交状态
	
	private Date xcsqshtgDate;// 巡厂申请审核通过日期
	private Integer xcsqshtgBzDay;// 巡厂申请审核通过标准天数
	private Integer xcsqshtgSjDay;// 巡厂申请审核通过实际天数
	private String xcsqshtgStatus;// 巡厂申请审核通过状态
	
	private Date pksjxcDate;// 品控实际巡厂日期
	private Integer pksjxcBzDay;// 品控实际巡厂标准天数
	private Integer pksjxcSjDay;// 品控实际巡厂实际天数
	private String pksjxcStatus;// 品控实际巡厂状态
	
	private Date xpyjbgtjDate;// 新品引进报告提交日期
	private Integer xpyjbgtjBzDay;// 新品引进报告提交标准天数
	private Integer xpyjbgtjSjDay;// 新品引进报告提交实际天数
	private String xpyjbgtjStatus;// 新品引进报告提交状态
	
	private Date xpyjbgsptgDate;// 新品引进报告审批通过日期
	private Integer xpyjbgsptgBzDay;// 新品引进报告审批通过标准天数
	private Integer xpyjbgsptgSjDay;// 新品引进报告审批通过实际天数
	private String xpyjbgsptgStatus;// 新品引进报告审批通过状态
	
	private Date zsjsqsptgDate;// 主数据申请审批通过日期
	private Integer zsjsqsptgBzDay;// 主数据申请审批通过标准天数
	private Integer zsjsqsptgSjDay;// 主数据申请审批通过实际天数
	private String zsjsqsptgStatus;// 主数据申请审批通过状态
	
	private Date bzsjsqDate;// 包装设计申请日期
	private Integer bzsjsqBzDay;// 包装设计申请标准天数
	private Integer bzsjsqSjDay;// 包装设计申请实际天数
	private String bzsjsqStatus;// 包装设计申请状态
	
	private Date bzsjsqwcDate;// 包装设计申请完成日期
	private Integer bzsjsqwcBzDay;// 包装设计申请完成标准天数
	private Integer bzsjsqwcSjDay;// 包装设计申请完成实际天数
	private String bzsjsqwcStatus;// 包装设计申请完成状态
	
	private Date bzsjcgqrDate;// 包装设计初稿确认日期
	private Integer bzsjcgqrBzDay;// 包装设计初稿确认标准天数
	private Integer bzsjcgqrSjDay;// 包装设计初稿确认实际天数
	private String bzsjcgqrStatus;// 包装设计初稿确认状态
	
	private Date bzsjwgtgDate;// 包装设计完稿通过日期
	private Integer jlbzsjcgqrBzDay;// 距离包装设计初稿确认日期标准天数
	private Integer jlbzsjcgqrSjDay;// 距离包装设计初稿确认日期实际天数
	private Integer jlwlzsjsqsptgBzDay;// 距离物料主数据申请审批通过日期标准天数
	private Integer jlwlzsjsqsptgSjDay;// 距离物料主数据申请审批通过日期实际天数
	private String bzsjwgtgStatus;// 包装设计完稿通过状态
	
	private Date xpffDate;// 新品发放日期
	private Integer xpffBzDay;// 新品发放标准天数
	private Integer xpffSjDay;// 新品发放实际天数
	private String xpffStatus;// 新品发放状态
	
	private Integer bzDay;// 标准总天数
	private Integer sjDay;// 实际总天数
	private Date startDate;// 生效日期
	private Date endDate;// 结束日期
	
	private String createby;// 创建人
	private Date updated;// 更新时间
	private String updateby;// 更新人

	private String applicationVfCode;//商品巡厂申请单号
	private String applicationPdCode;//商品包装设计申请单号
	
	/**
	 * 无参构造器
	 */
	public ApplicationScheduleM2() {
		
	}
	
	/**
	 * 有参构造器
	 */
	public ApplicationScheduleM2(String oaApplicationCode,
			String intentionCode, String supplierCode) {
		this.applicationCode = oaApplicationCode;
		this.merchandiseCode = intentionCode;
		this.supplierCode = supplierCode;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public Date getQryxpDate() {
		return qryxpDate;
	}

	public void setQryxpDate(Date qryxpDate) {
		this.qryxpDate = qryxpDate;
	}

	public Integer getSctgBzDay() {
		return sctgBzDay;
	}

	public void setSctgBzDay(Integer sctgBzDay) {
		this.sctgBzDay = sctgBzDay;
	}

	public Integer getSctgSjDay() {
		return sctgSjDay;
	}

	public void setSctgSjDay(Integer sctgSjDay) {
		this.sctgSjDay = sctgSjDay;
	}

	public Date getXcsqtjDate() {
		return xcsqtjDate;
	}

	public void setXcsqtjDate(Date xcsqtjDate) {
		this.xcsqtjDate = xcsqtjDate;
	}

	public String getXcsqtjStatus() {
		return xcsqtjStatus;
	}

	public void setXcsqtjStatus(String xcsqtjStatus) {
		this.xcsqtjStatus = xcsqtjStatus;
	}

	public Integer getXcsqtjSjDay() {
		return xcsqtjSjDay;
	}

	public void setXcsqtjSjDay(Integer xcsqtjSjDay) {
		this.xcsqtjSjDay = xcsqtjSjDay;
	}

	public Integer getXcsqtjBzDay() {
		return xcsqtjBzDay;
	}

	public void setXcsqtjBzDay(Integer xcsqtjBzDay) {
		this.xcsqtjBzDay = xcsqtjBzDay;
	}

	public Date getXcsqshtgDate() {
		return xcsqshtgDate;
	}

	public void setXcsqshtgDate(Date xcsqshtgDate) {
		this.xcsqshtgDate = xcsqshtgDate;
	}

	public String getXcsqshtgStatus() {
		return xcsqshtgStatus;
	}

	public void setXcsqshtgStatus(String xcsqshtgStatus) {
		this.xcsqshtgStatus = xcsqshtgStatus;
	}

	public Integer getXcsqshtgBzDay() {
		return xcsqshtgBzDay;
	}

	public void setXcsqshtgBzDay(Integer xcsqshtgBzDay) {
		this.xcsqshtgBzDay = xcsqshtgBzDay;
	}

	public Integer getXcsqshtgSjDay() {
		return xcsqshtgSjDay;
	}

	public void setXcsqshtgSjDay(Integer xcsqshtgSjDay) {
		this.xcsqshtgSjDay = xcsqshtgSjDay;
	}

	public Date getPksjxcDate() {
		return pksjxcDate;
	}

	public void setPksjxcDate(Date pksjxcDate) {
		this.pksjxcDate = pksjxcDate;
	}

	public String getPksjxcStatus() {
		return pksjxcStatus;
	}

	public void setPksjxcStatus(String pksjxcStatus) {
		this.pksjxcStatus = pksjxcStatus;
	}

	public Integer getPksjxcBzDay() {
		return pksjxcBzDay;
	}

	public void setPksjxcBzDay(Integer pksjxcBzDay) {
		this.pksjxcBzDay = pksjxcBzDay;
	}

	public Integer getPksjxcSjDay() {
		return pksjxcSjDay;
	}

	public void setPksjxcSjDay(Integer pksjxcSjDay) {
		this.pksjxcSjDay = pksjxcSjDay;
	}

	public Date getXpyjbgtjDate() {
		return xpyjbgtjDate;
	}

	public void setXpyjbgtjDate(Date xpyjbgtjDate) {
		this.xpyjbgtjDate = xpyjbgtjDate;
	}

	public String getXpyjbgtjStatus() {
		return xpyjbgtjStatus;
	}

	public void setXpyjbgtjStatus(String xpyjbgtjStatus) {
		this.xpyjbgtjStatus = xpyjbgtjStatus;
	}

	public Integer getXpyjbgtjBzDay() {
		return xpyjbgtjBzDay;
	}

	public void setXpyjbgtjBzDay(Integer xpyjbgtjBzDay) {
		this.xpyjbgtjBzDay = xpyjbgtjBzDay;
	}

	public Integer getXpyjbgtjSjDay() {
		return xpyjbgtjSjDay;
	}

	public void setXpyjbgtjSjDay(Integer xpyjbgtjSjDay) {
		this.xpyjbgtjSjDay = xpyjbgtjSjDay;
	}

	public Date getXpyjbgsptgDate() {
		return xpyjbgsptgDate;
	}

	public void setXpyjbgsptgDate(Date xpyjbgsptgDate) {
		this.xpyjbgsptgDate = xpyjbgsptgDate;
	}

	public String getXpyjbgsptgStatus() {
		return xpyjbgsptgStatus;
	}

	public void setXpyjbgsptgStatus(String xpyjbgsptgStatus) {
		this.xpyjbgsptgStatus = xpyjbgsptgStatus;
	}

	public Integer getXpyjbgsptgBzDay() {
		return xpyjbgsptgBzDay;
	}

	public void setXpyjbgsptgBzDay(Integer xpyjbgsptgBzDay) {
		this.xpyjbgsptgBzDay = xpyjbgsptgBzDay;
	}

	public Integer getXpyjbgsptgSjDay() {
		return xpyjbgsptgSjDay;
	}

	public void setXpyjbgsptgSjDay(Integer xpyjbgsptgSjDay) {
		this.xpyjbgsptgSjDay = xpyjbgsptgSjDay;
	}

	public Date getZsjsqsptgDate() {
		return zsjsqsptgDate;
	}

	public void setZsjsqsptgDate(Date zsjsqsptgDate) {
		this.zsjsqsptgDate = zsjsqsptgDate;
	}

	public String getZsjsqsptgStatus() {
		return zsjsqsptgStatus;
	}

	public void setZsjsqsptgStatus(String zsjsqsptgStatus) {
		this.zsjsqsptgStatus = zsjsqsptgStatus;
	}

	public Integer getZsjsqsptgBzDay() {
		return zsjsqsptgBzDay;
	}

	public void setZsjsqsptgBzDay(Integer zsjsqsptgBzDay) {
		this.zsjsqsptgBzDay = zsjsqsptgBzDay;
	}

	public Integer getZsjsqsptgSjDay() {
		return zsjsqsptgSjDay;
	}

	public void setZsjsqsptgSjDay(Integer zsjsqsptgSjDay) {
		this.zsjsqsptgSjDay = zsjsqsptgSjDay;
	}

	public Date getBzsjsqDate() {
		return bzsjsqDate;
	}

	public void setBzsjsqDate(Date bzsjsqDate) {
		this.bzsjsqDate = bzsjsqDate;
	}

	public String getBzsjsqStatus() {
		return bzsjsqStatus;
	}

	public void setBzsjsqStatus(String bzsjsqStatus) {
		this.bzsjsqStatus = bzsjsqStatus;
	}

	public Integer getBzsjsqBzDay() {
		return bzsjsqBzDay;
	}

	public void setBzsjsqBzDay(Integer bzsjsqBzDay) {
		this.bzsjsqBzDay = bzsjsqBzDay;
	}

	public Integer getBzsjsqSjDay() {
		return bzsjsqSjDay;
	}

	public void setBzsjsqSjDay(Integer bzsjsqSjDay) {
		this.bzsjsqSjDay = bzsjsqSjDay;
	}

	public Date getBzsjsqwcDate() {
		return bzsjsqwcDate;
	}

	public void setBzsjsqwcDate(Date bzsjsqwcDate) {
		this.bzsjsqwcDate = bzsjsqwcDate;
	}

	public String getBzsjsqwcStatus() {
		return bzsjsqwcStatus;
	}

	public void setBzsjsqwcStatus(String bzsjsqwcStatus) {
		this.bzsjsqwcStatus = bzsjsqwcStatus;
	}

	public Integer getBzsjsqwcBzDay() {
		return bzsjsqwcBzDay;
	}

	public void setBzsjsqwcBzDay(Integer bzsjsqwcBzDay) {
		this.bzsjsqwcBzDay = bzsjsqwcBzDay;
	}

	public Integer getBzsjsqwcSjDay() {
		return bzsjsqwcSjDay;
	}

	public void setBzsjsqwcSjDay(Integer bzsjsqwcSjDay) {
		this.bzsjsqwcSjDay = bzsjsqwcSjDay;
	}

	public Date getBzsjcgqrDate() {
		return bzsjcgqrDate;
	}

	public void setBzsjcgqrDate(Date bzsjcgqrDate) {
		this.bzsjcgqrDate = bzsjcgqrDate;
	}

	public String getBzsjcgqrStatus() {
		return bzsjcgqrStatus;
	}

	public void setBzsjcgqrStatus(String bzsjcgqrStatus) {
		this.bzsjcgqrStatus = bzsjcgqrStatus;
	}

	public Integer getBzsjcgqrBzDay() {
		return bzsjcgqrBzDay;
	}

	public void setBzsjcgqrBzDay(Integer bzsjcgqrBzDay) {
		this.bzsjcgqrBzDay = bzsjcgqrBzDay;
	}

	public Integer getBzsjcgqrSjDay() {
		return bzsjcgqrSjDay;
	}

	public void setBzsjcgqrSjDay(Integer bzsjcgqrSjDay) {
		this.bzsjcgqrSjDay = bzsjcgqrSjDay;
	}

	public Date getBzsjwgtgDate() {
		return bzsjwgtgDate;
	}

	public void setBzsjwgtgDate(Date bzsjwgtgDate) {
		this.bzsjwgtgDate = bzsjwgtgDate;
	}

	public String getBzsjwgtgStatus() {
		return bzsjwgtgStatus;
	}

	public void setBzsjwgtgStatus(String bzsjwgtgStatus) {
		this.bzsjwgtgStatus = bzsjwgtgStatus;
	}

	public Integer getJlbzsjcgqrBzDay() {
		return jlbzsjcgqrBzDay;
	}

	public void setJlbzsjcgqrBzDay(Integer jlbzsjcgqrBzDay) {
		this.jlbzsjcgqrBzDay = jlbzsjcgqrBzDay;
	}

	public Integer getJlbzsjcgqrSjDay() {
		return jlbzsjcgqrSjDay;
	}

	public void setJlbzsjcgqrSjDay(Integer jlbzsjcgqrSjDay) {
		this.jlbzsjcgqrSjDay = jlbzsjcgqrSjDay;
	}

	public Integer getJlwlzsjsqsptgBzDay() {
		return jlwlzsjsqsptgBzDay;
	}

	public void setJlwlzsjsqsptgBzDay(Integer jlwlzsjsqsptgBzDay) {
		this.jlwlzsjsqsptgBzDay = jlwlzsjsqsptgBzDay;
	}

	public Integer getJlwlzsjsqsptgSjDay() {
		return jlwlzsjsqsptgSjDay;
	}

	public void setJlwlzsjsqsptgSjDay(Integer jlwlzsjsqsptgSjDay) {
		this.jlwlzsjsqsptgSjDay = jlwlzsjsqsptgSjDay;
	}

	public Date getXpffDate() {
		return xpffDate;
	}

	public void setXpffDate(Date xpffDate) {
		this.xpffDate = xpffDate;
	}

	public String getXpffStatus() {
		return xpffStatus;
	}

	public void setXpffStatus(String xpffStatus) {
		this.xpffStatus = xpffStatus;
	}

	public Integer getXpffBzDay() {
		return xpffBzDay;
	}

	public void setXpffBzDay(Integer xpffBzDay) {
		this.xpffBzDay = xpffBzDay;
	}

	public Integer getXpffSjDay() {
		return xpffSjDay;
	}

	public void setXpffSjDay(Integer xpffSjDay) {
		this.xpffSjDay = xpffSjDay;
	}

	public Integer getBzDay() {
		return bzDay;
	}

	public void setBzDay(Integer bzDay) {
		this.bzDay = bzDay;
	}

	public Integer getSjDay() {
		return sjDay;
	}

	public void setSjDay(Integer sjDay) {
		this.sjDay = sjDay;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getApplicationVfCode() {
		return applicationVfCode;
	}

	public void setApplicationVfCode(String applicationVfCode) {
		this.applicationVfCode = applicationVfCode;
	}

	public String getApplicationPdCode() {
		return applicationPdCode;
	}

	public void setApplicationPdCode(String applicationPdCode) {
		this.applicationPdCode = applicationPdCode;
	}

}
