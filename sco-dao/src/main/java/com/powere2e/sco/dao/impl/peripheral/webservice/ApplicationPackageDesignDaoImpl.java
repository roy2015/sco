package com.powere2e.sco.dao.impl.peripheral.webservice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationPackageDesignDao;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesign;
/**
 * 商品包装设计申请单DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationPackageDesignDaoImpl extends DaoImpl implements ApplicationPackageDesignDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2199011067498130632L;
	//查询
	@Override
	public List<ApplicationPackageDesign> listApplicationPackageDesign(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ApplicationPackageDesignDao.class, "searchApplicationPackageDesign", map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationPackageDesign(Map<String, Object> map){
		this.insert(ApplicationPackageDesignDao.class, "saveApplicationPackageDesign", map);
	}
	//删除
	@Override
	public void deleteApplicationPackageDesign(Map<String, Object> map){
		this.delete(ApplicationPackageDesignDao.class, "deleteApplicationPackageDesign", map);
	}
	//修改
	@Override
	public void updateApplicationPackageDesign(Map<String, Object> map){
		this.update(ApplicationPackageDesignDao.class, "updateApplicationPackageDesign", map);
	}
	//装载一个商品包装设计申请单
	@Override
	public ApplicationPackageDesign loadApplicationPackageDesign(Map<String, Object> map) {
		return (ApplicationPackageDesign)this.get(ApplicationPackageDesignDao.class, "searchApplicationPackageDesign", map);
	}
}