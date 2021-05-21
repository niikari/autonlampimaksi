package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseLocation;

@RepositoryRestResource
public interface EnterpriseLocationRepository extends CrudRepository<EnterpriseLocation, Long> {
	List<EnterpriseLocation> findByEnterprise(Enterprise enterprise);
}
