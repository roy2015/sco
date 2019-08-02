package com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandisefinetype;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineType;
import com.powere2e.security.model.Option;
/**
 * 商品细分类维护Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月14日
 */
public interface MerchandiseFineTypeService extends Service {
	/**
	 * 商品细分类维护查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品细分类维护列表
	 */
	public List<MerchandiseFineType> listMerchandiseFineType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据名称加载一个商品细分类维护
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MerchandiseFineType loadMerchandiseFineType(String fineTypeName,String detailTypeCode);
	/**
	 * 添加商品细分类维护
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseFineType(MerchandiseFineType merchandiseFineType);
	/**
	 * 删除商品细分类维护
	 *
	 * @param map 
	 *				必须参数id为要删除的商品细分类维护id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseFineType(String fineTypeCode);
	/**
	 * 查询该细分类是否存在于商品
	 * @param roleCode
	 * @return
	 */
	public Integer serachMerchandiseFineType(String fineTypeCode);
	/**
	 * 查询细分类下拉列表
	 * @param map
	 * @return
	 */
	public List<Option> listQualitative(Map<String, Object> map);
}