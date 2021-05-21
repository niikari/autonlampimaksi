package palvelinohjelmointi.autonlampimaksi.models;

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
public class EnterpriseSupplier {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long enterpriseSupplierId;
	
	@ManyToOne
	@JoinColumn(name="enterpriseId")
	private Enterprise enterprise;
	
	@ManyToOne
	@JoinColumn(name="supplierId")
	private Supplier supplier;
	
	@ManyToOne
	@JoinColumn(name="discountId")
	private Discount discount;
}
