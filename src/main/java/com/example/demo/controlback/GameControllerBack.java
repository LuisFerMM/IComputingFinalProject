package com.example.demo.controlback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.TsscGame;
import com.example.demo.servicios.GameServiceImp;

@RestController
@RequestMapping("/backapi")
public class GameControllerBack {

	@Autowired
	private GameServiceImp gameS;
	
	@GetMapping("/games")
	public Iterable<TsscGame> cargarJuegos() {
		return gameS.findAll();
	}

	@GetMapping("/games/{id}")
	public TsscGame showUpdateForm(@PathVariable("id") long id) {
		return gameS.getGame(id);
	}
	
	@PostMapping("/games")
	public ResponseEntity saveGame(@RequestBody TsscGame game) {
		
		return ResponseEntity.ok(gameS.createGame(game));
	}
	
	@PostMapping("/games/{idT}")
	public ResponseEntity saveGame(@RequestBody TsscGame game, @PathVariable("idT") long idT) {
		
		return ResponseEntity.ok(gameS.createGameWithTopic2(game, idT));
	}
	
	@PutMapping("/games")
	public ResponseEntity updateGame(@RequestBody TsscGame game) {
		
		return ResponseEntity.ok(gameS.updateGame(game));
	}
	
	@PutMapping("/games/{idT}")
	public void updateGame(@RequestBody TsscGame game, @PathVariable("idT") long idT) {
		ResponseEntity.ok(gameS.updateGameWithTopic2(game, idT));
	}
	
	@DeleteMapping("/games/{id}")
	public void deleteGame(@PathVariable("id") long id) {
		TsscGame game = gameS.getGame(id);
		gameS.delete(game);
	}
	
}
