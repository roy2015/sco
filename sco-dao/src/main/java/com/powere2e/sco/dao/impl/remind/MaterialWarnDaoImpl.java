package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.remind.MaterialWarnDao;
import com.powere2e.sco.model.remind.MaterialWarn;

/**
 * 原料预警记录DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月22日
 */
public class MaterialWarnDaoImpl extends DaoImpl implements MaterialWarnDao{

	private static final long serialVersionUID = 4221091933319958289L;

	@Override
	public List<MaterialWarn> listMaterialWarn(Map<String, Object> map) {
		return query(MaterialWarnDao.class, "listMaterialWarn", map, null);
	}
	
	@Override
	public void insertMaterialWarn() {
		this.insert(MaterialWarnDao.class, "insertMaterialWarn", null);
	}

}
