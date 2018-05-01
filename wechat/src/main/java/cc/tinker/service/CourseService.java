package cc.tinker.service;

import cc.tinker.entity.Course;
import cc.tinker.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	public List<Course> findByTeacherId(Integer teacherId) {
		return courseRepository.findByTeacherId(teacherId);
	}

	public List<Course> findByStudentId(Integer studentId) {
		return courseRepository.findByStudentId(studentId);
	}

}