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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandisePromotionPurchaseDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandisePromotionPurchaseService;
import com.powere2e.sco.model.peripheral.sap.MerchandisePromotionPurchase;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
/**
 * 商品促销进货价业务类的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePromotionPurchaseServiceImpl extends ServiceImpl implements MerchandisePromotionPurchaseService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1049074463542354266L;
	private Logger logger=PeripheralFileUtils.logger;
	private MerchandisePromotionPurchaseDao merchandisePromotionPurchaseDao;
	
	//获得商品促销进货价DAO实例
	public MerchandisePromotionPurchaseDao getMerchandisePromotionPurchaseDao() {
		return merchandisePromotionPurchaseDao;
	}
	//设置商品促销进货价DAO实例
	public void setMerchandisePromotionPurchaseDao(MerchandisePromotionPurchaseDao merchandisePromotionPurchaseDao) {
		this.merchandisePromotionPurchaseDao = merchandisePromotionPurchaseDao;
	}
	
	//添加
	@Override
	public void insertMerchandisePromotionPurchase(){
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_PROMOTION_PURCHASE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandisePromotionPurchase(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandisePromotionPurchase(File file, String charsetCode, int processCount,String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_PROMOTION_PURCHASE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandisePromotionPurchase merchandisePromotionPurchase = new MerchandisePromotionPurchase();
		List<MerchandisePromotionPurchase> list = new ArrayList<MerchandisePromotionPurchase>();
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
				MerchandisePromotionPurchase subPromotionPurchase = convertMerchandisePromotionPurchaseLine2Obj(line);
				if (subPromotionPurchase != null) {
					processLine++;
					list.add(subPromotionPurchase);
					merchandisePromotionPurchase.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName()+"行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandisePromotionPurchaseDao.insertMerchandisePromotionPurchase(merchandisePromotionPurchase.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandisePromotionPurchaseDao.insertMerchandisePromotionPurchase(merchandisePromotionPurchase.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_PROMOTION_PURCHASE + " - " + validCount + " 行数据");
	}

	public MerchandisePromotionPurchase convertMerchandisePromotionPurchaseLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 10) {
			MerchandisePromotionPurchase subPromotionPurchase = new MerchandisePromotionPurchase();
			subPromotionPurchase.setSapUpdateDate(PeripheralParseUtils.strToDate(arr[0]));
			subPromotionPurchase.setPromotionCode(PeripheralParseUtils.trimString(arr[1]));
			subPromotionPurchase.setPromotionName(PeripheralParseUtils.trimString(arr[2]));
			subPromotionPurchase.setPromotionSchedule(PeripheralParseUtils.trimString(arr[3]));
			subPromotionPurchase.setMerchandiseCode(PeripheralParseUtils.trimString(arr[4]));
			subPromotionPurchase.setSupplierCode(PeripheralParseUtils.trimString(arr[5]));
			subPromotionPurchase.setRegionCode(PeripheralParseUtils.trimString(arr[6]));
			subPromotionPurchase.setStartDate(PeripheralParseUtils.strToDate(arr[7]));
			subPromotionPurchase.setEndDate(PeripheralParseUtils.strToDate(arr[8]));
			subPromotionPurchase.setPrice(PeripheralParseUtils.strToBigDecimal(arr[9]));

			return subPromotionPurchase;
		} else {
			return null;
		}
	}
	@Override
	public void deleteMerchandisePromotionPurchase() {
		merchandisePromotionPurchaseDao.deleteMerchandisePromotionPurchase();
	}
	
}