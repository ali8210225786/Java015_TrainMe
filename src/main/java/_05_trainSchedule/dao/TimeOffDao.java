package _05_trainSchedule.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_init.util.DBService;
import _01_register.model.StudentBean;
import _05_trainSchedule.model.TimeOffBean;


public class TimeOffDao {
	
	private DataSource ds = null;

	public  TimeOffDao() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("TimeOffDao類別#建構子發生例外: " + ex.getMessage());
		}
	}
	
	public int saveTimeOff(TimeOffBean tob) {
		int n = 0 ;
		String sql = "INSERT INTO trainer_off (id, tr_id, date, time) values(null, ?, ?, ?)";
		try (Connection con = ds.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);){
			
				ps.setInt(1, tob.getTrId());
				ps.setDate(2, tob.getDate());
				ps.setInt(3, tob.getTime());
							
				n = ps.executeUpdate();
			return n;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("TimeOffDao類別#saveTimeOff()發生例外: " + e.getMessage());
		}	
	}
	
	public int deleteTimeOff(String dateBegin ,String dateEnd,int trId) {
		int n = 0;
		System.out.println(dateBegin);
		System.out.println(dateEnd);
		System.out.println(trId);
		String sql = "DELETE FROM trainer_off WHERE date BETWEEN '" + dateBegin + "' and '" + dateEnd +
					 "' AND tr_id = ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, trId);
			

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("TimeOffDao類別#deleteTimeOff()發生例外: " + ex.getMessage());
		}
		return n;
		
	}
	
	public List<String> queryTimeOffList(String dateBegin ,String dateEnd,int trId) {
		List<String> list = new ArrayList<>();
		TimeOffBean tob ;
		String sql = "SELECT date , time FROM trainer_off WHERE date BETWEEN '" + dateBegin + "' and '" + dateEnd 
				+ "' AND tr_id = ?";
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			
			ps.setInt(1, trId);
			
			try (ResultSet rs = ps.executeQuery();) {
				while(rs.next()) {
					tob = new TimeOffBean();
					tob.setDate(rs.getDate("date"));
					tob.setTime(rs.getInt("time"));
					String closeHour = rs.getDate("date") + "_" + rs.getInt("time");
					list.add(closeHour);

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("TimeOffDao類別#deleteTimeOff()發生例外: " + ex.getMessage());
		}
		
		return list;
	}
	
}
