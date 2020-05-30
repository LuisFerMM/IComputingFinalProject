package com.example.demo.controlback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity savestory(@RequestBody TsscStory story, @PathVariable("id") long id) {			

		return ResponseEntity.ok(storyS.createStory(story, id));
	}
	
	@GetMapping("/stories/{id}")
	public TsscStory showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		return storyS.getStory(id);
	}

	@PutMapping("/stories")
	public void updatestory(@Validated({generalValidator.class}) TsscStory story, @PathVariable("idG") long idG) {
		
		ResponseEntity.ok(storyS.updateStory(story));
	}
	
	@DeleteMapping("/stories/{id}")
	public void deletestory(@PathVariable("id") long id) {
		TsscStory story = storyS.getStory(id);
		storyS.delete(story);
	}
}
