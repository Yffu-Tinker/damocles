package cc.tinker.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @author code generator
 */
@Entity
//@Table(name = "course")
public class Course implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String className;
	private String week;
	@DateTimeFormat(pattern = "HH:mm:ss")
	private Time beginTime;
	private String location;
	private Integer teacherId;
	private Integer signCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date latestSign;

	public Course() {
	}

	public Course(Integer id) {
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

	@Column(name = "class_name", length = 32)
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Column(name = "week", length = 8)
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Column(name = "begin_time")
	public Time getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Time beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "location", length = 64)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "teacher_id")
	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	@Column(name = "sign_count")
	public Integer getSignCount() {
		return signCount;
	}

	public void setSignCount(Integer signCount) {
		this.signCount = signCount;
	}

	@Column(name = "latest_sign")
	public Date getLatestSign() {
		return latestSign;
	}

	public void setLatestSign(Date latestSign) {
		this.latestSign = latestSign;
	}

}