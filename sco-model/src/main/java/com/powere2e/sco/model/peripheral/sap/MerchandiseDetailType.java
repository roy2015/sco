package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品区域销售情况(日直营)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月13日
 */
/**
 * 商品明细类实体类
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:10:47
 * @version 1.0
 */
public class MerchandiseDetailType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7137341415412410189L;
	private String detailTypeCode;//
	private String detailTypeName;//
	private String smallTypeCode;// 小分类编号
	private Date syncDate;//

	private List<MerchandiseDetailType> list;

	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}

	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseDetailType> getList() {
		return list;
	}

	public void setList(List<MerchandiseDetailType> list) {
		this.list = list;
	}

}