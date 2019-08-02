package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.attachment;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 商品快速引进申请文件 DAO接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public interface FastImportAttachmentDao extends Dao {

	/**
	 * 查询上传的申请文件
	 * 
	 * @param map
	 *            查询参数
	 * @return 返回实例
	 */
	public SupplierAttachmentM loadUploadAttachment(Map<String, Object> map);

	/**
	 * 保存或更新上传的申请文件
	 * 
	 * @param map
	 *            相关参数
	 */
	public void completeUploadAttachment(Map<String, Object> map);


}