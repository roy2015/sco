/**
 * CommonInterfaceService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.powere2e.sco.peripheral.webservice.lyf.user.webservice;

import java.net.URL;

import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;

public interface CommonInterfaceService extends Service {

	public String getCommonInterfaceAddress();

	public CommonInterface getCommonInterface() throws ServiceException;

	public CommonInterface getCommonInterface(URL portAddress)
			throws ServiceException;

}
