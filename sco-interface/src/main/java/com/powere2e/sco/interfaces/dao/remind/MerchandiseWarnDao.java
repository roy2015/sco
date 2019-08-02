package com.powere2e.sco.interfaces.dao.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.remind.MerchandiseWarn;

/**
 * 商品预警Dao接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public interface MerchandiseWarnDao extends Dao{
	
	/**
	 * 查询商品预警记录
	 * @param map 查询参数
	 * @return 商品预警记录集合
	 */
	public List<MerchandiseWarn> listMerchandiseWarn(Map<String, Object> map);
	/**
	 * 添加商品预警记录(定时任务 每月1日)
	 */
	public void insertMerchandiseWarn();
}
