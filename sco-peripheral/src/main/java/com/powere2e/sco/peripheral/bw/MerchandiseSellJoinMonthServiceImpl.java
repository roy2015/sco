package com.powere2e.sco.peripheral.bw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellJoinMonthDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseSellJoinMonthService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellJoinMonth;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品区域销售情况(月加盟)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellJoinMonthServiceImpl extends ServiceImpl implements MerchandiseSellJoinMonthService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4832641290210694587L;
	private MerchandiseSellJoinMonthDao merchandiseSellJoinMonthDao;

	// 获得商品区域销售情况(月加盟)DAO实例
	public MerchandiseSellJoinMonthDao getMerchandiseSellJoinMonthDao() {
		return merchandiseSellJoinMonthDao;
	}

	// 设置商品区域销售情况(月加盟)DAO实例
	public void setMerchandiseSellJoinMonthDao(MerchandiseSellJoinMonthDao merchandiseSellJoinMonthDao) {
		this.merchandiseSellJoinMonthDao = merchandiseSellJoinMonthDao;
	}

	// 添加
	@Override
	public void insertMerchandiseSellJoinMonth() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SELL_JOIN_MONTH));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseSellJoinMonth(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	@Override
	public void completeInsertMerchandiseSellJoinMonth(File file, String charsetCode, int processCount,String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_JOIN_MONTH);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSellJoinMonth merchandiseSellJoinMonth = new MerchandiseSellJoinMonth();
		List<MerchandiseSellJoinMonth> list = new ArrayList<MerchandiseSellJoinMonth>();
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
				MerchandiseSellJoinMonth subSellJoinMonth = convertSellJoinMonthLine2Obj(line);
				if (subSellJoinMonth != null) {
					processLine++;
					list.add(subSellJoinMonth);
					merchandiseSellJoinMonth.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName()+"行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSellJoinMonthDao.insertMerchandiseSellJoinMonth(merchandiseSellJoinMonth.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSellJoinMonthDao.insertMerchandiseSellJoinMonth(merchandiseSellJoinMonth.toMap());
		}
		if (bf != null) {
			try {
				bf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		PeripheralFileUtils.backupFile(file, fileRootPath, PeripheralConfigFile.BW_PACKAGE);//备份文件
		PeripheralFileUtils.deleteFile(file);//删除源文件
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_JOIN_MONTH + " - " + validCount + " 行数据");
	}

	public MerchandiseSellJoinMonth convertSellJoinMonthLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 20) {
			MerchandiseSellJoinMonth subSellJoinMonth = new MerchandiseSellJoinMonth();
			subSellJoinMonth.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));// 商品编码
			subSellJoinMonth.setSellMonth(PeripheralParseUtils.trimString(arr[1]));//销售月份(YYYY-MM)
			subSellJoinMonth.setSellRegion(PeripheralParseUtils.trimString(arr[2]));//销售地区编号
			subSellJoinMonth.setSupplierCode(PeripheralParseUtils.trimString(arr[3]));//供应商编码

			subSellJoinMonth.setSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[4]));//销售量
			subSellJoinMonth.setSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[5]));//销售额
			subSellJoinMonth.setSellProfit(PeripheralParseUtils.strToBigDecimal(arr[6]));//毛利额
			subSellJoinMonth.setPsdSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[7]));//PSD销量
			subSellJoinMonth.setPsdSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[8]));//PSD销售额
			subSellJoinMonth.setPsdSellProfit(PeripheralParseUtils.strToBigDecimal(arr[9]));//PSD毛利额
			subSellJoinMonth.setSellQuantityProportionM(PeripheralParseUtils.strToBigDecimal(arr[10]));//销售量占比(占所有商品)
			subSellJoinMonth.setSellTotalPriceProportionM(PeripheralParseUtils.strToBigDecimal(arr[11]));//销售额占比(占所有商品)
			subSellJoinMonth.setSellProfitProportionM(PeripheralParseUtils.strToBigDecimal(arr[12]));//毛利额占比(占所有商品)
			subSellJoinMonth.setSellQuantityProportionS(PeripheralParseUtils.strToBigDecimal(arr[13]));//销售量占比(占所有小分类)
			subSellJoinMonth.setSellTotalPriceProportionS(PeripheralParseUtils.strToBigDecimal(arr[14]));//销售额占比(占所有小分类)
			subSellJoinMonth.setSellProfitProportionS(PeripheralParseUtils.strToBigDecimal(arr[15]));//毛利额占比(占所有小分类)
			subSellJoinMonth.setSellQuantityProportionD(PeripheralParseUtils.strToBigDecimal(arr[16]));//销售量占比(占所有明细类)
			subSellJoinMonth.setSellTotalPriceProportionD(PeripheralParseUtils.strToBigDecimal(arr[17]));//销售额占比(占所有明细类)
			subSellJoinMonth.setSellProfitProportionD(PeripheralParseUtils.strToBigDecimal(arr[18]));//毛利额占比(占所有明细类)
			subSellJoinMonth.setSellStoreQuantity(PeripheralParseUtils.strToInteger(arr[19]));//销售店天数

			return subSellJoinMonth;
		} else {
			return null;
		}
	}
}