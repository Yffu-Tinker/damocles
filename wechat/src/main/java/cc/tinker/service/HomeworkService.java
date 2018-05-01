package cc.tinker.service;

import cc.tinker.entity.Homework;
import cc.tinker.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository homeworkRepository;

    public Homework save(Homework homework) {
        return homeworkRepository.save(homework);
    }

    public List<Homework> findByCourseId(Integer courseId) {
        return homeworkRepository.findByCourseId(courseId);
    }

    public Homework findById(Integer id) {
        return homeworkRepository.getOne(id);
    }

}