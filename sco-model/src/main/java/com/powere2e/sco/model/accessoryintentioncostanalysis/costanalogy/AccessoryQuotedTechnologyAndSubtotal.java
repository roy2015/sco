package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;

import java.math.BigDecimal;

import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTechnology;

public class AccessoryQuotedTechnologyAndSubtotal {
	private AccessoryQuotedTechnology accessoryQuotedTechnology;
	private BigDecimal subtotal;
	
	public AccessoryQuotedTechnology getAccessoryQuotedTechnology() {
		return accessoryQuotedTechnology;
	}
	public void setAccessoryQuotedTechnology(AccessoryQuotedTechnology accessoryQuotedTechnology) {
		this.accessoryQuotedTechnology = accessoryQuotedTechnology;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}
