package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;



/**
 * 商品明细类service接口
 * @author Joyce.li
 *  @since 2015年8月18日 上午10:46:46
 *  @version 1.0
 */
public interface MerchandiseDetailTypeService extends Service {
	/**
	 * 商品明细类
	 */
	public void insertMerchandiseDetailType();

	/**
	 * 商品明细类
	 * @param file
	 */
	public void completeInsertMerchandiseDetailType(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除商品明细类
	 */
	public void deleteMerchandiseDetailType();

}
