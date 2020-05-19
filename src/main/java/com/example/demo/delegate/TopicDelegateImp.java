package com.example.demo.delegate;

import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscTopic;

public class TopicDelegateImp implements TopicDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/";
	
	public TopicDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscTopic GET_Topic(int id) {
		TsscTopic tsscTopic = restTemplate.getForObject(SERVER+"topics/"+id, TsscTopic.class);
		return tsscTopic;
	}

	@Override
	public TsscTopic POST_Topic(TsscTopic POSTTopic) {
		TsscTopic tsscTopic = restTemplate.postForObject(SERVER+"topics", POSTTopic , TsscTopic.class);
		return tsscTopic;
	}

	@Override
	public void PUT_Topic(TsscTopic PUTTopic) {
		restTemplate.put(SERVER+"topics/"+PUTTopic.getId(), PUTTopic, TsscTopic.class);				
	}

	@Override
	public void DELETE_Topic(TsscTopic DELETETopic) {
		restTemplate.delete(SERVER+"topics/"+DELETETopic.getId());
	}

}
