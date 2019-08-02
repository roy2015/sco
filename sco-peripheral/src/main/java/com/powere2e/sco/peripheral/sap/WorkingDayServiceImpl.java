package com.powere2e.sco.peripheral.sap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.WorkingDayDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.WorkingDayService;
import com.powere2e.sco.model.peripheral.sap.WorkingDay;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

public class WorkingDayServiceImpl extends ServiceImpl implements WorkingDayService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6124083289465057116L;
	private Logger logger = PeripheralFileUtils.logger;
	private WorkingDayDao workingDayDao;

	/**
	 * 获取WorkingDayService工作日实例
	 * 
	 * @return 工作日实例
	 */
	public static WorkingDayService getInstance() {
		return (WorkingDayService) ConfigFactory.getInstance().getBean(
				"workingDayService");
	}

	// 获得区域信息DAO实例
	public WorkingDayDao getWorkingDayDao() {
		return workingDayDao;
	}

	// 设置区域信息DAO实例
	public void setWorkingDayDao(WorkingDayDao workingDayDao) {
		this.workingDayDao = workingDayDao;
	}

	@Override
	public void insertWorkingDay() {
		String fileRootPath = PeripheralFileUtils.getProperty(PeripheralConfigFile.FILE_ROOT_PATH);// 获取根目录
		if (StringUtils.isBlank(fileRootPath)) {
			fileRootPath = PeripheralConfigFile.DEFAULT_FILE_ROOT_PATH;// 默认根目录
		}
		String charsetCode = PeripheralFileUtils.getProperty(PeripheralConfigFile.FILE_CHARSET_CODE);// 获取编码格式
		if (StringUtils.isBlank(charsetCode)) {
			charsetCode = PeripheralConfigFile.DEFAULT_FILE_CHARSET_CODE;// 默认编码格式
		}
		String processLineCount = PeripheralFileUtils.getProperty(PeripheralConfigFile.PROCESS_LINE_COUNT);// 获取处理行数
		int processCount = 0;
		try {
			processCount = Integer.parseInt(processLineCount);
		} catch (NumberFormatException e) {
			processCount = PeripheralConfigFile.DEFAULT_PROCESS_LINE_COUNT;// 默认处理行数
		}
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.WORKINGDAY));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertWorkingDay(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertWorkingDay(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.WORKINGDAY);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		WorkingDay workingDay = new WorkingDay();
		List<WorkingDay> list = new ArrayList<WorkingDay>();
		int validCount = 0;
		int processLine = 0;
		int lineCount = 0;
		String line = "";
		while (line != null) {
			try {
				lineCount++;
				line = bf.readLine();// 读取一行信息
				if (StringUtils.isBlank(line)) {
					continue;
				}
				WorkingDay subWorkingDay = convertworkingDayLine2Obj(line);
				if (subWorkingDay != null) {
					processLine++;
					list.add(subWorkingDay);
					workingDay.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				this.deleteWorkingDay(workingDay.toMap());
				workingDayDao.insertWorkingDay(workingDay.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			this.deleteWorkingDay(workingDay.toMap());
			workingDayDao.insertWorkingDay(workingDay.toMap());
		}
		if (bf != null) {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PeripheralFileUtils.backupFile(file, fileRootPath, PeripheralConfigFile.SAP_PACKAGE);// 备份文件
		PeripheralFileUtils.deleteFile(file);// 删除源文件
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.WORKINGDAY + " - " + validCount + " 行数据");
	}

	public WorkingDay convertworkingDayLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 2) {
			WorkingDay subWorkingDay = new WorkingDay();
			subWorkingDay.setWorkingDay(PeripheralParseUtils.strToDate(arr[0]));
			subWorkingDay.setDayType(PeripheralParseUtils.trimString(arr[1]));
			return subWorkingDay;
		} else {
			return null;
		}
	}

	@Override
	public void deleteWorkingDay(Map<String, Object> map) {
		workingDayDao.deleteWorkingDay(map);
	}
	
	/**
	 * 查询两个日期间的工作日期天数
	 */
	@Override
	public int searchWeekDayByDate(Date startDate, Date endDate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return this.workingDayDao.searchWeekDayByDate(map);
	}

}
