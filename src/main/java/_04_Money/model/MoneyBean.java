package _04_Money.model;

import java.util.Date;

public class MoneyBean {
	Integer id;
	Integer target_id;
	Integer target_type;
	Date time;
	Integer change_amount;
	Integer total_amount;
	
	public MoneyBean(Integer id, Integer target_id, Integer target_type, Date time, Integer change_amount,
			Integer total_amount) {
		super();
		this.id = id;
		this.target_id = target_id;
		this.target_type = target_type;
		this.time = time;
		this.change_amount = change_amount;
		this.total_amount = total_amount;
	}
	
	

	public MoneyBean() {
		
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTarget_id() {
		return target_id;
	}

	public void setTarget_id(Integer target_id) {
		this.target_id = target_id;
	}

	public Integer getTarget_type() {
		return target_type;
	}

	public void setTarget_type(Integer target_type) {
		this.target_type = target_type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getChange_amount() {
		return change_amount;
	}

	public void setChange_amount(Integer change_amount) {
		this.change_amount = change_amount;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}
	
	
	
	
}
