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
public class Discount {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long discountId;
	
	private String discountGroup;
	
	private Double discount;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="discount")
	List<EnterpriseSupplier> enterpriseSuppliers;
}
