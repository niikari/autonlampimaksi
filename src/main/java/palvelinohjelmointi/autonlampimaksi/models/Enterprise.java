package palvelinohjelmointi.autonlampimaksi.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Enterprise {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long enterpriseId;
	
	@Column(name="name", nullable=false, unique=true)
	private String name;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="enterprise")
	List<User> users;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="enterprise")
	List<EnterpriseSupplier> suppliers;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="enterprise")
	List<EnterpriseCustomer> enterpriseCustomers;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="enterprise")
	List<Booking> bookings;
	
	public Enterprise(String name) {
		this.name = name;
	}
}
