package cc.tinker.controller;

import cc.tinker.entity.Course;
import cc.tinker.entity.Homework;
import cc.tinker.entity.Leaved;
import cc.tinker.entity.Student;
import cc.tinker.service.CourseService;
import cc.tinker.service.HomeworkService;
import cc.tinker.service.LeavedService;
import cc.tinker.service.StudentService;
import cc.tinker.wechat.Wechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/student")
public class StrdentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private LeavedService leavedService;
    @Autowired
    private HomeworkService homeworkService;

    private Wechat wechat = Wechat.getInstance();

    @RequestMapping(value = "/bindingPage", method = RequestMethod.GET)
    public ModelAndView bindingPage(String code) {
        ModelAndView mav = null;
        String openId = wechat.getOAuthToken(code).getOpenId();
        Student student = studentService.findByOpenId(openId);
        if (student == null || !student.getBinding()) {
            mav = new ModelAndView("binding");
            mav.addObject("openId", openId);
        } else {
            mav = new ModelAndView("success");
            mav.addObject("message", "亲！你已经绑定过了！");
        }
        return mav;
    }

    @RequestMapping(value = "/binding", method = RequestMethod.POST)
    public ModelAndView binding(String openId, String school, String name, String number) {
        ModelAndView mav = new ModelAndView("success");
        mav.addObject("message", "绑定成功！");
        Student student = studentService.findByOpenId(openId);
        if (student == null) {
            student = new Student();
            student.setBinding(true);
            student.setOpenId(openId);
            student.setSchool(school);
            student.setName(name);
            student.setNumber(number);
            studentService.save(student);
        } else {
            studentService.updateInfo(school, name, number, true, openId);
        }
        return mav;
    }

    @RequestMapping(value = "/leavePage", method = RequestMethod.GET)
    public ModelAndView leavePage(String code) {
        ModelAndView mav = null;
        String openId = wechat.getOAuthToken(code).getOpenId();
        Student student = studentService.findByOpenId(openId);
        if (student == null || student.getBinding() == false) {
            mav = new ModelAndView("warn");
            mav.addObject("message", "你还没绑定，请先绑定！");
        } else {
            mav = new ModelAndView("leave");
            List<Course> courses = courseService.findByStudentId(student.getId());
            mav.addObject("openId", openId);
            mav.addObject("courses", courses);
        }
        return mav;
    }

    @RequestMapping(value = "/leave", method = RequestMethod.POST)
    public ModelAndView leave(String openId, Integer courseId, String reason, String date) {
        ModelAndView mav = new ModelAndView("success");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date leaveDate = null;
        try {
            leaveDate = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Student student = studentService.findByOpenId(openId);
        Leaved leave = new Leaved();
        leave.setCourseId(courseId);
        leave.setReason(reason);
        leave.setTime(new java.sql.Date(leaveDate.getTime()));
        leave.setStudentId(student.getId());
        leave.setPermit(false);
        leavedService.save(leave);
        return mav;
    }

    @RequestMapping(value = "/getHmwPage", method = RequestMethod.GET)
    public ModelAndView homeworkPage(String code) {
        ModelAndView mav = null;
        String openId = wechat.getOAuthToken(code).getOpenId();
        Student student = studentService.findByOpenId(openId);
        if (student == null || student.getBinding() == false) {
            mav = new ModelAndView("warn");
            mav.addObject("message", "你还没绑定，请先绑定！");
        } else {
            mav = new ModelAndView("homework");
            List<Course> courses = courseService.findByStudentId(student.getId());
            mav.addObject("courses", courses);
        }
        return mav;
    }

    @RequestMapping(value = "/getHmwList", method = RequestMethod.GET)
    public ModelAndView getHmwList(Integer courseId) {
        ModelAndView mav = new ModelAndView("list");
        List<Homework> homeworks = homeworkService.findByCourseId(courseId);
        mav.addObject("homeworks", homeworks);
        return mav;
    }

    @RequestMapping(value = "/getHmwDetail", method = RequestMethod.GET)
    public ModelAndView getHmwDetail(Integer homeworkId) {
        ModelAndView mav = new ModelAndView("detail");
        Homework homework = homeworkService.findById(homeworkId);
        mav.addObject("homework", homework);
        return mav;
    }

}
