package cc.tinker.repository;

import cc.tinker.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelationRepository extends JpaRepository<Relation, Integer> {

	public Relation findByCourseIdAndStudentId(Integer courseId, Integer studentId);

}