package com.powere2e.sco.interfaces.service.peripheral.hbxt;

import com.powere2e.sco.model.peripheral.hbxt.MerchandiseIntentionApplyResponseResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;



/**
 *
 * 商品意向品申请service
 *
 *
 */
@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyMerchandiseIntentionHbxtService")
public interface ApplyMerchandiseIntentionHbxtService {

    /**
     *
     * @param purchaseDepartments 采购部门    Y        国内：INLAND 国际：IMPORT
     * @param intentionName  意向品名称       Y
     * @param rationed       是否定量装       Y       公斤装:  GJZ 定量装:  DLZ
     * @param purchaseType   采购类型              采购部门为国际时此字段才有可选值 直采：ZC 非直采: FZC
     * @param saleType       销售方式         Y       默认填” 格斗”，其他枚举值见备注1
     * @param orderType      订购数量
     * @param centreTypeCode   中分类        Y
     * @param smallTypeCode    小分类        Y
     * @param elseTypeName     自定义小分类名称     当小分类选”其他：ELSE” 时此项必填，明细类，细分类均填空
     * @param detailTypeCode   明细类        Y          见备注4
     * @param fineTypeCode     细分类                 见备注5
     * @param quotedCurrency   报价币种      Y          人民币
     * @param paymentType      付款方式
     * @param deliveryType     交货方式
     * @param specification    意向品规格
     * @param packingType      包装要求
     * @param intentionSupplierName  供应商名称
     * @return
     */
    @WebMethod
    MerchandiseIntentionApplyResponseResult applyMerchandiseIntentionHbxt(
            @WebParam(name = "purchaseDepartments")String purchaseDepartments   ,
            @WebParam(name = "intentionName")String intentionName         ,
            @WebParam(name = "rationed")String rationed              ,
            @WebParam(name = "purchaseType")String purchaseType          ,
            @WebParam(name = "saleType")String saleType              ,
            @WebParam(name = "orderType")String orderType             ,
            @WebParam(name = "centreTypeCode")String centreTypeCode        ,
            @WebParam(name = "smallTypeCode")String smallTypeCode         ,
            @WebParam(name = "elseTypeName")String elseTypeName          ,
            @WebParam(name = "detailTypeCode")String detailTypeCode        ,
            @WebParam(name = "fineTypeCode")String fineTypeCode          ,
            @WebParam(name = "quotedCurrency")String quotedCurrency        ,
            @WebParam(name = "paymentType")String paymentType           ,
            @WebParam(name = "deliveryType")String deliveryType          ,
            @WebParam(name = "specification")String specification         ,
            @WebParam(name = "packingType")String packingType           ,
            @WebParam(name = "intentionSupplierName")String intentionSupplierName

    );

}

