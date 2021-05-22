package palvelinohjelmointi.autonlampimaksi.controllers;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.services.CarService;
import palvelinohjelmointi.autonlampimaksi.services.ParsingService;

@Controller
public class AutoLampimaksiController {
	
	private static final String url = "https://secure.defa.com/api/eh/searchregm/?regid=ere-523&c=f";
	
	@Autowired
	private ParsingService parsingService;
	
	@Autowired
	private CarService carService;

	@GetMapping("/")
	public String home(Model model) {
		Car car = carService.returnCarByRegisterplate("eoo-562");
		System.out.println(car);			
		
		return "index";
	}
	
	@PostMapping("/")
	public String getCar() {
		
		return "redirect:/home";
	}
	
}
