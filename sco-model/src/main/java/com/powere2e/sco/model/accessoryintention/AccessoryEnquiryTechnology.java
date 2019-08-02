package com.powere2e.sco.model.accessoryintention;
import com.powere2e.frame.server.model.AppModel;
/**
 * 询价单工艺实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryTechnology extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9046969945018892883L;
	private String technologyCode;//
	private String enquiryCode;//
	private String technologyName;//
	private String technologyInfo;//

	
	// 获取
	public String getTechnologyCode() {
		return technologyCode;
	}
	// 设置
	public void setTechnologyCode(String technologyCode) {
		this.technologyCode = technologyCode;
	}
	
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	public String getTechnologyName() {
		return technologyName;
	}
	// 设置
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	
	// 获取
	public String getTechnologyInfo() {
		return technologyInfo;
	}
	// 设置
	public void setTechnologyInfo(String technologyInfo) {
		this.technologyInfo = technologyInfo;
	}
}