package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品中分类service接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:46:46
 * @version 1.0
 */
public interface MerchandiseCentreTypeService extends Service {
	/**
	 * 商品中分类service接口
	 */
	public void insertMerchandiseCentreType();
	/**
	 *  商品中分类service接口
	 * @param file
	 * 			解析的文件
	 * @param charsetCode
	 * 			解析文件的编码格式
	 * @param processCount
	 * 			每读取processCount行插入一次
	 * @param fileRootPath
	 * 			保存解析文件的路径(不包括BW/SAP模块名)
	 */
	public void completeInsertMerchandiseCentreType(File file, String charsetCode, int processCount,String fileRootPath);

	/**
	 * 删除商品中分类
	 */
	public void deleteMerchandiseCentreType();

}
