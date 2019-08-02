package com.powere2e.sco.interfaces.service.peripheral.tbpm;

import javax.jws.WebService;

import com.powere2e.sco.model.peripheral.tbmp.MeterialApplication;
	@WebService 
	public interface GetAccessoryApplicationTbmpService { 
	   
	   public MeterialApplication getAccessoryApplicationTbmp(String oaApplicationCode); 
	   
	}


