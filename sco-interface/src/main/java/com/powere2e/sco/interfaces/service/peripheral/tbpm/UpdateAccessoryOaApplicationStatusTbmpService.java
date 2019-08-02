package com.powere2e.sco.interfaces.service.peripheral.tbpm;

import javax.jws.WebService;
	@WebService 
	public interface UpdateAccessoryOaApplicationStatusTbmpService { 
	   
	   public String updateAccessoryOaApplicationStatusTbmp(String oaApplicationCode, String applicationType, String tbmpCode,String Status,String date ); 
	   
	}


