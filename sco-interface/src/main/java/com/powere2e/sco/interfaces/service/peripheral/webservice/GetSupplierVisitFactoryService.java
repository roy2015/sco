package com.powere2e.sco.interfaces.service.peripheral.webservice;

import com.powere2e.frame.commons.service.Service;

/**
 * 供应商年度巡厂得分Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2016年3月30日
 */
public interface GetSupplierVisitFactoryService extends Service {
	
	/**
	 * 添加供应商年度巡厂得分
	 *				
	 */
	public void insertSupplierVisitFactory();

    /**
     * 添加供应商年度巡厂得分（BPM）
     *
     */
	void insertSupplierVisitFactoryBPM();
}
