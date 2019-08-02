package com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 签量数据实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public class SignedQuantity extends Merchandise {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3678711688412137759L;
	private String qlCode;// 签量编号
	private Date qlCreated;// 签量单创建日期
	private Date qlStartDate;// 签量开始日期
	private BigDecimal qlPrice;// 签量价格
	private BigDecimal qlCount;// 签订数量
	private BigDecimal proCount;// 已完成量
	private BigDecimal proPercent;// 已完成百分比
	private Date finshDate;// 签量满足日期
	private BigDecimal beyondCount;// 超出量
	private String qlStatus;// 签量状态
	private String isSatisfyBeforeQl;// 是否优先满足前一条签量
	private String lastStatus;//上一次状态
	private String gqCode;// 改签单号
	private String qlCreateby;// 签量人
	private String remarks;// 备注
	private BigDecimal xPrice;//商品最新X001进价
	
	public SignedQuantity() {
		super();
	}

	/**
	 * 签量实例有参构造
	 * 
	 * @param mCode
	 *            商品编码
	 * @param sCode
	 *            供应商编码
	 * @param price
	 *            签量价格
	 * @param bak
	 *            备注
	 */
	public SignedQuantity(String qlCode, String mCode, String sCode,
			BigDecimal price, String bak) {
		this.qlCode = qlCode;
		setMerchandiseCode(mCode);
		setSupplierCode(sCode);
		this.qlPrice = price;
		this.remarks = bak;
	}
	
	// 获取
	public String getQlCode() {
		return qlCode;
	}
	// 设置
	public void setQlCode(String qlCode) {
		this.qlCode = qlCode;
	}
	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getQlStartDate() {
		return qlStartDate;
	}

	// 设置
	public void setQlStartDate(Date qlStartDate) {
		this.qlStartDate = qlStartDate;
	}

	// 获取
	public String getIsSatisfyBeforeQl() {
		return isSatisfyBeforeQl;
	}

	// 设置
	public void setIsSatisfyBeforeQl(String isSatisfyBeforeQl) {
		this.isSatisfyBeforeQl = isSatisfyBeforeQl;
	}

	// 获取
	public String getRemarks() {
		return remarks;
	}

	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// 获取
	public String getGqCode() {
		return gqCode;
	}

	// 设置
	public void setGqCode(String gqCode) {
		this.gqCode = gqCode;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getQlCreated() {
		return qlCreated;
	}

	public void setQlCreated(Date qlCreated) {
		this.qlCreated = qlCreated;
	}

	public BigDecimal getQlPrice() {
		return qlPrice;
	}

	public void setQlPrice(BigDecimal qlPrice) {
		this.qlPrice = qlPrice;
	}

	public BigDecimal getQlCount() {
		return qlCount;
	}

	public void setQlCount(BigDecimal qlCount) {
		this.qlCount = qlCount;
	}

	public BigDecimal getProCount() {
		return proCount;
	}

	public void setProCount(BigDecimal proCount) {
		this.proCount = proCount;
	}

	public BigDecimal getProPercent() {
		return proPercent;
	}

	public void setProPercent(BigDecimal proPercent) {
		this.proPercent = proPercent;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFinshDate() {
		return finshDate;
	}

	public void setFinshDate(Date fullPercent) {
		this.finshDate = fullPercent;
	}

	public BigDecimal getBeyondCount() {
		return beyondCount;
	}

	public void setBeyondCount(BigDecimal beyondCount) {
		this.beyondCount = beyondCount;
	}

	public String getQlStatus() {
		return qlStatus;
	}

	public void setQlStatus(String qlStatus) {
		this.qlStatus = qlStatus;
	}

	public String getQlCreateby() {
		return qlCreateby;
	}

	public void setQlCreateby(String qlCreateby) {
		this.qlCreateby = qlCreateby;
	}

	public BigDecimal getxPrice() {
		return xPrice;
	}

	public void setxPrice(BigDecimal xPrice) {
		this.xPrice = xPrice;
	}

	public String getLastStatus() {
		return lastStatus;
	}

	public void setLastStatus(String lastStatus) {
		this.lastStatus = lastStatus;
	}
}