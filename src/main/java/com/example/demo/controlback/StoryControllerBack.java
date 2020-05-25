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
import com.example.demo.modelo.TsscStory;
import com.example.demo.servicios.StoryServiceImp;

import lombok.extern.java.Log;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class StoryControllerBack {
	
	@Autowired
	private StoryServiceImp storyS;
	
	@GetMapping("/games/{id}/stories")
	public Iterable<TsscStory> showStories(@PathVariable("id") long id) {
		return storyS.findByGameId(id);
	}
	
	@PostMapping("/games/{id}/stories")
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
	
	@GetMapping("/stories/{id}")
	public TsscStory showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		return storyS.getStory(id);
	}

	@PutMapping("/games/{idG}/stories")
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
	
	@DeleteMapping("/games/{idG}/stories/{id}")
	public String deletestory(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		TsscStory story = storyS.getGameS().getGame(idG).removeTsscStory(storyS.getStory(id));
		storyS.delete(story);
		return "redirect:/games/"+idG+"/stories";
	}
}
