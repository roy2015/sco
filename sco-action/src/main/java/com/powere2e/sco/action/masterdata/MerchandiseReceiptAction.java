package com.powere2e.sco.action.masterdata;

import com.powere2e.frame.web.action.WorkAction;

/**
 * 商品收货单信息 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public class MerchandiseReceiptAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -29775817533964592L;
//	private MerchandiseReceiptService merchandiseReceiptService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
//		merchandiseReceiptService = (MerchandiseReceiptService) ConfigFactory
//				.getInstance().getBean("merchandiseReceiptManager");
	}

}
