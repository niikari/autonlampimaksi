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
import palvelinohjelmointi.autonlampimaksi.models.CustomerContact;
import palvelinohjelmointi.autonlampimaksi.models.Defacar;
import palvelinohjelmointi.autonlampimaksi.models.Defaproduct;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseContact;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseCustomer;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseLocation;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseSupplier;
import palvelinohjelmointi.autonlampimaksi.models.Price;
import palvelinohjelmointi.autonlampimaksi.models.Supplier;
import palvelinohjelmointi.autonlampimaksi.models.SupplierLocation;
import palvelinohjelmointi.autonlampimaksi.models.SupplierPrice;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CustomerContactRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CustomerRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.DefacarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.DefaproductRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseContactRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseCustomerRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseLocationRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseSupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.PriceRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierLocationRepository;
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
	public CommandLineRunner demo(UserRepository userRepository, EnterpriseContactRepository enterpriseContactRepository,
			EnterpriseLocationRepository enterpriseLocationRepository, EnterpriseRepository enterpriseRepository, 
			PriceRepository priceRepository, SupplierRepository supplierRepository, SupplierPriceRepository supplierPriceRepository,
			SupplierLocationRepository supplierLocationRepository, EnterpriseSupplierRepository esRepository, CustomerRepository customerRepository,
			EnterpriseCustomerRepository enterpriseCustomerRepository, CarRepository carRepository, CarService carService
			,CustomerContactRepository customerContactRepository, DefacarRepository defacarRepository, DefaproductRepository defeproductRepisitory
			) {
		return (args) -> {
			//BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();
			
			// Luodaan ensin yritykset ja käyttäjät
			User user = new User("username", "password", "role");
			Enterprise enterprise = new Enterprise("S-Autohuolto Oy");
			user.setEnterprise(enterprise);
			EnterpriseContact enterpriseContact = new EnterpriseContact("0103220250", "info@S-Autohuolto.fi");
			enterpriseContact.setEnterprise(enterprise);
			EnterpriseLocation enterpriseLocation = new EnterpriseLocation("Laaksotie 35", "01390", "Vantaa");
			enterpriseLocation.setEnterprise(enterprise);
			Price price = new Price();
			price.setPricePerHour(86);
			price.setSolidTimeInnercable(1);
			price.setEnterprise(enterprise);
			enterpriseRepository.save(enterprise);
			userRepository.save(user);
			enterpriseContactRepository.save(enterpriseContact);
			priceRepository.save(price);
			enterpriseLocationRepository.save(enterpriseLocation);
			
			// Luodaan toimittajat
			Supplier orum = new Supplier("Örum Oy Ab");
			Supplier mekonomen = new Supplier("Mekonomen Oy Ab");
			supplierRepository.save(orum);
			supplierRepository.save(mekonomen);
			SupplierPrice orumPrice = new SupplierPrice();
			orumPrice.setSupplier(orum);
			supplierPriceRepository.save(orumPrice);
			SupplierLocation orumLocation = new SupplierLocation();
			orumLocation.setSupplierCity("Espoo");
			orumLocation.setSupplierEmail("pks@orum.fi");
			orumLocation.setSupplier(orum);
			supplierLocationRepository.save(orumLocation);
			SupplierPrice mekoPrice = new SupplierPrice();
			mekoPrice.setSupplier(mekonomen);
			supplierPriceRepository.save(mekoPrice);
			SupplierLocation mekoLocation = new SupplierLocation();
			mekoLocation.setSupplierCity("Vantaa");
			mekoLocation.setSupplierEmail("mekonomen@vantaa.fi");
			mekoLocation.setSupplier(mekonomen);
			supplierLocationRepository.save(mekoLocation);
			
			// Yhdistetään toimittajat ja yritykset
			EnterpriseSupplier es = new EnterpriseSupplier();
			es.setSupplier(orum);
			es.setEnterprise(enterprise);
			esRepository.save(es);
			EnterpriseSupplier es2 = new EnterpriseSupplier();
			es2.setSupplier(mekonomen);
			es2.setEnterprise(enterprise);
			esRepository.save(es2);
			
			// Luodaan asiakkaita (kuluttajat)
			Customer customer = new Customer();
			customer.setCustFirstName("Niiles");
			customer.setCustLastName("Kari");
			customer.setCustCity("Vantaa");
			CustomerContact customerContact = new CustomerContact();
			customerContact.setCustomer(customer);
			customerContact.setCustEmail("pakniiles@gmail.com");
			customerContact.setCustPhone("0405310296");			
			Customer customer2 = new Customer();
			CustomerContact customerContact2 = new CustomerContact();
			customer2.setCustFirstName("Nora");
			customer2.setCustLastName("Granlund");
			customer2.setCustCity("Turku");
			customerContact2.setCustomer(customer2);
			customerContact2.setCustEmail("belinda86@gmail.com");
			customerContact2.setCustPhone("040808580");
			customerRepository.save(customer);
			customerRepository.save(customer2);
			customerContactRepository.save(customerContact);
			customerContactRepository.save(customerContact2);
			EnterpriseCustomer ec = new EnterpriseCustomer();
			ec.setCustomer(customer);
			ec.setEnterprise(enterprise);
			EnterpriseCustomer ec2 = new EnterpriseCustomer();
			ec2.setCustomer(customer2);
			ec2.setEnterprise(enterprise);
			enterpriseCustomerRepository.save(ec);
			enterpriseCustomerRepository.save(ec2);
			
			// Tuodaan yksi auto netistä
			Car car = carService.returnCarByRegisterplate("fmt-698");
			Car car2 = carService.returnCarByRegisterplate("ere-523");
			car.setCustomer(customer);
			car2.setCustomer(customer2);
			carRepository.save(car);
			carRepository.save(car2);	
			
			// Autojen lisääminen defacar tauluun
			lueTiedosto(defacarRepository, defeproductRepisitory);
			
		};
		
		
	}
	
	public static void lueTiedosto(DefacarRepository defacarRepository, DefaproductRepository defeproductRepisitory) {
		String tiedosto = "C:\\Users\\pakni\\OneDrive\\Työpöytä\\DEFA-Suositustaulukko-2018-2019_web.csv";
		
		int malli = 0; // 1
		int vuosi = 0; // 2
		int moottori = 0; // 3
		int lammitin = 0; // 4
		int kytkis = 0; // 7
		int haaroitus = 0; // 8
		int aika = 0;; // 9
		int lammitin2 = 0; // 10
				
		try {
			Scanner lukija = new Scanner(new File(tiedosto));
			int i = 0;
			while(lukija.hasNext()) {
				String rivi = lukija.nextLine();
				String[] palat = rivi.split(";");
				//System.out.println(palat[0]);
				List<String> osat = new ArrayList<>();
				for (String osa : palat) {
					osat.add(osa);
				}
				if (osat.contains("1")) {
					malli = osat.indexOf("1");
				}
				if (osat.contains("2")) {
					vuosi = osat.indexOf("2");
				}
				if (osat.contains("3")) {
					moottori = osat.indexOf("3");
				}
				
				if (osat.contains("4")) {
					lammitin = osat.indexOf("4");
				}
				if (osat.contains("7")) {
					kytkis = osat.indexOf("7");
				}
				if (osat.contains("8")) {
					haaroitus = osat.indexOf("8");
				}
				if (osat.contains("9")) {
					aika = osat.indexOf("9");
				}
				
				if (osat.contains("10")) {
					lammitin2 = osat.indexOf("10");
				}
				
				while (lukija.hasNext()) {
					String rivi2 = lukija.nextLine();
					String[] palat2 = rivi2.split(";");
					//System.out.println(palat[0]);
					List<String> osat2 = new ArrayList<>();
					for (String osa : palat2) {
						osat2.add(osa);
					}
					if (osat2.size() + 1 >= lammitin2) {
						Defacar car = new Defacar();
						car.setDefacarModel(osat2.get(malli));
						car.setDefacarModyear(osat2.get(vuosi));
						car.setDefacarEnginecode(osat2.get(moottori));
						car.setHeaterInstallTime(osat2.get(aika));
						defacarRepository.save(car);
						
						/*Defaproduct dp = new Defaproduct();
						
						System.out.println(osat2.get(malli));
						System.out.println(osat2.get(vuosi));
						System.out.println(osat2.get(moottori));
						System.out.println(osat2.get(lammitin));
						System.out.println(osat2.get(kytkis));
						System.out.println(osat2.get(haaroitus));
						System.out.println(osat2.get(aika));
						//System.out.println(osat2.get(lammitin2));
						System.out.println();*/
					}
					
					
					i++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		System.out.println(malli);
		System.out.println(vuosi);
		System.out.println(moottori);
		System.out.println(lammitin);
		System.out.println(kytkis);
		System.out.println(haaroitus);
		System.out.println(aika);
		System.out.println(lammitin2);
		*/
	}

}
