package com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.attachment;

import java.util.Map;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 快速引入申请报告 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月16日
 * @version 1.0
 */
public interface FastImportAttachmentService extends Service {

	/**
	 * 上传申请文件
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @param intentionAndSupplierCodes
	 *            意向品+供应商编号
	 * @param attch
	 *            附件实例
	 * @throws Exception
	 */
	public void completeUploadAttachment(String applicationCode,
			String intentionAndSupplierCodes, SupplierAttachmentM attch)
			throws Exception;

	/**
	 * 删除上传的申请文件
	 * 
	 * @param map
	 *            过滤参数
	 */
	public void completeDeleteApplicationFiles(Map<String, Object> map);

}