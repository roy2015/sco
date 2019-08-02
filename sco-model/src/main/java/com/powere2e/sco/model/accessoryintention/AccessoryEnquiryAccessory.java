package com.powere2e.sco.model.accessoryintention;
import com.powere2e.frame.server.model.AppModel;
/**
 * 辅料实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryAccessory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4834355692102153056L;
	private String accessoryCode;//
	private String enquiryCode;//
	private String accessoryName;//
	private String material;//
	private String materialSize;//

	
	// 获取
	public String getAccessoryCode() {
		return accessoryCode;
	}
	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
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
	public String getAccessoryName() {
		return accessoryName;
	}
	// 设置
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	
	// 获取
	public String getMaterial() {
		return material;
	}
	// 设置
	public void setMaterial(String material) {
		this.material = material;
	}
	
	// 获取
	public String getMaterialSize() {
		return materialSize;
	}
	// 设置
	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}
}