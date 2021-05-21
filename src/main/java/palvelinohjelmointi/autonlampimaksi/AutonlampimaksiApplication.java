package palvelinohjelmointi.autonlampimaksi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseContact;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseLocation;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseSupplier;
import palvelinohjelmointi.autonlampimaksi.models.Price;
import palvelinohjelmointi.autonlampimaksi.models.Supplier;
import palvelinohjelmointi.autonlampimaksi.models.SupplierLocation;
import palvelinohjelmointi.autonlampimaksi.models.SupplierPrice;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseContactRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseLocationRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseSupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.PriceRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierLocationRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierPriceRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.SupplierRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.UserRepository;

@SpringBootApplication
public class AutonlampimaksiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutonlampimaksiApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserRepository userRepository, EnterpriseContactRepository enterpriseContactRepository,
			EnterpriseLocationRepository enterpriseLocationRepository, EnterpriseRepository enterpriseRepository, 
			PriceRepository priceRepository, SupplierRepository supplierRepository, SupplierPriceRepository supplierPriceRepository,
			SupplierLocationRepository supplierLocationRepository, EnterpriseSupplierRepository esRepository) {
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
		};
	}

}
