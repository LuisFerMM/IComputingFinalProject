package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.delegate.TopicDelegateImp;
import com.example.demo.modelo.TsscTopic;

@RunWith(MockitoJUnitRunner.class)
class JUnitTopicDelegate {
	
	@Mock
	private TopicDelegateImp topicDelegate;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testGET() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic1");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("TesscTopic1");
		tsscTopic.setGroupPrefix("TT1");
		
		when(topicDelegate.GET_Topic(1)).thenReturn(tsscTopic);		
		assertTrue(topicDelegate.GET_Topic(1).getName().equals(tsscTopic.getName()));
	}
	
	@Test
	void testPOST() {
		TsscTopic tsscTopic = new TsscTopic();
		tsscTopic.setName("TsscTopic1");
		tsscTopic.setDefaultGroups(10);
		tsscTopic.setDefaultSprints(10);
		tsscTopic.setDescription("TesscTopic1");
		tsscTopic.setGroupPrefix("TT1");
		
		when(topicDelegate.POST_Topic(tsscTopic)).thenReturn(tsscTopic);
		assertTrue(topicDelegate.POST_Topic(tsscTopic).getName().equals(tsscTopic.getName()));
	}

}
