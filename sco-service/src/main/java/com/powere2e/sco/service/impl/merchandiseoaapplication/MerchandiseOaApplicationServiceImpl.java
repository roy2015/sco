package com.powere2e.sco.service.impl.merchandiseoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.MerchandiseOaApplicationDao;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.MerchandiseOaApplication;

/**
 * 商品OA申请单业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月22日
 */
public class MerchandiseOaApplicationServiceImpl extends ServiceImpl implements MerchandiseOaApplicationService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4425663394210935707L;
	private MerchandiseOaApplicationDao merchandiseOaApplicationDao;

	public static MerchandiseOaApplicationService getInstance() {
		return (MerchandiseOaApplicationService) ConfigFactory.getInstance().getBean("merchandiseOaApplicationService");
	}

	public MerchandiseOaApplicationDao getMerchandiseOaApplicationDao() {
		return merchandiseOaApplicationDao;
	}

	public void setMerchandiseOaApplicationDao(MerchandiseOaApplicationDao merchandiseOaApplicationDao) {
		this.merchandiseOaApplicationDao = merchandiseOaApplicationDao;
	}

	// 查询新品引进OA申请列表(针对意向品)
	@Override
	public List<MerchandiseIntention> queryIntentionApplicationList(Map<String, Object> map, PageInfo pageInfo)
			throws Exception {
		return merchandiseOaApplicationDao.queryIntentionApplicationList(map, pageInfo);
	}

	// 查询老品新上、正常调价和快速调价OA申请列表(针对商品)
	@Override
	public List<MerchandiseIntention> queryMerchandiseApplicationList(Map<String, Object> map, PageInfo pageInfo)
			throws Exception {
		return merchandiseOaApplicationDao.queryMerchandiseApplicationList(map, pageInfo);
	}

	// 添加
	@Override
	public void insertOaApplication(MerchandiseOaApplication oaApplication) throws Exception {
		merchandiseOaApplicationDao.insertOaApplication(oaApplication.toMap());
	}

	// 根据所选的意向品编号和供应商编号对该供应商对应的意向品新增OA申请
	@Override
	public void insertIntentionOaApplication(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.insertIntentionOaApplication(map);
	}

	// 根据意向品编号和供应商编号组查询OA申请记录，根据记录和页面获取的申请单号进行比对
	@Override
	public String getIntentionOaApplicationReceiptInfo(String applicationCode, String intentionAndSupplierCodes,
			String applicationType) throws Exception {
		if (StringUtils.isBlank(applicationType)) {
			// 如果申请类型为空，抛出异常
			throw new Exception();
		} else {
			List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
					.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
			if (BusinessConstants.ApplicationType.MERCHANDISE_NEW.toString().equals(applicationType)
					|| BusinessConstants.ApplicationType.MERCHANDISE_FASTIMPORT.toString().equals(applicationType)) {// 为新品引进，不能重复申请
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("applicationType", applicationType);// 申请类型
				List<ApplicationMerchandise> applicationList = merchandiseOaApplicationDao
						.listIntentionOaApplication(map);
				if (applicationList != null && applicationList.size() > 0) {// 说明查询到记录，因此已经做了OA申请
					for (ApplicationMerchandise merchandise : applicationList) {
						// 如果从页面获取到的oa单号和查询到oa单号不一致或者选择的编号组和查询到已申请记录条数的不一样，就不能做任何操作
						if (!applicationCode.equals(merchandise.getApplicationCode())
								|| (list.size() != applicationList.size())) {
							return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString();
						}
					}
					// 如果页面获取到的oa单号和查询到oa单号一致，还需要判断是否为草稿状态
					if (BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
							applicationList.get(0).getApplicationStatus())||BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
									applicationList.get(0).getApplicationStatus())) {
						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString();
					} else {
						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString();
					}
				} else {
					// 说明没有做OA申请
					map.put("applicationCode", applicationCode);
					map.put("applicationStatus", BusinessConstants.MerchandiseApplicationStatus.CG.toString());// 申请状态
					merchandiseOaApplicationDao.insertOaApplication(map);
					merchandiseOaApplicationDao.insertIntentionOaApplication(map);

					return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString();
				}
			} else {// 老品新上、正常调价和快速调价，可以重复申请
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("list", list);
				map.put("applicationType", applicationType);// 申请类型
				map.put("applicationStatus", BusinessConstants.MerchandiseApplicationStatus.SPTG.toString());// 只查询没有审批通过的申请单
				List<ApplicationMerchandise> applicationList = merchandiseOaApplicationDao
						.listIntentionOaApplication(map);
				if (applicationList != null && applicationList.size() > 0) {// 说明查询到记录，因此已经做了OA申请
					for (ApplicationMerchandise merchandise : applicationList) {
						// 如果从页面获取到的oa单号和查询到oa单号不一致或者选择的编号组和查询到已申请记录条数的不一样，就不能做任何操作
						if (!applicationCode.equals(merchandise.getApplicationCode())
								|| (list.size() != applicationList.size())) {
							return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString();
						}
					}
					// 如果页面获取到的oa单号和查询到oa单号一致，还需要判断是否为草稿状态
					if (BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
							applicationList.get(0).getApplicationStatus())||BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
									applicationList.get(0).getApplicationStatus())) {
						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_CG.toString();
					} else {
						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString();
					}
				} else {
					// 说明理论上没有做OA申请
					map.clear();
					map.put("list", list);
					map.put("applicationType", applicationType);// 申请类型
					List<ApplicationMerchandise> haveApplicationList = merchandiseOaApplicationDao
							.listIntentionOaApplication(map);
					if (haveApplicationList != null && haveApplicationList.size() > 0) {
						for (ApplicationMerchandise merchandise : haveApplicationList) {
							// 如果从页面获取到的oa单号和查询到oa单号一致,说明是修改操作,如果不一致，说明是对已经审批通过的商品继续做OA审批
							if (applicationCode.equals(merchandise.getApplicationCode())) {
								return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString();
							}
						}

						// 和已经查询到的oa单号不一致，说明是对已经审批通过的商品继续做OA审批
						map.put("applicationCode", applicationCode);
						map.put("applicationStatus", BusinessConstants.MerchandiseApplicationStatus.CG.toString());// 申请状态
						merchandiseOaApplicationDao.insertOaApplication(map);
						merchandiseOaApplicationDao.insertIntentionOaApplication(map);

						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString();

					} else {
						map.put("applicationCode", applicationCode);
						map.put("applicationStatus", BusinessConstants.MerchandiseApplicationStatus.CG.toString());// 申请状态
						merchandiseOaApplicationDao.insertOaApplication(map);
						merchandiseOaApplicationDao.insertIntentionOaApplication(map);

						return MerchandiseOaApplicationUtil.ReportNewOaApplicationState.NONE.toString();
					}

				}
			}
		}
	}

	// 根据申请单号删除OA申请
	@Override
	public void deleteOaApplicationByCode(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteOaApplicationByCode(map);
	}

	// 根据申请单号删除OA申请关联表
	@Override
	public void deleteApplicationMerhandiseByCode(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationMerhandiseByCode(map);
	}

	// 修改OA申请单状态
	@Override
	public void updateOaApplicationStatus(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.updateOaApplicationStatus(map);
	}

	// 添加缺失文件说明
	@Override
	public void insertApplicationLackFileM(Map<String, Object> map) throws Exception {
		// 新增缺少文件说明
		merchandiseOaApplicationDao.insertApplicationLackFileM(map);
		// 新建TBPM时，取消修改新品引进申请单床单
		// 修改申请单状态
		/*
		 * map.put("applicationStatus",
		 * BusinessConstants.ApplicationStatus.YX.toString());
		 * merchandiseOaApplicationDao.updateOaApplicationStatus(map);
		 */
	}

	// 撤销允许OA同步
	@Override
	public void completeUndoOaApplicationStatus(String applicationCodes) throws Exception {
		if (StringUtils.isNotBlank(applicationCodes)) {
			String codesArr[] = applicationCodes.split(",");
			if (codesArr != null && codesArr.length > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (String code : codesArr) {
					map.put("applicationCode", code);
					map.put("applicationStatus", BusinessConstants.ApplicationStatus.CG.toString());

					merchandiseOaApplicationDao.updateOaApplicationStatus(map);// 修改OA申请单为"草稿"状态
					merchandiseOaApplicationDao.deleteApplicationLackFileM(map);// 删除缺失文件说明
					map.clear();
				}
			}
		}
	}

	// 查看OA系统的申请单号和联系人
	@Override
	public MerchandiseOaApplication findApproveOpinion(String applicationCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);

		return merchandiseOaApplicationDao.findApproveOpinion(map);
	}

	// 根据ID号加载一个商品OA申请单
	@Override
	public MerchandiseOaApplication loadOaApplication(Map<String, Object> map) throws Exception {
		return merchandiseOaApplicationDao.loadOaApplication(map);
	}

	// 根据OA申请单号查询意向品/商品编号和对应的供应商编号
	@Override
	public List<ApplicationMerchandise> findMerchandiseCodeAndSupplierCodeByApplicationCode(Map<String, Object> map)
			throws Exception {
		return merchandiseOaApplicationDao.findMerchandiseCodeAndSupplierCodeByApplicationCode(map);
	}

	// 根据序列获取主键
	@Override
	public String selectReportNextID(String sequenceName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sequenceName", sequenceName);
		return merchandiseOaApplicationDao.selectReportNextID(map);
	}

	// CR
	// 添加商品巡厂申请单
	@Override
	public void insertApplicationVisitFactory(String applicationVfCode,String codes) throws Exception {
		// codes的数据格式为applicationCode1:merchandiseCode1:supplierCode1,code2:code2:code2
		Map<String, Object> factoryMap = new HashMap<String, Object>();
		factoryMap.put("applicationVfCode", applicationVfCode);//设置主键
//		factoryMap.put("applicationStatus", BusinessConstants.VisitApplicationStatus.SPZ.toString());//设置巡厂申请单状态
		if (StringUtils.isNotBlank(codes)) {
			String codeArr[] = codes.split(",");
			if (codeArr != null && codeArr.length > 0) {
				for (int i = 0; i < codeArr.length; i++) {
					String eachCode = codeArr[i];
					if (StringUtils.isNotBlank(eachCode)) {
						String eachArr[] = eachCode.split(":");
						if (eachArr != null && eachArr.length >= 3) {
							Map<String, Object> eachMap = new HashMap<String, Object>();
							eachMap.put("applicationVfCode", applicationVfCode);
							eachMap.put("applicationCode", eachArr[0]);// 申请单号
							eachMap.put("merchandiseCode", eachArr[1]);// 商品编号
							eachMap.put("supplierCode", eachArr[2]);// 供应商编号
							
							//新增TBPM巡厂申请单前，先把以前的TBPM巡厂申请单删除，然后再新增。一条OA记录只有一条巡厂申请单
							merchandiseOaApplicationDao.deleteApplicationVisitFactoryByVfCode(eachMap);
							merchandiseOaApplicationDao.deleteApplicationVisitFactoryMByVfCode(eachMap);
							//新增TBPM巡厂申请单
							merchandiseOaApplicationDao.insertApplicationVisitFactoryM(eachMap);
						}
					}
				}
			}
			merchandiseOaApplicationDao.insertApplicationVisitFactory(factoryMap);
		}
	}

	// 删除商品巡厂申请单
	@Override
	public void deleteApplicationVisitFactory(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationVisitFactory(map);
	}

	@Override
	public void insertApplicationVisitFactoryM(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.insertApplicationVisitFactoryM(map);
	}

	@Override
	public void deleteApplicationVisitFactoryM(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationVisitFactoryM(map);
	}

	// 添加商品包装设计申请单
	@Override
	public void insertApplicationPackageDesign(Map<String, Object> map) throws Exception {
		//新增TBPM包装设计申请单前，先把以前的TBPM巡厂申请单删除，然后再新增。一条OA记录只有一条巡厂申请单
		merchandiseOaApplicationDao.deleteApplicationPackageDesignByPdCode(map);
		merchandiseOaApplicationDao.deleteApplicationPackageDesignMByPdCode(map);
		
		merchandiseOaApplicationDao.insertApplicationPackageDesign(map);
		merchandiseOaApplicationDao.insertApplicationPackageDesignM(map);
	}

	// 删除商品包装设计申请单
	@Override
	public void deleteApplicationPackageDesign(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationPackageDesign(map);
	}

	@Override
	public void insertApplicationPackageDesignM(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.insertApplicationPackageDesignM(map);
	}

	@Override
	public void deleteApplicationPackageDesignM(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationPackageDesignM(map);
	}

	// 删除申请进度信息(商品OA)2
	@Override
	public void deleteApplicationScheduleM2(Map<String, Object> map) throws Exception {
		merchandiseOaApplicationDao.deleteApplicationScheduleM2(map);
	}

	// 根据意向品编号和供应商编号查询该供应商对应的意向品是否已经OA申请
	@Override
	public List<ApplicationMerchandise> listIntentionOaApplication(Map<String, Object> map) throws Exception {
		return merchandiseOaApplicationDao.listIntentionOaApplication(map);
	}

	/**
	 * 校验并添加申请报告
	 * 
	 * @param applicationType
	 *            申请单类型
	 * @param applicationCode
	 *            申请单号
	 * @param reportCode
	 *            报表编号
	 * @param intentionAndSupplierCodes
	 *            意向品供应商编号
	 * @param intentionCode
	 *            商品编号
	 * @param supplierCode
	 *            供应商编号
	 * @return map 申购报告
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getApplicationReportMap(String applicationType, String applicationCode, String reportCode, String intentionAndSupplierCodes,
			String intentionCode, String supplierCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		map.put("list", list);
		map.put("applicationType", applicationType);// 申请类型
		map.put("applicationStatus", BusinessConstants.MerchandiseApplicationStatus.SPTG.toString());// 只查询没有审批通过的申请单
		List<ApplicationMerchandise> applicationList = this.listIntentionOaApplication(map);

		boolean addFlag = true;
		if (!applicationList.isEmpty()) {
			for (ApplicationMerchandise merchandise : applicationList) {
				// 如果从页面获取到的oa单号和查询到oa单号不一致或者选择的编号组和查询到已申请记录条数的不一样，就不能做任何操作
				if (!applicationCode.equals(merchandise.getApplicationCode())
						|| (list.size() != applicationList.size())) {
					addFlag = false;
					break;
				}
			}
			// 如果页面获取到的oa单号和查询到oa单号一致，还需要判断是否为草稿状态
			if (addFlag) {
				if (BusinessConstants.MerchandiseApplicationStatus.CG.toString().equals(
						applicationList.get(0).getApplicationStatus())||BusinessConstants.MerchandiseApplicationStatus.BH.toString().equals(
								applicationList.get(0).getApplicationStatus())) {
				} else {
					addFlag = false;
				}
			}
		} else {
			map.remove("applicationStatus");
			List<ApplicationMerchandise> haveApplicationList = this.listIntentionOaApplication(map);
			if (!haveApplicationList.isEmpty()) {
				for (ApplicationMerchandise merchandise : haveApplicationList) {
					// 如果从页面获取到的oa单号和查询到oa单号一致,说明是修改操作,如果不一致，说明是对已经审批通过的商品继续做OA审批
					if (applicationCode.equals(merchandise.getApplicationCode())) {
						addFlag = false;
						break;
					}
				}
			}
		}
		if (addFlag) {
			map.put("reportCode", reportCode);
			map.put("applicationCode", applicationCode);
			map.put("merchandiseCode", intentionCode);
			map.put("supplierCode", supplierCode);
			return map;
		} 
		return null;
	}
}