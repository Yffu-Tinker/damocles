package cc.tinker.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author code generator
 */
@Entity
//@Table(name = "sign")
public class Sign implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private Integer courseId;
	private Integer studentId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date time;
	private Double latitude;
	private Double longitude;
	private String result;

	public Sign() {
	}

	public Sign(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Column(name = "time")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	@Column(name = "result", length = 4)
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}