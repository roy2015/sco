package com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualified;
/**
 * 商品抽检不合格记录Service接口
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public interface MerchandiseUnqualifiedService extends Service {
	
	/**
	 * 商品抽检不合格记录查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品抽检不合格记录列表
	 */
	public List<MerchandiseUnqualified> listMerchandiseUnqualified(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加商品抽检不合格记录
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseUnqualified(MerchandiseUnqualified merchandiseUnqualified);
	/**
	 * 添加Excel表中的商品抽检不合格记录
	 *
	 * @param map
	 *				
	 */
	public String completeImportMerchandiseUnqualified(File file);

}