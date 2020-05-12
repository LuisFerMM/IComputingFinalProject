package com.example.demo.servicios;

import com.example.demo.modelo.TsscSprint;

public interface SprintService {

	public TsscSprint createSprint (TsscSprint sprint);
	public TsscSprint updateSprint (TsscSprint sprint);
	public TsscSprint getSprint (Long id);
	public void deleteSprint (Long id);
}
