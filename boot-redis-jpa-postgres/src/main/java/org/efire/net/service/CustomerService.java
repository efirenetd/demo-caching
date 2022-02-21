package org.efire.net.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.efire.net.entity.Customer;
import org.efire.net.repository.CustomerRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Cacheable(cacheNames = "customerCache")
    public List<Customer> getAll() {
        waitSomeTime();
        return customerRepository.findAll();
    }

    @CacheEvict(cacheNames = "customerCache", allEntries = true)
    public Customer add(Customer customer) {
        return customerRepository.save(customer);
    }
    
    public long getRecordCount() {
        return customerRepository.count();
    }
    
    public Customer getAnyCustomer() {
        int c = (int) getRecordCount() - 1;
        return customerRepository.findAll().get(c);
    }

    @CacheEvict(cacheNames = "customerCache", allEntries = true)
    public Customer update(Customer customer) {
        var existingCustomer = customerRepository.findById(customer.getId()).orElseThrow(RuntimeException::new);

        existingCustomer.setName(customer.getName());
        existingCustomer.setContactName(customer.getContactName());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPostalCode(customer.getPostalCode());
        existingCustomer.setCity(customer.getCity());
        existingCustomer.setCountry(customer.getCountry());

        return customerRepository.save(existingCustomer);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    //Simulate delay process
    private void waitSomeTime() {
        log.warn("Long Wait Begin");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        log.warn("Long Wait End");
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}
