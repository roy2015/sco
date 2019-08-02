package com.powere2e.sco.common.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import net.sf.json.JsonConfig;
import net.sf.json.processors.DefaultValueProcessor;

public class BigDecimalConverter extends StrutsTypeConverter {

	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		BigDecimal bd = null;
		if (BigDecimal.class == toClass) {
			String bdStr = values[0];
			if (bdStr != null && !"".equals(bdStr) && CheckUploadUtil.checkNumber(bdStr)) {
				bd = new BigDecimal(bdStr);
			} else {
				// bd = BigDecimal.valueOf(-1);
			}
			return bd;
		}
		return BigDecimal.ZERO;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map arg0, Object arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * BigDecimal(为NULL时)转JSON字符串后为null(默认为0)
	 * 
	 * @return 配置
	 */
	public static JsonConfig setBigDecimalDefaultValue(){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerDefaultValueProcessor(BigDecimal.class, new DefaultValueProcessor() {
			@Override
			public Object getDefaultValue(@SuppressWarnings("rawtypes") Class type) {
				return null;
			}
        });
		return jsonConfig;
	}

}
