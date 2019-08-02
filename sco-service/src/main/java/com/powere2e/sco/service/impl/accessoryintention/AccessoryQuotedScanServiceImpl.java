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
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryQuotedScanDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryQuotedScanService;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedScan;
/**
 * 辅料报价单-扫描版业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedScanServiceImpl extends ServiceImpl implements AccessoryQuotedScanService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5336655600428395239L;
	private AccessoryQuotedScanDao accessoryQuotedScanDao;
	public static AccessoryQuotedScanService getInstance(){
		return (AccessoryQuotedScanService)ConfigFactory.getInstance().getBean("accessoryQuotedScanService");
	}
	//获得辅料报价单-扫描版DAO实例
	public AccessoryQuotedScanDao getAccessoryQuotedScanDao() {
		return accessoryQuotedScanDao;
	}
	//设置辅料报价单-扫描版DAO实例
	public void setAccessoryQuotedScanDao(AccessoryQuotedScanDao accessoryQuotedScanDao) {
		this.accessoryQuotedScanDao = accessoryQuotedScanDao;
	}
	//查询
	@Override
	public List<AccessoryQuotedScan> listAccessoryQuotedScan(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryQuotedScanDao().listAccessoryQuotedScan(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryQuotedScan(AccessoryQuotedScan accessoryQuotedScan){
		this.getAccessoryQuotedScanDao().insertAccessoryQuotedScan(accessoryQuotedScan.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryQuotedScan(String quotedCode){
		if(StringUtils.isNotBlank(quotedCode)){
		AccessoryQuotedScan accessoryQuotedScan=AccessoryQuotedScanServiceImpl.getInstance().loadAccessoryQuotedScan(quotedCode);
		String path=accessoryQuotedScan.getPath()==null?"":accessoryQuotedScan.getPath();
		path = ConfigPath.getUploadFilePath().concat(path);
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			LoggerUtil.logger.error("AccessoryQuotedScanServiceImpl.deleteAccessoryQuotedScan删除文件["+ file.getPath() +"]");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		this.getAccessoryQuotedScanDao().deleteAccessoryQuotedScan(map);
		}
	}
	//修改
	@Override
	public void updateAccessoryQuotedScan(AccessoryQuotedScan accessoryQuotedScan){
		this.getAccessoryQuotedScanDao().updateAccessoryQuotedScan(accessoryQuotedScan.toMap());
	}
	//加载一个辅料报价单-扫描版
	@Override
	public AccessoryQuotedScan loadAccessoryQuotedScan(String quotedCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("quotedCode", quotedCode);
		return this.getAccessoryQuotedScanDao().loadAccessoryQuotedScan(map);
	}
}