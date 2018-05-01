package cc.tinker.repository;

import cc.tinker.entity.Sign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SignRepository extends JpaRepository<Sign, Long> {

	@Query(value = "select s.* from sign s where s.course_id=? and s.student_id=? and s.time>?", nativeQuery = true)
	public Sign findTodaySign(Integer courseId, Integer studentId, Date today);

	@Modifying
	@Query(value = "update sign s set s.longitude=?,s.latitude=?,s.time=?,s.result=? where s.id=?", nativeQuery = true)
	public void updateSign(Double longitude, Double latitude, Date time, String result, Long id);

}