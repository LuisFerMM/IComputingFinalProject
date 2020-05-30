package com.example.demo.servicios;


import org.springframework.stereotype.Service;

import com.example.demo.dao.ITsscTimecontrolDao;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTimecontrol;

@Service
public class TimeControlServiceImp  implements TimeControlService{

	private GameServiceImp gS;

	private ITsscTimecontrolDao timeControlDao;
	
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return timeControlDao.findAll();
	}

	@Override
	public TsscTimecontrol createTimeControl(TsscTimecontrol timeControl, Long game) {	
		TsscGame g = gS.getGame(game);
		g.addTsscTimecontrol(timeControl);
		timeControlDao.save(timeControl);
		return timeControl;
	}

	@Override
	public TsscTimecontrol updateTimeControl(TsscTimecontrol timeControl) {
		timeControlDao.save(timeControl);
		return timeControl;
	}

	@Override
	public TsscTimecontrol getTimeControl(Long id) {
		return timeControlDao.findById(id);
	}


	@Override
	public void deleteTimeControl(TsscTimecontrol tsscTimecontrol) {
		timeControlDao.delete(tsscTimecontrol);
	}
}
