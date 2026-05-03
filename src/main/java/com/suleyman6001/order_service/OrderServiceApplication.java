package com.suleyman6001.order_service;

import com.suleyman6001.order_service.repository.OrderRepository;
import com.suleyman6001.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);

	}

	@Bean
	CommandLineRunner testRunner(OrderService orderService, OrderRepository orderRepository) {
		return args -> {
			/*System.out.println("Temporary test runner");

			/*Order order = new Order(Long.valueOf(5678), "Tea Pro SE IEM", 4, Status.PENDING, LocalDateTime.now());
			orderRepo.save(order);
			//List<Order> retrievedOrderS = orderRepo.findAllByCustomerId(Long.valueOf(5678));
			List<Order> retrievedOrders = orderRepo.findAllByStatus(Status.CONFIRMED);
			System.out.println(retrievedOrders);*/
		};
	}


}
