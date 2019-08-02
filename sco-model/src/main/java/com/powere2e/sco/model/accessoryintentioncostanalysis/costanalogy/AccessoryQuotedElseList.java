package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;

/**
 * 辅料报价-其他成本实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElseList extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8956309886346435491L;
	private List<AccessoryQuotedElse> accessoryQuotedElsearray;
	public List<AccessoryQuotedElse> getAccessoryQuotedElsearray() {
		return accessoryQuotedElsearray;
	}
	public void setAccessoryQuotedElsearray(List<AccessoryQuotedElse> accessoryQuotedElsearray) {
		this.accessoryQuotedElsearray = accessoryQuotedElsearray;
	}
	
	
	
}