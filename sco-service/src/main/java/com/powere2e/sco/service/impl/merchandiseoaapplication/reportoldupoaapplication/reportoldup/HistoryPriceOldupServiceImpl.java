package com.powere2e.sco.service.impl.merchandiseoaapplication.reportoldupoaapplication.reportoldup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldupDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportOldup.HistoryPriceOldupDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldupService;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.CheckStandardOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 历史与本次价格(老品新上)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class HistoryPriceOldupServiceImpl extends ServiceImpl implements HistoryPriceOldupService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1820872003183800797L;
	private HistoryPriceOldupDao priceOldupDao;
	private ApplicationReportOldupDao reportOldupDao;
	
	public static HistoryPriceOldupService getInstance() {
		return (HistoryPriceOldupService) ConfigFactory.getInstance().getBean("priceOldupService");
	}

	public HistoryPriceOldupDao getPriceOldupDao() {
		return priceOldupDao;
	}

	public void setPriceOldupDao(HistoryPriceOldupDao priceOldupDao) {
		this.priceOldupDao = priceOldupDao;
	}

	public ApplicationReportOldupDao getReportOldupDao() {
		return reportOldupDao;
	}

	public void setReportOldupDao(ApplicationReportOldupDao reportOldupDao) {
		this.reportOldupDao = reportOldupDao;
	}

	// 查询
	@Override
	public List<HistoryPriceOldup> listHistoryPriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listHistoryPriceOldup(map, pageInfo);
	}

	// 本次价格比历史价格高(老品新上)查询
	@Override
	public List<HistoryPriceOldup> listComparePriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listComparePriceOldup(map, pageInfo);
	}

	// 添加
	@Override
	public void insertHistoryPriceOldup(HistoryPriceOldup historyPriceOldup) throws Exception {
		priceOldupDao.insertHistoryPriceOldup(historyPriceOldup.toMap());
	}

	// 删除
	@Override
	public void deleteHistoryPriceOldup(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceOldupDao.deleteHistoryPriceOldup(map);
	}

	// 查询
	@Override
	public List<UpDownMarketOldup> listUpDownMarketOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listUpDownMarketOldup(map, pageInfo);
	}

	// 添加
	@Override
	public void insertUpDownMarketOldup(UpDownMarketOldup upDownMarketOldup) throws Exception {
		priceOldupDao.insertUpDownMarketOldup(upDownMarketOldup.toMap());
	}

	// 删除
	@Override
	public void deleteUpDownMarketOldup(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceOldupDao.deleteUpDownMarketOldup(map);
	}

	// 查询
	@Override
	public List<CheckStandardOldup> listCheckStandardOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listCheckStandardOldup(map, pageInfo);
	}

	// 添加
	@Override
	public void insertCheckStandardOldup(CheckStandardOldup checkStandardOldup) throws Exception {
		priceOldupDao.insertCheckStandardOldup(checkStandardOldup.toMap());
	}

	// 删除
	@Override
	public void deleteCheckStandardOldup(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceOldupDao.deleteCheckStandardOldup(map);
	}

	@Override
	public List<SameMerchandiseOldup> listSameMerchandiseOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listSameMerchandiseOldup(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSameMerchandiseOldup(SameMerchandiseOldup sameMerchandiseOldup) throws Exception {
		priceOldupDao.insertSameMerchandiseOldup(sameMerchandiseOldup.toMap());
	}

	// 删除
	@Override
	public void deleteSameMerchandiseOldup(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceOldupDao.deleteSameMerchandiseOldup(map);
	}

	// 查询
	@Override
	public List<AnticipatedSellOld> listAnticipatedSellOld(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceOldupDao.listAnticipatedSellOld(map, pageInfo);
	}

	// 添加
	@Override
	public void insertAnticipatedSellOld(AnticipatedSellOld anticipatedSellOld) throws Exception {
		priceOldupDao.insertAnticipatedSellOld(anticipatedSellOld.toMap());
	}

	// 删除
	@Override
	public void deleteAnticipatedSellOld(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceOldupDao.deleteAnticipatedSellOld(map);
	}

	@Override
	public String saveApplicationReportOldupToHtml(String fileName, String applicationCode,
			List<ApplicationReportOldup> applicationList, String intentionAndSupplierCodes) throws Exception {
		String path = "";
		Map<String, Object> map = new HashMap<String, Object>();// 放置到模板中的map
		Map<String ,Object> searMap = new HashMap<String, Object>();//用于查询条件
		
		if (applicationList != null && applicationList.size() > 0) {
			MerchandiseOaApplicationService merOaAppService = MerchandiseOaApplicationServiceImpl.getInstance();
			for (ApplicationReportOldup appplicationReportOldup : applicationList) {
				List<UpDownMarketOldup> upDownList = new ArrayList<UpDownMarketOldup>();
				List<HistoryPriceOldup> oldPriceList = new ArrayList<HistoryPriceOldup>();
				List<HistoryPriceOldup> nowPriceList = new ArrayList<HistoryPriceOldup>();
				List<SameMerchandiseOldup> sameList = new ArrayList<SameMerchandiseOldup>();
				List<AnticipatedSellOld> sellList = new ArrayList<AnticipatedSellOld>();
				List<MerchandiseMaterial> materialList = new ArrayList<MerchandiseMaterial>();
				//先查询出历史与本次价格备份
				List<HistoryPriceOldup>  backPriceList = new ArrayList<>();
				
				// 插入上下市时间
				UpDownMarketOldup upDownArr[] = appplicationReportOldup.getUpDownArr();
				upDownList = Arrays.asList(upDownArr);

				//商品编号
				String intentionCode = appplicationReportOldup.getMerchandiseCode();
				//供应商编号
				String supplierCode = appplicationReportOldup.getSupplierCode();
				//报表编号
				String reportCode = appplicationReportOldup.getReportCode();
				
				boolean ifEdit = StringUtils.isNotBlank(reportCode);
				if (!ifEdit) {//获取reportCode
					reportCode = merOaAppService.selectReportNextID("S_APPLICATION_REPORT_ADJUSTPRI");
					//申请报告
					Map<String, Object> oldPrice = merOaAppService.getApplicationReportMap(
							BusinessConstants.ApplicationType.MERCHANDISE_OLDUP.toString(), applicationCode,
							reportCode, intentionAndSupplierCodes, intentionCode, supplierCode);
					if(oldPrice != null) reportOldupDao.insertApplicationReportOldup(oldPrice);
				} else {
					searMap.put("applicationCode", applicationCode);
					searMap.put("intentionCode", intentionCode);
					searMap.put("supplierCode", supplierCode);
					searMap.put("piceType", "old"); // 设置类型为历史价格
					backPriceList = priceOldupDao.listHistoryPriceOldup(searMap, null);
					searMap.put("piceType", "now"); // 设置类型为本次价格
					backPriceList.addAll(priceOldupDao.listHistoryPriceOldup(searMap, null));

					// 删除老品新上历史与本次价格
					searMap.put("reportCode", reportCode);
					priceOldupDao.deleteHistoryPriceOldup(searMap);
				}
				
				// 商品历史价格(老品新上)
				HistoryPriceOldup oldPriceArr[] = appplicationReportOldup.getOldPriceArr();
				if (oldPriceArr.length > 0) {
					for (HistoryPriceOldup oldPriceOldup : oldPriceArr) {
						oldPriceOldup.setPiceType("old");// 设置类型为历史价格
						if ("".equals(oldPriceOldup.getSellPrice())||oldPriceOldup.getSellPrice() == null || oldPriceOldup.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							oldPriceOldup.setProfitRate(null);
							oldPriceOldup.setProfitRatePercent("0.00%");
							oldPriceList.add(oldPriceOldup);
						}else{
						BigDecimal profitRate = DecimalFormatUtils.divideBigDecimal(
								oldPriceOldup.getPurchasePrice(), oldPriceOldup.getSellPrice(), 4);
						oldPriceOldup.setProfitRate(profitRate);
						oldPriceOldup.setProfitRatePercent(DecimalFormatUtils.formatBigPercent(profitRate));
						oldPriceList.add(oldPriceOldup);
						}
						String tReportCode = oldPriceOldup.getReportCode();
						if (!ifEdit) {
							oldPriceOldup.setReportCode(reportCode);
						}
						priceOldupDao.insertHistoryPriceOldup(oldPriceOldup.toMap());
						oldPriceOldup.setReportCode(tReportCode);
					}
				}
				// 商品本次价格(老品新上)
				HistoryPriceOldup nowPriceArr[] = appplicationReportOldup.getNowPriceArr();
				if (nowPriceArr.length > 0) {
					for (HistoryPriceOldup nowPriceOldup : nowPriceArr) {
						nowPriceOldup.setPiceType("now");// 设置类型为本次价格
						if ("".equals(nowPriceOldup.getSellPrice())||nowPriceOldup.getSellPrice() == null || nowPriceOldup.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							nowPriceOldup.setProfitRate(null);
							nowPriceOldup.setProfitRatePercent("0.00%");
							nowPriceList.add(nowPriceOldup);
						}else{
						BigDecimal profitRate = DecimalFormatUtils.divideBigDecimal(
								nowPriceOldup.getPurchasePrice(), nowPriceOldup.getSellPrice(), 4);
						nowPriceOldup.setProfitRate(profitRate);
						nowPriceOldup.setProfitRatePercent(DecimalFormatUtils.formatBigPercent(profitRate));
						nowPriceList.add(nowPriceOldup);
						}
						String tReportCode = nowPriceOldup.getReportCode();
						if (!ifEdit) {
							nowPriceOldup.setReportCode(reportCode);
						}
						priceOldupDao.insertHistoryPriceOldup(nowPriceOldup.toMap());
						nowPriceOldup.setReportCode(tReportCode);
					}
				}
				// 同类商品市场零售价(老品新上)
				SameMerchandiseOldup sameArr[] = appplicationReportOldup.getSameArr();
				sameList = Arrays.asList(sameArr);
				
				//商品材料
				MerchandiseMaterial materialArr[] = appplicationReportOldup.getMaterialArr();
				materialList = Arrays.asList(materialArr);
				
				// 同类商品市场零售价(老品新上)
				AnticipatedSellOld sellArr[] = appplicationReportOldup.getSellArr();
				sellList = Arrays.asList(sellArr);
				//本次价格比历史价格高
				List<HistoryPriceOldup> nowThanOldPriceList = new ArrayList<HistoryPriceOldup>();
				Map<String, Object> nowThanOldMap = new HashMap<String, Object>();
				nowThanOldMap.put("intentionCode", intentionCode);
				nowThanOldMap.put("supplierCode", supplierCode);
				nowThanOldMap.put("applicationCode", applicationCode);
				
				//查询本次价格比历史价格高
				nowThanOldPriceList = listComparePriceOldup(nowThanOldMap, null);

				//还原老品新上历史与本次价格
				this.restorePriceAndReport(backPriceList, searMap, ifEdit, reportCode);
				
				appplicationReportOldup.setUpDownList(upDownList);
				appplicationReportOldup.setOldPriceList(oldPriceList);
				appplicationReportOldup.setNowPriceList(nowPriceList);
				appplicationReportOldup.setSameList(sameList);
				appplicationReportOldup.setSellList(sellList);
				appplicationReportOldup.setMaterialList(materialList);
				appplicationReportOldup.setNowThanOldPriceList(nowThanOldPriceList);
			}
		}

		map.put("applicationList", applicationList);
		try {
			String fileDirName = "merchandiseOaApplication/applicationReportOldupHtml.ftl";
			path=FreeMarkerUtil.generateHtml(fileDirName, "applicationReportOldup".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}

		return path;
	}

	/**
	 * 将原来的价格数据还原
	 * 
	 * @param backPriceList
	 *            备份的原始价格数据
	 * @param searMap
	 *            报表编号
	 * @param reportCode
	 *            报表编号
	 * @param ifEdit
	 *            是否是编辑
	 */
	private void restorePriceAndReport(List<HistoryPriceOldup> backPriceList, 
			Map<String, Object> searMap, boolean ifEdit, String reportCode) throws Exception {
		searMap.put("reportCode", reportCode);
		
		// 1.删除老品新上历史与本次价格
		priceOldupDao.deleteHistoryPriceOldup(searMap);
		// 2.删除申请报告
		if (!ifEdit) {
			reportOldupDao.deleteApplicationReportOldup(searMap);
		}
		// 3.还原原来数据
		for (HistoryPriceOldup oldPriceOldup : backPriceList) {
			priceOldupDao.insertHistoryPriceOldup(oldPriceOldup.toMap());
		}
	}
	
	//商品原料情况查询
	@Override
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return priceOldupDao.listMerchandiseMaterial(map, pageInfo);
	}

}