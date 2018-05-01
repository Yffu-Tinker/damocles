package cc.tinker.service;

import cc.tinker.entity.Relation;
import cc.tinker.repository.RelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationService {

	@Autowired
	private RelationRepository relationRepository;

	public Relation findByCourseIdAndStudentId(Integer courseId, Integer studentId) {
		return relationRepository.findByCourseIdAndStudentId(courseId, studentId);
	}

	public Relation save(Relation relation) {
		return relationRepository.save(relation);
	}

}