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

import com.example.demo.modelo.TsscTimecontrol;
import com.example.demo.servicios.TimeControlServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class TimeControllerBack {
	
	@Autowired
	private TimeControlServiceImp timeControllerService;
	
	@GetMapping("/games/{id}/timelines")
	public Iterable<TsscTimecontrol> showTimes(@PathVariable("id") long id) {
		return timeControllerService.findByGameId(id);
	}
	
	@PostMapping("/games/{idG}/timelines")
	public ResponseEntity saveTime(@RequestBody TsscTimecontrol time, @PathVariable("idG") long idG) {
		return ResponseEntity.ok(timeControllerService.createTimeControl(time, idG));
	}
	
	@GetMapping("/timelines/{id}")
	public TsscTimecontrol showUpdateForm(@PathVariable("id") long id) {
		return timeControllerService.getTimeControl(id);
	}

	@PutMapping("/timelines")
	public void updateTime(@RequestBody TsscTimecontrol tsscTimecontrol) {
		ResponseEntity.ok(timeControllerService.updateTimeControl(tsscTimecontrol));
	}
	
	@DeleteMapping("/timelines/{id}")
	public void deleteTime(@PathVariable("id") long id) {
		timeControllerService.deleteTimeControl(id);
	}
}