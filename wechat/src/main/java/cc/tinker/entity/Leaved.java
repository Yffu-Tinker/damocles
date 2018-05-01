package cc.tinker.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

/**
 * @author code generator
 */
@Entity
//@Table(name = "leaved")
public class Leaved implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer courseId;
	private Integer studentId;
	private String reason;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;
	private Boolean permit;

	public Leaved() {
	}

	public Leaved(Integer id) {
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

	@Column(name = "course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "student_id")
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "reason", length = 255)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Column(name = "permit")
	public Boolean getPermit() {
		return permit;
	}

	public void setPermit(Boolean permit) {
		this.permit = permit;
	}

}