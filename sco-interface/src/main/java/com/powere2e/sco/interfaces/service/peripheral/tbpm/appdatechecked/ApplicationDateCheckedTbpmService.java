package com.powere2e.sco.interfaces.service.peripheral.tbpm.appdatechecked;

import javax.jws.WebService;

/**
 * 审批日期接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年12月29日
 * @version 1.0
 */
@WebService
public interface ApplicationDateCheckedTbpmService {

	/**
	 * 审批日期进度检查
	 * 
	 * @param oaApplicationCode
	 *            SCO申请单号
	 * @param intentionCode
	 *            SCO意向品编号
	 * @param supplierCode
	 *            SCO供应商编号
	 * @param dateName
	 *            日期名称
	 * @param dateStr
	 *            该日期名称对应的日期(字符串格式:YYYYMMDD)
	 * @return "success" OR "error"
	 */
	public String completeCheckedApplicationDate(String oaApplicationCode,
			String intentionCode, String supplierCode, String dateName,
			String dateStr);

}
