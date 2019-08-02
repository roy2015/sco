package com.powere2e.sco.action.merchandiseintention;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.action.util.HttpClientHelper;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.merchandiseintention.ForetasteFeedbackService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.model.merchandiseintention.ForetasteFeedback;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.ColumnMapRowMapper;

import static com.powere2e.sco.common.utils.Constant.DATA_TIME_DATEFORMATE_CHI;

/**
 * 试吃反馈的Action
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class ForetasteFeedbackAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5163620339872494109L;
    private static Logger LOGGER = Logger.getLogger("cn.vesung");
	private ForetasteFeedbackService foretasteFeedbackService;
    private MerchandiseIntentionService merchandiseIntentionService;
    private static String COMMA = ",";
    private static String SINGLE_QUOTE = "'";

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		foretasteFeedbackService = (ForetasteFeedbackService) ConfigFactory.getInstance().getBean(
				"foretasteFeedbackService");
        merchandiseIntentionService = (MerchandiseIntentionService) ConfigFactory.getInstance().getBean(
                "merchandiseIntentionService");

    }

	/*-------------试吃反馈模块--------------------*/
	/**
	 * 试吃反馈查询
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListForetasteFeedback() throws Exception {
		String intentionCode = this.asString("intentionCode");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionCode", intentionCode);
		List<ForetasteFeedback> foretasteList = foretasteFeedbackService.listForetasteFeedback(map, this.getPageInfo());

		this.forwardData(true, foretasteList, null);
	}

	/**
	 * 显示试吃反馈界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowInsertForetasteForm() throws Exception {
		ForetasteFeedback foretasteFeedback = new ForetasteFeedback();
		String intentionCode = this.asString("intentionCode");// 意向品编号
		String intentionSupplierCode = this.asString("intentionSupplierCode");// 供应商的编号

		foretasteFeedback.setIntentionCode(intentionCode);
		foretasteFeedback.setIntentionSupplierCode(intentionSupplierCode);
		this.putObject("foretasteFeedback", foretasteFeedback);
		this.forwardPage("sco/merchandiseIntention/foretasteFeedbackForm.ftl");
	}

	/**
	 * 录入试吃反馈
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doInsertForetaste() throws Exception {
		ForetasteFeedback foretasteFeedback = getForetasteFeedback();
		// 手动获取试吃反馈时间,否则就没有秒数
		Date foretasteDate = this.asDate("foretasteDate", "yyyy-MM-dd HH:mm:ss");
		foretasteFeedback.setForetasteDate(foretasteDate);

		foretasteFeedbackService.insertForetasteFeedback(foretasteFeedback.toMap());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 显示修改试吃反馈界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowUpdateForetasteForm() throws Exception {
		String feedbackCode = this.asString("feedbackCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("feedbackCode", feedbackCode);

		ForetasteFeedback foretasteFeedback = foretasteFeedbackService.loadForetasteFeedback(map);
		this.putObject("foretasteFeedback", foretasteFeedback);
		this.forwardPage("sco/merchandiseIntention/foretasteFeedbackForm.ftl");
	}

	/**
	 * 修改试吃反馈
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doUpdateForetaste() throws Exception {
        ForetasteFeedback foretasteFeedback = getForetasteFeedback();
        Date foretasteDate = this.asDate("foretasteDate", "yyyy-MM-dd HH:mm:ss");
        foretasteFeedback.setForetasteDate(foretasteDate);
		foretasteFeedbackService.updateForetasteFeedback(foretasteFeedback.toMap());

        String intentionCode = foretasteFeedback.getIntentionCode();
        String intentionSupplierCode = foretasteFeedback.getIntentionSupplierCode();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("intentionCode", intentionCode);
        map.put("intentionSupplierCode", intentionSupplierCode);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setSize(100);
        List<IntentionSupplierMerchandise> list = merchandiseIntentionService.listIntentionSupplierMerchandise(map, pageInfo);
        if (list == null || list.isEmpty()) {
            this.forwardData(false, null, "意向品关联供应商不存在!");
            return;
        }
        IntentionSupplierMerchandise intentionSupplierMerchandise = list.get(0);
        String isForetastePass = intentionSupplierMerchandise.getIsForetastePass();
        //试吃结果接收接口：审批通过、不通过的时候将试吃记录一起给我们（协同）；审批通过、不通过以后发生的修改、删除再调我们（协同）的删除接口
        if ("是".equalsIgnoreCase(isForetastePass) || "否".equalsIgnoreCase(isForetastePass)) {
            InnerHBXTForeTasteRequest innerHBXTForeTasteRequest = new InnerHBXTForeTasteRequest();
            innerHBXTForeTasteRequest.setTastingCode(foretasteFeedback.getFeedbackCode());
            innerHBXTForeTasteRequest.setTastingDate(DateUtils.formatDateToStr(foretasteFeedback.getForetasteDate(),DATA_TIME_DATEFORMATE_CHI));
            innerHBXTForeTasteRequest.setTastingNumber(foretasteFeedback.getForetasteNumber() == null ? "0" : foretasteFeedback.getForetasteNumber().toString());
            BigDecimal foretasteFull = foretasteFeedback.getForetasteFull();
            BigDecimal foretasteGrade = foretasteFeedback.getForetasteGrade();
            foretasteFull.setScale(3, BigDecimal.ROUND_HALF_UP);
            foretasteGrade.setScale(3, BigDecimal.ROUND_HALF_UP);
            innerHBXTForeTasteRequest.setTastingFullScore(foretasteFull.toString());
            innerHBXTForeTasteRequest.setTastingScore(foretasteGrade.toString());
            innerHBXTForeTasteRequest.setTastingResult("是".equalsIgnoreCase(isForetastePass) ? "PASS" : "FAIL");

            try {
                String body = "";
                HttpClient httpclient = HttpClientHelper.getCustomHttpclient();
                String accessToken = HttpClientHelper.getAccessToken();
                String url = String.format(HttpClientHelper.HBXT_TASTE_UPDATE_DELETE_URL, innerHBXTForeTasteRequest.getTastingCode(), accessToken);
                HttpPatch httpPatch = new HttpPatch(url);
                HttpEntity re = new StringEntity(JSON.toJSONString(innerHBXTForeTasteRequest), ContentType.APPLICATION_JSON);
                httpPatch.setEntity(re);
                HttpResponse response = httpclient.execute(httpPatch);
                //获取结果实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(entity, "utf-8");
                }
                EntityUtils.consume(entity);
                JSONObject jsonObject = JSON.parseObject(body);
                Integer status = (Integer)jsonObject.get("status");
                String message = (String)jsonObject.get("message");
                if (HttpStatus.SC_OK != status.intValue()) {
                    LOGGER.info(String.format("调用协同接口报错, %s", message));
                } else {
                    LOGGER.info(String.format("调用协同接口成功, %s", message));
                }
            } catch (KeyManagementException e) {
                logger.error(e.getMessage(), e);
            } catch (NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            } catch (ClientProtocolException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {}

        this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除试吃反馈
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doDeleteForetaste() throws Exception {
		String feedbackCodes = this.asString("feedbackCodes");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("feedbackCodes", feedbackCodes);
		foretasteFeedbackService.deleteForetasteFeedback(map);
        //处理推送协同的逻辑
        String[] feebackCodeWihtQuotes = feedbackCodes.split(COMMA);
        for (String feebackCodeWihtQuote : feebackCodeWihtQuotes) {
            String feebackCode = feebackCodeWihtQuote.replaceAll(SINGLE_QUOTE, "");
            resolveDeleteOnePushHBXT(feebackCode);
        }

        this.forwardData(true, null, this.getText("public.success"));
	}

    /**
     * //处理协同的逻辑
     * @param feedbackCode
     */
	private void resolveDeleteOnePushHBXT (String feedbackCode) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();

        map.clear();
        map.put("feedbackCode", feedbackCode);
        ForetasteFeedback foretasteFeedback = foretasteFeedbackService.loadForetasteFeedback(map);
        if (foretasteFeedback == null) {
            this.forwardData(false, null, "不存在试吃记录!");
            return;
        }
        String intentionSupplierCode = foretasteFeedback.getIntentionSupplierCode();
        String intentionCode = foretasteFeedback.getIntentionCode();
        map.clear();
        map.put("intentionCode", intentionCode);
        map.put("intentionSupplierCode", intentionSupplierCode);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setSize(100);
        List<IntentionSupplierMerchandise> list = merchandiseIntentionService.listIntentionSupplierMerchandise(map, pageInfo);
        if (list == null || list.isEmpty()) {
            this.forwardData(false, null, "意向品关联供应商不存在!");
            return;
        }
        IntentionSupplierMerchandise intentionSupplierMerchandise = list.get(0);
        String isForetastePass = intentionSupplierMerchandise.getIsForetastePass();
        //试吃结果接收接口：审批通过、不通过的时候将试吃记录一起给我们（协同）；审批通过、不通过以后发生的修改、删除再调我们（协同）的删除接口
        if ("是".equalsIgnoreCase(isForetastePass) || "否".equalsIgnoreCase(isForetastePass)) {
            try {
                String body = "";
                HttpClient httpclient = HttpClientHelper.getCustomHttpclient();
                String accessToken = HttpClientHelper.getAccessToken();
                String url = String.format(HttpClientHelper.HBXT_TASTE_UPDATE_DELETE_URL, feedbackCode, accessToken);
                HttpDelete httpDelete = new HttpDelete(url);
                HttpResponse response = httpclient.execute(httpDelete);
                //获取结果实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(entity, "utf-8");
                }
                EntityUtils.consume(entity);
                JSONObject jsonObject = JSON.parseObject(body);
                Integer status = (Integer)jsonObject.get("status");
                String message = (String)jsonObject.get("message");
                if (HttpStatus.SC_OK != status.intValue()) {
                    LOGGER.info(String.format("调用协同接口报错, %s", message));
                } else {
                    LOGGER.info(String.format("调用协同接口成功, %s", message));
                }
            } catch (KeyManagementException e) {
                logger.error(e.getMessage(), e);
            } catch (NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            } catch (ClientProtocolException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        } else {}
    }


	/**
	 * 获得页面传递的试吃反馈相关的值
	 */
	private ForetasteFeedback getForetasteFeedback() throws Exception {
		ForetasteFeedback foretasteFeedback = new ForetasteFeedback();
		this.asBean(foretasteFeedback);
		return foretasteFeedback;
	}

	/**
	 * 修改试吃通过
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doUpdateForetasteIsPass() throws Exception {
		String intentionAndSupplierCodes = this.asString("intentionAndSupplierCodes");// 意向品供应商编号
		String isForetastePass = this.asString("isForetastePass");
        List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
                .getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
        if ("N".equals(isForetastePass)) {
            // 取消试吃通过,需要判断取消的供应商是否已经做了OA新品引进或者OA新品快速引进
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("codeList", list);
            List<ApplicationMerchandise> applicationList = foretasteFeedbackService.queryIsOaApplicationNew(map);
			if (applicationList != null && !applicationList.isEmpty()) {
				this.forwardData(false, null, "所选记录存在新品引进或快速引进OA申请单，不可取消试吃通过!");
				return;
			}
		}
		foretasteFeedbackService.updateForetasteFeedbackIsPass(intentionAndSupplierCodes, isForetastePass);

        String intentionCode = list.get(0).getMerchandiseCode();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("intentionCode", intentionCode);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPage(1);
        pageInfo.setSize(100);
        List<ForetasteFeedback> foretasteList = foretasteFeedbackService.listForetasteFeedback(map, pageInfo);
        if (foretasteList != null && !foretasteList.isEmpty()) {
            HBXTForeTasteRequest hbxtForeTasteRequest = new HBXTForeTasteRequest();
            InnerHBXTForeTasteRequest[] innerHBXTForeTasteRequests = new InnerHBXTForeTasteRequest[foretasteList.size()];
            hbxtForeTasteRequest.setProductTastings(innerHBXTForeTasteRequests);
            for (int i = 0; i < foretasteList.size(); i++) {
                ForetasteFeedback foretasteFeedback = foretasteList.get(i);
                InnerHBXTForeTasteRequest innerHBXTForeTasteRequest = new InnerHBXTForeTasteRequest();
                innerHBXTForeTasteRequest.setTastingCode(foretasteFeedback.getFeedbackCode());
                innerHBXTForeTasteRequest.setTastingDate(DateUtils.formatDateToStr(foretasteFeedback.getForetasteDate(),DATA_TIME_DATEFORMATE_CHI));
                innerHBXTForeTasteRequest.setTastingNumber(foretasteFeedback.getForetasteNumber() == null ? "0" : foretasteFeedback.getForetasteNumber().toString());
                BigDecimal foretasteFull = foretasteFeedback.getForetasteFull();
                BigDecimal foretasteGrade = foretasteFeedback.getForetasteGrade();
                foretasteFull.setScale(3, BigDecimal.ROUND_HALF_UP);
                foretasteGrade.setScale(3, BigDecimal.ROUND_HALF_UP);
                innerHBXTForeTasteRequest.setTastingFullScore(foretasteFull.toString());
                innerHBXTForeTasteRequest.setTastingScore(foretasteGrade.toString());
                innerHBXTForeTasteRequest.setTastingResult("N".equals(isForetastePass) ? "FAIL" : "PASS");
                innerHBXTForeTasteRequests[i] = innerHBXTForeTasteRequest;
            }

            try {
                String body = "";
                HttpClient httpclient = HttpClientHelper.getCustomHttpclient();
                String accessToken = HttpClientHelper.getAccessToken();
                String url = String.format(HttpClientHelper.HBXT_TASTE_CREATE_URL, accessToken, intentionCode);
                HttpPost httpPost = new HttpPost(url);
                HttpEntity re = new StringEntity(JSON.toJSONString(hbxtForeTasteRequest),ContentType.APPLICATION_JSON);
                httpPost.setEntity(re);
                HttpResponse response = httpclient.execute(httpPost);
                //获取结果实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    //按指定编码转换结果实体为String类型
                    body = EntityUtils.toString(entity, "utf-8");
                }
                EntityUtils.consume(entity);
                JSONObject jsonObject = JSON.parseObject(body);
                Integer status = (Integer)jsonObject.get("status");
                String message = (String)jsonObject.get("message");
                if (HttpStatus.SC_OK != status.intValue()) {
                    LOGGER.info(String.format("调用协同接口报错, %s", message));
                } else {
                    LOGGER.info(String.format("调用协同接口成功, %s", message));
                }
            } catch (KeyManagementException e) {
                logger.error(e.getMessage(), e);
            } catch (NoSuchAlgorithmException e) {
                logger.error(e.getMessage(), e);
            } catch (ClientProtocolException e) {
                logger.error(e.getMessage(), e);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }

		this.forwardData(true, null, this.getText("public.success"));
	}

	public static class HBXTForeTasteRequest {
	    private InnerHBXTForeTasteRequest[] productTastings;

        public InnerHBXTForeTasteRequest[] getProductTastings() {
            return productTastings;
        }

        public void setProductTastings(InnerHBXTForeTasteRequest[] productTastings) {
            this.productTastings = productTastings;
        }
    }

	public static class InnerHBXTForeTasteRequest {
	    private String tastingCode;//试吃唯一标识
        private String tastingDate;//试吃日期
        private String tastingNumber;//试吃人数
        private String tastingFullScore;//试吃满分
        private String tastingScore;//试吃评价
        private String tastingResult;//试吃结果 PASS, FAIL

        public String getTastingCode() {
            return tastingCode;
        }

        public void setTastingCode(String tastingCode) {
            this.tastingCode = tastingCode;
        }

        public String getTastingDate() {
            return tastingDate;
        }

        public void setTastingDate(String tastingDate) {
            this.tastingDate = tastingDate;
        }

        public String getTastingNumber() {
            return tastingNumber;
        }

        public void setTastingNumber(String tastingNumber) {
            this.tastingNumber = tastingNumber;
        }

        public String getTastingFullScore() {
            return tastingFullScore;
        }

        public void setTastingFullScore(String tastingFullScore) {
            this.tastingFullScore = tastingFullScore;
        }

        public String getTastingScore() {
            return tastingScore;
        }

        public void setTastingScore(String tastingScore) {
            this.tastingScore = tastingScore;
        }

        public String getTastingResult() {
            return tastingResult;
        }

        public void setTastingResult(String tastingResult) {
            this.tastingResult = tastingResult;
        }
    }
}
