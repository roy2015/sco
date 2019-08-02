package com.powere2e.sco.dao.impl.datamaintenance.gradecontroldata.merchandiserecycle;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycleDao;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycle;

/**
 * 商品回收记录DAO接口的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseRecycleDaoImpl extends DaoImpl implements MerchandiseRecycleDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5723119992673611388L;

	// 查询
	@Override
	public List<MerchandiseRecycle> listMerchandiseRecycle(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseRecycleDao.class, "searchMerchandiseRecycle", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMerchandiseRecycle(Map<String, Object> map) {
		this.insert(MerchandiseRecycleDao.class, "saveMerchandiseRecycle", map);
	}

	public void updateMerchandiseRecycle(Map<String, Object> map) {
		this.update(MerchandiseRecycleDao.class, "updateMerchandiseRecycle", map);
	}
}