package com.powere2e.sco.interfaces.service.datamaintenance.inventorydata.concessionreceive;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive.ConcessionReceive;
/**
 * 让步接收Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public interface ConcessionReceiveService extends Service {
	/**
	 * 让步接收查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回让步接收列表
	 */
	public List<ConcessionReceive> listConcessionReceive(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 
	 * 上传让步接收数据表
	 * 
	 * @param file 
	 *            上传文件
	 * @return
	 *            导入消息
	 */         
	public String completeImportConcessionReceiveData(File file);
}