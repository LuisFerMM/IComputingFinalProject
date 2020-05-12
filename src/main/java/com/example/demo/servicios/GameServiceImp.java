package com.example.demo.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManagerFactory;

import org.hibernate.loader.entity.EntityLoader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.repositorios.GameRepository;

@Service
public class GameServiceImp implements GameService {

	private GameRepository gameR;
	private TopicServiceImp topicS;
	
	@Autowired
	public GameServiceImp(GameRepository ga, TopicServiceImp tS) {
		gameR = ga;
		topicS = tS;
	}

	@Override
	public TsscGame createGame(TsscGame game) {
		if(game.getNSprints()>0 && game.getNGroups()>0) {
			if(game.getTsscStories() == null)
				game.setTsscStories(new ArrayList<>());
			return gameR.save(game);
		}
		return null;
	}

	@Override
	public TsscGame getGame(Long game) {
		Optional<TsscGame> alv = gameR.findById(game);
		if(!alv.isEmpty())
			return alv.get();
		return null;
	}

	@Override
	public TsscGame updateGame(TsscGame game)throws NoSuchElementException{
		try {
		TsscGame aeditar = gameR.findById(game.getId()).get();
		} catch (NoSuchElementException e) {
			throw e;
		}
		if(game.getNSprints()>0 && game.getNGroups()>0)
			return gameR.save(game);
		return null;
	}

	@Override
	public TsscGame createGameWithTopic(TsscGame game, Long idTopic) {
		TsscTopic t = topicS.getTopic(idTopic);
		if(t !=null) {
			if(game.getNSprints()>0 && game.getNGroups()>0) {
			game.setTsscTopic(t);
			if(game.getTsscStories() == null)
			game.setTsscStories(new ArrayList<>());
			return gameR.save(game);
			}
		}
		return null;
	}

	@Transactional
	@Override
	public TsscGame createGameWithTopic2(TsscGame game, Long idTopic) {
		TsscTopic t = topicS.getTopic(idTopic);
		if(t !=null && game.getNSprints()>0 && game.getNGroups()>0) {
			List <TsscStory> stories = t.getTsscStories();
			for (int i = 0; i < stories.size(); i++) {
				TsscStory story = new TsscStory();
				if(game.getTsscStories() == null)
					game.setTsscStories(new ArrayList<>());
				BeanUtils.copyProperties(stories.get(i), story);
				game.addTsscStory(story);
			}
				//aquí se supone que también se copian los cronómetros pero los Topic no tienen una lista de ellos
			game.setTsscTopic(t);
			return gameR.save(game);
		}
		return null;
	}

	@Override
	public Iterable<TsscGame> findAll() {
		return gameR.findAll();
	}
	
	@Override
	public void delete(TsscGame game) {
		gameR.delete(game);
	}

	public TopicServiceImp getTopicS() {
		return topicS;
	}

}
