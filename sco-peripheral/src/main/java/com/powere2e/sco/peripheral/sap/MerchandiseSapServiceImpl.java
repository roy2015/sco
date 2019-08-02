package com.powere2e.sco.peripheral.sap;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSapDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseSapService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseSap;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 商品业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseSapServiceImpl extends ServiceImpl implements MerchandiseSapService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8735677777574943120L;
	private MerchandiseSapDao merchandiseSapDao;

	public MerchandiseSapDao getMerchandiseSapDao() {
		return merchandiseSapDao;
	}

	public void setMerchandiseSapDao(MerchandiseSapDao merchandiseSapDao) {
		this.merchandiseSapDao = merchandiseSapDao;
	}

	// 添加
	@Override
	public void insertMerchandiseSap() {
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

		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseSap(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandiseSap(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSap merchandise = new MerchandiseSap();
		List<MerchandiseSap> list = new ArrayList<MerchandiseSap>();
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
				MerchandiseSap subMerchandise = convertMerchandiseLine2Obj(line);
				if (subMerchandise != null) {
					if (validCount == 0) {
						this.deleteMerchandiseSap();
					}
					processLine++;
					list.add(subMerchandise);
					merchandise.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSapDao.insertMerchandiseSap(merchandise.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSapDao.insertMerchandiseSap(merchandise.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE + " - " + validCount + " 行数据");
	}

	public MerchandiseSap convertMerchandiseLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 14) {
			MerchandiseSap subMerchandise = new MerchandiseSap();
			subMerchandise.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));// 商品编码
			subMerchandise.setSupplierCode(PeripheralParseUtils.trimString(arr[1]));// 供应商编码
			subMerchandise.setMerchandiseName(PeripheralParseUtils.trimString(arr[2]));// 商品名称
			subMerchandise.setIndustryStandards(PeripheralParseUtils.trimString(arr[3]));// 行业标准
			subMerchandise.setWlType(PeripheralParseUtils.trimString(arr[4]));// 物料类型
			subMerchandise.setPurchaseDepartments(PeripheralParseUtils.trimString(arr[5]));// 采购部门
			subMerchandise.setPurchaseGroup(PeripheralParseUtils.trimString(arr[6]));// 采购组
			subMerchandise.setCentreTypeCode(PeripheralParseUtils.trimString(arr[7]));// 中分类
			subMerchandise.setSmallTypeCode(PeripheralParseUtils.trimString(arr[8]));// 小分类
			subMerchandise.setDetailTypeCode(PeripheralParseUtils.trimString(arr[9]));// 明细类
			subMerchandise.setStorageForm(PeripheralParseUtils.trimString(arr[10]));// 存储形式-销售方式
			subMerchandise.setMerchandiseStatus(PeripheralParseUtils.trimString(arr[11]));// 商品状态
			subMerchandise.setNetWeight(PeripheralParseUtils.strToBigDecimal(arr[12]));// 净重-是否定量
			subMerchandise.setCompanySite(PeripheralParseUtils.trimString(arr[13]));// 工厂所在地
			return subMerchandise;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseSap() {
		merchandiseSapDao.deleteMerchandiseSap();
	}

}