package cc.tinker.service;

import cc.tinker.entity.Sign;
import cc.tinker.repository.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignService {

	@Autowired
	private SignRepository signRepository;

	public Sign findTodaySign(Integer courseId, Integer studentId, Date today) {
		return signRepository.findTodaySign(courseId, studentId, today);
	}

	public Sign save(Sign sign) {
		return signRepository.save(sign);
	}

	public void updateSign(Double longitude, Double latitude, Date time, String result, Long id) {
		signRepository.updateSign(longitude, latitude, time, result, id);
	}

}