package com.powere2e.sco.dao.impl.merchandiseoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.MerchandiseOaApplicationDao;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.MerchandiseOaApplication;

/**
 * 商品OA申请单DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月22日
 */
public class MerchandiseOaApplicationDaoImpl extends DaoImpl implements MerchandiseOaApplicationDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1336362898493844038L;

	// 查询新品引进OA申请列表(针对意向品)
	@Override
	public List<MerchandiseIntention> queryIntentionApplicationList(Map<String, Object> map, PageInfo pageInfo)
			throws Exception {
		return this.query(MerchandiseOaApplicationDao.class, "queryIntentionApplicationList", map, pageInfo);
	}

	// 查询老品新上、正常调价和快速调价OA申请列表(针对商品)
	@Override
	public List<MerchandiseIntention> queryMerchandiseApplicationList(Map<String, Object> map, PageInfo pageInfo)
			throws Exception {
		return this.query(MerchandiseOaApplicationDao.class, "queryMerchandiseApplicationList", map, pageInfo);
	}

	// 根据意向品编号和供应商编号查询该供应商对应的意向品是否已经OA申请
	@Override
	public List<ApplicationMerchandise> listIntentionOaApplication(Map<String, Object> map) throws Exception {
		return this.query(MerchandiseOaApplicationDao.class, "listIntentionOaApplication", map, null);
	}

	// 添加
	@Override
	public void insertOaApplication(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "saveOaApplication", map);
	}

	// 根据所选的意向品编号和供应商编号对该供应商对应的意向品新增OA申请
	@Override
	public void insertIntentionOaApplication(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "saveIntentionOaApplication", map);
	}

	// 根据申请单号删除OA申请
	@Override
	public void deleteOaApplicationByCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteOaApplicationByCode", map);
	}

	// 根据申请单号删除OA申请关联表
	@Override
	public void deleteApplicationMerhandiseByCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationMerhandiseByCode", map);
	}

	// 修改OA申请单状态
	@Override
	public void updateOaApplicationStatus(Map<String, Object> map) throws Exception {
		this.update(MerchandiseOaApplicationDao.class, "updateOaApplicationStatus", map);
	}

	// 添加缺失文件说明
	@Override
	public void insertApplicationLackFileM(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "insertApplicationLackFileM", map);
	}

	// 删除缺失文件说明
	@Override
	public void deleteApplicationLackFileM(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationLackFileM", map);
	}

	// 查看OA系统的申请单号和联系人
	@Override
	public MerchandiseOaApplication findApproveOpinion(Map<String, Object> map) throws Exception {
		return (MerchandiseOaApplication) this.get(MerchandiseOaApplicationDao.class, "findApproveOpinion", map);
	}

	// 根据ID号加载一个商品OA申请单
	@Override
	public MerchandiseOaApplication loadOaApplication(Map<String, Object> map) throws Exception {
		return (MerchandiseOaApplication) this.get(MerchandiseOaApplicationDao.class, "loadOaApplication", map);
	}

	// 根据OA申请单号查询意向品/商品编号和对应的供应商编号
	@Override
	public List<ApplicationMerchandise> findMerchandiseCodeAndSupplierCodeByApplicationCode(Map<String, Object> map)
			throws Exception {
		return this.query(MerchandiseOaApplicationDao.class, "findMerchandiseCodeAndSupplierCode", map, null);
	}

	// 根据序列获取主键
	@Override
	public String selectReportNextID(Map<String, Object> map) throws Exception {
		return (String) this.get(MerchandiseOaApplicationDao.class, "selectReportNextID", map);
	}

	// CR
	// 添加商品巡厂申请单
	@Override
	public void insertApplicationVisitFactory(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "insertApplicationVisitFactory", map);
	}

	// 删除商品巡厂申请单
	@Override
	public void deleteApplicationVisitFactory(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationVisitFactory", map);
	}

	// 根据巡厂单号删除商品巡厂申请单
	@Override
	public void deleteApplicationVisitFactoryByVfCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationVisitFactoryByVfCode", map);
	}

	@Override
	public void insertApplicationVisitFactoryM(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "insertApplicationVisitFactoryM", map);
	}

	@Override
	public void deleteApplicationVisitFactoryM(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationVisitFactoryM", map);
	}

	// 根据巡厂单号删除商品巡厂申请单
	@Override
	public void deleteApplicationVisitFactoryMByVfCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationVisitFactoryMByVfCode", map);
	}

	// 添加商品包装设计申请单
	@Override
	public void insertApplicationPackageDesign(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "insertApplicationPackageDesign", map);
	}

	// 删除商品包装设计申请单
	@Override
	public void deleteApplicationPackageDesign(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationPackageDesign", map);
	}

	// 根据包装设计申请单号删除删除商品包装设计申请单
	@Override
	public void deleteApplicationPackageDesignByPdCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationPackageDesignByPdCode", map);
	}

	@Override
	public void insertApplicationPackageDesignM(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseOaApplicationDao.class, "insertApplicationPackageDesignM", map);
	}

	@Override
	public void deleteApplicationPackageDesignM(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationPackageDesignM", map);
	}

	// 根据包装设计申请单号删除商品包装设计申请单(商品)
	@Override
	public void deleteApplicationPackageDesignMByPdCode(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationPackageDesignMByPdCode", map);
	}

	// 删除申请进度信息(商品OA)2
	@Override
	public void deleteApplicationScheduleM2(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseOaApplicationDao.class, "deleteApplicationScheduleM2", map);
	}

}