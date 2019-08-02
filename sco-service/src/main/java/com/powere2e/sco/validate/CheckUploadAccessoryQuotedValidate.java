package com.powere2e.sco.validate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.powere2e.sco.common.utils.CheckUploadUtil;
import com.powere2e.sco.common.utils.XLSUtil;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryIntentionSupplier;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElse;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTechnology;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedTotal;

/**
 * 意向品报价单导入校验
 * 
 * @author gavin.xu
 * @since 2015年3月25日 上午10:16:10
 * @version 1.0
 */
public class CheckUploadAccessoryQuotedValidate {
	private static final int BEGIN_READ_INDEX = 16;// 表格从第几行开始解析

	public Map<String, Object> upload(XSSFSheet sheet, Map<String, Object> map, List<AccessoryEnquiryMaterial> listAem, List<AccessoryEnquiryAccessory> listAea, List<AccessoryEnquiryPacking> listAep, List<AccessoryEnquiryTechnology> listAet, List<AccessoryEnquiryQuotedCount> listAeqc) {
		Map<String, Object> uploadMap;
		String check;
		AccessoryIntentionSupplier accessoryIntentionSupplier;
		List<AccessoryQuotedMaterial> listAccessoryQuotedMaterial;
		List<AccessoryQuotedAccessory> listAccessoryQuotedAccessory;
		List<AccessoryQuotedPacking> listAccessoryQuotedPacking;
		List<AccessoryQuotedTechnology> listAccessoryQuotedTechnology;
		List<AccessoryQuotedElse> listAccessoryQuotedElse;
		List<AccessoryQuotedTotal> listAccessoryQuotedTotal;
		AccessoryQuotedElectronic accessoryQuotedElectronic;

		String supplierCode = map.get("intentionSupplierCode").toString();
		String enquiryCode = map.get("enquiryCode").toString();
		String intentionCode = map.get("intentionCode").toString();
		Date quotedDate = (Date) map.get("quotedDate");
		String quotedCode = map.get("quotedCode").toString();
		uploadMap = new HashMap<String, Object>();
		check = "";

		accessoryIntentionSupplier = new AccessoryIntentionSupplier();
		listAccessoryQuotedMaterial = new ArrayList<AccessoryQuotedMaterial>();
		listAccessoryQuotedAccessory = new ArrayList<AccessoryQuotedAccessory>();
		listAccessoryQuotedPacking = new ArrayList<AccessoryQuotedPacking>();
		listAccessoryQuotedTechnology = new ArrayList<AccessoryQuotedTechnology>();
		listAccessoryQuotedElse = new ArrayList<AccessoryQuotedElse>();
		listAccessoryQuotedTotal = new ArrayList<AccessoryQuotedTotal>();
		accessoryQuotedElectronic = new AccessoryQuotedElectronic();
		try {
			XSSFRow row0 = sheet.getRow(1);
			XSSFCell cell0 = null;
			Object cellValue0 = null;
			cell0 = row0.getCell(1);
			cellValue0 = XLSUtil.getCellValue2007forMaped(cell0);
			cellValue0 = CheckUploadUtil.trimObjToStr(cellValue0);
			if (!map.get("enquiryCode").toString().equals(cellValue0)) {
				check += "询价单编号与选择的询价单编号不匹配";
			}
			accessoryQuotedElectronic.setEnquiryCode(cellValue0 == null ? null : cellValue0.toString());
			accessoryQuotedElectronic.setIntentionCode(intentionCode);

			accessoryQuotedElectronic.setIntentionSupplierCode(supplierCode);

			accessoryQuotedElectronic.setQuotedCode(quotedCode);
			accessoryQuotedElectronic.setQuotedDate(quotedDate);
			for (int j = BEGIN_READ_INDEX; j < 28; j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check += "导入的excel不符合规则!";
				}

				XSSFCell cell = null;
				Object cellValue = null;
				if (j == BEGIN_READ_INDEX) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "公司名称");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 100)) {
									check += CheckUploadUtil.getTooLongNotice(j, "公司名称");
								} else {
									accessoryQuotedElectronic.setCompanyName(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 1) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "公司地址");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 100)) {
									check += CheckUploadUtil.getTooLongNotice(j, "公司地址");
								} else {
									accessoryQuotedElectronic.setCompanySite(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 2) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "工厂地址");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 100)) {
									check += CheckUploadUtil.getTooLongNotice(j, "工厂地址");
								} else {
									accessoryQuotedElectronic.setFactorySite(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 3) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "供应商编号");
							} else if (!cellValue.equals(supplierCode)) {
								check += "导入的供应商编号跟选择的供应商不匹配";
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 20)) {
									check += CheckUploadUtil.getTooLongNotice(j, "供应商编号");
								} else {
									accessoryQuotedElectronic.setIntentionSupplierCode(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 4) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "发票类型 (增/普票)");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j, "发票类型 (增/普票)");
								} else if (!"增票".equals(cellValue) && !"普票".equals(cellValue)) {
									check += "第" + (j + 1) + "行" + "发票类型只能是增票或者普票!<br/>";
								} else {
									accessoryQuotedElectronic.setInvoiceType(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "税率(%)");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j, "税率(%)");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 9)) {
									check += CheckUploadUtil.getTooLongNotice(j, "税率(%)");
								} else {
									accessoryQuotedElectronic.setTaxRate(new BigDecimal(cellValue.toString()));
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 5) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "联系人");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j, "联系人");
								} else {
									accessoryQuotedElectronic.setContacts(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "联系电话");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check += CheckUploadUtil.getTooLongNotice(j, "联系电话");
								} else {
									accessoryQuotedElectronic.setPhone(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 6) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 30)) {
								check += CheckUploadUtil.getTooLongNotice(j, "联系人邮箱");
							} else {
								accessoryQuotedElectronic.setEmail(cellValue.toString());
							}
						}
						if (i == 3) {

							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 30)) {
								check += CheckUploadUtil.getTooLongNotice(j, "联系人传真号");
							} else {
								accessoryQuotedElectronic.setFax(cellValue.toString());
							}
						}
					}
				} else if (j == BEGIN_READ_INDEX + 7) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "注册资本");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check += CheckUploadUtil.getTooLongNotice(j, "注册资本");
								} else {
									accessoryQuotedElectronic.setRegisterCapital(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "年营业额");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check += CheckUploadUtil.getTooLongNotice(j, "年营业额");
								} else {
									accessoryQuotedElectronic.setYearTurnover(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 8) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "工厂面积");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j, "工厂面积");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 15)) {
									check += CheckUploadUtil.getTooLongNotice(j, "工厂面积");
								} else {
									accessoryQuotedElectronic.setFactoryArea(new BigDecimal(cellValue.toString()));
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "工人数");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j, "工人数");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j, "工人数");
								} else {
									accessoryQuotedElectronic.setStaffCount(new BigDecimal(cellValue.toString()));
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 9) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "合作过品牌");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 100)) {
									check += CheckUploadUtil.getTooLongNotice(j, "合作过品牌");
								} else {
									accessoryQuotedElectronic.setHzgpp(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 10) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j, "付款方式");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 33)) {
									check += CheckUploadUtil.getTooLongNotice(j, "付款方式");
								} else {
									accessoryQuotedElectronic.setPaymentType(cellValue.toString());
								}
							}
						}

					}
				} else if (j == BEGIN_READ_INDEX + 11) {
					for (int i = 0; i < 4; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 1) {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 33)) {
								check += CheckUploadUtil.getTooLongNotice(j, "交货方式");
							} else {
								accessoryQuotedElectronic.setDeliveryType(cellValue.toString());
							}
						}

					}
				}
			}
			// 原材料信息
			for (int j = 30; j < (30 + listAem.size()); j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check += "导入的excel不符合规则!";
				}

				XSSFCell cell = null;
				Object cellValue = null;
				AccessoryQuotedMaterial accessoryQuotedMaterial = new AccessoryQuotedMaterial();
				for (int i = 5; i < 8; i++) {
					cell = row.getCell(i);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					if (i == 5) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {

						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "品牌");
							} else {
								accessoryQuotedMaterial.setBrand(cellValue.toString());
							}
						}
					}
					if (i == 7) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "原产地");
							} else {
								accessoryQuotedMaterial.setOrigin(cellValue.toString());
							}
						}
					}
				}
				for (int k = 0; k < listAeqc.size(); k++) {
					AccessoryQuotedMaterial accessoryQuotedMaterials = new AccessoryQuotedMaterial();
					accessoryQuotedMaterials.setBrand(accessoryQuotedMaterial.getBrand());
					accessoryQuotedMaterials.setOrigin(accessoryQuotedMaterial.getOrigin());
					row = sheet.getRow(j + (listAem.size() * (k + 1)) + (k + 2));
					for (int i = 0; i < 8; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 0) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAem.size() * (k + 1)) + (k + 2), "订购数量");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "订购数量");
								} else {
									accessoryQuotedMaterials.setOrderCount(cellValue.toString().replaceAll(",", ""));
								}
							}
						}
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAem.size() * (k + 1)) + (k + 2), "原材料编号");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 15)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "原材料编号");
								} else {
									accessoryQuotedMaterials.setMaterialCode(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "价格单位");
								} else {
									accessoryQuotedMaterials.setPriceUnit(cellValue.toString());
								}
							}
						}
						if (i == 4) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAem.size() * (k + 1)) + (k + 2), "价格");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "价格");
								} else {
									accessoryQuotedMaterials.setPrice(new BigDecimal(cellValue.toString()));
								}
							}
						}
						/*
						 * if (i == 5) { if (CheckUploadUtil.isNullOrEmpty(cellValue)) { check += CheckUploadUtil.getBlankNotice(j, "用料量"); }else if (!CheckUploadUtil.checkNumber(cellValue)) { check += CheckUploadUtil.getNumberNotice(j, "用料量"); } else { cellValue = CheckUploadUtil.trimObjToStr(cellValue); accessoryQuotedMaterials.setWastage(new BigDecimal(cellValue.toString())); } }
						 */
						if (i == 6) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "用料损耗");
								} else {
									accessoryQuotedMaterials.setWastage(cellValue.toString());
								}
							}
						}
						if (i == 7) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAem.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAem.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAem.size() * (k + 1)) + (k + 2), "单位商品成本");
								} else {
									accessoryQuotedMaterials.setCost(new BigDecimal(cellValue.toString()));
								}
							}
						}
					}
					accessoryQuotedMaterials.setEnquiryCode(enquiryCode);
					accessoryQuotedMaterials.setQuotedCode(quotedCode);
					listAccessoryQuotedMaterial.add(accessoryQuotedMaterials);
				}

			}

			// 辅料
			
			int flSrart = 30 + listAem.size() + (listAem.size() + 1) * listAeqc.size() + 1 + 2;
			if(listAea.size()>0){
			for (int j = flSrart; j < (flSrart + listAea.size()); j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check += "导入的excel不符合规则!";
				}

				XSSFCell cell = null;
				Object cellValue = null;
				AccessoryQuotedAccessory accessoryQuotedAccessory = new AccessoryQuotedAccessory();
				for (int i = 5; i < 8; i++) {
					cell = row.getCell(i);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					if (i == 5) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "品牌");
							} else {
								accessoryQuotedAccessory.setBrand(cellValue.toString());
							}
						}
					}
					if (i == 7) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "原产地");
							} else {
								accessoryQuotedAccessory.setOrigin(cellValue.toString());
							}
						}
					}
				}
				for (int k = 0; k < listAeqc.size(); k++) {
					AccessoryQuotedAccessory accessoryQuotedAccessorys = new AccessoryQuotedAccessory();
					accessoryQuotedAccessorys.setBrand(accessoryQuotedAccessory.getBrand());
					accessoryQuotedAccessorys.setOrigin(accessoryQuotedAccessory.getOrigin());
					row = sheet.getRow(j + (listAea.size() * (k + 1)) + (k + 2));
					for (int i = 0; i < 8; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 0) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAea.size() * (k + 1)) + (k + 2), "订购数量");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "订购数量");
								} else {
									accessoryQuotedAccessorys.setOrderCount(cellValue.toString().replaceAll(",", ""));
								}
							}
						}
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAea.size() * (k + 1)) + (k + 2), "辅料编号");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 15)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "辅料编号");
								} else {
									accessoryQuotedAccessorys.setAccessoryCode(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "价格单位");
								} else {
									accessoryQuotedAccessorys.setPriceUnit(cellValue.toString());
								}
							}
						}
						if (i == 4) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAea.size() * (k + 1)) + (k + 2), "价格");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "价格");
								} else {
									accessoryQuotedAccessorys.setPrice(new BigDecimal(cellValue.toString()));
								}
							}
						}
						/*
						 * if (i == 5) { if (CheckUploadUtil.isNullOrEmpty(cellValue)) { check += CheckUploadUtil.getBlankNotice(j, "用料量"); }else if (!CheckUploadUtil.checkNumber(cellValue)) { check += CheckUploadUtil.getNumberNotice(j, "用料量"); } else { cellValue = CheckUploadUtil.trimObjToStr(cellValue); accessoryQuotedAccessorys.setWastage(new BigDecimal(cellValue.toString())); } }
						 */
						if (i == 6) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "用料损耗");
								} else {
									accessoryQuotedAccessorys.setWastage(cellValue.toString());
								}
							}
						}
						if (i == 7) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAea.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAea.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAea.size() * (k + 1)) + (k + 2), "单位商品成本");
								} else {
									accessoryQuotedAccessorys.setCost(new BigDecimal(cellValue.toString()));
								}
							}
						}
					}
					accessoryQuotedAccessorys.setEnquiryCode(enquiryCode);
					accessoryQuotedAccessorys.setQuotedCode(quotedCode);
					listAccessoryQuotedAccessory.add(accessoryQuotedAccessorys);
				}

			}
			}
			// 内外包装
			int nwbzSrart;
			if(listAea.size()>0){
			nwbzSrart = flSrart + listAea.size() + (listAea.size() + 1) * listAeqc.size() + 1 + 2;
			}else{
				nwbzSrart = flSrart;	
			}
			for (int j = nwbzSrart; j < (nwbzSrart + listAep.size()); j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check += "导入的excel不符合规则!";
				}

				XSSFCell cell = null;
				Object cellValue = null;
				AccessoryQuotedPacking accessoryQuotedPacking = new AccessoryQuotedPacking();
				for (int i = 5; i < 8; i++) {
					cell = row.getCell(i);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					if (i == 5) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "品牌");
							} else {
								accessoryQuotedPacking.setBrand(cellValue.toString());
							}
						}
					}
					if (i == 7) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "原产地");
							} else {
								accessoryQuotedPacking.setOrigin(cellValue.toString());
							}
						}
					}
				}
				for (int k = 0; k < listAeqc.size(); k++) {
					AccessoryQuotedPacking accessoryQuotedPackings = new AccessoryQuotedPacking();
					accessoryQuotedPackings.setBrand(accessoryQuotedPacking.getBrand());
					accessoryQuotedPackings.setOrigin(accessoryQuotedPacking.getOrigin());
					row = sheet.getRow(j + (listAep.size() * (k + 1)) + (k + 2));
					for (int i = 0; i < 8; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 0) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAep.size() * (k + 1)) + (k + 2), "订购数量");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "订购数量");
								} else {
									accessoryQuotedPackings.setOrderCount(cellValue.toString().replaceAll(",", ""));
								}
							}
						}
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAep.size() * (k + 1)) + (k + 2), "内外包装材料编号");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 15)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "内外包装材料编号");
								} else {
									accessoryQuotedPackings.setPackingCode(cellValue.toString());
								}
							}
						}
						if (i == 3) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "价格单位");
								} else {
									accessoryQuotedPackings.setPriceUnit(cellValue.toString());
								}
							}
						}
						if (i == 4) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAep.size() * (k + 1)) + (k + 2), "价格");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "价格");
								} else {
									accessoryQuotedPackings.setPrice(new BigDecimal(cellValue.toString()));
								}
							}
						}
						if (i == 6) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							}  else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "用料损耗");
								} else {
									accessoryQuotedPackings.setWastage(cellValue.toString());
								}
							}
						}
						if (i == 7) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAep.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAep.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAep.size() * (k + 1)) + (k + 2), "单位商品成本");
								} else {
									accessoryQuotedPackings.setCost(new BigDecimal(cellValue.toString()));
								}
							}
						}
					}
					accessoryQuotedPackings.setEnquiryCode(enquiryCode);
					accessoryQuotedPackings.setQuotedCode(quotedCode);
					listAccessoryQuotedPacking.add(accessoryQuotedPackings);
				}

			}
			// 工艺
			int gySrart = nwbzSrart + listAep.size() + (listAep.size() + 1) * listAeqc.size() + 1 + 2;
			if(listAet.size()>0){
			for (int j = gySrart; j < (gySrart + listAet.size()); j++) {
				XSSFRow row = sheet.getRow(j);
				if (row == null) {
					check += "导入的excel不符合规则!";
				}

				XSSFCell cell = null;
				Object cellValue = null;

				for (int k = 0; k < listAeqc.size(); k++) {
					AccessoryQuotedTechnology accessoryQuotedTechnologys = new AccessoryQuotedTechnology();
					row = sheet.getRow(j + (listAet.size() * (k + 1)) + (k + 2));
					for (int i = 0; i < 8; i++) {
						cell = row.getCell(i);
						cellValue = XLSUtil.getCellValue2007forMaped(cell);
						if (i == 0) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAet.size() * (k + 1)) + (k + 2), "订购数量");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 12)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAet.size() * (k + 1)) + (k + 2), "订购数量");
								} else {
									accessoryQuotedTechnologys.setOrderCount(cellValue.toString().replaceAll(",", ""));
								}
							}
						}
						if (i == 1) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAet.size() * (k + 1)) + (k + 2), "工艺编号");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 15)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAet.size() * (k + 1)) + (k + 2), "工艺编号");
								} else {
									accessoryQuotedTechnologys.setTechnologyCode(cellValue.toString());
								}
							}
						}
						if (i == 6) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAet.size() * (k + 1)) + (k + 2), "价格单位");
								} else {
									accessoryQuotedTechnologys.setPriceUnit(cellValue.toString());
								}
							}
						}
						if (i == 7) {
							if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
								check += CheckUploadUtil.getBlankNotice(j + (listAet.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else if (!CheckUploadUtil.checkNumber(cellValue)) {
								check += CheckUploadUtil.getNumberNotice(j + (listAet.size() * (k + 1)) + (k + 2), "单位商品成本");
							} else {
								cellValue = CheckUploadUtil.trimObjToStr(cellValue);
								if (CheckUploadUtil.isTooLong(cellValue, 10)) {
									check += CheckUploadUtil.getTooLongNotice(j + (listAet.size() * (k + 1)) + (k + 2), "单位商品成本");
								} else {
									accessoryQuotedTechnologys.setCost(new BigDecimal(cellValue.toString()));
								}
							}
						}
					}
					accessoryQuotedTechnologys.setEnquiryCode(enquiryCode);
					accessoryQuotedTechnologys.setQuotedCode(quotedCode);
					listAccessoryQuotedTechnology.add(accessoryQuotedTechnologys);
				}

			}
			}
			// 其他
			int qtSrart;
			if(listAet.size()>0){
				qtSrart = gySrart + listAet.size() + (listAet.size() + 1) * listAeqc.size() + 1 + 2;
			}else{
				qtSrart = gySrart;	
			}
			
			for (int i = 0; i < listAeqc.size(); i++) {
				for (int j = qtSrart + 9 * i; j < (qtSrart + 8 + 1 + 9 * i); j++) {
					XSSFRow row = sheet.getRow(j);
					if (row == null) {
						check += "导入的excel不符合规则!";
					}

					XSSFCell cell = null;
					Object cellValue = null;
					cell = row.getCell(7);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					cell = row.getCell(0);
					Object cellValue1 = "";
					cellValue1 = XLSUtil.getCellValue2007forMaped(cell);
					if (j == qtSrart + 9 * i) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "打样费");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "打样费");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "打样费");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("打样费");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}

						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 1) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "人工");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "人工");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "人工");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("人工");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 2) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "运输");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "运输");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "运输");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("运输");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 3) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "管理");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "管理");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "管理");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("管理");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 4) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "税额");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "税额");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "税额");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("税额");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 5) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "税额占比");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "税额占比");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "税额占比");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("税额占比");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 6) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "利润");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "利润");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "利润");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("利润");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}
						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
					if (j == qtSrart + 9 * i + 7) {
						AccessoryQuotedElse accessoryQuotedElse = new AccessoryQuotedElse();
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "利润占比");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "利润占比");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "利润占比");
							} else {
								accessoryQuotedElse.setCost(new BigDecimal(cellValue.toString()));
								accessoryQuotedElse.setOrderCount(cellValue1.toString().replaceAll(",", ""));
								accessoryQuotedElse.setCostType("利润占比");
							}
							accessoryQuotedElse.setEnquiryCode(enquiryCode);
							accessoryQuotedElse.setQuotedCode(quotedCode);
						}

						listAccessoryQuotedElse.add(accessoryQuotedElse);
					}
				}
			}

			// 总报价
			int zbjSrart = qtSrart + (8 + 1) * listAeqc.size() + 1;
			for (int j = zbjSrart; j < zbjSrart + listAeqc.size(); j++) {
				AccessoryQuotedTotal accessoryQuotedTotal = new AccessoryQuotedTotal();
				XSSFRow row = sheet.getRow(j);
				XSSFCell cell = null;
				Object cellValue = null;
				for (int i = 0; i < 8; i++) {
					cell = row.getCell(i);
					cellValue = XLSUtil.getCellValue2007forMaped(cell);
					if (i == 1) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "订购数量");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 12)) {
								check += CheckUploadUtil.getTooLongNotice(j, "订购数量");
							} else {
								accessoryQuotedTotal.setOrderCount(cellValue.toString().replaceAll(",", ""));
							}
						}
					}
					if (i == 3) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "打样周期");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "打样周期");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "打样周期");
							} else {
								accessoryQuotedTotal.setProofingCycle(new BigDecimal(cellValue.toString()));
							}
						}
					}
					if (i == 4) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "日产能");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "日产能");
							} else {
								accessoryQuotedTotal.setDailyCapacity(cellValue.toString());
								if (j == zbjSrart) {
									accessoryQuotedElectronic.setDailyCapacity(cellValue.toString());
								}
							}
						}
					}
					if (i == 5) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "生产周期");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "生产周期");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "生产周期");
							} else {
								accessoryQuotedTotal.setProductionCycle(new BigDecimal(cellValue.toString()));
							}

						}
					}
					if (i == 7) {
						if (CheckUploadUtil.isNullOrEmpty(cellValue)) {
							check += CheckUploadUtil.getBlankNotice(j, "单价");
						} else if (!CheckUploadUtil.checkNumber(cellValue)) {
							check += CheckUploadUtil.getNumberNotice(j, "单价");
						} else {
							cellValue = CheckUploadUtil.trimObjToStr(cellValue);
							if (CheckUploadUtil.isTooLong(cellValue, 10)) {
								check += CheckUploadUtil.getTooLongNotice(j, "单价");
							} else {
								accessoryQuotedTotal.setUnitPrice(new BigDecimal(cellValue.toString()));
								accessoryQuotedTotal.setSupplierCode(supplierCode);
							}

						}
					}
				}
				accessoryQuotedTotal.setEnquiryCode(enquiryCode);
				accessoryQuotedTotal.setQuotedCode(quotedCode);
				listAccessoryQuotedTotal.add(accessoryQuotedTotal);
			}
			// 备注
			int bzSrart = zbjSrart + listAeqc.size() + 1;
			XSSFRow rowbz = sheet.getRow(bzSrart);
			XSSFCell cellbz = null;
			Object cellValuebz = null;
			cellbz = rowbz.getCell(0);
			cellValuebz = XLSUtil.getCellValue2007forMaped(cellbz);
			cellValuebz = CheckUploadUtil.trimObjToStr(cellValuebz);
			if (CheckUploadUtil.isTooLong(cellValuebz, 333)) {
				check += CheckUploadUtil.getTooLongNotice(bzSrart, "备注");
			} else {
				accessoryQuotedElectronic.setRemarks(cellValuebz.toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			check += "excel内容错误";
			e.printStackTrace();
		}
		uploadMap.put("CHECK", check);
		if (check.length() == 0) {

			uploadMap.put("accessoryIntentionSupplier", accessoryIntentionSupplier);
			uploadMap.put("accessoryQuotedElectronic", accessoryQuotedElectronic);
			uploadMap.put("listAccessoryQuotedMaterial", listAccessoryQuotedMaterial);
			uploadMap.put("listAccessoryQuotedAccessory", listAccessoryQuotedAccessory);
			uploadMap.put("listAccessoryQuotedPacking", listAccessoryQuotedPacking);
			uploadMap.put("listAccessoryQuotedTechnology", listAccessoryQuotedTechnology);
			uploadMap.put("listAccessoryQuotedElse", listAccessoryQuotedElse);
			uploadMap.put("listAccessoryQuotedTotal", listAccessoryQuotedTotal);
		}
		return uploadMap;
	}

}
