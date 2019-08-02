package com.powere2e.sco.interfaces.service.peripheral.webservice;

import javax.jws.WebService;

@WebService
public interface UpdateAccessoryOaApplicationService {
	public String UpdateAccessoryOaApplication(String oaApplicationCode, String oaUid, String status, String date);
}
