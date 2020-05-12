package com.example.demo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.servicios.TopicServiceImp;

@Controller
public class TopicController {

	@Autowired
	private TopicServiceImp topicS;
	
	@GetMapping("/topics/")
	public String loadTopics (Model model) {
		if(topicS.findAll().iterator().hasNext())
			model.addAttribute("topics", topicS.findAll());
		return "topics/index";
	}
	
	@GetMapping("/topics/add")
	public String addtopicPage(Model model) {
		model.addAttribute("tsscTopic", new TsscTopic());
		return "topics/add-topic";
	}
	
	@PostMapping("/topics/add")
	public String savetopic(@Validated({generalValidator.class}) TsscTopic topic, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscTopic", topic);
				return "topics/add-topic";
			}
			topicS.createTopic(topic);
		}
		return "redirect:/topics/";
	}
	
	@GetMapping("/topics/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscTopic topic = topicS.getTopic(id);
		if (topic == null)
			throw new IllegalArgumentException("Invalid topic Id:" + id);
		model.addAttribute("tsscTopic", topic);
		return "topics/update-topic";
	}

	@PostMapping("/topics/edit")
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
	
	@GetMapping("/topics/del/{id}")
	public String deleteTopic(@PathVariable("id") long id) {
		TsscTopic topic = topicS.getTopic(id);
		topicS.deleteTopic(topic);
		return "redirect:/topics/";
	}
}
