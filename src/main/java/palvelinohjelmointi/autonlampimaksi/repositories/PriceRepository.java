package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Price;

@RepositoryRestResource
public interface PriceRepository extends CrudRepository<Price, Long> {
	List<Price> findByEnterprise(Enterprise enterprise);

}
