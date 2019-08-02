package com.powere2e.sco.service.impl.merchandisecostanalysis.totalcostanalogyanalysis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDao;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.ingredient.IngredientDao;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalogyAnalysisDao;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalogyAnalysisService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAddedvaluetax;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAggregate;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAggregateRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCostItem;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCustomscharges;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCustomsduties;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingElsesubtotal;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingElsesubtotalRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingExchangerate;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFactoryPrice;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFreight;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFreightRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingInterest;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManage;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManageRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManpower;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingNPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingOceanfreight;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingProfit;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingProfitRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingSbzjwh;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTax;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTaxDiffer;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTaxRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWastage;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWec;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;
import com.powere2e.sco.model.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalysis;
import com.powere2e.sco.model.reports.CostAnalysisMerchandise;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品分项成本类比
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月23日
 */
public class TotalCostAnalogyAnalysisServiceImpl extends ServiceImpl implements TotalCostAnalogyAnalysisService {

	private static final long serialVersionUID = -5084903761701825157L;
	private TotalCostAnalogyAnalysisDao totalCostAnalogyAnalysisDao;
	private boolean setUnits = false;          //是否设置统一对比单位
	private boolean isShowAllRemarks = false;  //是否显示所有备注
	private boolean isShowThisRemarks = false; //是否只显示本次申请商品备注
	private boolean isShowUnits = false;       //是否显示报价计量单位
	private boolean isShowSubTotal = false;    //是否只显示小计
	private boolean isHideAllRemarks = true;   //是否隐藏所有备注
	private String inlandImport = "";		   //进口还是非进口
	private int materialsSize = 0; 			   //原料的最大个数
	private int npackagsSize = 0;			   //内包装最大个数
	private int wpackagsSize = 0;			   //外包装最大个数
	private int wastagesSize = 0;			   //损耗最大个数
	private CellStyle leftStyle = null;           //左对齐样式
	private CellStyle leftRedStyle = null;        //左对齐变红样式
	private CellStyle titleSyle = null;           //表头样式
	private CellStyle twoPrecisionStyle = null;   //两位精度小数样式
	private CellStyle twoPrecisionRedStyle = null;//两位精度小数变红样式
	private CellStyle threePrecisionStyle = null; //三位精度小数样式
	private CellStyle threePrecisionRedStyle = null;//三位精度小数变红样式
	private CellStyle fourPrecisionStyle = null;    //四位精度小数样式
	private CellStyle fourPrecisionRedStyle = null; //四位精度小数变红样式
	private CellStyle accountingCodeStyle = null; //核算编号的样式,防止变成科学计数法
	
	private String startTime;
	private String endTime;
	private AccountingDao accountingDao;
	private IngredientDao ingredientDao;
	public AccountingDao getAccountingDao() {
		return accountingDao;
	}

	public void setAccountingDao(AccountingDao accountingDao) {
		this.accountingDao = accountingDao;
	}

	public IngredientDao getIngredientDao() {
		return ingredientDao;
	}

	public void setIngredientDao(IngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}
	
	public TotalCostAnalogyAnalysisDao getTotalCostAnalogyAnalysisDao() {
		return totalCostAnalogyAnalysisDao;
	}

	public void setTotalCostAnalogyAnalysisDao(TotalCostAnalogyAnalysisDao totalCostAnalogyAnalysisDao) {
		this.totalCostAnalogyAnalysisDao = totalCostAnalogyAnalysisDao;
	}

	public static TotalCostAnalogyAnalysisDao getInstance() {
		return (TotalCostAnalogyAnalysisDao) ConfigFactory.getInstance().getBean("totalCostAnalogyAnalysisService");
	}

	@Override
	public TotalCostAnalysis listThisApplicationMerchandise(Map<String, Object> map) {
		TotalCostAnalysis totalCostAnalysis = new TotalCostAnalysis();
		try {
			//判断是否是快速调价,快速调价是没有核算表的
			if (map.get("accountingCode") != null&&!(map.get("accountingCode") instanceof JSONNull)) {
				totalCostAnalysis.setAccounting(this.accountingDao.loadAccounting(map));
				totalCostAnalysis.setAccountingCostItem(this.accountingDao.loadAccountingCostItem(map));
				totalCostAnalysis.setAccountingElsesubtotal(this.accountingDao.loadAccountingElsesubtotal(map));
				totalCostAnalysis.setAccountingElsesubtotalRegionList(this.accountingDao.loadAccountingElsesubtotalRegion(map));
				totalCostAnalysis.setAccountingFreight(this.accountingDao.loadAccountingFreight(map));
				totalCostAnalysis.setIngredient(this.ingredientDao.loadIngredient(map));
				totalCostAnalysis.setIngredientItemList(this.ingredientDao.listIngredientItem(map, null));
				totalCostAnalysis.setAccountingAggregate(this.accountingDao.loadAccountingAggregate(map));
				totalCostAnalysis.setAccountingAggregateRegionList(this.accountingDao.loadAccountingAggregateRegion(map));
				totalCostAnalysis.setAccountingFreightRegionList(this.accountingDao.loadAccountingFreightRegion(map));
				totalCostAnalysis.setAccountingManage(this.accountingDao.loadAccountingManage(map));
				totalCostAnalysis.setAccountingManageRegionList(this.accountingDao.loadAccountingManageRegion(map));
				totalCostAnalysis.setAccountingManpower(this.accountingDao.loadAccountingManpower(map));
				totalCostAnalysis.setAccountingNPackagList(this.accountingDao.loadAccountingNPackag(map));
				totalCostAnalysis.setAccountingProfit(this.accountingDao.loadAccountingProfit(map));
				totalCostAnalysis.setAccountingProfitRegionList(this.accountingDao.loadAccountingProfitRegion(map));
				totalCostAnalysis.setAccountingRegionList(this.accountingDao.loadAccountingRegion(map));
				totalCostAnalysis.setAccountingSbzjwh(this.accountingDao.loadAccountingSbzjwh(map));
				totalCostAnalysis.setAccountingTax(this.accountingDao.loadAccountingTax(map));
				totalCostAnalysis.setAccountingTaxRegionList(this.accountingDao.loadAccountingTaxRegion(map));
				totalCostAnalysis.setAccountingWastageList(this.accountingDao.loadAccountingWastage(map));
				totalCostAnalysis.setAccountingWec(this.accountingDao.loadAccountingWec(map));
				totalCostAnalysis.setAccountingWPackagList(this.accountingDao.loadAccountingWPackag(map));
				totalCostAnalysis.setOaApplicationCode(totalCostAnalogyAnalysisDao.searchOAApplicationCode(map));
				//进口
				totalCostAnalysis.setAccountingFactoryPrice(this.accountingDao.loadAccountingFactoryPrice(map));
				totalCostAnalysis.setAccountingExchangerate(this.accountingDao.loadAccountingExchangerate(map));
				totalCostAnalysis.setAccountingOceanfreight(this.accountingDao.loadAccountingOceanfreight(map));
				totalCostAnalysis.setAccountingCustomscharges(this.accountingDao.loadAccountingCustomscharges(map));
				totalCostAnalysis.setAccountingCustomsduties(this.accountingDao.loadAccountingCustomsduties(map));
				totalCostAnalysis.setAccountingAddedvaluetax(this.accountingDao.loadAccountingAddedvaluetax(map));
				totalCostAnalysis.setAccountingTaxDiffer(this.accountingDao.loadAccountingTaxDiffer(map));
				totalCostAnalysis.setAccountingInterest(this.accountingDao.loadAccountingInterest(map));
			}else{
				totalCostAnalysis.setAccounting(this.totalCostAnalogyAnalysisDao.loadFalseAccounting(map));
				totalCostAnalysis.setAccountingRegionList(this.totalCostAnalogyAnalysisDao.listFalseAccountingRegion(map));
				totalCostAnalysis.setAccountingCostItem(new AccountingCostItem());
				totalCostAnalysis.setIngredient(new Ingredient());
				totalCostAnalysis.setIngredientItemList(new ArrayList<IngredientItem>());
				totalCostAnalysis.setAccountingAggregate(new AccountingAggregate());
				totalCostAnalysis.setAccountingAggregateRegionList(new ArrayList<AccountingAggregateRegion>());
				totalCostAnalysis.setAccountingElsesubtotal(new AccountingElsesubtotal());
				totalCostAnalysis.setAccountingElsesubtotalRegionList(new ArrayList<AccountingElsesubtotalRegion>());
				totalCostAnalysis.setAccountingFreight(new AccountingFreight());
				totalCostAnalysis.setAccountingFreightRegionList(new ArrayList<AccountingFreightRegion>());
				totalCostAnalysis.setAccountingManage(new AccountingManage());
				totalCostAnalysis.setAccountingManageRegionList(new ArrayList<AccountingManageRegion>());
				totalCostAnalysis.setAccountingManpower(new AccountingManpower());
				totalCostAnalysis.setAccountingNPackagList(new ArrayList<AccountingNPackag>());
				totalCostAnalysis.setAccountingProfit(new AccountingProfit());
				totalCostAnalysis.setAccountingProfitRegionList(new ArrayList<AccountingProfitRegion>());
				totalCostAnalysis.setAccountingSbzjwh(new AccountingSbzjwh());
				totalCostAnalysis.setAccountingTax(new AccountingTax());
				totalCostAnalysis.setAccountingTaxRegionList(new ArrayList<AccountingTaxRegion>());
				totalCostAnalysis.setAccountingWastageList(new ArrayList<AccountingWastage>());
				totalCostAnalysis.setAccountingWec(new AccountingWec());
				totalCostAnalysis.setAccountingWPackagList(new ArrayList<AccountingWPackag>());
				//进口
				totalCostAnalysis.setAccountingFactoryPrice(new AccountingFactoryPrice());
				totalCostAnalysis.setAccountingExchangerate(new AccountingExchangerate());
				totalCostAnalysis.setAccountingOceanfreight(new AccountingOceanfreight());
				totalCostAnalysis.setAccountingCustomscharges(new AccountingCustomscharges());
				totalCostAnalysis.setAccountingCustomsduties(new AccountingCustomsduties());
				totalCostAnalysis.setAccountingAddedvaluetax(new AccountingAddedvaluetax());
				totalCostAnalysis.setAccountingTaxDiffer(new AccountingTaxDiffer());
				totalCostAnalysis.setAccountingInterest(new AccountingInterest());
				totalCostAnalysis.setOaApplicationCode(totalCostAnalogyAnalysisDao.searchOAApplicationCode(map));
			}
			totalCostAnalysis.setMerchandiseContractPrices(totalCostAnalogyAnalysisDao.searchMerchandiseContractPrice(map));
		} catch (Exception e) {
			totalCostAnalysis = null;
		}
		return totalCostAnalysis;
	}
	
	@Override
	public List<AccountingBo> listTotalCostAnalogyAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return totalCostAnalogyAnalysisDao.listTotalCostAnalogyAnalysis(map, pageInfo);
	}

	@Override
	public List<AccountingBo> listReferMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return totalCostAnalogyAnalysisDao.listReferMerchandise(map, pageInfo);
	}

	@Override
	public List<AccountingBo> listReferIntention(Map<String, Object> map, PageInfo pageInfo) {
		return totalCostAnalogyAnalysisDao.listReferIntention(map, pageInfo);
	}

	@Override
	public Map<String, Object> listTitle(Map<String, Object> m) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("merchandiseName", "商品/意向品名称");
		map.put("merchandiseCode", "商品/意向品编号");
		map.put("supplierName", "供应商/意向供应商名称");
		map.put("supplierCode", "供应商/意向供应商编号");
		map.put("units", "报价计量单位");
		map.put("quotedDate", "报价日期");
		map.put("accountingCode", "核算/投料表编号");
		map.put("updateby", "采购员");
		map.put("region", "进货地区");
		if (!isShowSubTotal) {
			map.put("material", "原料");
		}
		map.put("yield", "原料得率");
		map.put("zlsubtotalValue", "主原料小计(投入成本)");
		map.put("flsubtotalValue", "辅料小计(投入成本)");
		//进口和非进口的纵向表头是不一样的
		if ("INLAND".equals(m.get("inlandImport"))) {
			//非进口核算表
			map.put("iTotalcostValue", "商品总投入成本");
			map.put("mTotalcostValue", "原材料总成本");
			map.put("packagproportionValue", "包装占比");
			map.put("deductptcostValue", "扣除包装后原材料总成本");
			if (!isShowSubTotal) {
				map.put("npackag", "内包装");
				map.put("wpackag", "外包装");
			}
			map.put("nwpackagsubtotalValue", "内外包装材料价格小计");
			if (!isShowSubTotal) {
				map.put("wastage", "损耗");
			}
			if (!isShowSubTotal) {
				map.put("wecPrice", "水电煤");
				map.put("sbzjwhPrice", "设备折旧及维护");
				map.put("ampPrice", "人工");
				map.put("amaPrice", "管理");
				map.put("afrPrice", "运输");
				map.put("atrPrice", "税收");
				map.put("aprPrice", "利润");
			}
		} else {
			//进口
			map.put("factoryPrice", "商品报价");
			map.put("exchangeRate", "汇率");
			map.put("rmbSettlementPrice", "商品人民币结算价格");
			if (!isShowSubTotal) {
				map.put("oceanfreight", "海运费/空运费");
				map.put("orderFee", "换单费");
				map.put("premium", "保险费");
				map.put("customscharges", "报关服务费");
			}
			map.put("importFeeTotal", "进口费用小计");
			if (!isShowSubTotal) {
				map.put("customsduties", "关税");
				map.put("addedvaluetax", "增值税");
			}
			map.put("cdAvtTotal", "关税、增值税小计");
			map.put("customsClearanceTotal", "清关后商品总成本");
			map.put("packagproportionValue", "包装占比");
			map.put("deductptcostValue", "扣除包装后原材料总成本");
			if (!isShowSubTotal) {
				map.put("npackag", "内包装");
				map.put("wpackag", "外包装");
			}
			map.put("nwpackagsubtotalValue", "内外包装材料价格小计");
			if (!isShowSubTotal) {
				map.put("wastage", "损耗");
			}
			if (!isShowSubTotal) {
				map.put("wecPrice", "水电煤");
				map.put("sbzjwhPrice", "设备折旧及维护");
				map.put("ampPrice", "人工");
				map.put("amaPrice", "管理");
				map.put("taxDiffer", "税差");
				map.put("interest", "利息");
				map.put("afrPrice", "运输");
				map.put("atrPrice", "税收");
				map.put("aprPrice", "利润");
			}
		}
		
		map.put("subTotal", "其它成本小计");
		map.put("sumPrice", "总价");
		map.put("mcpPrice", "合作价格");
		return map;
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out) {
		// 获取各种状态
		setUnits = Boolean.valueOf(map.get("setUnits").toString());
		isShowAllRemarks = Boolean.valueOf(map.get("isShowAllRemarks").toString());
		isShowThisRemarks = Boolean.valueOf(map.get("isShowThisRemarks").toString());
		isShowUnits = Boolean.valueOf(map.get("isShowUnits").toString());
		isShowSubTotal = Boolean.valueOf(map.get("isShowSubTotal").toString());
		isHideAllRemarks = Boolean.valueOf(map.get("isHideAllRemarks").toString());
		inlandImport = map.get("inlandImport").toString();
		materialsSize = 1;
		npackagsSize = 1;
		wpackagsSize = 1;
		wastagesSize = 1;
		// 准备要填充数据的List
		List<TotalCostAnalysis> list = new ArrayList<TotalCostAnalysis>();
		JSONArray jsonArray = JSONArray.fromObject(map.get("data"));
		Map<String, Object> accountingMap = new HashMap<String, Object>();
		JSONObject tempObj = null;
		JSONObject jsonObject = null;
		for (Object object : jsonArray) {
			accountingMap.clear();
			// 获取数据中的每一个对象
			jsonObject = JSONObject.fromObject(object);
			if (jsonObject == null) {
				continue;
			}
			// 根据对象附带的信息去查询每个对象完整的信息
			accountingMap.put("accountingCode", jsonObject.get("accountingCode") instanceof JSONNull?null:jsonObject.get("accountingCode"));
			accountingMap.put("ingredientCode", jsonObject.get("accountingCode") instanceof JSONNull?null:jsonObject.get("accountingCode"));
			accountingMap.put("applicationCode", jsonObject.get("applicationCode") instanceof JSONNull?null:jsonObject.get("applicationCode"));
			accountingMap.put("merchandiseCode", jsonObject.get("merchandiseCode") instanceof JSONNull?null:jsonObject.get("merchandiseCode"));
			accountingMap.put("supplierCode", jsonObject.get("supplierCode") instanceof JSONNull?null:jsonObject.get("supplierCode"));
			if (jsonObject.getString("rowTitleName").contains("报价单位转换后")) {
				for (int j = 0; j < jsonArray.size(); j++) {
					tempObj = jsonArray.getJSONObject(j);
					if (jsonObject.get("accountingCode") == null||jsonObject.get("accountingCode") instanceof JSONNull) {
						if (tempObj.getString("merchandiseCode").equals(jsonObject.get("merchandiseCode"))
						  &&tempObj.getString("supplierCode").equals(jsonObject.get("supplierCode"))
						 &&!tempObj.getString("rowTitleName").contains("报价单位转换后")) {
							accountingMap.put("quantity", tempObj.get("quantity"));
						}
					} else {
						if (jsonObject.get("accountingCode").equals(tempObj.getString("accountingCode"))&&!tempObj.getString("rowTitleName").contains("报价单位转换后")) {
							accountingMap.put("quantity", tempObj.get("quantity"));
						}
					}
				}
				accountingMap.put("convertAfterQuantity", jsonObject.get("quantity"));
			}
			TotalCostAnalysis tCostAnalysis = this.listThisApplicationMerchandise(accountingMap);
			// 获取投料表要跨的行数
			if (tCostAnalysis != null) {
				materialsSize = tCostAnalysis.getIngredientItemList().size()>materialsSize?tCostAnalysis.getIngredientItemList().size():materialsSize;
				npackagsSize = tCostAnalysis.getAccountingNPackagList().size()>npackagsSize?tCostAnalysis.getAccountingNPackagList().size():npackagsSize;
				wpackagsSize = tCostAnalysis.getAccountingWPackagList().size()>wpackagsSize?tCostAnalysis.getAccountingWPackagList().size():wpackagsSize;
				wastagesSize = tCostAnalysis.getAccountingWastageList().size()>wastagesSize?tCostAnalysis.getAccountingWastageList().size():wastagesSize;
				// 把查询出来的对象放入list
				list.add(tCostAnalysis);
			}
		}
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品总成本类比分析");
		configStyle(wb,sheet);
		// 填充表头
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("inlandImport", inlandImport);
		fillListHeaderCell(wb, sheet,m);
		try {
			// 2.用数据填充单元格
			if (!list.isEmpty()){
				this.fillDataCell(this.listTitle(m), list, wb, sheet, map);
				autoSizeListWidth(sheet);
				wb.write(out);
			}
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
			}
		}
	}
	
	/**
	 * 配置各种样式
	 * @param wb
	 * @param sheet
	 */
	public void configStyle(Workbook wb,Sheet sheet){
		Font commFont = wb.createFont();
		commFont.setFontName("微软雅黑");
		
		Font redFont = wb.createFont();
		redFont.setColor(Font.COLOR_RED);
		redFont.setFontName("微软雅黑");
		
		XSSFDataFormat format = (XSSFDataFormat) wb.createDataFormat();
		
		//文本左对齐样式
		leftStyle = wb.createCellStyle();
		ExcelUtils.setAlign(leftStyle, ExcelUtils.left, ExcelUtils.center, true);
		ExcelUtils.setFrame(leftStyle, true);
		leftStyle.setFont(commFont);
		//文本左对齐变红样式
		leftRedStyle = wb.createCellStyle();
		ExcelUtils.setAlign(leftRedStyle, ExcelUtils.left, ExcelUtils.center, true);
		ExcelUtils.setFrame(leftRedStyle, true);
		leftRedStyle.setFont(redFont);
		
		//2精度
		twoPrecisionStyle = wb.createCellStyle();
		ExcelUtils.setAlign(twoPrecisionStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(twoPrecisionStyle, true);
		twoPrecisionStyle.setDataFormat(format.getFormat("#,##0.00"));
		twoPrecisionStyle.setFont(commFont);
		//3精度
		threePrecisionStyle = wb.createCellStyle();
		ExcelUtils.setAlign(threePrecisionStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(threePrecisionStyle, true);
		threePrecisionStyle.setDataFormat(format.getFormat("#,##0.000"));
		threePrecisionStyle.setFont(commFont);
		//4精度
		fourPrecisionStyle = wb.createCellStyle();
		ExcelUtils.setAlign(fourPrecisionStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(fourPrecisionStyle, true);
		fourPrecisionStyle.setDataFormat(format.getFormat("#,##0.0000"));
		fourPrecisionStyle.setFont(commFont);
		
		//变红
		//2精度
		twoPrecisionRedStyle = wb.createCellStyle();
		ExcelUtils.setAlign(twoPrecisionRedStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(twoPrecisionRedStyle, true);
		twoPrecisionRedStyle.setDataFormat(format.getFormat("#,##0.00"));
		twoPrecisionRedStyle.setFont(redFont);
		//3精度
		threePrecisionRedStyle = wb.createCellStyle();
		ExcelUtils.setAlign(threePrecisionRedStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(threePrecisionRedStyle, true);
		threePrecisionRedStyle.setDataFormat(format.getFormat("#,##0.000"));
		threePrecisionRedStyle.setFont(redFont);
		//4精度
		fourPrecisionRedStyle = wb.createCellStyle();
		ExcelUtils.setAlign(fourPrecisionRedStyle, ExcelUtils.right, ExcelUtils.center, true);
		ExcelUtils.setFrame(fourPrecisionRedStyle, true);
		fourPrecisionRedStyle.setDataFormat(format.getFormat("#,##0.0000"));
		fourPrecisionRedStyle.setFont(redFont);
		//表头样式
		titleSyle = wb.createCellStyle();
		ExcelUtils.setAlign(titleSyle, ExcelUtils.left, ExcelUtils.center, true);
		ExcelUtils.setFrame(titleSyle, true);
		ExcelUtils.setFont(titleSyle, wb.createFont(),"微软雅黑", 12, true);// 字体
		titleSyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);// 设置背景色
		titleSyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//核算编号样式
		accountingCodeStyle = wb.createCellStyle();
		ExcelUtils.setAlign(accountingCodeStyle, ExcelUtils.left, ExcelUtils.center, true);
		accountingCodeStyle.setFont(commFont);
		ExcelUtils.setFrame(leftStyle, true);
		accountingCodeStyle.setDataFormat(format.getFormat("0"));
	}
	
	/**
	 * 创建excel纵向表头
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            sheet
	 */
	private void fillListHeaderCell(Workbook wb, Sheet sheet,Map<String, Object> m) {
		sheet.setColumnWidth(0, (short) (120 * 80));// 设置当前列单元格宽度
		Row row = null;
		Cell cell = null;
		int i = 1;
		// 填充成本细项单元格
		row = sheet.createRow(0);
		cell = row.createCell(0);
		cell.setCellStyle(titleSyle);
		cell.setCellValue("成本细项");
		// 逐行添加每行的第一个单元格
		for (Object temp : this.listTitle(m).values()) {
			row = sheet.createRow(i);
			cell = row.createCell(0);
			switch (temp.toString()) {
			 case "原料":
			    sheet.addMergedRegion(new CellRangeAddress(i, i + (materialsSize == 0?1:materialsSize) - 1, 0, 0));
				addMergedRegionBorder(sheet, i, i + (materialsSize == 0?1:materialsSize) - 1, 0, 0);
				i = i + materialsSize;
				break;
			case "内包装":
				sheet.addMergedRegion(new CellRangeAddress(i, i + (npackagsSize == 0?1:npackagsSize) - 1, 0, 0));
				addMergedRegionBorder(sheet, i, i + (npackagsSize == 0?1:npackagsSize) - 1, 0, 0);
				i = i + npackagsSize;
				break;
			case "外包装":
				sheet.addMergedRegion(new CellRangeAddress(i, i + (wpackagsSize == 0?1:wpackagsSize) - 1, 0, 0));
				addMergedRegionBorder(sheet, i, i + (wpackagsSize == 0?1:wpackagsSize) - 1, 0, 0);
				i = i + wpackagsSize;
				break;
			case "损耗":
				sheet.addMergedRegion(new CellRangeAddress(i, i + (wastagesSize == 0?1:wastagesSize) - 1, 0, 0));
				addMergedRegionBorder(sheet, i, i + (wastagesSize == 0?1:wastagesSize) - 1, 0, 0);
				i = i + wastagesSize;
				break;
			default:
				i++;
				break;
			}
			if (temp.equals("扣除包装后原材料总成本")||temp.equals("内外包装材料价格小计")||temp.equals("其它成本小计")) {
				cell.setCellStyle(leftRedStyle);
			}else{
				cell.setCellStyle(leftStyle);
			}
			cell.setCellValue(temp.toString());
		}
	}
	
	/**
	 * 为sheet填充数据
	 * 
	 * @param listMaps
	 *            纵向表头数据
	 * @param list
	 *            要填充的数据
	 * @param wb
	 *            工作薄
	 * @param sheet
	 *            sheet表
	 * @param map
	 *            存储各种状态的Map
	 * @throws ParseException  转换异常
	 */
	private void fillDataCell(Map<String, Object> listHeaderMaps, List<TotalCostAnalysis> list, Workbook wb, Sheet sheet, Map<String, Object> map) throws ParseException {
		// 解析页面传的 每列的基本信息
		JSONArray jsonArray = JSONArray.fromObject(map.get("data"));
		JSONObject obj = null;
		
		Map<String, Date> maxDateMap = new HashMap<String, Date>();
		for (int i = 0; i < jsonArray.size(); i++) {
			obj = jsonArray.getJSONObject(i);
			String key = obj.getString("merchandiseCode") + obj.getString("supplierCode");
			Date maxDate = maxDateMap.get(key);
			Date curDate = DateUtils.formatStrToDate(obj.getString("quotedDate"), "yyyy-MM-dd HH:mm:ss");
			if (maxDate == null || maxDate.before(curDate)){
				maxDateMap.put(key, curDate);
			}
		}
		List<Integer> indexs = new ArrayList<>();
		for (int i = 0; i < jsonArray.size(); i++) {
			obj = jsonArray.getJSONObject(i);
			String key = obj.getString("merchandiseCode") + obj.getString("supplierCode");
			if (maxDateMap.get(key).equals(DateUtils.formatStrToDate(obj.getString("quotedDate"), "yyyy-MM-dd HH:mm:ss"))) {
				indexs.add(i);
			}
		}
			
		int nextFillCellIndex = 1;
		for (int i = 0; i < list.size(); i++) {
			boolean noLast = true;
			for (int j = 0; j < indexs.size(); j++) {
				if (Integer.valueOf(indexs.get(j)) == i) {
					noLast = false;
				}
			}
			nextFillCellIndex = writeOneListDate(wb, sheet, listHeaderMaps, nextFillCellIndex, jsonArray.getJSONObject(i), list.get(i),noLast);
		}
	}

	/**
	 * 填充一列数据
	 * 
	 * @param wb
	 *            工作薄
	 * @param sheet
	 *            sheet
	 * @param listHeaderMaps
	 *            纵向表头的数据
	 * @param nextFillCellIndex
	 *            要填充下一个单元格的位置
	 * @param jsonObject
	 *            该列的基本信息,包含表头、商品编号、供应商编号、核算编号
	 * @param data
	 *            纵向的数据
	 * @param map
	 *            各种状态条件
	 * @return 下一次要从第几个单元格写
	 */
	private int writeOneListDate(Workbook wb, Sheet sheet, Map<String, Object> listHeaderMaps, int nextFillCellIndex, JSONObject jsonObject, TotalCostAnalysis data,boolean noLast){
		Row row = null;             //行
		Cell cell = null;           //单元格
		JSONObject object = null;   //空的JSONObject,以后省的定义
		JSONArray array = null;     //空的JSONArray,以后省的定义
		String key = null;          //纵向表头的key
		Object value = null;        //值
		String remarks = null;      //备注
		String units = null;        //计量单位
		int rowIndex = 1;           //记录写到哪一行
		boolean isThisApplication = false;  //该对象是否是本次申请商品
		double countSum = 0;
		double costSum = 0;
		double avgSum = 0;
		CellStyle valueStyle = null;
		CellStyle remarksStyle = null;
		CellStyle unitsStyle = null;
		// 把该列的数据转换为Json对象,以便取值
		JsonConfig jc = new JsonConfig();
		jc.registerDefaultValueProcessor(BigDecimal.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(@SuppressWarnings("rawtypes") Class c) {
				return "";
			}
		});
		jc.registerDefaultValueProcessor(Integer.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(@SuppressWarnings("rawtypes") Class c) {
				return "";
			}
		});
		jc.registerDefaultValueProcessor(String.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(@SuppressWarnings("rawtypes") Class c) {
				return "";
			}
		});
		JSONObject jsonData = JSONObject.fromObject(data,jc);
		// 获取有多少个地区
		int maxColSpan = JSONArray.fromObject(jsonData.get("accountingRegionList")).size();
		//是否是本次申请商品
		if (maxColSpan<2) {
			maxColSpan = 2;
		}
		//判断该列是否是本次申请商品
		if (jsonObject.getString("rowTitleName").contains("本次申请商品")) {
			isThisApplication = true;
		}else{
			isThisApplication = false;
		}
		//判断该列是否要显示备注
		if (isShowAllRemarks||(isShowThisRemarks&&isThisApplication&&!noLast)) {
			maxColSpan++;
		}
		//是否显示计量单位
		if (isShowUnits) {
			maxColSpan++;
		}
		
		// 填充第一行数据
		row = sheet.getRow(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1));
		addMergedRegionBorder(sheet, 0, 0, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1);
		cell = row.createCell(nextFillCellIndex);
		cell.setCellStyle(titleSyle);
		cell.setCellValue(jsonObject.getString("rowTitleName"));
		// 把纵向表头转换为可迭代的对象
		Set<Entry<String, Object>> s = listHeaderMaps.entrySet();
		Iterator<Entry<String, Object>> listHeaders = s.iterator();
		// 循环遍历根据纵向表头中hashMap中的key从jsonData中取对应的值
		while (listHeaders.hasNext()) {
			//默认这三个样式都是左对齐
			valueStyle = leftStyle;
			remarksStyle = leftStyle;
			unitsStyle = leftStyle;
			
			value = "";
			remarks = "";
			units = formatNum(jsonData.getJSONObject("accounting").getString("quantity"), 3) + jsonData.getJSONObject("accounting").getString("units");
			// 每次拿取一个键值对的元素
			Entry<String, Object> temp = listHeaders.next();
			key = temp.getKey();
			try {
			if ("INLAND".equals(inlandImport)) {
			//非进口
				switch (key) {
				case "merchandiseName":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseName").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionName"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseName");
					break;
				case "merchandiseCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseCode").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionCode"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseCode");
					break;
				case "supplierName":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierName").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionSupplierName"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierName");
					break;
				case "supplierCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierCode").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionSupplierCode"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierCode");
					break;
				case "units":
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						value = "";
					}else{
					    value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"quantity").equals("")?"":(formatNum(jsonData.getJSONObject("accounting").getString("quantity"),3) + jsonData.getJSONObject("accounting").getString("units"));
					}
					break;
				case "quotedDate":
					value = JSONObject.toBean((JSONObject)(jsonData.getJSONObject("accounting").get("quotedDate") == null?"":jsonData.getJSONObject("accounting").getJSONObject("quotedDate")), Date.class);
					value = DateUtils.formatDateToStr((Date) value, "yyyy-MM-dd HH:mm:ss");
					break;
				case "accountingCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode");
					valueStyle = accountingCodeStyle;
					break;
				case "updateby":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"updateUserName");
					break;
				case "region":
					array = jsonData.getJSONArray("accountingRegionList");
					for (int i = 0; i < array.size(); i++) {
						row = sheet.getRow(rowIndex);
						cell = row.createCell(nextFillCellIndex + i);
						cell.setCellStyle(leftStyle);
						try {
							object = array.getJSONObject(i);
							cell.setCellValue(object.getString("region"));
						} catch (Exception e) {
							cell.setCellValue("");
						}
					}
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
						if (isShowUnits) {  //显示报价计量单位并且不显示备注
							cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							cell.setCellStyle(leftStyle);
							sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (50 * 70));// 设置当前行单元格宽度
							cell.setCellValue("报价计量单位");
						}else{
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
						}
					}else{ 
			            if (isShowUnits) { //显示报价计量单位
			            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
							cell.setCellStyle(leftStyle);
							sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (50 * 70));// 设置当前行单元格宽度
							cell.setCellValue("报价计量单位");
						}else{ //不显示报价计量单位
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
						}
			                
			            cell = row.createCell(nextFillCellIndex + maxColSpan-1);
						cell.setCellStyle(leftStyle);
						sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (60 * 80));// 设置当前行单元格宽度
						cell.setCellValue("");
					}
					rowIndex++;
					continue;
				case "material":
					array = jsonData.getJSONArray("ingredientItemList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						countSum += object.get("inputCount") == ""?0:object.getDouble("inputCount");
						costSum += object.get("inputCost") == ""?0:object.getDouble("inputCost");
						avgSum += object.get("avgCost") == ""?0:object.getDouble("avgCost");
								
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue((object.getString("materialType").equalsIgnoreCase("ZL")?"主料-":"辅料-")+object.getString("materialName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(fourPrecisionStyle);
						cell.setCellValue(formatNum(object.getDouble("avgCost"),4));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								if (!setUnits||!jsonObject.getString("rowTitleName").contains("报价单位转换后")) {
									cell.setCellValue("1.000"+jsonData.getJSONObject("accounting").getString("units"));
								}else{
									cell.setCellValue(units);
								}
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								if (!setUnits||!jsonObject.getString("rowTitleName").contains("报价单位转换后")) {
									cell.setCellValue("1.000"+jsonData.getJSONObject("accounting").getString("units"));
								}else{
									cell.setCellValue(units);
								}
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							remarks = "原料采购价格(元/kg) : " + formatNum(object.getString("purchasePrice"),4) + "\n" +
				      				  "原料投入量(kg) : " + formatNum(object.getString("inputCount"),4) + "\n" +
				      				  "原料投入成本(元) : " + formatNum(object.getString("inputCost"),4) + "\n" +
		      						  "备注   : " + getJSONObjectValue(object, "remarks");
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
				
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+materialsSize,isThisApplication,noLast);
					rowIndex = rowIndex+materialsSize;
					continue;
				case "npackag":
					array = jsonData.getJSONArray("accountingNPackagList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "npackagName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(threePrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),3));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							
							switch (getJSONObjectValue(object, "npackagType")) {
							case "FHD_JM":
							case "FHD_LSM":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
										  "公斤价格(元) : " + formatNum(object.getString("kgPrice"),3) + "\n" + 
										  "重量占比(%) : " + formatNum(object.getString("weightProportion"),3) + "\n" + 
										  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "FHD_ZD":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
										  "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
										  "单个克重(g) : " + formatNum(object.getString("weight"),3) + "\n" +
										  "单价(元) : " + formatNum(object.getString("unitsPrice"),3) + "\n" + 
										  "公斤价格(元) : " + formatNum(object.getString("kgPrice"),3) + "\n" + 
										  "数量  : " + formatNum(object.getString("quantity"),3) + "\n" + 
										  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ST":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
									      "单个克重(g) : " + formatNum(object.getString("weight"),3) + "\n" +
									      "公斤价格(元) : " + formatNum(object.getString("kgPrice"),3) + "\n" + 
									      "重量占比(%) : " + formatNum(object.getString("weightProportion"),3) + "\n" + 
									      "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "TYJ":
								remarks = "单个克重(g) : " + formatNum(object.getString("weight"),3) + "\n" +
									      "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
									      "单价(元) : " + formatNum(object.getString("unitsPrice"),3) + "\n" + 
									      "数量  : " + formatNum(object.getString("quantity"),3) + "\n" + 
									      "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "NDD":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" +
								          "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
								          "单价(元) : " + formatNum(object.getString("unitsPrice"),3) + "\n" + 
								          "数量  : " + formatNum(object.getString("quantity"),3) + "\n" + 
								          "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "BQ":
							case "ZZL":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" +
							        	  "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
							        	  "数量  : " + formatNum(object.getString("quantity"),3) + "\n" + 
							        	  "工艺要求  : " + getJSONObjectValue(object, "technologyRequirements") + "\n" + 
							        	  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ELSE":
								remarks = "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							default:
								remarks = "";
								break;
							}
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+npackagsSize,isThisApplication,noLast);
					rowIndex = rowIndex+npackagsSize;
					continue;
				case "wpackag":
					array = jsonData.getJSONArray("accountingWPackagList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "wpackagName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(threePrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),3));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							
							switch (getJSONObjectValue(object, "wpackagType")) {
							case "FXD":
								remarks =  "单价(元/米) : " + formatNum(object.getString("unitsPrice"),3) + "\n" + 
										   "使用量(元/米) : " + formatNum(object.getString("useQuantity"),3) + "\n" + 
										   "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ZX":
								remarks =  "具体材质说明:" + getJSONObjectValue(object, "texture") + "\n" + 
										   "长(cm) : " + formatNum(object.getString("length"),3) + "\n" + 
										   "宽(cm) : " + formatNum(object.getString("width"),3) + "\n" + 
										   "高(cm) : " + formatNum(object.getString("height"),3) + "\n" + 
										   "纸箱用料面积(㎡) : " + formatNum(object.getString("area"),3) + "\n" + 
										   "单价(元/只) : " + formatNum(object.getString("unitsPrice"),3) + "\n" + 
										   "纸箱用料单价(元/㎡) : " + formatNum(object.getString("ylUnitsPrice"),3) + "\n" + 
								           "箱规  : " + getJSONObjectValue(object, "specification") + "\n" + 
							               "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ELSE":
								remarks =  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							default:
								remarks = "";
								break;
							}
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+wpackagsSize,isThisApplication,noLast);
					rowIndex = rowIndex+wpackagsSize;
					continue;
				case "wastage":
					array = jsonData.getJSONArray("accountingWastageList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "wastageType"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(threePrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),3));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							remarks = getJSONObjectValue(object, "remarks");
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+wastagesSize,isThisApplication,noLast);
					rowIndex = rowIndex+wastagesSize;
					continue;
				case "yield":// 得率行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("yieldValue"),2) + (formatNum(jsonData.getJSONObject("accountingCostItem").getString("yieldValue"),2).equals("")?"":"%");
					remarks = "投入量总计(kg) : " + formatNum(countSum,4) + "\n" +
							  "投入成本总计(元) : " + formatNum(costSum,4) + "\n" +
							  "平均成品原料成本总计(元/kg) : " + formatNum(avgSum,2) + "\n" +
							  "产品生产量(kg) : " + formatNum(jsonData.getJSONObject("ingredient").getString("productCount"),4) + "\n" +
							  "成品含水率(%) : " + jsonData.getJSONObject("ingredient").getString("moisture") + "\n" +
							  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "yieldRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "zlsubtotalValue": // 主料小计行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("zlsubtotalValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "zlsubtotalRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "flsubtotalValue":// 辅料小计行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("flsubtotalValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "flsubtotalRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "iTotalcostValue":// 商品总投入成本
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("itotalcostValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "itotalcostRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "mTotalcostValue":// 原料总投入成本
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("mtotalcostValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "mtotalcostRemarks");
					break;
				case "packagproportionValue":// 包装占比
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("packagproportionValue"),2) + (formatNum(jsonData.getJSONObject("accountingCostItem").getString("packagproportionValue"),2).equals("")?"":"%");
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "packagproportionRemarks");
					break;
				case "deductptcostValue":// 扣除包装后原材料总成本
					valueStyle = twoPrecisionRedStyle;
					remarksStyle = leftRedStyle;
					unitsStyle = leftRedStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("deductptcostValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "deductptcostRemarks");
					break;
				case "nwpackagsubtotalValue":// 内外包装材料价格小计
					valueStyle = twoPrecisionRedStyle;
					remarksStyle = leftRedStyle;
					unitsStyle = leftRedStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("nwpackagsubtotalValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "nwpackagsubtotalRemarks");
					break;
				case "wecPrice":// 水电煤
					valueStyle = threePrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingWec").getString("price"),3);
					remarks =   "耗水(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("water"),3) + "\n" + 
							    "耗油(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("oil"),3) + "\n" + 
								"耗电(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("electricity"),3) + "\n" + 
								"耗气(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("gas"),3) + "\n" + 
								"耗煤(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("coal"),3) + "\n" + 
								"合计(元/kg成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("total"),3) + "\n" + 
								"备注 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingWec"),"remarks");
					break;
				case "sbzjwhPrice":// 设备折旧及维护
					valueStyle = threePrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("price"),3);
			  	    remarks = "设备总价(万元) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("totalPrice"),3) + "\n" + 
					          "折旧年限(年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("ageLimit"),3) + "\n" + 
			  	    		  "折旧值(万元/年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("depreciation"),3) + "\n" + 
					          "产能值(t成品/年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("capacity"),3)+ "\n" + 
					          "合计折旧值(元/kg成品) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("total"),3) + "\n" + 
			  	    		  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingSbzjwh"),"remarks");
					break;
				case "ampPrice":// 人工
					valueStyle = threePrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingManpower").getString("price"),3);
				    remarks = "车间工人数(人次) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("manpowerCount"),3)+ "\n" + 
							  "平均工资(元/人/月) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("avgWage"),3)+ "\n" + 
							  "月产量(t) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("monthCapacity"),3)+ "\n" + 
							  "每kg成品(元/kg) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("unitsWage"),3)+ "\n" + 
							  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingManpower"),"remarks");
					break;
				case "amaPrice":// 管理各地区
					valueStyle = threePrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingManage").getString("price"),3);
					array = jsonData.getJSONArray("accountingManageRegionList");
					for (int i = 0; i < array.size(); i++) {
						try {
							object = array.getJSONObject(i);
							remarks += "进货地区" + object.getString("region") + " : \n" + 
							           "占比(%) : " + formatNum(object.getString("proportion"),3) + "\n";
						} catch (Exception e) {
							remarks += "进货地区" + " : /\n" + 
							           "占比(%) : " + "/\n";
						}
					}
					remarks += "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingManage"),"remarks");
					break;
				case "afrPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingFreightRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(threePrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),3));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            for (int i = 0; i < array.size(); i++) {
								 try {
									object = array.getJSONObject(i);
									 remarks += "进货地区" + object.getString("region") + " : \n" + 
											    "总公里数(km) : "+ formatNum(object.getString("sumKm"),3) + "\n";
								} catch (Exception e) {
									remarks += "进货地区" + " : /\n" + 
											   "总公里数(km) : "+ "/\n";
								}
							}
							remarks += "单位成本(元/"+jsonData.getJSONObject("accountingFreight").getString("units")+") : " + formatNum(jsonData.getJSONObject("accountingFreight").getString("unitsCost"),3) + "\n"+
							           "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingFreight"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "atrPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingTaxRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(threePrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),3));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            for (int i = 0; i < array.size(); i++) {
								 try {
									object = array.getJSONObject(i);
									 remarks += "进货地区" + object.getString("region") + " : \n" + 
											    "占比(%) : "+ formatNum(object.getString("proportion"),3) + "\n";
								} catch (Exception e) {
									remarks += "进货地区" + " : /\n" + 
											   "占比 (%) : "+ "/\n";
								}
							}
							remarks += "税率(%) : " + formatNum(jsonData.getJSONObject("accountingTax").getString("taxRate"),3) + "\n"+
							           "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingTax"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "aprPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingProfitRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(threePrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),3));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            for (int i = 0; i < array.size(); i++) {
								 try {
									object = array.getJSONObject(i);
									 remarks += "进货地区" + object.getString("region") + " : \n" + 
											    "占比(%) : "+ formatNum(object.getString("proportion"),4) + "\n";
								} catch (Exception e) {
									remarks += "进货地区" + ": /\n" + 
										       "占比(%) : " + "/\n";
								}
							}
				            remarks += "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingProfit"),"remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "subTotal":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingElsesubtotalRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,true);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(twoPrecisionRedStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("subtotal"),2));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(leftRedStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(leftRedStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            remarks = getJSONObjectValue(jsonData.getJSONObject("accountingElsesubtotal"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftRedStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "sumPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingAggregateRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(twoPrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								}else{
									cell.setCellValue(formatNum(object.get("sumPrice"),2));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            remarks = getJSONObjectValue(jsonData.getJSONObject("accountingAggregate"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "mcpPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("merchandiseContractPrices");
					for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
						row = sheet.getRow(rowIndex);
						cell = row.createCell(nextFillCellIndex + i);
						cell.setCellStyle(twoPrecisionStyle);
						if (isThisApplication) {
							cell.setCellValue("");
						} else {
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								}else{
									cell.setCellValue(formatNum(object.get("price"),2));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
					}
					if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
						if (isShowUnits) {  //显示报价计量单位并且不显示备注
							cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
							cell.setCellStyle(unitsStyle);
							// 若果设置了对比单位,则统一使用对比单位
							if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
								cell.setCellValue("");
							}else{
								cell.setCellValue(units);
							}
						}else{
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 1));
						}
					}else{ 
			            if (isShowUnits) { //显示报价计量单位
			            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 3));
							cell.setCellStyle(unitsStyle);
							// 若果设置了对比单位,则统一使用对比单位
							if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
								cell.setCellValue("");
							}else{
								cell.setCellValue(units);
							}
						}else{ //不显示报价计量单位
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
						}
						remarks = "";
						cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(remarks);
					}
					rowIndex++;
					continue;
				default:
					break;
				}
			} else {
			//进口
				switch (key) {
				case "merchandiseName":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseName").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionName"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseName");
					break;
				case "merchandiseCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseCode").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionCode"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"merchandiseCode");
					break;
				case "supplierName":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierName").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionSupplierName"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierName");
					break;
				case "supplierCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierCode").equals("")?getJSONObjectValue(jsonData.getJSONObject("accounting"),"intentionSupplierCode"):getJSONObjectValue(jsonData.getJSONObject("accounting"),"supplierCode");
					break;
				case "units":
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						value = "";
					}else{
					    value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"quantity").equals("")?"":(formatNum(jsonData.getJSONObject("accounting").getString("quantity"),3) + jsonData.getJSONObject("accounting").getString("units"));
					}
					break;
				case "quotedDate":
					value = JSONObject.toBean((JSONObject) (jsonData.getJSONObject("accounting").get("quotedDate") == null?"":jsonData.getJSONObject("accounting").getJSONObject("quotedDate")), Date.class);
					value = DateUtils.formatDateToStr((Date) value, "yyyy-MM-dd HH:mm:ss");
					break;
				case "accountingCode":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode");
					valueStyle = accountingCodeStyle;
					break;
				case "updateby":
					value = getJSONObjectValue(jsonData.getJSONObject("accounting"),"updateUserName");
					break;
				case "region":
					array = jsonData.getJSONArray("accountingRegionList");
					for (int i = 0; i < array.size(); i++) {
						row = sheet.getRow(rowIndex);
						cell = row.createCell(nextFillCellIndex + i);
						cell.setCellStyle(leftStyle);
						try {
							object = array.getJSONObject(i);
							cell.setCellValue(object.getString("region"));
						} catch (Exception e) {
							cell.setCellValue("");
						}
					}
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
						if (isShowUnits) {  //显示报价计量单位并且不显示备注
							cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							cell.setCellStyle(leftStyle);
							sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (50 * 70));// 设置当前行单元格宽度
							cell.setCellValue("报价计量单位");
						}else{
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
						}
					}else{ 
			            if (isShowUnits) { //显示报价计量单位
			            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
							cell.setCellStyle(leftStyle);
							sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (50 * 70));// 设置当前行单元格宽度
							cell.setCellValue("报价计量单位");
						}else{ //不显示报价计量单位
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
						}
			                
			            cell = row.createCell(nextFillCellIndex + maxColSpan-1);
						cell.setCellStyle(leftStyle);
						sheet.setColumnWidth(nextFillCellIndex + maxColSpan-1, (short) (60 * 80));// 设置当前行单元格宽度
						cell.setCellValue("");
					}
					rowIndex++;
					continue;
				case "material":
					array = jsonData.getJSONArray("ingredientItemList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						countSum += object.get("inputCount") == ""?0:object.getDouble("inputCount");
						costSum += object.get("inputCost") == ""?0:object.getDouble("inputCost");
						avgSum += object.get("avgCost") == ""?0:object.getDouble("avgCost");
						
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue((object.getString("materialType").equalsIgnoreCase("ZL")?"主料-":"辅料-")+object.getString("materialName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(fourPrecisionStyle);
						cell.setCellValue(formatNum(object.getDouble("avgCost"),4));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								if (!setUnits||!jsonObject.getString("rowTitleName").contains("报价单位转换后")) {
									cell.setCellValue("1.000"+jsonData.getJSONObject("accounting").getString("units"));
								}else{
									cell.setCellValue(units);
								}
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								if (!setUnits||!jsonObject.getString("rowTitleName").contains("报价单位转换后")) {
									cell.setCellValue("1"+jsonData.getJSONObject("accounting").getString("units"));
								}else{
									cell.setCellValue(units);
								}
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							remarks = "原料采购价格(元/kg) : " + formatNum(object.getString("purchasePrice"),4) + "\n" +
				      				  "原料投入量(kg) : " + formatNum(object.getString("inputCount"),4) + "\n" +
				      				  "原料投入成本(元) : " + formatNum(object.getString("inputCost"),4) + "\n" +
		      						  "备注  : " + getJSONObjectValue(object, "remarks");
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+materialsSize,isThisApplication,noLast);
					rowIndex = rowIndex+materialsSize;
					continue;
				case "npackag":
					array = jsonData.getJSONArray("accountingNPackagList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "npackagName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(fourPrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),4));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							
							switch (getJSONObjectValue(object, "npackagType")) {
							case "FHD_JM":
							case "FHD_LSM":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
										  "公斤价格(元) : " + formatNum(object.getString("kgPrice"),4) + "\n" + 
										  "重量占比(%) : " + formatNum(object.getString("weightProportion"),4) + "\n" + 
										  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "FHD_ZD":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
										  "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
										  "单个克重(g) : " + formatNum(object.getString("weight"),4) + "\n" +
										  "单价(元) : " + formatNum(object.getString("unitsPrice"),4) + "\n" + 
										  "公斤价格(元) : " + formatNum(object.getString("kgPrice"),4) + "\n" + 
										  "数量  : " + formatNum(object.getString("quantity"),4) + "\n" + 
										  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ST":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" + 
									      "单个克重(g) : " + formatNum(object.getString("weight"),4) + "\n" +
									      "公斤价格(元) : " + formatNum(object.getString("kgPrice"),4) + "\n" + 
									      "重量占比(%) : " + formatNum(object.getString("weightProportion"),4) + "\n" + 
									      "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "TYJ":
								remarks = "单个克重(g) : " + formatNum(object.getString("weight"),4) + "\n" +
									      "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
									      "单价(元) : " + formatNum(object.getString("unitsPrice"),4) + "\n" + 
									      "数量  : " + formatNum(object.getString("quantity"),4) + "\n" + 
									      "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "NDD":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" +
								          "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
								          "单价(元) : " + formatNum(object.getString("unitsPrice"),4) + "\n" + 
								          "数量  : " + formatNum(object.getString("quantity"),4) + "\n" + 
								          "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "BQ":
							case "ZZL":
								remarks = "具体材质&厚度  : " + getJSONObjectValue(object, "texture") + "\n" +
							        	  "尺寸(cm) : " + getJSONObjectValue(object, "materialSize") + "\n" + 
							        	  "数量  : " + formatNum(object.getString("quantity"),4) + "\n" + 
							        	  "工艺要求  : " + getJSONObjectValue(object, "technologyRequirements") + "\n" + 
							        	  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ELSE":
								remarks = "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							default:
								remarks = "";
								break;
							}
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+npackagsSize,isThisApplication,noLast);
					rowIndex = rowIndex+npackagsSize;
					continue;
				case "wpackag":
					array = jsonData.getJSONArray("accountingWPackagList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "wpackagName"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(fourPrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),4));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							
							switch (getJSONObjectValue(object, "wpackagType")) {
							case "FXD":
								remarks =  "单价(元/米) : " + formatNum(object.getString("unitsPrice"),4) + "\n" + 
										   "使用量(元/米) : " + formatNum(object.getString("useQuantity"),4) + "\n" + 
										   "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ZX":
								remarks =  "具体材质说明:" + getJSONObjectValue(object, "texture") + "\n" + 
										   "长(cm) : " + formatNum(object.getString("length"),4) + "\n" + 
										   "宽(cm) : " + formatNum(object.getString("width"),4) + "\n" + 
										   "高(cm) : " + formatNum(object.getString("height"),4) + "\n" + 
										   "纸箱用料面积(㎡) : " + formatNum(object.getString("area"),4) + "\n" + 
										   "单价(元/只) : " + formatNum(object.getString("unitsPrice"),4) + "\n" + 
										   "纸箱用料单价(元/㎡) : " + formatNum(object.getString("ylUnitsPrice"),4) + "\n" + 
								           "箱规  : " + getJSONObjectValue(object, "specification") + "\n" + 
							               "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							case "ELSE":
								remarks =  "备注 : " + getJSONObjectValue(object, "remarks");
								break;
							default:
								remarks = "";
								break;
							}
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+wpackagsSize,isThisApplication,noLast);
					rowIndex = rowIndex+wpackagsSize;
					continue;
				case "wastage":
					array = jsonData.getJSONArray("accountingWastageList");
					for (int j = 0; j < array.size(); j++) {
						addMergedRegionBorder(sheet, rowIndex+j, rowIndex+j, nextFillCellIndex+2, nextFillCellIndex+maxColSpan-1);
						object = array.getJSONObject(j);
						if (object == null) {
							continue;
						}
						row = sheet.getRow(rowIndex+j);
						cell = row.createCell(nextFillCellIndex);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(getJSONObjectValue(object, "wastageType"));
						
						cell = row.createCell(nextFillCellIndex+1);
						cell.setCellStyle(fourPrecisionStyle);
						cell.setCellValue(formatNum(object.get("price"),4));
						
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
								cell = row.createCell(nextFillCellIndex+maxColSpan-1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{
							if (isShowUnits) {
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
								cell = row.createCell(nextFillCellIndex+maxColSpan-2);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex+j, rowIndex+j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
							}
							cell = row.createCell(nextFillCellIndex+maxColSpan-1);
							remarks = getJSONObjectValue(object, "remarks");
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					fillNoHasAccounting(sheet,rowIndex,nextFillCellIndex,maxColSpan,rowIndex+array.size(),rowIndex+wastagesSize,isThisApplication,noLast);
					rowIndex = rowIndex+wastagesSize;
					continue;
				case "yield":// 得率行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("yieldValue"),2) + (formatNum(jsonData.getJSONObject("accountingCostItem").getString("yieldValue"),2).equals("")?"":"%");
					remarks = "投入量总计(kg) : " + formatNum(countSum,4) + "\n" +
							  "投入成本总计(元) : " + formatNum(costSum,4) + "\n" +
							  "平均成品原料成本总计(元/kg) : " + formatNum(avgSum,2) + "\n" +
							  "产品生产量(kg) : " + formatNum(jsonData.getJSONObject("ingredient").getString("productCount"),4) + "\n" +
							  "成品含水率(%) : " + jsonData.getJSONObject("ingredient").getString("moisture") + "\n" +
							  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "yieldRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "zlsubtotalValue": // 主料小计行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("zlsubtotalValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "zlsubtotalRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "flsubtotalValue":// 辅料小计行
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("flsubtotalValue"),2);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "flsubtotalRemarks");
					if (!setUnits) {
						units = "1.000"+jsonData.getJSONObject("accounting").getString("units");
					}
					break;
				case "factoryPrice":// 商品报价
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingFactoryPrice").getString("price"),4);
					remarks =   "币种 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingFactoryPrice"), "currency") + "\n" + 
						     	"付款方式 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingFactoryPrice"), "paymentType") + "\n" + 
							    "备注 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingFactoryPrice"), "remarks");
					break;
				case "exchangeRate":// 汇率
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingExchangerate").getString("exchangerate"), 4) + (formatNum(jsonData.getJSONObject("accountingExchangerate").getString("exchangerate"), 4).equals("")?"":"%");
					if(jsonData.getJSONObject("accountingExchangerate").get("referenceDate") == null){
						startTime = "";
					}else{
						try {
							startTime = DateUtils.formatDateToStr((Date)JSONObject.toBean((JSONObject) jsonData.getJSONObject("accountingExchangerate").get("referenceDate"), Date.class), "yyyy-MM-dd");
						} catch (Exception e) {
							startTime = "";
						}
					}
					remarks =   "参考日期 : "+  startTime + "\n" + 
						        "参考银行 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingExchangerate"), "referenceBank") + "\n" + 
							    "备注 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingExchangerate"), "remarks");
					break;
				case "rmbSettlementPrice"://商品人民币结算价格
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("rmbSettlementPrice"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "rmbSettlementPriceRemarks");	
					break;
				case "oceanfreight"://海运费/空运费
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingOceanfreight").getString("price"),4);
					String containerType = getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"), "containerTypeString");
					if(jsonData.getJSONObject("accountingOceanfreight").get("transportStartDate") == null){
						startTime = "";
					}else{
						try {
							startTime = DateUtils.formatDateToStr((Date)JSONObject.toBean((JSONObject) jsonData.getJSONObject("accountingOceanfreight").get("transportStartDate"), Date.class), "yyyy-MM-dd");
						} catch (Exception e) {
							startTime = "";
						}
					}
					if(jsonData.getJSONObject("accountingOceanfreight").get("transportEndDate") == null){
						endTime = "";
					}else{
						try {
							endTime = DateUtils.formatDateToStr((Date)JSONObject.toBean((JSONObject) jsonData.getJSONObject("accountingOceanfreight").get("transportEndDate"), Date.class), "yyyy-MM-dd");
						} catch (Exception e) {
							endTime = "";
						}
					}
							remarks = "运输时段 : " + startTime+"到"+endTime+"\n" + 
  		               		   		  "出发港 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"starting") + "\n" + 
  		               		   		  "到达港 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"destination") + "\n" + 
  		               		   		  "货柜类型 : " + containerType + "\n" + 
  		               		   		  "货柜尺寸 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"containerSize") + "\n" + 
  		               		   		  "单价(元/货柜) : " + formatNum(jsonData.getJSONObject("accountingOceanfreight").getString("unitPrice"),4) + "\n" + 
  		               		   		  "货柜内容物数量&重量 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"containerCapacity") + "\n" + 
  		               		   		  "计算方式 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"computeType") + "\n" + 
  		               		   		  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingOceanfreight"),"remarks");	
					break;
				case "orderFee"://换单费
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("updateOrderFee"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "updateOrderFeeRemarks");
					break;
				case "premium"://保险费
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("premium"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "premiumRemarks");	
					break;
				case "customscharges"://报关服务费
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCustomscharges").getString("price"),4);
					remarks =  "报关费 : " + jsonData.getJSONObject("accountingCustomscharges").getString("customscharges") +"\n" + 
		               		   "港杂费 : " + jsonData.getJSONObject("accountingCustomscharges").getString("portSurcharge") + "\n" + 
		               		   "滞港费 : " + jsonData.getJSONObject("accountingCustomscharges").getString("demurrageCharge") + "\n" + 
		               		   "污箱费 : " + jsonData.getJSONObject("accountingCustomscharges").getString("containerDirtynessChange") + "\n" + 
		               		   "其他费用 : " + jsonData.getJSONObject("accountingCustomscharges").getString("elseFee") + "\n" + 
		               		   "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCustomscharges"), "remarks");	
					break;
				case "importFeeTotal"://进口费用小计
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("importFeeTotal"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "importFeeTotalRemarks");	
					break;
				case "customsduties"://关税
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCustomsduties").getString("price"),4);
					remarks =   "HS编码 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCustomsduties"), "hsCode") +"\n" + 
		               		   	"税率(%) : " + formatNum(jsonData.getJSONObject("accountingCustomsduties").getString("taxRate"),4) + "\n" + 
		               		   	"关税计算方式 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCustomsduties"), "customsdutiesComputeType") + "\n" + 
		               		    "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingCustomsduties"), "remarks");
					break;
				case "addedvaluetax"://增值税
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingAddedvaluetax").getString("price"),4);
					remarks =   "税率(%) : " + formatNum(jsonData.getJSONObject("accountingAddedvaluetax").getString("taxRate"),4) +"\n" + 
		               			"增值税计算方式  : " + getJSONObjectValue(jsonData.getJSONObject("accountingAddedvaluetax"), "addedvaluetaxComputeType") + "\n" + 
	               		        "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingAddedvaluetax"), "remarks");	
					break;
				case "cdAvtTotal"://关税、增值税小计
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("cdAvtTotal"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "cdAvtTotalRemarks");	
					break;
				case "customsClearanceTotal"://清关后商品总成本
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("customsClearanceTotal"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "customsClearanceTotalRemark");	
					break;
				case "packagproportionValue":// 包装占比
					valueStyle = twoPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("packagproportionValue"),2) + (formatNum(jsonData.getJSONObject("accountingCostItem").getString("packagproportionValue"),2).equals("")?"":"%");
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "packagproportionRemarks");
					break;
				case "deductptcostValue":// 扣除包装后原材料总成本
					valueStyle = fourPrecisionRedStyle;
					remarksStyle = leftRedStyle;
					unitsStyle = leftRedStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("deductptcostValue"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "deductptcostRemarks");
					break;
				case "nwpackagsubtotalValue":// 内外包装材料价格小计
					valueStyle = fourPrecisionRedStyle;
					remarksStyle = leftRedStyle;
					unitsStyle = leftRedStyle;
					value = formatNum(jsonData.getJSONObject("accountingCostItem").getString("nwpackagsubtotalValue"),4);
					remarks = getJSONObjectValue(jsonData.getJSONObject("accountingCostItem"), "nwpackagsubtotalRemarks");
					break;
				case "taxDiffer"://税差
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingTaxDiffer").getString("price"),4);
					remarks = "税差计算方式 : " + getJSONObjectValue(jsonData.getJSONObject("accountingTaxDiffer"), "taxDifferComputeType") +"\n" + 
           		   		       "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingTaxDiffer"), "remarks");
					break;
				case "interest"://利息
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingInterest").getString("price"),4);
					remarks = "贷款利率(%) : " + formatNum(jsonData.getJSONObject("accountingInterest").getString("loanRate"),4) +"\n" + 
						      "利息计算方式 : " + getJSONObjectValue(jsonData.getJSONObject("accountingInterest"), "interestComputeType") +"\n" + 
		   		              "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingInterest"), "remarks");
					break;
				case "wecPrice":// 水电煤
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingWec").getString("price"),4);
					remarks =   "耗水(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("water"),4) + "\n" + 
							    "耗油(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("oil"),4) + "\n" + 
								"耗电(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("electricity"),4) + "\n" + 
								"耗气(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("gas"),4) + "\n" + 
								"耗煤(元/t成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("coal"),4) + "\n" + 
								"合计(元/kg成品) : "+ formatNum(jsonData.getJSONObject("accountingWec").getString("total"),4) + "\n" + 
								"备注 : "+ getJSONObjectValue(jsonData.getJSONObject("accountingWec"), "remarks");
					break;
				case "sbzjwhPrice":// 设备折旧及维护
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("price"),4);
			  	    remarks = "设备总价(万元) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("totalPrice"),4) + "\n" + 
					          "折旧年限(年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("ageLimit"),4) + "\n" + 
			  	    		  "折旧值(万元/年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("depreciation"),4) + "\n" + 
					          "产能值(t成品/年) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("capacity"),4)+ "\n" + 
					          "合计折旧值(元/kg成品) : " + formatNum(jsonData.getJSONObject("accountingSbzjwh").getString("total"),4) + "\n" + 
			  	    		  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingSbzjwh"), "remarks");
					break;
				case "ampPrice":// 人工
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingManpower").getString("price"),4);
				    remarks = "车间工人数(人次) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("manpowerCount"),4)+ "\n" + 
							  "平均工资(元/人/月) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("avgWage"),4)+ "\n" + 
							  "月产量(t) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("monthCapacity"),4)+ "\n" + 
							  "每kg成品(元/kg) : " + formatNum(jsonData.getJSONObject("accountingManpower").getString("unitsWage"),4)+ "\n" + 
							  "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingManpower"), "remarks");
					break;
				case "amaPrice":// 管理各地区
					valueStyle = fourPrecisionStyle;
					value = formatNum(jsonData.getJSONObject("accountingManage").getString("price"),4);
					array = jsonData.getJSONArray("accountingManageRegionList");
					for (int i = 0; i < array.size(); i++) {
						try {
							object = array.getJSONObject(i);
							remarks += "进货地区" + object.getString("region") + " : \n" + 
							           "占比(%) : " + formatNum(object.getString("proportion"),4) + "\n";
						} catch (Exception e) {
							remarks += "进货地区" + " : /\n" + 
							           "占比(%) : " + "/\n";
						}
					}
					remarks += "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingManage"), "remarks");
					break;
				case "afrPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingFreightRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(fourPrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),4));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				                
								for (int i = 0; i < array.size(); i++) {
									 try {
										object = array.getJSONObject(i);
										 remarks += "进货地区" + object.getString("region") + " : \n" + 
												    "总公里数(km) : "+ formatNum(object.getString("sumKm"),4) + "\n";
									} catch (Exception e) {
										remarks += "进货地区" + " : /\n" + 
												   "总公里数(km) : "+ "/\n";
									}
								}
								remarks += "单位成本(元/"+jsonData.getJSONObject("accountingFreight").getString("units")+") : " + formatNum(jsonData.getJSONObject("accountingFreight").getString("unitsCost"),4) + "\n"+
								           "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingFreight"), "remarks");
								cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
								cell.setCellStyle(leftStyle);
								cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "atrPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingTaxRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(fourPrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),4));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				                
				            for (int i = 0; i < array.size(); i++) {
								 try {
									object = array.getJSONObject(i);
									 remarks += "进货地区" + object.getString("region") + " : \n" + 
											    "占比(%) : "+ formatNum(object.getString("proportion"),4) + "\n";
								} catch (Exception e) {
									remarks += "进货地区" + " : /\n" + 
											   "占比 (%) : "+ "/\n";
								}
							}
							remarks += "税率(%) : " + formatNum(jsonData.getJSONObject("accountingTax").getString("taxRate"),4) + "\n"+
							           "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingTax"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "aprPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingProfitRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(fourPrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("price"),4));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            for (int i = 0; i < array.size(); i++) {
								 try {
									object = array.getJSONObject(i);
									 remarks += "进货地区" + object.getString("region") + " : \n" + 
											    "占比(%) : "+ formatNum(object.getString("proportion"),4) + "\n";
								} catch (Exception e) {
									remarks += "进货地区" + ": /\n" + 
										       "占比(%) : " + "/\n";
								}
							}
							remarks += "备注 : " + getJSONObjectValue(jsonData.getJSONObject("accountingProfit"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "subTotal":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingElsesubtotalRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,true);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(fourPrecisionRedStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								} else {
									cell.setCellValue(formatNum(object.get("subtotal"),4));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(leftRedStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(leftRedStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            remarks = getJSONObjectValue(jsonData.getJSONObject("accountingElsesubtotal"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftRedStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "sumPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("accountingAggregateRegionList");
					row = sheet.getRow(rowIndex);
					if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
						fillNoHasAccounting(sheet,jsonData,row,rowIndex,nextFillCellIndex,maxColSpan,isThisApplication ,noLast,false);
					}else{
						for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
							cell = row.createCell(nextFillCellIndex + i);
							cell.setCellStyle(twoPrecisionStyle);
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								}else{
									cell.setCellValue(formatNum(object.get("sumPrice"),2));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
						if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
							if (isShowUnits) {  //显示报价计量单位并且不显示备注
								cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 1));
							}
						}else{ 
				            if (isShowUnits) { //显示报价计量单位
				            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 3));
								cell.setCellStyle(unitsStyle);
								// 若果设置了对比单位,则统一使用对比单位
								cell.setCellValue(units);
							}else{ //不显示报价计量单位
									sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+array.size()-1, nextFillCellIndex + maxColSpan - 2));
							}
				            remarks = getJSONObjectValue(jsonData.getJSONObject("accountingAggregate"), "remarks");
							cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
							cell.setCellStyle(leftStyle);
							cell.setCellValue(remarks);
						}
					}
					rowIndex++;
					continue;
				case "mcpPrice":
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
					array = jsonData.getJSONArray("merchandiseContractPrices");
					for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
						row = sheet.getRow(rowIndex);
						cell = row.createCell(nextFillCellIndex + i);
						cell.setCellStyle(twoPrecisionStyle);
						if (isThisApplication) {
							cell.setCellValue("");
						} else {
							try {
								object = array.getJSONObject(i);
								if (object == null) {
									cell.setCellValue("");
								}else{
									cell.setCellValue(formatNum(object.get("price"),2));
								}
							} catch (Exception e) {
								cell.setCellValue("");
							}
						}
					}
					if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
						if (isShowUnits) {  //显示报价计量单位并且不显示备注
							cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
							cell.setCellStyle(unitsStyle);
							// 若果设置了对比单位,则统一使用对比单位
							if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
								cell.setCellValue("");
							}else{
								cell.setCellValue(units);
							}
						}else{
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 1));
						}
					}else{ 
			            if (isShowUnits) { //显示报价计量单位
			            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
							sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 3));
							cell.setCellStyle(unitsStyle);
							// 若果设置了对比单位,则统一使用对比单位
							if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")) {
								cell.setCellValue("");
							}else{
								cell.setCellValue(units);
							}
						}else{ //不显示报价计量单位
								sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
						}
			            remarks = "";
						cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
						cell.setCellStyle(leftStyle);
						cell.setCellValue(remarks);
					}
					rowIndex++;
					continue;
				default:
					break;
				}
			}
			} catch (Exception e) {
				value="";
				remarks="";
			}
			// 填充单元格的数据
			if (getJSONObjectValue(jsonData.getJSONObject("accounting"),"accountingCode").equals("")&&rowIndex > 9) {
				value = "";
				remarks = "";
				units = "";
			}
			fillCell(sheet, rowIndex, isThisApplication, nextFillCellIndex, value, remarks, units,valueStyle,remarksStyle,unitsStyle, maxColSpan ,noLast);
			rowIndex++;
		}
		return (nextFillCellIndex + maxColSpan);
	}
	
	/**
	 * 为单元格填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowIndex
	 *            单元格的所在行数
	 * @param listIndex
	 *            单元格所在列数
	 * @param data
	 *            单元格的数据
	 * @param maxColSpan
	 *            该单元格要跨的列数
	 * @param regionSize
	 *            地区的个数
	 */
	public void fillCell(Sheet sheet, int rowIndex,Boolean isThisApplication, int nextFillCellIndex, Object value, String remarks, String units, CellStyle valueStyle, CellStyle remarksStyle,CellStyle unitsStyle, int maxColSpan, boolean noLast) {
		Row row = sheet.getRow(rowIndex);
		Cell cell  = null;
		if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
			if (isShowUnits && rowIndex > 9) {  //显示报价计量单位并且不显示备注
				cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 2));
				addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 2);
				cell.setCellStyle(unitsStyle);
				// 若果设置了对比单位,则统一使用对比单位
				cell.setCellValue(units);
			}else{  //什么都不显示
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1));	
				addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1);
			}
		}else{ 
            if (isShowUnits && rowIndex > 9) { //显示报价计量单位
            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 3));
				addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 3);
				cell.setCellStyle(unitsStyle);
				// 若果设置了对比单位,则统一使用对比单位
				cell.setCellValue(units);
			}else{ //不显示报价计量单位
				if (rowIndex < 9) {
					sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1));
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 1);
				}else{
					sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 2));
					addMergedRegionBorder(sheet, rowIndex, rowIndex, nextFillCellIndex, nextFillCellIndex + maxColSpan - 2);
				}
			}
            	// 创建填充备注的单元格,并填备注
                cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
				cell.setCellStyle(remarksStyle);
                
        		if (StringUtils.isBlank(remarks)) {
        			remarks = "";
        		}
    			cell.setCellValue(remarks);
		}
		// 创建填充值的单元格,并填充值
		cell = row.createCell(nextFillCellIndex);
		//解决投料编号的科学计数法
		cell.setCellStyle(valueStyle);
        value = (value == null ? "" : value.toString()); // 如果为空设置为/
		try {
			if (rowIndex == 2 || rowIndex == 4) {//商品编号和供应商编号要以字符串形式写入,避免前面的0被去掉
				cell.setCellValue(value.toString());
			} else {
				cell.setCellValue(Double.valueOf(value.toString()));
			}
		} catch (Exception e) {
			cell.setCellValue(value.toString());
		}
	}
	
	/**
	 * 填充没有核算编号的复杂的表格
	 * @param sheet Sheet
	 * @param rowIndex 当前行的索引
	 * @param nextFillCellIndex 下一列的索引
	 * @param maxColSpan 跨的最大的列数
	 * @param firstRowIndex 跨的第一行的索引
	 * @param lastRowIndex 跨的最后一行的索引
	 * @param isThisApplication 是否是本次申请商品
	 * @param noLast 报价日期不是最晚的
	 */
	public void fillNoHasAccounting(Sheet sheet, int rowIndex ,int nextFillCellIndex , int maxColSpan ,int firstRowIndex,int lastRowIndex , boolean isThisApplication , boolean noLast){
		for (int j = firstRowIndex; j < lastRowIndex; j++) {
			addMergedRegionBorder(sheet, j, j, nextFillCellIndex, nextFillCellIndex+maxColSpan-1);
			Row row = sheet.getRow(j);
			
			Cell cell = row.createCell(nextFillCellIndex);
			cell.setCellStyle(leftStyle);
			cell.setCellValue("");
			
			cell = row.createCell(nextFillCellIndex+1);
			cell.setCellStyle(twoPrecisionStyle);
			cell.setCellValue("");
			
			if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
				if (isShowUnits) {
					sheet.addMergedRegion(new CellRangeAddress(j, j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
					cell = row.createCell(nextFillCellIndex+maxColSpan-1);
					cell.setCellStyle(leftStyle);
					cell.setCellValue("");
				}else{
					sheet.addMergedRegion(new CellRangeAddress(j, j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 1));
				}
			}else{
				if (isShowUnits) {
					sheet.addMergedRegion(new CellRangeAddress(j, j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 3));
					cell = row.createCell(nextFillCellIndex+maxColSpan-2);
					cell.setCellStyle(leftStyle);
					cell.setCellValue("");
				}else{
					sheet.addMergedRegion(new CellRangeAddress(j, j, nextFillCellIndex+1, nextFillCellIndex + maxColSpan - 2));
				}
				cell = row.createCell(nextFillCellIndex+maxColSpan-1);
				cell.setCellStyle(leftStyle);
				cell.setCellValue("");
			}
		}
	}
	
	
	/**
	 * 填充没有核算编号的复杂的表格
	 * @param sheet Sheet
	 * @param jsonData 当前数据
	 * @param row 当前行
	 * @param rowIndex 当前行的索引
	 * @param nextFillCellIndex 下一列的索引
	 * @param maxColSpan 跨的最大的列数
	 * @param isThisApplication 是否是本次申请商品
	 * @param noLast 报价日期不是最晚的
	 * @param isRed 该行是否红色显示
	 */
	public void fillNoHasAccounting(Sheet sheet,JSONObject jsonData,Row row, int rowIndex ,int nextFillCellIndex , int maxColSpan ,boolean isThisApplication , boolean noLast,boolean isRed){
		Cell cell = null;
		for (int i = 0; i < jsonData.getJSONArray("accountingRegionList").size(); i++) {
			cell = row.createCell(nextFillCellIndex + i);
			cell.setCellStyle(twoPrecisionStyle);
			cell.setCellValue("");
		}
		if (isHideAllRemarks||(isShowThisRemarks&&!isThisApplication)||(isShowThisRemarks&&noLast)) {
			if (isShowUnits) {  //显示报价计量单位并且不显示备注
				cell = row .createCell(nextFillCellIndex + maxColSpan - 1);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
				if (isRed) {
					cell.setCellStyle(leftRedStyle);
				} else {
					cell.setCellStyle(leftStyle);
				}
				cell.setCellValue("");
			}else{
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 1));
			}
		}else{ 
            if (isShowUnits) { //显示报价计量单位
            	cell = row .createCell(nextFillCellIndex + maxColSpan - 2);
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 3));
				if (isRed) {
					cell.setCellStyle(leftRedStyle);
				} else {
					cell.setCellStyle(leftStyle);
				}
				cell.setCellValue("");
			}else{ //不显示报价计量单位
				sheet.addMergedRegion(new CellRangeAddress(rowIndex, rowIndex, nextFillCellIndex+jsonData.getJSONArray("accountingRegionList").size()-1, nextFillCellIndex + maxColSpan - 2));
			}
			cell = row.createCell(nextFillCellIndex + maxColSpan - 1);
			if (isRed) {
				cell.setCellStyle(leftRedStyle);
			} else {
				cell.setCellStyle(leftStyle);
			}
			cell.setCellValue("");
		}
	}
	
	
	/**
	 * 给合并的单元格添加边框
	 * 
	 * @param sheet sheet
	 * @param firstRow 起始行
	 * @param lastRow  结束行
	 * @param firstCell  起始列
	 * @param lastCell  结束列
	 */
	public void addMergedRegionBorder(Sheet sheet,int firstRow, int lastRow, int firstCell, int lastCell){
		for (int i = firstRow; i <= lastRow; i++) {
			Row row = sheet.getRow(i);
			if (row == null) {
				row = sheet.createRow(i);
			}
			for (int j = firstCell; j <= lastCell; j++) {
				Cell cell = row.getCell(j);
				if (cell == null) {
				    cell = row.createCell(j);	
				}
				cell.setCellStyle(leftStyle);
			}
		}
	}	
	
	/**
	 * 调整列宽
	 * @param sheet sheet
	 */
	public void autoSizeListWidth(Sheet sheet){
		Row row = sheet.getRow(0);
		for (int i = 0; i < row.getLastCellNum(); i++) {
			sheet.setColumnWidth(i, (90*80));
		}
	}
	
	/**
	 * 格式化数字
	 * @param value 数字
	 * @param precision 精度
	 * @return 格式化以后的字符串
	 */
	public String formatNum(Object value,int precision) {
			if (value == null) {
				return "";
			}else{
				try {
					switch (precision) {
					case 2:
						value =  DecimalFormatUtils.formatBigDecimal(new BigDecimal(value.toString()), "#,##0.00");
						break;
					case 3:
						value =  DecimalFormatUtils.formatBigDecimal(new BigDecimal(value.toString()), "#,##0.000");
						break;
					case 4:
						value =  DecimalFormatUtils.formatBigDecimal(new BigDecimal(value.toString()), "#,##0.0000");
						break;
					default:
						break;
					}
					return value.toString();
				} catch (NumberFormatException e) {
					return value.toString();
				}
			}
	}
	
	/**
	 *  获取JSONObject的值
	 * @param obj JSONObject对象
	 * @param key 要取的key
	 * @return 要取的值
	 */
	public String getJSONObjectValue(JSONObject obj,String key){
		try {
			if (obj == null||obj.get(key) == null) {
				return "";
			}else{
				return StringUtils.trim(obj.get(key).toString());
			}
		} catch (Exception e) {
			return "";
		}
	}
	
	
	@Override
	public String isnertReports(String fileName, Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableInfo", paraMap.get("tableInfo"));
		map.put("width", paraMap.get("width"));
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("totalCostAnalogyAnalysis/totalCostAnalogyAnalysisReport.ftl", "totalCostAnalogyAnalysis".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.MCA.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
				//获取刚插入报表的报表编号
				String reportCode = ReportsServiceImpl.getInstance().searchCurrSequence();
				CostAnalysisMerchandise costAnalysisMerchandise = new CostAnalysisMerchandise();
				JSONArray array = JSONArray.fromObject(paraMap.get("info"));
				for (int i = 0; i < array.size(); i++) {
					JSONObject object = array.getJSONObject(i);
					costAnalysisMerchandise.setReportsCode(reportCode);
					costAnalysisMerchandise.setAccountingCode(object.get("accountingCode").toString());
					costAnalysisMerchandise.setMerchandiseCode(object.get("merchandiseCode").toString());
					costAnalysisMerchandise.setSupplierCode(object.get("supplierCode").toString());
					ReportsServiceImpl.getInstance().insertReportAnalysis(costAnalysisMerchandise);
				}
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("TotalCostAnalogyAnalysisServiceImpl.isnertReports删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

}
