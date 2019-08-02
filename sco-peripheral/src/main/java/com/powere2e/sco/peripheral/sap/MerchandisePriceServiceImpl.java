package com.powere2e.sco.peripheral.sap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePriceDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandisePriceService;
import com.powere2e.sco.model.peripheral.sap.MerchandisePrice;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品销售价格信息业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePriceServiceImpl extends ServiceImpl implements MerchandisePriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -476318870155362590L;
	private MerchandisePriceDao merchandisePriceDao;

	// 获得商品销售价格信息DAO实例
	public MerchandisePriceDao getMerchandisePriceDao() {
		return merchandisePriceDao;
	}

	// 设置商品销售价格信息DAO实例
	public void setMerchandisePriceDao(MerchandisePriceDao merchandisePriceDao) {
		this.merchandisePriceDao = merchandisePriceDao;
	}

	@Override
	public void insertMerchandisePrice() {
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

		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PRICE));// 获取SAP目录的文件
		if (file.exists()) {
			// 解析文件
			this.insertMerchandisePrice(file, charsetCode, processCount, fileRootPath);
		}
		// 去重复的价格数据
		File fileGroup = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PRICE_GROUP));// 获取SAP目录的文件
		if (fileGroup.exists()) {
			// 解析文件
			this.insertMerchandisePriceGroup(fileGroup, charsetCode, processCount, fileRootPath);
		}
	}

	@Override
	public void insertMerchandisePrice(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_PRICE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePrice merchandisePrice = new MerchandisePrice();
		List<MerchandisePrice> merchandisePriceList = new ArrayList<MerchandisePrice>();
		int validCount = 0;
		int processLine = 0;
		int lineCount = 0;
		int actualCount = 0;
		String line = "";
		while (line != null) {
			try {
				lineCount++;
				line = bf.readLine();// 读取一行信息
				if (StringUtils.isBlank(line)) {
					continue;
				}
				MerchandisePrice subMerchandisePrice = convertMerchandisePriceLine2Obj(line);
				if (subMerchandisePrice != null) {
					processLine++;
					actualCount++;
					merchandisePriceList.add(subMerchandisePrice);
					merchandisePrice.setList(merchandisePriceList);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandisePriceDao.insertMerchandisePrice(merchandisePrice.toMap());
				processLine = 0;
				merchandisePriceList.clear();
			}
		}
		// 判断保存数据的list中是否有数据
		if (!merchandisePriceList.isEmpty()) {
			merchandisePriceDao.insertMerchandisePrice(merchandisePrice.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PRICE + " - " + validCount + " 行数据, 入库成功:" + actualCount);
	}

	@Override
	public void insertMerchandisePriceGroup(File file, String charsetCode, int processCount, String fileRootPath) {
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePrice merchandisePriceGroup = new MerchandisePrice();
		List<MerchandisePrice> merchandisePriceGroupList = new ArrayList<MerchandisePrice>();
		int validCount = 0;
		int processLine = 0;
		int lineCount = 0;
		int actualCount = 0;
		String line = "";
		while (line != null) {
			try {
				lineCount++;
				line = bf.readLine();// 读取一行信息
				if (StringUtils.isBlank(line)) {
					continue;
				}
				MerchandisePrice subMerchandisePrice = convertMerchandisePriceLine2Obj(line);
				if (subMerchandisePrice != null) {
					processLine++;
					actualCount++;
					merchandisePriceGroupList.add(subMerchandisePrice);
					merchandisePriceGroup.setList(merchandisePriceGroupList);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则(Group)！]", e);
			}
			if (processLine == processCount) {
				// 插入重复数据
				merchandisePriceDao.insertMerchandisePriceGroup(merchandisePriceGroup.toMap());
				processLine = 0;
				merchandisePriceGroupList.clear();
			}
		}
		// 判断保存去重复数据的list中是否有数据
		if (!merchandisePriceGroupList.isEmpty()) {
			merchandisePriceDao.insertMerchandisePriceGroup(merchandisePriceGroup.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PRICE_GROUP + " - " + validCount + " 行数据, 入库成功:" + actualCount);
	}

	@Override
	public void deleteMerchandisePrice() {
		merchandisePriceDao.deleteMerchandisePrice();
	}

	/**
	 * 数据行转为对象
	 * 
	 * @param line
	 * @return
	 */
	public MerchandisePrice convertMerchandisePriceLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 6) {
			MerchandisePrice subMerchandisePrice = new MerchandisePrice();
			subMerchandisePrice.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));
			subMerchandisePrice.setSupplierCode(PeripheralParseUtils.trimString(arr[1]));
			subMerchandisePrice.setSellRegion(PeripheralParseUtils.trimString(arr[2]));
			subMerchandisePrice.setDirectJoin(PeripheralParseUtils.trimString(arr[3]));
			subMerchandisePrice.setSellPrice(PeripheralParseUtils.strToBigDecimal(arr[4]));
			subMerchandisePrice.setPriceDate(PeripheralParseUtils.strToDate(arr[5]));
			return subMerchandisePrice;
		} else {
			return null;
		}
	}
}