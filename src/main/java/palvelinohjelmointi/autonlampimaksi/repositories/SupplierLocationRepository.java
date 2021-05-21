package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.SupplierLocation;

@RepositoryRestResource
public interface SupplierLocationRepository extends CrudRepository<SupplierLocation, Long> {

}
