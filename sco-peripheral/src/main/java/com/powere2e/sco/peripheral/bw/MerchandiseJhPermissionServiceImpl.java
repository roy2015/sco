package com.powere2e.sco.peripheral.bw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseJhPermissionDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseJhPermissionService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseJhPermission;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品进货权限业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseJhPermissionServiceImpl extends ServiceImpl implements MerchandiseJhPermissionService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6135665543615409134L;
	private MerchandiseJhPermissionDao merchandiseJhPermissionDao;

	// 获得商品进货权限DAO实例
	public MerchandiseJhPermissionDao getMerchandiseJhPermissionDao() {
		return merchandiseJhPermissionDao;
	}

	// 设置商品进货权限DAO实例
	public void setMerchandiseJhPermissionDao(MerchandiseJhPermissionDao merchandiseJhPermissionDao) {
		this.merchandiseJhPermissionDao = merchandiseJhPermissionDao;
	}

	// 添加
	@Override
	public void insertMerchandiseJhPermission() {
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

		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_JH_PERMISSION));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseJhPermission(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	@Override
	public void completeInsertMerchandiseJhPermission(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_JH_PERMISSION);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseJhPermission merchandiseJhPermission = new MerchandiseJhPermission();
		List<MerchandiseJhPermission> list = new ArrayList<MerchandiseJhPermission>();
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
				MerchandiseJhPermission subJhPermission = convertMerchandiseJhPermissionLine2Obj(line);
				if (subJhPermission != null) {
					processLine++;
					list.add(subJhPermission);
					merchandiseJhPermission.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseJhPermissionDao.insertMerchandiseJhPermission(merchandiseJhPermission.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseJhPermissionDao.insertMerchandiseJhPermission(merchandiseJhPermission.toMap());
		}
		if (bf != null) {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		PeripheralFileUtils.backupFile(file, fileRootPath, PeripheralConfigFile.BW_PACKAGE);// 备份文件
		PeripheralFileUtils.deleteFile(file);// 删除源文件
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_JH_PERMISSION + " - " + validCount + " 行数据");
	}

	public MerchandiseJhPermission convertMerchandiseJhPermissionLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 9) {
			MerchandiseJhPermission subJhPermission = new MerchandiseJhPermission();
			subJhPermission.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));// 商品编号
			subJhPermission.setSupplierCode(PeripheralParseUtils.trimString(arr[1]));// 供应商编号
			subJhPermission.setPermissionDate(PeripheralParseUtils.strToDate(arr[2]));// 权限日期
			subJhPermission.setRegionCode(PeripheralParseUtils.trimString(arr[3]));// 区域编码
			subJhPermission.setStoreType(PeripheralParseUtils.trimString(arr[4]));// 门店类型
			subJhPermission.setPermissionStoreQuantity(PeripheralParseUtils.strToInteger(arr[5]));// 权限店天数
			subJhPermission.setAStoreQuantity(PeripheralParseUtils.strToInteger(arr[6]));// A门店数
			subJhPermission.setBStoreQuantity(PeripheralParseUtils.strToInteger(arr[7]));// B门店数
			subJhPermission.setCStoreQuantity(PeripheralParseUtils.strToInteger(arr[8]));// C门店数
			subJhPermission.setDStoreQuantity(PeripheralParseUtils.strToInteger(arr[9]));// D门店数

			return subJhPermission;
		} else {
			return null;
		}
	}

}