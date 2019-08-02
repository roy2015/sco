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
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedElectronicDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedElectronicService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
/**
 * 辅料报价单-电子版业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElectronicServiceImpl extends ServiceImpl implements AccessoryQuotedElectronicService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1086340981067762864L;
	private AccessoryQuotedElectronicDao accessoryQuotedElectronicDao;
	public static AccessoryQuotedElectronicService getInstance(){
		return (AccessoryQuotedElectronicService)ConfigFactory.getInstance().getBean("accessoryQuotedElectronicService");
	}
	//获得辅料报价单-电子版DAO实例
	public AccessoryQuotedElectronicDao getAccessoryQuotedElectronicDao() {
		return accessoryQuotedElectronicDao;
	}
	//设置辅料报价单-电子版DAO实例
	public void setAccessoryQuotedElectronicDao(AccessoryQuotedElectronicDao accessoryQuotedElectronicDao) {
		this.accessoryQuotedElectronicDao = accessoryQuotedElectronicDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedElectronic> listAccessoryQuotedElectronic(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedElectronicDao().listAccessoryQuotedElectronic(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedElectronic(AccessoryQuotedElectronic accessoryQuotedElectronic){
		this.getAccessoryQuotedElectronicDao().insertAccessoryQuotedElectronic(accessoryQuotedElectronic.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedElectronic(String quotedCode){
		if(StringUtils.isNotBlank(quotedCode)){
		AccessoryQuotedElectronic accessoryQuotedElectronic=new AccessoryQuotedElectronic();
		accessoryQuotedElectronic=AccessoryQuotedElectronicServiceImpl.getInstance().loadAccessoryQuotedElectronicFor(quotedCode);
		/*AccessoryQuotedElectronic accessoryQuotedElectronic = new AccessoryQuotedElectronic();
		accessoryQuotedElectronic = accessoryQuotedElectronicService.loadAccessoryQuotedElectronic(quotedCode);*/
		String path=accessoryQuotedElectronic.getPath()==null?"":accessoryQuotedElectronic.getPath();
		path = ConfigPath.getUploadFilePath().concat(path);
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			LoggerUtil.logger.error("AccessoryQuotedElectronicServiceImpl.deleteAccessoryQuotedElectronic删除文件["+ file.getPath() +"]");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedElectronicDao().deleteAccessoryQuotedElectronic(map);
		}
	}
	//修改
	@Override
	public void updateAccessoryQuotedElectronic(AccessoryQuotedElectronic accessoryQuotedElectronic){
		this.getAccessoryQuotedElectronicDao().updateAccessoryQuotedElectronic(accessoryQuotedElectronic.toMap());
	}
	//加载一个辅料报价单-电子版
	@Override
	public AccessoryQuotedElectronic loadAccessoryQuotedElectronic(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return this.getAccessoryQuotedElectronicDao().loadAccessoryQuotedElectronic(map);
	}
	//加载一个辅料报价单-电子版
		@Override
		public AccessoryQuotedElectronic loadAccessoryQuotedElectronicFor(String quotedCode) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("quotedCode", quotedCode);
			return this.getAccessoryQuotedElectronicDao().loadAccessoryQuotedElectronicFor(map);
		}
}