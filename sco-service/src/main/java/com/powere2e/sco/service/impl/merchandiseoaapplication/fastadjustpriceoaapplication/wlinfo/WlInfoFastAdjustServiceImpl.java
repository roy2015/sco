package com.powere2e.sco.service.impl.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo.WlInfoFastAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;
import com.powere2e.sco.service.impl.merchandiseoaapplication.MerchandiseOaApplicationServiceImpl;

/**
 * 快速调价物料信息 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月21日
 */
public class WlInfoFastAdjustServiceImpl extends ServiceImpl implements
		WlInfoFastAdjustService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7466731150478236790L;
	private WlInfoFastAdjustDao wlInfoFastAdjustDao;
	
	public static WlInfoFastAdjustService getInstance() {
		return (WlInfoFastAdjustService) ConfigFactory.getInstance()
				.getBean("wlInfoFastAdjustService");
	}
	
	public WlInfoFastAdjustDao getWlInfoFastAdjustDao() {
		return wlInfoFastAdjustDao;
	}

	public void setWlInfoFastAdjustDao(WlInfoFastAdjustDao wlInfoFastAdjustDao) {
		this.wlInfoFastAdjustDao = wlInfoFastAdjustDao;
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoFastAdjust(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.wlInfoFastAdjustDao.listWlInfoFastAdjust(map, pageInfo);
	}

	@Override
	public List<MerchandiseWlInfo> listWlInfoDetail(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.wlInfoFastAdjustDao.listWlInfoDetail(map, pageInfo);
	}
	
	@Override
	public Boolean ifWlInfoExists(Map<String, Object> map) {
		List<String> list = this.wlInfoFastAdjustDao.ifWlInfoExists(map);
		return list.size() > 0;
	}

	@Override
	public void insertWlInfoFastAdjust(MerchandiseWlInfo wlInfo,
			String applicationCode, String intentionAndSupplierCodes) {
		Map<String, Object> map = wlInfo.toMap();
		if (this.ifWlInfoExists(map)) {
			throw new EscmException("所选商品及进货地区已有物料信息记录，不可重复添加!");
		}//校验重复
//		this.validateOaStatus(applicationCode, intentionAndSupplierCodes);
		this.wlInfoFastAdjustDao.insertMerchandiseContractPrice(map);
	}
	
	@Override
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map) {
		return this.wlInfoFastAdjustDao.loadWlInfo(map);
	}
	
	@Override
	public void updateWlInfoFastAdjust(MerchandiseWlInfo wlInfo,
			String oldRegion, String applicationCode, String intentionAndSupplierCodes) {
		Map<String, Object> map = wlInfo.toMap();
		List<String> list = this.wlInfoFastAdjustDao.ifWlInfoExists(map);
		
//		this.validateOaStatus(applicationCode, intentionAndSupplierCodes);
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

	@Override
	public void deleteWlInfoFastAdjust(MerchandiseWlInfo[] dataArray,
			String applicationCode, String intentionAndSupplierCodes) {
//		this.validateOaStatus(applicationCode, intentionAndSupplierCodes);
		for (MerchandiseWlInfo wl : dataArray) {
			wl.setApplicationCode(applicationCode);
			this.wlInfoFastAdjustDao.deleteMerchandiseContractPrice(wl.toMap());
		}
	}

	/**
	 * 根据申请单的状态来显示不同的消息[Action已做校验]
	 * 
	 * @param map :
	 * 			<li>applicationCode 新品引进OA申请单号</li>
	 * 			<li>intentionAndSupplierCodes 所选择的意向品编号和供应商编号组</li>
	 */
	@SuppressWarnings("unused")
	private void validateOaStatus(String applicationCode, String intentionAndSupplierCodes) {
//		String oaStatus = 
			try {
				MerchandiseOaApplicationServiceImpl.getInstance()
					.getIntentionOaApplicationReceiptInfo(applicationCode,
							intentionAndSupplierCodes, BusinessConstants.ApplicationType.MERCHANDISE_FASTADJUSTPRICE.toString());
			} catch (Exception e) {
				e.printStackTrace();
				throw new EscmException("校验申请单[" + applicationCode + "]状态时出错！");
			}
//		MerchandiseOaApplicationUtil.responseMessage(oaStatus);
	}
}