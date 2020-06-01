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

import com.example.demo.modelo.TsscTimecontrol;
import com.example.demo.delegate.GameDelegateImp;
import com.example.demo.delegate.TimeDelegate;
import com.example.demo.delegate.TimeDelegateImp;
import com.example.demo.modelo.TsscGame.generalValidator;

@CrossOrigin
@Controller
@RequestMapping("/frontapi")
public class TimelineController {
	
	@Autowired
	private TimeDelegateImp timeDelegate;
	@Autowired
	private GameDelegateImp gameDelegate;
	
	@GetMapping("/games/{id}/timelines")
	public String showTimes(@PathVariable("id") long id, Model model) {
		model.addAttribute("timelines", timeDelegate.GET_times(id));
		model.addAttribute("tsscGame", gameDelegate.GET_Game(id));
		return "games/timelines/index";
	}
	
	@GetMapping("/games/{idG}/timelines/add")
	public String addTimePage(@PathVariable("idG") long idG, Model model) {
		model.addAttribute("tsscTimecontrol", new TsscTimecontrol());
		model.addAttribute("tsscGame", gameDelegate.GET_Game(idG));
		return "games/timelines/add-timeline";
	}
	
	@PostMapping("/games/{idG}/timelines/add")
	public String saveTime(@Validated({generalValidator.class}) TsscTimecontrol time, BindingResult bindingResult, @RequestParam(value = "action", required = true) String action, @PathVariable("idG") long idG, Model model) {			
		if (!action.equals("Cancel")) {
			if(bindingResult.hasErrors()) {
				model.addAttribute("tsscTimecontrol", time);
				return "games/timelines/add-timeline";
			}
			timeDelegate.POST_Time(time, idG);		
		}
		return "redirect:/frontapi/games/"+idG+"/timelines";
	}
	
	@GetMapping("/games/{idG}/timelines/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, @PathVariable("idG") long idG, Model model) {
		TsscTimecontrol time = timeDelegate.GET_Time(id);
		if (time == null)
			throw new IllegalArgumentException("Invalid story Id:" + id);
		model.addAttribute("tsscTimecontrol", time);
		model.addAttribute("tsscGame", gameDelegate.GET_Game(idG));
		return "games/timelines/update-timeline";
	}

	@PostMapping("/games/{idG}/timelines/edit")
	public String updateTime(@Validated({generalValidator.class}) TsscTimecontrol time, BindingResult bindingResult, @PathVariable("idG") long idG,
			@RequestParam(value = "action", required = true) String action, Model m) {
		if (!action.equals("Cancel")) {
		if(bindingResult.hasErrors()) {
			m.addAttribute("tsscTimecontrol", time);
			return "games/timelines/update-timeline";
		}
			timeDelegate.PUT_Time(time);
		}
		return "redirect:/frontapi/games/"+ idG +"/timelines";
	}
	
	
	@GetMapping("/games/{idG}/timelines/del/{id}")
	public String deleteTime(@PathVariable("id") long id, @PathVariable("idG") long idG) {
		TsscTimecontrol time = timeDelegate.GET_Time(id);
		timeDelegate.DELETE_Time(time);
		return "redirect:/frontapi/games/"+idG+"/timelines";
	}
}