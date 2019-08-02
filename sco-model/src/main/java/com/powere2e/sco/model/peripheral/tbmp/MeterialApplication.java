package com.powere2e.sco.model.peripheral.tbmp;

import java.util.List;

import com.powere2e.frame.server.model.AppModel;

public class MeterialApplication extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1907500055140793826L;

	List<ApplicationVisitFactoryMx> applicationMxList;
	
	List<MeterialApplicationAttachment> meterialApplicationAttachmentList;

	public List<ApplicationVisitFactoryMx> getApplicationMxList() {
		return applicationMxList;
	}

	public void setApplicationMxList(List<ApplicationVisitFactoryMx> applicationMxList) {
		this.applicationMxList = applicationMxList;
	}

	public List<MeterialApplicationAttachment> getMeterialApplicationAttachmentList() {
		return meterialApplicationAttachmentList;
	}

	public void setMeterialApplicationAttachmentList(List<MeterialApplicationAttachment> meterialApplicationAttachmentList) {
		this.meterialApplicationAttachmentList = meterialApplicationAttachmentList;
	}

}
