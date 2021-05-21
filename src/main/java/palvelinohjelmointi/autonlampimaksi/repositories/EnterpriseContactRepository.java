package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseContact;

@RepositoryRestResource
public interface EnterpriseContactRepository extends CrudRepository<EnterpriseContact, Long> {
	List<EnterpriseContact> findByEnterprise(Enterprise enterprise);
}
