package com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustprice;

/**
 * 申请报告(快速调价)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationFastadjustpriceService extends Service {

	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseFastadjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(快速调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回申请报告(快速调价)列表
	 */
	public List<ApplicationFastadjustprice> listApplicationFastadjustprice(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(快速调价)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationFastadjustprice loadApplicationFastadjustprice(String applicationCode) throws Exception;

	/**
	 * 添加申请报告(快速调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationFastadjustprice(ApplicationFastadjustprice applicationFastadjustprice) throws Exception;

	/**
	 * 删除申请报告(快速调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的申请报告(快速调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationFastadjustprice(String applicationCode) throws Exception;

	/**
	 * 查询申请文件的商品
	 */
	public List<ApplicationFastadjustprice> listApplicationFileMerchandise(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 新增申请文件
	 */
	public String completeInsertOrUpdateFastadjustprice(File uploadFile, Map<String, Object> map) throws Exception;

	/**
	 * 查询申请文件
	 */
	public List<ApplicationFastadjustprice> listApplicationFiles(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据申请单号删除申请报告(快速调价)
	 */
	public void completeDeleteFastadjustpriceByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据报告编号删除申请文件
	 */
	public String deleteApplicationFiles(Map<String, Object> map) throws Exception;
	/**
	 * 导出申请报告(快速调价)
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);
}