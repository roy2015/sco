package com.powere2e.sco.interfaces.service.peripheral.hbxt;


import com.powere2e.sco.model.peripheral.hbxt.CxfFileWrapper;
import com.powere2e.sco.model.peripheral.hbxt.MerchandiseAdjustPriceApplyResponseResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * 商品快速调价webservic接口
 *
 *
 */

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyMerchandiseAdjustPriceHbxtService")
public interface ApplyMerchandiseAdjustPriceHbxtService {

    /**
     *  商品编号
     *
     *
     * @param merchandiseCode
     * @param files
     * @return
     */
    @WebMethod
    MerchandiseAdjustPriceApplyResponseResult createMerchandiseAdjustPriceApplication(
                @WebParam(name = "merchandiseCode")String merchandiseCode,
              @WebParam(name = "files") CxfFileWrapper[] files);

}
