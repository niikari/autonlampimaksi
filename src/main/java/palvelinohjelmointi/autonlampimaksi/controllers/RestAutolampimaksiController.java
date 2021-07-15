package palvelinohjelmointi.autonlampimaksi.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Supplier;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.UserRepository;
import palvelinohjelmointi.autonlampimaksi.services.CarService;

@RestController
public class RestAutolampimaksiController {
	
	@Autowired
	private EnterpriseRepository enterpriseReposity;
	
	@Autowired
	private SupplierRepository supplierRepository;
		
	@Autowired
	private CarService carService;
	
	@GetMapping("/enterprises")
	@ResponseBody
	public List<Enterprise> getAllEnterprises() {
		return (List<Enterprise>) this.enterpriseReposity.findAll();
	}
	
	@PostMapping("/enterprises")
	public Enterprise addEnterprise(@RequestBody Enterprise enterprise) {
		
		return this.enterpriseReposity.save(enterprise);
	}
	
	@GetMapping("/suppliers")
	@ResponseBody
	public List<Supplier> getAllSuppliers() {
		return (List<Supplier>) this.supplierRepository.findAll();
	}
	//ss
	
	@GetMapping("/cars/{plate}")
	@ResponseBody
	public Car returnACarByLicense(@PathVariable(name="plate") String plate) {
		return this.carService.returnCarByRegisterplate(plate);
		//return plate;
	}
	
	@GetMapping("/cars")
	@ResponseBody
	public List<Car> returnAllCars() {
		return this.carService.allSearchedCars();
	}
	
}
