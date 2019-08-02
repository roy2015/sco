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
import com.powere2e.sco.interfaces.dao.accessoryintention.AccessoryEnquiryDao;
import com.powere2e.sco.interfaces.service.accessoryintention.AccessoryEnquiryService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
/**
 * 询价单业务类的实现
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryServiceImpl extends ServiceImpl implements AccessoryEnquiryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6417086234288322657L;
	private AccessoryEnquiryDao accessoryEnquiryDao;
	public static AccessoryEnquiryService getInstance(){
		return (AccessoryEnquiryService)ConfigFactory.getInstance().getBean("accessoryEnquiryService");
	}
	//获得询价单DAO实例
	public AccessoryEnquiryDao getAccessoryEnquiryDao() {
		return accessoryEnquiryDao;
	}
	//设置询价单DAO实例
	public void setAccessoryEnquiryDao(AccessoryEnquiryDao accessoryEnquiryDao) {
		this.accessoryEnquiryDao = accessoryEnquiryDao;
	}
	//查询
	@Override
	public List<AccessoryEnquiry> listAccessoryEnquiry(Map<String, Object> map,PageInfo pageInfo){
		return this.getAccessoryEnquiryDao().listAccessoryEnquiry(map,pageInfo);
	}
	//添加
	@Override
	public void insertAccessoryEnquiry(AccessoryEnquiry accessoryEnquiry){
		this.getAccessoryEnquiryDao().insertAccessoryEnquiry(accessoryEnquiry.toMap());
	}
	//删除
	@Override
	public void deleteAccessoryEnquiry(String enquiryCode){
		if(StringUtils.isNotBlank(enquiryCode)){
		AccessoryEnquiry accessoryEnquiry=AccessoryEnquiryServiceImpl.getInstance().loadAccessoryEnquiry(enquiryCode);
		String path=accessoryEnquiry.getAttachment()==null?"":accessoryEnquiry.getAttachment();
		path = ConfigPath.getUploadFilePath().concat(path);
		File file = new File(path);
		if(file.exists()) {
			file.delete();
			LoggerUtil.logger.error("AccessoryEnquiryServiceImpl.deleteAccessoryEnquiry删除文件["+ file.getPath() +"]");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		this.getAccessoryEnquiryDao().deleteAccessoryEnquiry(map);
		}
	}
	//修改
	@Override
	public void updateAccessoryEnquiry(AccessoryEnquiry accessoryEnquiry){
		this.getAccessoryEnquiryDao().updateAccessoryEnquiry(accessoryEnquiry.toMap());
	}
	//加载一个询价单
	@Override
	public AccessoryEnquiry loadAccessoryEnquiry(String enquiryCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("enquiryCode", enquiryCode);
		return this.getAccessoryEnquiryDao().loadAccessoryEnquiry(map);
	}
}