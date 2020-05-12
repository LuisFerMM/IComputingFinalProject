package com.example.demo.servicios;

import com.example.demo.modelo.TsscTopic;

public interface TopicService {

	public TsscTopic createTopic (TsscTopic topic);
	public TsscTopic updateTopic (TsscTopic topic);
	public TsscTopic getTopic (Long id);
	public void deleteTopic (TsscTopic id);
	public Iterable<TsscTopic> findAll();
} 
