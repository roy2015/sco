package com.powere2e.sco.model.peripheral.hbxt;

import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;

/**
 *
 * 商品快速调价的webservice接口的返回结果封装
 *
 */
public class MerchandiseAdjustPriceApplyResponseResult {
    private boolean sucess;
    private String message;
    private AdjustPriceApplyReturnVO data;

    public boolean getSucess() {
        return sucess;
    }

    public void setSucess(boolean sucess) {
        this.sucess = sucess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdjustPriceApplyReturnVO getData() {
        return data;
    }

    public void setData(AdjustPriceApplyReturnVO data) {
        this.data = data;
    }


}
