package com.powere2e.sco.model.accessoryintention;
import com.powere2e.frame.server.model.AppModel;
/**
 * 询价单其他要求实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月30日
 */
public class AccessoryEnquiryElse extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7462720197418093821L;
	private String elseCode;//
	private String enquiryCode;//
	private String name;//
	private String info;//

	
	// 获取
	public String getElseCode() {
		return elseCode;
	}
	// 设置
	public void setElseCode(String elseCode) {
		this.elseCode = elseCode;
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
	public String getName() {
		return name;
	}
	// 设置
	public void setName(String name) {
		this.name = name;
	}
	
	// 获取
	public String getInfo() {
		return info;
	}
	// 设置
	public void setInfo(String info) {
		this.info = info;
	}
}