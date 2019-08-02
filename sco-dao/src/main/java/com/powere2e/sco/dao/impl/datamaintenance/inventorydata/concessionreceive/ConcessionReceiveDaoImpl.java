package com.powere2e.sco.dao.impl.datamaintenance.inventorydata.concessionreceive;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.inventorydata.concessionreceive.ConcessionReceiveDao;
import com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive.ConcessionReceive;
/**
 * 让步接收DAO接口的实现
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public class ConcessionReceiveDaoImpl extends DaoImpl implements ConcessionReceiveDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5189856095137630505L;
	//查询
	@Override
	public List<ConcessionReceive> listConcessionReceive(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ConcessionReceiveDao.class, "searchConcessionReceive", map,pageInfo);
	}
	//添加
	@Override
	public void insertConcessionReceive(Map<String, Object> map){
		this.insert(ConcessionReceiveDao.class, "saveConcessionReceive", map);
	}
	//修改
	@Override
	public void updateConcessionReceive(Map<String, Object> map){
		this.insert(ConcessionReceiveDao.class, "updateConcessionReceive", map);
	}
}