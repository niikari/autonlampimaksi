package palvelinohjelmointi.autonlampimaksi.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import palvelinohjelmointi.autonlampimaksi.models.Enterprise;
import palvelinohjelmointi.autonlampimaksi.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findUserByUsername(String username);
	List<User> findByEnterprise(Enterprise enterprise);
}
