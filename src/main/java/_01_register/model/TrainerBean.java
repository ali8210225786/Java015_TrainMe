package _01_register.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author ali82
 *
 */
public class TrainerBean extends MemberBean {
	private static final long serialVersionUID = 1L;
	

	Integer trNo;
	Integer type;
	String name;
	String phone;
	String email;
	Date birth;
	String password;
	String id;
	String sex;
	Integer gymId;
	Integer verification;
	Integer is_delete;
	String myHash;
	
// =======================
	
	Integer year;
	Integer city_id;
	Integer area_id;
	String address;
	String photo;
	String nickname;
	String bank_account;
	String course;
	String introduction;
	
	
//=========================
	
	
	
	
	
	public TrainerBean(Integer trNo, Integer type, String name, String phone, String email, Date birth, String password,
			String id, String sex,Integer gymId, Integer verification, Integer is_delete,
			String myHash) {
		super();
		this.trNo = trNo;
		this.type = type;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birth = birth;
		this.password = password;
		this.id = id;
		this.sex = sex;
		this.gymId = gymId;
		this.verification = verification;
		this.is_delete = is_delete;
		this.myHash = myHash;
	}


	public TrainerBean() {
		
	}
	
	
	public Integer getTrNo() {
		return trNo;
	}
	public void setTrNo(Integer trNo) {
		this.trNo = trNo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getGymId() {
		return gymId;
	}
	public void setGymId(Integer gymId) {
		this.gymId = gymId;
	}
	public Integer getVerification() {
		return verification;
	}
	public void setVerification(Integer verification) {
		this.verification = verification;
	}
	public Integer getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(Integer is_delete) {
		this.is_delete = is_delete;
	}


	public String getMyHash() {
		return myHash;
	}


	public void setMyHash(String myHash) {
		this.myHash = myHash;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}


	public Integer getCity_id() {
		return city_id;
	}


	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}


	public Integer getArea_id() {
		return area_id;
	}


	public void setArea_id(Integer area_id) {
		this.area_id = area_id;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoto() {
		return photo;
	}


	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getBank_account() {
		return bank_account;
	}


	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getIntroduction() {
		return introduction;
	}


	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	
	
	
	
	

}

