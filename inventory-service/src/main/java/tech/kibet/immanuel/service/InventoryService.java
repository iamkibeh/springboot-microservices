package tech.kibet.immanuel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.kibet.immanuel.dto.InventoryResponse;
import tech.kibet.immanuel.model.Inventory;
import tech.kibet.immanuel.repository.InventoryRepo;

import java.util.List;


@RequiredArgsConstructor
@Service
@Slf4j
public class InventoryService {
    private final InventoryRepo inventoryRepo;

    public List<InventoryResponse> isInStock(List<String> skuCodes){
        log.info("in inventory service -> {}", skuCodes);
        log.info("inventoryRepo findBySkuCodeIn(skuCodes) -> {}", inventoryRepo.findBySkuCodeIn(skuCodes));
        return inventoryRepo.findBySkuCodeIn(skuCodes).stream()
                .map(this::mapToInventory).toList();
    }

    private InventoryResponse mapToInventory(Inventory inventory) {
        return  InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
