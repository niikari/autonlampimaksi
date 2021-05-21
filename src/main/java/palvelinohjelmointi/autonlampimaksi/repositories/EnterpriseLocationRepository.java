package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.EnterpriseLocation;

public interface EnterpriseLocationRepository extends CrudRepository<EnterpriseLocation, Long> {
	List<EnterpriseLocation> findByEnterprise(Enterprise enterprise);
}
