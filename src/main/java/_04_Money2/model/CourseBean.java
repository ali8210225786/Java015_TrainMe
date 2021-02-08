package _04_Money2.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import _04_ShoppingCart.model.OrderItemBean;

public class CourseBean {
Date date;
String skill;
String tainerName;
List<CourseBean> coursebean= new ArrayList<>();



public CourseBean(Date date, String skill, String tainerName, List<CourseBean> coursebean) {
	super();
	this.date = date;
	this.skill = skill;
	this.tainerName = tainerName;
	this.coursebean = coursebean;
}

public List<CourseBean> getCoursebean() {
	return coursebean;
}

public void setCoursebean(List<CourseBean> coursebean) {
	this.coursebean = coursebean;
}

public String getTainerName() {
	return tainerName;
}

public void setTainerName(String tainerName) {
	this.tainerName = tainerName;
}

public CourseBean() {
	super();
}

public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}

	
	
}
