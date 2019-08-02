package com.powere2e.sco.peripheral.bw;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.etc.utils.IOUtils;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDirectDayDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseSellDirectDayService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellDirectDay;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品区域销售情况(日直营)单Service实现
 * 
 * @author Joyce.li
 * @since 2015年8月13日 下午3:08:20
 * @version 1.0
 */

public class MerchandiseSellDirectDayServiceImpl extends ServiceImpl implements MerchandiseSellDirectDayService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6110053170404675957L;
	private MerchandiseSellDirectDayDao merchandiseSellDirectDayDao;

	public MerchandiseSellDirectDayDao getMerchandiseSellDirectDayDao() {
		return merchandiseSellDirectDayDao;
	}

	public void setMerchandiseSellDirectDayDao(MerchandiseSellDirectDayDao merchandiseSellDirectDayDao) {
		this.merchandiseSellDirectDayDao = merchandiseSellDirectDayDao;
	}

	@Override
	public void insertMerchandiseSellDirectDay() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SELL_DIRECT_DAY));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseSellDirectDay(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	// 添加事务控制
	@Override
	public void completeInsertMerchandiseSellDirectDay(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_DIRECT_DAY);
		Calendar cal = Calendar.getInstance();
		if (DateUtils.isFirstDay() && cal.get(Calendar.HOUR_OF_DAY) >= 18) {
			String date = DateUtils.formatDateToStr(new Date(), "yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", date);
			this.deleteMerchandiseSellDirectDay(map);
		}
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSellDirectDay merchandiseSellDirectDay = new MerchandiseSellDirectDay();
		List<MerchandiseSellDirectDay> sellDetailList = new ArrayList<MerchandiseSellDirectDay>();
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
				MerchandiseSellDirectDay subSellDirectDay = convertSellDirectDayLine2Obj(line);
				if (subSellDirectDay != null) {
					processLine++;
					sellDetailList.add(subSellDirectDay);
					merchandiseSellDirectDay.setSellDirectDayList(sellDetailList);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSellDirectDayDao.insertMerchandiseSellDirectDay(merchandiseSellDirectDay);
				processLine = 0;
				sellDetailList.clear();
			}
		}
		if (!sellDetailList.isEmpty()) {
			merchandiseSellDirectDayDao.insertMerchandiseSellDirectDay(merchandiseSellDirectDay);
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_DIRECT_DAY + " - " + validCount + " 行数据");
	}

	public MerchandiseSellDirectDay convertSellDirectDayLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 20) {
			MerchandiseSellDirectDay subSellDirectDay = new MerchandiseSellDirectDay();
			subSellDirectDay.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));// 商品编码
			subSellDirectDay.setSellDate(PeripheralParseUtils.strToDate(arr[1]));// 销售日期
			subSellDirectDay.setSellRegion(PeripheralParseUtils.trimString(arr[2]));// 销售地区编号
			subSellDirectDay.setSupplierCode(PeripheralParseUtils.trimString(arr[3]));// 供应商编码

			subSellDirectDay.setSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[4]));// 销售量
			subSellDirectDay.setSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[5]));// 销售额
			subSellDirectDay.setSellProfit(PeripheralParseUtils.strToBigDecimal(arr[6]));// 毛利额
			subSellDirectDay.setPsdSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[7]));// PSD销量
			subSellDirectDay.setPsdSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[8]));// PSD销售额
			subSellDirectDay.setPsdSellProfit(PeripheralParseUtils.strToBigDecimal(arr[9]));// PSD毛利额
			subSellDirectDay.setSellQuantityProportionM(PeripheralParseUtils.strToBigDecimal(arr[10]));// 销售量占比(占所有商品)
			subSellDirectDay.setSellTotalPriceProportionM(PeripheralParseUtils.strToBigDecimal(arr[11]));// 销售额占比(占所有商品)
			subSellDirectDay.setSellProfitProportionM(PeripheralParseUtils.strToBigDecimal(arr[12]));// 毛利额占比(占所有商品)
			subSellDirectDay.setSellQuantityProportionS(PeripheralParseUtils.strToBigDecimal(arr[13]));// 销售量占比(占所有小分类)
			subSellDirectDay.setSellTotalPriceProportionS(PeripheralParseUtils.strToBigDecimal(arr[14]));// 销售额占比(占所有小分类)
			subSellDirectDay.setSellProfitProportionS(PeripheralParseUtils.strToBigDecimal(arr[15]));// 毛利额占比(占所有小分类)
			subSellDirectDay.setSellQuantityProportionD(PeripheralParseUtils.strToBigDecimal(arr[16]));// 销售量占比(占所有明细类)
			subSellDirectDay.setSellTotalPriceProportionD(PeripheralParseUtils.strToBigDecimal(arr[17]));// 销售额占比(占所有明细类)
			subSellDirectDay.setSellProfitProportionD(PeripheralParseUtils.strToBigDecimal(arr[18]));// 毛利额占比(占所有明细类)
			subSellDirectDay.setSellStoreQuantity(PeripheralParseUtils.strToInteger(arr[19]));// 销售店天数

			return subSellDirectDay;
		} else {
			return null;
		}
	}

	// 删除商品中分类
	@Override
	public void deleteMerchandiseSellDirectDay(Map<String, Object> map) {
		merchandiseSellDirectDayDao.delateMerchandiseSellDirectDay(map);
	}
}
