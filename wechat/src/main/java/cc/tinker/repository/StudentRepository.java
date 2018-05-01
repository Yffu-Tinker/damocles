package cc.tinker.repository;

import cc.tinker.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Student findByOpenId(String openId);

	@Query(value = "select s.* from student s where s.open_id=? and s.binding=?", nativeQuery = true)
	public Student findWithBinding(String openId, Integer binding);

	@Modifying
	@Query(value = "update student s set s.longitude=?,s.latitude=? where s.open_id=?", nativeQuery = true)
	public void updateLocation(Double longitude, Double latitude, String openId);

	@Modifying
	@Query(value = "update student s set s.binding=? where s.open_id=?", nativeQuery = true)
	public void updateBinding(Boolean binding, String openId);

	@Modifying
	@Query(value = "update student s set s.school=?,s.name=?,s.number=?,s.binding=? where s.open_id=?", nativeQuery = true)
	public void updateInfo(String school, String name, String number, Boolean binding, String openId);

}