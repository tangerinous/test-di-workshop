package ru.yandex.workshop.testing.config;

import java.time.Clock;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ru.yandex.workshop.testing.customer.Customer;
import ru.yandex.workshop.testing.customer.CustomerRepository;

@Configuration
public class DataInitializer {

    @Bean
    Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    CommandLineRunner seedCustomers(CustomerRepository customerRepository) {
        return args -> {
            if (customerRepository.count() > 0) {
                return;
            }

            customerRepository.saveAll(List.of(
                new Customer(null, "anna@example.com", "Anna", true, "NEW", 1),
                new Customer(null, "boris@example.com", "Boris", true, "REGULAR", 2),
                new Customer(null, "irina@example.com", "Irina", true, "VIP", 3),
                new Customer(null, "oleg@example.com", "Oleg", false, "REGULAR", 2)
            ));
        };
    }
}
