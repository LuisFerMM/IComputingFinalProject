package com.example.demo.controlback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.TsscTimecontrol;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.servicios.TimeControlServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class TimeControllerBack {
	
	@Autowired
	private TimeControlServiceImp timeControllerService;

	@GetMapping("/games/{id}/timelines")
	public Iterable<TsscTimecontrol> showStories(@PathVariable("id") long id) {
		return timeControllerService.findAll();
	}
	
	@PostMapping("/games/{id}/timelines")
	public ResponseEntity savestory(@RequestBody TsscTimecontrol story, @PathVariable("id") long id) {
		return ResponseEntity.ok(timeControllerService.createTimeControl(story, id));
	}
	
	@GetMapping("/timelines/{id}")
	public TsscTimecontrol showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		return timeControllerService.getTimeControl(id);
	}

	@PutMapping("/timelines")
	public void updatestory(@Validated({generalValidator.class}) TsscTimecontrol tsscTimecontrol, @PathVariable("idG") long idG) {
		ResponseEntity.ok(timeControllerService.updateTimeControl(tsscTimecontrol));
	}
	
	@DeleteMapping("/timelines/{id}")
	public void deletestory(@PathVariable("id") long id) {
		TsscTimecontrol tsscTimecontrol = timeControllerService.getTimeControl(id);
		timeControllerService.deleteTimeControl(tsscTimecontrol);
	}
}
