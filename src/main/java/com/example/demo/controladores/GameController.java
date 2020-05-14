package com.example.demo.controladores;

import java.util.Optional;

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
import com.example.demo.modelo.TsscTopic;
import com.example.demo.modelo.TsscGame.generalValidator;
import com.example.demo.servicios.GameServiceImp;

@Controller
public class GameController {

	@Autowired
	private GameServiceImp gameS;
	
	@GetMapping("/games/")
	public String cargarJuegos(Model model) {
		if(gameS.findAll().iterator().hasNext())
		model.addAttribute("games", gameS.findAll());
		return "games/index";
	}
	
	@GetMapping("/games/add")
	public String addGamePage(Model model) {
		model.addAttribute("tsscGame", new TsscGame());
		model.addAttribute("topics", gameS.getTopicS().findAll());
		model.addAttribute("tsscTopic", null);
		return "games/add-game";
	}
	
	@PostMapping("/games/add")
	public String saveGame(@Validated({generalValidator.class}) TsscGame game, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscGame", game);
				model.addAttribute("topics", gameS.getTopicS().findAll());
				return "games/add-game";
			}
			gameS.createGame(game);
		}
		return "redirect:/games/";
	}
	
	@PostMapping("/games/add/{idT}")
	public String saveGame(@Validated({generalValidator.class}) TsscGame game, BindingResult bindingResult, @PathVariable("idT") long idT, @RequestParam(value = "action", required = true) String action, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscGame", game);
				model.addAttribute("topics", gameS.getTopicS().findAll());
				return "games/add-game";
			}
			System.out.println(idT);
			gameS.createGameWithTopic2(game, idT);
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		TsscGame game = gameS.getGame(id);
		if (game == null)
			throw new IllegalArgumentException("Invalid game Id:" + id);
		model.addAttribute("tsscGame", game);
		model.addAttribute("topics", gameS.getTopicS().findAll());
//		model.addAttribute("tsscTopic", -1);
		return "games/update-game";
	}

	@PostMapping("/games/edit")
	public String updateGame(@Validated({generalValidator.class}) TsscGame game, BindingResult bindingResult,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscGame", game);
			m.addAttribute("topics", gameS.getTopicS().findAll());
			return "games/update-game";
		}
			gameS.updateGame(game);
		}
		return "redirect:/games/";
	}
	
	@PostMapping("/games/edit/{idT}")
	public String updateGame(@Validated({generalValidator.class}) TsscGame game, BindingResult bindingResult, @PathVariable("idT") long idT,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (action != null && !action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscGame", game);
			m.addAttribute("topics", gameS.getTopicS().findAll());
			return "games/update-game";
		}
			System.out.println(idT);
			gameS.createGameWithTopic2(game, idT);
		}
		return "redirect:/games/";
	}
	
	@GetMapping("/games/del/{id}")
	public String deleteGame(@PathVariable("id") long id) {
		TsscGame game = gameS.getGame(id);
		gameS.delete(game);
		return "redirect:/games/";
	}
	
}
