package _04_Money2.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_init.util.DBService;
import _01_register.model.StudentBean;
import _04_Money2.model.CourseBean;
import _04_Money2.model.MoneyBean;
import model.AreaBean;
import model.CityBean;
import model.GymBean;
import model.Hibernate.City;

public class MemPointDao {
	DataSource ds = null;

	public MemPointDao() {
		
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc���O#�غc�l�o�ͨҥ~: " + ex.getMessage());
		}
	}
	public List <MoneyBean> getMoneyDetail(int id , int type) {
		MoneyBean moneybean=null;
		String sql="SELECT* FROM money WHERE target_id= ? and target_type = ? ORDER BY change_time";
		List <MoneyBean> items=new ArrayList<>();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			ps.setInt(2, type);
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					moneybean = new MoneyBean();
					moneybean.setId(rs.getInt("target_id"));
					moneybean.setChangeTime(rs.getDate("change_time"));
					moneybean.setChangeAmount(rs.getInt("change_amount"));
					moneybean.setTotal(rs.getInt("total_amount"));

					items.add(moneybean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemDataDao���O#cityList()�o��SQL�ҥ~: " + ex.getMessage());
		}
		return items;
	}
	public List <CourseBean> getCourseDetail(int id) {
		CourseBean coursebean=null;
//		String sql="SELECT  FROM student_course WHERE st_id= ? ";
//		String sql1="SELECT  FROM student_course sc JOIN trainer_course tc ON sc.trainer_course_id=tc.id  WHERE st_id=?";
		String sql2="SELECT t.name, s.name, sc.date, sc.is_allowed, tc.price, sc.is_executed FROM student_course sc JOIN trainer_course tc ON sc.trainer_course_id=tc.id JOIN trainer t ON tc.tr_id=t.id JOIN skill s ON tc.sk_id=s.id WHERE st_id=?";
		List <CourseBean> items=new ArrayList<>();
		try (Connection con = ds.getConnection(); 
				PreparedStatement ps = con.prepareStatement(sql2);
				
				) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery();
					) {
				while (rs.next()) {
					coursebean = new CourseBean();
					coursebean.setDate(rs.getDate("date"));
					coursebean.setTainerName(rs.getString("t.name"));
					coursebean.setSkill(rs.getString("s.name"));
					coursebean.setIsAllowed(rs.getInt("sc.is_allowed"));
					coursebean.setPrice(rs.getInt("tc.price"));
					coursebean.setIsExecuted(rs.getInt("sc.is_executed"));
					
					items.add(coursebean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemDataDao���O#cityList()�o��SQL�ҥ~: " + ex.getMessage());
		}
		return items;
	}
	
	public int saveMoney(MoneyBean mb) {
		String sql = "insert into Money " + " (id, target_id, change_time, "
				+ " change_amount,  total_amount, student_course_id)" + " values (null, ?, ?, ?, ?, ?)";
		int n = 0;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, mb.getTargetId());
//			ps.setInt(2, mb.getType());
			ps.setDate(2, (Date) mb.getChangeTime());
			ps.setInt(3, mb.getChangeAmount());
			ps.setInt(4, mb.getTotal());
			ps.setInt(5, mb.getStudebtCourseId());

			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("StudentDaoImpl_Jdbc類別#saveMember()發生例外: " + ex.getMessage());
		}
		return n;
	}
}
