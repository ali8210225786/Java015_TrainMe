package _04_Money2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import _04_ShoppingCart.model.OrderItemBean;



	public class MoneyBean {

		int id;
		int targetId;
		int type;
		java.sql.Date changeTime;
		int changeAmount;
		int total;
		List<MoneyBean> items = new ArrayList<>();
		int studebtCourseId;
		public MoneyBean() {
			
		};
		
		public MoneyBean(int id,int targetId, int type, java.sql.Date changeTime, int changeAmount, int total) {
			super();
			this.id = id;
			this.targetId=targetId;
			this.type = type;
			this.changeTime = changeTime;
			this.changeAmount = changeAmount;
			this.total = total;
			
		}
		public int getTargetId() {
			return targetId;
		}

		public void setTargetId(int targetId) {
			this.targetId = targetId;
		}

		public int getStudebtCourseId() {
			return studebtCourseId;
		}

		public void setStudebtCourseId(int studebtCourseId) {
			this.studebtCourseId = studebtCourseId;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public List<MoneyBean> getItems() {
			return items;
		}

		public void setItems(List<MoneyBean> items) {
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
		public void setChangeTime(java.sql.Date changeTime) {
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
