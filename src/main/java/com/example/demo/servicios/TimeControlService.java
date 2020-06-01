package com.example.demo.servicios;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTimecontrol;

public interface TimeControlService {

	public TsscTimecontrol createTimeControl (TsscTimecontrol timeControl, Long game);
	public TsscTimecontrol updateTimeControl (TsscTimecontrol timeControl);
	public TsscTimecontrol getTimeControl (Long id);
	public void deleteTimeControl (long id);
	public Iterable<TsscTimecontrol> findAll();
}
