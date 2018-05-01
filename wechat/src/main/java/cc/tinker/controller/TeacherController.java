package cc.tinker.controller;

import cc.tinker.entity.Course;
import cc.tinker.entity.Homework;
import cc.tinker.entity.Teacher;
import cc.tinker.service.CourseService;
import cc.tinker.service.HomeworkService;
import cc.tinker.service.TeacherService;
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
@RequestMapping(value = "/teacher")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private HomeworkService homeworkService;

	private Wechat wechat = Wechat.getInstance();

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public ModelAndView registerPage(String code) {
		ModelAndView mav = null;
		String openId = wechat.getOAuthToken(code).getOpenId();
		Teacher teacher = teacherService.findByOpenId(openId);
		if (teacher == null) {
			mav = new ModelAndView("register");
			mav.addObject("openId", openId);
		} else {
			mav = new ModelAndView("success");
			mav.addObject("message", "亲！你已经成功注册过了！");
		}
		return mav;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView register(String openId, String school, String name, String mobile, String password) {
		ModelAndView mav = new ModelAndView("success");
		mav.addObject("message", "注册成功！");
		Teacher teacher = new Teacher();
		teacher.setOpenId(openId);
		teacher.setSchool(school);
		teacher.setName(name);
		teacher.setMobile(mobile);
		teacher.setPassword(password);
		teacherService.save(teacher);
		return mav;
	}

	@RequestMapping(value = "/publishPage", method = RequestMethod.GET)
	public ModelAndView publishPage(String code) {
		ModelAndView mav = null;
		String openId = wechat.getOAuthToken(code).getOpenId();
		Teacher teacher = teacherService.findByOpenId(openId);
		if (teacher == null) {
			mav = new ModelAndView("warn");
			mav.addObject("message", "你还没注册，请先注册！");
		} else {
			List<Course> courses = courseService.findByTeacherId(teacher.getId());
			if (courses != null && courses.size() > 0) {
				mav = new ModelAndView("publish");
				mav.addObject("openId", openId);
				mav.addObject("courses", courses);
			} else {
				mav = new ModelAndView("warn");
				mav.addObject("message", "你目前还没有任何课程！");
			}
		}
		return mav;
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public ModelAndView publish(String openId, Integer courseId, String name, String content, String date) {
		ModelAndView mav = new ModelAndView("success");
		Homework homework = new Homework();
		homework.setCourseId(courseId);
		homework.setName(name);
		homework.setContent(content);
		homework.setPublishTime(new Date());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			homework.setFinishTime(new java.sql.Date(format.parse(date).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		homeworkService.save(homework);
		return mav;
	}

}
