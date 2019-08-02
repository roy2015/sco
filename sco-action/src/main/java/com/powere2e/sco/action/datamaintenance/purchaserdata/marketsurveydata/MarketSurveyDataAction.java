package com.powere2e.sco.action.datamaintenance.purchaserdata.marketsurveydata;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyDataService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyData;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 竞品价格数据WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月29日
 * @version 1.0
 */
public class MarketSurveyDataAction extends UploadUtils {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7368978032957189676L;
	private MarketSurveyDataService surveyDataService;

	@Override
	protected void beforeBuild() {
		surveyDataService = (MarketSurveyDataService) ConfigFactory
				.getInstance().getBean("marketSurveyDataService");
	}

	/**
	 * 显示竞品价格数据主界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData")
	public void doShowMarketSurveyDataMain() throws Exception {
		this.forwardPage("sco/dataMaintenance/purchaserData/marketSurveyData/marketSurveyDataGrid.ftl");
	}

	/**
	 * 查询竞品价格数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData")
	public void doListMarketSurveyData() throws Exception {
		Map<String, Object> map = this.getMarketSurveyData().toMap();
		map.put("marStartDate", this.asDate("marStartDate"));
		map.put("marEndDate", this.asDate("marEndDate"));
		List<MarketSurveyData> list = this.surveyDataService
				.listMarketSurveyData(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示导入竞品价格数据界面
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.upload")
	public void doShowImportMarketSurveyDataForm() {
		this.forwardPage("sco/dataMaintenance/purchaserData/marketSurveyData/marketDatauploadForm.ftl");
	}

	/**
	 * 下载导入模板
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.upload")
	public void doDownloadMarketSurveyDataTemplate() throws Exception {
		this.forwardDownload(
				"excel/sco/dataMaintenance/marketSurveyData/marketSurveyDataTemplate.xlsx",
				ExcelUtils.getEncodeFileName("市调数据维护模板.xlsx"));
	}

	/**
	 * 导入竞品价格市调数据
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.upload")
	public void doCompleteImportMarketSurveyData() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		String msg = this.surveyDataService
				.completeImportMarketSurveyData(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 删除竞品价格市调数据
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.delete")
	public void doDeleteMarketSurveyData() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", this.asString("ids"));
		this.surveyDataService.deleteMarketSurveyData(map);
		this.forwardData(true, null, "竞品价格市调数据删除成功");
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.export")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "市调数据-".concat(DateUtils.formateDateTime()).concat(
				".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1),
						Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getMarketSurveyData().toMap();
		map.put("NOAUTO", "TRUE");
		map.put("marStartDate", this.asDate("marStartDate"));
		map.put("marEndDate", this.asDate("marEndDate"));
		this.surveyDataService.exportSignedQtyExcel(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.dataMaintenance.purchaserData.marketSurveyData.save")
	public void doSaveSearchDataForm() throws Exception {

		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.MPD);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}

		// JSONArray jsonArr = JSONArray.fromObject(rows);
		// MarketSurveyData[] msds = new MarketSurveyData[jsonArr.size()];
		// for (int j = 0; j < jsonArr.size(); j++) {
		// String[] dateFormats = new String[] {"yyyy-MM-dd"};
		// JSONUtils.getMorpherRegistry().registerMorpher(new
		// DateMorpher(dateFormats));
		// msds[j] = (MarketSurveyData)
		// JSONObject.toBean(jsonArr.getJSONObject(j), MarketSurveyData.class);
		// }
		// 获取前台参数
		Map<String, Object> paraMap = this.getMarketSurveyData().toMap();
		paraMap.put("marStartDate", this.asDate("marStartDate"));
		paraMap.put("marEndDate", this.asDate("marEndDate"));
		paraMap.put("NOAUTO", "TRUE");
		String msg = this.surveyDataService.saveSearchDataForm(fileName,
				paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MarketSurveyData getMarketSurveyData() throws Exception {
		MarketSurveyData marketSurveyData = new MarketSurveyData();
		this.asBean(marketSurveyData);
		return marketSurveyData;
	}

}
