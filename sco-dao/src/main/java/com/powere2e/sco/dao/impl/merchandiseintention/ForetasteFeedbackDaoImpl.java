package com.powere2e.sco.dao.impl.merchandiseintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseintention.ForetasteFeedbackDao;
import com.powere2e.sco.model.merchandiseintention.ForetasteFeedback;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;

/**
 * 商品意向品DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class ForetasteFeedbackDaoImpl extends DaoImpl implements ForetasteFeedbackDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8474257320189804458L;

	/* ======================试吃反馈============================================== */
	// 试吃反馈查询
	@Override
	public List<ForetasteFeedback> listForetasteFeedback(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(ForetasteFeedbackDao.class, "listForetasteFeedback", map, pageInfo);
	}

	// 加载一个试吃反馈
	@Override
	public ForetasteFeedback loadForetasteFeedback(Map<String, Object> map) throws Exception {
		return (ForetasteFeedback) this.get(ForetasteFeedbackDao.class, "loadForetasteFeedback", map);
	}

	// 添加试吃反馈
	@Override
	public void insertForetasteFeedback(Map<String, Object> map) throws Exception {
		this.insert(ForetasteFeedbackDao.class, "saveForetasteFeedback", map);
	}

	// 删除试吃反馈
	@Override
	public void deleteForetasteFeedback(Map<String, Object> map) throws Exception {
		this.delete(ForetasteFeedbackDao.class, "deleteForetasteFeedback", map);
	}

	// 修改试吃反馈
	@Override
	public void updateForetasteFeedback(Map<String, Object> map) throws Exception {
		this.update(ForetasteFeedbackDao.class, "updateForetasteFeedback", map);
	}

	//修改试吃反馈是否通过
	@Override
	public void updateForetasteFeedbackIsPass(Map<String, Object> map) throws Exception {
		this.update(ForetasteFeedbackDao.class, "updateForetasteFeedbackIsPass", map);
	}

	//根据意向品编号和供应商编号查询是否做了OA新品引进或者OA新品快速引进
	@Override
	public List<ApplicationMerchandise> queryIsOaApplicationNew(Map<String, Object> map) throws Exception {
		return this.query(ForetasteFeedbackDao.class, "queryIsOaApplicationNew", map, null);
	}

}