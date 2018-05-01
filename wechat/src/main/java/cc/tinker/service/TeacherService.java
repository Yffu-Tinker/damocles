package cc.tinker.service;

import cc.tinker.entity.Teacher;
import cc.tinker.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

	@Autowired
	private TeacherRepository teacherRepository;

	public Teacher findByOpenId(String openId) {
		return teacherRepository.findByOpenId(openId);
	}

	public Teacher save(Teacher teacher) {
		return teacherRepository.save(teacher);
	}

}