package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.signedquantity;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity.SignedQuantity;

/**
 * 签量数据维护Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public interface SignedQuantityService extends Service {

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
	 * 导出签量数据Excel
	 * 
	 * @param map
	 *            查询条件
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out);

	/**
	 * 新增签量数据
	 * 
	 * @param validMap
	 *            验证是否重复条件
	 * @param signedQuantity
	 *            公共变量
	 * @param list
	 *            新增签量list
	 * @return 响应信息
	 */
	public String completeInsertSignedQuantity(Map<String, Object> validMap,
			SignedQuantity signedQuantity, List<SignedQuantity> list);

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
	 * 改签签量数据
	 * 
	 * @param oldCode
	 *            原签量单号
	 * @param signedQuantity
	 *            新的签量单号
	 * @param list
	 *            对应明细数据
	 * @return
	 */
	public void completeGqSignedQty(String oldCode,
			SignedQuantity signedQuantity, List<SignedQuantity> list);

	/**
	 * 修改签量单据
	 * 
	 * @param map
	 *            修改参数
	 */
	public void updateSignedQtyMain(Map<String, Object> map);

	/**
	 * 删除签量数据
	 * 
	 * @param qlCode
	 *            签量编号
	 */
	public void deleteSignedQuantity(String qlCode);

	/**
	 * 终止签量
	 * 
	 * @param qlCode
	 *            签量编号
	 */
	public void completeTerSignedQuantity(String qlCode);

	/**
	 * 撤销终止签量
	 * 
	 * @param qlCode
	 *            签量编号
	 */
	public void completeRevokerTerSignedQuantity(String qlCode);

	/**
	 * 更新签量数据
	 * 
	 * @param validMap
	 * 			验证是否重复条件
	 * @param pubData
	 *            签量主信息
	 * @param list
	 *            签量明细信息
	 * @return 信息
	 */
	public String completeUpdateSignedQty(Map<String, Object> validMap, SignedQuantity pubData,
			List<SignedQuantity> list);

	/**
	 * 定时日无：计算签量数据[查询当天的数据]
	 */
	public void completeQuartzCalculateQty();
	
}