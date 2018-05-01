package cc.tinker.repository;

import cc.tinker.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Integer> {

    public List<Homework> findByCourseId(Integer courseId);


}