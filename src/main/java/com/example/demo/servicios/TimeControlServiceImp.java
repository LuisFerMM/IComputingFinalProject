package com.example.demo.servicios;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ITsscTimecontrolDao;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTimecontrol;

@Service
public class TimeControlServiceImp  implements TimeControlService{

	@Autowired
	private GameServiceImp gS;

	@Autowired
	private ITsscTimecontrolDao timeControlDao;
	
	@Override
	public Iterable<TsscTimecontrol> findAll() {
		return timeControlDao.findAll();
	}

	@Override
	@Transactional
	public TsscTimecontrol createTimeControl(TsscTimecontrol timeControl, Long game) {	
		TsscGame g = gS.getGame(game);
		g.addTsscTimecontrol(timeControl);
		timeControlDao.save(timeControl);
		return timeControl;
	}

	@Override
	@Transactional
	public TsscTimecontrol updateTimeControl(TsscTimecontrol timeControl) {
		timeControlDao.update(timeControl);
		return timeControl;
	}

	@Override
	public TsscTimecontrol getTimeControl(Long id) {
		return timeControlDao.findById(id);
	}


	@Override
	@Transactional
	public void deleteTimeControl(TsscTimecontrol tsscTimecontrol) {
		timeControlDao.delete(tsscTimecontrol);
	}

	public Iterable<TsscTimecontrol> findByGameId(long id) {
		return timeControlDao.findByGameId(id);
	}
}
