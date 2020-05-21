package co.edu.icesi.fi.tics.tssc.delegate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.delegate.TopicDelegateImp;

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
		fail("Not yet implemented");
	}
	
	@Test
	void testPOST() {
		fail("Not yet implemented");
	}
	
	@Test
	void testPUT() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDELETE() {
		fail("Not yet implemented");
	}

}
