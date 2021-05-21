package palvelinohjelmointi.autonlampimaksi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AutoLampimaksiController {

	@GetMapping("/")
	public String home() {
		
		return "index";
	}
	
}
