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
public class EnterpriseContact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long enterpriseContactId;
	
	@Column(name="phone", nullable=false)
	private String phone;
	
	@Column(name="email", nullable=false)
	private String email;
	
	@ManyToOne
	@JoinColumn(name="enterpriseId")
	private Enterprise enterprise;
	
	public EnterpriseContact(String phone, String email) {
		this.phone = phone;
		this.email = email;
	}
}
