package com.example.demo.servicios;

import java.util.Iterator;

import com.example.demo.modelo.TsscStory;

public interface StoryService {

	public TsscStory createStory (TsscStory story, Long game);
	public TsscStory updateStory (TsscStory story);
	public TsscStory getStory (Long id);
	public Iterable<TsscStory> findAll();
	void delete(long id);
}
