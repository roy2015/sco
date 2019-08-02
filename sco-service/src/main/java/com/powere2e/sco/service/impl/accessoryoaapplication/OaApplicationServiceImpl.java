package com.powere2e.sco.service.impl.accessoryoaapplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.OaApplicationDao;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.OaApplicationService;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;

/**
 * 商品OA申请单业务类的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class OaApplicationServiceImpl extends ServiceImpl implements OaApplicationService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7976041194940506376L;
	private OaApplicationDao oaApplicationDao;

	public static OaApplicationService getInstance() {
		return (OaApplicationService) ConfigFactory.getInstance().getBean("oaApplicationService");
	}

	// 获得商品OA申请单DAO实例
	public OaApplicationDao getOaApplicationDao() {
		return oaApplicationDao;
	}

	// 设置商品OA申请单DAO实例
	public void setOaApplicationDao(OaApplicationDao oaApplicationDao) {
		this.oaApplicationDao = oaApplicationDao;
	}

	// 查询
	@Override
	public List<OaApplication> listOaApplication(Map<String, Object> map, PageInfo pageInfo) {
		return this.getOaApplicationDao().listOaApplication(map, pageInfo);
	}

	// 添加
	@Override
	public void insertOaApplication(OaApplication oaApplication) {
		this.getOaApplicationDao().insertOaApplication(oaApplication.toMap());
	}

	// 删除
	@Override
	public void deleteOaApplication(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		this.getOaApplicationDao().deleteOaApplication(map);
	}

	// 修改
	@Override
	public Integer updateOaApplication(OaApplication oaApplication) {
		return this.getOaApplicationDao().updateOaApplication(oaApplication.toMap());
	}

	// 修改
	@Override
	public void updateOaApplicationForUndo(OaApplication oaApplication) {
		this.getOaApplicationDao().updateOaApplicationForUndo(oaApplication.toMap());
	}

	// 修改
	@Override
	public void updateOaApplicationForAllow(OaApplication oaApplication) {
		this.getOaApplicationDao().updateOaApplicationForAllow(oaApplication.toMap());
	}

	// 加载一个商品OA申请单
	@Override
	public OaApplication loadOaApplication(String applicationCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("applicationCode", applicationCode);
		return this.getOaApplicationDao().loadOaApplication(map);
	}
}