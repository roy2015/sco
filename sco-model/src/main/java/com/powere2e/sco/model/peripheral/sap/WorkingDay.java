package com.powere2e.sco.model.peripheral.sap;

import com.powere2e.frame.server.model.AppModel;

import java.util.Date;
import java.util.List;

public class WorkingDay extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4811692599780843872L;
	private Date workingDay;// 节假日
	private String dayType;// 类型(NORM工作日/OFF非工作日)
	private Date syncDate;// 同步时间
	List<WorkingDay> list;

	public List<WorkingDay> getList() {
		return list;
	}

	public void setList(List<WorkingDay> list) {
		this.list = list;
	}

	public Date getWorkingDay() {
		return workingDay;
	}

	public void setWorkingDay(Date workingDay) {
		this.workingDay = workingDay;
	}

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public WorkingDay(Date workingDay, String dayType, Date syncDate) {
		super();
		this.workingDay = workingDay;
		this.dayType = dayType;
		this.syncDate = syncDate;
	}

	public WorkingDay() {
		super();
	}

}
