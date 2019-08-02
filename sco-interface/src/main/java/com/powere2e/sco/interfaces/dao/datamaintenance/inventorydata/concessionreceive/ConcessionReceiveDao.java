package com.powere2e.sco.interfaces.dao.datamaintenance.inventorydata.concessionreceive;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive.ConcessionReceive;
/**
 * 让步接收DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public interface ConcessionReceiveDao extends Dao {
	/**
	 * 让步接收查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回让步接收列表
	 */
	public List<ConcessionReceive> listConcessionReceive(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加让步接收
	 *
	 * @param map
	 *				
	 */
	public void insertConcessionReceive(Map<String, Object> map);
	/**
	 * 修改让步接收
	 *
	 * @param map
	 *				
	 */
	public void updateConcessionReceive(Map<String, Object> map);
}