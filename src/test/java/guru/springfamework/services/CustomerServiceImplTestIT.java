package guru.springfamework.services;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.bootstrap.Bootstrap;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CategoryRepository;
import guru.springfamework.repositories.CustomerRepository;
import guru.springfamework.repositories.VendorRepository;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceImplTestIT {

  public static final String UPDATED_NAME = "UpdatedName";
  @Autowired CustomerRepository customerRepository;
  @Autowired CategoryRepository categoryRepository;
  @Autowired VendorRepository vendorRepository;

  CustomerService customerService;

  @Before
  public void setUp() throws Exception {
    System.out.println("Loading Customer Data");
    System.out.println("Customer Data count is:" + categoryRepository.findAll().size());

    // setup data for testing
    Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository, vendorRepository);
    bootstrap.run();

    customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
  }

  @Test
  public void patchCustomerUpdateFirstName() {
    String updatedName = UPDATED_NAME;
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // save original first name
    String originalFirstName = originalCustomer.getFirstname();
    String originalLastName = originalCustomer.getLastname();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setFirstname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getFirstname());
    assertThat(originalFirstName, not(equalTo(updatedCustomer.getFirstname())));
    assertThat(originalLastName, equalTo(updatedCustomer.getLastname()));
  }

  @Test
  public void patchCustomerUpdateLastName() {
    String updatedName = UPDATED_NAME;
    long id = getCustomerIdValue();

    Customer originalCustomer = customerRepository.getOne(id);
    assertNotNull(originalCustomer);

    // save original name
    String originalFirstName = originalCustomer.getFirstname();
    String originalLastName = originalCustomer.getLastname();

    CustomerDTO customerDTO = new CustomerDTO();
    customerDTO.setLastname(updatedName);

    customerService.patchCustomer(id, customerDTO);

    Customer updatedCustomer = customerRepository.findById(id).get();

    assertNotNull(updatedCustomer);
    assertEquals(updatedName, updatedCustomer.getLastname());
    assertThat(originalLastName, not(equalTo(updatedCustomer.getLastname())));
    assertThat(originalFirstName, equalTo(updatedCustomer.getFirstname()));
  }

  private Long getCustomerIdValue() {
    List<Customer> customers = customerRepository.findAll();

    System.out.println("Customers Found: " + customers.size());

    // returning first id
    return customers.get(0).getId();
  }
}
