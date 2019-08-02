package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigType;
/**
 * 原料大类Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MaterialBigTypeService extends Service {
	/**
	 * 原料大类查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回原料大类列表
	 */
	public List<MaterialBigType> listMaterialBigType(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据名称加载一个原料大类
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MaterialBigType loadMaterialBigType(String materialBigTypeName);
	/**
	 * 添加原料大类
	 *
	 * @param map
	 *				
	 */
	public void insertMaterialBigType(MaterialBigType materialBigType);
	/**
	 * 删除原料大类
	 *
	 * @param map 
	 *				必须参数id为要删除的原料大类id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMaterialBigType(String materialBigTypeCode);
	/**
	 * 修改原料大类
	 *
	 * @param map 
	 *				必须参数id为要修改原料大类的id号，不能为数组
	 */
	public void updateMaterialBigType(MaterialBigType materialBigType);
	/**
	 * 查询该原料大类是否存在于原料小类
	 * @param materialBigTypeCode
	 * @return
	 */
	public Integer serachMaterialSmallType(String materialBigTypeCode);
	/**
	 * 根据名称ID一个原料大类
	 * @param materialBigTypeCode
	 * @return
	 */
	public MaterialBigType loadMaterialBigTypeByCode(String materialBigTypeCode);
}