package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.signedquantity;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.signedquantity.SignedQuantityDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.signedquantity.SignedQuantity;

/**
 * 签量数据维护DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public class SignedQuantityDaoImpl extends DaoImpl implements SignedQuantityDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4476843157024824358L;

	@Override
	public List<SignedQuantity> listSignedQuantity(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(SignedQuantityDao.class, "listSignedQuantity", map, pageInfo);
	}

	@Override
	public void insertSignedQuantityQl(Map<String, Object> map) {
		this.insert(SignedQuantityDao.class, "insertSignedQuantityQl", map);
	}

	@Override
	public void insertSignedQuantityDetail(Map<String, Object> map) {
		this.insert(SignedQuantityDao.class, "insertSignedQuantityDetail", map);
	}

	@Override
	public SignedQuantity searchSignedQtyMain(Map<String, Object> map) {
		return (SignedQuantity) this.get(SignedQuantityDao.class, "searchSignedQtyMain", map);
	}

	@Override
	public List<SignedQuantity> listSignedQtyDetail(Map<String, Object> map) {
		return this.query(SignedQuantityDao.class, "listSignedQtyDetail", map, null);
	}

	@Override
	public void updateSignedQtyMain(Map<String, Object> map) {
		this.update(SignedQuantityDao.class, "updateSignedQtyMain", map);
	}

	@Override
	public List<String> listMerCodeAndSuppCodeByQlCode(Map<String, Object> map) {
		return this.query(SignedQuantityDao.class, "listMerchandiseCodeByQlCode", map, null);
	}

	@Override
	public String searchDelQlCode(Map<String, Object> map) {
		return (String) this.get(SignedQuantityDao.class, "searchDelQlCode", map);
	}

	@Override
	public void deleteSignedQtyMain(Map<String, Object> map) {
		this.delete(SignedQuantityDao.class, "deleteSignedQtyMain", map);
	}

	@Override
	public void deleteSignedQtyDetail(Map<String, Object> map) {
		this.delete(SignedQuantityDao.class, "deleteSignedQtyDetail", map);
	}

	@Override
	public void insOrUpdSignedQty(Map<String, Object> map) {
		this.update(SignedQuantityDao.class, "insOrUpdSignedQty", map);
	}

	@Override
	public List<SignedQuantity> listCalculateSignedQuantity(
			Map<String, Object> map) {
		return this.query(SignedQuantityDao.class, "listCalculateSignedQuantity", map, null);
	}

	@Override
	public void resetSignedQtyCalculate(Map<String, Object> map) {
		this.update(SignedQuantityDao.class, "resetSignedQtyCalculate", map);
	}
	
	@Override
	public void updateSignedQtyCalculate(Map<String, Object> map) {
		this.update(SignedQuantityDao.class, "updateSignedQtyCalculate", map);
	}

}