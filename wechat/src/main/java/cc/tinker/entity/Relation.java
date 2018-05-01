package cc.tinker.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author code generator
 */
@Entity
//@Table(name = "relation")
public class Relation implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer studentId;
	private Integer courseId;

	public Relation() {
	}

	public Relation(Integer id) {
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

	@Column(name = "student_id")
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	@Column(name = "course_id")
	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}

}