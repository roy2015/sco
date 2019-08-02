package com.powere2e.sco.dao.impl.peripheral.webservice;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.peripheral.webservice.ApplicationPackageDesignMDao;
import com.powere2e.sco.model.peripheral.webservice.ApplicationPackageDesignM;
/**
 * 商品包装设计申请单(商品)DAO接口的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationPackageDesignMDaoImpl extends DaoImpl implements ApplicationPackageDesignMDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5063589857512500794L;
	//查询
	@Override
	public List<ApplicationPackageDesignM> listApplicationPackageDesignM(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ApplicationPackageDesignMDao.class, "searchApplicationPackageDesignM", map,pageInfo);
	}
	//添加
	@Override
	public void insertApplicationPackageDesignM(Map<String, Object> map){
		this.insert(ApplicationPackageDesignMDao.class, "saveApplicationPackageDesignM", map);
	}
	//删除
	@Override
	public void deleteApplicationPackageDesignM(Map<String, Object> map){
		this.delete(ApplicationPackageDesignMDao.class, "deleteApplicationPackageDesignM", map);
	}
	//修改
	@Override
	public void updateApplicationPackageDesignM(Map<String, Object> map){
		this.update(ApplicationPackageDesignMDao.class, "updateApplicationPackageDesignM", map);
	}
	//装载一个商品包装设计申请单(商品)
	@Override
	public ApplicationPackageDesignM loadApplicationPackageDesignM(Map<String, Object> map) {
		return (ApplicationPackageDesignM)this.get(ApplicationPackageDesignMDao.class, "searchApplicationPackageDesignM", map);
	}
}