package com.powere2e.sco.action.datamaintenance.gradecontroldata.suppliervisitfactory;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactoryService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;

/**
 * 供应商年度巡厂得分的WEB请求响应类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class SupplierVisitFactoryAction extends UploadUtils{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1546995176384019725L;
	private SupplierVisitFactoryService supplierVisitFactoryService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierVisitFactoryService=(SupplierVisitFactoryService)ConfigFactory.getInstance().getBean("supplierVisitFactoryService");
	}
	/**
	 * 供应商年度巡厂得分列表显示
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory")
	public void doShowSupplierVisitFactory() throws Exception
	{
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierVisitFactory/supplierVisitFactoryGrid.ftl");
	}
	/**
	 * 供应商年度巡厂得分列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory")
	public void doListSupplierVisitFactory() throws Exception
	{
		Map<String,Object> map=getSupplierVisitFactory().toMap();
			map.put("created", this.asString("created"));
			map.put("updated", this.asString("updated"));
			map.put("marStartDate",this.asString("marStartDate")==null?null:this.asInteger("marStartDate"));
			map.put("marEndDate", this.asString("marEndDate")==null?null:this.asInteger("marEndDate"));
		List<SupplierVisitFactory> list=supplierVisitFactoryService.listSupplierVisitFactory(map,this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 显示上传供应商年度巡厂得分表界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory.upload")
	public void doShowSupplierVisitFactoryForm() {
		this.forwardPage("sco/dataMaintenance/gradecontrolData/supplierVisitFactory/supplierVisitFactoryUploadForm.ftl");
	}
	/**
	 * 上传供应商年度巡厂得分表
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory.upload")
	public void doCompleteImportSupplierVisitFactoryData() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null; 
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return ;
		}
		String msg =this.supplierVisitFactoryService.completeImportSupplierVisitFactoryData(file);
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
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.suppliervisitactory.upload")
	public void doDownloadSupplierVisitFactoryDataTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/gradecontrolData/supplierVisitFactory/supplierVisitFactoryTemplate.xlsx", ExcelUtils.getEncodeFileName("巡检得分数据维护模板.xlsx"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private SupplierVisitFactory getSupplierVisitFactory() throws Exception{
		SupplierVisitFactory supplierVisitFactory=new SupplierVisitFactory();
		this.asBean(supplierVisitFactory);
		return supplierVisitFactory;
	}
}
