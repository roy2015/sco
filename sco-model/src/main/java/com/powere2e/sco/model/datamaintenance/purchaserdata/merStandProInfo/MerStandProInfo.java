package com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品引进标准进度信息维护 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public class MerStandProInfo extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4679603394852644440L;

	private String configCode;// 编号
	private Date startDate;// 生效日期
	private Date endDate;// 结束日期
	private BigDecimal sumDay;// 总天数
	
	private BigDecimal processDate1;// 流程天数(试吃通过日期)
	private BigDecimal processDate2;// 流程天数(包装设计申请提交日期)
	private BigDecimal processDate3;// 流程天数(巡厂申请提交日期)
	private BigDecimal processDate4;// 流程天数(新品引进申请提交日期)
	private BigDecimal processDate5;// 流程天数(新品引进审批通过日期)
	private BigDecimal processDate6;// 流程天数(包装设计审批通过日期)
	private BigDecimal processDate7;// 流程天数(品控实际巡厂日期)
	private BigDecimal processDate8;// 流程天数(物料主数据申请审批通过日期)
	private BigDecimal processDate9;// 流程天数(包装设计初稿确认日期)
	private BigDecimal processDate10;// 流程天数(包装设计完稿确认日期)
	private BigDecimal processDate11;// 流程天数()
	private BigDecimal processDate12;// 流程天数(新品发放日期)--
	
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	
	@JSON(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	@JSON(format = "yyyy-MM-dd")
	public Date getCreated() {
		return super.getCreated();
	}

	public BigDecimal getSumDay() {
		return sumDay;
	}
	public void setSumDay(BigDecimal sumDay) {
		this.sumDay = sumDay;
	}
	public BigDecimal getProcessDate1() {
		return processDate1;
	}
	public void setProcessDate1(BigDecimal processDate1) {
		this.processDate1 = processDate1;
	}
	public BigDecimal getProcessDate2() {
		return processDate2;
	}
	public void setProcessDate2(BigDecimal processDate2) {
		this.processDate2 = processDate2;
	}
	public BigDecimal getProcessDate3() {
		return processDate3;
	}
	public void setProcessDate3(BigDecimal processDate3) {
		this.processDate3 = processDate3;
	}
	public BigDecimal getProcessDate4() {
		return processDate4;
	}
	public void setProcessDate4(BigDecimal processDate4) {
		this.processDate4 = processDate4;
	}
	public BigDecimal getProcessDate5() {
		return processDate5;
	}
	public void setProcessDate5(BigDecimal processDate5) {
		this.processDate5 = processDate5;
	}
	public BigDecimal getProcessDate6() {
		return processDate6;
	}
	public void setProcessDate6(BigDecimal processDate6) {
		this.processDate6 = processDate6;
	}
	public BigDecimal getProcessDate7() {
		return processDate7;
	}
	public void setProcessDate7(BigDecimal processDate7) {
		this.processDate7 = processDate7;
	}
	public BigDecimal getProcessDate8() {
		return processDate8;
	}
	public void setProcessDate8(BigDecimal processDate8) {
		this.processDate8 = processDate8;
	}
	public BigDecimal getProcessDate9() {
		return processDate9;
	}
	public void setProcessDate9(BigDecimal processDate9) {
		this.processDate9 = processDate9;
	}
	public BigDecimal getProcessDate10() {
		return processDate10;
	}
	public void setProcessDate10(BigDecimal processDate10) {
		this.processDate10 = processDate10;
	}
	public BigDecimal getProcessDate11() {
		return processDate11;
	}
	public void setProcessDate11(BigDecimal processDate11) {
		this.processDate11 = processDate11;
	}
	public BigDecimal getProcessDate12() {
		return processDate12;
	}
	public void setProcessDate12(BigDecimal processDate12) {
		this.processDate12 = processDate12;
	}
	
}