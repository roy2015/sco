package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.signedquantity;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity.SignedQuantity;

/**
 * 签量数据维护Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public interface SignedQuantityDao extends Dao {

	/**
	 * 签量数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 签量数据列表
	 */
	public List<SignedQuantity> listSignedQuantity(Map<String, Object> map,
			PageInfo pageInfo);


	/**
	 * 新增签量主信息
	 * 
	 * @param map
	 *            主信息
	 */
	public void insertSignedQuantityQl(Map<String, Object> map);

	/**
	 * 新增签量明细数据
	 * 
	 * @param map
	 *            签量数据list
	 */
	public void insertSignedQuantityDetail(Map<String, Object> map);
	
	/**
	 * 查询需改签(或需修改)的主信息
	 * 
	 * @param map
	 *            查询参数
	 * @return 需改签(或需修改)的主信息
	 */
	public SignedQuantity searchSignedQtyMain(Map<String, Object> map);

	/**
	 * 查询需改签(或需修改)的明细
	 * 
	 * @param map
	 *            查询参数
	 * @return 需改签(或需修改)的明细
	 */
	public List<SignedQuantity> listSignedQtyDetail(Map<String, Object> map);

	/**
	 * 修改签量主信息
	 * 
	 * @param map
	 *            修改参数
	 */
	public void updateSignedQtyMain(Map<String, Object> map);

	/**
	 * 通过签量单号查询出其关联的商品+供应商
	 * 
	 * @param map
	 *            签量单号
	 * @return 商品编号
	 */
	public List<String> listMerCodeAndSuppCodeByQlCode(Map<String, Object> map);
	
	/**
	 * 查询需删除的签量编号
	 * 
	 * @param map
	 *            签量编号
	 * @return 需删除的签量编号(返回格式:'A','B')
	 */
	public String searchDelQlCode(Map<String, Object> map);
	
	/**
	 * 删除签量主信息
	 * 
	 * @param map
	 *            签量编号
	 */
	public void deleteSignedQtyMain(Map<String, Object> map);

	/**
	 * 删除签量明细数据
	 * 
	 * @param map
	 *            签量编号
	 */
	public void deleteSignedQtyDetail(Map<String, Object> map);

	/**
	 * 修改签量时批量新增/修改操作
	 * 
	 * @param map
	 *            明细数据
	 */
	public void insOrUpdSignedQty(Map<String, Object> map);

	/**
	 * 计算签量时查询相关签量数据
	 * 
	 * @param map
	 *            查询参数
	 * @return 签量数据
	 */
	public List<SignedQuantity> listCalculateSignedQuantity(
			Map<String, Object> map);

	/**
	 * 非定时任务时重置所有计算的数据(需要重新计算所有)
	 * 
	 * @param map
	 *            签量单号
	 */
	public void resetSignedQtyCalculate(Map<String, Object> map);

	/**
	 * 更新签量统计信息
	 * 
	 * @param map 签量实例
	 */
	public void updateSignedQtyCalculate(Map<String, Object> map);

}