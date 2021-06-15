package palvelinohjelmointi.autonlampimaksi.repositories;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.CustomerContact;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

}
