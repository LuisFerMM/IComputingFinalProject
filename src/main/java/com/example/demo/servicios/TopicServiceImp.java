package com.example.demo.servicios;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TsscTopicDao;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscSprint;
import com.example.demo.modelo.TsscTopic;
@Service
public class TopicServiceImp implements TopicService{

	private TsscTopicDao topicR;
	
	@Autowired
	public TopicServiceImp(TsscTopicDao t) {
		topicR = t;
	}
	
	@Override
	@Transactional
	public TsscTopic createTopic(TsscTopic topic) {
		if(topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0) {
		topicR.save(topic);
		return topicR.findById(topic.getId());
		}
		return null;
	}

	@Override
	@Transactional
	public TsscTopic updateTopic(TsscTopic topic) throws NoSuchElementException{
		
		TsscTopic aeditar = topicR.findById(topic.getId());
		if(aeditar != null) {
		if(topic.getDefaultSprints()>0 && topic.getDefaultGroups()>0) {
			topicR.update(topic);
			return topicR.findById(topic.getId());
		} else return null;
		}
		throw new NoSuchElementException();
	}

	@Override
	public TsscTopic getTopic(Long id) {
		return topicR.findById(id);
		
	}

	@Override
	@Transactional
	public void deleteTopic(TsscTopic topic) {
		topicR.delete(topic);
	}
	@Override
	public Iterable<TsscTopic> findAll() {
		return topicR.findAll();
	}

}
