package com.powere2e.sco.peripheral.webservice.lyf.user.bean;

import java.io.Serializable;
import java.lang.reflect.Array;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

/**
 * 用于存储用户信息及响应消息
 * 
 * @author Gavillen.Zhou
 * @since 2015年11月16日
 * @version 1.0
 */
public class CommonEmployeeField implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 42722325880834464L;

	private EmployeeInfo[] emp;

	private String result;

	public CommonEmployeeField() {
	}

	public CommonEmployeeField(EmployeeInfo[] emp, String result) {
		this.emp = emp;
		this.result = result;
	}

	/**
	 * Gets the emp value for this CommonEmployeeField.
	 * 
	 * @return emp
	 */
	public EmployeeInfo[] getEmp() {
		return emp;
	}

	/**
	 * Sets the emp value for this CommonEmployeeField.
	 * 
	 * @param emp
	 */
	public void setEmp(EmployeeInfo[] emp) {
		this.emp = emp;
	}

	/**
	 * Gets the result value for this CommonEmployeeField.
	 * 
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the result value for this CommonEmployeeField.
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	private Object __equalsCalc = null;

	@SuppressWarnings("unused")
	public synchronized boolean equals(Object obj) {
		if (!(obj instanceof CommonEmployeeField))
			return false;
		CommonEmployeeField other = (CommonEmployeeField) obj;
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true
				&& ((this.emp == null && other.getEmp() == null) || (this.emp != null && java.util.Arrays
						.equals(this.emp, other.getEmp())))
				&& ((this.result == null && other.getResult() == null) || (this.result != null && this.result
						.equals(other.getResult())));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;

	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (getEmp() != null) {
			for (int i = 0; i < Array.getLength(getEmp()); i++) {
				Object obj = Array.get(getEmp(), i);
				if (obj != null && !obj.getClass().isArray()) {
					_hashCode += obj.hashCode();
				}
			}
		}
		if (getResult() != null) {
			_hashCode += getResult().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(CommonEmployeeField.class,
			true);

	static {
		typeDesc.setXmlType(new QName("http://bean.lyfwebservice.ibm.com",
				"CommonEmployeeField"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("emp");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"emp"));
		elemField.setXmlType(new QName("http://bean.lyfwebservice.ibm.com",
				"EmployeeInfo"));
		elemField.setNillable(true);
		elemField.setItemQName(new QName(
				"http://webservice.lyfwebservice.ibm.com", "item"));
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("result");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"result"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
	}

	/**
	 * Return type metadata object
	 */
	public static TypeDesc getTypeDesc() {
		return typeDesc;
	}

	/**
	 * Get Custom Serializer
	 */
	public static Serializer getSerializer(String mechType,
			@SuppressWarnings("rawtypes") Class _javaType, QName _xmlType) {
		return new BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static Deserializer getDeserializer(String mechType,
			@SuppressWarnings("rawtypes") Class _javaType, QName _xmlType) {
		return new BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
