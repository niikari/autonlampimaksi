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
public class Price {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long priceId;
	
	@Column(name="price_per_hour", nullable=false)
	private double pricePerHour;
	
	private double solidTimeInnercable, discountWork, discountParts;
	
	@ManyToOne
	@JoinColumn(name="enterpriseId")
	private Enterprise enterprise;
	
	
}
