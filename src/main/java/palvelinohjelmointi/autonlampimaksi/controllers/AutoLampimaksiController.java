package palvelinohjelmointi.autonlampimaksi.controllers;


import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.services.CarService;
import palvelinohjelmointi.autonlampimaksi.services.ParsingService;

@Controller
public class AutoLampimaksiController {
	
	private static final String url = "https://secure.defa.com/api/eh/searchregm/?regid=ere-523&c=f";
	
	@Autowired
	private ParsingService parsingService;

	@Autowired
	private EnterpriseRepository enterpriseRepository;

	@RequestMapping(value="/login")
    public String login() {	
        return "login";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		model.addAttribute("yritykset", this.enterpriseRepository.findAll());
		return "index";
	}
			
	@PostMapping("/addnewenterprise")
	public String addNewEnterpriseForm(@Validated Enterprise enterprise, BindingResult bd) {
		Enterprise ent = new Enterprise();
		if (bd.hasErrors()) {
			return "/enterprise";
		} else {
			enterpriseRepository.save(enterprise);
			ent = enterpriseRepository.findByName(enterprise.getName());			
		}
						
		return "redirect:/enterprise/" + ent.getEnterpriseId();
	}
	
	@GetMapping("/enterprise/{id}")
	public String enterpriseUser(Model model, @PathVariable Long id) {
		model.addAttribute("user", new User());
		model.addAttribute("enterprise", enterpriseRepository.findById(id));
		
		return "enterpriseuser";
	}

	
	// REKISTERÖINTI
	@GetMapping("/rekisterointi")
	public String enterpriseRegister(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "enterprise";
	}
	
	
	// LOGIN
	
}
