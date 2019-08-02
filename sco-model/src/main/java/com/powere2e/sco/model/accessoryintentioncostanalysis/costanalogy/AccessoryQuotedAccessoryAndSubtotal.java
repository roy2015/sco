package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;

import java.math.BigDecimal;

import com.powere2e.sco.model.accessoryintention.AccessoryQuotedAccessory;

public class AccessoryQuotedAccessoryAndSubtotal {
	private  AccessoryQuotedAccessory  accessoryQuotedAccessory;
	private BigDecimal subtotal;
	
	public AccessoryQuotedAccessory getAccessoryQuotedAccessory() {
		return accessoryQuotedAccessory;
	}
	public void setAccessoryQuotedAccessory(AccessoryQuotedAccessory accessoryQuotedAccessory) {
		this.accessoryQuotedAccessory = accessoryQuotedAccessory;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}
