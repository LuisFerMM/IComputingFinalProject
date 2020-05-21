package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.delegate.GameDelegateImp;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
class JUnitGameDelegate {
	
	@Mock
	private GameDelegateImp gameDelegate;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGET() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame1");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		when(gameDelegate.GET_Game(1)).thenReturn(tsscGame);		
		assertTrue(gameDelegate.GET_Game(1).getName().equals(tsscGame.getName()));
	}
	
	@Test
	void testPOST() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame1");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		when(gameDelegate.POST_Game(tsscGame)).thenReturn(tsscGame);
		assertTrue(gameDelegate.POST_Game(tsscGame).getName().equals(tsscGame.getName()));
	}
	
	@Test
	void testPOSTWithTopic() {
		TsscGame tsscGame = new TsscGame();
		tsscGame.setName("tsscGame1");
		tsscGame.setNGroups(10);
		tsscGame.setNSprints(10);
		
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic1");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("TesscTopic1");
		tsscTopic.setGroupPrefix("TT1");

		when(gameDelegate.POST_GameWithTopic(tsscGame, tsscTopic.getId())).thenReturn(tsscGame);
		assertTrue(gameDelegate.POST_GameWithTopic(tsscGame, tsscTopic.getId()).getName().equals(tsscGame.getName()));
	}
	
	
}
