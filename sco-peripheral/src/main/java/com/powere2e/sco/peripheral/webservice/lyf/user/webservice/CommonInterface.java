/**
 * CommonInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.powere2e.sco.peripheral.webservice.lyf.user.webservice;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.powere2e.sco.peripheral.webservice.lyf.user.bean.CommonEmployeeField;

/**
 * 服务端方法
 * 
 * @author Gavillen.Zhou
 * @since 2015年11月16日
 * @version 1.0
 */
public interface CommonInterface extends Remote {

	/**
	 * 查询用户信息
	 * 
	 * @param systemID
	 *            系统定义的第三方系统的ID值
	 * @param syncParameter 同步门户系统上用户的参数值
	 *            <li>如果参数值不填则同步所有用户</li>
	 *            <li>如果参数值填日期[如：20150817]则同步此日期及此日期之后有过调整的用户信息</li>
	 *            <li>如果参数值填工号，则返回此员工的详细信息</li>
	 * @param token
	 *            访问WebService的安全性口令
	 * @return 用户信息及相应消息
	 * @throws RemoteException
	 */
	public CommonEmployeeField sendEmployee(String systemID,
			String syncParameter, String token) throws RemoteException;

	/**
	 * main方法
	 * 
	 * @param args
	 *            参数
	 * @throws RemoteException
	 */
	public void main(String[] args) throws RemoteException;

}
