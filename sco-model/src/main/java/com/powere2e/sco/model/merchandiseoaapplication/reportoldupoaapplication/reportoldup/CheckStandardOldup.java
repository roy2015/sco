package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup;
import com.powere2e.frame.server.model.AppModel;
/**
 * 报告检验标准(老品新上)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class CheckStandardOldup extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253046859834406224L;
	private String reportCode;//
	private String oldStandardColour;//
	private String newStandardColour;//
	private String oldStandardSmell;//
	private String newStandardSmell;//
	private String oldStandardFrom;//
	private String newStandardForm;//
	private String oldMoistureContent;//历史水分含量
	private String newMoistureContent;//本次水分含量

	
	// 获取
	public String getReportCode() {
		return reportCode;
	}
	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	
	// 获取
	public String getOldStandardColour() {
		return oldStandardColour;
	}
	// 设置
	public void setOldStandardColour(String oldStandardColour) {
		this.oldStandardColour = oldStandardColour;
	}
	
	// 获取
	public String getNewStandardColour() {
		return newStandardColour;
	}
	// 设置
	public void setNewStandardColour(String newStandardColour) {
		this.newStandardColour = newStandardColour;
	}
	
	// 获取
	public String getOldStandardSmell() {
		return oldStandardSmell;
	}
	// 设置
	public void setOldStandardSmell(String oldStandardSmell) {
		this.oldStandardSmell = oldStandardSmell;
	}
	
	// 获取
	public String getNewStandardSmell() {
		return newStandardSmell;
	}
	// 设置
	public void setNewStandardSmell(String newStandardSmell) {
		this.newStandardSmell = newStandardSmell;
	}
	
	// 获取
	public String getOldStandardFrom() {
		return oldStandardFrom;
	}
	// 设置
	public void setOldStandardFrom(String oldStandardFrom) {
		this.oldStandardFrom = oldStandardFrom;
	}
	
	// 获取
	public String getNewStandardForm() {
		return newStandardForm;
	}
	// 设置
	public void setNewStandardForm(String newStandardForm) {
		this.newStandardForm = newStandardForm;
	}
	public String getOldMoistureContent() {
		return oldMoistureContent;
	}
	public void setOldMoistureContent(String oldMoistureContent) {
		this.oldMoistureContent = oldMoistureContent;
	}
	public String getNewMoistureContent() {
		return newMoistureContent;
	}
	public void setNewMoistureContent(String newMoistureContent) {
		this.newMoistureContent = newMoistureContent;
	}
	
}