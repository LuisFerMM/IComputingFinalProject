package com.example.demo.delegate;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

public class GameDelegateImp implements GameDelegate {

	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8081/backapi/";
	
	public GameDelegateImp() {
		restTemplate = new RestTemplate();
	}

	@Override
	public TsscGame GET_Game(long id) {
		TsscGame tsscAdmin = restTemplate.getForObject(SERVER+"games/"+id, TsscGame.class);
		return tsscAdmin;
	}
	
	@Override
	public Iterable<TsscGame> GET_Games() {
		TsscGame[] tsscGames = restTemplate.getForObject(SERVER+"games", TsscGame[].class);
		List<TsscGame> listGames = Arrays.asList(tsscGames);
		return listGames;
	}
	
	/*
	 *Good?
	 */
	@Override
	public TsscTopic GET_TopicGame(long idGame, long idTopic) {
		TsscTopic tsscTopic = restTemplate.getForObject(SERVER+"games/"+idGame+"/topics/"+idTopic, TsscTopic.class);
		return tsscTopic;
	}

	/*
	 *Good?
	 */
	@Override
	public TsscGame POST_GameWithTopic(TsscGame POSTGame, long idT) {
		TsscGame tsscAdmin = restTemplate.postForObject(SERVER+"games/"+POSTGame.getId()+"/topics/"+idT, POSTGame , TsscGame.class);
		return tsscAdmin;
	}

	/*
	 *Good?
	 */
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

	@Override
	public Iterable<TsscTopic> GET_TopicsGame() {
		TsscTopic[] tsscTopics = restTemplate.getForObject(SERVER+"topics", TsscTopic[].class);
		List<TsscTopic> lisTopics = Arrays.asList(tsscTopics);
		return lisTopics;
	}

}
