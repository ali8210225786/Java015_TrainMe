package _05_trainSchedule.model;

import java.sql.Date;

public class TimeOffBean {
	
	Integer id;
	Integer trId;
	Date date;
	Integer time;
	Integer stId;
	
	public TimeOffBean(Integer id, Integer trId, Date date, Integer time) {
		super();
		this.id = id;
		this.trId = trId;
		this.date = date;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTrId() {
		return trId;
	}

	public void setTrId(Integer trId) {
		this.trId = trId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getStId() {
		return stId;
	}

	public void setStId(Integer stId) {
		this.stId = stId;
	}
	
	
	
	
	
	
}
