package com.powere2e.sco.interfaces.service.parameterset.qlremindconfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
/**
 * 签量提醒设置Service接口
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public interface QlRemindConfigService extends Service {
	/**
	 * 签量提醒设置查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回签量提醒设置列表
	 */
	public List<QlRemindConfig> listQlRemindConfig(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个签量提醒设置
	 *
	 * @param map
	 *				
	 * @return
	 */
	public QlRemindConfig loadQlRemindConfig(String configCode);
	/**
	 * 添加签量提醒设置
	 *
	 * @param map
	 *				
	 */
	public void insertQlRemindConfig(QlRemindConfig qlRemindConfig);
	/**
	 * 删除签量提醒设置
	 *
	 * @param map 
	 *				必须参数id为要删除的签量提醒设置id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteQlRemindConfig(String configCode);
	/**
	 * 修改签量提醒设置
	 *
	 * @param map 
	 *				必须参数id为要修改签量提醒设置的id号，不能为数组
	 */
	public void updateQlRemindConfig(QlRemindConfig qlRemindConfig);
	/**
	 * 
	 * 根据阀值查新签量提醒
	 * 
	 * @param thresholdValue 签量提醒的阀值
	 * @return   签量提醒
	 */
	public QlRemindConfig loadQlRemindConfigBythresholdValue(BigDecimal thresholdValue);
}