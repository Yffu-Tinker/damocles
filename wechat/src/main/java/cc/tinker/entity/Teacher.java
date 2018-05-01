package cc.tinker.entity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author code generator
 */
@Entity
//@Table(name = "teacher")
public class Teacher implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String openId;
	private String mobile;
	private String password;
	private String name;
	private String school;

	public Teacher() {
	}

	public Teacher(Integer id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "open_id", length = 32)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "password", length = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "school", length = 32)
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}