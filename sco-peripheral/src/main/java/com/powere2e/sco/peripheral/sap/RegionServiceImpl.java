package com.powere2e.sco.peripheral.sap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.RegionDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.RegionService;
import com.powere2e.sco.model.peripheral.sap.Region;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 区域信息业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class RegionServiceImpl extends ServiceImpl implements RegionService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1466666754905515793L;
	private Logger logger = PeripheralFileUtils.logger;
	private RegionDao regionDao;

	// 获得区域信息DAO实例
	public RegionDao getRegionDao() {
		return regionDao;
	}

	// 设置区域信息DAO实例
	public void setRegionDao(RegionDao regionDao) {
		this.regionDao = regionDao;
	}

	// 添加
	@Override
	public void insertRegion() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.REGION));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertRegion(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertRegion(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.REGION);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		Region region = new Region();
		List<Region> list = new ArrayList<Region>();
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
				Region subRegion = convertRegionLine2Obj(line);
				if (subRegion != null) {
					if (validCount == 0) {
						this.deleteRegion();
					}
					processLine++;
					list.add(subRegion);
					region.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				regionDao.insertRegion(region.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			regionDao.insertRegion(region.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.REGION + " - " + validCount + " 行数据");
	}

	public Region convertRegionLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 4) {
			Region subRegion = new Region();
			subRegion.setRegionCode(PeripheralParseUtils.trimString(arr[0]));// 区域编码
			subRegion.setRegionName(PeripheralParseUtils.trimString(arr[1]));// 区域名称
			subRegion.setDirectStoreQuantity(PeripheralParseUtils.strToInteger(arr[2]));// 直营门店数
			subRegion.setJoinStoreQuantity(PeripheralParseUtils.strToInteger(arr[3]));// 加盟门店数

			return subRegion;
		} else {
			return null;
		}
	}

	@Override
	public void deleteRegion() {
		regionDao.deleteRegion();
	}

}