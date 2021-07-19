package palvelinohjelmointi.autonlampimaksi;

import java.io.File;
import java.io.FileNotFoundException;
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
	public CommandLineRunner testiTiedonLataaminen(DefaproductRepository defaproductRepository) {
		return (args) -> {
			//BCryptPasswordEncoder crypt = new BCryptPasswordEncoder();		
			//System.out.println(lueDefaTiedosto().size());
			lueDefaa();
		};
		
		
	}
	
	public void lueDefaa() {
		
		try {
			Scanner lukija = new Scanner(new File("defadata.csv"));
			
			int i = 1;
			while (lukija.hasNext()) {
				String[] palat = lukija.nextLine().split(";");
				if (palat.length == 1) {
					System.out.println(i);
				}
				
				i++;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// LAITTAA DEFAN DATAN PAIKOILLEEN TIETOKANTAAN
	public List<Defaproduct> lueDefaTiedosto() {
		
		List<Defaproduct> tuotteet = new ArrayList<>();
		
		String tiedosto = "defadata.csv";
		
		try {
			Scanner lukija = new Scanner(new File(tiedosto));
			int i = 0;
			while (lukija.hasNext()) {
				String rivi = lukija.nextLine();
				if (i > 0) {
					String[] palat = rivi.split(";");
					if (palat.length == 9) {
						Defaproduct dp = new Defaproduct();
						dp.setModel(palat[0]);
						dp.setModyear(palat[1]);
						dp.setEnginecode(palat[2]);
						dp.setEngineheaters(palat[3]);
						dp.setPlace(palat[4]);
						dp.setKytkikset(palat[5]);
						dp.setHaaroitukset(palat[6]);
						dp.setAika(palat[7]);
						dp.setEngineheatersMore(palat[8]);
						tuotteet.add(dp);
					}
				}
				
				i++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return tuotteet;
	}
	
	// LATAA 1 TOIMITTAJAN HINNASTON TIETOKANTAAN (TIEDOSTO TALLENNETTU TÄMÄN PROJEKTIN JUUREEN)
	public void lueOrumHinnastoTiedosto() {
		
	}
	
	// LATAA 2 TOIMITTAJAN HINNASTON TIETOKANTAAN (TIEDOSTO TALLENNETTU TÄMÄN PROJEKTIN JUUREEN)
	public void lueMekonomenHinnastoTiedosto() {
		
	}
	
}
