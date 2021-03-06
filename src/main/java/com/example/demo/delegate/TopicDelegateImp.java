package com.example.demo.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscTopic;
@Component
public class TopicDelegateImp implements TopicDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/backapi/";
	
	public TopicDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscTopic GET_Topic(long id) {
		TsscTopic tsscTopic = restTemplate.getForObject(SERVER+"topics/"+id, TsscTopic.class);
		return tsscTopic;
	}
	
	@Override
	public Iterable<TsscTopic> GET_Topics() {
		TsscTopic[] tsscTopics = restTemplate.getForObject(SERVER+"topics", TsscTopic[].class);
		List<TsscTopic> lisTopics = Arrays.asList(tsscTopics);
		return lisTopics;
	}

	@Override
	public TsscTopic POST_Topic(TsscTopic POSTTopic) {
		HttpEntity req = new HttpEntity(POSTTopic);
		TsscTopic tsscTopic = restTemplate.postForObject(SERVER+"topics", req , TsscTopic.class);
		return tsscTopic;
	}

	@Override
	public void PUT_Topic(TsscTopic PUTTopic) {
		restTemplate.put(SERVER+"topics", PUTTopic, TsscTopic.class);				
	}

	@Override
	public void DELETE_Topic(TsscTopic DELETETopic) {
		restTemplate.delete(SERVER+"topics/"+DELETETopic.getId());
	}

}
