package palvelinohjelmointi.autonlampimaksi.controllers;


import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Offer;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.OfferRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.UserRepository;
import palvelinohjelmointi.autonlampimaksi.services.CarService;
import palvelinohjelmointi.autonlampimaksi.services.OfferService;
import palvelinohjelmointi.autonlampimaksi.services.ParsingService;

@Controller
public class AutoLampimaksiController {
	
	private static final String url = "https://secure.defa.com/api/eh/searchregm/?regid=ere-523&c=f";
	
	@Autowired
	private ParsingService parsingService;

	@Autowired
	private EnterpriseRepository enterpriseRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CarService carService;
	
	@Autowired
	private OfferService offerService;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CarRepository carRepository;

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
	
	// TARJOUKSIEN NÄYTTÄMINEN ASIAKKAALLE - UUSI HTML SIVU LIST.HTML
	// LUOKKA TARJOUS => TALLENNETAAN JOKAINEN ANNETTU TARJOUS... EHKÄ? :)
	@GetMapping("/list/{rek}")
	public String chooseEnterpriseByPrice(Model model, @PathVariable String rek) {
		model.addAttribute("offer", new Offer());
		
		// TÄÄLTÄ TULEE ERRORIA, MUTTA TOIMII - TUO NULL ARVO HÄIRITSEE (EN TIEDÄ TARKALLEEN MISTÄ JOHTUU)
		if (carRepository.findCarByPlate(rek) == null) {
			carRepository.save(carService.getCarByRegisterplate(rek));
		} 
			
		List<Defaproduct> productsFit = carService.getDefaproductsByCar(carRepository.findCarByPlate(rek));
			
		for (Enterprise enterprise : enterpriseRepository.findAll()) {
			offerService.newOffer(enterprise, productsFit, carRepository.findCarByPlate(rek));
		}
		
		List<String> parts = offerService.getPartsOfTheOffer(productsFit.get(0));
				
		model.addAttribute("offers", offerRepository.findByCar(carRepository.findCarByPlate(rek)));
		model.addAttribute("parts", parts);
		
		return "list";
	}
	
	@GetMapping("/enterprise/{id}")
	public String enterpriseUser(Model model, @PathVariable Long id) {
		model.addAttribute("user", new User());
		model.addAttribute("enterprise", enterpriseRepository.findById(id));
				
		return "enterpriseuser";
	}
	
	@PostMapping("/enterprise/{id}")
	public String addNewUser(@Validated User user, BindingResult bd, @PathVariable Long id) {
		if (bd.hasErrors()) {
			return "redirect:/enterprise/" + id;
		}
		
		//userRepository.save(user);
		Enterprise ent = enterpriseRepository.findById(id).get();
		user.setEnterprise(ent);
		userRepository.save(user);
		
		return "redirect:/";
	}

	
	// REKISTERÖINTI
	@GetMapping("/rekisterointi")
	public String enterpriseRegister(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "enterprise";
	}
	
	
	// LOGIN
	
}
