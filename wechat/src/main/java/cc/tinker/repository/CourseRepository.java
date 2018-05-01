package cc.tinker.repository;

import cc.tinker.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    public List<Course> findByTeacherId(Integer teacherId);

    @Query(value = "select c.* from course c inner join relation r on c.id=r.course_id where r.student_id=?", nativeQuery = true)
    public List<Course> findByStudentId(Integer studentId);

}