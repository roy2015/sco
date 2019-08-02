package com.powere2e.sco.service.impl.parameterset.qlremindconfig;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.parameterset.qlremindconfig.QlRemindConfigDao;
import com.powere2e.sco.interfaces.service.parameterset.qlremindconfig.QlRemindConfigService;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
/**
 * 签量提醒设置业务类的实现
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public class QlRemindConfigServiceImpl extends ServiceImpl implements QlRemindConfigService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1591333938225259053L;
	private QlRemindConfigDao qlRemindConfigDao;
	public static QlRemindConfigService getInstance(){
		return (QlRemindConfigService)ConfigFactory.getInstance().getBean("qlRemindConfigService");
	}
	//获得签量提醒设置DAO实例
	public QlRemindConfigDao getQlRemindConfigDao() {
		return qlRemindConfigDao;
	}
	//设置签量提醒设置DAO实例
	public void setQlRemindConfigDao(QlRemindConfigDao qlRemindConfigDao) {
		this.qlRemindConfigDao = qlRemindConfigDao;
	}
	//查询
	@Override
	public List<QlRemindConfig> listQlRemindConfig(Map<String, Object> map,PageInfo pageInfo){
		return this.getQlRemindConfigDao().listQlRemindConfig(map,pageInfo);
	}
	//添加
	@Override
	public void insertQlRemindConfig(QlRemindConfig qlRemindConfig){
		this.getQlRemindConfigDao().insertQlRemindConfig(qlRemindConfig.toMap());
	}
	//删除
	@Override
	public void deleteQlRemindConfig(String configCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode.split(","));
		this.getQlRemindConfigDao().deleteQlRemindConfig(map);
	}
	//修改
	@Override
	public void updateQlRemindConfig(QlRemindConfig qlRemindConfig){
		this.getQlRemindConfigDao().updateQlRemindConfig(qlRemindConfig.toMap());
	}
	//加载一个签量提醒设置
	@Override
	public QlRemindConfig loadQlRemindConfig(String configCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode);
		return this.getQlRemindConfigDao().loadQlRemindConfig(map);
	}
	//加载一个签量提醒设置
	@Override
	public QlRemindConfig loadQlRemindConfigBythresholdValue(BigDecimal thresholdValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("thresholdValue", thresholdValue);
		return this.getQlRemindConfigDao().loadQlRemindConfig(map);
	}
}