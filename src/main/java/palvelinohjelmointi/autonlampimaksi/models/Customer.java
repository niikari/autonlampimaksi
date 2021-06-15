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
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerId;

	private String custFirstName;
	private String custLastName;
	private String custAddress;
	private String custPostcode;
	private String custCity;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	List<EnterpriseCustomer> enterpriseCustomers;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	List<Car> cars;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	List<CustomerContact> customerContacts;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL,mappedBy="customer")
	List<Booking> bookings;
}