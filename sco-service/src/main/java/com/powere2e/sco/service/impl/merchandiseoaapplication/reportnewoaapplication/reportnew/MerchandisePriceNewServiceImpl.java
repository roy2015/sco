package com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.reportnew;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNewDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNewService;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;

/**
 * 商品价格(新品引进)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月27日
 */
public class MerchandisePriceNewServiceImpl extends ServiceImpl implements MerchandisePriceNewService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1143370018123489542L;
	private MerchandisePriceNewDao priceNewDao;

	public static MerchandisePriceNewService getInstance() {
		return (MerchandisePriceNewService) ConfigFactory.getInstance().getBean("priceNewService");
	}

	public MerchandisePriceNewDao getPriceNewDao() {
		return priceNewDao;
	}

	public void setPriceNewDao(MerchandisePriceNewDao priceNewDao) {
		this.priceNewDao = priceNewDao;
	}

	// 查询
	@Override
	public List<MerchandisePriceNew> listMerchandisePriceNew(Map<String, Object> map, PageInfo pageInfo)
			throws Exception {
		return priceNewDao.listMerchandisePriceNew(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandisePriceNew(MerchandisePriceNew merchandisePriceNew) throws Exception {
		priceNewDao.insertMerchandisePriceNew(merchandisePriceNew.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandisePriceNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceNewDao.deleteMerchandisePriceNew(map);
	}

	// 修改
	@Override
	public void updateMerchandisePriceNew(MerchandisePriceNew merchandisePriceNew) throws Exception {
		priceNewDao.updateMerchandisePriceNew(merchandisePriceNew.toMap());
	}

	// 加载一个商品价格(新品引进)
	@Override
	public MerchandisePriceNew loadMerchandisePriceNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		return priceNewDao.loadMerchandisePriceNew(map);
	}

	// 根据申请单号删除商品价格
	@Override
	public void deletePriceNewByApplicationCode(Map<String, Object> map) throws Exception {
		priceNewDao.deletePriceNewByApplicationCode(map);
	}

	// 相同商品模块
	// 查询
	@Override
	public List<SameMerchandiseNew> listSameMerchandiseNew(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceNewDao.listSameMerchandiseNew(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSameMerchandiseNew(SameMerchandiseNew sameMerchandiseNew) throws Exception {
		priceNewDao.insertSameMerchandiseNew(sameMerchandiseNew.toMap());
	}

	// 删除
	@Override
	public void deleteSameMerchandiseNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceNewDao.deleteSameMerchandiseNew(map);
	}

	// 修改
	@Override
	public void updateSameMerchandiseNew(SameMerchandiseNew sameMerchandiseNew) throws Exception {
		priceNewDao.updateSameMerchandiseNew(sameMerchandiseNew.toMap());
	}

	// 加载一个同类商品市场零售价(新品引进)
	@Override
	public SameMerchandiseNew loadSameMerchandiseNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		return priceNewDao.loadSameMerchandiseNew(map);
	}

	// 根据申请单号删除同类商品
	@Override
	public void deleteSameNewByApplicationCode(Map<String, Object> map) throws Exception {
		priceNewDao.deleteSameNewByApplicationCode(map);
	}

	// 销售预计模块
	// 查询
	@Override
	public List<SellAnticipatedNew> listSellAnticipatedNew(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return priceNewDao.listSellAnticipatedNew(map, pageInfo);
	}

	// 添加
	@Override
	public void insertSellAnticipatedNew(SellAnticipatedNew sellAnticipatedNew) throws Exception {
		priceNewDao.insertSellAnticipatedNew(sellAnticipatedNew.toMap());
	}

	// 删除
	@Override
	public void deleteSellAnticipatedNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		priceNewDao.deleteSellAnticipatedNew(map);
	}

	// 修改
	@Override
	public void updateSellAnticipatedNew(SellAnticipatedNew sellAnticipatedNew) throws Exception {
		priceNewDao.updateSellAnticipatedNew(sellAnticipatedNew.toMap());
	}

	// 加载一个商品销售预计(新品引进)
	@Override
	public SellAnticipatedNew loadSellAnticipatedNew(String reportCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportCode", reportCode);
		return priceNewDao.loadSellAnticipatedNew(map);
	}

	// 根据申请单号删除销售预计
	@Override
	public void deleteSellNewByApplicationCode(Map<String, Object> map) throws Exception {
		priceNewDao.deleteSellNewByApplicationCode(map);
	}

	// 根据新品引进申请报告生成html
	@Override
	public String saveApplicationReportNewToHtml(String fileName, List<ApplicationReportNew> applicationList)
			throws Exception {
		String path = "";
		Map<String, Object> map = new HashMap<String, Object>();
		if (applicationList != null && applicationList.size() > 0) {
			for (ApplicationReportNew appplicationReportNew : applicationList) {
				List<MerchandisePriceNew> priceList = new ArrayList<MerchandisePriceNew>();
				List<SameMerchandiseNew> sameList = new ArrayList<SameMerchandiseNew>();
				List<SellAnticipatedNew> sellList = new ArrayList<SellAnticipatedNew>();
				List<MerchandiseMaterial> materialList = new ArrayList<MerchandiseMaterial>();

				// 插入商品价格(新品引进)
				MerchandisePriceNew priceArr[] = appplicationReportNew.getPriceArr();
				if (priceArr.length > 0) {
					for (MerchandisePriceNew priceNew : priceArr) {
						if ("".equals(priceNew.getSellPrice())||priceNew.getSellPrice() == null || priceNew.getSellPrice().compareTo(BigDecimal.ZERO) == 0) {
							priceNew.setProfitRate(null);
							priceNew.setProfitRatePercent("0.00%");
							priceList.add(priceNew);
						}else{
						BigDecimal profitRate = DecimalFormatUtils.divideBigDecimal(priceNew.getPurchasePrice(),priceNew.getSellPrice(), 4);
						priceNew.setProfitRate(profitRate);
						priceNew.setProfitRatePercent(DecimalFormatUtils.formatBigPercent(profitRate));
						priceList.add(priceNew);
						}
					}
				}
				// 同类商品市场零售价(新品引进)
				sameList=Arrays.asList(appplicationReportNew.getSameArr());
				//商品材料
				materialList = Arrays.asList(appplicationReportNew.getMaterialArr());
				// 同类商品市场零售价(新品引进)
				sellList=Arrays.asList(appplicationReportNew.getSellArr());
				
				appplicationReportNew.setPriceList(priceList);
				appplicationReportNew.setSameList(sameList);
				appplicationReportNew.setSellList(sellList);
				appplicationReportNew.setMaterialList(materialList);
			}
		}
		map.put("applicationList", applicationList);
		try {
			String fileDirName = "merchandiseOaApplication/applicationReportNewHtml.ftl";
			path=FreeMarkerUtil.generateHtml(fileDirName, "applicationReportNew".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}

		return path;
	}

	@Override
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return priceNewDao.listMerchandiseMaterial(map, pageInfo);
	}
}