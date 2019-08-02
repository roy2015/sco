package com.powere2e.sco.dao.impl.merchandiseoaapplication.fastimportoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImportDao;
import com.powere2e.sco.model.merchandiseoaapplication.fastimportoaapplication.ApplicationFastImport;

/**
 * 商品快速引进 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class ApplicationFastImportDaoImpl extends DaoImpl implements
		ApplicationFastImportDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3002410345462321817L;

	@Override
	public List<String> listApplicationMerStatus(Map<String, Object> map) {
		return this.query(ApplicationFastImportDao.class,
				"listApplicationMerStatus", map, null);
	}

	@Override
	public List<ApplicationFastImport> listApplicationAndMer(
			Map<String, Object> map) {
		return this.query(ApplicationFastImportDao.class,
				"listApplicationAndMer", map, null);
	
	}

}