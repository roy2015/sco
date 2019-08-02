package com.powere2e.sco.model.accessoryintention;
import com.powere2e.frame.server.model.AppModel;
/**
 * 询价单原材料实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryMaterial extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2567837737595991128L;
	private String materialCode;//
	private String enquiryCode;//
	private String materialName;//
	private String material;//
	private String materialSize;//

	
	// 获取
	public String getMaterialCode() {
		return materialCode;
	}
	// 设置
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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
	public String getMaterialName() {
		return materialName;
	}
	// 设置
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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