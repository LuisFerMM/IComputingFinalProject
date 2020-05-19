package com.example.demo.delegate;

import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscStory;

public class StoryDelegateImp implements StoryDelegate{

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/";
	
	public StoryDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscStory GET_Story(int id) {
		TsscStory tsscAdmin = restTemplate.getForObject(SERVER+"stories/"+id, TsscStory.class);
		return tsscAdmin;
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
