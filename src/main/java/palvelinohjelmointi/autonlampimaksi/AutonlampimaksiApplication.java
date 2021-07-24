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
import palvelinohjelmointi.autonlampimaksi.models.Supplier;
import palvelinohjelmointi.autonlampimaksi.models.SupplierPrice;
import palvelinohjelmointi.autonlampimaksi.models.User;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.CustomerRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.DefaproductRepository;
import palvelinohjelmointi.autonlampimaksi.repositories.EnterpriseRepository;
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
			List<Defaproduct> kamat= lueDefaa();
			for (Defaproduct dp : kamat) {
				defaproductRepository.save(dp);
			}
		};
		
		
	}
	
	// LUKEE DEFATIEDOSTON JA PALAUTTAA VAIN 610 RIVIÄ, ENKÄ TAJUA MIKSI (MUTTA TESTIKSI RIITTÄÄ NYT)
	public List<Defaproduct> lueDefaa() {
		List<Defaproduct> kamat = new ArrayList<>();
		try {
			Scanner lukija = new Scanner(new File("defadata.csv"));
			
			
			while (lukija.hasNext()) {
				String rivi = lukija.nextLine();
				String[] palat = rivi.split(";");
				if (palat.length == 9) {
					Defaproduct dp = new Defaproduct();
					dp.setModel(palat[0].trim());
					dp.setModyear(palat[1].trim());
					dp.setEnginecode(palat[2].trim());
					dp.setEngineheaters(palat[3].trim());
					dp.setPlace(palat[4].trim());
					dp.setKytkikset(palat[5].trim());
					dp.setHaaroitukset(palat[6].trim());
					dp.setAika(palat[7].trim());
					if (!palat[8].equals("tyhja")) {
						dp.setEngineheatersMore(palat[8].trim());
					}
					kamat.add(dp);
				}
			}
						
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return kamat;
	}
	

	
	// LATAA 1 TOIMITTAJAN HINNASTON TIETOKANTAAN (TIEDOSTO TALLENNETTU TÄMÄN PROJEKTIN JUUREEN)
	public void lueOrumHinnastoTiedosto() {
		
	}
	
	// LATAA 2 TOIMITTAJAN HINNASTON TIETOKANTAAN (TIEDOSTO TALLENNETTU TÄMÄN PROJEKTIN JUUREEN)
	public void lueMekonomenHinnastoTiedosto() {
		
	}
	
}
