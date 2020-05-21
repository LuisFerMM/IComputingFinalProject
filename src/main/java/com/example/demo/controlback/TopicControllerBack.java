package com.example.demo.controlback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.servicios.TopicServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class TopicControllerBack {

	@Autowired
	private TopicServiceImp topicS;
	
	@GetMapping("/topics")
	public Iterable<TsscTopic> loadTopics (Model model) {
		return topicS.findAll();
	}

	@GetMapping("/topics/{id}")
	public TsscTopic showUpdateForm(@PathVariable("id") long id, Model model) {
		return topicS.getTopic(id);
	}
	
	@PostMapping("/topics")
	public void savetopic(@Validated({generalValidator.class}) TsscTopic topic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {			
			topicS.createTopic(topic);
	}
	
	@PutMapping("/topics")
	public String updatetopic(@Validated({generalValidator.class}) TsscTopic topic, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			return "topics/update-topic";
		}
			topicS.updateTopic(topic);
		}
		return "redirect:/topics/";
	}
	
	@DeleteMapping("/topics/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic topic = topicS.getTopic(id);
		topicS.deleteTopic(topic);
		return "redirect:/topics/";
	}
}
