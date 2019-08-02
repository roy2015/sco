package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品小分类实体类
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:10:47
 * @version 1.0
 */
public class MerchandiseSmallType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7137341415412410189L;
	private String smallTypeCode;//
	private String smallTypeName;//
	private String centreTypeCode;// 中分类编号
	private Date syncDate;//

	private List<MerchandiseSmallType> list;

	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	public String getSmallTypeName() {
		return smallTypeName;
	}

	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName;
	}

	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseSmallType> getList() {
		return list;
	}

	public void setList(List<MerchandiseSmallType> list) {
		this.list = list;
	}

}