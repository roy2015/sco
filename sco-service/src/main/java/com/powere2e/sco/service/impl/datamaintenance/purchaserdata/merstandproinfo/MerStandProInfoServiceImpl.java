package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.merstandproinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.ApplicationScheduleM2;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;

/**
 * 商品引进标准进度信息维护 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public class MerStandProInfoServiceImpl implements MerStandProInfoService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2519008986524197852L;
	private MerStandProInfoDao merStandProInfoDao;

	/**
	 * 获取商品引进标准进度信息实例
	 * 
	 * @return 商品引进标准进度信息实例
	 */
	public static MerStandProInfoService getInstance() {
		return (MerStandProInfoService) ConfigFactory
				.getInstance().getBean("merStandProInfoService");
	}
	
	public MerStandProInfoDao getMerStandProInfoDao() {
		return merStandProInfoDao;
	}

	public void setMerStandProInfoDao(MerStandProInfoDao merStandProInfoDao) {
		this.merStandProInfoDao = merStandProInfoDao;
	}

	/**
	 * 查询标准进度信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 标准进度信息list
	 */
	@Override
	public List<MerStandProInfo> listMerMerStandProInfo(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.merStandProInfoDao.listMerMerStandProInfo(map, pageInfo);
	}

	/**
	 * 新增标准进度信息
	 */
	@Override
	public void insertMerStandProInfo(MerStandProInfo merStandProInfo) {
		if (this.ifMerStandProInfoExists(merStandProInfo)) {
			throw new EscmException("该生效日期已存在，不可重复使用!");
		} else {
			if (DateUtils.ifBefore(merStandProInfo.getStartDate(), DateUtils
					.getCalendar().getTime(), false)) {
				throw new EscmException("生效日期不可早于或等于当前日期!");
			}
		}
		merStandProInfo.setConfigCode(MasterDataTypeServiceImpl.getInstance()
				.nextID("s_merchandise_introduce_plan"));
		this.merStandProInfoDao.insertMerStandProInfo(merStandProInfo.toMap());
	}

	/**
	 * 修改标准进度信息
	 * 
	 * @param merStandProInfo
	 *            标准信息实例对象
	 */
	@Override
	public void updateMerStandProInfo(MerStandProInfo merStandProInfo) {
		this.merStandProInfoDao.updateMerStandProInfo(merStandProInfo.toMap());
	}

	/**
	 * 删除标准进度信息
	 * 
	 * @param merStandProInfo
	 *            相关参数
	 */
	@Override
	public void deleteMerStandProInfo(MerStandProInfo merStandProInfo) {
		this.merStandProInfoDao.deleteMerStandProInfo(merStandProInfo.toMap());
	}

	/**
	 * 是否存在
	 * 
	 * @param merStandProInfo
	 *            相关参数
	 * @return 返回存在数据的信息
	 */
	@Override
	public Boolean ifMerStandProInfoExists(MerStandProInfo merStandProInfo) {
		String merStandPro = this.merStandProInfoDao
				.ifMerStandProInfoExists(merStandProInfo.toMap());
		return StringUtils.isNotBlank(merStandPro);
	}

	/**
	 * 根据审批号和商品信息查询信息
	 * 
	 * @param oaApplicationCode
	 *            SCO申请单号
	 * @param intentionCode
	 *            SCO意向品编号
	 * @param supplierCode
	 *            SCO供应商编号
	 * @return 审批进度信息
	 */
	@Override
	public ApplicationScheduleM2 searchAppByCodeAndMerchandise(
			String oaApplicationCode, String intentionCode, String supplierCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oaApplicationCode", oaApplicationCode);
		map.put("intentionCode", intentionCode);
		map.put("supplierCode", supplierCode);
		return this.merStandProInfoDao.searchAppByCodeAndMerchandise(map);
		/*return (appScheduleM == null ? new ApplicationScheduleM2(
				oaApplicationCode, intentionCode, supplierCode) : appScheduleM);*/
	}

	/**
	 * 根据意向品创建日期查询生效日期最近的标准进度数据
	 */
	@Override
	public MerStandProInfo searchMerStandProInfoByCreated(
			ApplicationScheduleM2 appSchedule) {
		return this.merStandProInfoDao.searchMerStandProInfoByCreated(appSchedule.toMap());
	}

	@Override
	public void completeAppSchedule(ApplicationScheduleM2 appSchedule) {
		this.merStandProInfoDao.completeAppSchedule(appSchedule.toMap());
	}

	@Override
	public List<ApplicationScheduleM2> searchAppByCodeAndMerchandise(Map<String, Object> map) {
		return this.merStandProInfoDao.listAppByCodeAndMerchandise(map);
	}

}