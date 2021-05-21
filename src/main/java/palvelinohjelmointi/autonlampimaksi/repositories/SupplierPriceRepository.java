package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.SupplierPrice;

@RepositoryRestResource
public interface SupplierPriceRepository extends CrudRepository<SupplierPrice, Long> {

}
