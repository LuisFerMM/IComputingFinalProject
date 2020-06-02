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

import com.example.demo.modelo.TsscStory;
import com.example.demo.servicios.StoryServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class StoryControllerBack {
	
	@Autowired
	private StoryServiceImp storyS;
	
	@GetMapping("/games/{id}/stories")
	public Iterable<TsscStory> showGameStories(@PathVariable("id") long id) {
		return storyS.findByGameId(id);
	}
	
	@GetMapping("/stories")
	public Iterable<TsscStory> showStories() {
		return storyS.findAll();
	}
	
	@PostMapping("/games/{idG}/stories")
	public ResponseEntity savestory(@RequestBody TsscStory story, @PathVariable("idG") long idG) {
		return ResponseEntity.ok(storyS.createStory(story, idG));
	}
	
	@GetMapping("/stories/{id}")
	public TsscStory showUpdateForm(@PathVariable("id") long id) {
		return storyS.getStory(id);
	}

	@PutMapping("/stories")
	public void updatestory(@RequestBody TsscStory story) {
		ResponseEntity.ok(storyS.updateStory(story));
	}
	
	@DeleteMapping("/games/{idG}/stories/{id}")
	public void deletestoryGame(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		storyS.delete(id);
	}
	
	@DeleteMapping("/stories/{id}")
	public void deletestory(@PathVariable("id") long id) {
		storyS.delete(id);
	}
}