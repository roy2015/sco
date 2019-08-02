package com.powere2e.sco.peripheral.webservice.sco.tbpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseMarketDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.GetMerchandiseMarketService;
import com.powere2e.sco.model.masterdata.MerchandiseMarket;
import com.powere2e.sco.peripheral.utils.HttpPostWebService;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;


public class GetMerchandiseMarketServiceImpl implements GetMerchandiseMarketService{

	private Logger logger = PeripheralFileUtils.logger;
	private static final long serialVersionUID = -876711205387274565L;
	private MerchandiseMarketDao merchandiseMarketDao;

	public MerchandiseMarketDao getMerchandiseMarketDao() {
		return merchandiseMarketDao;
	}

	public void setMerchandiseMarketDao(MerchandiseMarketDao merchandiseMarketDao) {
		this.merchandiseMarketDao = merchandiseMarketDao;
	}

	@Override
	public void insertMerchandiseMarket() {
		try {
			List<MerchandiseMarket> list = getMerchandiseMarket();
			if (list == null||list.size() == 0) {
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("list", list);
			merchandiseMarketDao.insertMerchandiseMarket(map);
			logger.info("TBPM商品上下市通知接收的数据已全部入库");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("TBPM商品上下市通知接收的数据异常："+e);
		}
	}
	
	public List<MerchandiseMarket> getMerchandiseMarket() throws Exception{
		String url = "http://10.1.0.196/cordys/com.eibus.web.soap.Gateway.wcp?organization=o=laiyifen,cn=cordys,cn=defaultInst,o=laiyifen";
					             
		String requestXML = 
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bpm=\"http://schemas.cordys.com/bpmtoa\">"+
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
				"<bpm:GetNewProductNoticeDetailToSco preserveSpace=\"no\" qAccess=\"0\" qValues=\"\"/>" +
				"</soapenv:Body>" +
				"</soapenv:Envelope>";
		String result = HttpPostWebService.invoke(url, requestXML);
		logger.info("TBPM商品上下市通知接收的数据为："+result);
        Document document = DocumentHelper.parseText(result);
        Element root = document.getRootElement();
        List<MerchandiseMarket> list = new ArrayList<>();
        parseNode(root, list);
        return list;
	}

	/**
	 * 从指定节点开始,递归遍历所有子节点  
	 * @param node 要遍历的节点
	 * @param list market集合
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static void parseNode(Element node,List<MerchandiseMarket> list) throws Exception {
		if (node.getName().equals("NEWPRODUCT_NOTICE")) {
			MerchandiseMarket market = new MerchandiseMarket();
			// 递归遍历当前节点所有的子节点
			List<Element> listElement = node.elements();// 所有一级子节点的list
			for (Element e : listElement) {// 遍历所有一级子节点
				switch (e.getName()) {
				case "PRODUCT_ID": //商品编号
					market.setMerchandiseCode(e.getTextTrim());
					break;
				case "UPORDOWN": //显示上市或下市
					market.setMarketType(e.getTextTrim());
					break;
				case "PROCESS_DATE": //上下市日期，格式YYYY-MM-DD
					market.setMarketDate(PeripheralParseUtils.strToDate(e.getTextTrim()));
					break;
				case "ISUPDOWN": //是否是上下市通知
					market.setIsUpDown(e.getTextTrim());
					break;
				default:
					break;
				}
			}
			list.add(market);
		} else {
			// 递归遍历当前节点所有的子节点
			List<Element> listElement = node.elements();// 所有一级子节点的list
			for (Element e : listElement) {// 遍历所有一级子节点
				parseNode(e,list);// 递归
			}
		}
	}
}
