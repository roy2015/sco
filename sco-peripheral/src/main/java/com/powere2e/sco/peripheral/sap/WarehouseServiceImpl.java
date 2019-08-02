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
import com.powere2e.sco.interfaces.dao.peripheral.sap.WarehouseDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.WarehouseService;
import com.powere2e.sco.model.peripheral.sap.Warehouse;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 仓库业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class WarehouseServiceImpl extends ServiceImpl implements WarehouseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8229284083540930391L;
	private Logger logger = PeripheralFileUtils.logger;
	private WarehouseDao warehouseDao;

	// 获得仓库DAO实例
	public WarehouseDao getWarehouseDao() {
		return warehouseDao;
	}

	// 设置仓库DAO实例
	public void setWarehouseDao(WarehouseDao warehouseDao) {
		this.warehouseDao = warehouseDao;
	}

	// 添加
	@Override
	public void insertWarehouse() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.WAREHOUSE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertWarehouse(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertWarehouse(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.WAREHOUSE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		Warehouse warehouse = new Warehouse();
		List<Warehouse> list = new ArrayList<Warehouse>();
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
				Warehouse subWarehouse = convertWarehouseLine2Obj(line);
				if (subWarehouse != null) {
					if (validCount == 0) {
						this.deleteWarehouse();
					}
					processLine++;
					list.add(subWarehouse);
					warehouse.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				warehouseDao.insertWarehouse(warehouse.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			warehouseDao.insertWarehouse(warehouse.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.WAREHOUSE + " - " + validCount + " 行数据");
	}

	public Warehouse convertWarehouseLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 3) {
			Warehouse subWarehouse = new Warehouse();
			subWarehouse.setWarehouseCode(PeripheralParseUtils.trimString(arr[0]));// 仓库编码
			subWarehouse.setWarehouseName(PeripheralParseUtils.trimString(arr[1]));// 仓库名称
			subWarehouse.setRegionCode(PeripheralParseUtils.trimString(arr[2]));// 区域编码

			return subWarehouse;
		} else {
			return null;
		}
	}

	@Override
	public void deleteWarehouse() {
		warehouseDao.deleteWarehouse();
	}

}