package _04_Money2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import _04_ShoppingCart.model.OrderItemBean;

public class MoneyBean {

	int id;
	Date changeTime;
	int changeAmount;
	int total;
	List<MoneyBean> items = new ArrayList<>();
	
	public MoneyBean() {
		
	};
	
	public MoneyBean(int id, Date changeTime, int changeAmount, int total, List<MoneyBean> items) {
		super();
		this.id = id;
		this.changeTime = changeTime;
		this.changeAmount = changeAmount;
		this.total = total;
		this.items = items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public int getChangeAmount() {
		return changeAmount;
	}
	public void setChangeAmount(int changeAmount) {
		this.changeAmount = changeAmount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
}
