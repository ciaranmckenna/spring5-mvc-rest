package guru.springfamework.api.v1.mapper;

import static org.junit.Assert.*;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import org.junit.Test;

public class CustomerMapperTest {

  public static final String FIRSTNAME = "BILL";
  public static final String LASTNAME = "JOEL";

  CustomerMapper customerMapper = CustomerMapper.INSTANCE;

  CustomerDTO customerDTO;

  @Test
  public void customerToCustomerDTO() {

    Customer customer = new Customer();
    customer.setFirstname(FIRSTNAME);
    customer.setLastname(LASTNAME);

    customerDTO = customerMapper.customerToCustomerDTO(customer);

    assertEquals(FIRSTNAME, customerDTO.getFirstname());
    assertEquals(LASTNAME, customerDTO.getLastname());
  }
}
