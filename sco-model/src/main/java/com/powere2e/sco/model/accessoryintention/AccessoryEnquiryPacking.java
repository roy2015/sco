package com.powere2e.sco.model.accessoryintention;
import com.powere2e.frame.server.model.AppModel;
/**
 * 询价单内外包装实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryPacking extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6555165050937126985L;
	private String packingCode;//
	private String enquiryCode;//
	private String packingName;//
	private String packingMaterial;//
	private String materialSize;//

	
	// 获取
	public String getPackingCode() {
		return packingCode;
	}
	// 设置
	public void setPackingCode(String packingCode) {
		this.packingCode = packingCode;
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
	public String getPackingName() {
		return packingName;
	}
	// 设置
	public void setPackingName(String packingName) {
		this.packingName = packingName;
	}
	
	// 获取
	public String getPackingMaterial() {
		return packingMaterial;
	}
	// 设置
	public void setPackingMaterial(String packingMaterial) {
		this.packingMaterial = packingMaterial;
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