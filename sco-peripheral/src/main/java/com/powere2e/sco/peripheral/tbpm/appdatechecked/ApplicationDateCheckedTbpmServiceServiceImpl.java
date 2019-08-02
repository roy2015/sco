package com.powere2e.sco.peripheral.tbpm.appdatechecked;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoService;
import com.powere2e.sco.interfaces.service.peripheral.sap.WorkingDayService;
import com.powere2e.sco.interfaces.service.peripheral.tbpm.appdatechecked.ApplicationDateCheckedTbpmService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.ApplicationScheduleM2;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;
import com.powere2e.sco.peripheral.sap.WorkingDayServiceImpl;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.datamaintenance.purchaserdata.merstandproinfo.MerStandProInfoServiceImpl;

/**
 * 审批日期进度检查
 * 
 * @author Gavillen.Zhou
 * @since 2015年12月29日
 * @version 1.0
 */
@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.tbpm.appdatechecked.ApplicationDateCheckedTbpmService")
public class ApplicationDateCheckedTbpmServiceServiceImpl implements
		ApplicationDateCheckedTbpmService {

	/**
	 * 审批日期进度检查
	 */
	@Override
	public String completeCheckedApplicationDate(String oaApplicationCode,
			String intentionCode, String supplierCode, String dateName,
			String dateStr) {
		// 日志消息
		String msg = "[单号-商品编号-供应商编号]:" + (oaApplicationCode) + ("-")
				+ (intentionCode) + ("-") + (supplierCode);
		String header = "TBPM进度日期-" + msg + "\r\n";
		String errMsg = header + dateName + "[" + dateStr + "]" + "格式不正确!";
		PeripheralFileUtils.logger.error("接收到数据[正常日志记录]：" + header + dateName + "[" + dateStr + "]");

		Date date = DateUtils.formatStrToDate(dateStr, "yyyyMMdd");
		// 校验日期
		if (!this.validateDate(dateStr, date, errMsg)) {
			return "error";
		}
		return this.compareDateAndCalculateDay(oaApplicationCode,
				intentionCode, supplierCode, dateName, date, header);

	}

	/**
	 * 计算比较日期
	 * 
	 * @param code
	 *            相比编号
	 * @param intentionCode
	 *            商品编号
	 * @param supplierCode
	 *            供应商配合
	 * @param dateName
	 *            日期名称
	 * @param date
	 *            日期
	 * @param header
	 *            头部消息
	 * @return error/success
	 */
	private String compareDateAndCalculateDay(String code,
			String intentionCode, String supplierCode, String dateName,
			Date date, String header) {
	
		MerStandProInfoService merProService = MerStandProInfoServiceImpl.getInstance();//用于查询审批信息和标准进度信息
		WorkingDayService workDayService = WorkingDayServiceImpl.getInstance();//查询工作日
		Map<String, Object> map = new HashMap<String, Object>();//查询条件
		
		String dateType1 = "巡厂申请提交日期、巡厂申请审核通过日期、品控实际巡厂日期";
		String dateType2 = "新品引进申请提交日期、新品引进审批通过日期";
		String dateType4 = "包装设计申请提交日期、包装设计申请完成日期、包装设计初稿确认日期、包装设计完稿通过日期";
		String dateType3 = "主数据申请审批通过日期";
		String dateType5 = "新品发放日期";
		
		try {
			if (dateType1.contains(dateName)) {
				map.put("dateType", "dateType1");
				map.put("applicationVfCode", code);// SCO巡厂申请单号
				map.put("supplierCode", supplierCode);// 供应商编号
				List<ApplicationScheduleM2> appSchedules = merProService.searchAppByCodeAndMerchandise(map);// 审批商品信息
				if (appSchedules.isEmpty()) {
					PeripheralFileUtils.logger.error(header + "[" + dateName + "]未查询到审批信息!");
					return "error";
				}

				// 根据设置进度日期和实际日期计算
				for (ApplicationScheduleM2 appSchedule : appSchedules) {
					MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);//查询设置的最近的标准进度
					if (merPro == null) {
						PeripheralFileUtils.logger.error(header + "未查询到标准进度,审批单号["+ appSchedule.getApplicationCode() + "]!\r\n");
						merPro = new MerStandProInfo();
					}
					appSchedule.setApplicationVfCode(code);
					//0.计算总天数
					this.setAppSchedule(appSchedule, merPro, workDayService);
					Date qryxpDate = appSchedule.getQryxpDate();// 试吃通过日期
					
					if ("巡厂申请提交日期".equals(dateName)) {
						// 1.巡厂申请提交日期
						appSchedule.setXcsqtjDate(date);
						// 2.巡厂申请提交标准天数
						Integer xcsqtjBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate3());
						appSchedule.setXcsqtjBzDay(xcsqtjBzDay);
						if (qryxpDate != null && date != null) {
							// 3.巡厂申请提交实际天数
							int xcsqtjSjDay = workDayService
									.searchWeekDayByDate(
											DateUtils.getCalendar(qryxpDate).getTime(), 
											DateUtils.getCalendar(date).getTime());
							appSchedule.setXcsqtjSjDay(xcsqtjSjDay);
							// 4.巡厂申请提交状态
							if (xcsqtjBzDay != null) appSchedule.setXcsqtjStatus((xcsqtjSjDay <= xcsqtjBzDay ? "0": "1"));
						}
					} else if ("巡厂申请审核通过日期".equals(dateName)) {
						// 1.巡厂申请审核通过日期
						appSchedule.setXcsqshtgDate(date);
						// 2.巡厂申请审核通过标准天数[无标准天数]
						// 3.巡厂申请审核通过实际天数
						Date xcsqtjDay = appSchedule.getXcsqtjDate();
						if (xcsqtjDay != null && date != null) {
							int xcsqshtgSjDay = workDayService
									.searchWeekDayByDate(
											DateUtils.getCalendar(xcsqtjDay).getTime(), 
											DateUtils.getCalendar(date).getTime());
							appSchedule.setXcsqshtgSjDay(xcsqshtgSjDay);
						}
					} else if ("品控实际巡厂日期".equals(dateName)) {
						// 1.品控实际巡厂日期
						appSchedule.setPksjxcDate(date);
						// 2.品控实际巡厂标准天数
						Integer pksjxcBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate7());
						appSchedule.setPksjxcBzDay(pksjxcBzDay);
						
						Date xcsqshtgDate = appSchedule.getXcsqshtgDate();
						if (xcsqshtgDate != null && date != null) {
							//3.品控实际巡厂实际天数
							int pksjxcSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(xcsqshtgDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setPksjxcSjDay(pksjxcSjDay);
							//4.品控实际巡厂状态
							if (pksjxcBzDay != null)appSchedule.setPksjxcStatus((pksjxcSjDay <= pksjxcBzDay ? "0" : "1"));
						}
					}
					// 插入或更新进度信息
					merProService.completeAppSchedule(appSchedule);
				}
			} else if (dateType2.contains(dateName)) {
				// 1.查询商品进度信息
				map.put("dateType", "dateType2");
				map.put("oaApplicationCode", code);
				map.put("intentionCode", intentionCode);
				map.put("supplierCode", supplierCode);// 供应商编号
				List<ApplicationScheduleM2> appSchedules = merProService.searchAppByCodeAndMerchandise(map);// 审批商品信息
				if (appSchedules.isEmpty()) {
					PeripheralFileUtils.logger.error(header + "[" + dateName + "]未查询到审批信息!");
					return "error";
				}

				for (ApplicationScheduleM2 appSchedule : appSchedules) {
					// 2.查询最近的一条标准进度数据
					MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);
					if (merPro == null) {
						PeripheralFileUtils.logger.error(header + "未查询到标准进度,审批单号[" + appSchedule.getApplicationCode() + "]!\r\n");
						merPro = new MerStandProInfo();
					}
					// 0.计算总天数
					this.setAppSchedule(appSchedule, merPro, workDayService);
					Date qryxpDate = appSchedule.getQryxpDate();// 试吃通过日期
					if ("新品引进申请提交日期".equals(dateName)) {
						// 1.新品引进申请提交日期
						appSchedule.setXpyjbgtjDate(date);
						// 2.新品引进报告提交标准天数
						Integer xpyjbgtjBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate4());
						appSchedule.setXpyjbgtjBzDay(xpyjbgtjBzDay);

						if (qryxpDate != null && date != null) {
							// 3.新品引进报告提交实际天数
							int xpyjbgtjSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(qryxpDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setXpyjbgtjSjDay(xpyjbgtjSjDay);
							// 4.新品引进报告提交状态
							if (xpyjbgtjBzDay != null) appSchedule.setXpyjbgtjStatus((xpyjbgtjSjDay <= xpyjbgtjBzDay ? "0" : "1"));
						}
					} else if ("新品引进审批通过日期".equals(dateName)) {
						// 1.新品引进报告审批通过日期
						appSchedule.setXpyjbgsptgDate(date);
						// 2.新品引进报告审批通过标准天数
						Integer xpyjbgsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate5());
						appSchedule.setXpyjbgsptgBzDay(xpyjbgsptgBzDay);

						Date xpyjbgtjDate = appSchedule.getXpyjbgtjDate();
						if (xpyjbgtjDate != null && date != null) {
							// 3.新品引进报告审批通过实际天数
							int xpyjbgsptgSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(xpyjbgtjDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setXpyjbgsptgSjDay(xpyjbgsptgSjDay);
							// 4.新品引进报告审批通过状态
							if (xpyjbgsptgBzDay != null) appSchedule.setXpyjbgsptgStatus((xpyjbgsptgSjDay <= xpyjbgsptgBzDay ? "0" : "1"));
						}
					}
					// 插入或更新进度信息
					merProService.completeAppSchedule(appSchedule);
				}
			} else if (dateType3.contains(dateName)) {
				// 1.查询商品进度信息
				map.put("dateType", "dateType3");
				map.put("intentionCode", intentionCode);
				map.put("supplierCode", supplierCode);// 供应商编号
				List<ApplicationScheduleM2> appSchedules = merProService.searchAppByCodeAndMerchandise(map);// 审批商品信息
				if (appSchedules.isEmpty()) {
					PeripheralFileUtils.logger.error(header + "[" + dateName + "]未查询到审批信息!");
					return "error";
				}
				
				for (ApplicationScheduleM2 appSchedule : appSchedules) {
					// 查询最近的一条标准进度数据
					MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);
					if (merPro == null) {
						PeripheralFileUtils.logger.error(header + "未查询到标准进度,审批单号["+ appSchedule.getApplicationCode() + "]!\r\n");
						merPro = new MerStandProInfo();
					}
					// 0.计算总天数
					this.setAppSchedule(appSchedule, merPro, workDayService);
					
					//1.主数据申请审批通过日期
					appSchedule.setZsjsqsptgDate(date);
					//2.主数据申请审批通过标准天数
					Integer zsjsqsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate8());
					appSchedule.setZsjsqsptgBzDay(zsjsqsptgBzDay);
					
					Date xpyjbgsptgDate = appSchedule.getXpyjbgsptgDate();
					if (xpyjbgsptgDate != null && date != null) {
						//3.主数据申请审批通过实际天数
						int zsjsqsptgSjDay = workDayService.searchWeekDayByDate(
								DateUtils.getCalendar(xpyjbgsptgDate).getTime(), 
								DateUtils.getCalendar(date).getTime());
						appSchedule.setZsjsqsptgSjDay(zsjsqsptgSjDay);
						//4.主数据申请审批通过状态
						if (zsjsqsptgBzDay != null) appSchedule.setZsjsqsptgStatus((zsjsqsptgSjDay <= zsjsqsptgBzDay ? "0" : "1"));
					}
					// 插入或更新进度信息
					merProService.completeAppSchedule(appSchedule);
				}
			} else if (dateType4.contains(dateName)) {
				// 1.查询商品进度信息
				map.put("dateType", "dateType4");
				map.put("applicationPdCode", code);//SCO包装设计申请单号
				map.put("intentionCode", intentionCode);
				map.put("supplierCode", supplierCode);// 供应商编号
				List<ApplicationScheduleM2> appSchedules = merProService.searchAppByCodeAndMerchandise(map);// 审批商品信息
				if (appSchedules.isEmpty()) {
					PeripheralFileUtils.logger.error(header + "[" + dateName + "]未查询到审批信息!");
					return "error";
				}
				
				for (ApplicationScheduleM2 appSchedule : appSchedules) {
					// 查询最近的一条标准进度数据
					MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);
					if (merPro == null) {
						PeripheralFileUtils.logger.error(header + "未查询到标准进度,审批单号["+ appSchedule.getApplicationCode() + "]!\r\n");
						merPro = new MerStandProInfo();
					}
					appSchedule.setApplicationPdCode(code);
					// 0.计算总天数
					this.setAppSchedule(appSchedule, merPro, workDayService);
					Date qryxpDate = appSchedule.getQryxpDate();// 试吃通过日期

					if ("包装设计申请提交日期".equals(dateName)) {
						// 1.包装设计申请日期
						appSchedule.setBzsjsqDate(date);
						// 2.包装设计申请标准天数
						Integer bzsjsqBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate2());
						appSchedule.setBzsjsqBzDay(bzsjsqBzDay);

						if (qryxpDate != null && date != null) {
							// 3.包装设计申请实际天数
							int bzsjsqSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(qryxpDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setBzsjsqSjDay(bzsjsqSjDay);
							// 4.包装设计申请状态
							if (bzsjsqBzDay != null) appSchedule.setBzsjsqStatus((bzsjsqSjDay <= bzsjsqBzDay ? "0" : "1"));
						}
					} else if ("包装设计申请完成日期".equals(dateName)) {
						// 1.包装设计申请完成日期
						appSchedule.setBzsjsqwcDate(date);
						// 2.包装设计申请完成标准天数
						Integer bzsjsqwcBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate6());
						appSchedule.setBzsjsqwcBzDay(bzsjsqwcBzDay);

						Date bzsjsqDate = appSchedule.getBzsjsqDate();
						if (bzsjsqDate != null && date != null) {
							// 3.包装设计申请完成实际天数
							int bzsjsqwcSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(bzsjsqDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setBzsjsqwcSjDay(bzsjsqwcSjDay);
							// 4.包装设计申请完成状态
							if (bzsjsqwcBzDay != null) appSchedule.setBzsjsqwcStatus((bzsjsqwcSjDay <= bzsjsqwcBzDay ? "0" : "1"));
						}
					} else if ("包装设计初稿确认日期".equals(dateName)) {
						// 1.包装设计初稿确认日期
						appSchedule.setBzsjcgqrDate(date);
						// 2.包装设计初稿确认标准天数
						Integer bzsjcgqrBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate9());
						appSchedule.setBzsjcgqrBzDay(bzsjcgqrBzDay);

						Date bzsjsqwcDate = appSchedule.getBzsjsqwcDate();
						if (bzsjsqwcDate != null && date != null) {
							// 3.包装设计申请完成实际天数
							int bzsjcgqrSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(bzsjsqwcDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setBzsjcgqrSjDay(bzsjcgqrSjDay);
							// 4.包装设计初稿确认状态
							if (bzsjcgqrBzDay != null) appSchedule.setBzsjcgqrStatus((bzsjcgqrSjDay <= bzsjcgqrBzDay ? "0" : "1"));
						}
					} else if ("包装设计完稿通过日期".equals(dateName)) {
						// 1.包装设计完稿通过日期
						appSchedule.setBzsjwgtgDate(date);

						// 2.距离包装设计初稿确认日期标准天数
						Integer jlbzsjcgqrBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate11());
						appSchedule.setJlbzsjcgqrBzDay(jlbzsjcgqrBzDay);
						// 3.距离包装设计初稿确认日期实际天数
						Date bzsjcgqrDate = appSchedule.getBzsjcgqrDate();// 包装设计初稿确认日期
						Integer jlwlzsjsqsptgSjDay = null;
						if (bzsjcgqrDate != null && date != null) {
							jlwlzsjsqsptgSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(bzsjcgqrDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setJlwlzsjsqsptgSjDay(jlwlzsjsqsptgSjDay);
						}

						// 4.距离物料主数据申请审批通过日期标准天数
						Integer jlwlzsjsqsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate10());
						appSchedule.setJlwlzsjsqsptgBzDay(jlwlzsjsqsptgBzDay);
						// 5.距离包装设计初稿确认日期实际天数
						Date zsjsqsptgDate = appSchedule.getZsjsqsptgDate();// 主数据申请审批通过日期
						Integer jlbzsjcgqrSjDay = null;
						if (zsjsqsptgDate != null && date != null) {
							jlbzsjcgqrSjDay = workDayService.searchWeekDayByDate(
									DateUtils.getCalendar(zsjsqsptgDate).getTime(), 
									DateUtils.getCalendar(date).getTime());
							appSchedule.setJlbzsjcgqrSjDay(jlbzsjcgqrSjDay);
						}

						if (date != null) {
							if (bzsjcgqrDate != null && zsjsqsptgDate != null) {
								if (zsjsqsptgDate.after(bzsjcgqrDate)) {// zsjsqsptgDate > bzsjcgqrDate
									bzsjcgqrDate = null;
								} else {
									zsjsqsptgDate = null;// 当包装设计初稿确认日期 >= 物料主数据申请审批通过日期,为"包装设计初稿确认日期"
								}
							}
							if (bzsjcgqrDate != null && zsjsqsptgDate == null) {// jlwlzsjsqsptgSjDay不为null,zsjsqsptgDate为null
								if (jlwlzsjsqsptgSjDay != null && jlbzsjcgqrBzDay != null) {
									appSchedule.setBzsjwgtgStatus((jlwlzsjsqsptgSjDay <= jlbzsjcgqrBzDay ? "0" : "1"));
								}
							} else if (bzsjcgqrDate == null
									&& zsjsqsptgDate != null) {// jlwlzsjsqsptgSjDay为null,zsjsqsptgDate不为null
								if (jlbzsjcgqrSjDay != null && jlwlzsjsqsptgBzDay != null) {
									appSchedule.setBzsjwgtgStatus((jlbzsjcgqrSjDay <= jlwlzsjsqsptgBzDay ? "0" : "1"));
								}
							}
						}
					}
					// 插入或更新进度信息
					merProService.completeAppSchedule(appSchedule);
				}
			} else if (dateType5.contains(dateName)) {
				// 1.查询商品进度信息
				map.put("dateType", "dateType5");
				map.put("sapMerchandise", intentionCode);
				List<ApplicationScheduleM2> appSchedules = merProService.searchAppByCodeAndMerchandise(map);// 审批商品信息
				if (appSchedules.isEmpty()) {
					PeripheralFileUtils.logger.error(header + "[" + dateName + "]未查询到审批信息!");
					return "error";
				}
				
				for (ApplicationScheduleM2 appSchedule : appSchedules) {
					// 查询最近的一条标准进度数据
					MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);
					if (merPro == null) {
						PeripheralFileUtils.logger.error(header + "未查询到标准进度,审批单号["+ appSchedule.getApplicationCode() + "]!\r\n");
						merPro = new MerStandProInfo();
					}
					// 0.计算总天数
/*					Integer sctgSjDay = this.setAppSchedule(appSchedule, merPro, workDayService);*/
					
					//1.新品发放日期
					appSchedule.setXpffDate(date);
					//2.新品发放标准天数
					Integer xpffBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate12());
					appSchedule.setXpffBzDay(xpffBzDay);
					
					Date bzsjwgtgDate = appSchedule.getBzsjwgtgDate();
					if (bzsjwgtgDate != null && date != null) {
						//3.新品发放实际天数
						int xpffSjDay = workDayService.searchWeekDayByDate(
								DateUtils.getCalendar(bzsjwgtgDate).getTime(), 
								DateUtils.getCalendar(date).getTime());
						appSchedule.setXpffSjDay(xpffSjDay);
						//4.新品发放状态
						if (xpffBzDay != null) appSchedule.setXpffStatus((xpffSjDay <= xpffBzDay ? "0" : "1"));
					}
					//实际总天数sumInteger
					/*Integer sjDay1 = DecimalFormatUtils.sumInteger(sctgSjDay, appSchedule.getXpyjbgtjSjDay(), 
							appSchedule.getXpyjbgsptgSjDay(), appSchedule.getZsjsqsptgSjDay(),
							appSchedule.getJlbzsjcgqrSjDay(), appSchedule.getXpffSjDay());
					Integer sjDay2 = DecimalFormatUtils.sumInteger(sctgSjDay,appSchedule.getBzsjsqSjDay(),
							appSchedule.getBzsjsqwcSjDay(), appSchedule.getBzsjcgqrSjDay(),
							appSchedule.getJlwlzsjsqsptgSjDay(), appSchedule.getXpffSjDay());
					
					if (sjDay1 != null && sjDay2 != null) {
						if (sjDay1 > sjDay2) {//比较两条路线,找出较大的
							sjDay2 = null;
						} else {
							sjDay1 = null;
						}
					}
					//计算实际总天数
					if (sjDay1 != null && sjDay2 == null) {
						appSchedule.setSjDay(sjDay1);
					} else if (sjDay1 == null && sjDay2 != null) {
						appSchedule.setSjDay(sjDay2);
					}*/
					//实际总天数sumInteger
					Integer sumDay = workDayService.searchWeekDayByDate(DateUtils.getCalendar(appSchedule.getCreated()).getTime(), 
							DateUtils.getCalendar(appSchedule.getXpffDate()).getTime());
					appSchedule.setSjDay(sumDay);
					// 插入或更新进度信息
					merProService.completeAppSchedule(appSchedule);
				}
			} else {
				PeripheralFileUtils.logger.error(header + "不知道时间名称:[" + dateName + "]");
				return "error";
			}
		} catch (Exception e) {
			PeripheralFileUtils.logger.error(header + "检查审批日期时出错 [日期名称:"+ dateName + "]\r\n".concat(StrUtils.getStackMsgToStr(e)));
			e.printStackTrace();
			return "error";
		}
		return "success";
	}

	/**
	 * 设置相关天数
	 * 
	 * @param appSchedule
	 *            审批进度信息
	 * @param merPro
	 *            设置的标准进度
	 * @param workDayService
	 *            工作日业务实例
	 * @return 试吃通过实际天数(可能为Null)
	 */
	private Integer setAppSchedule(ApplicationScheduleM2 appSchedule,
			MerStandProInfo merPro, WorkingDayService workDayService) {
		// 试吃通过标准天数
		appSchedule.setSctgBzDay(DecimalFormatUtils.getInteger(merPro
				.getProcessDate1()));
		// 试吃通过实际天数
		Date qryxpDate = appSchedule.getQryxpDate();
		Integer sctgSjDay = null;
		if (qryxpDate != null) {
			sctgSjDay = workDayService.searchWeekDayByDate(
					DateUtils.getCalendar(appSchedule.getCreated()).getTime(), 
					DateUtils.getCalendar(qryxpDate).getTime());
			appSchedule.setSctgSjDay(sctgSjDay);
		}

		// 标准总天数
		appSchedule.setBzDay(DecimalFormatUtils.getInteger(merPro.getSumDay()));
		// 生效日期
		appSchedule.setStartDate(merPro.getStartDate());
		// 结束日期
		appSchedule.setEndDate(merPro.getEndDate());
		return sctgSjDay;
	}

	/**
	 * 校验日期格式
	 * 
	 * @param dateStr
	 *            日期
	 * @param errMsg
	 *            错误消息
	 * @return 是否正确
	 */
	private Boolean validateDate(String dateStr, Date date, String errMsg) {
		// 校验时间格式
		boolean ifNotNull = StringUtils.isNotBlank(dateStr);

		if (ifNotNull) {
			if (date == null) {
				PeripheralFileUtils.logger.error(errMsg);// 格式有误
				return false;
			} else {
				String tmp = DateUtils.formatDateToStr(date, "yyyyMMdd");
				if (!dateStr.equals(tmp)) {
					PeripheralFileUtils.logger.error(errMsg);// 格式正确但年月日不规范
					return false;
				}
				return true;
			}
		} else {
			PeripheralFileUtils.logger.error(errMsg);// 日期为空
		}
		return false;
	}

}
