package com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualified;

/**
 * 商品抽检不合格记录DAO接口
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MerchandiseUnqualifiedDao extends Dao {
	/**
	 * 商品抽检不合格记录查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品抽检不合格记录列表
	 */
	public List<MerchandiseUnqualified> listMerchandiseUnqualified(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品抽检不合格记录
	 *
	 * @param map
	 *
	 */
	public void insertMerchandiseUnqualified(Map<String, Object> map);

	/**
	 * 查询一条不合格的数据，以便更新操作
	 * 
	 * @return
	 */
	public MerchandiseUnqualified searchMerchandiseUnqualified(Map<String, Object> map);

	/**
	 * 覆盖商品抽检不合格记录的抽检批次
	 *
	 * @param map
	 *
	 */
	public void updateMerchandiseUnqualified(Map<String, Object> map);

	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandiseUnqualified> listMerchandise(Map<String, Object> map, PageInfo pageInfo);
}