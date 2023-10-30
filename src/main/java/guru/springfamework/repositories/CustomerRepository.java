package guru.springfamework.repositories;

import guru.springfamework.domain.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Optional<Customer> findByfirstname(String firstname);

  Optional<Customer> findBylastname(String lastname);
}
