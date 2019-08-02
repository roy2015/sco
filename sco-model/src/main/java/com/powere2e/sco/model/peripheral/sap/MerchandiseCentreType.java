package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品中分类实体类
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:10:29
 * @version 1.0
 */
public class MerchandiseCentreType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7137341415412410189L;
	private String centreTypeCode;//
	private String centreTypeName;//
	private Date syncDate;//

	private List<MerchandiseCentreType> list;

	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	public String getCentreTypeName() {
		return centreTypeName;
	}

	public void setCentreTypeName(String centreTypeName) {
		this.centreTypeName = centreTypeName;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseCentreType> getList() {
		return list;
	}

	public void setList(List<MerchandiseCentreType> list) {
		this.list = list;
	}

}