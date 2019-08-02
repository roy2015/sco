package com.powere2e.sco.peripheral.webservice.lyf.user.webservice;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;

import com.powere2e.sco.peripheral.webservice.lyf.user.bean.CommonEmployeeField;

public class CommonInterfaceProxy implements CommonInterface {
	private String _endpoint = null;
	private CommonInterface commonInterface = null;

	public CommonInterfaceProxy() {
		_initCommonInterfaceProxy();
	}

	public CommonInterfaceProxy(String endpoint) {
		_endpoint = endpoint;
		_initCommonInterfaceProxy();
	}

	private void _initCommonInterfaceProxy() {
		try {
			commonInterface = (new CommonInterfaceServiceLocator())
					.getCommonInterface();
			if (commonInterface != null) {
				if (_endpoint != null)
					((Stub) commonInterface)._setProperty(
							"service.endpoint.address", _endpoint);
				else
					_endpoint = (String) ((Stub) commonInterface)
							._getProperty("service.endpoint.address");
			}

		} catch (ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (commonInterface != null)
			((Stub) commonInterface)._setProperty("service.endpoint.address",
					_endpoint);

	}

	public CommonInterface getCommonInterface() {
		if (commonInterface == null)
			_initCommonInterfaceProxy();
		return commonInterface;
	}

	public CommonEmployeeField sendEmployee(String systemID,
			String syncParameter, String token) throws RemoteException {
		if (commonInterface == null)
			_initCommonInterfaceProxy();
		return commonInterface.sendEmployee(systemID, syncParameter, token);
	}

	public void main(String[] args) throws java.rmi.RemoteException {
		if (commonInterface == null)
			_initCommonInterfaceProxy();
		commonInterface.main(args);
	}

}