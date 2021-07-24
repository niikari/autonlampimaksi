package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Car;
import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.Offer;

public interface OfferRepository extends CrudRepository<Offer, Long> {
	Offer findByCarAndEnterprise(Car car, Enterprise enterprise);
}
