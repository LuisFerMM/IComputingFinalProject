package com.example.demo.delegate;

import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

public class GameDelegateImp implements GameDelegate {

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/";
	
	public GameDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscGame GET_Game(int id) {
		TsscGame tsscAdmin = restTemplate.getForObject(SERVER+"games/"+id, TsscGame.class);
		return tsscAdmin;
	}

	@Override
	public TsscTopic GET_TopicGame(int idGame, int idTopic) {
		TsscTopic tsscTopic = restTemplate.getForObject(SERVER+"games/"+idGame+"/topics/"+idTopic, TsscTopic.class);
		return tsscTopic;
	}

	@Override
	public TsscGame POST_GameWithTopic(TsscGame POSTGame) {
		TsscGame tsscAdmin = restTemplate.postForObject(SERVER+"games", POSTGame , TsscGame.class);
		return tsscAdmin;
	}

	@Override
	public void PUT_TopicGame(TsscTopic PUTTopic, TsscGame PUTGame) {
		restTemplate.put(SERVER+"games/"+PUTGame.getId()+"/topics/"+PUTTopic.getId(), PUTTopic, TsscTopic.class);						
	}

	@Override
	public TsscGame POST_Game(TsscGame POSTGame) {
		TsscGame tsscAdmin = restTemplate.postForObject(SERVER+"games", POSTGame , TsscGame.class);
		return tsscAdmin;
	}

	@Override
	public void PUT_Game(TsscGame PUTGame) {
		restTemplate.put(SERVER+"games/"+PUTGame.getId(), PUTGame, TsscGame.class);				
	}

	@Override
	public void DELETE_Game(TsscGame DELETEGame) {
		restTemplate.delete(SERVER+"games/"+DELETEGame.getId());
		
	}

}
