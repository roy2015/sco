package com.powere2e.sco.action.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplateService;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;
/**
 * 供应商考评表模板的WEB请求响应类
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public class SupplierEvaluateTemplateAction extends WorkAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4902657369219269002L;
	private SupplierEvaluateTemplateService supplierEvaluateTemplateService;
	
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierEvaluateTemplateService=(SupplierEvaluateTemplateService)ConfigFactory.getInstance().getBean("supplierEvaluateTemplateManager");
	}
	//显示页面
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doShowSupplierEvaluateTemplate(){
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/supplierEvaluateTemplateGrid.ftl");
	}
	//根根TemplateCode查询出一条数据
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doFindSupplierEvaluateTemplateByTemplateCode() throws Exception{
		String temp=(String) request.getSession().getAttribute("code");
		List<SupplierEvaluateTemplate> supplierEvaluateTemplate=supplierEvaluateTemplateService.loadSupplierEvaluateTemplateItem(temp);
		this.forwardData(true, supplierEvaluateTemplate, null);
	}
	//显示一个模板新页面
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doShowSupplierEvaluateTemplateByTemplateCode() throws Exception{
		Map<String,Object> map=getSupplierEvaluateTemplate().toMap();
		if(map.get("templateCode")!=null){
			request.getSession().setAttribute("code", map.get("templateCode"));
		}
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/supplierEvaluateTemplate.ftl");
	}
	//显示一个修改模板的新页面
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doShowUpdateSupplierEvaluateTemplateByTemplateCode() throws Exception{
		List<SupplierEvaluateTemplate> supplierEvaluateTemplateList=new ArrayList<SupplierEvaluateTemplate>();
		String templateCode=this.asString("templateCode");
		if(templateCode!=null){
			supplierEvaluateTemplateList=supplierEvaluateTemplateService.loadSupplierEvaluateTemplateItem(templateCode);
		}
		this.putObject("supplierEvaluateTemplateList", supplierEvaluateTemplateList);
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/updateSupplierEvaluateTemplate.ftl");
	}
	//新页面中显示一个商品供应商考评表模板
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doShowMerchandiseSupplierEvaluateTemplate(){
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/merchandiseSupplierEvaluateTemplate.ftl");
	}
	//新页面中显示一个辅料供应商考评表模板
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doShowAccessorySupplierEvaluateTemplate(){
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTemplate/supplierEvaluateTemplate/accessorySupplierEvaluateTemplate.ftl");
	}
	/**
	 * 供应商考评表模板列表
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doListSupplierEvaluateTemplate() throws Exception
	{
		Map<String,Object> map=getSupplierEvaluateTemplate().toMap();
			map.put("minCreated", this.asString("minCreated"));
			map.put("maxCreated", this.asString("maxCreated"));
			map.put("minPublishDate", this.asString("minPublishDate"));
			map.put("maxPublishDate", this.asString("maxPublishDate"));
		List<SupplierEvaluateTemplate> list=supplierEvaluateTemplateService.listSupplierEvaluateTemplate(map,this.getPageInfo());
		for(SupplierEvaluateTemplate l:list){
			String str=l.getStatus();
			if(str.equals("CG")){
				l.setStatus("草稿");
			}else if(str.equals("YFB")){
				l.setStatus("已发布");
			}else if(str.equals("YGB")){
				l.setStatus("已关闭");
			}
		}
		this.forwardData(true, list, null);
	}
	/**
	 * 根据ID号获一个供应商考评表模板
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doLoadSupplierEvaluateTemplate() throws Exception{
		List<SupplierEvaluateTemplate> supplierEvaluateTemplate=this.supplierEvaluateTemplateService.loadSupplierEvaluateTemplateItem(asString("id"));
		this.forwardData(true,supplierEvaluateTemplate, null);
	}
	
	/**
	 *添加供应商考评表模板-----商品
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doInsertSupplierEvaluateTemplate() throws Exception {
		List<Object> list=this.asList("list");
		supplierEvaluateTemplateService.addSupplierEvaluateTemplate(list);
		this.forwardData(true, null, this.getText("public.success"));
	}
	/**
	 *添加供应商考评表模板-----辅料
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doInsertAccessorySupplierEvaluateTemplate() throws Exception{
		List<Object> list=this.asList("list");
		supplierEvaluateTemplateService.addAccessorySupplierEvaluateTemplate(list);
		this.forwardData(true, null, this.getText("public.success"));
	}
	/**
	 * 发布考评表模板
	 * @throws Exception
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doPublishSupplierEvaluateTemplateByTemplateCode() throws Exception{
		String str=this.asString("list");
		String[] arr=str.split(",");
		List<Object> list=new ArrayList<Object>();
		for(String s:arr){
			if(s!=null&&s!=""){
				list.add(s.trim());
			}
		}
		if(list!=null){
			supplierEvaluateTemplateService.updateSupplierEvaluateTemplate(list);
			this.forwardData(true, null, this.getText("public.success"));
		}
	}
	/**
	 * 修改供应商考评表模板
	 *
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doUpdateSupplierEvaluateTemplate() throws Exception {
		List<Object> list=this.asList("array");
		supplierEvaluateTemplateService.alterSupplierEvaluateTemplate(list);
		this.forwardData(true, null, this.getText("public.success"));
		
	}
	 /**
	  * 关闭考评表模板
	  */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doCloseSupplierEvaluateTemplate() throws Exception {
		String templateCodes=this.asString("list");
		String[] templateCodeArray=templateCodes.split(";");
		List<Object> templateCodelist=new ArrayList<Object>();
		for(int i=0;i<templateCodeArray.length;i++){
			if(templateCodeArray[i]!=null){
				templateCodelist.add(templateCodeArray[i]);
			}
		}
		supplierEvaluateTemplateService.updateSupplierEvaluateTemplateByTemplateCode(templateCodelist);
		this.forwardData(true, null, this.getText("public.success"));
	}
	/**
	 * 删除供应商考评表模板
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	@Authority(privilege="com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate")
	public void doDeleteSupplierEvaluateTemplate() throws Exception{
		String templateCodes=this.asString("array");
		String[] templateCodeArray=templateCodes.split(";");
		List<Object> templateCodelist=new ArrayList<Object>();
		for(int i=0;i<templateCodeArray.length;i++){
			if(templateCodeArray[i]!=null){
				templateCodelist.add(templateCodeArray[i]);
			}
		}
		supplierEvaluateTemplateService.deleteSupplierEvaluateTemplate(templateCodelist);
		this.forwardData(true, null,this.getText("public.success"));
	}
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *				可能抛出Exception异常
	 */
	private SupplierEvaluateTemplate getSupplierEvaluateTemplate() throws Exception{
		SupplierEvaluateTemplate supplierEvaluateTemplate=new SupplierEvaluateTemplate();
		this.asBean(supplierEvaluateTemplate);
		return supplierEvaluateTemplate;
	}
}
