package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Supplier;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
	Supplier findByName(String name);
}
