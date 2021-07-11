package palvelinohjelmointi.autonlampimaksi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Customer;
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseCustomer;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseSupplier;
import palvelinohjelmointi.autonlampimaksi.models.Supplier;
import palvelinohjelmointi.autonlampimaksi.models.SupplierPrice;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CustomerRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.DefaproductRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseCustomerRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseSupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierPriceRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.UserRepository;
import palvelinohjelmointi.autonlampimaksi.services.CarService;

@SpringBootApplication
public class AutonlampimaksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonlampimaksiApplication.class, args);
	}

	@Bean
	public CommandLineRunner testiTiedonLataaminen() {
		return (args) -> {
			//BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
			
			
			
		};
		
		
	}
	
}
