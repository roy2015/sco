package com.powere2e.sco.model.peripheral.hbxt;

import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;

/**
 *
 * 商品意向品的webservice接口的返回结果封装
 *
 */
public class MerchandiseIntentionApplyResponseResult {
    private boolean sucess;
    private String message;
    private MerchandiseIntention data;

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

    public MerchandiseIntention getData() {
        return data;
    }

    public void setData(MerchandiseIntention data) {
        this.data = data;
    }
}
