package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindDao;
import com.powere2e.sco.model.remind.ElseRemind;
import com.powere2e.sco.model.remind.RemindMerchandsieQL;

/**
 * 其他提醒DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemindDaoImpl extends DaoImpl implements ElseRemindDao {

	private static final long serialVersionUID = -2015397316957704975L;

	@Override
	public List<ElseRemind> listElseRemind(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(ElseRemindDao.class, "listElseRemind", map, pageInfo);
	}
	
	@Override
	public List<RemindMerchandsieQL> listMerchandiseHashQL(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(ElseRemindDao.class, "listMerchandiseHashQL", map, pageInfo);
	}
	
	@Override
	public void deleteElseRemindByMonth() {
		this.delete(ElseRemindDao.class, "deleteElseRemindByMonth", null);
	}

	@Override
	public void deleteElseRemindByDay() {
		this.delete(ElseRemindDao.class, "deleteElseRemindByDay", null);
	}

	@Override
	public void deleteElseRemind() {
		this.delete(ElseRemindDao.class, "deleteElseRemind", null);
	}
	
	@Override
	public void insertZJLQTXRemind(Map<String, Object> map) {
		this.insert(ElseRemindDao.class, "insertZJLQTXRemind", map);
	}
	
	@Override
	public void insertZJGQTXRemind(Map<String, Object> map) {
		this.insert(ElseRemindDao.class, "insertZJGQTXRemind", map);
	}
	
	@Override
	public void insertTHBRemind() {
		this.insert(ElseRemindDao.class, "insertTHBRemind", null);
	}
	
	@Override
	public void insertMerchandiseWarnRemind() {
		this.insert(ElseRemindDao.class, "insertMerchandiseWarnRemind", null);
	}
	
	@Override
	public void insertAccessoryWarnRemind() {
		this.insert(ElseRemindDao.class, "insertAccessoryWarnRemind", null);
	}
	
	@Override
	public void insertQLWCTXRemind(Map<String, Object> map) {
		this.insert(ElseRemindDao.class, "insertQLWCTXRemind", map);
	}

	@Override
	public void insertTJQLWHTXRemind() {
		this.insert(ElseRemindDao.class, "insertTJQLWHTXRemind", null);
	}

}
