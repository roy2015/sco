package com.powere2e.sco.interfaces.service.peripheral.tbpm;

import java.util.List;

import javax.jws.WebService;

import com.powere2e.sco.model.peripheral.tbmp.ApplicationVisitFactoryMx;
	@WebService 
	public interface GetAccessoryApplicationVisitFactoryTbmpService { 
	   
	   public List<ApplicationVisitFactoryMx> getAccessoryApplicationVisitFactoryTbmp(String applicationVfCode); 
	   
	}


