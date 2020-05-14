package com.example.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.TsscGameDao;
import com.example.demo.dao.TsscStoryDao;
import com.example.demo.dao.TsscTimecontrolDao;
import com.example.demo.dao.TsscTopicDao;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscStory;
import com.example.demo.modelo.TsscTimecontrol;
import com.example.demo.modelo.TsscTopic;

@SpringBootTest
public class DaoTests {

	@Autowired
	private TsscGameDao gameDao;
	@Autowired
	private TsscTopicDao topicDao;
	@Autowired
	private TsscTimecontrolDao timeCDao;
	@Autowired
	private TsscStoryDao storyDao;
	
	@DisplayName("Create game")
	@Test
	@Transactional
	void saveGameTest() {
		TsscGame game = new TsscGame();
		game.setNSprints(2);
		game.setNGroups(2);
		gameDao.save(game);
		TsscGame result = gameDao.findById(game.getId());
		assertNotNull(result);
		assertEquals(result.getNGroups(),2);
	}
	
	@DisplayName("Create topic")
	@Test
	@Transactional
	void saveTopicTest() {
		TsscTopic topic = new TsscTopic();
		topicDao.save(topic);
		assertNotNull(topicDao.findById(topic.getId()));
	}
	
	@DisplayName("find Game By Topic Id")
	@Test
	@Transactional
	void findGameByTopicId() {
		TsscGame game = new TsscGame();
		TsscTopic t = new TsscTopic();
		topicDao.save(t);
		game.setTsscTopic(t);
		gameDao.save(game);
		assertEquals(1, gameDao.findByTopicId(t.getId()).size());
		assertEquals(true,gameDao.findByTopicId(t.getId()+1).isEmpty());
	}
	
	@DisplayName("find Game By Topic Name")
	@Test
	@Transactional
	void findGameByTopicName() {
		TsscGame game = new TsscGame();
		TsscTopic t = new TsscTopic();
		t.setName("trolazo");
		topicDao.save(t);
		game.setTsscTopic(t);
		gameDao.save(game);
		assertEquals(1, gameDao.findByTopicName(t.getName()).size());
		assertEquals(true,gameDao.findByTopicName(t.getName()+"ahre").isEmpty());
	}
	
	@DisplayName("find Game By Topic Description")
	@Test
	@Transactional
	void findGameByTopicDescription() {
		TsscGame game = new TsscGame();
		TsscTopic t = new TsscTopic();
		t.setDescription("lala");
		topicDao.save(t);
		game.setTsscTopic(t);
		gameDao.save(game);
		assertEquals(1, gameDao.findByTopicDescription(t.getDescription()).size());
		assertEquals(true,gameDao.findByTopicDescription(t.getDescription()+"ahre").isEmpty());
	}
	
	@DisplayName("find Game By Date Range")
	@Test
	@Transactional
	void findGameByDateRange() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		TsscGame game1 = new TsscGame();
		game1.setScheduledDate(LocalDate.parse("01-03-2020", f));
		gameDao.save(game1);
		TsscGame game2 = new TsscGame();
		game2.setScheduledDate(LocalDate.parse("01-18-2020", f));
		gameDao.save(game2);
		TsscGame game3 = new TsscGame();
		game3.setScheduledDate(LocalDate.parse("01-22-2020", f));
		gameDao.save(game3);
		assertEquals(2, gameDao.findByDateRange(LocalDate.parse("01-15-2020", f), LocalDate.parse("01-30-2020", f)).size());
	}
	
	@DisplayName("find Game By Date but Time Range")
	@Test
	@Transactional
	void findGameByDateAndTimeRange() {
		DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_TIME;
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		
		TsscGame game1 = new TsscGame();
		game1.setScheduledTime(LocalTime.parse("13:30:00", f));
		game1.setScheduledDate(LocalDate.parse("01-15-2020", f1));
		gameDao.save(game1);
		TsscGame game2 = new TsscGame();
		game2.setScheduledTime(LocalTime.parse("22:45:00", f));
		game2.setScheduledDate(LocalDate.parse("01-15-2020", f1));
		gameDao.save(game2);
		TsscGame game3 = new TsscGame();
		game3.setScheduledTime(LocalTime.parse("11:21:00", f));
		game3.setScheduledDate(LocalDate.parse("01-03-2020", f1));
		gameDao.save(game3);
		assertEquals(1, gameDao.findByDateAndTimeRange(LocalDate.parse("01-15-2020", f1), LocalTime.parse("10:30:00", f), LocalTime.parse("15:00:00", f)).size());
	}
	
	@DisplayName("find Game By Date with less 10 stories and without timecontrols")
	@Test
	@Transactional
	void findGameByDateAndStrangeRestrictions() {
		DateTimeFormatter f1 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		TsscGame game1 = new TsscGame();
		game1.setName("primero");
		game1.setTsscStories(new ArrayList<TsscStory>());
		for (int i = 0; i < 10; i++) {
			TsscStory s =  new TsscStory();
			storyDao.save(s);
			game1.addTsscStory(s);
		}
		TsscTimecontrol timeC = new TsscTimecontrol();
		timeCDao.save(timeC);
		game1.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game1.addTsscTimecontrol(timeC);
		game1.setScheduledDate(LocalDate.parse("01-15-2020", f1));
		gameDao.save(game1);
		
		TsscGame game2 = new TsscGame();
		game2.setName("segundo");
		game2.setScheduledDate(LocalDate.parse("01-15-2020", f1));
		game2.setTsscStories(new ArrayList<TsscStory>());
		for (int i = 0; i < 10; i++) {
			TsscStory s =  new TsscStory();
			storyDao.save(s);
			game2.addTsscStory(s);
		}
		gameDao.save(game2);
		
		TsscGame game3 = new TsscGame();
		game3.setName("tercero");
		game3.setScheduledDate(LocalDate.parse("01-03-2020", f1));
		gameDao.save(game3);
		
		TsscGame game4 = new TsscGame();
		game4.setName("cuarto");
		TsscTimecontrol timeC2 = new TsscTimecontrol();
		timeCDao.save(timeC2);
		game4.setTsscTimecontrol(new ArrayList<TsscTimecontrol>());
		game4.addTsscTimecontrol(timeC2);
		game4.setScheduledDate(LocalDate.parse("01-15-2020", f1));
		gameDao.save(game4);
		
		assertEquals(2, gameDao.findByDateAndStrangeRestrictions(LocalDate.parse("01-15-2020", f1)).size());
	}
	
	@DisplayName("find Topic By Description")
	@Test
	@Transactional
	void findTopicByDescription() {
		TsscTopic t = new TsscTopic();
		t.setDescription("lala");
		topicDao.save(t);
		assertEquals(1, topicDao.findByDescription(t.getDescription()).size());
		assertEquals(true,topicDao.findByDescription(t.getDescription()+"ahre").isEmpty());
	}
	
	@DisplayName("find Topic By Name")
	@Test
	@Transactional
	void findTopicByName() {
		TsscTopic t = new TsscTopic();
		t.setName("lala");
		topicDao.save(t);
		assertEquals(1, topicDao.findByName(t.getName()).size());
		assertEquals(true,topicDao.findByName(t.getName()+"ahre").isEmpty());
	}

	@DisplayName("find Topic By Date and Order by Time")
	@Test
	@Transactional
	void findTopicCustomByDate() {
		DateTimeFormatter f = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		DateTimeFormatter f1 = DateTimeFormatter.ISO_LOCAL_TIME;
		TsscTopic t = new TsscTopic();
		TsscTopic t2 = new TsscTopic();
		TsscTopic t3 = new TsscTopic();
		t.setName("topic1");
		t2.setName("topic2");
		t3.setName("topic3");
		topicDao.save(t);
		topicDao.save(t2);
		topicDao.save(t3);
		
		TsscGame game1 = new TsscGame();
		game1.setTsscTopic(t);
		game1.setScheduledDate(LocalDate.parse("12-06-2020", f));
		game1.setScheduledTime(LocalTime.parse("12:45:00", f1));
		gameDao.save(game1);
		TsscGame game2 = new TsscGame();
		game2.setTsscTopic(t2);
		game2.setScheduledDate(LocalDate.parse("12-06-2020", f));
		game2.setScheduledTime(LocalTime.parse("22:45:00", f1));
		gameDao.save(game2);
		TsscGame game3 = new TsscGame();
		game3.setTsscTopic(t2);
		game3.setScheduledDate(LocalDate.parse("12-06-2020", f));
		game3.setScheduledTime(LocalTime.parse("10:45:00", f1));
		gameDao.save(game3);
		TsscGame game4 = new TsscGame();
		game4.setTsscTopic(t3);
		game4.setScheduledDate(LocalDate.parse("12-10-2020", f));
		game4.setScheduledTime(LocalTime.parse("16:45:00", f1));
		gameDao.save(game4);
		List <TsscTopic> result = topicDao.findByGameSchedule(LocalDate.parse("12-06-2020",f));
		assertEquals(3, result.size());
		assertEquals(t2.getName(),result.get(0).getName());
		assertEquals(t.getName(),result.get(1).getName());
		assertEquals(t2.getName(),result.get(2).getName());
	}
}
