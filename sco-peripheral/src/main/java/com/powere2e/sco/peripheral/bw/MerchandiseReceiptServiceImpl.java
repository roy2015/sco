package com.powere2e.sco.peripheral.bw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseReceiptDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseReceiptService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseReceipt;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品收货单信息业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseReceiptServiceImpl extends ServiceImpl implements MerchandiseReceiptService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 784156659658442213L;
	private MerchandiseReceiptDao merchandiseBwReceiptDao;

	// 获得商品收货单信息DAO实例

	public MerchandiseReceiptDao getMerchandiseBwReceiptDao() {
		return merchandiseBwReceiptDao;
	}

	public void setMerchandiseBwReceiptDao(MerchandiseReceiptDao merchandiseBwReceiptDao) {
		this.merchandiseBwReceiptDao = merchandiseBwReceiptDao;
	}

	// 添加
	@Override
	public void insertMerchandiseReceipt() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_RECEIPT));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseReceipt(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	// 添加事务控制
	@Override
	public void completeInsertMerchandiseReceipt(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_RECEIPT);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseReceipt merchandiseReceipt = new MerchandiseReceipt();
		List<MerchandiseReceipt> list = new ArrayList<MerchandiseReceipt>();
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
				MerchandiseReceipt subReceipt = convertMerchandiseReceiptLine2Obj(line);
				if (subReceipt != null) {
					processLine++;
					list.add(subReceipt);
					merchandiseReceipt.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseBwReceiptDao.insertMerchandiseReceipt(merchandiseReceipt.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseBwReceiptDao.insertMerchandiseReceipt(merchandiseReceipt.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_RECEIPT + " - " + validCount + " 行数据");
	}

	public MerchandiseReceipt convertMerchandiseReceiptLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 10) {
			MerchandiseReceipt subReceipt = new MerchandiseReceipt();
			subReceipt.setRegionCode(PeripheralParseUtils.trimString(arr[0]));// 区域编码
			subReceipt.setWarehouseCode(PeripheralParseUtils.trimString(arr[1]));// 仓库编码
			subReceipt.setWarehouseSiteCode(PeripheralParseUtils.trimString(arr[2]));// 仓位编码
			subReceipt.setMerchandiseCode(PeripheralParseUtils.trimString(arr[3]));// 商品编号
			subReceipt.setSupplierCode(PeripheralParseUtils.trimString(arr[4]));// 供应商编码
			subReceipt.setOrderDate(PeripheralParseUtils.strToDate(arr[5]));// 订单时间
			subReceipt.setOrderCode(PeripheralParseUtils.trimString(arr[6]));// 订单编号
			subReceipt.setRealityReceiptDate(PeripheralParseUtils.strToDate(arr[7]));// 实际到货时间
			subReceipt.setReceiptRationed(PeripheralParseUtils.strToBigDecimal(arr[8]));// 到货数量
			subReceipt.setReceiptTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[9]));// 到货总金额

			return subReceipt;
		} else {
			return null;
		}
	}

}