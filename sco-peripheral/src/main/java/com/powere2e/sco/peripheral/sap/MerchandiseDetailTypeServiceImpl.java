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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseDetailTypeDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseDetailTypeService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseDetailType;
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
public class MerchandiseDetailTypeServiceImpl extends ServiceImpl implements MerchandiseDetailTypeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110053170404675957L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandiseDetailTypeDao merchandiseDetailTypeDao;

	public MerchandiseDetailTypeDao getMerchandiseDetailTypeDao() {
		return merchandiseDetailTypeDao;
	}

	public void setMerchandiseDetailTypeDao(MerchandiseDetailTypeDao merchandiseDetailTypeDao) {
		this.merchandiseDetailTypeDao = merchandiseDetailTypeDao;
	}

	@Override
	public void insertMerchandiseDetailType() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_DETAIL_TYPE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseDetailType(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandiseDetailType(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_DETAIL_TYPE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseDetailType merchandiseDetailType = new MerchandiseDetailType();
		List<MerchandiseDetailType> list = new ArrayList<MerchandiseDetailType>();
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
				MerchandiseDetailType subDetailType = convertMerchandiseDetailTypeLine2Obj(line);
				if (subDetailType != null) {
					if (validCount == 0) {
						this.deleteMerchandiseDetailType();// 入库前需要先删除数据
					}
					processLine++;
					list.add(subDetailType);
					merchandiseDetailType.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseDetailTypeDao.insertMerchandiseDetailType(merchandiseDetailType.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseDetailTypeDao.insertMerchandiseDetailType(merchandiseDetailType.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_DETAIL_TYPE + " - " + validCount + " 行数据");
	}

	public MerchandiseDetailType convertMerchandiseDetailTypeLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 3) {
			MerchandiseDetailType subDetailType = new MerchandiseDetailType();
			subDetailType.setDetailTypeCode(PeripheralParseUtils.trimString(arr[0]));// 明细类编号
			subDetailType.setDetailTypeName(PeripheralParseUtils.trimString(arr[1]));// 明细类名称
			subDetailType.setSmallTypeCode(PeripheralParseUtils.trimString(arr[2]));// 小分类编号

			return subDetailType;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseDetailType() {
		merchandiseDetailTypeDao.deleteMerchandiseDetailType();
	}

}
