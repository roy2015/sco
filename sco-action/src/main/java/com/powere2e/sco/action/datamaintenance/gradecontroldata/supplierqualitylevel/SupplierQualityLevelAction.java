package com.powere2e.sco.action.datamaintenance.gradecontroldata.supplierqualitylevel;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevelService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevel;

/**
 * 供应商质量星级的WEB请求响应类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierQualityLevelAction extends UploadUtils{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7337872553189653904L;
	private SupplierQualityLevelService supplierQualityLevelService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierQualityLevelService=(SupplierQualityLevelService)ConfigFactory.getInstance().getBean("supplierQualityLevelService");
	}
	/**
	 * 供应商质量星级列表显示
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.supplierqualitylevel")
	public void doShowListSupplierQualityLevel() throws Exception
	{
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierQualityLevel/supplierQualityLevelGrid.ftl");
	}
	/**
	 * 供应商质量星级列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.supplierqualitylevel")
	public void doListSupplierQualityLevel() throws Exception
	{
		Map<String,Object> map=getSupplierQualityLevel().toMap();
			map.put("created", this.asString("created"));
			map.put("updated", this.asString("updated"));
			map.put("marStartDate",this.asString("marStartDate")==null?null:this.asInteger("marStartDate"));
			map.put("marEndDate", this.asString("marEndDate")==null?null:this.asInteger("marEndDate"));
		List<SupplierQualityLevel> list=supplierQualityLevelService.listSupplierQualityLevel(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 显示上传质量星级列表界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.supplierqualitylevel.upload")
	public void doShowSupplierQualityLevelForm() {
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierQualityLevel/supplierQualityLevelUploadForm.ftl");
	}
	/**
	 * 上传质量星级列表
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.supplierqualitylevel.upload")
	public void doCompleteImportSupplierQualityLevelData() {
		List<File> fileList = this.doUpload("XLSX");
		String year = this.asString("Year");
		File file = null; 
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return ;
		}
		String msg = this.supplierQualityLevelService.completeImportSupplierQualityLevelData(file,year);
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
		this.forwardDownload("excel/sco/dataMaintenance/gradecontrolData/supplierQualityLevel/supplierQualityLevelTemplate.xlsx", ExcelUtils.getEncodeFileName("质量星级数据维护模板.xlsx"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private SupplierQualityLevel getSupplierQualityLevel() throws Exception{
		SupplierQualityLevel supplierQualityLevel=new SupplierQualityLevel();
		this.asBean(supplierQualityLevel);
		return supplierQualityLevel;
	}
}
