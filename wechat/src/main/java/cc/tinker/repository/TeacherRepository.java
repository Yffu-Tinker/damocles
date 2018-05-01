package cc.tinker.repository;

import cc.tinker.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	public Teacher findByOpenId(String openId);

}