package com.powere2e.sco.service.impl.remind;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificateDao;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindDao;
import com.powere2e.sco.interfaces.dao.remind.ElseRemindFlagDao;
import com.powere2e.sco.interfaces.service.remind.ElseRemindService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate.SupplierCertificate;
import com.powere2e.sco.model.parameterset.certificateoutofdateconfig.CertificateOutofdateConfig;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
import com.powere2e.sco.model.remind.ElseRemind;
import com.powere2e.sco.model.remind.ElseRemindFlag;
import com.powere2e.sco.model.remind.RemindMerchandsieQL;
import com.powere2e.sco.service.impl.parameterset.certificateoutofdateconfig.CertificateOutofdateConfigServiceImpl;
import com.powere2e.sco.service.impl.parameterset.qlremindconfig.QlRemindConfigServiceImpl;

/**
 * 其他提醒ServiceImpl实现类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemindServiceImpl extends ServiceImpl implements ElseRemindService {

	private static final long serialVersionUID = -594524439431127672L;
	private ElseRemindDao elseRemindDao;
	private ElseRemindFlagDao elseRemindFlagDao;
	private SupplierCertificateDao supplierCertificateDao;
	
	public ElseRemindDao getElseRemindDao() {
		return elseRemindDao;
	}

	public void setElseRemindDao(ElseRemindDao elseRemindDao) {
		this.elseRemindDao = elseRemindDao;
	}
	
	public SupplierCertificateDao getSupplierCertificateDao() {
		return supplierCertificateDao;
	}

	public void setSupplierCertificateDao(SupplierCertificateDao supplierCertificateDao) {
		this.supplierCertificateDao = supplierCertificateDao;
	}
	
	public ElseRemindFlagDao getElseRemindFlagDao() {
		return elseRemindFlagDao;
	}

	public void setElseRemindFlagDao(ElseRemindFlagDao elseRemindFlagDao) {
		this.elseRemindFlagDao = elseRemindFlagDao;
	}

	@Override
	public List<ElseRemind> listElseRemind(Map<String, Object> map, PageInfo pageInfo) {
		return this.elseRemindDao.listElseRemind(map, pageInfo);
	}

	@Override
	public void insertElseRemindByDay() {
		try {
			this.insertQLWCTXRemind(); //添加签量完成提醒(首次符合或者超过给予提醒,否则不提醒)
			this.insertTJQLWHTXRemind();//添加调价签量维护提醒
			this.insertZJLQTXRemind();//添加证件临期提醒(首次只有刚好符合才给予提醒,否则不提醒)
			this.insertZJGQTXRemind();//添加证件过期提醒(首次只有刚好符合才给予提醒,否则不提醒)
			LoggerUtil.logger.info("首页的其他提醒的每天定时任务执行!");
		} catch (Exception e) {
			LoggerUtil.logger.error("首页的其他提醒的每天定时任务出现异常", e);
		}
	}

	@Override
	public void insertElseRemindByMonth() {
		try {
			this.elseRemindDao.insertTHBRemind();//添加原料同环比预警提醒
			this.elseRemindDao.insertMerchandiseWarnRemind();//添加商品价格预警提醒
			this.elseRemindDao.insertAccessoryWarnRemind();//添加辅料意向品价格预警提醒
			LoggerUtil.logger.info("首页的其他提醒的每月定时任务执行!");
		} catch (Exception e) {
			LoggerUtil.logger.error("首页的其他提醒的每月定时任务出现异常", e);
		}
	}
	
	public void insertTJQLWHTXRemind(){
		this.elseRemindDao.insertTJQLWHTXRemind();
	}
	
	/**
	 * 添加签量完成提醒
	 */
	public void insertQLWCTXRemind(){
		List<RemindMerchandsieQL> list = this.elseRemindDao.listMerchandiseHashQL(null, null);
		List<RemindMerchandsieQL> result = new ArrayList<RemindMerchandsieQL>();
		List<QlRemindConfig> configs = QlRemindConfigServiceImpl.getInstance().listQlRemindConfig(null, null);
		List<ElseRemindFlag> flags = this.elseRemindFlagDao.listElseRemindFlag(null,null);
		boolean isPass = false;
		boolean isRead = false;
		QlRemindConfig ql = new QlRemindConfig();
		
		for (RemindMerchandsieQL temp : list) {
			isPass = false;
			isRead = false;
			if (temp == null) {
				 continue;
			}
			//判读集合中的该对象是否超过签量设置的任何一个阀值
			for (QlRemindConfig qlRemindConfig : configs) {
				if (Double.valueOf(temp.getFinishPercent() == null?"0":temp.getFinishPercent().toString())<Double.valueOf(qlRemindConfig.getThresholdValue() == null?"0":qlRemindConfig.getThresholdValue().toString())) {
					if (isPass) {
						continue;
					}
					isPass = false;
				}else{
					//把超过的设置存起来
					if (Double.valueOf(ql.getThresholdValue() == null?"0":ql.getThresholdValue().toString())<Double.valueOf(qlRemindConfig.getThresholdValue()==null?"0":qlRemindConfig.getThresholdValue().toString())) {
						ql = qlRemindConfig;
					}
					isPass = true;
				}
			}
			temp.setConfigCode(ql.getConfigCode());
			//判读该集合总的对象是否在已阅清除的列表中
			for (ElseRemindFlag elseRemindFlag : flags) {
				if ((temp.getQlCode()==null?"":temp.getQlCode()).equals(elseRemindFlag.getQlCode())&&(temp.getConfigCode()==null?"":temp.getConfigCode()).equals(elseRemindFlag.getConfigCode())) {
					isRead = true;
				}else{
					if (isRead) {
						continue;
					}
					isRead = false;
				}
			}
			if (isPass == true && isRead == false) {
				result.add(temp);
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (result.size()>0) {
			map.put("list", result);
			this.elseRemindDao.insertQLWCTXRemind(map);
		}
		
	}
	
	/**
	 * 添加证件临期提醒
	 * @return
	 */
	public void insertZJLQTXRemind(){
		List<SupplierCertificate> lists = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SupplierCertificate> certificates = this.supplierCertificateDao.listSupplierCertificate(new HashMap<String, Object>(), null);
		List<CertificateOutofdateConfig> certificateOutofdateConfigs = CertificateOutofdateConfigServiceImpl.getInstance().listCertificateOutofdateConfig(null, null);
		List<Integer> outDays = new ArrayList<Integer>();
		for (CertificateOutofdateConfig certificateOutofdateConfig : certificateOutofdateConfigs) {
			outDays.add(certificateOutofdateConfig.getOutofdate());
		}
		//筛选有有效期的证件
		for (SupplierCertificate temp : certificates) {
			if (temp.getStartDate() != null&&temp.getEndDate() != null) {
				//查询签量
				Integer dayDiff = (int)getDayDiff(new Date(), temp.getEndDate());
				if (outDays.contains(dayDiff)) {
					lists.add(temp);
				}
			}
		}
		if (lists.size() > 0) {
			map.put("list", lists);
			this.elseRemindDao.insertZJLQTXRemind(map);
		}
	}
	
	/**
	 * 添加证件过期提醒
	 * @return
	 */
	public void insertZJGQTXRemind(){
		List<SupplierCertificate> lists = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<SupplierCertificate> certificates = this.supplierCertificateDao.listSupplierCertificate(new HashMap<String, Object>(), null);
		//筛选有有效期的证件
		for (SupplierCertificate temp : certificates) {
			if (temp.getStartDate() != null&&temp.getEndDate() != null) {
				if (getDayDiff(new Date(), temp.getEndDate())==-1) {
					lists.add(temp);
				}
			}
		}
		if (lists.size() > 0) {
			map.put("list", lists);
			this.elseRemindDao.insertZJGQTXRemind(map);
		}
	}
	
	/**
	 * 获取两个时间的天差
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 天差
	 */
	public Integer getDayDiff(Date startDate,Date endDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Long value = 0L;
        try {
    		long to = 0;  
            to = sdf.parse(sdf.format(endDate)).getTime();
            long from = 0;  
			from = sdf.parse(sdf.format(startDate)).getTime();
            value = (to - from)/(1000 * 60 * 60 * 24);
            } catch (Exception e) {
				value = null;
			}
        return value==null?null:Integer.parseInt(value.toString());  
	}

}
