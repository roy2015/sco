package com.powere2e.sco.interfaces.service.masterdata;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.security.model.Option;

/**
 * 商品角色查询 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public interface MerchandiseRoleService extends Service {

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

//	/**
//	 * 添加定量角色
//	 *
//	 * @param map
//	 *				
//	 */
//	public void insertQuantify(Map<String, Object> map);
//	/**
//	 * 删除定量角色
//	 *
//	 * @param map 
//	 *				必须参数id为要删除的原料行情预警设置id号，可以为多个id,用逗号隔开，如'1','2'
//	 */
//	public void deleteQuantify(String roleCode);
	/**
	 * 修改商品角色
	 *
	 * @param map
	 *            必须参数id为要修改商品角色的id号，不能为数组
	 */
	public void updateMerchandise(Merchandise merchandise);

	/**
	 * 导入商品角色
	 * 
	 * @param file
	 * @return
	 */
	public String completeImportMerchandise(File file);
	/**
	 * 商品定性角色查询
	 * 
	 * @return 商品定性角色列表
	 */
	public List<String> listMerchandiseDxRoleStorageForm();
}