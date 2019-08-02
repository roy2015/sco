package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseDataDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseDataService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata.MerchandisePromotionPurchaseData;

/**
 * 商品促销进货价格维护业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public class MerchandisePromotionPurchaseDataServiceImpl extends ServiceImpl implements MerchandisePromotionPurchaseDataService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6290647478807216419L;
	private MerchandisePromotionPurchaseDataDao merchandisePromotionPurchaseDataDao;

	public static MerchandisePromotionPurchaseDataService getInstance() {
		return (MerchandisePromotionPurchaseDataService) ConfigFactory.getInstance().getBean("merchandisePromotionPurchaseDataManager");
	}

	// 获得商品促销进货价格维护DAO实例
	public MerchandisePromotionPurchaseDataDao getMerchandisePromotionPurchaseDataDao() {
		return merchandisePromotionPurchaseDataDao;
	}

	// 设置商品促销进货价格维护DAO实例
	public void setMerchandisePromotionPurchaseDataDao(MerchandisePromotionPurchaseDataDao merchandisePromotionPurchaseDataDao) {
		this.merchandisePromotionPurchaseDataDao = merchandisePromotionPurchaseDataDao;
	}

	// 查询
	@Override
	public List<MerchandisePromotionPurchaseData> listMerchandisePromotionPurchaseData(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandisePromotionPurchaseDataDao().listMerchandisePromotionPurchaseData(map, pageInfo);
	}

	// 删除
	@Override
	public void deleteMerchandisePromotionPurchaseData(Map<String, Object> map) {
		this.getMerchandisePromotionPurchaseDataDao().deleteMerchandisePromotionPurchaseData(map);
	}

	@Override
	public String completeImportMerchandisePromotionPurchaseData(File file) {
		// 用于存储消息
		String msg = "";
		// 用于存储excel中的数据
		List<MerchandisePromotionPurchaseData> dataList = new ArrayList<MerchandisePromotionPurchaseData>();
		try {
			// 注册Double类型默认值为null
			dataList = this.parseExcel(file, dataList);
		} catch (Exception e) {
			msg = e.getMessage();
		} finally {
			// 取消注册Double类型默认值为null
			// ConvertUtils.deregister();
		}
		if (!msg.equals("")) {// 有错误消息
			return msg.toString();
		} else if (dataList.isEmpty()) {// 文件中无数据
			return "请在文件中填写需上传数据";
		} else {
			this.insertMerchandisePromotionPurchaseData(dataList);
		}
		return null;
	}

	/**
	 * 新增商品促销进货价格维护数据
	 * 
	 * @param dataList
	 */
	private void insertMerchandisePromotionPurchaseData(List<MerchandisePromotionPurchaseData> list) {
		// 批量插入
		Map<String, Object> map = new HashMap<String, Object>();
		// List分批插入(每批100条)
		int listSize = list.size();
		int maxCount = listSize / Constant.BATCH_SIZE;
		int i = 0;
		for (; i < maxCount; i++) {
			int toIndex = (i + 1) * Constant.BATCH_SIZE > listSize ? listSize : (i + 1) * Constant.BATCH_SIZE;
			if (list.size() != 0) {
				map.put("list", list.subList(i * Constant.BATCH_SIZE, toIndex));
				this.merchandisePromotionPurchaseDataDao.insertMerchandisePromotionPurchaseData(map);
			}
		}
		// 防止遗漏数据
		int currentIndex = i * Constant.BATCH_SIZE;
		if (currentIndex < list.size()) {
			if (list.size() != 0) {
				map.put("list", list.subList(currentIndex, list.size()));
				this.merchandisePromotionPurchaseDataDao.insertMerchandisePromotionPurchaseData(map);
			}
		}
	}

	/**
	 * 解析Excel
	 * 
	 * @param file
	 *            上传的文件
	 * @param mList
	 *            商品编码
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的商品促销进货价格维护
	 * @throws Exception
	 */
	private List<MerchandisePromotionPurchaseData> parseExcel(File file, final List<MerchandisePromotionPurchaseData> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		// 获取表格(开始行号)
		int idx = 2;
		StringBuilder sb = new StringBuilder();// 存储错误信息
		for (; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			MerchandisePromotionPurchaseData msd = this.processRow(idx, rowData, sb);
			if (sb.length() == 0 && msd != null) {
				dataList.add(msd);
			}

		}
		// 检测是否有错误
		if (sb.length() > 0) {
			throw new EscmException(sb.toString());
		}
		return dataList;
	}

	/**
	 * 处理excel一行的数据
	 * 
	 * @param idx
	 *            当前行数
	 * @param rowData
	 *            当前行数据
	 * @param mList
	 *            商品主数据
	 * @param msg
	 *            错误信息
	 * @return
	 * @throws ParseException
	 */
	private MerchandisePromotionPurchaseData processRow(int idx, ExcelRow rowData, StringBuilder msg) throws ParseException {
		StringBuilder sb = new StringBuilder();
		String dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		String dataB = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
		String dataC = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
		String dataD = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D"));
		String dataE = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("E"));
		String dataF = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("F"));
		String dataG = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("G"));
		if ("".equals(dataA) && "".equals(dataB) && "".equals(dataC) && "".equals(dataD) && "".equals(dataE) && "".equals(dataF) && "".equals(dataG)) {
			return null;
		}
		String regEx = "[0-9]{4}+[-]+[0-9]{2}+[-]+[0-9]{2}";
		// 校验A列数据--促销活动名称
		if (dataA == null || dataA.length() == 0) {
			sb.append("第" + idx + "行A列:促销活动名称不可为空<br>");
		} else {
			if (StrUtils.getLength(dataA) > 100) {
				sb.append("第" + idx + "行A列:促销活动名称长度超过100<br>");
			}
		}

		// 校验B列数据--促销档期
		if (dataB == null || dataB.length() == 0) {
			sb.append("第" + idx + "行B列:活动档期不可为空<br>");
		} else {
			if (StrUtils.getLength(dataB) > 100) {
				sb.append("第" + idx + "行B列:活动档期长度超过100<br>");
			}
		}
		// 校验C列数据--物料号
		if (dataC == null || dataC.length() == 0) {
			sb.append("第" + idx + "行C列:商品编号不可为空<br>");
		} else {
			if (StrUtils.getLength(dataC) > 30) {
				sb.append("第" + idx + "行C列:商品编号长度超过30<br>");
			}
		}
		// 校验D列数据--供应商号
		if (dataD == null || dataD.length() == 0) {
			sb.append("第" + idx + "行D列:供应商编号不可为空<br>");
		} else {
			if (StrUtils.getLength(dataD) > 30) {
				sb.append("第" + idx + "行D列:供应商编号长度超过30<br>");
			}
		}
		// 校验E列数据--开始日期

		if (dataE == null || dataE.length() == 0) {
			sb.append("第" + idx + "行E列:开始日期不可为空<br>");
		} else {
			if (!dataE.matches(regEx)) {
				sb.append("第" + idx + "行E列:开始日期格式不符，格式应为YYYY-MM-DD<br>");
			}
		}
		// 校验F列数据--结束日期
		if (dataF == null || dataF.length() == 0) {
			sb.append("第" + idx + "行F列:抽检日期不可为空<br>");
		} else {
			if (!dataF.matches(regEx)) {
				sb.append("第" + idx + "行F列:抽检日期格式不符，格式应为YYYY-MM-DD<br>");
			}
		}
		// 校验G列数据--促销价格
		if (dataG == null || dataG.length() == 0) {
			sb.append("第" + idx + "行G列:促销价格不可为空<br>");
		} else {
			if (!dataG.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
				sb.append("第" + idx + "行G列:促销价格不是数字<br>");
			}
		}
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		BigDecimal b = new BigDecimal(dataG);
		b = b.setScale(2, BigDecimal.ROUND_HALF_UP); // 四舍五入
		return new MerchandisePromotionPurchaseData(dataB, dataA, dataC, dataD, dataE, dataF, b);
	}
}