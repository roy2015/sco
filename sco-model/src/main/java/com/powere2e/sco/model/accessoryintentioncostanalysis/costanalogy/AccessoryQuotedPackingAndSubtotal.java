package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;

import java.math.BigDecimal;

import com.powere2e.sco.model.accessoryintention.AccessoryQuotedPacking;

public class AccessoryQuotedPackingAndSubtotal {
	private AccessoryQuotedPacking accessoryQuotedPacking;
	private BigDecimal subtotal;
	
	public AccessoryQuotedPacking getAccessoryQuotedPacking() {
		return accessoryQuotedPacking;
	}
	public void setAccessoryQuotedPacking(AccessoryQuotedPacking accessoryQuotedPacking) {
		this.accessoryQuotedPacking = accessoryQuotedPacking;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	
}
