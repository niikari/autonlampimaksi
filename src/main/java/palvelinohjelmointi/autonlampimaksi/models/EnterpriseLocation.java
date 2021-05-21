package palvelinohjelmointi.autonlampimaksi.models;

import javax.persistence.Column;
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
public class EnterpriseLocation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long enterpriseLocationId;
	
	@Column(name="street", nullable=false)
	private String street;
	
	@Column(name="postcode", nullable=false)
	private String postcode;
	
	@Column(name="city", nullable=false)
	private String city;
	
	@ManyToOne
	@JoinColumn(name="enterpriseId")
	private Enterprise enterprise;
	
	public EnterpriseLocation(String street, String postcode, String city) {
		this.street = street;
		this.postcode = postcode;
		this.city = city;
	}
}
