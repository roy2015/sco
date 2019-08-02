package com.powere2e.sco.dao.impl.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseintention.MerchandiseQuotedDao;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 商品意向品DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class MerchandiseQuotedDaoImpl extends DaoImpl implements MerchandiseQuotedDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474257320189804458L;

	/* ======================报价单操作========================================== */
	// 添加供应商报价单
	@Override
	public void insertMerchandiseQuoted(Map<String, Object> map) throws Exception {
		this.insert(MerchandiseQuotedDao.class, "saveMerchandiseQuoted", map);
	}

	// 修改供应商报价单
	@Override
	public void updateMerchandiseQuoted(Map<String, Object> map) throws Exception {
		this.update(MerchandiseQuotedDao.class, "updateMerchandiseQuoted", map);
	}

	// 删除报价单(根据报价日期和供应商编号删除)
	@Override
	public void deleteMerchandiseQuoted(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseQuotedDao.class, "deleteMerchandiseQuoted", map);
	}

	// 根据id删除报价单
	@Override
	public void deleteMerchandiseQuotedById(Map<String, Object> map) throws Exception {
		this.delete(MerchandiseQuotedDao.class, "deleteMerchandiseQuotedById", map);
	}

	// 供应商报价单查询
	@Override
	public List<MerchandiseQuoted> listMerchandiseQuoted(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(MerchandiseQuotedDao.class, "listMerchandiseQuoted", map, pageInfo);
	}
	
	// 供应商报价单查询
		@Override
		public List<MerchandiseQuoted> listSupplierQuoted(Map<String, Object> map) throws Exception {
			return this.query(MerchandiseQuotedDao.class, "listSupplierQuoted", map,null);
		}

	// 根据ID号加载一个供应商报价单
	@Override
	public MerchandiseQuoted loadMerchandiseQuoted(Map<String, Object> map) throws Exception {
		return (MerchandiseQuoted) this.get(MerchandiseQuotedDao.class, "loadMerchandiseQuoted", map);
	}

	//查询勾选的参照品的报价计量单位
		@Override
		public List<MerchandiseQuoted> listRefMerchandiseQuoted(
				Map<String, Object> map) throws Exception {
			return this.query(MerchandiseQuotedDao.class, "listRefMerchandiseQuoted", map,null);
		}

}