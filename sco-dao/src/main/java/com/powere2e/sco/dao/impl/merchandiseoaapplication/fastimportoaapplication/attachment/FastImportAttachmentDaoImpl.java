package com.powere2e.sco.dao.impl.merchandiseoaapplication.fastimportoaapplication.attachment;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.attachment.FastImportAttachmentDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 商品快速引进申请文件 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class FastImportAttachmentDaoImpl extends DaoImpl implements
		FastImportAttachmentDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8920746831251108221L;

	@Override
	public SupplierAttachmentM loadUploadAttachment(Map<String, Object> map) {
		return (SupplierAttachmentM) this.get(FastImportAttachmentDao.class, "loadUploadAttachment", map);
	}

	@Override
	public void completeUploadAttachment(Map<String, Object> map) {
		this.update(FastImportAttachmentDao.class, "completeUploadAttachment", map);
	}

}