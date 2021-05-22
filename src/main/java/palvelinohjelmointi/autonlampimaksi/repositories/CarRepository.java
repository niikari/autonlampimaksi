package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
	Car findCarByPlate(String plate);
}
