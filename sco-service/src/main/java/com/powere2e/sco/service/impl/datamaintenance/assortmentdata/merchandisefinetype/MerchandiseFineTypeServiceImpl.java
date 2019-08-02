package com.powere2e.sco.service.impl.datamaintenance.assortmentdata.merchandisefinetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineTypeDao;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineTypeService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineType;
import com.powere2e.security.model.Option;

/**
 * 商品细分类维护业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseFineTypeServiceImpl extends ServiceImpl implements MerchandiseFineTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -977445052269360549L;
	private MerchandiseFineTypeDao merchandiseFineTypeDao;

	public static MerchandiseFineTypeService getInstance() {
		return (MerchandiseFineTypeService) ConfigFactory.getInstance().getBean("merchandiseFineTypeService");
	}

	// 获得商品细分类维护DAO实例
	public MerchandiseFineTypeDao getMerchandiseFineTypeDao() {
		return merchandiseFineTypeDao;
	}

	// 设置商品细分类维护DAO实例
	public void setMerchandiseFineTypeDao(MerchandiseFineTypeDao merchandiseFineTypeDao) {
		this.merchandiseFineTypeDao = merchandiseFineTypeDao;
	}

	// 查询
	@Override
	public List<MerchandiseFineType> listMerchandiseFineType(Map<String, Object> map, PageInfo pageInfo) {
		return this.merchandiseFineTypeDao.listMerchandiseFineType(map, pageInfo);
	}

	@Override
	public List<Option> listQualitative(Map<String, Object> map) {
		return this.merchandiseFineTypeDao.listQualitative(map);
	}

	// 添加
	@Override
	public void insertMerchandiseFineType(MerchandiseFineType merchandiseFineType) {
		this.merchandiseFineTypeDao.insertMerchandiseFineType(merchandiseFineType.toMap());
	}

	// 删除
	@Override
	public void deleteMerchandiseFineType(String fineTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeCode", fineTypeCode.split(","));
		this.merchandiseFineTypeDao.deleteMerchandiseFineType(map);
	}

	// 加载一个商品细分类维护
	@Override
	public MerchandiseFineType loadMerchandiseFineType(String fineTypeName, String detailTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeNameAU", fineTypeName);
		map.put("detailTypeCodeAU", detailTypeCode);
		return this.merchandiseFineTypeDao.loadMerchandiseFineType(map);
	}

	// 根据编号查询
	@Override
	public Integer serachMerchandiseFineType(String fineTypeCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fineTypeCode", fineTypeCode);
		return this.merchandiseFineTypeDao.searchMerchandiseFineType(map);
	}
}