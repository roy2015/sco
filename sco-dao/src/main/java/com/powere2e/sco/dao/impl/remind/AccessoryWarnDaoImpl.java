package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.remind.AccessoryWarnDao;
import com.powere2e.sco.model.remind.AccessoryWarn;

/**
 * 辅料商品预警记录DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public class AccessoryWarnDaoImpl extends DaoImpl implements AccessoryWarnDao {

	private static final long serialVersionUID = -7212183655902389182L;

	@Override
	public List<AccessoryWarn> listAccessoryWarn(Map<String, Object> map) {
		return query(AccessoryWarnDao.class, "listAccessoryWarn", map, null);
	}

	@Override
	public void insertAccessoryWarn() {
		this.insert(AccessoryWarnDao.class, "insertAccessoryWarn", null);
	}

}
