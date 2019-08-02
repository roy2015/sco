package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigTypeDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigType;
/**
 * 原料大类DAO接口的实现
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialBigTypeDaoImpl extends DaoImpl implements MaterialBigTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2225770921894005085L;
	//查询
	@Override
	public List<MaterialBigType> listMaterialBigType(Map<String, Object> map,PageInfo pageInfo){
		return this.query(MaterialBigTypeDao.class, "searchMaterialBigType", map,pageInfo);
	}
	//添加
	@Override
	public void insertMaterialBigType(Map<String, Object> map){
		this.insert(MaterialBigTypeDao.class, "saveMaterialBigType", map);
	}
	//删除
	@Override
	public void deleteMaterialBigType(Map<String, Object> map){
		this.delete(MaterialBigTypeDao.class, "deleteMaterialBigType", map);
	}
	//修改
	@Override
	public void updateMaterialBigType(Map<String, Object> map){
		this.update(MaterialBigTypeDao.class, "updateMaterialBigType", map);
	}
	//装载一个原料大类
	@Override
	public MaterialBigType loadMaterialBigType(Map<String, Object> map) {
		return (MaterialBigType)this.get(MaterialBigTypeDao.class, "searchMaterialBigType", map);
	}
	//查询原料小类中存在该原料大类的数量
	@Override
	public Integer searchMaterialSmallType(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(MaterialBigTypeDao.class, "searchMaterialSmallType", map);
	}
}