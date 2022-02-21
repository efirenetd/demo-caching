package org.efire.net;

import org.efire.net.entity.Customer;
import org.efire.net.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootRedisJpaPostgresApplication implements CommandLineRunner {

	@Autowired
	private CustomerService customerService;

	public static void main(String[] args) {
		SpringApplication.run(BootRedisJpaPostgresApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		customerService.deleteAll();

		for (int i = 0; i < 10; i++) {
			var customer = new Customer();
			customer.setName("CUST-"+i);
			customer.setContactName("XXXXX");
			customer.setAddress("XYZ");
			customer.setPostalCode("123");
			customer.setCity("TX");
			customer.setCountry("US");

			customerService.add(customer);
		}
	}
}
