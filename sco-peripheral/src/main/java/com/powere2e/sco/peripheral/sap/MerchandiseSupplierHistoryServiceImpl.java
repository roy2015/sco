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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSupplierHistoryDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseSupplierHistoryService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseSupplierHistory;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 供应商历史物料号业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseSupplierHistoryServiceImpl extends ServiceImpl implements MerchandiseSupplierHistoryService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8787659820008346634L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandiseSupplierHistoryDao merchandiseSupplierHistoryDao;

	// 获得供应商历史物料号DAO实例
	public MerchandiseSupplierHistoryDao getMerchandiseSupplierHistoryDao() {
		return merchandiseSupplierHistoryDao;
	}

	// 设置供应商历史物料号DAO实例
	public void setMerchandiseSupplierHistoryDao(MerchandiseSupplierHistoryDao merchandiseSupplierHistoryDao) {
		this.merchandiseSupplierHistoryDao = merchandiseSupplierHistoryDao;
	}

	// 添加
	@Override
	public void insertMerchandiseSupplierHistory() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.SUPPLIER_HISTORY));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseSupplierHistory(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandiseSupplierHistory(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.SUPPLIER_HISTORY);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSupplierHistory supplierHistory = new MerchandiseSupplierHistory();
		List<MerchandiseSupplierHistory> list = new ArrayList<MerchandiseSupplierHistory>();
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
				MerchandiseSupplierHistory subSupplierHistory = convertMerchandiseSupplierHistoryLine2Obj(line);
				if (subSupplierHistory != null) {
					if (validCount == 0) {
						this.deleteMerchandiseSupplierHistory();
					}
					processLine++;
					list.add(subSupplierHistory);
					supplierHistory.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSupplierHistoryDao.insertMerchandiseSupplierHistory(supplierHistory.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSupplierHistoryDao.insertMerchandiseSupplierHistory(supplierHistory.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.SUPPLIER_HISTORY + " - " + validCount + " 行数据");
	}

	public MerchandiseSupplierHistory convertMerchandiseSupplierHistoryLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 2) {
			MerchandiseSupplierHistory subSupplierHistory = new MerchandiseSupplierHistory();
			subSupplierHistory.setSupplierCode(PeripheralParseUtils.trimString(arr[0]));// 供应商编号
			subSupplierHistory.setWlReplace(PeripheralParseUtils.trimString(arr[1]));// 物料替代码

			return subSupplierHistory;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseSupplierHistory() {
		merchandiseSupplierHistoryDao.deleteMerchandiseSupplierHistory();
	}

}