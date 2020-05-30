package com.example.demo.controlback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.TsscTopic;
import com.example.demo.servicios.TopicServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class TopicControllerBack {

	@Autowired
	private TopicServiceImp topicS;
	
	@GetMapping("/topics")
	public Iterable<TsscTopic> loadTopics () {
		return topicS.findAll();
	}

	@GetMapping("/topics/{id}")
	public TsscTopic showUpdateForm(@PathVariable("id") long id) {
		
		return topicS.getTopic(id);
	}
	
	@PostMapping("/topics")
	public ResponseEntity savetopic(@RequestBody TsscTopic topic) {
		
		return ResponseEntity.ok(topicS.createTopic(topic));
	}
	
	@PutMapping("/topics")
	public ResponseEntity updatetopic(@RequestBody TsscTopic topic) {
		
		return ResponseEntity.ok(topicS.updateTopic(topic));
	}
	
	@DeleteMapping("/topics/{id}")
	public void deleteTopic(@PathVariable("id") long id) {
		TsscTopic topic = topicS.getTopic(id);
		topicS.deleteTopic(topic);
	}
}
