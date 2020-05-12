package com.example.demo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscGroup;
import com.example.demo.modelo.TsscSprint;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.repositorios.GameRepository;
import com.example.demo.repositorios.StoryRepository;
import com.example.demo.repositorios.TopicRepository;
import com.example.demo.servicios.GameServiceImp;
import com.example.demo.servicios.StoryServiceImp;
import com.example.demo.servicios.TopicServiceImp;

@RunWith(MockitoJUnitRunner.class)
class JUnitMockTests {

	@Nested
	class Game {
		@Mock
		private GameRepository gameR;

		@Mock
		private TopicServiceImp tS;

		@InjectMocks
		private GameServiceImp gameS;

		@BeforeEach
		void setup() {
			MockitoAnnotations.initMocks(this);
		}

		@DisplayName("Crear sin relaciones")
		@Test
		void saveGameTest() {
			TsscGame game = new TsscGame();
			game.setNSprints(2);
			game.setNGroups(2);
			when(gameR.save(game)).thenReturn(game);
			assertEquals(gameS.createGame(game), game);
		}

		@DisplayName("Crear con la cantidad de Sprints y Grupos en 0")
		@Test
		void saveGameTest1() {
			TsscGame game = new TsscGame();
			game.setNSprints(0);
			game.setNGroups(0);
			assertNull(gameS.createGame(game));
		}

		@DisplayName("Crear con Tema asociado (v1)")
		@Test
		void saveGameWithTopicTest() {
			TsscGame game = new TsscGame();
			game.setNSprints(10);
			game.setNGroups(10);
			TsscTopic t = new TsscTopic();
			when(gameR.save(game)).thenReturn(game);
			when(tS.getTopic(t.getId())).thenReturn(t);
			assertEquals(gameS.createGameWithTopic(game, t.getId()), game);
		}

		@DisplayName("Crear con el id del Tema no existente (v1)")
		@Test
		void saveGameWithTopicTest1() {
			TsscGame game = new TsscGame();
			game.setNSprints(2);
			game.setNGroups(2);
			when(tS.getTopic((long)0)).thenReturn(null);
			assertNull(gameS.createGameWithTopic(game, (long) 0));
		}
		
		@DisplayName("Crear asociado a un Tema (v2)")
		@Test
		void saveGameWithTopicTestv2() {
			TsscGame game = new TsscGame();
			TsscTopic t = new TsscTopic();
			game.setNSprints(2);
			game.setNGroups(2);
			t.setTsscStories(new ArrayList<>());
			game.setTsscStories(new ArrayList<>());
			TsscStory primera = new TsscStory();
			primera.setDescription("Simulación iniciada");
			t.addTsscStory(primera);
			when(tS.getTopic(t.getId())).thenReturn(t);
			when(gameR.save(game)).thenReturn(game);
			when(gameR.findById(game.getId())).thenReturn(Optional.of(game));
			List<TsscStory> storiesGame = gameS.getGame(game.getId()).getTsscStories();
			assertAll(
				()->	assertEquals(gameS.createGameWithTopic2(game, t.getId()), game),
				()->	assertEquals(storiesGame.size(),1),
				()->	assertNotEquals(storiesGame.get(0), primera),
				()->	assertEquals(storiesGame.get(0).getDescription(), primera.getDescription())
					);
			
		}
		
		@DisplayName("Crear asociado a un Tema sin Grupos(v2)")
		@Test
		void saveGameWithTopicTestv21() {
			TsscGame game = new TsscGame();
			TsscTopic t = new TsscTopic();
			game.setNSprints(2);
			game.setNGroups(0);
			t.setTsscStories(new ArrayList<>());
			game.setTsscStories(new ArrayList<>());
			TsscStory primera = new TsscStory();
			primera.setDescription("Simulación iniciada");
			t.addTsscStory(primera);
			when(tS.getTopic(t.getId())).thenReturn(t);
			when(gameR.save(game)).thenReturn(game);
			assertNull(gameS.createGameWithTopic2(game, t.getId()));
		}
		
		@DisplayName("Editar sin grupos")
		@Test
		void editGameTest() {
			TsscGame game = new TsscGame();
			game.setNSprints(2);
			game.setNGroups(0);
			when(gameR.findById(game.getId())).thenReturn(Optional.of(game));
			assertNull(gameS.updateGame(game));
		}
		
		@DisplayName("Editar con Id diferente al original")
		@Test
		void editGameTest1() {
			TsscGame game = new TsscGame();
			game.setNSprints(2);
			game.setNGroups(0);
			when(gameR.findById(game.getId())).thenReturn(Optional.empty());
			assertThrows(NoSuchElementException.class, () ->{
				gameS.updateGame(game);
			});
		}
		
		@DisplayName("Editar con Sprints y Grupos mayores que 0")
		@Test
		void editGameTest2() {
			TsscGame game = new TsscGame();
			game.setNSprints(2);
			game.setNGroups(3);
			when(gameR.save(game)).thenReturn(game);
			when(gameR.findById(game.getId())).thenReturn(Optional.of(game));
			assertEquals(gameS.updateGame(game), game);
		}
	}
	
	@Nested
	class Topic {
		@Mock
		private TopicRepository topicR;

		@InjectMocks
		private TopicServiceImp topicS;

		@BeforeEach
		public void setup() {
			MockitoAnnotations.initMocks(this);
		}
		
		@DisplayName("Crear con Grupos y Sprints mayores que 0")
		@Test
		public void createTopicTest() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultSprints(1);
			topic.setDefaultGroups(1);
			when(topicR.save(topic)).thenReturn(topic);
			assertEquals(topicS.createTopic(topic), topic);
		}

		@DisplayName("Crear con todo en 0's")
		@Test
		public void createTopicTest1() {
			TsscTopic topic = new TsscTopic();
			assertNull(topicS.createTopic(topic));
		}

		@DisplayName("Crear con Grupos mayor que 0 pero sin Sprints")
		@Test
		public void createTopicTest2() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			assertNull(topicS.createTopic(topic));
		}

		@DisplayName("Crear con Sprints mayor que 0 pero sin Grupos")
		@Test
		public void createTopicTest3() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultSprints(1);
			assertNull(topicS.createTopic(topic));
		}
		
		@DisplayName("Editar con Grupos y Sprints mayores que 0")
		@Test
		public void editTopicTest() {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultSprints(1);
			topic.setDefaultGroups(1);
			topic.setId(4);
			when(topicR.save(topic)).thenReturn(topic);
			when(topicR.findById((long) 4)).thenReturn(Optional.of(topic));
			assertEquals(topicS.updateTopic(topic), topic);
		}
		
		@DisplayName("Editar con Sprints mayor que 0 pero sin Grupos")
		@Test
		public void editTopicTest1 () {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultSprints(1);
			topic.setId(4);
			when(topicR.findById((long) 4)).thenReturn(Optional.of(topic));
			assertNull(topicS.updateTopic(topic));
		}
		
		@DisplayName("Editar con Grupos mayor que 0 pero sin Sprints")
		@Test
		public void editTopicTest2 () {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			topic.setId(4);
			when(topicR.findById((long) 4)).thenReturn(Optional.of(topic));
			assertNull(topicS.updateTopic(topic));
		}
		
		@DisplayName("Editar con id diferente al original")
		@Test
		public void editTopicTest3 () {
			TsscTopic topic = new TsscTopic();
			topic.setDefaultGroups(1);
			when(topicR.findById(topic.getId())).thenReturn(Optional.empty());
			assertThrows(NoSuchElementException.class, () ->{
				topicS.updateTopic(topic);
			});
		}
	}
	
	@Nested
	class Story {
		@Mock
		private StoryRepository storyR;

		@Mock
		private GameServiceImp gS;

		@InjectMocks
		private StoryServiceImp storyS;

		@BeforeEach
		void setup() {
			MockitoAnnotations.initMocks(this);
		}

		@DisplayName("Crear con el Sprint inicial, priodidad y valor del negocio en 0")
		@Test
		void saveStoryTest() {
			TsscStory story = new TsscStory();
			TsscGame game = new TsscGame();
			story.setInitialSprint(BigDecimal.ZERO);
			story.setBusinessValue(BigDecimal.ZERO);
			story.setPriority(BigDecimal.ZERO);
			when(gS.getGame(game.getId())).thenReturn(game);
			when(storyR.save(story)).thenReturn(story);
			assertNull(storyS.createStory(story, game.getId()));
		}

		@DisplayName("Crear con Sprint inicial en 0")
		@Test
		void saveStoryTest1() {
			TsscStory story = new TsscStory();
			TsscGame game = new TsscGame();
			story.setInitialSprint(BigDecimal.ZERO);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(gS.getGame(game.getId())).thenReturn(game);
			when(storyR.save(story)).thenReturn(story);
			assertNull(storyS.createStory(story, game.getId()));
		}

		@DisplayName("Crear con el id del Juego no existente")
		@Test
		void saveStoryTest2() {
			TsscStory story = new TsscStory();
			TsscGame game = new TsscGame();
			story.setInitialSprint(BigDecimal.TEN);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(gS.getGame(game.getId())).thenReturn(null);
			when(storyR.save(story)).thenReturn(story);
			assertNull(storyS.createStory(story, game.getId()));
		}
		
		@DisplayName("Crear correctamente")
		@Test
		void saveStoryTest3() {
			TsscStory story = new TsscStory();
			TsscGame game = new TsscGame();
			game.setTsscStories(new ArrayList<>());
			story.setInitialSprint(BigDecimal.TEN);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(gS.getGame(game.getId())).thenReturn(game);
			when(storyR.save(story)).thenReturn(story);
			assertEquals(storyS.createStory(story, game.getId()), story);
			assertEquals(story.getTsscGame(), game);
		}
		
		@DisplayName("Editar con Sprint inicial en 0")
		@Test
		void editGameTest() {
			TsscStory story = new TsscStory();
			story.setInitialSprint(BigDecimal.ZERO);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(storyR.save(story)).thenReturn(story);
			when(storyR.findById(story.getId())).thenReturn(Optional.of(story));
			assertNull(storyS.updateStory(story));
		}
		
		@DisplayName("Editar con Id diferente al original")
		@Test
		void editGameTest1() {
			TsscStory story = new TsscStory();
			story.setInitialSprint(BigDecimal.TEN);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(storyR.save(story)).thenReturn(story);
			when(storyR.findById(story.getId())).thenReturn(Optional.empty());
			assertThrows(NoSuchElementException.class, () ->{
				storyS.updateStory(story);
			});
		}
		
		@DisplayName("Editar exitoso")
		@Test
		void editGameTest2() {
			TsscStory story = new TsscStory();
			story.setInitialSprint(BigDecimal.TEN);
			story.setBusinessValue(BigDecimal.TEN);
			story.setPriority(BigDecimal.TEN);
			when(storyR.save(story)).thenReturn(story);
			when(storyR.findById(story.getId())).thenReturn(Optional.of(story));
			assertEquals(storyS.updateStory(story), story);
		}
	}
}
