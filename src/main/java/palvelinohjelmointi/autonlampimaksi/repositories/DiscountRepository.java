package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Discount;

public interface DiscountRepository extends CrudRepository<Discount, Long> {

}
