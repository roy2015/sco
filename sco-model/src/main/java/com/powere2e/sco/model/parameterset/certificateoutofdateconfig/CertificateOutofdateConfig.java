package com.powere2e.sco.model.parameterset.certificateoutofdateconfig;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 证件过期提醒设置实体类
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月3日
 */
public class CertificateOutofdateConfig extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5036410000885760245L;
	private String configCode;//配置编号
	private Integer outofdate;//过期前天数
	private Date created;//创建时间
	private String createby;//创建人
	private Date updated;//更新时间
	private String updateby;//更新人

	
	// 获取
	public String getConfigCode() {
		return configCode;
	}
	// 设置
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	
	// 获取
	public Integer getOutofdate() {
		return outofdate;
	}
	// 设置
	public void setOutofdate(Integer outofdate) {
		this.outofdate = outofdate;
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