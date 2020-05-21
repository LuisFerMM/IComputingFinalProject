package com.example.demo.controlback;

import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.TsscGame;
import com.example.demo.modelo.TsscTopic;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.servicios.GameServiceImp;

@CrossOrigin
@RestController
@RequestMapping("/backapi")
public class GameControllerBack {

	@Autowired
	private GameServiceImp gameS;
	
	@GetMapping("/games/")
	public Iterable<TsscGame> cargarJuegos() {
		return gameS.findAll();
	}

	@GetMapping("/games/{id}")
	public TsscGame showUpdateForm(@PathVariable("id") long id) {
		return gameS.getGame(id);
	}
	
	@PostMapping("/games/")
	public TsscGame saveGame(@RequestBody TsscGame game) {			
		return gameS.createGame(game);
	}
	
	@PostMapping("/games/{idT}")
	public TsscGame saveGame(@RequestBody TsscGame game, @PathVariable("idT") long idT) {			
		return gameS.createGameWithTopic2(game, idT);
	}
	
	@PutMapping("/games/")
	public void updateGame(@RequestBody TsscGame game) {
			gameS.updateGame(game);
	}
	
	@PutMapping("/games/{idT}")
	public void updateGame(@RequestBody TsscGame game, @PathVariable("idT") long idT) {
			gameS.createGameWithTopic2(game, idT);
	}
	
	@DeleteMapping("/games/{id}")
	public void deleteGame(@PathVariable("id") long id) {
		TsscGame game = gameS.getGame(id);
		gameS.delete(game);
	}
	
}
