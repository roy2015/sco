package com.powere2e.sco.dao.impl.datamaintenance.assortmentdata.merchandisefinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineTypeDao;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineType;
import com.powere2e.security.model.Option;

/**
 * 商品细分类维护DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseFineTypeDaoImpl extends DaoImpl implements MerchandiseFineTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6962553073088486612L;

	// 查询
	@Override
	public List<MerchandiseFineType> listMerchandiseFineType(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseFineTypeDao.class, "searchMerchandiseFineType", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseFineType(Map<String, Object> map) {
		this.insert(MerchandiseFineTypeDao.class, "saveMerchandiseFineType", map);
	}

	// 删除
	@Override
	public void deleteMerchandiseFineType(Map<String, Object> map) {
		this.delete(MerchandiseFineTypeDao.class, "deleteMerchandiseFineType", map);
	}

	// 装载一个商品细分类维护
	@Override
	public MerchandiseFineType loadMerchandiseFineType(Map<String, Object> map) {
		return (MerchandiseFineType) this.get(MerchandiseFineTypeDao.class, "searchMerchandiseFineType", map);
	}

	@Override
	public Integer searchMerchandiseFineType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(MerchandiseFineTypeDao.class, "searchMerchandise", map);
	}

	@Override
	public List<Option> listQualitative(Map<String, Object> map) {
		return this.query(MerchandiseFineTypeDao.class, "listQualitative", map, null);
	}
}