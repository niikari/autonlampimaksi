package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.EnterpriseSupplier;

@RepositoryRestResource
public interface EnterpriseSupplierRepository extends CrudRepository<EnterpriseSupplier, Long> {

}
