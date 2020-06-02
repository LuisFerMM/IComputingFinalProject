package com.example.demo.controlfront;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.delegate.GameDelegateImp;
import com.example.demo.delegate.StoryDelegateImp;
import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.modelo.TsscStory;

@CrossOrigin
@Controller
@RequestMapping("/frontapi")
public class StoryController {

	@Autowired
	private StoryDelegateImp storyDelegate;
	@Autowired
	private GameDelegateImp gameDelegate;
	
	@GetMapping("/games/{id}/stories")
	public String showGameStories(@PathVariable("id") long id, Model model) {
		model.addAttribute("stories", storyDelegate.GET_GameStories(id));
		TsscGame game = gameDelegate.GET_Game(id);
		model.addAttribute("parentName", game.getName());
		model.addAttribute("parent", "games");
		model.addAttribute("parentId", id);
		return "games/stories/index";
	}
	
	@GetMapping("/stories")
	public String showStories(Model model) {
		model.addAttribute("stories", storyDelegate.GET_Stories());
		return "games/stories/index";
	}
	
	@GetMapping("/games/{idG}/stories/add")
	public String addstoryPage(@PathVariable("idG") long idG, Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("tsscGame", gameDelegate.GET_Game(idG));
		return "games/stories/add-story";
	}
	
	@GetMapping("/stories/add")
	public String addstoryPage(Model model) {
		model.addAttribute("tsscStory", new TsscStory());
		model.addAttribute("games", gameDelegate.GET_Games());
		return "games/stories/add-story";
	}
	
	@PostMapping("/games/{idG}/stories/add")
	public String savestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @PathVariable("idG") long idG, @RequestParam(value = "action", required = true) String action, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscStory", story);
				model.addAttribute("tsscGame", gameDelegate.GET_Game(idG));
				return "games/stories/add-story";
			}
			storyDelegate.POST_Story(story, idG);			
		}
		return "redirect:/frontapi/games/"+idG+"/stories";
	}
	
	@PostMapping("/stories/add")
	public String savestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscStory", story);
				model.addAttribute("games", gameDelegate.GET_Games());
				return "games/stories/add-story";
			}
			long idG = story.getTsscGame().getId();
			storyDelegate.POST_Story(story, idG);			
		}
		return "redirect:/frontapi/stories";
	}
	
	@GetMapping("/stories/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscStory story = storyDelegate.GET_Story(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscStory", story);
		return "games/stories/update-story";
	}
	
	@PostMapping("/stories/edit")
	public String updatestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if(!action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscStory", story);
			return "games/stories/update-story";
		}
			storyDelegate.PUT_Story(story);
		}
		return "redirect:/frontapi/stories";
	}
	
	@GetMapping("/games/{idG}/stories/edit/{id}")
	public String showUpdateFormGame(@PathVariable("id") long id, @PathVariable("idG") long idG, Model model) {
		TsscStory story = storyDelegate.GET_Story(id);
		if (story == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscStory", story);
		model.addAttribute("parentId", gameDelegate.GET_Game(idG).getId());
		model.addAttribute("parent", "games");
		return "games/stories/update-story";
	}

	@PostMapping("/games/{idG}/stories/edit")
	public String updatestory(@Validated({generalValidator.class}) TsscStory story, BindingResult bindingResult, @PathVariable("idG") long idG,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if(!action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscStory", story);
			return "games/stories/update-story";
		}
			storyDelegate.PUT_Story(story);
		}
		return "redirect:/frontapi/games/"+ idG +"/stories";
	}
	
	@GetMapping("/games/{idG}/stories/del/{id}")
	public String deletestoryGame(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		storyDelegate.DELETE_Story(id);
		return "redirect:/frontapi/games/"+idG+"/stories";
	}
	
	@GetMapping("/stories/del/{id}")
	public String deletestoryGame(@PathVariable("id") long id) {
		storyDelegate.DELETE_Story(id);
		return "redirect:/frontapi/stories";
	}
}