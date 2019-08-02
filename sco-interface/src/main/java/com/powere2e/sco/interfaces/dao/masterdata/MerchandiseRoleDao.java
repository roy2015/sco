package com.powere2e.sco.interfaces.dao.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.security.model.Option;

/**
 * 商品角色查询 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public interface MerchandiseRoleDao extends Dao {

	/**
	 * 定性角色数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 定性角色数据列表
	 */
	public List<Option> listQualitative(Map<String, Object> map);

	/**
	 * 定量角色数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 定量角色数据列表
	 */
	public List<Option> listQuantify(Map<String, Object> map);

	/**
	 * 修改商品角色维护
	 *
	 * @param map
	 *            必须参数id为要修改商品角色的id号，不能为数组
	 */
	public void updateMerchandise(Map<String, Object> map);

	/**
	 * 添加商品角色维护
	 * 
	 * @param map
	 */
	public void insertMerchandise(Map<String, Object> map);

	/**
	 * 定性角色Id查询
	 * 
	 * @return 商品定性角色列表
	 */
	public List<String> listMerchandiseDxRoleStorageForm();

	/**
	 * 查询商品角色维护是否有该商品
	 * 
	 * @param map
	 * @return
	 */
	public Integer searchMerchandiseCount(Map<String, Object> map);

	/**
	 * 根据定性角色名字查询定性角色编号
	 * 
	 * @param map
	 * @return
	 */
	public String searchMerchandiseDxRoleName(Map<String, Object> map);
	/**
	 * 根据定量角色名字查询定量角色编号
	 * 
	 * @param map
	 * @return
	 */
	public String searchMerchandiseDlRoleName(Map<String, Object> map);

}