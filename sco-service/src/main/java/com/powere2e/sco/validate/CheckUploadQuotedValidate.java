package com.powere2e.sco.validate;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.powere2e.sco.common.utils.CheckUploadUtil;
import com.powere2e.sco.common.utils.XLSUtil;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;

/**
 * 意向品报价单导入校验
 * 
 * @author Joyce.li
 * @since 2015年3月25日 上午10:16:10
 * @version 1.0
 */
public class CheckUploadQuotedValidate {
	private static final int BEGIN_READ_INDEX = 7;// 表格从第几行开始解析
	/* 变更：不检验具体类容
	 * private static final String MONEY_CNY = "CNY";
	private static final String MONEY_USD = "USD";
	private static final String MONEY_EUR = "EUR";
	*/
	public Map<String, Object> upload(XSSFSheet sheet, MerchandiseQuoted selectedQuoted) throws Exception {
		Map<String, Object> uploadMap = new HashMap<String, Object>();
		StringBuilder check = new StringBuilder();
		MerchandiseQuoted merchandiseQuoted = new MerchandiseQuoted();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (sheet.getPhysicalNumberOfRows() <= BEGIN_READ_INDEX) {
			// 如果文件行数少于7行，说明此文件格式错误
			check.append("所选报价单文件格式不正确，请重新下载询价单并按格式填写报价单!<br/>");
		} else {
			for (int j = BEGIN_READ_INDEX; j < sheet.getPhysicalNumberOfRows(); j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check.append("所选报价单文件格式不正确，请重新下载询价单并按格式填写报价单!<br/>");
				}

				XSSFCell cell = null;
				Object cellValue = null;
				for (int i = 0; i < 4; i++) {
					cell = row.getCell(i);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					if (j == BEGIN_READ_INDEX) {
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "意向品编号"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (!cellValue.equals(selectedQuoted.getIntentionCode())) {
									check.append("意向品编号与用户当前操作意向品的意向品编号不相同!<br/>");
								} else {
									merchandiseQuoted.setIntentionCode(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 8) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "公司名称"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "公司名称"));
								} else {
									merchandiseQuoted.setCompanyName(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 9) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "公司地址"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 100)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "公司地址"));
								} else {
									merchandiseQuoted.setCompanySite(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 10) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "供应商编号"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (!cellValue.equals(selectedQuoted.getIntentionSupplierCode())) {
									check.append("供应商编号与用户导入报价单时选择的供应商编号不相同!");
								} else {
										merchandiseQuoted.setIntentionSupplierCode(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 11) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "联系人"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "联系人"));
								} else {
									merchandiseQuoted.setContactsName(cellValue.toString());
								}
							}
						} else if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "联系人电话"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 30)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "联系人电话"));
								} else {
									merchandiseQuoted.setContactsPhone(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 12) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 30)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "联系人邮箱"));
								} else {
									merchandiseQuoted.setContactsEmail(cellValue.toString());
								}
							}
						} else if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 30)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "联系人传真号"));
								} else {
									merchandiseQuoted.setContactsFax(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 14) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "报价币种"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "报价币种"));
								} else {
									/* 变更：不检验具体类容
									 * if (!MONEY_CNY.equalsIgnoreCase(cellValue.toString()) && !MONEY_USD.equalsIgnoreCase(cellValue.toString()) && !MONEY_EUR.equalsIgnoreCase(cellValue.toString())) {
										check.append("报价币种只能为CNY、USD或EUR!<br/>");
									} else {
									}*/
									merchandiseQuoted.setQuotedCurrency(cellValue.toString().toUpperCase());
								}
							}
						} else if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 9)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "最小起订量"));
								} else {
									merchandiseQuoted.setMinRationed(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 15) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "基本计量单位"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (!CheckUploadUtil.checkNumber(cellValue)) {
									check.append(CheckUploadUtil.getNumberNotice(j, "基本计量单位"));
								} else {
									if (CheckUploadUtil.isTooLong(cellValue, 9)) {
										check.append(CheckUploadUtil.getTooLongNotice(j, "基本计量单位"));
									} else {
										merchandiseQuoted.setQuotationUnits(CheckUploadUtil.formateDoubleTo2Scale(cellValue));
									}
								}
							}
						} else if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check.append(CheckUploadUtil.getBlankNotice(j, "报价"));
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (!CheckUploadUtil.checkNumber(cellValue)) {
									check.append(CheckUploadUtil.getNumberNotice(j, "报价"));
								} else {
									/*if (CheckUploadUtil.isTooLong(cellValue, 11)) {
										check.append(CheckUploadUtil.getTooLongNotice(j, "报价"));
									} else {*/
										try {
											merchandiseQuoted.setPrice(CheckUploadUtil.formateDoubleTo2Scale(cellValue));
										} catch (Exception e) {
											check.append(CheckUploadUtil.getTooLongNotice(j, "报价"));
											throw new Exception();
										}
									//}
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 16) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								try {
									cellValue = CheckUploadUtil.trimObjToStr(cellValue);
									if (!CheckUploadUtil.checkDate(cellValue)) {
										check.append(CheckUploadUtil.getDateNotice(j, "报价有效期"));
									} else {
										merchandiseQuoted.setQuotedEndDate(sdf.parse(cellValue.toString()));
									}
								} catch (Exception e) {
									check.append(CheckUploadUtil.getDateNotice(j, "报价有效期"));
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 17) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "包装方式"));
								} else {
									merchandiseQuoted.setPackingType(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 18) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "付款方式"));
								} else {
									merchandiseQuoted.setPaymentType(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 19) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "交货方式"));
								} else {
									merchandiseQuoted.setDeliveryType(cellValue.toString());
								}
							}
						}
					} else if (j == BEGIN_READ_INDEX + 20) {
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 333)) {
									check.append(CheckUploadUtil.getTooLongNotice(j, "备注"));
								} else {
									merchandiseQuoted.setRemarks(cellValue.toString());
								}
							}
						}
					} else {
						break;
					}
				}
			}
		}

		uploadMap.put("CHECK", check.toString());
		if (check.length() == 0) {
			merchandiseQuoted.setQuotedDate(selectedQuoted.getQuotedDate());
			uploadMap.put("MERCHANDISEQUOTED", merchandiseQuoted);
		}
		return uploadMap;
	}

}
