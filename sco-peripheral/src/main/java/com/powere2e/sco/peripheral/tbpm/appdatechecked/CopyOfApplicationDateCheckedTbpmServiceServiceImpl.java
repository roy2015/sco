package com.powere2e.sco.peripheral.tbpm.appdatechecked;

import java.util.Date;

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
public class CopyOfApplicationDateCheckedTbpmServiceServiceImpl implements
		ApplicationDateCheckedTbpmService {

	/**
	 * 审批日期进度检查
	 */
	@Override
	public String completeCheckedApplicationDate(String oaApplicationCode,
			String intentionCode, String supplierCode, String dateName,
			String dateStr) {
		//返回消息
		String result = "error";
		//日志消息
		String header = "TBPM进度日期-" + dateStr + "\r\n";
		String msg = "[SCO申请单号-商品编号-供应商编号]:" + (oaApplicationCode) 
				+ ("-") + (intentionCode) + ("-") + (supplierCode);
		//校验时间格式
		Date date = DateUtils.formatStrToDate(dateStr, "yyyyMMdd");
		boolean ifNotNull = StringUtils.isNotBlank(dateStr);
		if (ifNotNull) {
			if (date == null) {
				PeripheralFileUtils.logger.error(header + dateName + "格式不正确!" + dateStr + msg);
				return result;
			} else {
				String tmp = DateUtils.formatDateToStr(date, "yyyyMMdd");
				if (!tmp.equals(dateStr)) {
					PeripheralFileUtils.logger.error(header + dateName + "格式不正确!" + dateStr + msg);
					return result;
				}
			}
		}
		
		try {
			MerStandProInfoService merProService = MerStandProInfoServiceImpl.getInstance();
			// 1.查询商品进度信息
			ApplicationScheduleM2 appSchedule = merProService.searchAppByCodeAndMerchandise(
							oaApplicationCode, intentionCode, supplierCode);
			if (appSchedule == null) {
				PeripheralFileUtils.logger.error(header + "未查询到审批单".concat(msg));
				return result;
			}
			
			// 2.查询最近的一条标准进度数据
			MerStandProInfo merPro = merProService.searchMerStandProInfoByCreated(appSchedule);
			if (merPro == null) {
				PeripheralFileUtils.logger.error(header + "未查询到商品标准进度信息数据!".concat(msg));
			}
			
			// 3.比较并计算
			boolean flag = this.completeCompareDateAndCalculateDay(dateName, date, appSchedule, merPro, msg, header);
			if (!flag) return "error";
			
			// 4.插入或更新
			merProService.completeAppSchedule(appSchedule);
			
			result = "success";
		} catch (Exception e) {
			PeripheralFileUtils.logger.error(header + "检查审批日期时出错".concat(msg)
					.concat("\r\n").concat(StrUtils.getStackMsgToStr(e)));
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据传入日期计算并比较天数
	 * 
	 * @param dateName
	 *            日期名称
	 * @param date
	 *            日期
	 * @param appSchedule
	 *            进度信息
	 * @param merPro
	 *            标准数据
	 * @param msg 审批单消息
	 * @return 是否需要执行新增/更新操作
	 */
	private boolean completeCompareDateAndCalculateDay(String dateName, Date date,
			ApplicationScheduleM2 appSchedule, MerStandProInfo merPro, String msg, String header) {
		if (merPro == null) {
			merPro = new MerStandProInfo();
		}
		WorkingDayService workDayService = WorkingDayServiceImpl.getInstance();
		
		//试吃通过标准天数
		appSchedule.setSctgBzDay(DecimalFormatUtils.getInteger(merPro.getProcessDate1()));
		//试吃通过实际天数
		Date qryxpDate = appSchedule.getQryxpDate();
		int sctgSjDay = workDayService.searchWeekDayByDate(appSchedule.getCreated(), qryxpDate);
		appSchedule.setSctgSjDay(sctgSjDay);
		
		//标准总天数
		appSchedule.setBzDay(DecimalFormatUtils.getInteger(merPro.getSumDay()));
		// 生效日期
		appSchedule.setStartDate(merPro.getStartDate());
		//结束日期
		appSchedule.setEndDate(merPro.getEndDate());
		
		//比较其他时间
		if ("巡厂申请提交日期".equals(dateName)) {
			//1.巡厂申请提交日期
			appSchedule.setXcsqtjDate(date);
			//2.巡厂申请提交标准天数
			Integer xcsqtjBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate3());
			appSchedule.setXcsqtjBzDay(xcsqtjBzDay);
			
			if (qryxpDate != null && date != null ) {
				//3.巡厂申请提交实际天数
				int xcsqtjSjDay = workDayService.searchWeekDayByDate(qryxpDate, date);
				appSchedule.setXcsqtjSjDay(xcsqtjSjDay);
				//4.巡厂申请提交状态
				if (xcsqtjBzDay != null) appSchedule.setXcsqtjStatus((xcsqtjSjDay <= xcsqtjBzDay ? "0" : "1"));
			}
		} else if ("巡厂申请审核通过日期".equals(dateName)) {  //*** 不检查
			//1.巡厂申请审核通过日期
			appSchedule.setXcsqshtgDate(date);
		}else if ("品控实际巡厂日期".equals(dateName)) {
			//1.品控实际巡厂日期
			appSchedule.setPksjxcDate(date);
			//2.品控实际巡厂标准天数
			Integer pksjxcBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate7());
			appSchedule.setPksjxcBzDay(pksjxcBzDay);
			
			Date xcsqshtgDate = appSchedule.getXcsqshtgDate();
			if (xcsqshtgDate != null && date != null) {
				//3.品控实际巡厂实际天数
				int pksjxcSjDay = workDayService.searchWeekDayByDate(xcsqshtgDate, date);
				appSchedule.setPksjxcSjDay(pksjxcSjDay);
				//4.品控实际巡厂状态
				if (pksjxcBzDay != null)appSchedule.setPksjxcStatus((pksjxcSjDay <= pksjxcBzDay ? "0" : "1"));
			}
			
		}else if ("新品引进申请提交日期".equals(dateName)) {
			//1.新品引进申请提交日期
			appSchedule.setXpyjbgtjDate(date);
			//2.新品引进报告提交标准天数
			Integer xpyjbgtjBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate4());
			appSchedule.setXpyjbgtjBzDay(xpyjbgtjBzDay);
			
			if (qryxpDate != null && date != null) {
				//3.新品引进报告提交实际天数
				int xpyjbgtjSjDay = workDayService.searchWeekDayByDate(qryxpDate, date);
				appSchedule.setXpyjbgtjSjDay(xpyjbgtjSjDay);
				//4.新品引进报告提交状态
				if (xpyjbgtjBzDay != null) appSchedule.setXpyjbgtjStatus((xpyjbgtjSjDay <= xpyjbgtjBzDay ? "0" : "1"));
			}
		}else if ("新品引进审批通过日期".equals(dateName)) {
			//1.新品引进报告审批通过日期
			appSchedule.setXpyjbgsptgDate(date);
			//2.新品引进报告审批通过标准天数
			Integer xpyjbgsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate5());
			appSchedule.setXpyjbgsptgBzDay(xpyjbgsptgBzDay);
			
			Date xpyjbgtjDate = appSchedule.getXpyjbgtjDate();
			if (xpyjbgtjDate != null && date != null) {
				//3.新品引进报告审批通过实际天数
				int xpyjbgsptgSjDay = workDayService.searchWeekDayByDate(xpyjbgtjDate, date);
				appSchedule.setXpyjbgsptgSjDay(xpyjbgsptgSjDay);
				//4.新品引进报告审批通过状态
				if (xpyjbgsptgBzDay != null) appSchedule.setXpyjbgsptgStatus((xpyjbgsptgSjDay <= xpyjbgsptgBzDay ? "0" : "1"));
			}
		}else if ("主数据申请审批通过日期".equals(dateName)) {
			//1.主数据申请审批通过日期
			appSchedule.setZsjsqsptgDate(date);
			//2.主数据申请审批通过标准天数
			Integer zsjsqsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate8());
			appSchedule.setZsjsqsptgBzDay(zsjsqsptgBzDay);
			
			Date xpyjbgsptgDate = appSchedule.getXpyjbgsptgDate();
			if (xpyjbgsptgDate != null && date != null) {
				//3.主数据申请审批通过实际天数
				int zsjsqsptgSjDay = workDayService.searchWeekDayByDate(xpyjbgsptgDate, date);
				appSchedule.setZsjsqsptgSjDay(zsjsqsptgSjDay);
				//4.主数据申请审批通过状态
				if (zsjsqsptgBzDay != null) appSchedule.setXpyjbgsptgStatus((zsjsqsptgSjDay <= zsjsqsptgBzDay ? "0" : "1"));
			}
		}else if ("包装设计申请提交日期".equals(dateName)) {
			//1.包装设计申请日期
			appSchedule.setBzsjsqDate(date);
			//2.包装设计申请标准天数
			Integer bzsjsqBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate2());
			appSchedule.setBzsjsqBzDay(bzsjsqBzDay);
			
			if (qryxpDate != null && date != null) {
				//3.包装设计申请实际天数
				int bzsjsqSjDay = workDayService.searchWeekDayByDate(qryxpDate, date);
				appSchedule.setBzsjsqSjDay(bzsjsqSjDay);
				//4.包装设计申请状态
				if (bzsjsqBzDay != null) appSchedule.setBzsjsqStatus((bzsjsqSjDay <= bzsjsqBzDay ? "0" : "1"));
			}
		}else if ("包装设计申请完成日期".equals(dateName)) {
			//1.包装设计申请完成日期
			appSchedule.setBzsjsqwcDate(date);
			//2.包装设计申请完成标准天数
			Integer bzsjsqwcBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate6());
			appSchedule.setBzsjsqwcBzDay(bzsjsqwcBzDay);
			
			Date bzsjsqDate = appSchedule.getBzsjsqDate();
			if (bzsjsqDate != null && date != null) {
				//3.包装设计申请完成实际天数
				int bzsjsqwcSjDay = workDayService.searchWeekDayByDate(bzsjsqDate, date);
				appSchedule.setBzsjsqwcSjDay(bzsjsqwcSjDay);
				//4.包装设计申请完成状态
				if (bzsjsqwcBzDay != null) appSchedule.setBzsjsqwcStatus((bzsjsqwcSjDay <= bzsjsqwcBzDay ? "0" : "1"));
			}
		}else if ("包装设计初稿确认日期".equals(dateName)) {
			//1.包装设计初稿确认日期
			appSchedule.setBzsjcgqrDate(date);
			//2.包装设计初稿确认标准天数
			Integer bzsjcgqrBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate9());
			appSchedule.setBzsjcgqrBzDay(bzsjcgqrBzDay);
			
			Date bzsjsqwcDate = appSchedule.getBzsjsqwcDate();
			if (bzsjsqwcDate != null && date != null) {
				//3.包装设计申请完成实际天数
				int bzsjcgqrSjDay = workDayService.searchWeekDayByDate(bzsjsqwcDate, date);
				appSchedule.setBzsjcgqrSjDay(bzsjcgqrSjDay);
				//4.包装设计初稿确认状态
				if (bzsjcgqrBzDay != null) appSchedule.setBzsjcgqrStatus((bzsjcgqrSjDay <= bzsjcgqrBzDay ? "0" : "1"));
			}
		}else if ("包装设计完稿通过日期".equals(dateName)) {
			//1.包装设计完稿通过日期
			appSchedule.setBzsjwgtgDate(date);
			
			//2.距离包装设计初稿确认日期标准天数
			Integer jlbzsjcgqrBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate11());
			appSchedule.setJlbzsjcgqrBzDay(jlbzsjcgqrBzDay);
			//3.距离包装设计初稿确认日期实际天数
			Date bzsjcgqrDate = appSchedule.getBzsjcgqrDate();//包装设计初稿确认日期
			Integer jlwlzsjsqsptgSjDay = null;
			if (bzsjcgqrDate != null && date != null) {
				jlwlzsjsqsptgSjDay = workDayService.searchWeekDayByDate(bzsjcgqrDate, date);
				appSchedule.setJlwlzsjsqsptgSjDay(jlwlzsjsqsptgSjDay);
			}
			
			//4.距离物料主数据申请审批通过日期标准天数
			Integer jlwlzsjsqsptgBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate10());
			appSchedule.setJlwlzsjsqsptgBzDay(jlwlzsjsqsptgBzDay);
			//5.距离包装设计初稿确认日期实际天数
			Date zsjsqsptgDate = appSchedule.getZsjsqsptgDate();// 主数据申请审批通过日期
			Integer jlbzsjcgqrSjDay = null;
			if (zsjsqsptgDate != null && date != null) {
				jlbzsjcgqrSjDay = workDayService.searchWeekDayByDate(zsjsqsptgDate, date);
				appSchedule.setJlbzsjcgqrSjDay(jlbzsjcgqrSjDay);
			}

			if (date != null) {
				if (bzsjcgqrDate != null && zsjsqsptgDate != null) {
					if (zsjsqsptgDate.after(bzsjcgqrDate)) {//zsjsqsptgDate > bzsjcgqrDate
						bzsjcgqrDate = null;
					} else {
						zsjsqsptgDate = null;//当包装设计初稿确认日期 >= 物料主数据申请审批通过日期,为"包装设计初稿确认日期"
					}
				}
				if (bzsjcgqrDate != null && zsjsqsptgDate == null) {//jlwlzsjsqsptgSjDay不为null,zsjsqsptgDate为null
					if (jlwlzsjsqsptgSjDay != null && jlbzsjcgqrBzDay != null) {
						appSchedule.setBzsjwgtgStatus((jlwlzsjsqsptgSjDay <= jlbzsjcgqrBzDay ? "0" : "1"));
					}
				} else if (bzsjcgqrDate == null && zsjsqsptgDate != null) {//jlwlzsjsqsptgSjDay为null,zsjsqsptgDate不为null
					if (jlbzsjcgqrSjDay != null && jlwlzsjsqsptgBzDay != null) {
						appSchedule.setBzsjwgtgStatus((jlbzsjcgqrSjDay <= jlwlzsjsqsptgBzDay ? "0" : "1"));
					}
				}
			}
		}else if ("新品发放日期".equals(dateName)) {
			//1.新品发放日期
			appSchedule.setXpffDate(date);
			//2.新品发放标准天数
			Integer xpffBzDay = DecimalFormatUtils.getInteger(merPro.getProcessDate12());
			appSchedule.setXpffBzDay(xpffBzDay);
			
			Date bzsjwgtgDate = appSchedule.getBzsjwgtgDate();
			if (bzsjwgtgDate != null && date != null) {
				//3.新品发放实际天数
				int xpffSjDay = workDayService.searchWeekDayByDate(bzsjwgtgDate, date);
				appSchedule.setXpffSjDay(xpffSjDay);
				//4.新品发放状态
				if (xpffBzDay != null) appSchedule.setXpffStatus((xpffSjDay <= xpffBzDay ? "0" : "1"));
			}
			//实际总天数sumInteger
			Integer sjDay1 = DecimalFormatUtils.sumInteger(sctgSjDay, appSchedule.getXpyjbgtjSjDay(), 
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
			}
		}else {
			PeripheralFileUtils.logger.error(header + "不知道时间名称:[" + (dateName) + "]" + (
					msg));
			return false;
		}
		return true;
	}

	public String completeCompareDateAndCalculateDay(String code,
			String intentionCode, String supplierCode, String dateName,
			Date date, String header) {
		// TODO Auto-generated method stub
		return null;
	}

}
