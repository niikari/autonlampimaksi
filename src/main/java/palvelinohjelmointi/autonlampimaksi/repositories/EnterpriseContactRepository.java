package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseContact;

public interface EnterpriseContactRepository extends CrudRepository<EnterpriseContact, Long> {
	List<EnterpriseContact> findByEnterprise(Enterprise enterprise);
}
