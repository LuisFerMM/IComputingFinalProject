package com.example.demo.servicios;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscSprint;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.repositorios.TopicRepository;
@Service
public class TopicServiceImp implements TopicService{

	private TopicRepository topicR;
	
	@Autowired
	public TopicServiceImp(TopicRepository t) {
		topicR = t;
	}
	
	@Override
	public TsscTopic createTopic(TsscTopic topic) {
		if(topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0)
		return topicR.save(topic);
		return null;
	}

	@Override
	public TsscTopic updateTopic(TsscTopic topic) throws NoSuchElementException{
		try {
			TsscTopic aeditar = topicR.findById(topic.getId()).get();
		if(topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0)
			return topicR.save(topic);
			return null;
		} catch (NoSuchElementException e) {
			throw e;
		}
	}

	@Override
	public TsscTopic getTopic(Long id) {
		Optional<TsscTopic> alv = topicR.findById(id);
		if(!alv.isEmpty())
			return alv.get();
		return null;
	}

	@Override
	public void deleteTopic(TsscTopic topic) {
		topicR.delete(topic);
	}
	@Override
	public Iterable<TsscTopic> findAll() {
		return topicR.findAll();
	}

}
