package com.powere2e.sco.peripheral.webservice.sco;

import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.interfaces.service.peripheral.webservice.UpdateAccessoryOaApplicationTbmpService;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.webservice.UpdateAccessoryOaApplicationTbmpService")
public class UpdateAccessoryOaApplicationTbpmServiceImpl implements UpdateAccessoryOaApplicationTbmpService {

	public String UpdateAccessoryOaApplicationTbmp(String oaApplicationCode, String applicationType, String applicationLink) {
		String result = "error";
		if (!StringUtils.isBlank(oaApplicationCode) && !StringUtils.isBlank(applicationType) && !StringUtils.isBlank(applicationLink)) {
			if("新品引进".equals(applicationType)||"巡厂申请".equals(applicationType)||"包装设计申请".equals(applicationType)){
			try {
				OaApplication oaApplication = new OaApplication();
				oaApplication.setApplicationCode(oaApplicationCode);
				oaApplication.setApplicationType(applicationType);
				oaApplication.setApplicationLink(applicationLink);
				Integer count=OaApplicationServiceImpl.getInstance().updateOaApplication(oaApplication);
				if(count!=0){
					result = "success";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return result;

	}

	@Override
	public String UpdateAccessoryOaApplicationTbmp(String oaApplicationCode, String applicationType, String tbmpType, String tbmpCode, String Status, Date date) {
		String result = "error";
		if (!StringUtils.isBlank(oaApplicationCode) && !StringUtils.isBlank(applicationType) && !StringUtils.isBlank(tbmpType) && !StringUtils.isBlank(tbmpCode) && date != null) {
			if("新品引进".equals(applicationType)||"老品新上".equals(applicationType)||"快速调价".equals(applicationType)||"正常调价".equals(applicationType)||"新品引进的巡厂申请".equals(applicationType)||"新品引进的包装设计申请".equals(applicationType)||"新品快速引进的申请单审批状态信息".equals(applicationType)){
			try {
				OaApplication oaApplication = new OaApplication();
				oaApplication.setApplicationCode(oaApplicationCode);
				oaApplication.setApplicationType(applicationType);
				oaApplication.setApplicationStatus(Status);
				oaApplication.setApplicationStatusDate(date);
				Integer count=OaApplicationServiceImpl.getInstance().updateOaApplication(oaApplication);
				if(count!=0){
					result = "success";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		return result;
	}
}
