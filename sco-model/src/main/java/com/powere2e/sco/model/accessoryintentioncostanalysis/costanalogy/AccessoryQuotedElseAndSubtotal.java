package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;

import java.math.BigDecimal;

import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;

public class AccessoryQuotedElseAndSubtotal {
	private AccessoryQuotedElse accessoryQuotedElse;
	private BigDecimal subtotal;
	
	public AccessoryQuotedElse getAccessoryQuotedElse() {
		return accessoryQuotedElse;
	}
	public void setAccessoryQuotedElse(AccessoryQuotedElse accessoryQuotedElse) {
		this.accessoryQuotedElse = accessoryQuotedElse;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}
