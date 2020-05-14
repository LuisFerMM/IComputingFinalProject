package com.example.demo.dao;


import java.util.List;

import com.example.demo.modelo.TsscTimecontrol;

public interface ITsscTimecontrolDao {

	public void save(TsscTimecontrol entity);
	public void update(TsscTimecontrol entity);
	public void delete(TsscTimecontrol entity);
	public TsscTimecontrol findById(Long codigo);
	public List<TsscTimecontrol> findAll();
	
}
