package com.powere2e.sco.peripheral.webservice.sco.tbpm;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactoryDao;
import com.powere2e.sco.interfaces.service.peripheral.webservice.GetSupplierVisitFactoryService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;
import com.powere2e.sco.peripheral.utils.HttpPostWebService;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

public class GetSupplierVisitFactoryServiceImpl implements GetSupplierVisitFactoryService {

	private Logger logger = PeripheralFileUtils.logger;
	private static final long serialVersionUID = -9106940557601157285L;
	private static final String BPM_URL = "http://10.2.7.156/lyfbpm/LYFData/queryIPQCReport";
	private SupplierVisitFactoryDao supplierVisitFactoryDao;

	public SupplierVisitFactoryDao getSupplierVisitFactoryDao() {
		return supplierVisitFactoryDao;
	}

	public void setSupplierVisitFactoryDao(SupplierVisitFactoryDao supplierVisitFactoryDao) {
		this.supplierVisitFactoryDao = supplierVisitFactoryDao;
	}

	@Override
	public void insertSupplierVisitFactory() {
		try {
			List<SupplierVisitFactory> list = getMerchandiseMarket();
			if (list == null || list.size() == 0) {
				return;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("insertList", list);
			supplierVisitFactoryDao.insertSupplierVisitFactory(map);
			logger.info("TBPM供应商巡厂评分数据已全部入库");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("TBPM供应商巡厂评分数据异常：" + e);
		}
	}

    @Override
    public void insertSupplierVisitFactoryBPM() {
        try {
           List<SupplierVisitFactory> list = getMerchandiseMarketNew();
            if (list == null || list.size() == 0) {
                return;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("insertList", list);
            supplierVisitFactoryDao.insertSupplierVisitFactory(map);
            logger.info("BPM供应商巡厂评分数据已全部入库");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("BPM供应商巡厂评分数据异常：" + e);
        }
    }


    public List<SupplierVisitFactory> getMerchandiseMarket() throws Exception {
		String url = "http://10.1.0.196/cordys/com.eibus.web.soap.Gateway.wcp?organization=o=laiyifen,cn=cordys,cn=defaultInst,o=laiyifen";
		String requestXML = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bpm=\"http://schemas.cordys.com/bpmtoa\">" + "<soapenv:Header/>" + "<soapenv:Body>" + "<bpm:GetNewProductVisitReportDetailToSco preserveSpace=\"no\" qAccess=\"0\" qValues=\"\"/>" + "</soapenv:Body>" + "</soapenv:Envelope>";
		String result = HttpPostWebService.invoke(url, requestXML);
		logger.info("TBPM供应商巡厂评分接收的数据为：" + result);
		Document document = DocumentHelper.parseText(result);
		Element root = document.getRootElement();
		List<SupplierVisitFactory> list = new ArrayList<>();
		parseNode(root, list);
		return list;
	}

    public List<SupplierVisitFactory> getMerchandiseMarketNew() throws Exception {
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(BPM_URL);
        List <NameValuePair> parameters = new ArrayList<>();
        //todo
        NameValuePair valuePair = new BasicNameValuePair("date", "2019-07-10");
        parameters.add(valuePair);
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
        post.setEntity(formEntity);
        HttpResponse response = client.execute(post);
        StringBuilder result = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(),HTTP.UTF_8));
            String line = null;
            while((line = reader.readLine()) != null){
                result.append(line);
            }
        } catch (SocketTimeoutException e) {
            PeripheralFileUtils.logger.info("连接超时[" + BPM_URL + "]");
            throw e;
        } catch (java.net.ConnectException e) {
            PeripheralFileUtils.logger.info("连接失败[" + BPM_URL + "]");
            throw e;
        } catch (Exception e) {
            PeripheralFileUtils.logger.info("连接时出现异常[" + BPM_URL + "]");
            throw e;
        }

        logger.info("BPM供应商巡厂评分接收的数据为：" + result);
        List<SupplierVisitFactory> list = new ArrayList<>();
        String responseStr = result.toString();
        SupplierVisitFactoryResp supplierVisitFactoryResp = JSON.parseObject(responseStr, SupplierVisitFactoryResp.class);
        if (supplierVisitFactoryResp != null && supplierVisitFactoryResp.isSuccess()
                && supplierVisitFactoryResp.getData() != null &&
                supplierVisitFactoryResp.getData().length > 0 ) {
            SupplierVisitFactoryResp.SupplierVisitFactoryInnerVO[] data = supplierVisitFactoryResp.getData();
            for (SupplierVisitFactoryResp.SupplierVisitFactoryInnerVO innerVO : data) {
                SupplierVisitFactory visitFactory = new SupplierVisitFactory();
                visitFactory.setSupplierCode(innerVO.getSupplycode());
                if (StringUtils.isNotBlank(innerVO.getFactoryArea())) {
                    visitFactory.setFloorArea(new BigDecimal(innerVO.getFactoryArea()));
                }
                if (StringUtils.isNotBlank(innerVO.getWorkshopArea())) {
                    visitFactory.setPlantArea(new BigDecimal(innerVO.getWorkshopArea()));
                }
                if (StringUtils.isNotBlank(innerVO.getNumberOfCompany())) {
                    visitFactory.setEnterpriseCountPeople(Integer.parseInt(innerVO.getNumberOfCompany()));
                }
                if (StringUtils.isNotBlank(innerVO.getAnnualAsset())) {
                    visitFactory.setYearTotalOutput(new BigDecimal(innerVO.getAnnualAsset()));
                }
                if (StringUtils.isNotBlank(innerVO.getVisitTime())) {
                    visitFactory.setVisitFactoryDate(DateUtils.formatStrToDate(innerVO.getVisitTime().replaceAll("T", " "), "yyyy-MM-dd HH:mm:ss"));
                }
                if (StringUtils.isNotBlank(innerVO.getFullScore())) {
                    visitFactory.setFullScore(new BigDecimal(innerVO.getFullScore()));
                }
                if (StringUtils.isNotBlank(innerVO.getStandScore())) {
                    visitFactory.setQualifiedScore(new BigDecimal(innerVO.getStandScore()));
                }
                if (StringUtils.isNotBlank(innerVO.getVisitScore())) {
                    visitFactory.setScore(new BigDecimal(innerVO.getVisitScore()));
                }
                if (StringUtils.isNotBlank(innerVO.getVisitOpinion())) {
                    visitFactory.setVisitFactoryOpinion(innerVO.getVisitOpinion());
                }
                if (StringUtils.isNotBlank(innerVO.getVisitor())) {
                    visitFactory.setVisitFactoryPrincipal(innerVO.getVisitor());
                }
                list.add(visitFactory);
            }
        }
        return list;
    }

	/**
	 * 从指定节点开始,递归遍历所有子节点
	 * 
	 * @param node
	 *            要遍历的节点
	 * @param list
	 *            market集合
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void parseNode(Element node, List<SupplierVisitFactory> list) throws Exception {
		// 只取节点(Newproduct_Visitreport)的数据
		if (node.getName().equals("Newproduct_Visitreport")) {
			SupplierVisitFactory visitFactory = new SupplierVisitFactory();
			// 递归遍历当前节点所有的子节点
			List<Element> listElement = node.elements();// 所有一级子节点的list
			for (Element e : listElement) {// 遍历所有一级子节点
				switch (e.getName()) {
				case "SUPPLY_CODE": // 供应商编码
					visitFactory.setSupplierCode(e.getTextTrim());
					break;
				case "FACTORY_AREA": // 占地面积
					visitFactory.setFloorArea(new BigDecimal(e.getTextTrim()));
					break;
				case "WORKSHOP_AREA": // 车间面积
					visitFactory.setPlantArea(new BigDecimal(e.getTextTrim()));
					break;
				case "NUMBER_OF_COMPANY": // 企业总人数
					visitFactory.setEnterpriseCountPeople(Integer.parseInt(e.getTextTrim()));
					break;
				case "ANNUAL_ASSET": // 年总产值
					visitFactory.setYearTotalOutput(new BigDecimal(e.getTextTrim()));
					break;
				case "VISIT_TIME": // 巡厂日期,格式为(yyyy-MM-dd HH:mm:ss)
					visitFactory.setVisitFactoryDate(DateUtils.formatStrToDate(e.getTextTrim().replaceAll("T", " "), "yyyy-MM-dd HH:mm:ss"));
					break;
				case "FULL_SCORE": // 满分
					visitFactory.setFullScore(new BigDecimal(e.getTextTrim()));
					break;
				case "STAND_SCORE": // 合格分
					visitFactory.setQualifiedScore(new BigDecimal(e.getTextTrim()));
					break;
				case "VISIT_SCORE": // 供应商巡厂得分
					visitFactory.setScore(new BigDecimal(e.getTextTrim()));
					break;
				case "VISIT_OPINION": // 巡检意见
					visitFactory.setVisitFactoryOpinion(e.getTextTrim());
					break;
				case "VISITOR": // 巡检负责人
					visitFactory.setVisitFactoryPrincipal(e.getTextTrim());
					break;
				default:
					break;
				}
			}
			list.add(visitFactory);
		} else {
			// 递归遍历当前节点所有的子节点
			List<Element> listElement = node.elements();// 所有一级子节点的list
			for (Element e : listElement) {// 遍历所有一级子节点
				parseNode(e, list);// 递归
			}
		}
	}
}
