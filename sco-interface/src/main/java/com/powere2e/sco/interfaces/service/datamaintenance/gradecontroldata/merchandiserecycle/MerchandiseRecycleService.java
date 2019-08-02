package com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.merchandiserecycle;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycle;
/**
 * 商品回收记录Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public interface MerchandiseRecycleService extends Service {
	/**
	 * 商品回收记录查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品回收记录列表
	 */
	public List<MerchandiseRecycle> listMerchandiseRecycle(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 
	 * 上传商品回收记录表
	 * 
	 * @param file 
	 *            上传文件
	 * @return
	 *            导入消息
	 */         
	public String completeImportConcessionReceiveData(File file);
}