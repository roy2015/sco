package com.powere2e.sco.dao.impl.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualifiedDao;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualified;

/**
 * 商品抽检不合格记录DAO接口的实现
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public class MerchandiseUnqualifiedDaoImpl extends DaoImpl implements MerchandiseUnqualifiedDao {

	private static final long serialVersionUID = 2480910750351190083L;

	// 查询
	@Override
	public List<MerchandiseUnqualified> listMerchandiseUnqualified(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseUnqualifiedDao.class, "searchMerchandiseUnqualified", map, pageInfo);
	}

	// 查询
	@Override
	public List<MerchandiseUnqualified> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MerchandiseUnqualifiedDao.class, "searchMerchandise", map, pageInfo);
	}

	// 添加一批(100条)商品抽检不合格记录
	@Override
	public void insertMerchandiseUnqualified(Map<String, Object> map) {
		this.insert(MerchandiseUnqualifiedDao.class, "saveMerchandiseUnqualified", map);
	}

	// 查询一条记录，以便更新操作
	@Override
	public MerchandiseUnqualified searchMerchandiseUnqualified(Map<String, Object> map) {
		return (MerchandiseUnqualified) this.get(MerchandiseUnqualifiedDao.class, "getMerchandiseUnqualified", map);
	}

	// 覆盖商品抽检不合格记录的抽检批次字段
	@Override
	public void updateMerchandiseUnqualified(Map<String, Object> map) {
		this.update(MerchandiseUnqualifiedDao.class, "updateMerchandiseUnqualified", map);
	}
}