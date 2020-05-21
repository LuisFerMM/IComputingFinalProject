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

import com.example.demo.delegate.StoryDelegateImp;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.modelo.TsscStory;

@Controller
public class StoryController {

	@Autowired
	private StoryDelegateImp storyDelegate;
	
	@GetMapping("/games/{id}/stories")
	public String showStories(@PathVariable("id") long id, Model model) {
		model.addAttribute("stories", storyDelegate.GET_GameStories(id));
		return "games/stories/index";
	}
	
	@GetMapping("/games/{id}/stories/add")
	public String addstoryPage(@PathVariable("id") long id, Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		return "games/stories/add-story";
	}
	
	@PostMapping("/games/{id}/stories/add")
	public String savestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, @PathVariable("id") long id, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscStory", story);
				return "games/stories/add-story";
			}
			storyDelegate.POST_Story(story);			
		}
		return "redirect:/games/"+id+"/stories";
	}
	
	@GetMapping("/games/{idG}/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG, Model model) {
		TsscStory story = storyDelegate.GET_Story(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscStory", story);
		return "games/stories/update-story";
	}

	@PostMapping("/games/{idG}/stories/edit")
	public String updatestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @PathVariable("idG") long idG,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscStory", story);
			return "games/stories/update-story";
		}
			storyDelegate.PUT_Story(story);
		}
		return "redirect:/games/"+ idG +"/stories";
	}
	
	@GetMapping("/games/{idG}/stories/del/{id}")
	public String deletestory(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		storyDelegate.DELETE_StoryGame(idG, id);
		return "redirect:/games/"+idG+"/stories";
	}
}
