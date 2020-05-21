package com.example.demo.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscStory;

public class StoryDelegateImp implements StoryDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/backapi/";
	
	public StoryDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscStory GET_Story(long id) {
		TsscStory tsscAdmin = restTemplate.getForObject(SERVER+"stories/"+id, TsscStory.class);
		return tsscAdmin;
	}
	
	@Override
	public Iterable<TsscStory> GET_Stories() {
		TsscStory[] tsscStories = restTemplate.getForObject(SERVER+"stories", TsscStory[].class);
		List<TsscStory> lisStories = Arrays.asList(tsscStories);
		return lisStories;
	}
	
	@Autowired
	public Iterable<TsscStory> GET_GameStories(long id) {
		TsscStory[] tsscStories = restTemplate.getForObject(SERVER+"game/"+id+"/stories", TsscStory[].class);
		List<TsscStory> lisStories = Arrays.asList(tsscStories);
		return lisStories;
	}
	
	@Autowired
	public void DELETE_StoryGame(long idG, long idS ) {
		restTemplate.delete(SERVER+"game/"+idG+"/stories/"+idS);
	}

	@Override
	public TsscStory POST_Story(TsscStory POSTStory) {
		TsscStory tsscAdmin = restTemplate.postForObject(SERVER+"stories", POSTStory , TsscStory.class);
		return tsscAdmin;
	}

	@Override
	public void PUT_Story(TsscStory PUTStory) {
		restTemplate.put(SERVER+"stories/"+PUTStory.getId(), PUTStory, TsscStory.class);		
	}

	@Override
	public void DELETE_Story(TsscStory DELETEStory) {
		restTemplate.delete(SERVER+"stories/"+DELETEStory.getId());		
	}

}
