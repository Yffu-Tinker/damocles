package cc.tinker.service;

import cc.tinker.entity.Student;
import cc.tinker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student findByOpenId(String openId) {
		return studentRepository.findByOpenId(openId);
	}
	
	public Student findWithBinding(String openId, Integer binding) {
		return studentRepository.findWithBinding(openId, binding);
	}

	public Student save(Student student) {
		return studentRepository.save(student);
	}
	
	public void updateLocation(Double longitude, Double latitude, String openId) {
		studentRepository.updateLocation(longitude, latitude, openId);
	}
	
	public void updateBinding(Boolean binding, String openId) {
		studentRepository.updateBinding(binding, openId);
	}
	
	public void updateInfo(String school, String name, String number, Boolean binding, String openId) {
		studentRepository.updateInfo(school, name, number, binding, openId);
	}

}