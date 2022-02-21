package org.efire.net.controller;

import lombok.AllArgsConstructor;
import org.efire.net.dto.CustomerDTO;
import org.efire.net.entity.Customer;
import org.efire.net.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public List<Customer> retrieveAllCustomer() {
        return customerService.getAll();
    }

    @PostMapping
    public Customer addNewCustomer() {
        Customer copyCust = customerService.getAnyCustomer();

        Customer newCustomer = new Customer();
        long c = customerService.getRecordCount();
        newCustomer.setName("CUST-"+ c);
        newCustomer.setCountry(copyCust.getCountry());
        newCustomer.setContactName(copyCust.getContactName());
        newCustomer.setAddress(copyCust.getAddress());
        newCustomer.setCity(copyCust.getCity());
        newCustomer.setPostalCode(copyCust.getPostalCode());

        return customerService.add(newCustomer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable String id, @RequestBody CustomerDTO customerDTO) {

        var customerById = new Customer();
        customerById.setId(id);
        customerById.setName(customerDTO.getName());
        customerById.setContactName(customerDTO.getContactName());
        customerById.setAddress(customerDTO.getAddress());
        customerById.setPostalCode(customerDTO.getPostalCode());
        customerById.setCity(customerDTO.getCity());
        customerById.setCountry(customerDTO.getCountry());
        return customerService.update(customerById);
    }
}
