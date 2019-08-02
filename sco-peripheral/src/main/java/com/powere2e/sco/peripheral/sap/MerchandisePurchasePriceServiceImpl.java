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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePurchasePriceDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandisePurchasePriceService;
import com.powere2e.sco.model.peripheral.sap.MerchandisePurchasePrice;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品进货价格信息业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePurchasePriceServiceImpl extends ServiceImpl implements MerchandisePurchasePriceService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2385782789391286528L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandisePurchasePriceDao merchandisePurchasePriceDao;

	// 获得商品进货价格信息DAO实例
	public MerchandisePurchasePriceDao getMerchandisePurchasePriceDao() {
		return merchandisePurchasePriceDao;
	}

	// 设置商品进货价格信息DAO实例
	public void setMerchandisePurchasePriceDao(MerchandisePurchasePriceDao merchandisePurchasePriceDao) {
		this.merchandisePurchasePriceDao = merchandisePurchasePriceDao;
	}

	// 添加
	@Override
	public void insertMerchandisePurchasePrice() {
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

		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PURCHASE_PRICE));// 获取SAP目录的文件
		if (file.exists()) {
			this.insertMerchandisePurchasePrice(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
		// 去重复的价格数据
		File fileGroup = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PURCHASE_PRICE_GROUP));// 获取SAP目录的文件
		if (fileGroup.exists()) {
			this.insertMerchandisePurchasePriceGroup(fileGroup, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	@Override
	public void insertMerchandisePurchasePrice(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_PURCHASE_PRICE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePurchasePrice merchandisePurchasePrice = new MerchandisePurchasePrice();
		List<MerchandisePurchasePrice> merchandisePurchasePriceList = new ArrayList<MerchandisePurchasePrice>();
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
				MerchandisePurchasePrice subPurchasePrice = convertMerchandisePurchasePriceLine2Obj(line);
				if (subPurchasePrice != null) {
					processLine++;
					actualCount++;
					merchandisePurchasePriceList.add(subPurchasePrice);
					merchandisePurchasePrice.setList(merchandisePurchasePriceList);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandisePurchasePriceDao.insertMerchandisePurchasePrice(merchandisePurchasePrice.toMap());
				processLine = 0;
				merchandisePurchasePriceList.clear();
			}
		}
		// 判断保存数据的list中是否有数据
		if (!merchandisePurchasePriceList.isEmpty()) {
			merchandisePurchasePriceDao.insertMerchandisePurchasePrice(merchandisePurchasePrice.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PURCHASE_PRICE + " - " + validCount + " 行数据, 入库成功:" + actualCount);
	}

	@Override
	public void insertMerchandisePurchasePriceGroup(File file, String charsetCode, int processCount, String fileRootPath) {
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePurchasePrice merchandisePurchasePriceGroup = new MerchandisePurchasePrice();
		List<MerchandisePurchasePrice> merchandisePurchasePriceGroupList = new ArrayList<MerchandisePurchasePrice>();
		int validCount = 0;
		int lineCount = 0;
		int processLine = 0;
		int actualCount = 0;
		String line = "";
		while (line != null) {
			try {
				lineCount++;
				line = bf.readLine();// 读取一行信息
				if (StringUtils.isBlank(line)) {
					continue;
				}
				MerchandisePurchasePrice subPurchasePrice = convertMerchandisePurchasePriceLine2Obj(line);
				if (subPurchasePrice != null) {
					processLine++;
					actualCount++;
					merchandisePurchasePriceGroupList.add(subPurchasePrice);
					merchandisePurchasePriceGroup.setList(merchandisePurchasePriceGroupList);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandisePurchasePriceDao.insertMerchandisePurchasePriceGroup(merchandisePurchasePriceGroup.toMap());
				processLine = 0;
				merchandisePurchasePriceGroupList.clear();
			}
		}
		// 判断保存重复数据的list中是否有数据
		if (!merchandisePurchasePriceGroupList.isEmpty()) {
			merchandisePurchasePriceDao.insertMerchandisePurchasePriceGroup(merchandisePurchasePriceGroup.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PURCHASE_PRICE_GROUP + " - " + validCount + " 行数据, 入库成功:" + actualCount);
	}

	/**
	 * 数据行转为对象
	 * 
	 * @param line
	 * @return
	 */
	public MerchandisePurchasePrice convertMerchandisePurchasePriceLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 5) {
			MerchandisePurchasePrice subPurchasePrice = new MerchandisePurchasePrice();
			subPurchasePrice.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));
			subPurchasePrice.setSupplierCode(PeripheralParseUtils.trimString(arr[1]));
			subPurchasePrice.setWarehouseCode(PeripheralParseUtils.trimString(arr[2]));
			subPurchasePrice.setPurchasePrice(PeripheralParseUtils.strToBigDecimal(arr[3]));
			subPurchasePrice.setPriceDate(PeripheralParseUtils.strToDate(arr[4]));

			return subPurchasePrice;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandisePurchasePrice() {
		merchandisePurchasePriceDao.deleteMerchandisePurchasePrice();
	}
}