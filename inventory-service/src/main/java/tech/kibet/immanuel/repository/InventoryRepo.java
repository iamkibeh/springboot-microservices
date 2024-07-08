package tech.kibet.immanuel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.kibet.immanuel.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
