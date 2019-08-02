package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品历史物料号实体类
 * @author Joyce.li
 *  @since 2015年8月18日 上午10:10:47
 *  @version 1.0
 */
public class MerchandiseHistory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7137341415412410189L;
	private String merchandiseCode;//
	private String wlReplace;//
	private Date syncDate;//

	private List<MerchandiseHistory> list;

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getWlReplace() {
		return wlReplace;
	}

	public void setWlReplace(String wlReplace) {
		this.wlReplace = wlReplace;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseHistory> getList() {
		return list;
	}

	public void setList(List<MerchandiseHistory> list) {
		this.list = list;
	}

}