package palvelinohjelmointi.autonlampimaksi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long bookingId;
	
	// MITÄ TAVAROITA TARVITAAN, MONEN SUHDE MONEEN DEFAPRODUCT TAULUN KANSSA
	//private List<Defaproduct> productsIncluded = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="enterpriseId")
	private Enterprise enterprise;
}
