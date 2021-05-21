package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;

public interface EnterpriseRepository extends CrudRepository<Enterprise, Long> {
	Enterprise findByName(String name);

}
