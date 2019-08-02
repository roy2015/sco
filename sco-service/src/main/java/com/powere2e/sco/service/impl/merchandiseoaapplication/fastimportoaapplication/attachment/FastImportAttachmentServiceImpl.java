package com.powere2e.sco.service.impl.merchandiseoaapplication.fastimportoaapplication.attachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.attachment.FastImportAttachmentDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.attachment.FastImportAttachmentService;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceServiceImpl;

/**
 * 快速引入申请报告 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class FastImportAttachmentServiceImpl extends ServiceImpl implements
		FastImportAttachmentService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994524607664835054L;
	private FastImportAttachmentDao fastImportAttachmentDao;// 快速引进申请文件
	private ApplicationFastadjustpriceDao fastAdjustpriceDao;// 快速调价

	public FastImportAttachmentDao getFastImportAttachmentDao() {
		return fastImportAttachmentDao;
	}

	public void setFastImportAttachmentDao(
			FastImportAttachmentDao fastImportAttachmentDao) {
		this.fastImportAttachmentDao = fastImportAttachmentDao;
	}

	public ApplicationFastadjustpriceDao getFastAdjustpriceDao() {
		return fastAdjustpriceDao;
	}

	public void setFastAdjustpriceDao(
			ApplicationFastadjustpriceDao fastAdjustpriceDao) {
		this.fastAdjustpriceDao = fastAdjustpriceDao;
	}

	@Override
	public void completeUploadAttachment(String applicationCode, String intentionAndSupplierCodes,
			SupplierAttachmentM attachment) throws Exception {
		Map<String, Object> map = attachment.toMap();

		this.validateApplicationStatus(applicationCode,
				BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT
						.toString(), intentionAndSupplierCodes);
		
		// 删除原有文件
		SupplierAttachmentM existAttach = this.fastImportAttachmentDao
				.loadUploadAttachment(map);
		if (existAttach != null) {
			FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(
					existAttach.getPath()));
		}
		//保存或更新上传的申请文件
		this.fastImportAttachmentDao.completeUploadAttachment(map);
	}

	@Override
	public void completeDeleteApplicationFiles(Map<String, Object> map) {
		// 根据参数去查询所有上传的文件信息，然后根据上传的路径
		try {
			List<ApplicationFastadjustprice> list = ApplicationFastadjustpriceServiceImpl.getInstance().
					listApplicationFiles(map, null);
			fastAdjustpriceDao.deleteApplicationFiles(map);
			//删除服务器上的文件
			for (ApplicationFastadjustprice aj : list) {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(aj.getPath()));
			}
		} catch (Exception e) {
			throw new EscmException("删除申请文件出错!");
		}
	}

	/**
	 * 校验申请单状态
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @param oaType
	 *            审批类型
	 * @param intentionAndSupplierCodes
	 *            意向品编号和供应商编号组
	 */
	private void validateApplicationStatus(String applicationCode,
			String oaType, String intentionAndSupplierCodes) {
		String receiptInfo;
		try {
			receiptInfo = MerchandiseOaApplicationServiceImpl.getInstance()
					.getIntentionOaApplicationReceiptInfo(applicationCode,
							intentionAndSupplierCodes, oaType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
		}
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equalsIgnoreCase(receiptInfo)) {
			throw new EscmException("勾选的数据做了不同的OA申请，不能进行后续操作");
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString().equalsIgnoreCase(receiptInfo)) {
			throw new EscmException("选择的数据不是\"草稿\"或\"驳回\"状态，无法修改");
		}
	}
	
}