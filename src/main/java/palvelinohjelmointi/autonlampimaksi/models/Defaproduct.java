package palvelinohjelmointi.autonlampimaksi.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Defaproduct {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long defaproductId;
	
	private String defaproductNumber;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="defaproduct")
	List<DefacarDefaproduct> defacarDefaproducts;
}
