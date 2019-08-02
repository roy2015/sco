package com.powere2e.sco.action.datamaintenance.inventorydata.concessionreceive;


import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.inventorydata.concessionreceive.ConcessionReceiveService;
import com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive.ConcessionReceive;

/**
 * 让步接收的WEB请求响应类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public class ConcessionReceiveAction extends UploadUtils{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5440523750661603523L;
	private ConcessionReceiveService concessionReceiveService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		concessionReceiveService=(ConcessionReceiveService)ConfigFactory.getInstance().getBean("concessionReceiveService");
	}
	/**
	 * 让步接收列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.inventorydata.concessionreceivedata")
	public void doListConcessionReceive() throws Exception
	{
		Map<String,Object> map=getConcessionReceive().toMap();
			map.put("applicationDate", this.asString("applicationDate"));
			map.put("created", this.asString("created"));
			map.put("updated", this.asString("updated"));
			map.put("marStartDate", this.asDate("marStartDate"));
			map.put("marEndDate", this.asDate("marEndDate"));
		List<ConcessionReceive> list=concessionReceiveService.listConcessionReceive(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 
	 * 显示让步接收列表页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.inventorydata.concessionreceivedata")
	public void doShowConcessionReceive() throws Exception
	{
		this.forwardPage("sco/dataMaintenance/inventoryData/concessionReceive/concessionReceiveGrid.ftl");
	}
	/**
	 * 显示上传让步接收数据界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.inventorydata.concessionreceivedata.upload")
	public void doShowConcessionReceiveDataForm() {
		this.forwardPage("sco/dataMaintenance/inventoryData/concessionReceive/concessionReceiveUploadForm.ftl");
	}
	/**
	 * 上传让步接收数据
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.inventorydata.concessionreceivedata.upload")
	public void doCompleteImportConcessionReceiveData() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null; 
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return ;
		}
		String msg = this.concessionReceiveService.completeImportConcessionReceiveData(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}
	/**
	 * 下载导入模板
	 * @throws Exception 
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.inventorydata.concessionreceivedata.upload")
	public void doDownloadConcessionReceiveDataTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/inventoryData/concessionReceive/concessionReceiveTemplate.xlsx", ExcelUtils.getEncodeFileName("让步接收数据维护模板.xlsx"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private ConcessionReceive getConcessionReceive() throws Exception{
		ConcessionReceive concessionReceive=new ConcessionReceive();
		this.asBean(concessionReceive);
		return concessionReceive;
	}
}
