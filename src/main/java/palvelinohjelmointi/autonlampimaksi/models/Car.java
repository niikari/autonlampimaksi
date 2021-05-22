package palvelinohjelmointi.autonlampimaksi.models;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@JsonIgnoreProperties
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="plate", unique=true, nullable=false)
	private String plate;
	
	private String make;
	private String model;
	private String engine;
	private String fuel;
	public int modyear;
	private String engineheater;
		
}
