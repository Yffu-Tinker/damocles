package cc.tinker.service;

import cc.tinker.entity.Leaved;
import cc.tinker.repository.LeavedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeavedService {
	
	@Autowired
	private LeavedRepository leavedRepository;
	
	public Leaved save(Leaved leaved) {
		return leavedRepository.save(leaved);
	}

}