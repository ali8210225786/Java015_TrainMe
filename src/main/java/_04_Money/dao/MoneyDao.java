package _04_Money.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _00_init.util.DBService;
import _04_Money.model.MoneyBean;
import model.GymBean;

public class MoneyDao {
	
	private DataSource ds = null;
	private Connection con = null;

	public MoneyDao() {
		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MoneyDao類別#建構子發生例外: " + ex.getMessage());
		}
	}
	
	
	

	/**
	 * @param id
	 * @param type
	 * @return
	 */
	public List<MoneyBean> moneyList(int id , int type) {
		MoneyBean mb = null;
		 List<MoneyBean> moneys = new ArrayList<>();
		String sql = "SELECT * FROM money WHERE target_id = ? and target_type = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
				ps.setInt(1, id);
				ps.setInt(2, type);
				
			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					mb = new MoneyBean();
					mb.setId(rs.getInt("id"));
					mb.setTime(rs.getDate("change_time"));
					mb.setChange_amount(rs.getInt("change_amount"));
					mb.setTotal_amount(rs.getInt("total_amount"));
					
					moneys.add(mb);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#gymList()發生SQL例外: " + ex.getMessage());
		}
		return moneys;
	}
	
	
}
