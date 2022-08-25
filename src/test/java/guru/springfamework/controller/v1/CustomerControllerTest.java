package guru.springfamework.controller.v1;

import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static guru.springfamework.controller.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest {

    public static final String FIRST_NAME = "Paidi";
    public static final String LAST_NAME = "OSe";
    public static final Long ID = 1L;

    MockMvc mockMvc;
    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {
        //given
        List<CustomerDTO> customers = Arrays.asList(new CustomerDTO(), new CustomerDTO(),new CustomerDTO());

        when(customerService.getAllCustomers()).thenReturn(customers);

        //then
        mockMvc.perform(get("/api/v1/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));
    }

    @Test
    public void getCustomerByFirstName() throws Exception {
        CustomerDTO customerDTO = getDto(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerByFirstName(FIRST_NAME)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/firstname/Paidi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)));

    }

    @Test
    public void getCustomerByLastName() throws Exception {
        CustomerDTO customerDTO = getDto(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerByLastName(LAST_NAME)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/lastname/OSe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)));
    }

    @Test
    public void getCustomerById() throws Exception {
        CustomerDTO customerDTO = getDto(FIRST_NAME, LAST_NAME);

        when(customerService.getCustomerById(ID)).thenReturn(customerDTO);

        //then
        mockMvc.perform(get("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = getDto(FIRST_NAME, LAST_NAME);

        CustomerDTO returnDTO = getDto(customerDTO.getFirstname(), customerDTO.getLastname());
        returnDTO.setCustomerUrl("/api/v1/customer/1");

        when(customerService.createNewCustomer(customerDTO)).thenReturn(returnDTO);

        mockMvc.perform(post("/api/v1/customers/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(customerDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.lastname", equalTo(LAST_NAME)))
                .andExpect(jsonPath("$.customerUrl", equalTo("/api/v1/customer/1")));
    }

    private static CustomerDTO getDto(String firstName, String lastName) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(firstName);
        customerDTO.setLastname(lastName);
        return customerDTO;
    }

}