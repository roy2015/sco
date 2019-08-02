package com.powere2e.sco.service.impl.accessoryintention;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryProofingDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryProofingService;
import com.powere2e.sco.model.accessoryintention.AccessoryProofing;
/**
 * 辅料打样业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryProofingServiceImpl extends ServiceImpl implements AccessoryProofingService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2646600283520594338L;
	private AccessoryProofingDao accessoryProofingDao;
	public static AccessoryProofingService getInstance(){
		return (AccessoryProofingService)ConfigFactory.getInstance().getBean("accessoryProofingService");
	}
	//获得辅料打样DAO实例
	public AccessoryProofingDao getAccessoryProofingDao() {
		return accessoryProofingDao;
	}
	//设置辅料打样DAO实例
	public void setAccessoryProofingDao(AccessoryProofingDao accessoryProofingDao) {
		this.accessoryProofingDao = accessoryProofingDao;
	}
	//查询
	@Override
	public List<AccessoryProofing> listAccessoryProofing(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryProofingDao().listAccessoryProofing(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryProofing(AccessoryProofing accessoryProofing){
		this.getAccessoryProofingDao().insertAccessoryProofing(accessoryProofing.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryProofing(String proofingCode){
		if(StringUtils.isNotBlank(proofingCode)){
		AccessoryProofing accessoryProofing=AccessoryProofingServiceImpl.getInstance().loadAccessoryProofing(proofingCode);
		String path=accessoryProofing.getPath()==null?"":accessoryProofing.getPath();
		path = ConfigPath.getUploadFilePath().concat(path);
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			LoggerUtil.logger.error("AccessoryProofingServiceImpl.deleteAccessoryProofing删除文件["+ file.getPath() +"]");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proofingCode", proofingCode);
		this.getAccessoryProofingDao().deleteAccessoryProofing(map);
		}
	}
	//修改
	@Override
	public void updateAccessoryProofing(AccessoryProofing accessoryProofing){
		this.getAccessoryProofingDao().updateAccessoryProofing(accessoryProofing.toMap());
	}
	//加载一个辅料打样
	@Override
	public AccessoryProofing loadAccessoryProofing(String proofingCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proofingCode", proofingCode);
		return this.getAccessoryProofingDao().loadAccessoryProofing(map);
	}
}