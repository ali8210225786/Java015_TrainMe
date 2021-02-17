package _05_trainSchedule.service;

import java.util.List;

import _05_trainSchedule.dao.TimeOffDao;
import _05_trainSchedule.model.TimeOffBean;

public class TimeOffService {
	
	
	
	
	TimeOffDao timeOffDao = new TimeOffDao();
	
	public int saveTimeOff(TimeOffBean tob) {
		return timeOffDao.saveTimeOff(tob);
	}
	
	public int deleteTimeOff(String dateBegin ,String dateEnd,int trId) {
		return timeOffDao.deleteTimeOff(dateBegin, dateEnd, trId);
	}
	
	public List<String> queryTimeOffList(String dateBegin ,String dateEnd ,int trId) {
		return timeOffDao.queryTimeOffList(dateBegin, dateEnd, trId);
	}
	
	
}
