package palvelinohjelmointi.autonlampimaksi.services;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private ParsingService parsingService;
	
	@Autowired
	private CarRepository carRepository;
	
	// For consept of proof! => should be changed to payd service
	// private String url = "https://secure.defa.com/api/eh/searchregm/?regid=";
	
	//ere-523&c=f
	
	public List<Car> allSearchedCars() {
		List<Car> cars = (List<Car>) this.carRepository.findAll();
		return cars;
	}
	
	public String testi(String toinen) {
		return "Testiä puskee " + toinen;
	}
	
	public Car returnCarByRegisterplate(String plate) {
		ObjectMapper objectMapper = new ObjectMapper();
		Car car = new Car();
		Map<String, Object> map = new HashMap<>();
		String url = "https://secure.defa.com/api/eh/searchregm/?regid=";
		url += plate + "&c=f";
		// System.out.println(url);
		try {
			map = objectMapper.readValue(new URL(url),new TypeReference<Map<String,Object>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String rivi = "";
		int i = 0;
		// VOI TEHDÄ TARKEMMAKSI JOS OSAISI KÄSITELLÄ JSON TIEDOSTON OIKEIN...
		for (Object o : map.keySet()) {
			rivi = map.get(o).toString();
			if (i > 0) {
				String[] palat = rivi.split(",");
				for (String sana : palat) {					
					sana = sana.replace("{", "");
					sana = sana.replace("[", "");
					sana = sana.replace("}", "");
					sana = sana.replace("]", "");
					sana = sana.replace(" ", "");
					System.out.println(sana);
					//sana = sana.replace("-", "");
					
					String[] j = sana.split("=");
					if (j[0].equals("make")) {
						car.setMake(j[1]);						
					}
					if (j[0].equals("model")) {
						car.setModel(j[1]);
					}
					if (j[0].equals("engine")) {
						car.setEngine(j[1]);
					}
					if (j[0].equals("fuel")) {
						car.setFuel(j[1]);
					}
					if (j[0].equals("modyear")) {
						car.setModyear(j[1]);
					} 
					if (j[0].equals("engineheater")) {
						car.setEngineheater(j[1]);
					}
					if (j[0].equals("enginecode")) {
						car.setDevaEngineCode(j[1]);
					}
					car.setPlate(plate);
				}
			}			
			
			i++;
		}
		try {
			this.carRepository.save(car);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Auto kyseisellä rekisterinumerolla jo olemassa!");
		}
		
		
		return car;
	}
}
