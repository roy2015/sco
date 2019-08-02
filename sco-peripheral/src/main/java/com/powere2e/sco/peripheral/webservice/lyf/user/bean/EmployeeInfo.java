package com.powere2e.sco.peripheral.webservice.lyf.user.bean;

import javax.xml.namespace.QName;

import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

/**
 * 用户信息
 * 
 * @author Gavillen.Zhou
 * @since 2015年11月16日
 * @version 1.0
 */
@SuppressWarnings("rawtypes")
public class EmployeeInfo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4008804769743339853L;

	private String changeType;

	private String cn;// 姓名

	private String departmentNumber;//部门ID

	private String employeeType;// 员工类型

	private String erPersonStatus;

	private String givenname;// 名

	private String ilaiyfstate;

	private String jobLevel;// 层级

	private String lyfbirthday;// 出生日期

	private String lyfchinesepinyin;//中文拼音

	private String lyfcompanynumber;

	private String lyfcostcenter;//成本中心

	private String lyfenglishname;// 英文名

	private String lyfhireday;// 雇佣日期

	private String lyfhomeplace;// 家庭地址

	private String lyfidcard;// 身份证号

	private String lyflevel;// 职级

	private String lyfmail;// 公司邮箱

	private String lyfmanagernumber;// 上级领导

	private String lyfnationality;// 籍贯

	private String lyforgshort;// 部门简称

	private String lyfpassport;// 护照

	private String lyfsex;// 性别

	private String lyfstation;// 岗位名称

	private String lyfstationcode;// 岗位ID

	private String lyftermiday;// 离职日期

	private String lyfwebshort;// 公司短号

	private String lyfworkarea;// 人事子范围

	private String lyfworktime;// 班次ID

	private String mail;// 个人邮箱

	private String mobile;//个人手机

	private String o;//部门全称

	private String postalcode;// 邮政编码

	private String sn;// 姓

	private String telephonenumber;// 办公电话

	private String telexnumber;// 传真号

	private String uid;// 工号

	public EmployeeInfo() {
	}

	public EmployeeInfo(String changeType, String cn, String departmentNumber,
			String employeeType, String erPersonStatus, String givenname,
			String ilaiyfstate, String jobLevel, String lyfbirthday,
			String lyfchinesepinyin, String lyfcompanynumber,
			String lyfcostcenter, String lyfenglishname, String lyfhireday,
			String lyfhomeplace, String lyfidcard, String lyflevel,
			String lyfmail, String lyfmanagernumber, String lyfnationality,
			String lyforgshort, String lyfpassport, String lyfsex,
			String lyfstation, String lyfstationcode, String lyftermiday,
			String lyfwebshort, String lyfworkarea, String lyfworktime,
			String mail, String mobile, String o, String postalcode, String sn,
			String telephonenumber, String telexnumber, String uid) {
		this.changeType = changeType;
		this.cn = cn;
		this.departmentNumber = departmentNumber;
		this.employeeType = employeeType;
		this.erPersonStatus = erPersonStatus;
		this.givenname = givenname;
		this.ilaiyfstate = ilaiyfstate;
		this.jobLevel = jobLevel;
		this.lyfbirthday = lyfbirthday;
		this.lyfchinesepinyin = lyfchinesepinyin;
		this.lyfcompanynumber = lyfcompanynumber;
		this.lyfcostcenter = lyfcostcenter;
		this.lyfenglishname = lyfenglishname;
		this.lyfhireday = lyfhireday;
		this.lyfhomeplace = lyfhomeplace;
		this.lyfidcard = lyfidcard;
		this.lyflevel = lyflevel;
		this.lyfmail = lyfmail;
		this.lyfmanagernumber = lyfmanagernumber;
		this.lyfnationality = lyfnationality;
		this.lyforgshort = lyforgshort;
		this.lyfpassport = lyfpassport;
		this.lyfsex = lyfsex;
		this.lyfstation = lyfstation;
		this.lyfstationcode = lyfstationcode;
		this.lyftermiday = lyftermiday;
		this.lyfwebshort = lyfwebshort;
		this.lyfworkarea = lyfworkarea;
		this.lyfworktime = lyfworktime;
		this.mail = mail;
		this.mobile = mobile;
		this.o = o;
		this.postalcode = postalcode;
		this.sn = sn;
		this.telephonenumber = telephonenumber;
		this.telexnumber = telexnumber;
		this.uid = uid;
	}

	/**
	 * Gets the changeType value for this EmployeeInfo.
	 * 
	 * @return changeType
	 */
	public String getChangeType() {
		return changeType;
	}

	/**
	 * Sets the changeType value for this EmployeeInfo.
	 * 
	 * @param changeType
	 */
	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	/**
	 * Gets the cn value for this EmployeeInfo.
	 * 
	 * @return cn
	 */
	public String getCn() {
		return cn;
	}

	/**
	 * Sets the cn value for this EmployeeInfo.
	 * 
	 * @param cn
	 */
	public void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * Gets the departmentNumber value for this EmployeeInfo.
	 * 
	 * @return departmentNumber
	 */
	public String getDepartmentNumber() {
		return departmentNumber;
	}

	/**
	 * Sets the departmentNumber value for this EmployeeInfo.
	 * 
	 * @param departmentNumber
	 */
	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
	}

	/**
	 * Gets the employeeType value for this EmployeeInfo.
	 * 
	 * @return employeeType
	 */
	public String getEmployeeType() {
		return employeeType;
	}

	/**
	 * Sets the employeeType value for this EmployeeInfo.
	 * 
	 * @param employeeType
	 */
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	/**
	 * Gets the erPersonStatus value for this EmployeeInfo.
	 * 
	 * @return erPersonStatus
	 */
	public String getErPersonStatus() {
		return erPersonStatus;
	}

	/**
	 * Sets the erPersonStatus value for this EmployeeInfo.
	 * 
	 * @param erPersonStatus
	 */
	public void setErPersonStatus(String erPersonStatus) {
		this.erPersonStatus = erPersonStatus;
	}

	/**
	 * Gets the givenname value for this EmployeeInfo.
	 * 
	 * @return givenname
	 */
	public String getGivenname() {
		return givenname;
	}

	/**
	 * Sets the givenname value for this EmployeeInfo.
	 * 
	 * @param givenname
	 */
	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	/**
	 * Gets the ilaiyfstate value for this EmployeeInfo.
	 * 
	 * @return ilaiyfstate
	 */
	public String getIlaiyfstate() {
		return ilaiyfstate;
	}

	/**
	 * Sets the ilaiyfstate value for this EmployeeInfo.
	 * 
	 * @param ilaiyfstate
	 */
	public void setIlaiyfstate(String ilaiyfstate) {
		this.ilaiyfstate = ilaiyfstate;
	}

	/**
	 * Gets the jobLevel value for this EmployeeInfo.
	 * 
	 * @return jobLevel
	 */
	public String getJobLevel() {
		return jobLevel;
	}

	/**
	 * Sets the jobLevel value for this EmployeeInfo.
	 * 
	 * @param jobLevel
	 */
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}

	/**
	 * Gets the lyfbirthday value for this EmployeeInfo.
	 * 
	 * @return lyfbirthday
	 */
	public String getLyfbirthday() {
		return lyfbirthday;
	}

	/**
	 * Sets the lyfbirthday value for this EmployeeInfo.
	 * 
	 * @param lyfbirthday
	 */
	public void setLyfbirthday(String lyfbirthday) {
		this.lyfbirthday = lyfbirthday;
	}

	/**
	 * Gets the lyfchinesepinyin value for this EmployeeInfo.
	 * 
	 * @return lyfchinesepinyin
	 */
	public String getLyfchinesepinyin() {
		return lyfchinesepinyin;
	}

	/**
	 * Sets the lyfchinesepinyin value for this EmployeeInfo.
	 * 
	 * @param lyfchinesepinyin
	 */
	public void setLyfchinesepinyin(String lyfchinesepinyin) {
		this.lyfchinesepinyin = lyfchinesepinyin;
	}

	/**
	 * Gets the lyfcompanynumber value for this EmployeeInfo.
	 * 
	 * @return lyfcompanynumber
	 */
	public String getLyfcompanynumber() {
		return lyfcompanynumber;
	}

	/**
	 * Sets the lyfcompanynumber value for this EmployeeInfo.
	 * 
	 * @param lyfcompanynumber
	 */
	public void setLyfcompanynumber(String lyfcompanynumber) {
		this.lyfcompanynumber = lyfcompanynumber;
	}

	/**
	 * Gets the lyfcostcenter value for this EmployeeInfo.
	 * 
	 * @return lyfcostcenter
	 */
	public String getLyfcostcenter() {
		return lyfcostcenter;
	}

	/**
	 * Sets the lyfcostcenter value for this EmployeeInfo.
	 * 
	 * @param lyfcostcenter
	 */
	public void setLyfcostcenter(String lyfcostcenter) {
		this.lyfcostcenter = lyfcostcenter;
	}

	/**
	 * Gets the lyfenglishname value for this EmployeeInfo.
	 * 
	 * @return lyfenglishname
	 */
	public String getLyfenglishname() {
		return lyfenglishname;
	}

	/**
	 * Sets the lyfenglishname value for this EmployeeInfo.
	 * 
	 * @param lyfenglishname
	 */
	public void setLyfenglishname(String lyfenglishname) {
		this.lyfenglishname = lyfenglishname;
	}

	/**
	 * Gets the lyfhireday value for this EmployeeInfo.
	 * 
	 * @return lyfhireday
	 */
	public String getLyfhireday() {
		return lyfhireday;
	}

	/**
	 * Sets the lyfhireday value for this EmployeeInfo.
	 * 
	 * @param lyfhireday
	 */
	public void setLyfhireday(String lyfhireday) {
		this.lyfhireday = lyfhireday;
	}

	/**
	 * Gets the lyfhomeplace value for this EmployeeInfo.
	 * 
	 * @return lyfhomeplace
	 */
	public String getLyfhomeplace() {
		return lyfhomeplace;
	}

	/**
	 * Sets the lyfhomeplace value for this EmployeeInfo.
	 * 
	 * @param lyfhomeplace
	 */
	public void setLyfhomeplace(String lyfhomeplace) {
		this.lyfhomeplace = lyfhomeplace;
	}

	/**
	 * Gets the lyfidcard value for this EmployeeInfo.
	 * 
	 * @return lyfidcard
	 */
	public String getLyfidcard() {
		return lyfidcard;
	}

	/**
	 * Sets the lyfidcard value for this EmployeeInfo.
	 * 
	 * @param lyfidcard
	 */
	public void setLyfidcard(String lyfidcard) {
		this.lyfidcard = lyfidcard;
	}

	/**
	 * Gets the lyflevel value for this EmployeeInfo.
	 * 
	 * @return lyflevel
	 */
	public String getLyflevel() {
		return lyflevel;
	}

	/**
	 * Sets the lyflevel value for this EmployeeInfo.
	 * 
	 * @param lyflevel
	 */
	public void setLyflevel(String lyflevel) {
		this.lyflevel = lyflevel;
	}

	/**
	 * Gets the lyfmail value for this EmployeeInfo.
	 * 
	 * @return lyfmail
	 */
	public String getLyfmail() {
		return lyfmail;
	}

	/**
	 * Sets the lyfmail value for this EmployeeInfo.
	 * 
	 * @param lyfmail
	 */
	public void setLyfmail(String lyfmail) {
		this.lyfmail = lyfmail;
	}

	/**
	 * Gets the lyfmanagernumber value for this EmployeeInfo.
	 * 
	 * @return lyfmanagernumber
	 */
	public String getLyfmanagernumber() {
		return lyfmanagernumber;
	}

	/**
	 * Sets the lyfmanagernumber value for this EmployeeInfo.
	 * 
	 * @param lyfmanagernumber
	 */
	public void setLyfmanagernumber(String lyfmanagernumber) {
		this.lyfmanagernumber = lyfmanagernumber;
	}

	/**
	 * Gets the lyfnationality value for this EmployeeInfo.
	 * 
	 * @return lyfnationality
	 */
	public String getLyfnationality() {
		return lyfnationality;
	}

	/**
	 * Sets the lyfnationality value for this EmployeeInfo.
	 * 
	 * @param lyfnationality
	 */
	public void setLyfnationality(String lyfnationality) {
		this.lyfnationality = lyfnationality;
	}

	/**
	 * Gets the lyforgshort value for this EmployeeInfo.
	 * 
	 * @return lyforgshort
	 */
	public String getLyforgshort() {
		return lyforgshort;
	}

	/**
	 * Sets the lyforgshort value for this EmployeeInfo.
	 * 
	 * @param lyforgshort
	 */
	public void setLyforgshort(String lyforgshort) {
		this.lyforgshort = lyforgshort;
	}

	/**
	 * Gets the lyfpassport value for this EmployeeInfo.
	 * 
	 * @return lyfpassport
	 */
	public String getLyfpassport() {
		return lyfpassport;
	}

	/**
	 * Sets the lyfpassport value for this EmployeeInfo.
	 * 
	 * @param lyfpassport
	 */
	public void setLyfpassport(String lyfpassport) {
		this.lyfpassport = lyfpassport;
	}

	/**
	 * Gets the lyfsex value for this EmployeeInfo.
	 * 
	 * @return lyfsex
	 */
	public String getLyfsex() {
		return lyfsex;
	}

	/**
	 * Sets the lyfsex value for this EmployeeInfo.
	 * 
	 * @param lyfsex
	 */
	public void setLyfsex(String lyfsex) {
		this.lyfsex = lyfsex;
	}

	/**
	 * Gets the lyfstation value for this EmployeeInfo.
	 * 
	 * @return lyfstation
	 */
	public String getLyfstation() {
		return lyfstation;
	}

	/**
	 * Sets the lyfstation value for this EmployeeInfo.
	 * 
	 * @param lyfstation
	 */
	public void setLyfstation(String lyfstation) {
		this.lyfstation = lyfstation;
	}

	/**
	 * Gets the lyfstationcode value for this EmployeeInfo.
	 * 
	 * @return lyfstationcode
	 */
	public String getLyfstationcode() {
		return lyfstationcode;
	}

	/**
	 * Sets the lyfstationcode value for this EmployeeInfo.
	 * 
	 * @param lyfstationcode
	 */
	public void setLyfstationcode(String lyfstationcode) {
		this.lyfstationcode = lyfstationcode;
	}

	/**
	 * Gets the lyftermiday value for this EmployeeInfo.
	 * 
	 * @return lyftermiday
	 */
	public String getLyftermiday() {
		return lyftermiday;
	}

	/**
	 * Sets the lyftermiday value for this EmployeeInfo.
	 * 
	 * @param lyftermiday
	 */
	public void setLyftermiday(String lyftermiday) {
		this.lyftermiday = lyftermiday;
	}

	/**
	 * Gets the lyfwebshort value for this EmployeeInfo.
	 * 
	 * @return lyfwebshort
	 */
	public String getLyfwebshort() {
		return lyfwebshort;
	}

	/**
	 * Sets the lyfwebshort value for this EmployeeInfo.
	 * 
	 * @param lyfwebshort
	 */
	public void setLyfwebshort(String lyfwebshort) {
		this.lyfwebshort = lyfwebshort;
	}

	/**
	 * Gets the lyfworkarea value for this EmployeeInfo.
	 * 
	 * @return lyfworkarea
	 */
	public String getLyfworkarea() {
		return lyfworkarea;
	}

	/**
	 * Sets the lyfworkarea value for this EmployeeInfo.
	 * 
	 * @param lyfworkarea
	 */
	public void setLyfworkarea(String lyfworkarea) {
		this.lyfworkarea = lyfworkarea;
	}

	/**
	 * Gets the lyfworktime value for this EmployeeInfo.
	 * 
	 * @return lyfworktime
	 */
	public String getLyfworktime() {
		return lyfworktime;
	}

	/**
	 * Sets the lyfworktime value for this EmployeeInfo.
	 * 
	 * @param lyfworktime
	 */
	public void setLyfworktime(String lyfworktime) {
		this.lyfworktime = lyfworktime;
	}

	/**
	 * Gets the mail value for this EmployeeInfo.
	 * 
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * Sets the mail value for this EmployeeInfo.
	 * 
	 * @param mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * Gets the mobile value for this EmployeeInfo.
	 * 
	 * @return mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the mobile value for this EmployeeInfo.
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Gets the o value for this EmployeeInfo.
	 * 
	 * @return o
	 */
	public String getO() {
		return o;
	}

	/**
	 * Sets the o value for this EmployeeInfo.
	 * 
	 * @param o
	 */
	public void setO(String o) {
		this.o = o;
	}

	/**
	 * Gets the postalcode value for this EmployeeInfo.
	 * 
	 * @return postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * Sets the postalcode value for this EmployeeInfo.
	 * 
	 * @param postalcode
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * Gets the sn value for this EmployeeInfo.
	 * 
	 * @return sn
	 */
	public String getSn() {
		return sn;
	}

	/**
	 * Sets the sn value for this EmployeeInfo.
	 * 
	 * @param sn
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * Gets the telephonenumber value for this EmployeeInfo.
	 * 
	 * @return telephonenumber
	 */
	public String getTelephonenumber() {
		return telephonenumber;
	}

	/**
	 * Sets the telephonenumber value for this EmployeeInfo.
	 * 
	 * @param telephonenumber
	 */
	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	/**
	 * Gets the telexnumber value for this EmployeeInfo.
	 * 
	 * @return telexnumber
	 */
	public String getTelexnumber() {
		return telexnumber;
	}

	/**
	 * Sets the telexnumber value for this EmployeeInfo.
	 * 
	 * @param telexnumber
	 */
	public void setTelexnumber(String telexnumber) {
		this.telexnumber = telexnumber;
	}

	/**
	 * Gets the uid value for this EmployeeInfo.
	 * 
	 * @return uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Sets the uid value for this EmployeeInfo.
	 * 
	 * @param uid
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	private Object __equalsCalc = null;

	@SuppressWarnings("unused")
	public synchronized boolean equals(Object obj) {
		if (!(obj instanceof EmployeeInfo))
			return false;
		EmployeeInfo other = (EmployeeInfo) obj;
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
				&& ((this.changeType == null && other.getChangeType() == null) || (this.changeType != null && this.changeType
						.equals(other.getChangeType())))
				&& ((this.cn == null && other.getCn() == null) || (this.cn != null && this.cn
						.equals(other.getCn())))
				&& ((this.departmentNumber == null && other
						.getDepartmentNumber() == null) || (this.departmentNumber != null && this.departmentNumber
						.equals(other.getDepartmentNumber())))
				&& ((this.employeeType == null && other.getEmployeeType() == null) || (this.employeeType != null && this.employeeType
						.equals(other.getEmployeeType())))
				&& ((this.erPersonStatus == null && other.getErPersonStatus() == null) || (this.erPersonStatus != null && this.erPersonStatus
						.equals(other.getErPersonStatus())))
				&& ((this.givenname == null && other.getGivenname() == null) || (this.givenname != null && this.givenname
						.equals(other.getGivenname())))
				&& ((this.ilaiyfstate == null && other.getIlaiyfstate() == null) || (this.ilaiyfstate != null && this.ilaiyfstate
						.equals(other.getIlaiyfstate())))
				&& ((this.jobLevel == null && other.getJobLevel() == null) || (this.jobLevel != null && this.jobLevel
						.equals(other.getJobLevel())))
				&& ((this.lyfbirthday == null && other.getLyfbirthday() == null) || (this.lyfbirthday != null && this.lyfbirthday
						.equals(other.getLyfbirthday())))
				&& ((this.lyfchinesepinyin == null && other
						.getLyfchinesepinyin() == null) || (this.lyfchinesepinyin != null && this.lyfchinesepinyin
						.equals(other.getLyfchinesepinyin())))
				&& ((this.lyfcompanynumber == null && other
						.getLyfcompanynumber() == null) || (this.lyfcompanynumber != null && this.lyfcompanynumber
						.equals(other.getLyfcompanynumber())))
				&& ((this.lyfcostcenter == null && other.getLyfcostcenter() == null) || (this.lyfcostcenter != null && this.lyfcostcenter
						.equals(other.getLyfcostcenter())))
				&& ((this.lyfenglishname == null && other.getLyfenglishname() == null) || (this.lyfenglishname != null && this.lyfenglishname
						.equals(other.getLyfenglishname())))
				&& ((this.lyfhireday == null && other.getLyfhireday() == null) || (this.lyfhireday != null && this.lyfhireday
						.equals(other.getLyfhireday())))
				&& ((this.lyfhomeplace == null && other.getLyfhomeplace() == null) || (this.lyfhomeplace != null && this.lyfhomeplace
						.equals(other.getLyfhomeplace())))
				&& ((this.lyfidcard == null && other.getLyfidcard() == null) || (this.lyfidcard != null && this.lyfidcard
						.equals(other.getLyfidcard())))
				&& ((this.lyflevel == null && other.getLyflevel() == null) || (this.lyflevel != null && this.lyflevel
						.equals(other.getLyflevel())))
				&& ((this.lyfmail == null && other.getLyfmail() == null) || (this.lyfmail != null && this.lyfmail
						.equals(other.getLyfmail())))
				&& ((this.lyfmanagernumber == null && other
						.getLyfmanagernumber() == null) || (this.lyfmanagernumber != null && this.lyfmanagernumber
						.equals(other.getLyfmanagernumber())))
				&& ((this.lyfnationality == null && other.getLyfnationality() == null) || (this.lyfnationality != null && this.lyfnationality
						.equals(other.getLyfnationality())))
				&& ((this.lyforgshort == null && other.getLyforgshort() == null) || (this.lyforgshort != null && this.lyforgshort
						.equals(other.getLyforgshort())))
				&& ((this.lyfpassport == null && other.getLyfpassport() == null) || (this.lyfpassport != null && this.lyfpassport
						.equals(other.getLyfpassport())))
				&& ((this.lyfsex == null && other.getLyfsex() == null) || (this.lyfsex != null && this.lyfsex
						.equals(other.getLyfsex())))
				&& ((this.lyfstation == null && other.getLyfstation() == null) || (this.lyfstation != null && this.lyfstation
						.equals(other.getLyfstation())))
				&& ((this.lyfstationcode == null && other.getLyfstationcode() == null) || (this.lyfstationcode != null && this.lyfstationcode
						.equals(other.getLyfstationcode())))
				&& ((this.lyftermiday == null && other.getLyftermiday() == null) || (this.lyftermiday != null && this.lyftermiday
						.equals(other.getLyftermiday())))
				&& ((this.lyfwebshort == null && other.getLyfwebshort() == null) || (this.lyfwebshort != null && this.lyfwebshort
						.equals(other.getLyfwebshort())))
				&& ((this.lyfworkarea == null && other.getLyfworkarea() == null) || (this.lyfworkarea != null && this.lyfworkarea
						.equals(other.getLyfworkarea())))
				&& ((this.lyfworktime == null && other.getLyfworktime() == null) || (this.lyfworktime != null && this.lyfworktime
						.equals(other.getLyfworktime())))
				&& ((this.mail == null && other.getMail() == null) || (this.mail != null && this.mail
						.equals(other.getMail())))
				&& ((this.mobile == null && other.getMobile() == null) || (this.mobile != null && this.mobile
						.equals(other.getMobile())))
				&& ((this.o == null && other.getO() == null) || (this.o != null && this.o
						.equals(other.getO())))
				&& ((this.postalcode == null && other.getPostalcode() == null) || (this.postalcode != null && this.postalcode
						.equals(other.getPostalcode())))
				&& ((this.sn == null && other.getSn() == null) || (this.sn != null && this.sn
						.equals(other.getSn())))
				&& ((this.telephonenumber == null && other.getTelephonenumber() == null) || (this.telephonenumber != null && this.telephonenumber
						.equals(other.getTelephonenumber())))
				&& ((this.telexnumber == null && other.getTelexnumber() == null) || (this.telexnumber != null && this.telexnumber
						.equals(other.getTelexnumber())))
				&& ((this.uid == null && other.getUid() == null) || (this.uid != null && this.uid
						.equals(other.getUid())));
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
		if (getChangeType() != null) {
			_hashCode += getChangeType().hashCode();
		}
		if (getCn() != null) {
			_hashCode += getCn().hashCode();
		}
		if (getDepartmentNumber() != null) {
			_hashCode += getDepartmentNumber().hashCode();
		}
		if (getEmployeeType() != null) {
			_hashCode += getEmployeeType().hashCode();
		}
		if (getErPersonStatus() != null) {
			_hashCode += getErPersonStatus().hashCode();
		}
		if (getGivenname() != null) {
			_hashCode += getGivenname().hashCode();
		}
		if (getIlaiyfstate() != null) {
			_hashCode += getIlaiyfstate().hashCode();
		}
		if (getJobLevel() != null) {
			_hashCode += getJobLevel().hashCode();
		}
		if (getLyfbirthday() != null) {
			_hashCode += getLyfbirthday().hashCode();
		}
		if (getLyfchinesepinyin() != null) {
			_hashCode += getLyfchinesepinyin().hashCode();
		}
		if (getLyfcompanynumber() != null) {
			_hashCode += getLyfcompanynumber().hashCode();
		}
		if (getLyfcostcenter() != null) {
			_hashCode += getLyfcostcenter().hashCode();
		}
		if (getLyfenglishname() != null) {
			_hashCode += getLyfenglishname().hashCode();
		}
		if (getLyfhireday() != null) {
			_hashCode += getLyfhireday().hashCode();
		}
		if (getLyfhomeplace() != null) {
			_hashCode += getLyfhomeplace().hashCode();
		}
		if (getLyfidcard() != null) {
			_hashCode += getLyfidcard().hashCode();
		}
		if (getLyflevel() != null) {
			_hashCode += getLyflevel().hashCode();
		}
		if (getLyfmail() != null) {
			_hashCode += getLyfmail().hashCode();
		}
		if (getLyfmanagernumber() != null) {
			_hashCode += getLyfmanagernumber().hashCode();
		}
		if (getLyfnationality() != null) {
			_hashCode += getLyfnationality().hashCode();
		}
		if (getLyforgshort() != null) {
			_hashCode += getLyforgshort().hashCode();
		}
		if (getLyfpassport() != null) {
			_hashCode += getLyfpassport().hashCode();
		}
		if (getLyfsex() != null) {
			_hashCode += getLyfsex().hashCode();
		}
		if (getLyfstation() != null) {
			_hashCode += getLyfstation().hashCode();
		}
		if (getLyfstationcode() != null) {
			_hashCode += getLyfstationcode().hashCode();
		}
		if (getLyftermiday() != null) {
			_hashCode += getLyftermiday().hashCode();
		}
		if (getLyfwebshort() != null) {
			_hashCode += getLyfwebshort().hashCode();
		}
		if (getLyfworkarea() != null) {
			_hashCode += getLyfworkarea().hashCode();
		}
		if (getLyfworktime() != null) {
			_hashCode += getLyfworktime().hashCode();
		}
		if (getMail() != null) {
			_hashCode += getMail().hashCode();
		}
		if (getMobile() != null) {
			_hashCode += getMobile().hashCode();
		}
		if (getO() != null) {
			_hashCode += getO().hashCode();
		}
		if (getPostalcode() != null) {
			_hashCode += getPostalcode().hashCode();
		}
		if (getSn() != null) {
			_hashCode += getSn().hashCode();
		}
		if (getTelephonenumber() != null) {
			_hashCode += getTelephonenumber().hashCode();
		}
		if (getTelexnumber() != null) {
			_hashCode += getTelexnumber().hashCode();
		}
		if (getUid() != null) {
			_hashCode += getUid().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}

	// Type metadata
	private static TypeDesc typeDesc = new TypeDesc(EmployeeInfo.class, true);

	static {
		typeDesc.setXmlType(new QName("http://bean.lyfwebservice.ibm.com",
				"EmployeeInfo"));
		ElementDesc elemField = new ElementDesc();
		elemField.setFieldName("changeType");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"changeType"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("cn");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"cn"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("departmentNumber");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"departmentNumber"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("employeeType");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"employeeType"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("erPersonStatus");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"erPersonStatus"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("givenname");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"givenname"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("ilaiyfstate");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"ilaiyfstate"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("jobLevel");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"jobLevel"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfbirthday");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfbirthday"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfchinesepinyin");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfchinesepinyin"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfcompanynumber");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfcompanynumber"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfcostcenter");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfcostcenter"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfenglishname");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfenglishname"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfhireday");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfhireday"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfhomeplace");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfhomeplace"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfidcard");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfidcard"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyflevel");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyflevel"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfmail");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfmail"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfmanagernumber");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfmanagernumber"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfnationality");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfnationality"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyforgshort");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyforgshort"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfpassport");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfpassport"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfsex");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfsex"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfstation");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfstation"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfstationcode");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfstationcode"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyftermiday");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyftermiday"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfwebshort");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfwebshort"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfworkarea");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfworkarea"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("lyfworktime");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"lyfworktime"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("mail");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"mail"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("mobile");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"mobile"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("o");
		elemField
				.setXmlName(new QName("http://bean.lyfwebservice.ibm.com", "o"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("postalcode");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"postalcode"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("sn");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"sn"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("telephonenumber");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"telephonenumber"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("telexnumber");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"telexnumber"));
		elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema",
				"string"));
		elemField.setNillable(true);
		typeDesc.addFieldDesc(elemField);
		elemField = new ElementDesc();
		elemField.setFieldName("uid");
		elemField.setXmlName(new QName("http://bean.lyfwebservice.ibm.com",
				"uid"));
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
	public static Serializer getSerializer(String mechType, Class _javaType,
			QName _xmlType) {
		return new BeanSerializer(_javaType, _xmlType, typeDesc);
	}

	/**
	 * Get Custom Deserializer
	 */
	public static Deserializer getDeserializer(String mechType,
			Class _javaType, QName _xmlType) {
		return new BeanDeserializer(_javaType, _xmlType, typeDesc);
	}

}
