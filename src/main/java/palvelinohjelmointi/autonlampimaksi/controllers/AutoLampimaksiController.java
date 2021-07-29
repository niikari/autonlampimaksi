package palvelinohjelmointi.autonlampimaksi.controllers;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import palvelinohjelmointi.autonlampimaksi.models.Booking;
import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Customer;
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Offer;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.BookingRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CustomerRepository;
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
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	
	@RequestMapping(value="/login")
    public String login() {	
		
        return "login";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password) {
		User user = userRepository.findUserByUsername(username);
		if (password.equals(password)) {
			if (user.getEnterprise() == null) {
				// KULUTTAJA
				
			} else {
				// PALVELUNTARJOAJA
				
			}
			
		}
		return "redirect:/";
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
	// LUOKKA TARJOUS => TALLENNETAAN JOKAINEN TARJOUS, AUTO JA ASIAKAS JÄÄVÄT POIS VIELÄ TÄSSÄ VAIHEESSA:)
	@GetMapping("/list/{rek}")
	public String chooseEnterpriseByPrice(Model model, @PathVariable String rek, RedirectAttributes redirAttrs) {
		// JOS AUTO ON JO MERKITTY JOLLEKKIN ASIAKKAALLE NIIN OHJATAAN LOGIN SIVULLE
		// TÄHÄN PITÄISI SAADA FLASH VIESTI => AUTO ON JO MERKITTY ASIAKKAALLE - OLE HYVÄ JA KIRJAUDU SISÄÄN
		if (carRepository.findCarByPlate(rek.toUpperCase()) != null) {
			redirAttrs.addFlashAttribute("message", "Ajoneuvo on jo rekisteröity käyttäjälle, ole hyvä ja kirjaudu nähdäksesi lisätietoja.");
			return "redirect:/login";
		}
		model.addAttribute("offer", new Offer());
		List<Defaproduct> productsFit = carService.getDefaproductsByCar(carService.getCarByRegisterplate(rek));
		// ANTAA HERJAN ARRAYLIST => MUKA TYHJÄ? VAIKKA LÖYTYY OSA
		List<String> parts = offerService.getPartsOfTheOffer(productsFit.get(0));
		List<Offer> offers = new ArrayList<>();
		for (Enterprise enterprise : enterpriseRepository.findAll()) {
			offers.add(offerRepository.save(offerService.newOffer(enterprise, productsFit)));
			//offerRepository.save(offerService.newOffer(enterprise, productsFit));
		}
				
		model.addAttribute("offers", offers);
		model.addAttribute("parts", parts);
		model.addAttribute("rek", rek);
		
		return "list";
	}
	
	@GetMapping("/list/offer/{id}/{rek}")
	public String customerChoseAnOffer(@PathVariable Long id, @PathVariable String rek, Model model, @Validated Customer customer, BindingResult bd) {
		Offer offer = offerRepository.findById(id).get();
		model.addAttribute("customer", new Customer());
		model.addAttribute("offer", offer);
		model.addAttribute("rek", rek);
		return "offer";
	}
	
	@PostMapping("/addnewcustomer/{offerid}/{rek}")
	public String addUserForCustomer(@PathVariable Long offerid, @PathVariable String rek, Model model, @Validated Customer customer, BindingResult bd) {
		if (bd.hasErrors()) {
			return "redirect:/addnewcustomer/" + offerid + "/" + rek;
		}
		
		Customer added = customerRepository.save(customer);
		
		return "redirect:/reguser/" + offerid + "/" + rek + "/" + added.getCustomerId();
	}
	
	// TÄHÄN KOHTAAN VIELÄ: REK, ASIAKASID JA SITTEN ASIAKKAAN LIITTÄMINEN AUTOON JA TARJOUKSEN VAHVISTAMINEN VARAUKSEKSI
	@GetMapping("/reguser/{offerid}/{rek}/{custid}")
	public String regCustomersUser(@PathVariable Long offerid, @PathVariable String rek, @PathVariable Long custid, Model model) {
		//model.addAttribute("user", new User());
		Offer offer = offerRepository.findById(offerid).get();
		Enterprise ent = offer.getEnterprise();
		model.addAttribute("jono", ent.getFreeInDays());
		Customer customer = customerRepository.findById(custid).get();
		model.addAttribute("email", customer.getEmail());
		return "customeruser";
	}
	
	@PostMapping("/reguser/{offerid}/{rek}/{custid}")
	public String addCustomersUser(@PathVariable Long offerid, @PathVariable String rek, @PathVariable Long custid, Model model, RedirectAttributes redirAttrs,
			@RequestParam String firstPwrd, @RequestParam String secondPwrd, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookedDate) {
		
		if (firstPwrd.equals(secondPwrd) && bookedDate != null) {
			Booking booking = new Booking();
			Car car = carService.getCarByRegisterplate(rek);
			car.setCustomer(customerRepository.findById(custid).get());
			carRepository.save(car);
			Offer offer = offerRepository.findById(offerid).get();
			offer.setCar(car);
			offerRepository.save(offer);			
			booking.setOffer(offer);
			User user = new User();
			user.setUsername(customerRepository.findById(custid).get().getEmail());
			user.setPasswordHash(secondPwrd);
			userRepository.save(user);
			booking.setBookedDate(bookedDate);
		} else if (!firstPwrd.equals(secondPwrd)) {
			// YRITIN SAADA FLASHVIESTIN TOIMIMAAN - KUN ANTAA ERI SALASANAT => TULISI TULLA ILMOITUS
			redirAttrs.addFlashAttribute("message", "Salasanojen pitää olla samat, yritäs uudelleen");
			return "redirect:/reguser/" + offerid + "/" + rek + "/" + custid;
		} else {
			redirAttrs.addFlashAttribute("message", "Valitse vielä haluamasi päivä asennukselle");
			return "redirect:/reguser/" + offerid + "/" + rek + "/" + custid;
		}
				
		return "redirect:/";
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
	/*
	@GetMapping("/booking/{id}")
	public String setBookingTime() {
		
	}
	*/
	
	// U
	@GetMapping("/rekisterointi")
	public String enterpriseRegister(Model model) {
		model.addAttribute("enterprise", new Enterprise());
		return "enterprise";
	}
	
	
	// LOGIN
	
}
