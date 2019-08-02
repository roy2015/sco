package com.powere2e.sco.dao.impl.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.remind.MerchandiseWarnDao;
import com.powere2e.sco.model.remind.MerchandiseWarn;

/**
 * 商品预警记录DaoImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public class MerchandiseWarnDaoImpl extends DaoImpl implements MerchandiseWarnDao {

	private static final long serialVersionUID = 2404371612014501556L;

	@Override
	public List<MerchandiseWarn> listMerchandiseWarn(Map<String, Object> map) {
		return this.query(MerchandiseWarnDao.class, "listMerchandiseWarn", map, null);
	}

	@Override
	public void insertMerchandiseWarn() {
		this.insert(MerchandiseWarnDao.class, "insertMaterialWarn", null);
	}

}
