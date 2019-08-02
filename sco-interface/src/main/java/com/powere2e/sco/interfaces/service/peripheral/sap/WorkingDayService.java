package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;
import java.util.Date;
import java.util.Map;

public interface WorkingDayService {
	/**
	 * 工作日service接口
	 */
	public void insertWorkingDay();

	/**
	 * 工作日service接口
	 * 
	 * @param file
	 *            解析的文件
	 * @param charsetCode
	 *            解析文件的编码格式
	 * @param processCount
	 *            每读取processCount行插入一次
	 * @param fileRootPath
	 *            保存解析文件的路径(不包括BW/SAP模块名)
	 */
	public void completeInsertWorkingDay(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 删除工作日
	 */
	public void deleteWorkingDay(Map<String, Object> map);
	
	/**
	 * 查询两个日期间的工作日期天数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 之间的工作日天数
	 */
	public int searchWeekDayByDate(Date startDate, Date endDate);

}
