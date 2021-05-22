package palvelinohjelmointi.autonlampimaksi.services;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import palvelinohjelmointi.autonlampimaksi.models.Car;

@Service
public class CarService {

	@Autowired
	private ParsingService parsingService;
	
	private static String url = "https://secure.defa.com/api/eh/searchregm/?regid=";
	
	private List<Car> cars = new ArrayList<>();
	//ere-523&c=f
	
	public List<Car> allSearchedCars() {
		return this.cars;
	}
	
	public Car returnCarByRegisterplate(String plate) {
		ObjectMapper objectMapper = new ObjectMapper();
		Car car = new Car();
		Map<String, Object> map = new HashMap<>();
		url += plate + "&c=f";
		
		try {
			map = objectMapper.readValue(new URL(url),new TypeReference<Map<String,Object>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		String rivi = "";
		int i = 0;
		//System.out.println(map);
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
					sana = sana.replace("-", "");
					//System.out.println(sana);
					
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
						car.setModyear(Integer.parseInt(j[1]));
					} 
					if (j[0].equals("engineheater")) {
						car.setEngineheater(j[1]);
					}
				}
			}
			
			i++;
		}
		this.cars.add(car);
		return car;
	}
}
