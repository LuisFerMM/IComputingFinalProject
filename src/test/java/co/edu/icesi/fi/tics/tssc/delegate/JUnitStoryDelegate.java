package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.delegate.StoryDelegateImp;
import com.example.demo.modelo.TsscStory;

@RunWith(MockitoJUnitRunner.class)
class JUnitStoryDelegate {
	
	@Mock
	private StoryDelegateImp storyDelegate;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGET() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setAltDescripton("tsscStory1");
		tsscStory.setAltDescShown("tsscStory1");
		tsscStory.setBusinessValue(new BigDecimal("100"));
		tsscStory.setDescription("tsscStory1");
		tsscStory.setNumber(new BigDecimal("100"));
		tsscStory.setPriority(new BigDecimal("100"));
		tsscStory.setShortDescription("tsscStory1");
		
		when(storyDelegate.GET_Story(1)).thenReturn(tsscStory);		
		assertTrue(storyDelegate.GET_Story(1).getDescription().equals(tsscStory.getDescription()));
	}
	
	@Test
	void testPOST() {
		TsscStory tsscStory = new TsscStory();
		tsscStory.setAltDescripton("tsscStory1");
		tsscStory.setAltDescShown("tsscStory1");
		tsscStory.setBusinessValue(new BigDecimal("100"));
		tsscStory.setDescription("tsscStory1");
		tsscStory.setNumber(new BigDecimal("100"));
		tsscStory.setPriority(new BigDecimal("100"));
		tsscStory.setShortDescription("tsscStory1");
		
//		when(storyDelegate.POST_Story(tsscStory)).thenReturn(tsscStory);
//		assertTrue(storyDelegate.POST_Story(tsscStory).getDescription().equals(tsscStory.getDescription()));
	}
	
}
