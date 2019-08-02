package com.powere2e.sco.peripheral.tbpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.service.peripheral.tbpm.GetAccessoryApplicationTbmpService;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateM;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;
import com.powere2e.sco.model.peripheral.tbmp.ApplicationVisitFactoryMx;
import com.powere2e.sco.model.peripheral.tbmp.MeterialApplication;
import com.powere2e.sco.model.peripheral.tbmp.MeterialApplicationAttachment;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;
import com.powere2e.sco.service.impl.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisServiceImpl;
import com.powere2e.sco.service.impl.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateServiceImpl;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;
import com.powere2e.sco.service.impl.merchandiseintention.MerchandiseIntentionServiceImpl;
import com.powere2e.sco.service.impl.merchandiseintention.QuotedCompareServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNewServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.suppliercertificate.SupplierCertificateMServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldupServiceImpl;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.tbpm.GetAccessoryApplicationTbmpService")
public class GetAccessoryApplicationTbmpServiceImpl implements GetAccessoryApplicationTbmpService {

	@Override
	public MeterialApplication getAccessoryApplicationTbmp(String oaApplicationCode) {
		if(StringUtils.isBlank(oaApplicationCode)){
			PeripheralFileUtils.logger.error("TBPM-SCO申请单明细信息获取失败-SCO申请单号: " + oaApplicationCode);
			return null;
		}else{
			oaApplicationCode=oaApplicationCode.trim();
			Map<String, Object> map = new HashMap<String, Object>();
			OaApplication oaApplication = OaApplicationServiceImpl.getInstance().loadOaApplication(oaApplicationCode);
			if (oaApplication != null){
					MeterialApplication meterialApplication=new MeterialApplication();
					List<ApplicationVisitFactoryMx> applicationMxList=new ArrayList<ApplicationVisitFactoryMx>();
					List<MeterialApplicationAttachment> meterialApplicationAttachmentList=new ArrayList<MeterialApplicationAttachment>();
					try {
					map.put("applicationCode", oaApplicationCode);
					List<ApplicationMerchandise> applicationMerchandiseList= MerchandiseOaApplicationServiceImpl.getInstance().findMerchandiseCodeAndSupplierCodeByApplicationCode(map);
					if(applicationMerchandiseList!=null && applicationMerchandiseList.size()>0){
						for(ApplicationMerchandise applicationMerchandise:applicationMerchandiseList){
							ApplicationVisitFactoryMx applicationVisitFactoryMx=new ApplicationVisitFactoryMx();
							map.clear();
							map.put("supplierCode", applicationMerchandise.getSupplierCode());
							applicationVisitFactoryMx.setSupplierCode(applicationMerchandise.getSupplierCode());
							applicationVisitFactoryMx.setApplicationCode(oaApplicationCode);
							Supplier supplier=SupplierServiceImpl.getInstance().loadSupplier(applicationMerchandise.getSupplierCode());
							if(supplier!=null){
							applicationVisitFactoryMx.setSupplierCode(supplier.getSupplierCode());
							applicationVisitFactoryMx.setSupplierName(supplier.getSupplierName());
							applicationVisitFactoryMx.setContacts(supplier.getContacts());
							applicationVisitFactoryMx.setCompanySite(supplier.getCompanySite());
							applicationVisitFactoryMx.setCompanyPhone(supplier.getCompanyPhone());
							}else{
								supplier = SupplierServiceImpl.getInstance().loadIntentionSupplier(applicationMerchandise.getSupplierCode());
								applicationVisitFactoryMx.setSupplierName(supplier.getSupplierName());
								map.clear();
								map.put("excludeIntention", "excludeIntention");
								map.put("intentionSupplierCode", applicationMerchandise.getSupplierCode());
								MerchandiseQuoted merchandiseQuoted=QuotedCompareServiceImpl.getQuotedCompareServiceInstance().queryLastQuoted(map);
								if(merchandiseQuoted!=null){
									applicationVisitFactoryMx.setContacts(merchandiseQuoted.getContactsName());
									applicationVisitFactoryMx.setCompanySite(merchandiseQuoted.getCompanySite());
									applicationVisitFactoryMx.setCompanyPhone(merchandiseQuoted.getContactsPhone());	
								}
							}
							map.clear();
							map.put("merchandiseCode", applicationMerchandise.getMerchandiseCode());
							Merchandise merchandise=MerchandiseServiceImpl.getInstance().loadMerchandise(applicationMerchandise.getMerchandiseCode(),applicationMerchandise.getSupplierCode());
							if(merchandise!=null){
							applicationVisitFactoryMx.setMerchandiseCode(applicationMerchandise.getMerchandiseCode());
							applicationVisitFactoryMx.setMerchandiseName(merchandise.getMerchandiseName());
							applicationVisitFactoryMx.setSupplierName(merchandise.getSupplierName());
							}else{
							map.clear();
							map.put("intentionCode", applicationMerchandise.getMerchandiseCode());
							//List<MerchandiseIntention> merchandiseIntentions=MerchandiseIntentionServiceImpl.getInstance().listMerchandiseIntention(map, null);
							MerchandiseIntention merchandiseIntentions = MerchandiseIntentionServiceImpl.getInstance().loadMerchandiseIntention(applicationMerchandise.getMerchandiseCode());
							//if(merchandiseIntentions!=null &&merchandiseIntentions.size()>0){
							if(merchandiseIntentions!=null){
								applicationVisitFactoryMx.setMerchandiseCode(applicationMerchandise.getMerchandiseCode());
								applicationVisitFactoryMx.setMerchandiseName(merchandiseIntentions.getIntentionName());
								}
							}
							applicationMxList.add(applicationVisitFactoryMx);
						}
						
					}
					
					
				//对象2	
					String action = "downLoadFile_downloadDhFile_6.html?path=";
					
					map.clear();
					map.put("applicationCode", oaApplicationCode);

					String intentionAndSupplierCodes = "";
					List<ApplicationMerchandise> codeList = MerchandiseOaApplicationServiceImpl.getInstance()
							.findMerchandiseCodeAndSupplierCodeByApplicationCode(map);
					if (codeList != null && codeList.size() > 0) {
						for (int i = 0; i < codeList.size(); i++) {
							ApplicationMerchandise eachMerchandise = codeList.get(i);
							if (i < codeList.size() - 1) {
								intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":"
										+ eachMerchandise.getSupplierCode() + ",";
							} else if (i == codeList.size() - 1) {
								intentionAndSupplierCodes += eachMerchandise.getMerchandiseCode() + ":"
										+ eachMerchandise.getSupplierCode();
							}
						}
					}
					try {
						List<ApplicationReportNew> applicationReportNewList= ApplicationReportNewServiceImpl.getInstance().listApplicationReportNew(oaApplicationCode, intentionAndSupplierCodes);
						for (ApplicationReportNew applicationReportNew : applicationReportNewList) {
							if(!StringUtils.isBlank(applicationReportNew.getPath())){
							MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
							meterialApplicationAttachment.setAttachmentName("申请报告_"+applicationReportNew.getIntentionName()+"_"+applicationReportNew.getSupplierName());
							meterialApplicationAttachment.setAttachmentUrl( action + applicationReportNew.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					try {
						List<ApplicationReportOldup> applicationReportOldupList= ApplicationReportOldupServiceImpl.getInstance().listApplicationReportOldup(oaApplicationCode, intentionAndSupplierCodes);
						for (ApplicationReportOldup applicationReportOldup : applicationReportOldupList) {
							if(!StringUtils.isBlank(applicationReportOldup.getPath())){
							MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
							meterialApplicationAttachment.setAttachmentName("申请报告_"+applicationReportOldup.getMerchandiseName()+"_"+applicationReportOldup.getSupplierName());
							meterialApplicationAttachment.setAttachmentUrl( action + applicationReportOldup.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					try {
						List<ApplicationReportAdjustprice> applicationReportAdjustpriceList= ApplicationReportAdjustpriceServiceImpl.getInstance().listApplicationReportAdjustprice(oaApplicationCode, intentionAndSupplierCodes);
						for (ApplicationReportAdjustprice applicationReportAdjustprice : applicationReportAdjustpriceList) {
							if(!StringUtils.isBlank(applicationReportAdjustprice.getPath())){
							MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
							meterialApplicationAttachment.setAttachmentName("申请报告_"+applicationReportAdjustprice.getMerchandiseName()+"_"+applicationReportAdjustprice.getSupplierName());
							meterialApplicationAttachment.setAttachmentUrl( action + applicationReportAdjustprice.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					map.clear();
					map.put("applicationCode", oaApplicationCode);
					List<ApplicationFastadjustprice> applicationFastadjustpriceList= ApplicationFastadjustpriceServiceImpl.getInstance().listApplicationFastadjustprice(map, null);
					for (ApplicationFastadjustprice applicationFastadjustprice : applicationFastadjustpriceList) {
						if(!StringUtils.isBlank(applicationFastadjustprice.getPath())){
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						meterialApplicationAttachment.setAttachmentName((applicationFastadjustprice.getReportFileName()==null||"".equals(applicationFastadjustprice.getReportFileName()))?"":applicationFastadjustprice.getReportFileName().substring(0,applicationFastadjustprice.getReportFileName() .lastIndexOf(".")));
						meterialApplicationAttachment.setAttachmentUrl( action + applicationFastadjustprice.getPath());
						meterialApplicationAttachmentList.add(meterialApplicationAttachment);
						}
					}
					
					map.clear();
					map.put("oaNo", oaApplicationCode);
					map.put("load", oaApplicationCode);
					List<Reports> listReports = ReportsServiceImpl.getInstance().listReports(map, null);
					for (Reports report : listReports) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						meterialApplicationAttachment.setAttachmentName("分析报告_" + report.getReportsTypeName()+"_"+report.getReportsName());
						meterialApplicationAttachment.setAttachmentUrl( action + report.getReportsUrl());
						meterialApplicationAttachmentList.add(meterialApplicationAttachment);
					}
					List<Reports> reportsList = CommitteeReportAnalysisServiceImpl.getInstance().listUploadByApp(oaApplicationCode);
					for (Reports report : reportsList) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						meterialApplicationAttachment.setAttachmentName("分析报告_" + report.getReportsTypeName()+"_"+report.getReportsName());
						meterialApplicationAttachment.setAttachmentUrl( action + report.getReportsUrl());
						meterialApplicationAttachmentList.add(meterialApplicationAttachment);
					}
					/*List<Reports> reportsSGList = CommitteeReportAnalysisServiceImpl.getInstance().listPurOrderByApp(oaApplicationCode);
					for (Reports report : reportsSGList) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						meterialApplicationAttachment.setAttachmentName("申购单_" + report.getReportsName());
						meterialApplicationAttachment.setAttachmentUrl( action + report.getReportsUrl());
						meterialApplicationAttachmentList.add(meterialApplicationAttachment);
					}*/
					map.clear();
					map.put("applicationCode", oaApplicationCode);
					List<SupplierAttachmentM> listSupplierAttachmentM=SupplierAttachmentMServiceImpl.getInstance().listSupplierAttachmentM(map, null);
					for (SupplierAttachmentM supplierAttachmentM : listSupplierAttachmentM) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						if("新品信息登记表".equals(supplierAttachmentM.getAttachmentType())){
							meterialApplicationAttachment.setAttachmentName("供应商附件_" + supplierAttachmentM.getAttachmentType()+"_"+(supplierAttachmentM.getIntentionName()==null?supplierAttachmentM.getMerchandiseName():supplierAttachmentM.getIntentionName())+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
							meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);	
						}
					}
					for (SupplierAttachmentM supplierAttachmentM : listSupplierAttachmentM) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						if("新供应商信息登记表".equals(supplierAttachmentM.getAttachmentType())){
							meterialApplicationAttachment.setAttachmentName("供应商附件_" + supplierAttachmentM.getAttachmentType()+"_"+(supplierAttachmentM.getIntentionName()==null?supplierAttachmentM.getMerchandiseName():supplierAttachmentM.getIntentionName())+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
							meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);	
						}
						
					}
					for (SupplierAttachmentM supplierAttachmentM : listSupplierAttachmentM) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						if("供应商调价申请函".equals(supplierAttachmentM.getAttachmentType())){
							meterialApplicationAttachment.setAttachmentName("供应商附件_" + supplierAttachmentM.getAttachmentType()+"_"+(supplierAttachmentM.getIntentionName()==null?supplierAttachmentM.getMerchandiseName():supplierAttachmentM.getIntentionName())+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
							meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);	
						}
						
					}
					for (SupplierAttachmentM supplierAttachmentM : listSupplierAttachmentM) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						if("感官标准".equals(supplierAttachmentM.getAttachmentType())){
							meterialApplicationAttachment.setAttachmentName("供应商附件_" + supplierAttachmentM.getAttachmentType()+"_"+(supplierAttachmentM.getIntentionName()==null?supplierAttachmentM.getMerchandiseName():supplierAttachmentM.getIntentionName())+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
							meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);	
						}
						
					}
					
					for (SupplierAttachmentM supplierAttachmentM : listSupplierAttachmentM) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
						
						if("其它".equals(supplierAttachmentM.getAttachmentType())){
						meterialApplicationAttachment.setAttachmentName("供应商附件_" + supplierAttachmentM.getAttachmentType()+"_"+supplierAttachmentM.getElseAttachmentType()+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
						meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
						meterialApplicationAttachmentList.add(meterialApplicationAttachment);
						}
					}
					map.clear();
					map.put("applicationCode", oaApplicationCode);
					List<SupplierAttachmentM> supplierAttachmentMList=SupplierAttachmentMServiceImpl.getInstance().listSupplierAttachmentMInFrom(map, null);
					for (SupplierAttachmentM supplierAttachmentM : supplierAttachmentMList) {
						MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
							meterialApplicationAttachment.setAttachmentName("供应商附件_" + ("1".equals(supplierAttachmentM.getTypeCode())?"核算表":"投料表")+"_"+(supplierAttachmentM.getIntentionName()==null?supplierAttachmentM.getMerchandiseName():supplierAttachmentM.getIntentionName())+"_"+(supplierAttachmentM.getSupplierName()==null?supplierAttachmentM.getIntentionSupplierName():supplierAttachmentM.getSupplierName()));
							meterialApplicationAttachment.setAttachmentUrl( action + supplierAttachmentM.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);	
					}
					map.clear();
					map.put("applicationCode", oaApplicationCode);
					List<ApplicationMerchandise> applicationMerchandises = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
					map.put("list", applicationMerchandises);
					List<SupplierCertificateM> supplierCertificateMList = SupplierCertificateMServiceImpl.getInstance().listSupplierCertificateM(map, null);
					for (SupplierCertificateM supplierCertificateM : supplierCertificateMList) {
						SupplierCertificate supplierCertificate = SupplierCertificateServiceImpl.getInstance().loadCertificateByCode(supplierCertificateM.getCertificateCode());
						if (supplierCertificate != null){
							MeterialApplicationAttachment meterialApplicationAttachment=new MeterialApplicationAttachment();
							meterialApplicationAttachment.setAttachmentName("供应商证件_" + supplierCertificate.getCertificateTypeName()+"_"+supplierCertificateM.getSupplierName());
							meterialApplicationAttachment.setAttachmentUrl( action + supplierCertificate.getPath());
							meterialApplicationAttachmentList.add(meterialApplicationAttachment);
						}
					}
						meterialApplication.setApplicationMxList(applicationMxList);
						meterialApplication.setMeterialApplicationAttachmentList(meterialApplicationAttachmentList);
						PeripheralFileUtils.logger.info("TBPM-SCO申请单明细信息获取成功-SCO申请单号: " + oaApplicationCode);
				} catch (Exception e) {
					PeripheralFileUtils.logger.error("TBPM-SCO申请单明细信息获取失败-SCO申请单号: " + oaApplicationCode);
					PeripheralFileUtils.logger.error(e);
					e.printStackTrace();
				}
					
				return meterialApplication;	
			}else{
				PeripheralFileUtils.logger.error("TBPM-SCO申请单明细信息获取失败-找不到对应的申请单");
				return null;
			}
			
		}
		
	}


}
