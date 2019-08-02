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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePromotionSellDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandisePromotionSellService;
import com.powere2e.sco.model.peripheral.sap.MerchandisePromotionSell;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品促销销售价业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePromotionSellServiceImpl extends ServiceImpl implements MerchandisePromotionSellService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 918362878374404122L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandisePromotionSellDao merchandisePromotionSellDao;

	// 获得商品促销销售价DAO实例
	public MerchandisePromotionSellDao getMerchandisePromotionSellDao() {
		return merchandisePromotionSellDao;
	}

	// 设置商品促销销售价DAO实例
	public void setMerchandisePromotionSellDao(MerchandisePromotionSellDao merchandisePromotionSellDao) {
		this.merchandisePromotionSellDao = merchandisePromotionSellDao;
	}

	// 添加
	@Override
	public void insertMerchandisePromotionSell() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PROMOTION_SELL));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandisePromotionSell(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandisePromotionSell(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_PROMOTION_SELL);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePromotionSell merchandisePromotionSell = new MerchandisePromotionSell();
		List<MerchandisePromotionSell> list = new ArrayList<MerchandisePromotionSell>();
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
				MerchandisePromotionSell subPromotionSell = convertMerchandisePromotionSellLine2Obj(line);
				if (subPromotionSell != null) {
					processLine++;
					list.add(subPromotionSell);
					merchandisePromotionSell.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandisePromotionSellDao.insertMerchandisePromotionSell(merchandisePromotionSell.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandisePromotionSellDao.insertMerchandisePromotionSell(merchandisePromotionSell.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PROMOTION_SELL + " - " + validCount + " 行数据");
	}

	public MerchandisePromotionSell convertMerchandisePromotionSellLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 9) {
			MerchandisePromotionSell subPromotionSell = new MerchandisePromotionSell();
			subPromotionSell.setSapUpdateDate(PeripheralParseUtils.strToDate(arr[0]));
			subPromotionSell.setPromotionCode(PeripheralParseUtils.trimString(arr[1]));
			subPromotionSell.setPromotionName(PeripheralParseUtils.trimString(arr[2]));
			subPromotionSell.setRegionCode(PeripheralParseUtils.trimString(arr[3]));
			subPromotionSell.setMerchandiseCode(PeripheralParseUtils.trimString(arr[4]));
			subPromotionSell.setSupplierCode(PeripheralParseUtils.trimString(arr[5]));
			subPromotionSell.setStartDate(PeripheralParseUtils.strToDate(arr[6]));
			subPromotionSell.setEndDate(PeripheralParseUtils.strToDate(arr[7]));
			subPromotionSell.setPrice(PeripheralParseUtils.strToBigDecimal(arr[8]));
			return subPromotionSell;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandisePromotionSell() {
		merchandisePromotionSellDao.deleteMerchandisePromotionSell();
	}

}