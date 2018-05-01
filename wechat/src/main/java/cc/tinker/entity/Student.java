package cc.tinker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author code generator
 */
@Entity
//@Table(name = "student")
public class Student implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String openId;
	private String school;
	private String name;
	private String number;
	private String password;
	private Double latitude;
	private Double longitude;
	private Boolean binding;

	public Student() {
	}

	public Student(Integer id) {
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

	@Column(name = "school", length = 32)
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "number", length = 16)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "password", length = 16)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "latitude", precision = 22, scale = 31)
	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "longitude", precision = 22, scale = 31)
	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "binding")
	public Boolean getBinding() {
		return binding;
	}

	public void setBinding(Boolean binding) {
		this.binding = binding;
	}

}