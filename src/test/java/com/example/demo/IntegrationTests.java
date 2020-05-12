package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.servicios.GameServiceImp;
import com.example.demo.servicios.StoryServiceImp;
import com.example.demo.servicios.TopicServiceImp;

@SpringBootTest
public class IntegrationTests {

	private TopicServiceImp tS;

	private GameServiceImp gameS;
	
	private StoryServiceImp storyS;
	@Autowired
	public IntegrationTests(GameServiceImp gS, TopicServiceImp topicS, StoryServiceImp story) {
		gameS = gS;
		tS = topicS;
		storyS = story;
	}
	
	@DisplayName("Crear Juego sin relaciones")
	@Test
	void saveGameTest() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(2);
		assertEquals(gameS.createGame(game), game);
	}
	@DisplayName("Crear Juego con la cantidad de Sprints y Grupos en 0")
	@Test
	void saveGameTest1() {
		TsscGame game = new TsscGame();
		game.setNSprints(0);
		game.setNGroups(0);
		assertNull(gameS.createGame(game));
	}

	@DisplayName("Crear Juego con Tema asociado (v1)")
	@Test
	void saveGameWithTopicTest() {
		TsscGame game = new TsscGame();
		game.setNSprints(10);
		game.setNGroups(10);
		TsscTopic t = new TsscTopic();
		t.setDefaultSprints(10);
		t.setDefaultGroups(5);
		assertEquals(tS.createTopic(t), t);
		assertEquals(gameS.createGameWithTopic(game, t.getId()), game);
	}

	@DisplayName("Crear Juego con el id del Tema no existente (v1)")
	@Test
	void saveGameWithTopicTest1() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(2);
		assertNull(gameS.createGameWithTopic(game, (long) 0));
	}
	
	@DisplayName("Crear Juego asociado a un Tema (v2)")
	@Test
	@Transactional
	void saveGameWithTopicTestv2() {
		TsscGame game = new TsscGame();
		TsscTopic t = new TsscTopic();
		game.setNSprints(2);
		game.setNGroups(2);
		t.setTsscStories(new ArrayList<>());
		TsscStory primera = new TsscStory();
		primera.setDescription("Simulación iniciada");
		t.addTsscStory(primera);
		t.setDefaultSprints(20);
		t.setDefaultGroups(12);
		assertEquals(tS.createTopic(t),t);
		assertEquals(gameS.createGameWithTopic2(game, t.getId()), game);
		List<TsscStory> storiesGame = gameS.getGame(game.getId()).getTsscStories();
		assertAll(
			()->	assertEquals(storiesGame.size(),1),
			()->	assertNotEquals(storiesGame.get(0), primera),
			()->	assertEquals(storiesGame.get(0).getDescription(), primera.getDescription())
				);
		
	}
	
	@DisplayName("Crear Juego asociado a un Tema sin Grupos(v2)")
	@Test
	void saveGameWithTopicTestv21() {
		TsscGame game = new TsscGame();
		TsscTopic t = new TsscTopic();
		game.setNSprints(2);
		game.setNGroups(0);
		t.setTsscStories(new ArrayList<>());
		TsscStory primera = new TsscStory();
		primera.setDescription("Simulación iniciada");
		t.addTsscStory(primera);
		assertNull(gameS.createGameWithTopic2(game, t.getId()));
	}
	
	@DisplayName("Editar Juego sin grupos")
	@Test
	void editGameTest() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(2);
		gameS.createGame(game);
		TsscGame aEditar = gameS.getGame(game.getId());
		aEditar.setNGroups(0);
		assertNull(gameS.updateGame(aEditar));
	}
	
	@DisplayName("Editar Juego con Id diferente al original")
	@Test
	void editGameTest1() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(3);
		gameS.createGame(game);
		TsscGame gg = new TsscGame();
		assertThrows(NoSuchElementException.class, () ->{
			gameS.updateGame(gg);
		});
	}
	
	@DisplayName("Editar Juego con Sprints y Grupos mayores que 0")
	@Test
	void editGameTest2() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(3);
		assertEquals(gameS.createGame(game),game);
		TsscGame aEditar = gameS.getGame(game.getId());
		assertEquals(aEditar.getNGroups(),3);
		assertEquals(aEditar.getNSprints(),2);
		aEditar.setNGroups(9);
		aEditar.setNSprints(15);
		gameS.updateGame(aEditar);
		TsscGame actualizado = gameS.getGame(game.getId());
		assertEquals(actualizado.getNGroups(),9);
		assertEquals(actualizado.getNSprints(),15);
		
	}
	
	@DisplayName("Crear Tema con Grupos y Sprints mayores que 0")
	@Test
	public void createTopicTest() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(1);
		assertEquals(tS.createTopic(topic), topic);
	}

	@DisplayName("Crear Tema con todo en 0's")
	@Test
	public void createTopicTest1() {
		TsscTopic topic = new TsscTopic();
		assertNull(tS.createTopic(topic));
	}

	@DisplayName("Crear Tema con Grupos mayor que 0 pero sin Sprints")
	@Test
	public void createTopicTest2() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		assertNull(tS.createTopic(topic));
	}

	@DisplayName("Crear Tema con Sprints mayor que 0 pero sin Grupos")
	@Test
	public void createTopicTest3() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		assertNull(tS.createTopic(topic));
	}
	
	@DisplayName("Editar Tema con Grupos y Sprints mayores que 0")
	@Test
	public void editTopicTest() {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(15);
		assertEquals(tS.createTopic(topic),topic);
		TsscTopic aEditar = tS.getTopic(topic.getId());
		assertEquals(aEditar.getDefaultGroups(),15);
		assertEquals(aEditar.getDefaultSprints(),1);
		aEditar.setDefaultSprints(10);
		tS.updateTopic(aEditar);
		TsscTopic actualizado = tS.getTopic(topic.getId());
		assertEquals(actualizado.getDefaultGroups(),15);
		assertEquals(actualizado.getDefaultSprints(),10);
	}
	
	@DisplayName("Editar Tema con Sprints mayor que 0 pero sin Grupos")
	@Test
	public void editTopicTest1 () {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultSprints(1);
		topic.setDefaultGroups(15);
		tS.createTopic(topic);
		topic = tS.getTopic(topic.getId());
		topic.setDefaultGroups(0);
		assertNull(tS.updateTopic(topic));
	}
	
	@DisplayName("Editar Tema con Grupos mayor que 0 pero sin Sprints")
	@Test
	public void editTopicTest2 () {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(20);
		tS.createTopic(topic);
		topic = tS.getTopic(topic.getId());
		topic.setDefaultSprints(0);
		assertNull(tS.updateTopic(topic));
	}
	
	@DisplayName("Editar Tema con id diferente al original")
	@Test
	public void editTopicTest3 () {
		TsscTopic topic = new TsscTopic();
		topic.setDefaultGroups(1);
		topic.setDefaultSprints(20);
		tS.createTopic(topic);
		TsscTopic tt = new TsscTopic();
		assertThrows(NoSuchElementException.class, () ->{
			tS.updateTopic(tt);
		});
	}
	
	@DisplayName("Crear Historia con el Sprint inicial, priodidad y valor del negocio en 0")
	@Test
	void saveStoryTest() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		story.setInitialSprint(BigDecimal.ZERO);
		story.setBusinessValue(BigDecimal.ZERO);
		story.setPriority(BigDecimal.ZERO);
		assertNull(storyS.createStory(story, game.getId()));
	}

	@DisplayName("Crear Historia con Sprint inicial en 0")
	@Test
	void saveStoryTest1() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		story.setInitialSprint(BigDecimal.ZERO);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		assertNull(storyS.createStory(story, game.getId()));
	}

	@DisplayName("Crear Historia con el id del Juego no existente")
	@Test
	void saveStoryTest2() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		assertNull(storyS.createStory(story, game.getId()));
	}
	
	@DisplayName("Crear Historia correctamente")
	@Test
	void saveStoryTest3() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(3);
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.ONE);
		story.setPriority(BigDecimal.TEN);
		gameS.createGame(game);
		assertEquals(storyS.createStory(story, game.getId()), story);
		TsscStory delRepo = storyS.getStory(story.getId());
		assertEquals(delRepo.getInitialSprint().intValue(),BigDecimal.TEN.intValue());
	}
	
	@DisplayName("Editar Historia con Sprint inicial en 0")
	@Test
	void editStoryTest() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(2);
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		gameS.createGame(game);
		assertEquals(storyS.createStory(story, game.getId()), story);
		TsscStory aEditar = storyS.getStory(story.getId());
		assertAll(
		()-> assertEquals(aEditar.getInitialSprint().intValue(), BigDecimal.TEN.intValue()),
		()-> assertEquals(aEditar.getBusinessValue().intValue(),BigDecimal.TEN.intValue()),
		()-> assertEquals(aEditar.getPriority().intValue(),BigDecimal.TEN.intValue())
		);
		aEditar.setInitialSprint(BigDecimal.ZERO);
		assertEquals(aEditar.getInitialSprint().intValue(), BigDecimal.ZERO.intValue());
		storyS.updateStory(aEditar);
		TsscStory noModificado = storyS.getStory(story.getId());
		assertEquals(noModificado.getInitialSprint().intValue(), BigDecimal.TEN.intValue());
	}
	
	@DisplayName("Editar Historia con Id diferente al original")
	@Test
	void editStoryTest1() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		gameS.createGame(game);
		storyS.createStory(story, game.getId());
		TsscStory otro = new TsscStory();
		assertThrows(NoSuchElementException.class, () ->{
			storyS.updateStory(otro);
		});
	}
	
	@DisplayName("Editar Historia exitoso")
	@Test
	void editStoryTest2() {
		TsscStory story = new TsscStory();
		TsscGame game = new TsscGame();
		game.setNSprints(1);
		game.setNGroups(2);
		story.setInitialSprint(BigDecimal.TEN);
		story.setBusinessValue(BigDecimal.TEN);
		story.setPriority(BigDecimal.TEN);
		gameS.createGame(game);
		assertEquals(storyS.createStory(story, game.getId()), story);
		TsscStory aEditar = storyS.getStory(story.getId());
		assertAll(
				()-> assertEquals(aEditar.getInitialSprint().intValue(), BigDecimal.TEN.intValue()),
				()-> assertEquals(aEditar.getBusinessValue().intValue(),BigDecimal.TEN.intValue()),
				()-> assertEquals(aEditar.getPriority().intValue(),BigDecimal.TEN.intValue()));
		aEditar.setInitialSprint(BigDecimal.ONE);
		storyS.updateStory(aEditar);
		TsscStory actualizado = storyS.getStory(story.getId());
		assertAll(
				()-> assertEquals(actualizado.getInitialSprint().intValue(),BigDecimal.ONE.intValue()),
				()-> assertEquals(actualizado.getBusinessValue().intValue(),BigDecimal.TEN.intValue()),
				()-> assertEquals(actualizado.getPriority().intValue(),BigDecimal.TEN.intValue()));
	}
}
