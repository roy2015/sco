package com.powere2e.sco.dao.impl.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseDao;
import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品角色查询 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月22日
 */
public class MerchandiseDaoImpl extends DaoImpl implements MerchandiseDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6636562128715255664L;

	/**
	 * 查询商品数据（可分页）
	 */
	@Override
	public List<Merchandise> listMerchandise(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(MerchandiseDao.class, "listMerchandise", map, pageInfo);
	}

	/**
	 * 查询商品名称
	 */
	@Override
	public List<String> listMerchandiseStorageForm() {
		return this.query(MerchandiseDao.class, "listMerchandiseStorageForm", null, null);
	}

	/**
	 * 查询商品id
	 */
	@Override
	public List<String> listMerchandiseCode(Map<String, Object> map) {
		return this.query(MerchandiseDao.class, "listMerchandiseCode", map, null);
	}

	/**
	 * 根据id获取商品数据
	 */
	@Override
	public Merchandise loadMerchandise(Map<String, Object> map) {
		return (Merchandise) this.get(MerchandiseDao.class, "loadMerchandise", map);
	}

}