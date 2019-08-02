package com.powere2e.sco.model.datamaintenance.purchaserdata.accessoryfinetype;
import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;
/**
 * 辅助细分类维护实体类
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class AccessoryFineType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -48965768145430035L;
	private String fineTypeCode;//细分类编号
	private String fineTypeName;//细分类名称
	private Date created;//创建日期
	private String createby;//创建人
	private Date updated;//更新日期
	private String updateby;//更新人

	
	// 获取
	public String getFineTypeCode() {
		return fineTypeCode;
	}
	// 设置
	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}
	
	// 获取
	public String getFineTypeName() {
		return fineTypeName;
	}
	// 设置
	public void setFineTypeName(String fineTypeName) {
		this.fineTypeName = fineTypeName.trim();
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
}