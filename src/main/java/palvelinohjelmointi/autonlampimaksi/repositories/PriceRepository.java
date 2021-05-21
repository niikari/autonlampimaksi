package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Price;

public interface PriceRepository extends CrudRepository<Price, Long> {
	List<Price> findByEnterprise(Enterprise enterprise);

}
