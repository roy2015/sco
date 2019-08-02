package com.powere2e.sco.action.accessoryoaapplication.nonFoodApply.applicationschedule;

import java.util.List;
import java.util.Map;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.applicationschedule.NonFoodApplicationScheduleService;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 非食品竞价单OA申请进度信息 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public class NonFoodApplicationScheduleAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8473753501171754671L;
	private NonFoodApplicationScheduleService NonFoodApplicationScheduleService;

	@Override
	protected void beforeBuild() {
		NonFoodApplicationScheduleService = (NonFoodApplicationScheduleService) ConfigFactory
				.getInstance().getBean("nonFoodApplicationScheduleService");
	}

	/**
	 * 显示进度信息列表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListApplicationScheduleNonFood() throws Exception {
		List<AccessoryApplicationSchedulea> list = this.NonFoodApplicationScheduleService
				.listApplicationScheduleNonFood(this.getAppliScheMap(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 保存进度信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doInsertApplicationScheduleNonFood() throws Exception {
		String rows = this.asString("rows");
		if(!StringUtils.isBlank(rows)) {
			JSONArray jsonArr = JSONArray.fromObject(rows);
			AccessoryApplicationSchedulea[] dataArray = new AccessoryApplicationSchedulea[jsonArr.size()];
			for (int j = 0; j < jsonArr.size(); j++) {
				String[] dateFormats = new String[] { "yyyy-MM-dd" };
				JSONUtils.getMorpherRegistry().registerMorpher(
						new DateMorpher(dateFormats));
				dataArray[j] = (AccessoryApplicationSchedulea) JSONObject.toBean(
						jsonArr.getJSONObject(j), AccessoryApplicationSchedulea.class);
			}
			this.NonFoodApplicationScheduleService.insertApplicationScheduleNonFood(
					this.getAppliScheMap(), dataArray);
		}
		this.forwardData(true, null, "保存成功");
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Map<String, Object> getAppliScheMap() throws Exception {
		AccessoryApplicationSchedulea applicationSchedule = new AccessoryApplicationSchedulea();
		this.asBean(applicationSchedule);
		Map<String, Object> map = applicationSchedule.toMap();
		map.put("quotedCodes", this.asString("quotedCodes"));
		map.put("applicationCode", this.asString("applicationCode"));
		return map;
	}

}
