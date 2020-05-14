package com.example.demo.dao;


import java.util.List;

import com.example.demo.modelo.TsscStory;

public interface ITsscStoryDao {

	public void save(TsscStory entity);
	public void update(TsscStory entity);
	public void delete(TsscStory entity);
	public TsscStory findById(Long codigo);
	public List<TsscStory> findAll();
	
}
