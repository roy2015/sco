/**
 * CommonInterfaceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.powere2e.sco.peripheral.webservice.lyf.user.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Service;
import org.apache.axis.client.Stub;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommonInterfaceServiceLocator extends Service implements
		CommonInterfaceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1990107924767707288L;

	public CommonInterfaceServiceLocator() {
	}

	public CommonInterfaceServiceLocator(
			org.apache.axis.EngineConfiguration config) {
		super(config);
	}

	public CommonInterfaceServiceLocator(String wsdlLoc, QName sName)
			throws ServiceException {
		super(wsdlLoc, sName);
	}

	// Use to get a proxy class for CommonInterface
	private String CommonInterface_address = "http://eipsc.laiyifen.com/LyfWebService/services/CommonInterface";

	public String getCommonInterfaceAddress() {
		return CommonInterface_address;
	}

	// The WSDD service name defaults to the port name.
	private String CommonInterfaceWSDDServiceName = "CommonInterface";

	public String getCommonInterfaceWSDDServiceName() {
		return CommonInterfaceWSDDServiceName;
	}

	public void setCommonInterfaceWSDDServiceName(String name) {
		CommonInterfaceWSDDServiceName = name;
	}

	public CommonInterface getCommonInterface() throws ServiceException {
		URL endpoint;
		try {
			endpoint = new URL(CommonInterface_address);
		} catch (MalformedURLException e) {
			throw new ServiceException(e);
		}
		return getCommonInterface(endpoint);
	}

	public CommonInterface getCommonInterface(URL portAddress)
			throws ServiceException {
		try {
			CommonInterfaceSoapBindingStub _stub = new CommonInterfaceSoapBindingStub(
					portAddress, this);
			_stub.setPortName(getCommonInterfaceWSDDServiceName());
			return _stub;
		} catch (org.apache.axis.AxisFault e) {
			return null;
		}
	}

	public void setCommonInterfaceEndpointAddress(String address) {
		CommonInterface_address = address;
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(Class serviceEndpointInterface)
			throws ServiceException {
		try {
			if (CommonInterface.class
					.isAssignableFrom(serviceEndpointInterface)) {
				CommonInterfaceSoapBindingStub _stub = new CommonInterfaceSoapBindingStub(
						new URL(CommonInterface_address), this);
				_stub.setPortName(getCommonInterfaceWSDDServiceName());
				return _stub;
			}
		} catch (Throwable t) {
			throw new ServiceException(t);
		}
		throw new ServiceException(
				"There is no stub implementation for the interface:  "
						+ (serviceEndpointInterface == null ? "null"
								: serviceEndpointInterface.getName()));
	}

	/**
	 * For the given interface, get the stub implementation. If this service has
	 * no port for the given interface, then ServiceException is thrown.
	 */
	public java.rmi.Remote getPort(QName portName,
			Class serviceEndpointInterface) throws ServiceException {
		if (portName == null) {
			return getPort(serviceEndpointInterface);
		}
		String inputPortName = portName.getLocalPart();
		if ("CommonInterface".equals(inputPortName)) {
			return getCommonInterface();
		} else {
			java.rmi.Remote _stub = getPort(serviceEndpointInterface);
			((Stub) _stub).setPortName(portName);
			return _stub;
		}
	}

	public QName getServiceName() {
		return new QName("http://webservice.lyfwebservice.ibm.com",
				"CommonInterfaceService");
	}

	private HashSet ports = null;

	public Iterator getPorts() {
		if (ports == null) {
			ports = new HashSet();
			ports.add(new QName("http://webservice.lyfwebservice.ibm.com",
					"CommonInterface"));
		}
		return ports.iterator();
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(String portName, String address)
			throws ServiceException {

		if ("CommonInterface".equals(portName)) {
			setCommonInterfaceEndpointAddress(address);
		} else { // Unknown Port Name
			throw new ServiceException(
					" Cannot set Endpoint Address for Unknown Port" + portName);
		}
	}

	/**
	 * Set the endpoint address for the specified port name.
	 */
	public void setEndpointAddress(QName portName, String address)
			throws ServiceException {
		setEndpointAddress(portName.getLocalPart(), address);
	}

}
