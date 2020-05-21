package com.example.demo.servicios;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TsscStoryDao;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;

@Service
public class StoryServiceImp implements StoryService {

	private GameServiceImp gS;
	private TsscStoryDao storyR;

	@Autowired
	public StoryServiceImp(GameServiceImp gameS, TsscStoryDao sR) {
		gS = gameS;
		storyR = sR;
	}

	@Transactional
	@Override
	public TsscStory createStory(TsscStory story, Long game) {
		TsscGame g = gS.getGame(game);
		if (story.getBusinessValue() != null && story.getBusinessValue().compareTo(BigDecimal.ZERO) == 1
				&& story.getInitialSprint() != null && story.getInitialSprint().compareTo(BigDecimal.ZERO) == 1
				&& story.getPriority() != null && story.getPriority().compareTo(BigDecimal.ZERO) == 1 && g != null) {
			//g.addTsscStory(story);
			storyR.save(story);
			return storyR.findById(story.getId());
		}
		return null;
	}

	@Override
	@Transactional
	public TsscStory updateStory(TsscStory story) throws NoSuchElementException{
	
		TsscStory s = storyR.findById(story.getId());
		if(s != null) {
		if (story.getBusinessValue() != null && story.getBusinessValue().compareTo(BigDecimal.ZERO) == 1
				&& story.getInitialSprint() != null && story.getInitialSprint().compareTo(BigDecimal.ZERO) == 1
				&& story.getPriority() != null && story.getPriority().compareTo(BigDecimal.ZERO) == 1) {
			storyR.update(story);
			return storyR.findById(story.getId());
		} else return null;
		}
		throw new NoSuchElementException();
	}

	@Override
	public TsscStory getStory(Long id) {
		return storyR.findById(id);
		
	}

	@Override
	public void delete(TsscStory story) {
		storyR.delete(story);
	}

	@Override
	public Iterable<TsscStory> findAll() {
		return storyR.findAll();
	}

	public GameServiceImp getGameS() {
		return gS;
	}

	public Iterable<TsscStory> findByGameId(long id) {
		return storyR.findByGameId(id);
	}
}
