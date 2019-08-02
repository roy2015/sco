package com.powere2e.sco.service.impl.merchandiseintention;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.interfaces.dao.merchandiseintention.ForetasteFeedbackDao;
import com.powere2e.sco.interfaces.service.merchandiseintention.ForetasteFeedbackService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.model.merchandiseintention.ForetasteFeedback;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;

/**
 * 商品意向品业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class ForetasteFeedbackServiceImpl extends ServiceImpl implements ForetasteFeedbackService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1898963804653668435L;
	private ForetasteFeedbackDao foretasteFeedbackDao;

	public static MerchandiseIntentionService getInstance() {
		return (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionManager");
	}

	/* ======================试吃反馈============================================== */
	// 试吃反馈查询
	@Override
	public List<ForetasteFeedback> listForetasteFeedback(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return foretasteFeedbackDao.listForetasteFeedback(map, pageInfo);
	}

	// 加载一个试吃反馈
	@Override
	public ForetasteFeedback loadForetasteFeedback(Map<String, Object> map) throws Exception {
		return foretasteFeedbackDao.loadForetasteFeedback(map);
	}

	// 添加试吃反馈
	@Override
	public void insertForetasteFeedback(Map<String, Object> map) throws Exception {
		foretasteFeedbackDao.insertForetasteFeedback(map);
	}

    @Override
    public void pushInsertForetasteFeedbackToHBXT(Map<String, Object> map) throws Exception {

    }

    // 删除试吃反馈
	@Override
	public void deleteForetasteFeedback(Map<String, Object> map) throws Exception {
		foretasteFeedbackDao.deleteForetasteFeedback(map);
	}

    @Override
    public void pushDeleteForetasteFeedback(Map<String, Object> map) throws Exception {

    }

    // 修改试吃反馈
	@Override
	public void updateForetasteFeedback(Map<String, Object> map) throws Exception {
		foretasteFeedbackDao.updateForetasteFeedback(map);
	}

    @Override
    public void pushUpdateForetasteFeedback(Map<String, Object> map) throws Exception {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = builder.create();
        logger.info(gson.toJson(map));
        //todo

    }

    public ForetasteFeedbackDao getForetasteFeedbackDao() {
		return foretasteFeedbackDao;
	}

	public void setForetasteFeedbackDao(ForetasteFeedbackDao foretasteFeedbackDao) {
		this.foretasteFeedbackDao = foretasteFeedbackDao;
	}

	// 修改试吃反馈是否通过
	@Override
	public void updateForetasteFeedbackIsPass(String intentionAndSupplierCodes, String isForetastePass) throws Exception {
		List<ApplicationMerchandise> list = MerchandiseOaApplicationUtil
				.getIntentionAndSupplierCodeGroupList(intentionAndSupplierCodes);
		if (list != null && list.size() > 0) {
			for(int i=0;i<list.size();i++){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("intentionCode", list.get(i).getMerchandiseCode());
				map.put("intentionSupplierCode", list.get(i).getSupplierCode());
				if("Y".equals(isForetastePass)){
					map.put("isForetastePass", "是");
					//如果为点击试吃通过，则设置更新通过日期为当前日期
					map.put("foretastePassDate", new Date());
				}else{
					//如果为点击取消试吃通过，则设置更新通过日期为空
					map.put("isForetastePass", "否");
					map.put("foretastePassDate", null);
				}
				this.foretasteFeedbackDao.updateForetasteFeedbackIsPass(map);
			}
		}
	}

	//根据意向品编号和供应商编号查询是否做了OA新品引进或者OA新品快速引进
	@Override
	public List<ApplicationMerchandise> queryIsOaApplicationNew(Map<String, Object> map) throws Exception {
		return foretasteFeedbackDao.queryIsOaApplicationNew(map);
	}

}