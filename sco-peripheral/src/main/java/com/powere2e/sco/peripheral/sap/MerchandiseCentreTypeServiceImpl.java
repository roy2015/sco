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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseCentreTypeDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseCentreTypeService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseCentreType;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 商品中分类service实现
 * 
 * @author Joyce.li
 * @since 2015年8月17日 下午7:20:58
 * @version 1.0
 */
public class MerchandiseCentreTypeServiceImpl extends ServiceImpl implements MerchandiseCentreTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110053170404675957L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandiseCentreTypeDao merchandiseCentreTypeDao;

	public MerchandiseCentreTypeDao getMerchandiseCentreTypeDao() {
		return merchandiseCentreTypeDao;
	}

	public void setMerchandiseCentreTypeDao(MerchandiseCentreTypeDao merchandiseCentreTypeDao) {
		this.merchandiseCentreTypeDao = merchandiseCentreTypeDao;
	}

	// 解析指定路径下的商品中分类文件
	@Override
	public void insertMerchandiseCentreType() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_CENTRE_TYPE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseCentreType(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	// 批量插入商品中分类数据
	@Override
	public void completeInsertMerchandiseCentreType(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_CENTRE_TYPE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseCentreType merchandiseCentreType = new MerchandiseCentreType();
		List<MerchandiseCentreType> list = new ArrayList<MerchandiseCentreType>();
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
				MerchandiseCentreType subCentreType = convertMerchandiseCentreTypeLine2Obj(line);
				if (subCentreType != null) {
					if (validCount == 0) {
						this.deleteMerchandiseCentreType();// 入库前需要先删除数据
					}
					processLine++;
					list.add(subCentreType);
					merchandiseCentreType.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseCentreTypeDao.insertMerchandiseCentreType(merchandiseCentreType.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseCentreTypeDao.insertMerchandiseCentreType(merchandiseCentreType.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_CENTRE_TYPE + " - " + validCount + " 行数据");
	}

	// 将一行数据转换为model
	public MerchandiseCentreType convertMerchandiseCentreTypeLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 2) {
			MerchandiseCentreType subCentreType = new MerchandiseCentreType();
			subCentreType.setCentreTypeCode(PeripheralParseUtils.trimString(arr[0]));// 中分类编号
			subCentreType.setCentreTypeName(PeripheralParseUtils.trimString(arr[1]));// 中分类名称

			return subCentreType;
		} else {
			return null;
		}
	}

	// 删除商品中分类
	@Override
	public void deleteMerchandiseCentreType() {
		merchandiseCentreTypeDao.deleteMerchandiseCentreType();
	}

}
