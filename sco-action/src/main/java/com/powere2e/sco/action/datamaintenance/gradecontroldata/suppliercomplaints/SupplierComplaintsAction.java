package com.powere2e.sco.action.datamaintenance.gradecontroldata.suppliercomplaints;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaintsService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaints;

/**
 * 供应商年度千万元客诉的WEB请求响应类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierComplaintsAction extends UploadUtils{
	/**
	 * 
	 */
	private static final long serialVersionUID = 453516140266122113L;
	private SupplierComplaintsService supplierComplaintsService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierComplaintsService=(SupplierComplaintsService)ConfigFactory.getInstance().getBean("supplierComplaintsService");
	}
	/**
	 * 供应商年度千万元客诉列表显示
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints")
	public void doShowSupplierComplaints() throws Exception
	{
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierComplaints/supplierComplaintsGrid.ftl");
	}
	/**
	 * 供应商年度千万元客诉列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints")
	public void doListSupplierComplaints() throws Exception
	{
		Map<String,Object> map=getSupplierComplaints().toMap();
			map.put("created", this.asString("created"));
			map.put("updated", this.asString("updated"));
			map.put("marStartDate",this.asString("marStartDate")==null?null:this.asInteger("marStartDate"));
			map.put("marEndDate", this.asString("marEndDate")==null?null:this.asInteger("marEndDate"));
		List<SupplierComplaints> list=supplierComplaintsService.listSupplierComplaints(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 显示上传供应商年度千万元客诉界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.upload")
	public void doShowSupplierComplaintsForm() {
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierComplaints/supplierComplaintsUploadForm.ftl");
	}
	/**
	 * 上传供应商年度千万元客诉表
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.upload")
	public void doCompleteImportSupplierComplaintsData() {
		List<File> fileList = this.doUpload("XLSX");
		String year = this.asString("Year");
		File file = null; 
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return ;
		}
		String msg =this.supplierComplaintsService.completeImportSupplierComplaintsData(file,year);
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
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliercomplaints.upload")
	public void doDownloadSupplierComplaintsDataTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/gradecontrolData/supplierComplaints/supplierComplaintsTemplate.xlsx", ExcelUtils.getEncodeFileName("客诉记录数据维护模板.xlsx"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private SupplierComplaints getSupplierComplaints() throws Exception{
		SupplierComplaints supplierComplaints=new SupplierComplaints();
		this.asBean(supplierComplaints);
		return supplierComplaints;
	}
}
