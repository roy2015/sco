package com.powere2e.sco.action.datamaintenance.assortmentdata.merchandisefinetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineTypeService;
import com.powere2e.sco.model.datamaintenance.assortmentdata.merchandisefinetype.MerchandiseFineType;
import com.powere2e.security.model.Option;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 商品细分类维护的WEB请求响应类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseFineTypeAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2086406198811271500L;
	private MerchandiseFineTypeService merchandiseFineTypeService;
	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseFineTypeService = (MerchandiseFineTypeService) ConfigFactory.getInstance().getBean("merchandiseFineTypeService");
	}

	/**
	 * 商品细分类下拉维护设置列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode")
	public void doMerchandiseFineTypeGrid() throws Exception {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseFineType/merchandiseFineTypeGrid.ftl");
	}

	/**
	 * 商品细分类维护列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode")
	public void doListMerchandiseFineType() throws Exception {
		Map<String, Object> map = getMerchandiseFineType().toMap();
		List<MerchandiseFineType> list = merchandiseFineTypeService.listMerchandiseFineType(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}
	/**
	 * 商品细分类数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListQualitative() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = merchandiseFineTypeService.listQualitative(map);
		this.forwardData(true, list, null);
	}
	/**
	 * 添加商品细分类维护界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode.insert")
	public void doShowInsertMerchandiseFineTypeForm() throws Exception {
		MerchandiseFineType merchandiseFineType = new MerchandiseFineType();
		this.putObject("merchandiseFineType", merchandiseFineType);
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseFineType/merchandiseFineTypeForm.ftl");
	}

	/**
	 * 查询商品细分类维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private boolean doListMerchandiseFineTypeByInfo(String finetypeName,String detailtypeCode) throws Exception {
		boolean erification = false;
		MerchandiseFineType merchandiseFineType = this.merchandiseFineTypeService.loadMerchandiseFineType(finetypeName,detailtypeCode);
		if (merchandiseFineType == null) {
			erification = true;
		}
		return erification;
	}

	/**
	 * 添加商品细分类维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode.insert")
	public void doInsertMerchandiseFineType() throws Exception {
		MerchandiseFineType merchandiseFineType = getMerchandiseFineType();
		boolean erification = doListMerchandiseFineTypeByInfo(merchandiseFineType.getFineTypeName(),merchandiseFineType.getDetailTypeCode());
		String msg = "该细分类名称已存在于您所选的中、小、明细类下，不可新增";
		if (erification == true) {
			msg = this.getText("public.success");
			User loginUser = PowerUtils.getCurrentUser();
			merchandiseFineType.setCreateby(loginUser.getLoginName());
			merchandiseFineTypeService.insertMerchandiseFineType(merchandiseFineType);
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 修改商品细分类维护
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode.edit")
	public void doUpdateMerchandiseFineType() throws Exception {
		// merchandiseFineTypeService.updateMerchandiseFineType(getMerchandiseFineType());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获取当前细分类ID是否存在于商品
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public boolean doListMerchandiseFineType(String finetypeCode) throws Exception {
		boolean erification = false;
		String[] role = finetypeCode.split(",");
		for (int i = 0; i < role.length; i++) {
			Integer count = merchandiseFineTypeService.serachMerchandiseFineType(role[i]);
			if (count == 0) {
				erification = true;
			} else {
				erification = false;
				break;
			}
		}
		return erification;
	}

	/**
	 * 删除商品细分类维护
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.fineTypeCode.delete")
	public void doDeleteMerchandiseFineType() throws Exception {
		boolean erification = doListMerchandiseFineType(asString("fineTypeCode"));
		String msg = "系统中目前有意向品属于该细分类，不可将该细分类从下拉列表中删除";
		if (erification == true) {
			msg = this.getText("public.success");
			merchandiseFineTypeService.deleteMerchandiseFineType(asString("fineTypeCode"));
		}
		this.forwardData(erification, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseFineType getMerchandiseFineType() throws Exception {
		MerchandiseFineType merchandiseFineType = new MerchandiseFineType();
		this.asBean(merchandiseFineType);
		return merchandiseFineType;
	}
}
