package com.powere2e.sco.model.peripheral.tbmp;


import com.powere2e.frame.server.model.AppModel;

public class MeterialApplicationAttachment extends AppModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8633219115933094705L;
	
	private String attachmentName;
	private String attachmentUrl;
	
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	

}
