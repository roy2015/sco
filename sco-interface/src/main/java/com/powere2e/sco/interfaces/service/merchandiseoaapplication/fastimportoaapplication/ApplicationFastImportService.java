package com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication;

import java.util.List;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;

/**
 * 商品快速引进 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public interface ApplicationFastImportService extends Service {

	/**
	 * 校验申请单状态
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @param oaType
	 *            审批类型
	 * @param intentionAndSupplierCodes
	 *            意向品编号和供应商编号组
	 * 
	 */
	public void validateApplicationStatus(String applicationCode,
			String oaType, String intentionAndSupplierCodes);

	/**
	 * 根据审批单号删除快速申请
	 * 
	 * @param applicationCodes
	 *            申请单号
	 * @param 是否有不能删除的申请单
	 */
	public String completeDelFastImport(String applicationCodes);

	/**
	 * 导出快速引进数据
	 * 
	 * @param list
	 *            快速引进数据
	 * @param out
	 *            输出流
	 */
	public void exportFastImportToExcel(List<MerchandiseIntention> list,
			ServletOutputStream out);

}