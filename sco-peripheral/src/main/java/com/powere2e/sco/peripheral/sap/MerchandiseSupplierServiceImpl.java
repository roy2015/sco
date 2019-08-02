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
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSupplierDao;
import com.powere2e.sco.interfaces.service.peripheral.sap.MerchandiseSupplierService;
import com.powere2e.sco.model.peripheral.sap.MerchandiseSupplier;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;

/**
 * 商品历史物料号service实现
 * 
 * @author Joyce.li
 * @since 2015年8月17日 下午7:20:58
 * @version 1.0
 */
public class MerchandiseSupplierServiceImpl extends ServiceImpl implements MerchandiseSupplierService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110053170404675957L;
	private Logger logger = PeripheralFileUtils.logger;
	private MerchandiseSupplierDao merchandiseSupplierDao;

	public MerchandiseSupplierDao getMerchandiseSupplierDao() {
		return merchandiseSupplierDao;
	}

	public void setMerchandiseSupplierDao(MerchandiseSupplierDao merchandiseSupplierDao) {
		this.merchandiseSupplierDao = merchandiseSupplierDao;
	}

	@Override
	public void insertMerchandiseSupplier() {
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

		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.SAP_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SUPPLIER));// 获取SAP目录的文件
		if (file.exists()) {
			completeInsertMerchandiseSupplier(file, charsetCode, processCount, fileRootPath);// 解析文件
		} else {
			return;
		}
	}

	@Override
	public void completeInsertMerchandiseSupplier(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SUPPLIER);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSupplier merchandiseSupplier = new MerchandiseSupplier();
		List<MerchandiseSupplier> list = new ArrayList<MerchandiseSupplier>();
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
				MerchandiseSupplier subSupplier = convertMerchandiseSupplierLine2Obj(line);
				if (subSupplier != null) {
					if (validCount == 0) {
						this.deleteMerchandiseSupplier();
					}
					processLine++;
					list.add(subSupplier);
					merchandiseSupplier.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSupplierDao.insertMerchandiseSupplier(merchandiseSupplier.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSupplierDao.insertMerchandiseSupplier(merchandiseSupplier.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SUPPLIER + " - " + validCount + " 行数据");
	}

	public MerchandiseSupplier convertMerchandiseSupplierLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 9) {
			MerchandiseSupplier subSupplier = new MerchandiseSupplier();
			subSupplier.setSupplierCode(PeripheralParseUtils.trimString(arr[0]));// 供应商编码
			subSupplier.setSupplierName(PeripheralParseUtils.trimString(arr[1]));// 供应商名称
			subSupplier.setCompanyType(PeripheralParseUtils.trimString(arr[2]));// 供应商分类
			subSupplier.setContacts(PeripheralParseUtils.trimString(arr[3]));// 联系人
			subSupplier.setContactsPhone(PeripheralParseUtils.trimString(arr[4]));// 联系人电话
			subSupplier.setCompanySite(PeripheralParseUtils.trimString(arr[5]));// 公司地址
			subSupplier.setCompanyPhone(PeripheralParseUtils.trimString(arr[6]));// 公司电话
			subSupplier.setCompanyFax(PeripheralParseUtils.trimString(arr[7]));// 公司传真
			subSupplier.setPostcode(PeripheralParseUtils.trimString(arr[8]));// 邮编

			return subSupplier;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseSupplier() {
		merchandiseSupplierDao.deleteMerchandiseSupplier();
	}

}
