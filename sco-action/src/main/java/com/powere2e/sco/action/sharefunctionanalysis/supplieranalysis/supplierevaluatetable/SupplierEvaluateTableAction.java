package com.powere2e.sco.action.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.google.gson.Gson;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryIntentionService;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateTableService;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable.SupplierEvaluateScore;
import com.powere2e.security.model.Option;

/**
 * 供应商考评表的WEB请求响应类
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public class SupplierEvaluateTableAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1952804086393290341L;
	private SupplierEvaluateTableService supplierEvaluateTableService;
	private AccessoryIntentionService accessoryIntentionService;

	/**
	 * 设置要调用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		accessoryIntentionService = (AccessoryIntentionService) ConfigFactory.getInstance().getBean("accessoryIntentionService");
		supplierEvaluateTableService = (SupplierEvaluateTableService) ConfigFactory.getInstance().getBean("supplierEvaluateTableManager");
	}

	// 显示页面
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doShowSupplierEvaluateTable() {
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateTableGrid.ftl");
	}

	/**
	 * 细分类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMinceType() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = accessoryIntentionService.listMinceType(map);
		this.forwardData(true, list, null);
	}

	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doListSupplierEvaluateTable() throws Exception {
		// templateName','created','status','supplierName','supplierCode','createby','minPublishDate','maxPublishDate','minCreated','maxCreated'
		Map<String, Object> map = getSupplierEvaluateScore().toMap();
		map.put("evaluateStartDate", this.asString("minCreated"));
		map.put("evaluateEndDate", this.asString("maxCreated"));
		map.put("minPublishDate", this.asString("minPublishDate"));
		map.put("maxPublishDate", this.asString("maxPublishDate"));
		map.put("templateName", this.asString("templateName"));
		map.put("status", this.asString("status"));
		map.put("searchButton", this.asString("searchButton"));
		List<SupplierEvaluateScore> list = supplierEvaluateTableService.listSupplierEvaluateTemplate(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	// 显示发布供应商考评表的弹出窗口页面
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doShowSupplierEvaluateTableForm() {
		// 把templateCode存到Session里，发布考评表的时候要用
		request.getSession().setAttribute("templateCode", this.asString("templateCode"));
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateTableForm.ftl");
	}

	// 弹出窗口中查询符合条件的供应商
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doListSupplier() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplierName", this.asString("sName"));
		map.put("supplierCode", this.asString("sCode"));
		map.put("centreTypeCode", this.asString("centreTypeCode"));
		map.put("smallTypeCode", this.asString("smallTypeCode"));
		map.put("detailTypeCode", this.asString("detailTypeCode"));
		map.put("fineTypeCode", this.asString("fineTypeCode"));
		map.put("fTypeCode", this.asString("fTypeCode"));
		List<Supplier> list = supplierEvaluateTableService.listSupplier(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	// 发布供应商考评模板
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doInsertTemplateTable() throws Exception {
		List<SupplierEvaluateScore> list = new ArrayList<SupplierEvaluateScore>();
		// 查询是否已经发布过该供应商考评表
		String[] supplierCodes = this.asString("supplierCodes").split(",");
		String[] templateCodes = this.asString("templateCodes").split(",");
		for (int i = 0; i < supplierCodes.length; i++) {
			SupplierEvaluateScore supplierEvaluateScore = new SupplierEvaluateScore();
			supplierEvaluateScore.setSupplierCode(supplierCodes[i]);
			supplierEvaluateScore.setTemplateCode(templateCodes[i]);
			list.add(supplierEvaluateScore);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("item", list);
		List<SupplierEvaluateScore> supplier = supplierEvaluateTableService.listSupplierCode(map);
		if (supplier.size()==0) {
			supplierEvaluateTableService.insertSupplierEvaluateTable(list);
			this.forwardData(true, null, "操作成功");
		} else {
			String supplierCode="";
			for(int i=0;i<supplier.size();i++){
				supplierCode+=supplier.get(i).getSupplierCode()+"-"+supplier.get(i).getSupplierName()+"<br/>";
			}
			this.forwardData(false, null, "以下供应商已发布过该模板的考评表:<br/>"+supplierCode);
		}
	}

	// 显示一个考评详情页面
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doShowSupplierEvaluateTableByEvaluateTableCode() throws Exception {
		List<SupplierEvaluateScore> sesList = new ArrayList<SupplierEvaluateScore>();
		String evaluateTableCode = this.asString("evaluateTableCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evaluateTableCode", evaluateTableCode);
		map.put("type", "");
		if (evaluateTableCode != null) {
			sesList = supplierEvaluateTableService.loadSupplierEvaluateTableItem(map);
		}
		this.putObject("sesList", sesList);
		this.putObject("evaluateTableCode", evaluateTableCode);
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateTemplateTable.ftl");
	}

	// 显示一个考评详情页面－－打分
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doShowSupplierEvaluateTableMarkByEvaluateTableCode() throws Exception {
		List<SupplierEvaluateScore> sesList = new ArrayList<SupplierEvaluateScore>();
		String evaluateTableCode = this.asString("evaluateTableCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evaluateTableCode", evaluateTableCode);
		map.put("type", "show");
		if (evaluateTableCode != null) {
			sesList = supplierEvaluateTableService.loadSupplierEvaluateTableItem(map);
		}
		this.putObject("sesList", sesList);
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierEvaluateTable/supplierEvaluateMark.ftl");
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierEvaluateScore getSupplierEvaluateScore() throws Exception {
		SupplierEvaluateScore supplierEvaluateScore = new SupplierEvaluateScore();
		this.asBean(supplierEvaluateScore);
		return supplierEvaluateScore;
	}

	// 给供应商考评表打分
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doUpdateSupplierEvaluateTableByEvaluateItemCode() throws Exception {
		String str = this.asString("array");
		Gson gson = new Gson();
		Object obj = gson.fromJson(str, Object.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", obj);
		supplierEvaluateTableService.updateSupplierEvaluateScoreByEvaluateItemCode(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.supplieranalysis.supplierevaluatetable")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "考评表详情-".concat(this.asString("evaluateTableCode")).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		String evaluateTableCode = this.asString("evaluateTableCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evaluateTableCode", evaluateTableCode);
		map.put("type", "");
		this.supplierEvaluateTableService.exportSupplierEvaluateTable(map, out);
	}
}
