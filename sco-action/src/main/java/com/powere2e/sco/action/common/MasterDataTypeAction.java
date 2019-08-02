package com.powere2e.sco.action.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.security.model.Option;

/**
 * SAP主数据中的类型及管理员维护的细分类查询
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月19日
 */
public class MasterDataTypeAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6624921734961690334L;
	private MasterDataTypeService masterDataTypeService;

	@Override
	protected void beforeBuild() {
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 中分类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListCentreType() {
		List<Option> list = masterDataTypeService.listCenterType(new HashMap<String, Object>());
		this.addBlankToList(list);
		this.forwardData(true, list, null);
	}

	/**
	 * 小分类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSmallType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centreTypeCode", asString("centreTypeCode"));
		List<Option> list = masterDataTypeService.listSmallType(map);
		this.addBlankToList(list);
		this.forwardData(true, list, null);
	}

	/**
	 * 小分类数据查询-其他
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSmallTypeElse() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("centreTypeCode", asString("centreTypeCodeElse"));
		List<Option> list = masterDataTypeService.listSmallType(map);
		if (!this.asString("centreTypeCodeElse").equals("null")) {
			Option o = new Option();
			o.setId("ELSE");
			o.setText("其他");
			list.add(o);
		}
		this.addBlankToList(list);
		this.forwardData(true, list, null);
	}

	/**
	 * 明细类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListDetailType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("smallTypeCode", asString("smallTypeCode"));
		List<Option> list = masterDataTypeService.listDetailType(map);
		this.addBlankToList(list);
		this.forwardData(true, list, null);
	}

	/**
	 * 细分类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListFineType() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("detailTypeCode", asString("detailTypeCode"));
		List<Option> list = masterDataTypeService.listFineType(map);
		this.addBlankToList(list);
		this.forwardData(true, list, null);
	}

	/**
	 * 销售方式查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSaleType() {
		List<Option> list = masterDataTypeService.listSaleType();
		this.forwardData(true, list, null);
	}

	/**
	 * 区域(region)查询(包含全国)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListRegion() {
		List<Option> list = masterDataTypeService.listRegion();
		//地区下拉菜单需要添加"全国"项
		Option option = new Option();
		option.setId("all");
		option.setText("全国");
		list.add(0, option);
		this.forwardData(true, list, null);
	}

	/**
	 * 区域(region)查询(不包含全国)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListRegionWithoutAll() {
		List<Option> list = masterDataTypeService.listRegion();
		this.forwardData(true, list, null);
	}

	/**
	 * 仓库(warehouse)查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListWarehouseOption() {
		List<Option> list = masterDataTypeService.listWarehouseOption();
		Option o = new Option();
		o.setId("全国");
		o.setText("全国");
		list.add(0, o);
		this.forwardData(true, list, null);
	}

	/**
	 * 为list在第一个位置新增一个空的选项(value和text均是空窜)
	 * 
	 * @param list
	 *            用于存储在页面显示的下拉选项
	 */
	private void addBlankToList(List<Option> list) {
		// 暂不做此变更
		// if (list == null) return;
		// list.add(0, new Option("", "　"));//后面的空格是全角的空格
	}
}
