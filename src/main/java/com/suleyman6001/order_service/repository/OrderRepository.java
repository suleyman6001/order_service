package com.suleyman6001.order_service.repository;

import com.suleyman6001.order_service.entity.Order;
import com.suleyman6001.order_service.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Boolean existsByProductCode(String productCode);
    List<Order> findAllByProductCode(String productCode);
    List<Order> findAllByCustomerId(String customerId);
    List<Order> findAllByStatus(Status status);
    List<Order> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
