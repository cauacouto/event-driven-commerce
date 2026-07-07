package couto.dev.order_service.repository;

import couto.dev.order_service.domin.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity,UUID> {
}
