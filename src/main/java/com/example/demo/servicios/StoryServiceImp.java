package com.example.demo.servicios;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.repositorios.GameRepository;
import com.example.demo.repositorios.StoryRepository;

@Service
public class StoryServiceImp implements StoryService {

	private GameServiceImp gS;
	private StoryRepository storyR;

	@Autowired
	public StoryServiceImp(GameServiceImp gameS, StoryRepository sR) {
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
			g.addTsscStory(story);
			return storyR.save(story);
		}
		return null;
	}

	@Override
	public TsscStory updateStory(TsscStory story) throws NoSuchElementException{
		try {
		TsscStory s = storyR.findById(story.getId()).get();
		} catch (NoSuchElementException e) {
			throw e;
		}
		if (story.getBusinessValue() != null && story.getBusinessValue().compareTo(BigDecimal.ZERO) == 1
				&& story.getInitialSprint() != null && story.getInitialSprint().compareTo(BigDecimal.ZERO) == 1
				&& story.getPriority() != null && story.getPriority().compareTo(BigDecimal.ZERO) == 1) {
			return storyR.save(story);
		}
		return null;
	}

	@Override
	public TsscStory getStory(Long id) {
		Optional<TsscStory> alv = storyR.findById(id);
		if(!alv.isEmpty())
			return alv.get();
		return null;
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
}
