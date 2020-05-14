package com.example.demo.dao;


import java.time.LocalDate;
import java.util.List;
import com.example.demo.modelo.TsscTopic;

public interface ITsscTopicDao {

	public void save(TsscTopic entity);
	public void update(TsscTopic entity);
	public void delete(TsscTopic entity);
	public TsscTopic findById(Long codigo);
	public List<TsscTopic> findByDescription(String description);
	public List<TsscTopic> findAll();
	public List<TsscTopic> findByName(String name);
	public List<TsscTopic> findByGameSchedule(LocalDate date);
}
