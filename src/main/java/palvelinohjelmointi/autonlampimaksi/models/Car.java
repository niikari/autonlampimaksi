package palvelinohjelmointi.autonlampimaksi.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	private String make;
	private String model;
	private String engine;
	private String fuel;
	public int modyear;
	private String engineheater;
		
}
