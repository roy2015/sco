package com.powere2e.sco.dao.impl.merchandiseintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseintention.MerchandiseIntentionDao;
import com.powere2e.sco.model.merchandiseintention.IntentionSupplierMerchandise;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;

/**
 * 商品意向品DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseIntentionDaoImpl extends DaoImpl implements MerchandiseIntentionDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474257320189804458L;

	// 查询
	@Override
	public List<MerchandiseIntention> listMerchandiseIntention(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandiseIntentionDao.class, "searchMerchandiseIntention", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseIntention(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseIntentionDao.class, "saveMerchandiseIntention", map);
	}

	// 删除
	@Override
	public void deleteMerchandiseIntention(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseIntentionDao.class, "deleteMerchandiseIntention", map);
	}

	// 修改
	@Override
	public void updateMerchandiseIntention(Map<String, Object> map) throws Exception {
		this.update(MerchandiseIntentionDao.class, "updateMerchandiseIntention", map);
	}

	// 装载一个商品意向品
	@Override
	public MerchandiseIntention loadMerchandiseIntention(Map<String, Object> map) throws Exception {
		return (MerchandiseIntention) this.get(MerchandiseIntentionDao.class, "loadMerchandiseIntention", map);
	}

	// 查询关联供应商列表
	@Override
	public List<IntentionSupplierMerchandise> listIntentionSupplierMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandiseIntentionDao.class, "listIntentionSupplierMerchandise", map, pageInfo);
	}

	// 添加关联供应商
	@Override
	public void insertIntentionSupplierMerchandise(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseIntentionDao.class, "saveIntentionSupplierMerchandise", map);
	}

	// 删除关联的供应商
	@Override
	public void deleteIntentionSupplierMerchandise(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseIntentionDao.class, "deleteIntentionSupplierMerchandise", map);
	}

	// 查询意向供应商列表
	@Override
	public List<MerchandiseIntentionSupplier> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandiseIntentionDao.class, "listIntentionSupplier", map, pageInfo);
	}

	// 新增意向供应商
	@Override
	public void insertIntentionSupplier(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseIntentionDao.class, "saveIntentionSupplier", map);
	}

	// 判断供应商为正式供应商还是意向供应商
	@Override
	public MerchandiseIntentionSupplier findSupplierIsTrue(String supplierCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionSupplierCode", supplierCode);
		return (MerchandiseIntentionSupplier) this.get(MerchandiseIntentionDao.class, "findSupplierIsTrue", map);
	}

	// 判断供应商名称是否存在
	public MerchandiseIntentionSupplier findSupplierNameIsExists(String intentionSupplierName) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionSupplierName", intentionSupplierName);
		return (MerchandiseIntentionSupplier) this.get(MerchandiseIntentionDao.class, "findSupplierNameIsExists", map);
	}

	// 根据供应商编号判断该供应商在关联表中是否存在
	@Override
	public IntentionSupplierMerchandise findSupplierMerchandiseIsExists(Map<String, Object> map) throws Exception {
		return (IntentionSupplierMerchandise) this.get(MerchandiseIntentionDao.class, "findSupplierMerchandiseIsExists", map);
	}

	// 根据意向品编号查询是否有OA申请
	@Override
	public List<MerchandiseIntention> findApplicationByIntentionCodes(Map<String, Object> map) throws Exception {
		return this.query(MerchandiseIntentionDao.class, "findApplicationByIntentionCode", map, null);
	}

}