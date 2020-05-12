package com.example.demo.servicios;

import com.example.demo.modelo.TsscTimecontrol;

public interface TimeControlService {

	public TsscTimecontrol createTimeControl (TsscTimecontrol timeControl);
	public TsscTimecontrol updateTimeControl (TsscTimecontrol timeControl);
	public TsscTimecontrol getTimeControl (Long id);
	public void deleteTimeControl (Long id);
}
