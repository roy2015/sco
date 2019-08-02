package com.powere2e.sco.model.peripheral.hbxt;

public class AdjustPriceApplyReturnVO implements java.io.Serializable {
    private static final long serialVersionUID = -8611686216852144542L;
    private String merchandiseCode;//商品编号
    private String applicationCode;//oA申请单号

    public AdjustPriceApplyReturnVO() {
    }

    public AdjustPriceApplyReturnVO(String merchandiseCode, String applicationCode) {
        this.merchandiseCode = merchandiseCode;
        this.applicationCode = applicationCode;
    }
}
