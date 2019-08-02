package com.powere2e.sco.common.service.merchandiseoaapplication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;

/**
 * 商品意向品模块一些公共方法和参数
 * 
 * @author Joyce.li
 * @since 2015年4月3日 上午10:10:50
 * @version 1.0
 */
public class MerchandiseOaApplicationUtil {

	// 意向品编号和供应商编号组分隔符
	private static final String GROUP_SPLIT = ",";
	// 一组意向品编号和供应商编号分隔符
	private static final String EACH_GROUP_SPLIT = ":";

	public static enum ReportNewOaApplicationState {
		// 所选择的记录没有做OA申请
		NONE("没有OA申请"),
		// 所选择的记录已经做了OA申请，并且和页面传递的OA申请单一致,申请状态为草稿
		SAME_CG("OA申请单号一致,申请状态为草稿"),
		// 所选择的记录已经做了OA申请，并且和页面传递的OA申请单一致，申请状态为非草稿
		SAME_OTHER("OA申请单号一致,申请状态为非草稿"),
		// 页面的OA申请单号和查询到的OA申请单号不一致
		DIFFER("OA申请单号不一致");

		private String receiptApplicationState;

		public String getReceiptApplicationState() {
			return receiptApplicationState;
		}

		public void setReceiptApplicationState(String receiptApplicationState) {
			this.receiptApplicationState = receiptApplicationState;
		}

		private ReportNewOaApplicationState(String receiptApplicationState) {
			this.receiptApplicationState = receiptApplicationState;
		}

	}

	// 根据意向品编号和供应商编号组先分割成不同的组，然后再对每一组进行分割，得到意向品编号和供应商编号
	public static List<ApplicationMerchandise> getIntentionAndSupplierCodeGroupList(String intentionAndSupplierCodes) {
		List<ApplicationMerchandise> groupList = new ArrayList<ApplicationMerchandise>();
		if (StringUtils.isNotBlank(intentionAndSupplierCodes)) {
			String codeGroupArr[] = intentionAndSupplierCodes.split(GROUP_SPLIT);
			for (String codeGroup : codeGroupArr) {
				String eachCodeGroup[] = codeGroup.split(EACH_GROUP_SPLIT);
				ApplicationMerchandise merchandise = new ApplicationMerchandise();
				if (eachCodeGroup.length >= 2) {
					merchandise.setMerchandiseCode(eachCodeGroup[0]);
					merchandise.setSupplierCode(eachCodeGroup[1]);
					groupList.add(merchandise);
				}
			}
		}
		return groupList;
	}

	/**
	 * 根据申请单的状态来显示不同的消息
	 * 
	 * @param oaStatus
	 *            申请单状态
	 */
	public static void responseMessage(String oaStatus) {
		if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.DIFFER.toString().equalsIgnoreCase(oaStatus)) {
			throw new EscmException("选择的数据做了不同的OA申请，不能进行后续操作");
		} else if (MerchandiseOaApplicationUtil.ReportNewOaApplicationState.SAME_OTHER.toString().equalsIgnoreCase(oaStatus)) {
			throw new EscmException("选择的数据不是“草稿”或”驳回“的状态,无法修改!");
		}
	}

}
