package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.Discount;

@RepositoryRestResource
public interface DiscountRepository extends CrudRepository<Discount, Long> {

}
