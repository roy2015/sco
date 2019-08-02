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
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDjDayDao;
import com.powere2e.sco.interfaces.service.peripheral.bw.MerchandiseSellDjDayService;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellDjDay;
import com.powere2e.sco.peripheral.utils.PeripheralConfigFile;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.peripheral.utils.PeripheralParseUtils;

/**
 * 商品区域销售情况(日直营+加盟)业务类的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDjDayServiceImpl extends ServiceImpl implements MerchandiseSellDjDayService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1644587675619177959L;
	private MerchandiseSellDjDayDao merchandiseSellDjDayDao;

	// 获得商品区域销售情况(日直营+加盟)DAO实例
	public MerchandiseSellDjDayDao getMerchandiseSellDjDayDao() {
		return merchandiseSellDjDayDao;
	}

	// 设置商品区域销售情况(日直营+加盟)DAO实例
	public void setMerchandiseSellDjDayDao(MerchandiseSellDjDayDao merchandiseSellDjDayDao) {
		this.merchandiseSellDjDayDao = merchandiseSellDjDayDao;
	}

	// 添加
	@Override
	public void insertMerchandiseSellDjDay() {
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
		File file = new File(fileRootPath.concat(File.separator).concat(PeripheralConfigFile.BW_PACKAGE).concat(File.separator).concat(PeripheralConfigFile.MERCHANDISE_SELL_D_J_DAY));// 获取指定目录的文件
		if (!file.exists()) {
			return;
		} else {
			completeInsertMerchandiseSellDjDay(file, charsetCode, processCount, fileRootPath);// 解析文件
		}
	}

	@Override
	public void completeInsertMerchandiseSellDjDay(File file, String charsetCode, int processCount, String fileRootPath) {
		PeripheralFileUtils.logger.info("开始解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_D_J_DAY);
		Calendar cal = Calendar.getInstance();
		if (DateUtils.isFirstDay() && cal.get(Calendar.HOUR_OF_DAY) >= 18) {
			String date = DateUtils.formatDateToStr(new Date(), "yyyy-MM-dd");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("date", date);
			this.deleteMerchandiseSellDjDay(map);
		}
		BufferedReader bf = IOUtils.newBufferedReader(file, charsetCode);
		MerchandiseSellDjDay merchandiseSellDjDay = new MerchandiseSellDjDay();
		List<MerchandiseSellDjDay> list = new ArrayList<MerchandiseSellDjDay>();
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
				MerchandiseSellDjDay subSellDjDay = convertSellDjDayLine2Obj(line);
				if (subSellDjDay != null) {
					processLine++;
					list.add(subSellDjDay);
					merchandiseSellDjDay.setList(list);
				}
				validCount++;
			} catch (Exception e) {
				PeripheralFileUtils.logger.error(file.getName() + "行：" + lineCount + " [该行数据不符合规则！]", e);
			}
			if (processLine == processCount) {
				merchandiseSellDjDayDao.insertMerchandiseSellDjDay(merchandiseSellDjDay.toMap());
				processLine = 0;
				list.clear();
			}
		}
		if (!list.isEmpty()) {
			merchandiseSellDjDayDao.insertMerchandiseSellDjDay(merchandiseSellDjDay.toMap());
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
		PeripheralFileUtils.logger.info("成功解析文件:" + PeripheralConfigFile.MERCHANDISE_SELL_D_J_DAY + " - " + validCount + " 行数据");
	}

	public MerchandiseSellDjDay convertSellDjDayLine2Obj(String line) throws Exception {
		String arr[] = StringUtils.splitPreserveAllTokens(line, PeripheralConfigFile.LINE_SEPARATOR);
		if (arr.length >= 20) {
			MerchandiseSellDjDay subSellDjDay = new MerchandiseSellDjDay();
			subSellDjDay.setMerchandiseCode(PeripheralParseUtils.trimString(arr[0]));// 商品编码
			subSellDjDay.setSellRegion(PeripheralParseUtils.trimString(arr[1]));// 销售地区编号,这里和日直营/加盟的有区别，将区域和日期顺序交换
			subSellDjDay.setSellDate(PeripheralParseUtils.strToDate(arr[2]));// 销售日期
			subSellDjDay.setSupplierCode(PeripheralParseUtils.trimString(arr[3]));// 供应商编码

			subSellDjDay.setSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[4]));// 销售量
			subSellDjDay.setSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[5]));// 销售额
			subSellDjDay.setSellProfit(PeripheralParseUtils.strToBigDecimal(arr[6]));// 毛利额
			subSellDjDay.setPsdSellQuantity(PeripheralParseUtils.strToBigDecimal(arr[7]));// PSD销量
			subSellDjDay.setPsdSellTotalPrice(PeripheralParseUtils.strToBigDecimal(arr[8]));// PSD销售额
			subSellDjDay.setPsdSellProfit(PeripheralParseUtils.strToBigDecimal(arr[9]));// PSD毛利额
			subSellDjDay.setSellQuantityProportionM(PeripheralParseUtils.strToBigDecimal(arr[10]));// 销售量占比(占所有商品)
			subSellDjDay.setSellTotalPriceProportionM(PeripheralParseUtils.strToBigDecimal(arr[11]));// 销售额占比(占所有商品)
			subSellDjDay.setSellProfitProportionM(PeripheralParseUtils.strToBigDecimal(arr[12]));// 毛利额占比(占所有商品)
			subSellDjDay.setSellQuantityProportionS(PeripheralParseUtils.strToBigDecimal(arr[13]));// 销售量占比(占所有小分类)
			subSellDjDay.setSellTotalPriceProportionS(PeripheralParseUtils.strToBigDecimal(arr[14]));// 销售额占比(占所有小分类)
			subSellDjDay.setSellProfitProportionS(PeripheralParseUtils.strToBigDecimal(arr[15]));// 毛利额占比(占所有小分类)
			subSellDjDay.setSellQuantityProportionD(PeripheralParseUtils.strToBigDecimal(arr[16]));// 销售量占比(占所有明细类)
			subSellDjDay.setSellTotalPriceProportionD(PeripheralParseUtils.strToBigDecimal(arr[17]));// 销售额占比(占所有明细类)
			subSellDjDay.setSellProfitProportionD(PeripheralParseUtils.strToBigDecimal(arr[18]));// 毛利额占比(占所有明细类)
			subSellDjDay.setSellStoreQuantity(PeripheralParseUtils.strToInteger(arr[19]));// 销售店天数

			return subSellDjDay;
		} else {
			return null;
		}
	}

	@Override
	public void deleteMerchandiseSellDjDay(Map<String, Object> map) {
		merchandiseSellDjDayDao.delateMerchandiseSellDjDay(map);
	}
}