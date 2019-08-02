package com.powere2e.sco.service.impl.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;

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
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustpriceDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustpriceDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustpriceService;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpItemAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpTotalAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 历史与本次价格(调价)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class HistoryPriceAdjustpriceServiceImpl extends ServiceImpl implements HistoryPriceAdjustpriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 776362329315361264L;
	private HistoryPriceAdjustpriceDao priceAdjustpriceDao;
	private ApplicationReportAdjustpriceDao reportAdjustpriceDao;// 正常调价报告的dao
	
	public static HistoryPriceAdjustpriceService getInstance() {
		return (HistoryPriceAdjustpriceService) ConfigFactory.getInstance().getBean("priceAdjustpriceService");
	}

	// 历史与本次价格(调价)查询
	@Override
	public List<HistoryPriceAdjustprice> listHistoryPriceAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listHistoryPriceAdjustprice(map, pageInfo);
	}

	//本次价格比历史价格高(正常调价)查询
	@Override
	public List<HistoryPriceAdjustprice> listComparePriceAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listComparePriceAdjustprice(map, pageInfo);
	}

	// 商品签量情况-总查询
	@Override
	public List<AtpTotalAdjustprice> listAtpTotalAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listAtpTotalAdjustprice(map, pageInfo);
	}

	// 商品签量情况-明细(调价)查询
	@Override
	public List<AtpItemAdjustprice> listAtpItemAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listAtpItemAdjustprice(map, pageInfo);
	}

	// 同类商品市场零售价(调价)查询
	@Override
	public List<SameMerchandiseAdjustprice> listSameMerchandiseAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listSameMerchandiseAdjustprice(map, pageInfo);
	}

	// 商品原料情况(调价)查询
	@Override
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return priceAdjustpriceDao.listMerchandiseMaterial(map, pageInfo);
	}

	public HistoryPriceAdjustpriceDao getPriceAdjustpriceDao() {
		return priceAdjustpriceDao;
	}

	public void setPriceAdjustpriceDao(HistoryPriceAdjustpriceDao priceAdjustpriceDao) {
		this.priceAdjustpriceDao = priceAdjustpriceDao;
	}

	public ApplicationReportAdjustpriceDao getReportAdjustpriceDao() {
		return reportAdjustpriceDao;
	}

	public void setReportAdjustpriceDao(ApplicationReportAdjustpriceDao reportAdjustpriceDao) {
		this.reportAdjustpriceDao = reportAdjustpriceDao;
	}

	@Override
	public String saveApplicationReportAdjustpriceToHtml(String fileName, String applicationCode,
			List<ApplicationReportAdjustprice> applicationList, String intentionAndSupplierCodes) throws Exception {
		String path="";
		Map<String, Object> map = new HashMap<String, Object>();//放置到模板中的map
		Map<String ,Object> searMap = new HashMap<String, Object>();//用于查询条件
		
		
		if (applicationList != null && applicationList.size() > 0) {
			MerchandiseOaApplicationService merOaAppService = MerchandiseOaApplicationServiceImpl.getInstance();
			for (ApplicationReportAdjustprice applicationReportAdjustprice : applicationList) {
				List<HistoryPriceAdjustprice> oldPriceList = new ArrayList<HistoryPriceAdjustprice>();
				List<HistoryPriceAdjustprice> nowPriceList = new ArrayList<HistoryPriceAdjustprice>();
				List<SameMerchandiseAdjustprice> sameList = new ArrayList<SameMerchandiseAdjustprice>();
				List<MerchandiseMaterial> materialList = new ArrayList<MerchandiseMaterial>();
				//先查询出历史与本次价格备份
				List<HistoryPriceAdjustprice>  backPriceList = new ArrayList<>();
				
				//商品编号
				String intentionCode = applicationReportAdjustprice.getMerchandiseCode();
				//供应商编号
				String supplierCode = applicationReportAdjustprice.getSupplierCode();
				//报表编号
				String reportCode = applicationReportAdjustprice.getReportCode();
				
				boolean ifEdit = StringUtils.isNotBlank(reportCode);
				if (!ifEdit) {//获取reportCode
					reportCode = merOaAppService.selectReportNextID("S_APPLICATION_REPORT_ADJUSTPRI");
					//申请报告
					Map<String, Object> adjustPrice = merOaAppService.getApplicationReportMap(
							BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString(), applicationCode,
							reportCode, intentionAndSupplierCodes, intentionCode, supplierCode);
					if(adjustPrice != null) reportAdjustpriceDao.insertApplicationReportAdjustprice(adjustPrice);
				} else  {
					searMap.put("applicationCode", applicationCode);
					searMap.put("intentionCode", intentionCode);
					searMap.put("supplierCode", supplierCode);
					searMap.put("piceType", "old"); // 设置类型为历史价格
					backPriceList = priceAdjustpriceDao.listHistoryPriceAdjustprice(searMap, null);
					searMap.put("piceType", "now"); // 设置类型为本次价格
					backPriceList.addAll(priceAdjustpriceDao.listHistoryPriceAdjustprice(searMap, null));

					// 删除老品新上历史与本次价格
					searMap.put("reportCode", reportCode);
					priceAdjustpriceDao.deleteHistoryPriceAdjustprice(searMap);
				}
				
				// 商品历史价格(正常调价)
				HistoryPriceAdjustprice oldPriceArr[] = applicationReportAdjustprice.getOldPriceArr();
				if (oldPriceArr.length > 0) {
					for (HistoryPriceAdjustprice oldPriceAdjustprice : oldPriceArr) {
						oldPriceAdjustprice.setPiceType("old");// 设置类型为历史价格
						if ("".equals(oldPriceAdjustprice.getSellPrice())||oldPriceAdjustprice.getSellPrice() == null || oldPriceAdjustprice.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							oldPriceAdjustprice.setProfitRate(null);
							oldPriceAdjustprice.setProfitRatePercent("0.00%");
							oldPriceList.add(oldPriceAdjustprice);	
						}else{
						BigDecimal profitRate = DecimalFormatUtils.divideBigDecimal(
								oldPriceAdjustprice.getPurchasePrice(), oldPriceAdjustprice.getSellPrice(), 4);
						oldPriceAdjustprice.setProfitRate(profitRate);
						oldPriceAdjustprice.setProfitRatePercent(DecimalFormatUtils.formatBigPercent(profitRate));
						oldPriceList.add(oldPriceAdjustprice);
						}
						String tReportCode = oldPriceAdjustprice.getReportCode();
						if (!ifEdit) {
							oldPriceAdjustprice.setReportCode(reportCode);
						}
						priceAdjustpriceDao.insertHistoryPriceAdjustprice(oldPriceAdjustprice.toMap());
						oldPriceAdjustprice.setReportCode(tReportCode);
					}
				}
				// 插入商品本次价格(正常调价)
				HistoryPriceAdjustprice nowPriceArr[] = applicationReportAdjustprice.getNowPriceArr();
				if (nowPriceArr.length > 0) {
					for (HistoryPriceAdjustprice nowPriceAdjustprice : nowPriceArr) {
						nowPriceAdjustprice.setPiceType("now");// 设置类型为本次价格
						if ("".equals(nowPriceAdjustprice.getSellPrice())||nowPriceAdjustprice.getSellPrice() == null || nowPriceAdjustprice.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							nowPriceAdjustprice.setProfitRate(null);
							nowPriceAdjustprice.setProfitRatePercent("0.00%");
							nowPriceList.add(nowPriceAdjustprice);	
						}else{
						BigDecimal profitRate = DecimalFormatUtils.divideBigDecimal(
								nowPriceAdjustprice.getPurchasePrice(), nowPriceAdjustprice.getSellPrice(), 4);
						nowPriceAdjustprice.setProfitRate(profitRate);
						nowPriceAdjustprice.setProfitRatePercent(DecimalFormatUtils.formatBigPercent(profitRate));
						nowPriceList.add(nowPriceAdjustprice);
						}
						String tReportCode = nowPriceAdjustprice.getReportCode();
						if (!ifEdit) {
							nowPriceAdjustprice.setReportCode(reportCode);
						}
						priceAdjustpriceDao.insertHistoryPriceAdjustprice(nowPriceAdjustprice.toMap());
						nowPriceAdjustprice.setReportCode(tReportCode);
					}
				}
				// 同类商品市场零售价(正常调价)
				SameMerchandiseAdjustprice sameArr[] = applicationReportAdjustprice.getSameArr();
				sameList = Arrays.asList(sameArr);
				// 同类商品市场零售价(正常调价)
				MerchandiseMaterial materialArr[] = applicationReportAdjustprice.getMaterialArr();
				materialList = Arrays.asList(materialArr);
				
				// 本次价格比历史价格高
				List<HistoryPriceAdjustprice> nowThanOldPriceList = new ArrayList<HistoryPriceAdjustprice>();
				Map<String, Object> nowThanOldMap = new HashMap<String, Object>();
				nowThanOldMap.put("intentionCode", intentionCode);
				nowThanOldMap.put("supplierCode", supplierCode);
				nowThanOldMap.put("applicationCode", applicationCode);
				
				//查询本次价格比历史价格高
				nowThanOldPriceList = listComparePriceAdjustprice(nowThanOldMap, null);

				//还原老品新上历史与本次价格
				this.restorePriceAndReport(backPriceList, searMap, ifEdit, reportCode);
				
				applicationReportAdjustprice.setOldPriceList(oldPriceList);
				applicationReportAdjustprice.setNowPriceList(nowPriceList);
				applicationReportAdjustprice.setSameList(sameList);
				applicationReportAdjustprice.setMaterialList(materialList);
				applicationReportAdjustprice.setNowThanOldPriceList(nowThanOldPriceList);
			}
		}
		
		map.put("applicationList", applicationList);
		try {
			String fileDirName = "merchandiseOaApplication/applicationReportAdjustpriceHtml.ftl";
			path=FreeMarkerUtil.generateHtml(fileDirName, "applicationReportAdjustprice".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}

		return path;
	}
	
	/**
	 * 将原来的价格和申请报告数据还原
	 * 
	 * @param backPriceList
	 *            备份的原始价格数据
	 * @param searMap
	 *            报表编号
	 * @param reportCode
	 *            报表编号
	 * @param ifEdit
	 *            是否为编辑
	 */
	private void restorePriceAndReport(List<HistoryPriceAdjustprice> backPriceList, 
			Map<String, Object> searMap, boolean ifEdit, String reportCode) throws Exception {
		searMap.put("reportCode", reportCode);
	
		// 1.删除老品新上历史与本次价格
		priceAdjustpriceDao.deleteHistoryPriceAdjustprice(searMap);
		//2.删除申请报告
		if (!ifEdit) {
			reportAdjustpriceDao.deleteApplicationReportAdjustprice(searMap);
		}
		// 3.还原原来数据
		for (HistoryPriceAdjustprice oldPriceOldup : backPriceList) {
			priceAdjustpriceDao.insertHistoryPriceAdjustprice(oldPriceOldup.toMap());
		}
	}

}