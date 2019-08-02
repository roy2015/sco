package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品供应商service接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:46:46
 * @version 1.0
 */
public interface MerchandiseSupplierService extends Service {
	/**
	 * 商品供应商service接口
	 */
	public void insertMerchandiseSupplier();

	/**
	 * 商品供应商service接口
	 * 
	 * @param file
	 */
	public void completeInsertMerchandiseSupplier(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除商品供应商
	 */
	public void deleteMerchandiseSupplier();

}
