package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;

/**
 * 供应商附件DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public class SupplierAttachmentMDaoImpl extends DaoImpl implements SupplierAttachmentMDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8031391594046983178L;

	// 查询
	@Override
	public List<SupplierAttachmentM> listSupplierAttachmentM(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierAttachmentMDao.class, "searchSupplierAttachmentM", map, pageInfo);
	}

	// 查询
	@Override
	public List<SupplierAttachmentM> listSupplierAttachmentMInFrom(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierAttachmentMDao.class, "searchSupplierAttachmentMInFrom", map, pageInfo);
	}

	// 查询
	@Override
	public List<ApplicationLackFileM> listApplicationLackFileM(Map<String, Object> map) {
		return this.query(SupplierAttachmentMDao.class, "searchAttachmentLackInfo", map, null);
	}

	// 查询
	@Override
	public List<ApplicationLackFileM> listApplicationInfo(Map<String, Object> map) {
		return this.query(SupplierAttachmentMDao.class, "searchApplicationInfo", map, null);
	}

	// 装载一个供应商附件
	@Override
	public List<SupplierAttachmentM> loadSupplierAttachmentM(Map<String, Object> map) {
		return this.query(SupplierAttachmentMDao.class, "loadSupplierAttachmentM", map, null);
	}

	/**
	 * 添加商品角色
	 */
	@Override
	public void insertSupplierAttachmentM(Map<String, Object> map) {
		this.insert(SupplierAttachmentMDao.class, "saveSupplierAttachmentM", map);
	}

	@Override
	public List<SupplierAttachmentM> listIntentionSupplier(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(SupplierAttachmentMDao.class, "searchIntentionSupplier", map, pageInfo);
	}

	@Override
	public List<SupplierAttachmentM> listMerchandiseSupplier(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(SupplierAttachmentMDao.class, "searchMerchandiseSupplier", map, pageInfo);
	}

	@Override
	public void deleteSupplierAttachmentMs(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.delete(SupplierAttachmentMDao.class, "deleteSupplierAttachmentMs", map);
	}

	@Override
	public void deleteSupplierAttachmentM(Map<String, Object> map) {
		// TODO Auto-generated method stub
		this.delete(SupplierAttachmentMDao.class, "deleteSupplierAttachmentM", map);
	}

	// 根据申请单号删除供应商附件(新品引进)
	@Override
	public void deleteSupplierAttachmentByCode(Map<String, Object> map) {
		this.delete(SupplierAttachmentMDao.class, "deleteSupplierAttachmentByCode", map);
	}
}