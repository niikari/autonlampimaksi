package palvelinohjelmointi.autonlampimaksi.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long supplierId;
	
	@Column(name="supplierName", nullable=false)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="supplier")
	List<EnterpriseSupplier> enterpriseSuppliers;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="supplier")
	List<SupplierLocation> locations;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="supplier")
	List<SupplierPrice> prices;
	
	public Supplier(String name) {
		this.name=name;
	}
}
