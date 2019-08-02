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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSmallTypeDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseSmallTypeService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseSmallType;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 商品小分类service实现
 * 
 * @author Joyce.li
 * @since 2015年8月17日 下午7:20:58
 * @version 1.0
 */
public class MerchandiseSmallTypeServiceImpl extends ServiceImpl implements MerchandiseSmallTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7351783263499302571L;

	private Logger logger = PeripheralFileUtils.logger;
	private MerchandiseSmallTypeDao merchandiseSmallTypeDao;

	public MerchandiseSmallTypeDao getMerchandiseSmallTypeDao() {
		return merchandiseSmallTypeDao;
	}

	public void setMerchandiseSmallTypeDao(MerchandiseSmallTypeDao merchandiseSmallTypeDao) {
		this.merchandiseSmallTypeDao = merchandiseSmallTypeDao;
	}

	@Override
	public void insertMerchandiseSmallType() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SMALL_TYPE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseSmallType(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}

	}

	@Override
	public void completeInsertMerchandiseSmallType(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SMALL_TYPE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSmallType merchandiseSmallType = new MerchandiseSmallType();
		List<MerchandiseSmallType> list = new ArrayList<MerchandiseSmallType>();
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
				MerchandiseSmallType subSmallType = convertMerchandiseSmallTypeLine2Obj(line);
				if (subSmallType != null) {
					if (validCount == 0) {
						this.deleteMerchandiseSmallType();
					}
					processLine++;
					list.add(subSmallType);
					merchandiseSmallType.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSmallTypeDao.insertMerchandiseSmallType(merchandiseSmallType.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSmallTypeDao.insertMerchandiseSmallType(merchandiseSmallType.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SMALL_TYPE + " - " + validCount + " 行数据");
	}

	public MerchandiseSmallType convertMerchandiseSmallTypeLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 3) {
			MerchandiseSmallType subSmallType = new MerchandiseSmallType();
			subSmallType.setSmallTypeCode(PeripheralParseUtils.trimString(arr[0]));// 小分类编号
			subSmallType.setSmallTypeName(PeripheralParseUtils.trimString(arr[1]));// 小分类名称
			subSmallType.setCentreTypeCode(PeripheralParseUtils.trimString(arr[2]));// 中分类编号

			return subSmallType;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseSmallType() {
		merchandiseSmallTypeDao.deleteMerchandiseSmallType();
	}

}
