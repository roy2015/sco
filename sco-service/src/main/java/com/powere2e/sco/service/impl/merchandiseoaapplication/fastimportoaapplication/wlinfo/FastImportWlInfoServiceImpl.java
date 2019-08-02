package com.powere2e.sco.service.impl.merchandiseoaapplication.fastimportoaapplication.wlinfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustDao;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.wlinfo.FastImportWlInfoDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.wlinfo.FastImportWlInfoService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;
import com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustServiceImpl;
import com.powere2e.sco.service.impl.merchandiseoaapplication.reportnewoaapplication.wlinfo.WlInfoNewServiceImpl;

/**
 * 快速引入物料信息  业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月19日
 * @version 1.0
 */
public class FastImportWlInfoServiceImpl extends ServiceImpl implements
		FastImportWlInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9927914332127095L;
	private FastImportWlInfoDao fastImportWlInfoDao;
	private WlInfoFastAdjustDao wlInfoFastAdjustDao;

	public static FastImportWlInfoService getInstance() {
		return (FastImportWlInfoService) ConfigFactory.getInstance().getBean(
				"fastImportWlInfoService");
	}

	 
	public FastImportWlInfoDao getFastImportWlInfoDao() {
		return fastImportWlInfoDao;
	}

	public void setFastImportWlInfoDao(FastImportWlInfoDao fastImportWlInfoDao) {
		this.fastImportWlInfoDao = fastImportWlInfoDao;
	}

	@Override
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map) {
		return this.fastImportWlInfoDao.loadWlInfo(map);
	}
	
	public WlInfoFastAdjustDao getWlInfoFastAdjustDao() {
		return wlInfoFastAdjustDao;
	}

	public void setWlInfoFastAdjustDao(WlInfoFastAdjustDao wlInfoFastAdjustDao) {
		this.wlInfoFastAdjustDao = wlInfoFastAdjustDao;
	}

	@Override
	public void insertWlInfoFastImport(MerchandiseWlInfo wlInfoFastAdjust,
			String applicationCode, String intentionAndSupplierCodes) {
		List<MerchandiseWlInfo> list = new ArrayList<MerchandiseWlInfo>();
		list.add(wlInfoFastAdjust);
		WlInfoFastAdjustServiceImpl.getInstance().insertWlInfoFastAdjust(wlInfoFastAdjust, applicationCode, intentionAndSupplierCodes);
		//SAP物料号
		WlInfoNewServiceImpl.getInstance().insertWlInfoNew(list, applicationCode, intentionAndSupplierCodes);
	}

	@Override
	public void completeDeleteWlInfo(String applicationCode, String intSup, String intSupReg,
			String intentionAndSupplierCodes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		boolean ifBatch = applicationCode.contains("'");
		if (ifBatch) {//是否批量
			map.put("ifBatch", ifBatch);
		}
		if (!ifBatch) map.put("intSup", intSup);
		map.put("intSupReg", intSupReg);//非批量删除时
		this.fastImportWlInfoDao.deleteWlInfo(map);
		MerchandiseWlInfo ifHave = null;
		if (!ifBatch) ifHave = this.fastImportWlInfoDao.loadWlInfo(map);
		if (ifHave == null) {
			//当该商品所有物料都被删除时，sap物料号需删除
			this.fastImportWlInfoDao.deleteSAPData(map);
			if (ifBatch) map.put("intSup", intSup);//批量删除时
			this.fastImportWlInfoDao.updateISMData(map);
		}
	}

	@Override
	public void updateWlInfoFastAdjust(MerchandiseWlInfo wlInfo,
			String oldRegion, String applicationCode, String intentionAndSupplierCodes) {
		Map<String, Object> map = wlInfo.toMap();
		List<String> list = this.wlInfoFastAdjustDao.ifWlInfoExists(map);
		
		if (wlInfo.getRegion().equals(oldRegion)) {//如果没有更改地区
			if (list.size() > 1) {
				throw new EscmException("所选商品及进货地区已有物料信息记录，不可重复添加!");
			}
		} else {//如果改变了
			if (list.size() > 0 ) {
				throw new EscmException("所选商品及进货地区已有物料信息记录，不可重复添加!");
			}
		}
		map.put("oldRegion", oldRegion);
		this.wlInfoFastAdjustDao.updateMerchandiseContractPrice(map);
	}
}