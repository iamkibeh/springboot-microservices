package tech.kibet.immanuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibet.immanuel.model.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
