package com.powere2e.sco.peripheral.webservice.sco;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.common.service.BusinessConstants.ApplicationStatus;
import com.powere2e.sco.interfaces.service.peripheral.webservice.GetOaApplicationAttachmentService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedScan;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationReportAccessory;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.QuotedElectronicOfScan;
import com.powere2e.sco.model.accessoryoaapplication.supplierattachment.SupplierAttachmentA;
import com.powere2e.sco.model.accessoryoaapplication.suppliercertificate.SupplierCertificateA;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryQuotedScanServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.ApplicationReportAccessoryServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.supplierattachment.QuotedElectronicOfScanServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.supplierattachment.SupplierAttachmentAServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.suppliercertificate.SupplierCertificateAServiceImpl;
import com.powere2e.sco.service.impl.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateServiceImpl;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.webservice.GetOaApplicationAttachmentService")
public class GetOaApplicationAttachmentServiceImpl implements GetOaApplicationAttachmentService {

	public String getOaApplicationAttachment(String oaApplicationCode) {
		if (StringUtils.isBlank(oaApplicationCode)) {
			PeripheralFileUtils.logger.info("OA获取SCO申请单信息失败-SCO申请单号: " + oaApplicationCode);
			return "error";
		} else {
			oaApplicationCode = oaApplicationCode.trim();
			Map<String, Object> map = new HashMap<String, Object>();
			OaApplication oaApplication = OaApplicationServiceImpl.getInstance().loadOaApplication(oaApplicationCode);
			if (oaApplication != null) {
				if (ApplicationStatus.YX.toString().equals(oaApplication.getApplicationStatus())) {
					String result = "";
					try {
						String action = "downLoadFile_downloadDhFile_6.html?path=";
						map.clear();
						map.put("applicationCode", oaApplicationCode);
						ApplicationReportAccessory applicationReportAccessory = ApplicationReportAccessoryServiceImpl.getInstance().loadApplicationReportAccessory(oaApplicationCode);
						result += "申请报告_" + (applicationReportAccessory.getContent()==null?applicationReportAccessory.getPurchaseName():applicationReportAccessory.getContent()) + ":" + action + applicationReportAccessory.getPath() + ",";
						map.clear();
						map.put("oaNo", oaApplicationCode);
						map.put("load", oaApplicationCode);
						List<Reports> listReports = ReportsServiceImpl.getInstance().listReports(map, null);
						for (Reports report : listReports) {
							result += "关联的报表_" + report.getReportsTypeName() + ":" + action + report.getReportsUrl() + ",";
						}
						List<Reports> reportsList = CommitteeReportAnalysisServiceImpl.getInstance().listUploadByApp(oaApplicationCode);
						for (Reports report : reportsList) {
							result += "本地报表:" + action + report.getReportsUrl() + ",";
						}
						List<Reports> reportsSGList = CommitteeReportAnalysisServiceImpl.getInstance().listPurOrderByApp(oaApplicationCode);
						for (Reports report : reportsSGList) {
							result += "申购单_" + report.getReportsName() + ":" + action + report.getReportsUrl() + ",";
						}
						map.clear();
						map.put("applicationCode", oaApplicationCode);
						List<SupplierAttachmentA> listSupplierAttachmentA = SupplierAttachmentAServiceImpl.getInstance().listSupplierAttachmentA(map, null);
						for (SupplierAttachmentA supplierAttachmentA : listSupplierAttachmentA) {
							if ("其它".equals(supplierAttachmentA.getFileType())) {
								result += "供应商附件_" + supplierAttachmentA.getElseType() + ":" + action + supplierAttachmentA.getPath() + ",";
							} else {
								result += "供应商附件_" + supplierAttachmentA.getFileType() + ":" + action + supplierAttachmentA.getPath() + ",";
							}
						}
						map.clear();
						map.put("applicationCode", oaApplicationCode);
						List<QuotedElectronicOfScan> quotedElectronicOfScanList = QuotedElectronicOfScanServiceImpl.getInstance().listQuotedElectronicOfScan(map, null);
						for (QuotedElectronicOfScan quotedElectronicOfScan : quotedElectronicOfScanList) {
							AccessoryQuotedScan accessoryQuotedScan = AccessoryQuotedScanServiceImpl.getInstance().loadAccessoryQuotedScan(quotedElectronicOfScan.getQuotedCodeScan());
							if (accessoryQuotedScan != null)
								result += "扫描版报价单" + ":" + action + accessoryQuotedScan.getPath() + ",";
						}

						map.clear();
						map.put("applicationCode", oaApplicationCode);
						List<SupplierCertificateA> supplierCertificateAList = SupplierCertificateAServiceImpl.getInstance().listSupplierCertificateA(map, null);
						for (SupplierCertificateA supplierCertificateA : supplierCertificateAList) {
							SupplierCertificate supplierCertificate = SupplierCertificateServiceImpl.getInstance().loadCertificateByCode(supplierCertificateA.getCertificateCode());
							if (supplierCertificate != null)
								result += "供应商证件_" + supplierCertificate.getCertificateTypeName()+"_"+(supplierCertificateA.getSupplierName()==null?supplierCertificateA.getIntentionSupplierName():supplierCertificateA.getSupplierName()) + ":" + action + supplierCertificate.getPath() + ",";
						}
						result = result.substring(0, result.length() - 1);

						PeripheralFileUtils.logger.info("OA获取SCO申请单信息成功-SCO申请单号: " + oaApplicationCode);
					} catch (Exception e) {
						result = "";
						PeripheralFileUtils.logger.error("OA获取SCO申请单信息失败-SCO申请单号: " + oaApplicationCode);
						PeripheralFileUtils.logger.error(e);
						e.printStackTrace();
					}
					return result;
				} else {
					PeripheralFileUtils.logger.info("OA获取SCO申请单信息失败-SCO申请单号: " + oaApplicationCode + "_申请状态不是允许OA同步");
					return "error";
				}
			} else {
				PeripheralFileUtils.logger.info("OA获取SCO申请单信息失败-SCO申请单号: " + oaApplicationCode + "_找不到对应的申请单");
				return "error";
			}
		}
	}
}
