package com.powere2e.sco.peripheral.website;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialDao;
import com.powere2e.sco.interfaces.service.peripheral.website.MaterialDataFetchWorkerService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

public class MaterialDataFetchWorkerServiceImpl2 extends ServiceImpl implements MaterialDataFetchWorkerService {
	private Logger logger = PeripheralFileUtils.logger;
	private static final long serialVersionUID = 581960836294317071L;

	private MaterialDao materialDao;

	// 获得原料DAO实例
	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	// 设置原料DAO实例
	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	/*
	 * 生意社数据抓取
	 */
	@Override
	public void execute() {
		Map<String, Object> map = new HashMap<>();
		List<Material> listMaterial = this.getMaterialDao().listMaterial(map, null);
		WebClient webClient = null;
		for (Material material : listMaterial) {
			webClient = new WebClient(BrowserVersion.FIREFOX_38);
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			webClient.getOptions().setThrowExceptionOnScriptError(false);
			webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
			String materialName = material.getMaterialName();// 原料名称
			String websiteName = material.getWebsiteName();// 公示网站名称
			logger.info("materialName:" + materialName + "  websiteName:" + websiteName);
			if (StringUtils.isNotBlank(materialName)) {
				try {
					if ("生意社".equals(websiteName.trim())) {
						sys(materialName, webClient, material);
					} else if ("一亩田农产品网".equals(websiteName)) {
						ymt(materialName, webClient, material);
					} else if ("江苏凌家塘市场发展有限公司".equals(websiteName)) {
						ljt(materialName, webClient, material);
					} else if ("中国国家统计局".equals(websiteName)) {
						all50(materialName, webClient, material);
					} else if ("Understanding Dairy Markets".equals(websiteName)) {
						abroadSite1(materialName, webClient, material);
					} else if ("index  mundi".equals(websiteName)) {
						abroadSite2(materialName, webClient, material);
					}

				} catch (FailingHttpStatusCodeException e) {
					logger.error("error get data >>>>>>>>>>>>>>>", e);
				}
			}

			if (webClient != null) {
				webClient.close();
			}
		}
	}

	/**
	 * 
	 * @param htmlPage
	 * @param org
	 *            报价机构
	 * @param spec
	 *            规格
	 * @param origin
	 *            产地
	 * @return
	 */
	public BigDecimal getValueSYS(HtmlPage htmlPage, String org, String spec, String origin) {
		if (htmlPage == null)
			return null;
		List<?> list = htmlPage.getByXPath("//body/div/div/div/table/tbody/tr");
		for (Object object : list) {
			HtmlTableRow htmlTableRow = (HtmlTableRow) object;
			String org1 = htmlTableRow.getCell(0).asText().trim();
			String spec1 = htmlTableRow.getCell(3).asText().trim();
			if (org1.equals(org) && spec1.equals(spec)) {
				if (origin != null) {
					String origin1 = htmlTableRow.getCell(4).asText().trim();
					if (origin1.equals(origin)) {
						String value = htmlTableRow.getCell(2).asText().trim().replace("元/吨", "");
						try {
							BigDecimal bigDecimal = new BigDecimal(value);
							return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
						} catch (Exception e) {
							logger.error(htmlTableRow.asText(), e);
						}
					}
				} else {
					String value = htmlTableRow.getCell(2).asText().trim().replace("元/吨", "");
					try {
						BigDecimal bigDecimal = new BigDecimal(value);
						return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
					} catch (Exception e) {
						logger.error(htmlTableRow.asText(), e);
					}
				}
			}
		}
		logger.warn(htmlPage.asText());
		return null;
	}

	/**
	 * 获得一亩田价格
	 * 
	 * @param htmlPage
	 * @return
	 */
	public BigDecimal getValueYMT(HtmlPage htmlPage) {
		if (htmlPage == null)
			return null;
		BigDecimal bigDecimal = null;
		try {
			HtmlElement htmlElement = htmlPage.getFirstByXPath("//div[@class='content']/div[@class='jg_area']/div[@class='jg_area_left']/div[@class='jg_high_low']/div[@class='fl_t_jg']/div[@class='text1']/em");
			if (htmlElement == null)
				return null;
			String value = htmlElement.asText().trim();
			bigDecimal = new BigDecimal(value);
			bigDecimal = bigDecimal.multiply(new BigDecimal(2));
		} catch (Exception e) {
			logger.error("get ymt cell vale >>>  " + e.getMessage(), e);
		}
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			logger.warn(htmlPage.asText());
		}
		return bigDecimal;
	}

	public BigDecimal getValueYMT1(HtmlPage htmlPage, String name) {
		BigDecimal bigDecimal = null;
		try {
			List<?> list = htmlPage.getByXPath("//ul[@class='product_list']/li");
			for (Object object : list) {
				HtmlListItem htmlListItem = (HtmlListItem) object;
				String value = htmlListItem.asText();
				if (value.startsWith(name)) {
					value = value.replace(name, "").replace("元/斤", "").replace("↑", "").trim();
					bigDecimal = new BigDecimal(value);
					bigDecimal = bigDecimal.multiply(new BigDecimal(2));
				}
			}
		} catch (Exception e) {
			logger.error("ymt >>" + name, e);
		}
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			logger.warn(htmlPage.asText());
		}
		return bigDecimal;
	}

	/**
	 * 获得某个url地址页面的对象
	 * 
	 * @param webClient
	 * @param url
	 * @return
	 */
	public HtmlPage getPage(WebClient webClient, String url) {
		try {
			HtmlPage htmlPage = webClient.getPage(url);
			return htmlPage;
		} catch (FailingHttpStatusCodeException | IOException e) {
			logger.error("error get page >>> " + url, e);
		}
		return null;
	}

	/**
	 * 生意社
	 * 
	 * @param materialName
	 */
	public void sys(String materialName, WebClient webClient, Material material) {
		HtmlPage htmlPage = null;
		BigDecimal bigDecimal = null;
		String area = material.getPriceRegion();
		String materialBigTypeName = material.getMaterialBigTypeName();
		String materialSmallTypeName = material.getMaterialSmallTypeName();
		switch (materialName.toUpperCase().trim()) {
		case "ABS":
			// ABS，选择：中油华东，牌号:0215A http://abs.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://abs.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "中油华东", "牌号:0215A", null);
			break;
		case "HDPE":
			// HDPE，选择：大庆石化，牌号:5000S http://hdpe.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://hdpe.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "大庆石化", "牌号:5000S", null);
			break;
		case "LLDPE":
			// LLDPE，选择:大庆石化，牌号:7042 http://pe.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://pe.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "大庆石化", "牌号:7042", null);
			break;
		case "PP":
			// PP，选择:大庆石化 ，牌号:T30S http://pp.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://pp.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "大庆石化", "牌号:T30S", null);
			break;
		case "PVC":
			// PVC，选择:泰山盐化，牌号:SG5 http://pvc.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://pvc.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "泰山盐化", "牌号:SG5", null);
			break;
		case "PS":
			// PS，选择产地：广州石化，牌号:525 http://ps.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://ps.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "广州石化", "牌号:525", null);
			break;
		case "BOPP":
			// BOPP，选择:浙江奔多，分类:普通型(A类) http://bopp.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://bopp.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "浙江奔多", "分类:普通型(A类)", null);
			break;
		case "CPP":
			// cpp，选择：广东利盛商行， 型号:蒸煮膜 http://cpp.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://cpp.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "广东利盛商行", "型号:蒸煮膜", null);
			break;
		case "PET":
			// PET，选择:上海远纺，用途级别:水瓶级 http://pet.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("塑膜类") && materialSmallTypeName.equals("塑料")) {

			}
			htmlPage = getPage(webClient, "http://pet.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "上海远纺", "用途级别:水瓶级", null);
			break;
		case "不锈钢板":
			// 不锈钢板：选择天津巨龙，材质:304/2B，太钢 http://bxgb.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("钢材类") && materialSmallTypeName.equals("板材")) {

			}
			htmlPage = getPage(webClient, "http://bxgb.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "天津巨龙", "材质:304/2B", "太钢");
			break;
		case "冷轧板":
			// 冷轧板：选择江苏汇泽，牌号:DC01，马钢 http://lyb.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("钢材类") && materialSmallTypeName.equals("板材")) {

			}
			htmlPage = getPage(webClient, "http://lyb.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "江苏汇泽", "牌号:DC01", "马钢");
			break;
		case "铜":
			// 铜：选择上海亘凯，品名:1#电解铜 http://cu.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("钢材类") && materialSmallTypeName.equals("板材")) {

			}
			htmlPage = getPage(webClient, "http://cu.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "上海亘凯", "品名:1#电解铜", null);
			break;
		case "32S涤纶纱":
			// 32S涤纶纱：长兴轻纺，纱支:32S http://dls.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("纺织类") && materialSmallTypeName.equals("纱线")) {

			}
			htmlPage = getPage(webClient, "http://dls.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "长兴轻纺", "纱支:32S", null);
			break;
		case "21S棉纱":
			// 21S棉纱：选择耐拉纺织，纱支:21S http://ms.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("纺织类") && materialSmallTypeName.equals("纱线")) {

			}
			htmlPage = getPage(webClient, "http://ms.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "耐拉纺织", "纱支:21S", null);
			break;
		case "涤纶DTY":
			// 涤纶DTY：选择惠丰化纤--粗细:150D http://dldty.100ppi.com/price/plist-840-sA276a945-1.html
			if ("全国".equals(area) && materialBigTypeName.equals("纺织类") && materialSmallTypeName.equals("纱线")) {

			}
			htmlPage = getPage(webClient, "http://dldty.100ppi.com/price/plist-840-sA276a945-1.html");
			bigDecimal = getValueSYS(htmlPage, "惠丰化纤", "粗细:150D", null);
			break;
		case "灰板纸":
			// 灰板纸：东莞天山，分类:灰板纸 http://paper.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("纸张类") && materialSmallTypeName.equals("灰板纸")) {

			}
			htmlPage = getPage(webClient, "http://paper.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "东莞天山", "分类:灰板纸", null);
			break;
		case "木浆":
			// 木浆：选择嘉阳创业，分类:阔叶木浆 http://woodpulp.100ppi.com/price/
			if ("全国".equals(area) && materialBigTypeName.equals("纸张类") && materialSmallTypeName.equals("木浆")) {

			}
			htmlPage = getPage(webClient, "http://woodpulp.100ppi.com/price/");
			bigDecimal = getValueSYS(htmlPage, "嘉阳创业", "分类:阔叶木浆", null);
			break;
		case "WTI原油":
			// 生意社原油页面下，WTI原油走势下，NYMEX交易所对应的WTI原油价格 http://www.100ppi.com/crudeoil/ (美元/桶)
			if ("全国".equals(area) && materialBigTypeName.equals("石油类") && materialSmallTypeName.equals("原油")) {
				htmlPage = getPage(webClient, "http://www.100ppi.com/crudeoil/");
				if (htmlPage == null)
					return;
				List<?> list = htmlPage.getByXPath("//div/div/div/div/div/ul[@class='vbor']");
				for (Object object : list) {
					try {
						HtmlUnorderedList htmlUnorderedList = (HtmlUnorderedList) object;
						String string = htmlUnorderedList.asText();
						if (string.contains("NYMEX") && string.contains("WTI原油")) {
							Iterable<DomElement> elements = htmlUnorderedList.getChildElements();
							List<DomElement> list2 = new ArrayList<>();
							for (DomElement domElement : elements) {
								list2.add(domElement);
							}
							for (int i = 0; i < list2.size(); i++) {
								if (i == 2) {
									Object object2 = list2.get(i);
									DomElement domElement = (DomElement) object2;
									String value = domElement.asText().trim().replace(",", "");
									bigDecimal = new BigDecimal(value);
								}
							}
						}
					} catch (Exception e) {
						logger.error("error WTI >>>", e);
					}
				}
			}
			break;

		default:
			break;
		}
		logger.info(materialName + "===========================================" + bigDecimal);
		if (bigDecimal != null) {
			material.setPrice(bigDecimal);
			material.setPriceDate(new Date());
			material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			materialDao.insertMaterialPrice(material.toMap());
		}
	}

	public void ymt(String materialName, WebClient webClient, Material material) {
		HtmlPage htmlPage = null;
		BigDecimal bigDecimal = null;
		String area = material.getPriceRegion();
		String materialBigTypeName = material.getMaterialBigTypeName();
		String materialSmallTypeName = material.getMaterialSmallTypeName();
		switch (materialName.toUpperCase().trim()) {
		// 荸荠 全国 http://hangqing.ymt.com/jiage/7196
		case "荸荠":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7196");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 冬笋 全国 http://hangqing.ymt.com/jiage/7437
		case "冬笋":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7437");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 竹笋 全国 http://hangqing.ymt.com/jiage/8349
		case "竹笋":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8349");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 土豆 全国 http://hangqing.ymt.com/jiage/8397
		// 土豆 内蒙古 http://hangqing.ymt.com/jiage/8397_7
		// 土豆 山东 http://hangqing.ymt.com/jiage/8397_16
		case "土豆":
			if ("内蒙古".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8397_7");
			} else if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8397_16");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8397");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 芋头 全国 http://hangqing.ymt.com/jiage/8544
		case "芋头":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8544");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 莲藕 全国 http://hangqing.ymt.com/jiage/8053
		case "莲藕":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8053");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 红薯 全国 http://hangqing.ymt.com/jiage/7710
		// 红薯 福建 http://hangqing.ymt.com/jiage/7710_14
		// 红薯 山东 http://hangqing.ymt.com/jiage/7710_16
		case "红薯":
			if ("福建".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7710_14");
			} else if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7710_16");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7710");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 大枣 全国 http://hangqing.ymt.com/jiage/9856
		// 大枣 山东 http://hangqing.ymt.com/jiage/9856_16
		// 大枣 陕西 http://hangqing.ymt.com/jiage/9856_27
		// 大枣 甘肃 http://hangqing.ymt.com/jiage/9856_28
		case "大枣":
			if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9856_16");
			} else if ("陕西".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9856_27");
			} else if ("甘肃".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9856_28");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9856");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 香蕉 全国 http://hangqing.ymt.com/jiage/8453
		case "香蕉":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8453");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 葡萄 全国 http://hangqing.ymt.com/jiage/8205
		// 葡萄 新疆 http://hangqing.ymt.com/jiage/8205_31
		case "葡萄":
			if ("新疆".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8205_31");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8205");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 苹果 全国 http://hangqing.ymt.com/jiage/8199
		// 苹果 山东 http://hangqing.ymt.com/jiage/8199_16
		// 苹果 陕西 http://hangqing.ymt.com/jiage/8199_27
		case "苹果":
			if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8199_16");
			} else if ("陕西".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8199_27");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8199");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 杨梅 全国 http://hangqing.ymt.com/jiage
		case "杨梅":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage");
			}
			bigDecimal = getValueYMT1(htmlPage, "杨梅价格");
			break;
		// 樱桃 全国 http://hangqing.ymt.com/jiage/8360
		case "樱桃":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8360");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 芒果 全国 http://hangqing.ymt.com/jiage/8112
		case "芒果":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8112");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 猕猴桃 全国 http://hangqing.ymt.com/jiage/7734
		// 猕猴桃 四川 http://hangqing.ymt.com/jiage/7734_23
		case "猕猴桃":
			if ("四川".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7734_23");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7734");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 山楂 全国 http://hangqing.ymt.com/jiage/8268
		// 山楂 山东 http://hangqing.ymt.com/jiage/8268_16
		case "山楂":
			if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8268_16");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8268");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 草莓 全国 http://hangqing.ymt.com/jiage/7252
		case "草莓":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7252");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 木瓜 全国 http://hangqing.ymt.com/jiage/8154
		case "木瓜":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8154");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 梨 全国 http://hangqing.ymt.com/jiage/8031
		case "梨":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8031");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 柿子 全国 http://hangqing.ymt.com/jiage/8315
		case "柿子":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8315");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 柠檬 全国 http://hangqing.ymt.com/jiage/8167
		case "柠檬":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8167");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 枣 全国 http://hangqing.ymt.com/jiage/8556
		// 枣 山东 http://hangqing.ymt.com/jiage/8556_16
		// 枣 新疆 http://hangqing.ymt.com/jiage/8556_31
		// 枣 陕西 http://hangqing.ymt.com/jiage/8556_27
		// 枣 河北 http://hangqing.ymt.com/jiage/8556 ?? http://hangqing.ymt.com/jiage/8556_5
		case "枣":
			if ("山东".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8556_16");
			} else if ("新疆".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8556_31");
			} else if ("陕西".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8556_27");
			} else if ("河北".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8556_5");
			} else if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8556");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 脐橙 全国 http://hangqing.ymt.com/jiage/7277
		case "脐橙":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7277");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 甜橙 全国 http://hangqing.ymt.com/jiage/7278
		case "甜橙":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7278");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 板栗 全国 http://hangqing.ymt.com/jiage/7171
		// 板栗 河北 http://hangqing.ymt.com/jiage/7171_5
		// 板栗 湖北 http://hangqing.ymt.com/jiage/7171_18
		case "板栗":
			if ("河北".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("板栗")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7171_5");
			} else if ("湖北".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("板栗")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7171_18");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("板栗")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7171");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 红枣 全国 http://hangqing.ymt.com/jiage/7731
		// 红枣 新疆 http://hangqing.ymt.com/jiage/7731_31
		// 红枣 山西 http://hangqing.ymt.com/jiage/7731_6
		// 红枣 河南 http://hangqing.ymt.com/jiage/7731_17
		case "红枣":
			if ("新疆".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7731_31");
			} else if ("山西".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7731_6");
			} else if ("河南".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7731_17");
			} else if ("全国".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7731");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 葵花籽 全国 http://hangqing.ymt.com/jiage/8012
		case "葵花籽":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("葵花籽")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8012");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 核桃 全国 http://hangqing.ymt.com/jiage/7645
		// 核桃 陕西 http://hangqing.ymt.com/jiage/7645_27
		// 核桃 新疆 http://hangqing.ymt.com/jiage/7645_31
		// 核桃 甘肃 http://hangqing.ymt.com/jiage/7645_28
		case "核桃":
			if ("陕西".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("核桃")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7645_27");
			} else if ("新疆".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("核桃")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7645_31");
			} else if ("甘肃".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("核桃")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7645_28");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("核桃")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7645");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 柿饼 全国 http://hangqing.ymt.com/jiage/306960
		case "柿饼":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("柿饼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/306960");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 花生米 全国 http://hangqing.ymt.com/jiage/7774
		case "花生米":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7774");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 杏仁 全国 http://hangqing.ymt.com/jiage/8480
		// 杏仁 广东 http://hangqing.ymt.com/jiage/8480_20
		case "杏仁":
			if ("广东".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("杏仁")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8480_20");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("杏仁")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8480");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 夏威夷果 全国 http://hangqing.ymt.com/jiage/10273
		case "夏威夷果":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("夏威夷果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10273");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 腰果 全国 http://hangqing.ymt.com/jiage/10307
		// 腰果 广东 http://hangqing.ymt.com/jiage/10307_20
		case "腰果":
			if ("广东".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("腰果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10307_20");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("腰果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10307");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 开心果 全国 http://hangqing.ymt.com/jiage/8001
		// 开心果 广东 http://hangqing.ymt.com/jiage/8001_20
		case "开心果":
			if ("广东".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("开心果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8001_20");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("开心果")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8001");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 蜜枣 全国 http://hangqing.ymt.com/jiage/8137
		// 蜜枣 陕西 http://hangqing.ymt.com/jiage/8137_27
		case "蜜枣":
			if ("陕西".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8137_27");
			} else if ("全国".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8137");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 话梅 全国 http://hangqing.ymt.com/jiage/53835
		// 话梅 广东 http://hangqing.ymt.com/jiage/53835_20
		case "话梅":
			if ("广东".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("话梅")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/53835_20");
			} else if ("全国".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("话梅")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/53835");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 葡萄干 全国 http://hangqing.ymt.com/jiage/8206
		case "葡萄干":
			if ("全国".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("葡萄")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8206");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 干山楂 全国 http://hangqing.ymt.com/jiage/442164
		case "干山楂":
			if ("全国".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("山楂")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/442164");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 松子 全国 http://hangqing.ymt.com/jiage/10271
		case "松子":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("松子")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10271");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 鸡蛋 全国 http://hangqing.ymt.com/jiage/7850
		case "鸡蛋":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7850");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 牛肉 全国 http://hangqing.ymt.com/jiage/8173
		case "牛肉":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("牛")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8173");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 羊肉 全国 http://hangqing.ymt.com/jiage/8501
		case "羊肉":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("羊")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8501");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 肉鸡 全国 http://hangqing.ymt.com/jiage/9790
		case "肉鸡":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9790");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 猪肉 全国 http://hangqing.ymt.com/jiage/8572
		case "猪肉":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8572");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 鹌鹑蛋 全国 http://hangqing.ymt.com/jiage/15016
		case "鹌鹑蛋":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鹌鹑")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/15016");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 鹌鹑 全国 http://hangqing.ymt.com/jiage/16005
		case "鹌鹑":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鹌鹑")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/16005");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 杏鲍菇 全国 http://hangqing.ymt.com/jiage/7184
		case "杏鲍菇":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7184");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 香菇 全国 http://hangqing.ymt.com/jiage/8451
		case "香菇":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8451");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 平菇 全国 http://hangqing.ymt.com/jiage/8198
		case "平菇":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8198");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 金针菇 全国 http://hangqing.ymt.com/jiage/7947
		case "金针菇":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7947");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 木耳 全国 http://hangqing.ymt.com/jiage/8153
		case "木耳":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8153");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 银耳 全国 http://hangqing.ymt.com/jiage/8512
		case "银耳":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8512");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 牛肝菌 全国 http://hangqing.ymt.com/jiage/9835
		case "牛肝菌":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/9835");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 蘑菇 全国 http://hangqing.ymt.com/jiage/307404
		case "蘑菇":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8146");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 绿豆 全国 http://hangqing.ymt.com/jiage/306895
		// 绿豆 内蒙古 http://hangqing.ymt.com/jiage/306895_7
		case "绿豆":
			if ("内蒙古".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/306895_7");
			} else if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/306895");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 小茴香 全国 http://hangqing.ymt.com/jiage/7827
		case "小茴香":
			if ("全国".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("小茴香")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7827");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 藕 全国 http://hangqing.ymt.com/jiage/306732
		case "藕":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/306732");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 猪蹄 全国 http://hangqing.ymt.com/jiage_c69 http://hangqing.ymt.com/jiage/307506
		case "猪蹄":
			if ("全国".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/307506");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 甘草 全国 http://hangqing.ymt.com/jiage/10501
		case "甘草":
			if ("全国".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("小茴香")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10501");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 莲子 全国 http://hangqing.ymt.com/jiage/10270
		case "莲子":
			if ("全国".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("莲子")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/10270");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 赤小豆 全国 http://hangqing.ymt.com/jiage/306854
		case "赤小豆":
			if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/306854");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 陈皮 全国 http://hangqing.ymt.com/jiage/307666
		case "陈皮":
			if ("全国".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("陈皮")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/307666");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 扇贝 全国 http://hangqing.ymt.com/jiage/8280
		case "扇贝":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("贝壳类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8280");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 带鱼 全国 http://hangqing.ymt.com/jiage/7392
		case "带鱼":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7392");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 鲳鱼 全国 http://hangqing.ymt.com/jiage/7259
		case "鲳鱼":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7259");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 草鱼 全国 http://hangqing.ymt.com/jiage/7253
		case "草鱼":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7253");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 黄鱼 全国 http://hangqing.ymt.com/jiage/8833
		case "黄鱼":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8833");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 凤尾鱼 全国 http://hangqing.ymt.com/jiage/27445
		case "凤尾鱼":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/27445");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 虾仁 全国 http://hangqing.ymt.com/jiage/8441
		case "虾仁":
			if ("全国".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8441");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 大米 全国 http://hangqing.ymt.com/jiage/7361
		case "大米":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7361");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 蚕豆 全国 http://hangqing.ymt.com/jiage/7243
		// 蚕豆 江苏 http://hangqing.ymt.com/jiage/7243_11
		// 蚕豆 江苏 http://hangqing.ymt.com/jiage/7243_11 已确认重复
		case "蚕豆":
			if ("江苏".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7243_11");
			} else if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7243");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 棕榈油 全国 http://hangqing.ymt.com/jiage/8584
		case "棕榈油":
			if ("全国".equals(area) && materialBigTypeName.equals("粮油类") && materialSmallTypeName.equals("棕榈油")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8584");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 面粉 全国 http://hangqing.ymt.com/jiage/8139
		case "面粉":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面粉")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8139");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 糯米 全国 http://hangqing.ymt.com/jiage/8180
		case "糯米":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8180");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 玉米 全国 http://hangqing.ymt.com/jiage/8534
		case "玉米":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("玉米")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/8534");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 花生 全国 http://hangqing.ymt.com/jiage/7773
		// 花生 安徽 http://hangqing.ymt.com/jiage/7773_13
		// 花生 四川 http://hangqing.ymt.com/jiage/7773_23
		case "花生":
			if ("安徽".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7773_13");
			} else if ("四川".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7773_23");
			} else if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7773");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 红豆 全国 http://hangqing.ymt.com/jiage/7679
		case "红豆":
			if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7679");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 粳米 全国 http://hangqing.ymt.com/jiage/7958
		case "粳米":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7958");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 淀粉 全国 http://hangqing.ymt.com/jiage/7411
		case "淀粉":
			if ("全国".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("淀粉")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7411");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 黄豆 全国 http://hangqing.ymt.com/jiage/7795
		case "黄豆":
			if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7795");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 黑豆 全国 http://hangqing.ymt.com/jiage/7662
		case "黑豆":
			if ("全国".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7662");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 白芝麻 全国 http://hangqing.ymt.com/jiage/7159
		case "白芝麻":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("芝麻")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/7159");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;
		// 黑芝麻 全国 http://hangqing.ymt.com/jiage/7673
		case "黑芝麻":
			if ("全国".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("芝麻")) {
				htmlPage = getPage(webClient, "http://hangqing.ymt.com/jiage/472650");
			}
			bigDecimal = getValueYMT(htmlPage);
			break;

		default:
			break;
		}
		logger.info(materialName + "===========================================" + bigDecimal);
		if (bigDecimal != null) {
			material.setPrice(bigDecimal);
			material.setPriceDate(new Date());
			material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			materialDao.insertMaterialPrice(material.toMap());
		}
	}

	public void ljt(String materialName, WebClient webClient, Material material) {
		BigDecimal bigDecimal = null;
		String area = material.getPriceRegion();
		String materialBigTypeName = material.getMaterialBigTypeName();
		String materialSmallTypeName = material.getMaterialSmallTypeName();
		boolean flag = false;
		try {
			if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米") && materialName.equals("东北粳米长粒")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面粉") && materialName.equals("玉米粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面粉") && materialName.equals("特一粉(富强粉)")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("玉米") && materialName.equals("食用玉米")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("淀粉") && materialName.equals("淀粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米") && materialName.equals("糯米")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米") && materialName.equals("糯米粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面筋") && materialName.equals("面筋")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("苏北黄豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("东北黄豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("芸豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("豌豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("蚕豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("赤豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆类") && materialName.equals("绿豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("腐竹") && materialName.equals("腐竹")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("百叶") && materialName.equals("百叶")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("粮油类") && materialSmallTypeName.equals("大豆油") && materialName.equals("一级豆油")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪") && materialName.equals("猪后腿肉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪") && materialName.equals("猪前腿肉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪") && materialName.equals("猪脚爪-kg")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪") && materialName.equals("猪尾巴")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪") && materialName.equals("猪耳朵")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("牛") && materialName.equals("牛肉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("牛") && materialName.equals("牛肚")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("牛") && materialName.equals("牛百叶")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("羊") && materialName.equals("羊肉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("白条鸡")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("鸡蛋")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("翅尖")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("翅中")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("翅根")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("鸡肫")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("鸡爪")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡") && materialName.equals("鸡腿")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鹌鹑") && materialName.equals("鹌鹑蛋")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸭") && materialName.equals("麻鸭")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸭") && materialName.equals("鸭肫")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鹅") && materialName.equals("鹅")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸽") && materialName.equals("鸽子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("河鱼") && materialName.equals("草鱼")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("河鱼") && materialName.equals("凤尾鱼")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼") && materialName.equals("带鱼")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼") && materialName.equals("目鱼片")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼") && materialName.equals("小黄鱼")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("虾") && materialName.equals("虾仁")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("虾") && materialName.equals("开洋")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海带") && materialName.equals("海带丝")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("山芋")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("玉米")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("扁豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("毛豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("刀豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("豇豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("四季豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("西红柿")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("陈土豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("新土豆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("胡萝卜")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜") && materialName.equals("莲藕")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("黄瓜")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("圣女果")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("菠萝蜜")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("蓝莓")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("柠檬")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("进口脐橙")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("脐橙")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("山竹")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("榴莲")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("火龙果")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("山楂")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("水仙芒 ")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("台芒")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("龙眼")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("樱桃")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("杨梅")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("哈密瓜")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("水蜜桃")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("马奶子葡萄")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("巨峰葡萄")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("葡萄") && materialName.equals("葡萄干")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("砀山梨")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("菠萝蜜")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("草莓")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("樱桃")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣") && materialName.equals("红枣")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣") && materialName.equals("蜜枣")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("蜜饯") && materialSmallTypeName.equals("枣") && materialName.equals("金丝红枣")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生") && materialName.equals("花生")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生") && materialName.equals("花生果")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("花生") && materialName.equals("花生米")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("白瓜子") && materialName.equals("东北白瓜子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("白瓜子") && materialName.equals("云南白瓜子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("白瓜子") && materialName.equals("白瓜子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("葵花籽") && materialName.equals("吉林葵花籽")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("葵花籽") && materialName.equals("内蒙一级葵花籽")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("葵花籽") && materialName.equals("赤峰葵花籽")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("芝麻") && materialName.equals("脱皮芝麻")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("芝麻") && materialName.equals("白芝麻")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("芝麻") && materialName.equals("黑芝麻")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("葵花籽") && materialName.equals("葵花子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("西瓜子") && materialName.equals("大黑西瓜子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("松子") && materialName.equals("松子")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("开心果") && materialName.equals("开心果")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("腰果") && materialName.equals("腰果")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("莲子") && materialName.equals("莲心")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("核桃") && materialName.equals("桃仁")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("干货") && materialName.equals("笋干")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("炒货") && materialSmallTypeName.equals("干货") && materialName.equals("桂圆")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类") && materialName.equals("木耳")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类") && materialName.equals("香菇")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("菌菇类") && materialName.equals("香菇")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("山东富士")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("进口香蕉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("香蕉干")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("野生猕猴桃")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果") && materialName.equals("国产猕猴桃")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("味精")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("鸡精")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("咖喱粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("花椒粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("伍香粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("食盐")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("桂皮")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("花椒")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("黑胡椒粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("白胡椒粉")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("孜然")) {
				flag = true;
			} else if ("凌家塘".equals(area) && materialBigTypeName.equals("调味料") && materialSmallTypeName.equals("调味料") && materialName.equals("小茴香")) {
				flag = true;
			}
			if (flag) {
				String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				String url = "http://www.ljt.cn/priceinfo.asp?page=1&bdate=" + date + "&todate=" + date + "&pname=" + URLEncoder.encode(materialName, "GBK") + "&pclass=0";
				HtmlPage page = getPage(webClient, url);
				if (page == null) {
					logger.warn("get url error >>> " + url);
					return;
				}
				List<?> list = page.getByXPath("//tr[@bgcolor='FFFFFF']");
				for (Object object : list) {
					HtmlTableRow htmlTableRow = (HtmlTableRow) object;
					String value = htmlTableRow.getCell(0).asText().trim();
					if (value.equals(materialName)) {
						HtmlTableCell cell = htmlTableRow.getCell(3);
						value = cell.asText().trim();
						bigDecimal = new BigDecimal(value);
						break;
					}
				}
				logger.info(materialName + "===========================================" + bigDecimal);
				if (bigDecimal != null) {
					material.setPrice(bigDecimal);
					material.setPriceDate(new Date());
					material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
					materialDao.insertMaterialPrice(material.toMap());
				} else {
					logger.warn(page.asText());
				}
			}
		} catch (IOException e) {
			logger.error("error get date ljt >> " + materialName, e);
		}

	}

	public void all50(String materialName, WebClient webClient, Material material) {
		String url = "http://www.stats.gov.cn/was5/web/search?channelid=288041&andsen=50%E4%B8%AA%E5%9F%8E%E5%B8%82%E4%B8%BB%E8%A6%81%E9%A3%9F%E5%93%81";
		try {
			webClient.getOptions().setJavaScriptEnabled(true);
			HtmlPage page = getPage(webClient, url);
			if (page == null) {
				logger.warn("error get page url >>>>  " + url);
				return;
			}
			List<?> list = page.getByXPath("//ul/li/span/font/a");
			BigDecimal bigDecimal = null;
			Date date = null;
			for (Object object : list) {
				HtmlAnchor htmlAnchor = (HtmlAnchor) object;
				String href = htmlAnchor.getHrefAttribute();
				page = webClient.getPage(href);
				if (!page.asText().contains("50个城市主要食品平均价格变动情况")) {
					continue;
				}
				DomElement domElement = page.getFirstByXPath("//h2[@class='xilan_tit']");
				String value = domElement.asText();
				Pattern pattern = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}");
				Matcher matcher = pattern.matcher(value);
				if (matcher.find()) {
					String dateString = matcher.group();
					date = new SimpleDateFormat("yyyy年MM月dd").parse(dateString);
				}
				String area = material.getPriceRegion();
				String materialBigTypeName = material.getMaterialBigTypeName();
				String materialSmallTypeName = material.getMaterialSmallTypeName();
				switch (materialName) {
				// 米 粳米
				case "粳米":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("米")) {
						bigDecimal = getAll50(page, "大 米", null);
					}
					break;
				// 面粉 富强粉
				case "富强粉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面粉")) {
						bigDecimal = getAll50(page, "面 粉", "富强粉");
					}
					break;
				// 面粉 标准粉
				case "标准粉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮食") && materialSmallTypeName.equals("面粉")) {
						bigDecimal = getAll50(page, "面 粉", "标准粉");
					}
					break;
				// 豆腐 豆腐
				case "豆腐":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("豆类&豆制品") && materialSmallTypeName.equals("豆腐")) {
						bigDecimal = getAll50(page, "豆制品", null);
					}
					break;
				// 花生油 花生油-一级压榨
				case "花生油-一级压榨":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮油类") && materialSmallTypeName.equals("花生油")) {
						bigDecimal = getAll50(page, "花生油", null);
					}
					break;
				// 大豆油 大豆油-5L桶装
				case "大豆油-5L桶装":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮油类") && materialSmallTypeName.equals("大豆油")) {
						bigDecimal = getAll50(page, "大豆油", null);
					}
					break;
				// 菜籽油 菜籽油-一级散装
				case "菜籽油-一级散装":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("粮油类") && materialSmallTypeName.equals("菜籽油")) {
						bigDecimal = getAll50(page, "菜籽油", null);
					}
					break;
				// 猪 猪肉后臀尖(后腿肉)
				case "猪肉后臀尖(后腿肉)":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪")) {
						bigDecimal = getAll50(page, "猪 肉", "猪肉后臀尖(后腿肉)");
					}
					break;
				// 猪 猪肉五花肉
				case "猪肉五花肉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("猪")) {
						bigDecimal = getAll50(page, "猪 肉", "五花肉");
					}
					break;
				// 牛 腿肉
				// 羊 腿肉
				case "腿肉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("牛")) {
						if (material.getMaterialSmallTypeName().equals("牛")) {
							bigDecimal = getAll50(page, "牛 肉", null);
						}
					}
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("羊")) {
						if (material.getMaterialSmallTypeName().equals("羊")) {
							bigDecimal = getAll50(page, "羊 肉", null);
						}
					}
					break;
				// 鸡 白条鸡
				case "白条鸡":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡")) {
						bigDecimal = getAll50(page, "鸡", "白条鸡");
					}
					break;
				// 鸡 鸡胸肉
				case "鸡胸肉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡")) {
						bigDecimal = getAll50(page, "鸡", "鸡胸肉");
					}
					break;
				// 鸡 鸡蛋
				case "鸡蛋":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸡")) {
						bigDecimal = getAll50(page, "鸡 蛋", null);
					}
					break;
				// 鸭 白条鸭
				case "白条鸭":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("禽肉类") && materialSmallTypeName.equals("鸭")) {
						bigDecimal = getAll50(page, "鸭", null);
					}
					break;
				// 河鱼 活鲤鱼
				case "活鲤鱼":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("河鱼")) {
						bigDecimal = getAll50(page, "活鲤鱼", null);
					}
					break;
				// 河鱼 活草鱼
				case "活草鱼":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("河鱼")) {
						bigDecimal = getAll50(page, "活草鱼", null);
					}
					break;
				// 海鱼 带鱼
				case "带鱼":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("水产类") && materialSmallTypeName.equals("海鱼")) {
						bigDecimal = getAll50(page, "带 鱼", null);
					}
					break;
				// 蔬菜 大白菜
				case "大白菜":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "大白菜", null);
					}
					break;
				// 蔬菜 油菜
				case "油菜":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "油 菜", null);
					}
					break;
				// 蔬菜 芹菜
				case "芹菜":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "芹 菜", null);
					}
					break;
				// 蔬菜 黄瓜
				case "黄瓜":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "黄 瓜", null);
					}
					break;
				// 蔬菜 西红柿
				case "西红柿":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "西红柿", null);
					}
					break;
				// 蔬菜 豆角
				case "豆角":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "豆 角", null);
					}
					break;
				// 蔬菜 土豆
				case "土豆":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("蔬菜")) {
						bigDecimal = getAll50(page, "土 豆", null);
					}
					break;
				// 苹果 富士苹果
				case "苹果":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
						bigDecimal = getAll50(page, "苹 果", null);
					}
					break;
				// 水果 国产香蕉
				case "国产香蕉":
					if ("全国50个城市".equals(area) && materialBigTypeName.equals("果蔬") && materialSmallTypeName.equals("水果")) {
						bigDecimal = getAll50(page, "香 蕉", null);
					}
					break;
				default:
					break;
				}
				break;
			}
			logger.info(materialName + "===========================================" + bigDecimal);
			if (bigDecimal != null) {
				material.setPrice(bigDecimal);
				material.setPriceDate(date);
				material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(date));
				materialDao.insertMaterialPrice(material.toMap());
			}
		} catch (FailingHttpStatusCodeException | IOException | ParseException e) {
			logger.error("all50 error >>>>>>> " + e.getMessage(), e);
		}
	}

	public BigDecimal getAll50(HtmlPage htmlPage, String name, String spec) {
		BigDecimal bigDecimal = null;
		if (htmlPage == null) {
			logger.warn("getAll50 page is null >>>> name:" + name + " spec:" + spec);
			return null;
		}
		List<?> list = htmlPage.getByXPath("//table[@class='MsoNormalTable']/tbody/tr");
		for (Object object : list) {
			HtmlTableRow htmlTableRow = (HtmlTableRow) object;
			String cell0 = htmlTableRow.getCell(0).asText().trim();
			if (!cell0.equals(name)) {
				continue;
			}
			if (spec != null) {
				String cell1 = htmlTableRow.getCell(1).asText().trim();
				if (!cell1.equals(spec)) {
					continue;
				}
			}
			String cell3 = htmlTableRow.getCell(3).asText().trim();
			try {
				bigDecimal = new BigDecimal(cell3);
				String cell2 = htmlTableRow.getCell(2).asText().trim();
				if (cell2.equals("升")) {
					bigDecimal = bigDecimal.multiply(new BigDecimal(0.92));
				}
			} catch (Exception e) {
				logger.error("getAll50 data error >>>> name:" + name + " spec:" + spec, e);
			}
			break;
		}
		if (bigDecimal != null) {
			bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			logger.warn(htmlPage.asText());
		}
		return bigDecimal;
	}

	public void abroadSite1(String materialName, WebClient webClient, Material material) {
		BigDecimal bigDecimal = null;
		// http://future.aae.wisc.edu/data/weekly_values/by_area/1701?duration=1&year=2015&area=US
		String area = "US";
		String priceRegion = material.getPriceRegion();
		String materialBigTypeName = material.getMaterialBigTypeName();
		String materialSmallTypeName = material.getMaterialSmallTypeName();

		if ("澳洲".equals(priceRegion)) {
			area = "OCEANIA";
		} else if ("欧洲".equals(priceRegion)) {
			area = "Europe";
		} else if ("美国".equals(priceRegion)) {
			area = "US";
		} else if ("西欧".equals(priceRegion)) {
			area = "WESTERN EUROPE";
		}
		if (materialName.equals("黄油") && materialBigTypeName.equals("国外网站") && materialSmallTypeName.equals("油")) {
			String url = "http://future.aae.wisc.edu/data/weekly_values/by_area/1701?duration=1&year=2015&area=" + area;
			HtmlPage htmlPage = getPage(webClient, url);
			if (htmlPage == null) {
				logger.warn("abroadSite1 page is null >>>> materialName:" + materialName);
				return;
			}
			try {
				List<?> list = htmlPage.getByXPath("//div[@id='datagrid']/table/tbody/tr");
				Object object = list.get(list.size() - 1);
				HtmlTableRow htmlTableRow = (HtmlTableRow) object;
				String value = htmlTableRow.getCell(4).asText().trim();
				bigDecimal = new BigDecimal(value);
				bigDecimal = bigDecimal.multiply(new BigDecimal(1000));
				bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			} catch (Exception e) {
				logger.error("abroadSite1 error get data >>>> materialName:" + materialName);
			}
			logger.info(materialName + "===========================================" + bigDecimal);
			if (bigDecimal != null) {
				material.setPrice(bigDecimal);
				material.setPriceDate(new Date());
				material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				materialDao.insertMaterialPrice(material.toMap());
			}
		}
	}

	public void abroadSite2(String materialName, WebClient webClient, Material material) {
		// http://www.indexmundi.com/commodities/?commodity=coconut-oil
		// String priceRegion = material.getPriceRegion();
		String materialBigTypeName = material.getMaterialBigTypeName();
		String materialSmallTypeName = material.getMaterialSmallTypeName();
		if (materialName.equals("椰子油") && materialBigTypeName.equals("国外网站") && materialSmallTypeName.equals("油")) {
			HtmlPage htmlPage = getPage(webClient, "http://www.indexmundi.com/commodities/?commodity=coconut-oil");
			if (htmlPage == null) {
				logger.warn("abroadSite2 page is null >>>> materialName:" + materialName);
				return;
			}
			BigDecimal bigDecimal = null;
			try {
				List<?> list = htmlPage.getByXPath("//table[@id='gvPrices']/tbody/tr");
				HtmlTableRow htmlTableRow = (HtmlTableRow) list.get(list.size() - 1);
				String value = htmlTableRow.getCell(1).asText().replace(",", "").trim();
				bigDecimal = new BigDecimal(value);
				bigDecimal = bigDecimal.multiply(new BigDecimal(1000));
				bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
			} catch (Exception e) {
				logger.error("abroadSite2 error get data >>>> materialName:" + materialName);
			}
			logger.info(materialName + "===========================================" + bigDecimal);
			if (bigDecimal != null) {
				material.setPrice(bigDecimal);
				material.setPriceDate(new Date());
				material.setOrgName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				materialDao.insertMaterialPrice(material.toMap());
			}
		}
	}

	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_38);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String url = "https://laiyifen.tmall.com/?spm=a1z10.3-b-s.1997427721.d4918089.757b7bc5a13d4g";
		HtmlPage page = webClient.getPage(url);
System.out.println(page.asXml());
		BigDecimal bigDecimal = null;
		List<?> list = page.getByXPath("//tr[@bgcolor='FFFFFF']");
		for (Object object : list) {
			HtmlTableRow htmlTableRow = (HtmlTableRow) object;
			String value = htmlTableRow.getCell(0).asText().trim();
			if (value.equals("猪后腿肉")) {
				HtmlTableCell cell = htmlTableRow.getCell(3);
				value = cell.asText().trim();
				bigDecimal = new BigDecimal(value);
				break;
			}
		}
		System.out.println(bigDecimal);
		webClient.close();
	}

}
