package com.powere2e.sco.peripheral.tbpm;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.interfaces.service.peripheral.tbpm.UpdateAccessoryOaApplicationLinkTbmpService;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesign;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactory;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;
import com.powere2e.sco.service.impl.peripheral.webservice.ApplicationPackageDesignServiceImpl;
import com.powere2e.sco.service.impl.peripheral.webservice.ApplicationVisitFactoryServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.tbpm.UpdateAccessoryOaApplicationLinkTbmpService")
public class UpdateAccessoryOaApplicationLinkTbpmServiceImpl implements UpdateAccessoryOaApplicationLinkTbmpService {

	public String updateAccessoryOaApplicationLinkTbmp(String oaApplicationCode, String applicationType, String applicationLink) {
		String result = "error";
		if (!StringUtils.isBlank(oaApplicationCode) && !StringUtils.isBlank(applicationType) && !StringUtils.isBlank(applicationLink)) {
			oaApplicationCode = oaApplicationCode.trim();
			if ("新品引进".equals(applicationType) || "巡厂申请".equals(applicationType) || "包装设计申请".equals(applicationType)) {
				if ("新品引进".equals(applicationType)) {
					try {
						OaApplication oaApplication = new OaApplication();
						oaApplication=OaApplicationServiceImpl.getInstance().loadOaApplication(oaApplicationCode);
						oaApplication.setApplicationLink(applicationLink);
						Integer count = OaApplicationServiceImpl.getInstance().updateOaApplication(oaApplication);
						if (count != 0) {
							result = "success";
							PeripheralFileUtils.logger.info("TBPM-审批环节-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						PeripheralFileUtils.logger.error("TBPM-审批环节-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
						e.printStackTrace();
					}
				}
				if ("巡厂申请".equals(applicationType)) {
					try {
						ApplicationVisitFactory applicationVisitFactory = new ApplicationVisitFactory();
						applicationVisitFactory=ApplicationVisitFactoryServiceImpl.getInstance().loadApplicationVisitFactory(oaApplicationCode);
						applicationVisitFactory.setApplicationLink(applicationLink);
						ApplicationVisitFactoryServiceImpl.getInstance().updateApplicationVisitFactory(applicationVisitFactory);
						result = "success";
						PeripheralFileUtils.logger.info("TBPM-审批环节-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						PeripheralFileUtils.logger.error("TBPM-审批环节-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
						e.printStackTrace();
					}
				}
				if ("包装设计申请".equals(applicationType)) {
					try {
						ApplicationPackageDesign applicationPackageDesign = new ApplicationPackageDesign();
						applicationPackageDesign=ApplicationPackageDesignServiceImpl.getInstance().loadApplicationPackageDesign(oaApplicationCode);
						applicationPackageDesign.setApplicationLink(applicationLink);
						ApplicationPackageDesignServiceImpl.getInstance().updateApplicationPackageDesign(applicationPackageDesign);
						result = "success";
						PeripheralFileUtils.logger.info("TBPM-审批环节-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						PeripheralFileUtils.logger.error("TBPM-审批环节-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
						PeripheralFileUtils.logger.error(e);
						e.printStackTrace();
					}
				}
			} else {
				PeripheralFileUtils.logger.error("TBPM-审批环节-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-审批环节:" + applicationLink);
			}
		}
		return result;

	}

}
