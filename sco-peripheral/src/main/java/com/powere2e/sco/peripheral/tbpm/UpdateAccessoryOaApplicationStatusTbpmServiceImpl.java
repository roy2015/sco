package com.powere2e.sco.peripheral.tbpm;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.common.service.BusinessConstants.MerchandiseApplicationStatus;
import com.powere2e.sco.interfaces.service.peripheral.tbpm.UpdateAccessoryOaApplicationStatusTbmpService;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesign;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactory;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.accessoryoaapplication.OaApplicationServiceImpl;
import com.powere2e.sco.service.impl.peripheral.webservice.ApplicationPackageDesignServiceImpl;
import com.powere2e.sco.service.impl.peripheral.webservice.ApplicationVisitFactoryServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.tbpm.UpdateAccessoryOaApplicationStatusTbmpService")
public class UpdateAccessoryOaApplicationStatusTbpmServiceImpl implements UpdateAccessoryOaApplicationStatusTbmpService {

	@Override
	public String updateAccessoryOaApplicationStatusTbmp(String oaApplicationCode, String applicationType, String tbmpCode, String Status, String date) {
		String result = "error";
		if (!StringUtils.isBlank(oaApplicationCode) && !StringUtils.isBlank(applicationType) && !StringUtils.isBlank(tbmpCode) &&!StringUtils.isBlank(Status) && date != null) {
			oaApplicationCode = oaApplicationCode.trim();
			if ("新品引进".equals(applicationType) || "老品新上".equals(applicationType) || "快速调价".equals(applicationType) || "正常调价".equals(applicationType) || "新品引进的巡厂申请".equals(applicationType)
					|| "新品引进的包装设计申请".equals(applicationType) || "新品快速引进".equals(applicationType)) {
				if ("新品引进".equals(applicationType) || "老品新上".equals(applicationType) || "快速调价".equals(applicationType) || "正常调价".equals(applicationType) || "新品快速引进".equals(applicationType)) {
					try {
						SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date UpdateDate = dateFormater.parse(date);
						OaApplication oaApplication = new OaApplication();
						oaApplication = OaApplicationServiceImpl.getInstance().loadOaApplication(oaApplicationCode);
						oaApplication.setOaApplicationCode(tbmpCode);
						oaApplication.setApplicationStatus(Status);
						oaApplication.setApplicationStatusDate(UpdateDate);
						if (Status.equals(MerchandiseApplicationStatus.SPTG.toString())) {
							oaApplication.setOaApproveDate(UpdateDate);
						}
						if (Status.equals(MerchandiseApplicationStatus.SPZ.toString())) {
							oaApplication.setOaInitiateDate(UpdateDate);
						}
						Integer count = OaApplicationServiceImpl.getInstance().updateOaApplication(oaApplication);
						if (count != 0) {
							result = "success";
							PeripheralFileUtils.logger.info("TBPM审批状态审批状态-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "流程名称:" + applicationType + "-"+ "-" + "申请单号:" + tbmpCode + "-"+ "TBPM审批状态:" + Status + "-"+ "状态时间:" + date);
						}
					} catch (Exception e) {
						PeripheralFileUtils.logger.error("TBPM审批状态审批状态-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "流程名称:" + applicationType + "-"+ "-" + "申请单号:" + tbmpCode + "-"+ "TBPM审批状态:" + Status + "-"+ "状态时间:" + date);
						PeripheralFileUtils.logger.error(e);
						e.printStackTrace();
					}
				}

				if ("新品引进的巡厂申请".equals(applicationType)) {
					try {
						SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date UpdateDate = dateFormater.parse(date);
						ApplicationVisitFactory applicationVisitFactory = new ApplicationVisitFactory();
						applicationVisitFactory = ApplicationVisitFactoryServiceImpl.getInstance().loadApplicationVisitFactory(oaApplicationCode);
						applicationVisitFactory.setOaApplicationCode(tbmpCode);
						applicationVisitFactory.setApplicationStatus(Status);
						applicationVisitFactory.setApplicationStatusDate(UpdateDate);
						if (Status.equals(MerchandiseApplicationStatus.SPTG.toString())) {
							applicationVisitFactory.setOaApproveDate(UpdateDate);
						}
						if (Status.equals(MerchandiseApplicationStatus.SPZ.toString())) {
							applicationVisitFactory.setOaInitiateDate(UpdateDate);
						}
						ApplicationVisitFactoryServiceImpl.getInstance().updateApplicationVisitFactory(applicationVisitFactory);
						result = "success";
						PeripheralFileUtils.logger.info("TBPM-审批状态审批状态-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode+ "-" + "TBPM-审批状态:" + Status + "-"+ "TBPM-状态时间:" + date);

					} catch (Exception e) {
						PeripheralFileUtils.logger.error("TBPM-审批状态审批状态-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode + "-"+ "TBPM-审批状态:" + Status + "-"+ "TBPM-状态时间:" + date);
						PeripheralFileUtils.logger.error(e);
						e.printStackTrace();
					}
				}

				if ("新品引进的包装设计申请".equals(applicationType)) {
					try {
						SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Date UpdateDate = dateFormater.parse(date);
						ApplicationPackageDesign applicationPackageDesign = new ApplicationPackageDesign();
						applicationPackageDesign = ApplicationPackageDesignServiceImpl.getInstance().loadApplicationPackageDesign(oaApplicationCode);
						applicationPackageDesign.setOaApplicationCode(tbmpCode);
						applicationPackageDesign.setApplicationStatus(Status);
						applicationPackageDesign.setApplicationStatusDate(UpdateDate);
						if (Status.equals(MerchandiseApplicationStatus.SPTG.toString())) {
							applicationPackageDesign.setOaApproveDate(UpdateDate);
						}
						if (Status.equals(MerchandiseApplicationStatus.SPZ.toString())) {
							applicationPackageDesign.setOaInitiateDate(UpdateDate);
						}
						ApplicationPackageDesignServiceImpl.getInstance().updateApplicationPackageDesign(applicationPackageDesign);
						result = "success";
						PeripheralFileUtils.logger.info("TBPM-审批状态审批状态-更新成功-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode + "-" + "TBPM-审批状态:" + Status + "-" + "TBPM-状态时间:" + date);
					} catch (Exception e) {
						PeripheralFileUtils.logger.error("TBPM-审批状态审批状态-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode + "-"+ "TBPM-审批状态:" + Status + "-"+ "TBPM-状态时间:" + date);
						PeripheralFileUtils.logger.error(e);
						e.printStackTrace();
					}
				}
			} else {
				PeripheralFileUtils.logger.error("TBPM-审批状态审批状态-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode + "-"+ "TBPM-审批状态:" + Status + "-"+ "TBPM-状态时间:" + date);
			}

		} else {
			PeripheralFileUtils.logger.error("TBPM-审批状态审批状态-更新失败-SCO申请单号:" + oaApplicationCode + "-" + "TBPM-流程名称:" + applicationType + "-" + "TBPM-申请单号:" + tbmpCode + "TBPM-审批状态:" + Status + "TBPM-状态时间:" + date);
		}
		return result;
	}
}
