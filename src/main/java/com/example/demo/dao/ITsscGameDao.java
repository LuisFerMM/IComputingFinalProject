package com.example.demo.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;


public interface ITsscGameDao {

	public void save(TsscGame entity);
	public void update(TsscGame entity);
	public void delete(TsscGame entity);
	public TsscGame findById(Long codigo);
	public List<TsscGame> findAll();
	public List<TsscGame> findByTopicName(String name);
	public List<TsscGame> findByTopicId(long id);
	public List<TsscGame> findByTopicDescription(String description);
	public List<TsscGame> findByDateRange(LocalDate schIniDate, LocalDate schLstDate);
	public List<TsscGame> findByDateAndTimeRange(LocalDate schDate, LocalTime schIniTime, LocalTime schLstTime);
	List<TsscGame> findByDateAndStrangeRestrictions(LocalDate date);
	
}
