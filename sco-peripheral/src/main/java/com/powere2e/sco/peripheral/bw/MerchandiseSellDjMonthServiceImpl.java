package com.powere2e.sco.peripheral.bw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDjMonthDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseSellDjMonthService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellDjMonth;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品区域销售情况(月直营+加盟)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDjMonthServiceImpl extends ServiceImpl implements MerchandiseSellDjMonthService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4856077464334563860L;
	private MerchandiseSellDjMonthDao merchandiseSellDjMonthDao;

	// 获得商品区域销售情况(月直营+加盟)DAO实例
	public MerchandiseSellDjMonthDao getMerchandiseSellDjMonthDao() {
		return merchandiseSellDjMonthDao;
	}

	// 设置商品区域销售情况(月直营+加盟)DAO实例
	public void setMerchandiseSellDjMonthDao(MerchandiseSellDjMonthDao merchandiseSellDjMonthDao) {
		this.merchandiseSellDjMonthDao = merchandiseSellDjMonthDao;
	}

	// 添加
	@Override
	public void insertMerchandiseSellDjMonth() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SELL_D_J_MONTH));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseSellDjMonth(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	@Override
	public void completeInsertMerchandiseSellDjMonth(File file, String charsetCode, int processCount,String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_D_J_MONTH);
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSellDjMonth merchandiseSellDjMonth = new MerchandiseSellDjMonth();
		List<MerchandiseSellDjMonth> list = new ArrayList<MerchandiseSellDjMonth>();
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
				MerchandiseSellDjMonth subSellDjMonth = convertSellDjMonthLine2Obj(line);
				if (subSellDjMonth != null) {
					processLine++;
					list.add(subSellDjMonth);
					merchandiseSellDjMonth.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName()+"行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSellDjMonthDao.insertMerchandiseSellDjMonth(merchandiseSellDjMonth.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSellDjMonthDao.insertMerchandiseSellDjMonth(merchandiseSellDjMonth.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_D_J_MONTH + " - " + validCount + " 行数据");
	}

	public MerchandiseSellDjMonth convertSellDjMonthLine2Obj(String line) {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 20) {
			MerchandiseSellDjMonth subSellDjMonth = new MerchandiseSellDjMonth();
			subSellDjMonth.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));//商品编码
			subSellDjMonth.setSellMonth(PeripheralParseUtils.trimString(arr[1]));//销售月份(YYYY-MM)
			subSellDjMonth.setSellRegion(PeripheralParseUtils.trimString(arr[2]));// 销售地区编号
			subSellDjMonth.setSupplierCode(PeripheralParseUtils.trimString(arr[3]));//供应商编码

			subSellDjMonth.setSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[4]));//销售量
			subSellDjMonth.setSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[5]));//销售额
			subSellDjMonth.setSellProfit(PeripheralParseUtils.strToBigDecimal(arr[6]));//毛利额
			subSellDjMonth.setPsdSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[7]));//PSD销量
			subSellDjMonth.setPsdSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[8]));//PSD销售额
			subSellDjMonth.setPsdSellProfit(PeripheralParseUtils.strToBigDecimal(arr[9]));//PSD毛利额
			subSellDjMonth.setSellQuantityProportionM(PeripheralParseUtils.strToBigDecimal(arr[10]));//销售量占比(占所有商品)
			subSellDjMonth.setSellTotalPriceProportionM(PeripheralParseUtils.strToBigDecimal(arr[11]));//销售额占比(占所有商品)
			subSellDjMonth.setSellProfitProportionM(PeripheralParseUtils.strToBigDecimal(arr[12]));//毛利额占比(占所有商品)
			subSellDjMonth.setSellQuantityProportionS(PeripheralParseUtils.strToBigDecimal(arr[13]));//销售量占比(占所有小分类)
			subSellDjMonth.setSellTotalPriceProportionS(PeripheralParseUtils.strToBigDecimal(arr[14]));//销售额占比(占所有小分类)
			subSellDjMonth.setSellProfitProportionS(PeripheralParseUtils.strToBigDecimal(arr[15]));//毛利额占比(占所有小分类)
			subSellDjMonth.setSellQuantityProportionD(PeripheralParseUtils.strToBigDecimal(arr[16]));//销售量占比(占所有明细类)
			subSellDjMonth.setSellTotalPriceProportionD(PeripheralParseUtils.strToBigDecimal(arr[17]));//销售额占比(占所有明细类)
			subSellDjMonth.setSellProfitProportionD(PeripheralParseUtils.strToBigDecimal(arr[18]));//毛利额占比(占所有明细类)
			subSellDjMonth.setSellStoreQuantity(PeripheralParseUtils.strToInteger(arr[19]));//销售店天数

			return subSellDjMonth;
		} else {
			return null;
		}
	}

}