package _03_memberData.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.annotation.MultipartConfig;
import javax.sql.DataSource;

import _00_init.util.DBService;
import _01_register.model.StudentBean;
import _01_register.model.TrainerBean;
import model.AreaBean;
import model.CityBean;
import model.GymBean;
import model.Hibernate.City;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
* 500, maxRequestSize = 1024 * 1024 * 500 * 5)
public class MemDataDao {
	DataSource ds = null;

	public MemDataDao() {

		System.out.println("DBService.JNDI_DB_NAME=" + DBService.JNDI_DB_NAME);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(DBService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemDataDao 發生錯誤: " + ex.getMessage());
		}
	}

	public List<CityBean> cityList() {
		CityBean city = null;
		List<CityBean> citys = new ArrayList<CityBean>();
		String sql = "SELECT * FROM city;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					city = new CityBean();
					city.setId(rs.getInt("id"));
					city.setName(rs.getString("name"));

					citys.add(city);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemDataDao類別的#cityList()發生SQL錯誤: " + ex.getMessage());
		}
		return citys;
	}

	public List<AreaBean> areaList(int city_id) {
		AreaBean area = null;
		List<AreaBean> areas = new ArrayList<AreaBean>();
		String sql = "SELECT * FROM area WHERE city_id = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {

			ps.setInt(1, city_id);

			try (ResultSet rs = ps.executeQuery();) {
				while (rs.next()) {
					area = new AreaBean();
					area.setId(rs.getInt("id"));
					area.setName(rs.getString("name"));
					area.setCity_id(rs.getInt("city_id"));

					areas.add(area);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemDataDao類別的#areaList()發生SQL錯誤:" + ex.getMessage());
		}
		return areas;
	}

	public int updateStudentData(StudentBean sb) {
		int result = 0;
		String sql1 = "UPDATE  student "
				+ " SET city_id =  ?  , area_id = ? , address = ? , nickname = ? , phone = ? "
				+ " WHERE id = ?";
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement ps1 = con.prepareStatement(sql1);) {
			ps1.setInt(1, sb.getCity_id());
			ps1.setInt(2, sb.getArea_id());
			ps1.setString(3,  sb.getAddress());
			ps1.setString(4,  sb.getNickname());
			ps1.setString(5,  sb.getPhone());
			ps1.setInt(6, sb.getStNo());

			result = ps1.executeUpdate();
			
			
			if (result == 1) {
				System.out.println(sb.getName() + " update successfully ");
			} else {
				System.out.println(sb.getName() + " gets wrong ");				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateStudentData()發生SQL例外: " + ex.getMessage());
		}
		return result;

	}
	public int updateStudentDataPhoto(StudentBean sb) {
		int result = 0;
		String sql1 = "UPDATE  student "
				+ " SET city_id =  ?  , area_id = ? , address = ? , nickname = ? , phone = ? , profile_image = ?"
				+ " WHERE id = ?";
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement ps1 = con.prepareStatement(sql1);) {
			ps1.setInt(1, sb.getCity_id());
			ps1.setInt(2, sb.getArea_id());
			ps1.setString(3,  sb.getAddress());
			ps1.setString(4,  sb.getNickname());
			ps1.setString(5,  sb.getPhone());
			ps1.setString(6, sb.getPhoto());
			ps1.setInt(7, sb.getStNo());
			
			result = ps1.executeUpdate();
			
			
			if (result == 1) {
				System.out.println(sb.getName() + " update successfully ");
			} else {
				System.out.println(sb.getName() + " gets wrong ");				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateUnpaidOrderAmount()發生SQL例外: " + ex.getMessage());
		}
		return result;
		
	}
	public int updateTrainerData(TrainerBean tb) {
		int result = 0;
		String sql1 = "UPDATE  trainer "
				+ " SET city_id =  ?  , area_id = ? , address = ? , nickname = ? , phone = ? , bank_account = ? "
				+ " WHERE id = ?";
		
		try (Connection con = ds.getConnection(); 
			PreparedStatement ps1 = con.prepareStatement(sql1);) {
			ps1.setInt(1, tb.getCity_id());
			ps1.setInt(2, tb.getArea_id());
			ps1.setString(3,  tb.getAddress());
			ps1.setString(4,  tb.getNickname());
			ps1.setString(5,  tb.getPhone());
			ps1.setString(6, tb.getBank_account());
			ps1.setInt(7, tb.getTrNo());

			result = ps1.executeUpdate();
			
			
			if (result == 1) {
				System.out.println(tb.getName() + " update successfully ");
			} else {
				System.out.println(tb.getName() + " gets wrong ");				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateTrainerData()發生SQL例外: " + ex.getMessage());
		}
		return result;

	}
	
	public int updateTrainerDataPhoto(TrainerBean tb) {
		int result = 0;
		String sql1 = "UPDATE  trainer "
				+ " SET city_id =  ?  , area_id = ? , address = ? , nickname = ? , phone = ? , profile_image = ?, bank_account = ?"
				+ " WHERE id = ?";
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement ps1 = con.prepareStatement(sql1);) {
			ps1.setInt(1, tb.getCity_id());
			ps1.setInt(2, tb.getArea_id());
			ps1.setString(3,  tb.getAddress());
			ps1.setString(4,  tb.getNickname());
			ps1.setString(5,  tb.getPhone());
			ps1.setString(6, tb.getPhoto());
			ps1.setString(7, tb.getBank_account());
			ps1.setInt(8, tb.getTrNo());
			
			result = ps1.executeUpdate();
			
			
			if (result == 1) {
				System.out.println(tb.getName() + " update successfully ");
			} else {
				System.out.println(tb.getName() + " gets wrong ");				
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#updateTrainerDataPhoto()發生SQL例外: " + ex.getMessage());
		}
		return result;
		
	}

}
