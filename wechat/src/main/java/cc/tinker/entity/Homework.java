package cc.tinker.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author code generator
 */
@Entity
//@Table(name = "homework")
public class Homework implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date publishTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.sql.Date finishTime;
	private Integer courseId;
	private String file;

	public Homework() {
	}

	public Homework(Integer id) {
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

	@Column(name = "name", length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "content", length = 255)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "publish_time")
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "finish_time")
	public java.sql.Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(java.sql.Date finishTime) {
		this.finishTime = finishTime;
	}

	@Column(name = "course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

	@Column(name = "file", length = 255)
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}