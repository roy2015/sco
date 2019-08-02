package com.powere2e.sco.action.masterdata;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseRoleService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.security.model.Option;

/**
 * 商品角色模块
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年3月19日
 */
public class MerchandiseRoleAction extends UploadUtils {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6202538109598978227L;
	private MerchandiseRoleService merchandiseRoleService;

	@Override
	protected void beforeBuild() {
		merchandiseRoleService = (MerchandiseRoleService) ConfigFactory.getInstance().getBean("merchandiseRoleService");
	}

	/**
	 * 商品定性角色数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListQualitative() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = merchandiseRoleService.listQualitative(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 商品定量角色数据查询
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListQuantify() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Option> list = merchandiseRoleService.listQuantify(map);
		this.forwardData(true, list, null);
	}

	/**
	 * 显示导入商品角色数据界面
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.upload")
	public void doImportMerchandiseRole() {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRoleMaintenance/merchandiseRoleUploadForm.ftl");
	}

	/**
	 * 下载导入模板
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadMerchandiseRoleTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/assortmentData/merchandiseRole/merchandiseRole.xlsx", ExcelUtils.getEncodeFileName("商品角色维护数据") + ".xlsx");
	}

	/**
	 * 导入商品角色数据
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.upload")
	public void doCompleteImportMerchandiseRole() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		String msg = this.merchandiseRoleService.completeImportMerchandise(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 修改商品定性角色界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.edit")
	public void doShowUpdateMerchandiseDXRoleForm() throws Exception {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRoleMaintenance/merchandiseDxRoleForm.ftl");
	}

	/**
	 * 修改商品定量角色界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.edit")
	public void doShowUpdateMerchandiseDLRoleForm() throws Exception {
		this.forwardPage("sco/dataMaintenance/assortmentdata/merchandiseRoleMaintenance/merchandiseDlRoleForm.ftl");
	}

	/**
	 * 修改商品角色
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datemaintenance.assortmentdata.merchandiseRole.edit")
	public void doUpdateMerchandiseRole() throws Exception {
		Merchandise merchandise = this.getMerchaniseRole();
		merchandiseRoleService.updateMerchandise(merchandise);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Merchandise getMerchaniseRole() throws Exception {
		Merchandise merchandise = new Merchandise();
		this.asBean(merchandise);
		return merchandise;
	}
}
