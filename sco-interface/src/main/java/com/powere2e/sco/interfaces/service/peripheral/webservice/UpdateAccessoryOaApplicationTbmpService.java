package com.powere2e.sco.interfaces.service.peripheral.webservice;

import java.util.Date;

import javax.jws.WebService;
	@WebService 
	public interface UpdateAccessoryOaApplicationTbmpService { 
	   public String UpdateAccessoryOaApplicationTbmp(String oaApplicationCode, String applicationType, String applicationLink);
	   
	   public String UpdateAccessoryOaApplicationTbmp(String oaApplicationCode, String applicationType, String tbmpType,String tbmpCode,String Status,Date date ); 
	   
	}


