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
public class CustomerContact {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long custContactId;
	
	private String custEmail;
	private String custPhone;
	
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
}
