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

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyNormalMerchandiseAdjustPriceHbxtService")
public interface ApplyNormalMerchandiseAdjustPriceHbxtService {

    /**
     *
     * @param merchandiseCode 商品编号
     * @param file1  供应商调价申请函
     * @param file2  感官标准
     * @param file3  其它
     * @return
     */
    @WebMethod
    MerchandiseAdjustPriceApplyResponseResult createNormalMerchandiseAdjustPriceApplication(
            @WebParam(name = "merchandiseCode") String merchandiseCode,
            @WebParam(name = "file1") CxfFileWrapper file1,
            @WebParam(name = "file2") CxfFileWrapper file2,
            @WebParam(name = "file3") CxfFileWrapper file3
            );

}
