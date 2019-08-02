package com.powere2e.sco.action.datamaintenance.gradecontroldata.merchandiseunqualified;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualifiedService;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiseunqualified.MerchandiseUnqualified;

/**
 * 商品外部抽检不合格记录的WEB请求响应类
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月15日
 */
public class MerchandiseUnqualifiedAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8978337513623943533L;
	private MerchandiseUnqualifiedService merchandiseUnqualifiedService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseUnqualifiedService = (MerchandiseUnqualifiedService) ConfigFactory.getInstance().getBean("merchandiseUnqualifiedManager");
	}

	/**
	 * 
	 * 显示抽检不合格列表页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified")
	public void doShowMerchandiseUnqualified() throws Exception {
		this.forwardPage("sco/dataMaintenance/gradecontrolData/merchandiseUnqualified/merchandiseUnqualifiedGrid.ftl");
	}

	/**
	 * 显示上传抽检不合格 界面
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.upload")
	public void doShowMerchandiseUnqualifiedForm() {
		this.forwardPage("sco/dataMaintenance/gradecontrolData/merchandiseUnqualified/merchandiseUnqualifiedUploadForm.ftl");
	}

	/**
	 * 上传Excel中商品不合格数据
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.upload")
	public void doCompleteImportMerchandiseUnqualified() {
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		String msg = this.merchandiseUnqualifiedService.completeImportMerchandiseUnqualified(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 查询商品外部抽检不合格记录列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified")
	public void doListMerchandiseUnqualified() throws Exception {
		Map<String, Object> map = getMerchandiseUnqualified().toMap();
		map.put("spotCheckDate", this.asString("spotCheckDate"));
		map.put("minSpotCheckDate", this.asString("minSpotCheckDate"));
		map.put("maxSpotCheckDate", this.asString("maxSpotCheckDate"));
		map.put("created", this.asString("created"));
		map.put("updated", this.asString("updated"));
		List<MerchandiseUnqualified> list = merchandiseUnqualifiedService.listMerchandiseUnqualified(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseUnqualified getMerchandiseUnqualified() throws Exception {
		MerchandiseUnqualified merchandiseUnqualified = new MerchandiseUnqualified();
		this.asBean(merchandiseUnqualified);
		return merchandiseUnqualified;
	}

	/**
	 * 添加商品外部抽检不合格记录界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.insert")
	public void doShowInsertMerchandiseUnqualifiedForm() throws Exception {
		MerchandiseUnqualified merchandiseUnqualified = new MerchandiseUnqualified();
		this.putObject("merchandiseUnqualified", merchandiseUnqualified);
		this.forwardPage("merchandiseUnqualified/merchandiseUnqualifiedForm.ftl");
	}

	@Authority(privilege = "com.powere2e.sco.datamaintenance.gradecontroldata.merchandiseunqualified.upload")
	public void doDownloadMerchandiseUnqualifiedTemplate() throws Exception {
		this.forwardDownload("excel/sco/dataMaintenance/gradecontrolData/merchandiseUnqualified/merchandiseUnqualified.xlsx", ExcelUtils.getEncodeFileName("商品外部抽检不合格记录表") + ".xlsx");
	}

}
