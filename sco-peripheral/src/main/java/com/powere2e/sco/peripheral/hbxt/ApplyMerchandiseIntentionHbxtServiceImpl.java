package com.powere2e.sco.peripheral.hbxt;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyMerchandiseIntentionHbxtService;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;
import com.powere2e.sco.model.peripheral.hbxt.MerchandiseIntentionApplyResponseResult;
import com.powere2e.security.model.Option;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.jws.WebService;
import java.util.*;


public class ApplyMerchandiseIntentionHbxtServiceImpl implements ApplyMerchandiseIntentionHbxtService {

    public static Logger logger = Logger.getLogger("cn.vesung");
    private static final String SYXT = "SYXT";
    private static final String SYGXT ="SYGXT";
    private static String SUCCESS = "sucess";
    private static String FAILED = "failed";

    private MerchandiseIntentionService merchandiseIntentionService;
    private MasterDataTypeService masterDataTypeService;


    /**
     * @param purchaseDepartments   采购部门    Y        国内：INLAND 国际：IMPORT
     * @param intentionName         意向品名称       Y
     * @param rationed              是否定量装       Y       公斤装:  GJZ 定量装:  DLZ
     * @param purchaseType          采购类型              采购部门为国际时此字段才有可选值 直采：ZC 非直采: FZC
     * @param saleType              销售方式         Y       默认填” 格斗”，其他枚举值见备注1
     * @param orderType             订购数量
     * @param centreTypeCode        中分类        Y
     * @param smallTypeCode         小分类        Y
     * @param elseTypeName          自定义小分类名称     当小分类选”其他：ELSE” 时此项必填，明细类，细分类均填空
     * @param detailTypeCode        明细类        Y          见备注4
     * @param fineTypeCode          细分类                 见备注5
     * @param quotedCurrency        报价币种      Y          人民币
     * @param paymentType           付款方式
     * @param deliveryType          交货方式
     * @param specification         意向品规格
     * @param packingType           包装要求
     * @param intentionSupplierName 供应商名称
     * @return
     *
     * 意向品申请
     *
     */
    @Override
    public MerchandiseIntentionApplyResponseResult applyMerchandiseIntentionHbxt(
                                                String purchaseDepartments,
                                                String intentionName,
                                                String rationed,
                                                String purchaseType,
                                                String saleType,
                                                String orderType,
                                                String centreTypeCode,
                                                String smallTypeCode,
                                                String elseTypeName,
                                                String detailTypeCode,
                                                String fineTypeCode,
                                                String quotedCurrency,
                                                String paymentType,
                                                String deliveryType,
                                                String specification,
                                                String packingType,
                                                String intentionSupplierName) {
        MerchandiseIntentionApplyResponseResult result =  new MerchandiseIntentionApplyResponseResult();
        try {

            merchandiseIntentionService = (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionService");
            masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");

            MerchandiseIntention merchandiseIntention = null;
            merchandiseIntention = new MerchandiseIntention();
            merchandiseIntention.setPurchaseDepartments(purchaseDepartments);
            merchandiseIntention.setIntentionName(intentionName);
            merchandiseIntention.setRationed(rationed);
            merchandiseIntention.setPurchaseType(purchaseType);
            merchandiseIntention.setSaleType(saleType);
            merchandiseIntention.setOrderType(orderType);
            merchandiseIntention.setCentreTypeCode(centreTypeCode);
            merchandiseIntention.setSmallTypeCode(smallTypeCode);
            merchandiseIntention.setElseTypeName(elseTypeName);
            merchandiseIntention.setDetailTypeCode(detailTypeCode);
            merchandiseIntention.setFineTypeCode(fineTypeCode);
            merchandiseIntention.setQuotedCurrency(quotedCurrency);
            merchandiseIntention.setPaymentType(paymentType);
            merchandiseIntention.setDeliveryType(deliveryType);
            merchandiseIntention.setSpecification(specification);
            merchandiseIntention.setPackingType(packingType);
            merchandiseIntention.setIntentionSupplierName(intentionSupplierName);

            logger.info(String.format("applyMerchandiseIntentionHbxt接口入参：purchaseDepartments[%s], intentionName[%s], rationed[%s]," +
                    "purchaseType[%s], saleType[%s], orderType[%s], centreTypeCode[%s], smallTypeCode[%s], elseTypeName[%s], detailTypeCode[%s]," +
                    "fineTypeCode[%s], quotedCurrency[%s], paymentType[%s], deliveryType[%s], specification[%s], packingType[%s], intentionSupplierName[%s],",
                    purchaseDepartments, intentionName, rationed, purchaseType, saleType, orderType, centreTypeCode, smallTypeCode,
                    elseTypeName, detailTypeCode, fineTypeCode, quotedCurrency, paymentType,deliveryType,specification,packingType,intentionSupplierName
                    ));

            ValidateVo validateVo = emptyCheck(merchandiseIntention);
            if (validateVo.getResult() == false) {
                result.setSucess(false);
                result.setMessage(String.format("%s", validateVo.getMessage()));
                logger.error(result.getMessage());
                return result;
            } else {
                ValidateVo selectCheck = selectCheck(merchandiseIntention);
                if (selectCheck.getResult() == false) {
                    result.setSucess(false);
                    result.setMessage(String.format("%s", selectCheck.getMessage()));
                    logger.error(result.getMessage());
                    return result;
                }
            }

            //生成主键
            String intentionCode = SYXT + masterDataTypeService.nextID("S_MERCHANDISE_INTENTION");
            merchandiseIntention.setIntentionCode(intentionCode);
            MerchandiseIntention merchandiseIntentionDb = merchandiseIntentionService.loadMerchandiseIntention(intentionCode);
            if (merchandiseIntentionDb != null) {
                merchandiseIntention.setUpdated(new Date());
                merchandiseIntentionService.updateMerchandiseIntention(merchandiseIntention);
            } else {
                merchandiseIntentionService.insertMerchandiseIntention(merchandiseIntention);
            }
            //处理意向供应商
            if (StringUtils.isNotBlank(intentionSupplierName)) {
                String intentionSupplierCode;
                MerchandiseIntentionSupplier intentionSupplier = merchandiseIntentionService.findSupplierNameIsExists(intentionSupplierName);
                if (intentionSupplier == null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    intentionSupplierCode = SYGXT.concat(masterDataTypeService.nextID("S_MERCHANDISE_INTENTION_SUPPLI"));
                    map.put("intentionSupplierCode", intentionSupplierCode);
                    map.put("intentionSupplierName", intentionSupplierName);
                    merchandiseIntentionService.insertIntentionSupplier(map);
                } else {
                    intentionSupplierCode = intentionSupplier.getIntentionSupplierCode();
                }

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("intentionSupplierCode", intentionSupplierCode);
                map.put("intentionCode", intentionCode);
                //判断供应商是否已经被关联
                IntentionSupplierMerchandise supplierMerchandise =merchandiseIntentionService.findSupplierMerchandiseIsExists(map);
                //已关联的不处理，未关联的关联
                if (supplierMerchandise == null) {
                    merchandiseIntentionService.insertIntentionSupplierMerchandise(map);
                }
                merchandiseIntention.setIntentionSupplierCode(intentionSupplierCode);
            }

            MerchandiseIntentionApplyResponseResult responseResult = new MerchandiseIntentionApplyResponseResult();
            responseResult.setSucess(true);
            responseResult.setData(merchandiseIntention);
            return responseResult;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result.setSucess(false);
            result.setMessage(String.format("%s, %s", FAILED, e.getMessage()));
            return result;
        }
    }

    /**
     * 下拉框选值的校验
     * @param merchandiseIntention
     * @return
     */
    private ValidateVo selectCheck(MerchandiseIntention merchandiseIntention) {
        ArrayList<Option> procurementDepartments = BusinessUtils.PROCUREMENT_DEPARTMENTS;//采购部门
        ArrayList<Option> rationeds = BusinessUtils.RATIONED;//是否定量装
        ArrayList<Option> purchaseTypes = BusinessUtils.PURCHASE_TYPE;

        if (StringUtils.isNotBlank(merchandiseIntention.getPurchaseDepartments()) &&
                ! isContains(procurementDepartments, merchandiseIntention.getPurchaseDepartments())) {
            return new ValidateVo(false, "【采购部门purchaseDepartments】不正确，国内：INLAND 国际：IMPORT");
        }
        if (StringUtils.isNotBlank(merchandiseIntention.getRationed()) &&
            ! isContains(rationeds, merchandiseIntention.getRationed())
        ) {
            return new ValidateVo(false, "【是否定量装rationed】不正确，公斤装:  GJZ 定量装:  DLZ");
        }
        if (StringUtils.isNotBlank(merchandiseIntention.getPurchaseDepartments())) {
            String purchaseDepartments = merchandiseIntention.getPurchaseDepartments();
            if (BusinessConstants.ProcurementDepartments.INLAND.name().equalsIgnoreCase(purchaseDepartments)) {
                merchandiseIntention.setPurchaseType(null);
            } else if (BusinessConstants.ProcurementDepartments.IMPORT.name().equalsIgnoreCase(purchaseDepartments)) {
                if ( StringUtils.isNotBlank(merchandiseIntention.getPurchaseType()) && ! isContains(purchaseTypes, merchandiseIntention.getPurchaseType())) {
                    return new ValidateVo(false, "【采购类型purchaseType】不正确，采购部门purchaseDepartments为国际IMPORT时此字段才有可选值，直采：ZC 非直采: FZC");
                }
            }
        }

        List<Option> saleTypes = masterDataTypeService.listSaleType();
        if (StringUtils.isNotBlank(merchandiseIntention.getSaleType()) && ! isContains(saleTypes, merchandiseIntention.getSaleType()) ) {
            return new ValidateVo(false, "【销售方式saleType】不正确，取数表merchandise distinct");
        }

        String centreTypeCode = merchandiseIntention.getCentreTypeCode();
        String smallTypeCode = merchandiseIntention.getSmallTypeCode();
        String detailTypeCode = merchandiseIntention.getDetailTypeCode();
        String fineTypeCode = merchandiseIntention.getFineTypeCode();

        List<Option> centerTypes = masterDataTypeService.listCenterType(new HashMap<String, Object>());
        if (StringUtils.isNotBlank(centreTypeCode) && ! isContains(centerTypes, centreTypeCode)) {
            return new ValidateVo(false, "【中分类centreTypeCode】不正确");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("centreTypeCode", centreTypeCode);
        List<Option> smallTypes = masterDataTypeService.listSmallType(map);
        smallTypes.add(new Option("ELSE", "其他"));
        if (StringUtils.isNotBlank(smallTypeCode) && ! isContains(smallTypes, smallTypeCode) ) {
            return new ValidateVo(false, "【小分类smallTypeCode】不正确");
        }

        if ("ELSE".equalsIgnoreCase(smallTypeCode)) {
            //当小分类选”其他”时自定义小分类名称必填，明细类，细分类均填空
            merchandiseIntention.setDetailTypeCode(null);
            merchandiseIntention.setFineTypeCode(null);
        } else {
            map.clear();
            map.put("smallTypeCode", smallTypeCode);
            List<Option> detailTypes = masterDataTypeService.listDetailType(map);
            if (StringUtils.isNotBlank(detailTypeCode ) && ! isContains(detailTypes, detailTypeCode)) {
                return new ValidateVo(false, "【明细类detailTypeCode】不正确");
            }

            map.clear();
            map.put("detailTypeCode", detailTypeCode);
            List<Option> fineTypes = masterDataTypeService.listFineType(map);
            if (StringUtils.isNotBlank(fineTypeCode) && ! isContains(fineTypes, fineTypeCode)) {
                return new ValidateVo(false, "【细分类fineTypeCode】不正确");
            }
        }
        return new ValidateVo();
    }

    /**
     * 判断传值是否在下拉框选值范围内
     * @param optionList
     * @param id
     * @return
     */
    private boolean isContains(List<Option> optionList, String id) {
        if (null == optionList || optionList.isEmpty()) {
            return false;
        }
        for (Option option : optionList) {
            if (option != null && id.equalsIgnoreCase(option.getId())) {
                return true;
            }
        }
        return false;
    }


    private ValidateVo emptyCheck(MerchandiseIntention merchandiseIntention) {
        if (StringUtils.isBlank(merchandiseIntention.getIntentionName())) {
            return new ValidateVo(false, "商品意向品名称intentionName不能为空");
        }
        if (StringUtils.isBlank(merchandiseIntention.getPurchaseDepartments())) {
            return new ValidateVo(false, "采购部门purchaseDepartments不能为空");
        }
        if (StringUtils.isBlank(merchandiseIntention.getRationed())) {
            return new ValidateVo(false, "是否定量装rationed不能为空");
        }
        if (StringUtils.isBlank(merchandiseIntention.getSaleType())) {
            return new ValidateVo(false, "销售方式saleType不能为空");
        }
        if (StringUtils.isBlank(merchandiseIntention.getCentreTypeCode())) {
            return new ValidateVo(false, "中分类centreTypeCode不能为空");
        }
        if (StringUtils.isBlank(merchandiseIntention.getSmallTypeCode())) {
            return new ValidateVo(false, "小分类smallTypeCode不能为空");
        }

        if ("ELSE".equalsIgnoreCase(merchandiseIntention.getSmallTypeCode())
                ) {

            if (StringUtils.isBlank(merchandiseIntention.getElseTypeName())) {
                return new ValidateVo(false, "当小分类为'其他'时自定义小分类名称elseTypeName不能为空");
            }
        } else {//其它值时detailTypeCode非空
            if (StringUtils.isBlank(merchandiseIntention.getDetailTypeCode())) {
                return new ValidateVo(false, "小分类非'其他'时明细类detailTypeCode不能为空");
            }
        }

        if (StringUtils.isBlank(merchandiseIntention.getQuotedCurrency())) {
            return new ValidateVo(false, "报价币种quotedCurrency不能为空");
        }
        return new ValidateVo();
    }


    private class ValidateVo {
        private boolean result;
        private String message;

        public ValidateVo() {
            result = true;
        }

        public ValidateVo(boolean result, String message) {
            this.result = result;
            this.message = message;
        }

        public boolean getResult() {
            return result;
        }

        public String getMessage() {
            return message;
        }
    }
}
