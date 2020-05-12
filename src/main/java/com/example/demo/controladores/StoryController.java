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
import com.example.demo.modelo.TsscStory;
import com.example.demo.servicios.StoryServiceImp;

import lombok.extern.java.Log;

@Log
@Controller
public class StoryController {
	
	@Autowired
	private StoryServiceImp storyS;
	
	@GetMapping("/games/{id}/stories")
	public String showStories(@PathVariable("id") long id, Model model) {
		TsscGame game = storyS.getGameS().getGame(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("game", game);
		model.addAttribute("stories", game.getTsscStories());
		return "games/stories/index";
	}
	
	@GetMapping("/games/{id}/stories/add")
	public String addstoryPage(@PathVariable("id") long id, Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("game", storyS.getGameS().getGame(id));
		return "games/stories/add-story";
	}
	
	@PostMapping("/games/{id}/stories/add")
	public String savestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, @PathVariable("id") long id, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscStory", story);
				model.addAttribute("game", storyS.getGameS().getGame(id));
				return "games/stories/add-story";
			}
			storyS.createStory(story, id);
		}
		return "redirect:/games/"+id+"/stories";
	}
	
	@GetMapping("/games/{idG}/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG, Model model) {
		TsscStory story = storyS.getStory(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscStory", story);
		model.addAttribute("game", storyS.getGameS().getGame(idG));
		return "games/stories/update-story";
	}

	@PostMapping("/games/{idG}/stories/edit")
	public String updatestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @PathVariable("idG") long idG,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscStory", story);
			m.addAttribute("game", storyS.getGameS().getGame(idG));
			return "games/stories/update-story";
		}
			storyS.updateStory(story);
		}
		return "redirect:/games/"+ idG +"/stories";
	}
	
	@GetMapping("/games/{idG}/stories/del/{id}")
	public String deletestory(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		TsscStory story = storyS.getGameS().getGame(idG).removeTsscStory(storyS.getStory(id));
		storyS.delete(story);
		return "redirect:/games/"+idG+"/stories";
	}
}
