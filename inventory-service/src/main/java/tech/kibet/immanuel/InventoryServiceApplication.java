package tech.kibet.immanuel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import tech.kibet.immanuel.model.Inventory;
import tech.kibet.immanuel.repository.InventoryRepo;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepo inventoryRepo){
        return args -> {
            Inventory inventory = new Inventory();
            inventory.setSkuCode("iphone_13");
            inventory.setQuantity(2);

            Inventory inventory2 = new Inventory();
            inventory2.setSkuCode("iphone_15");
            inventory2.setQuantity(1);

            Inventory inventory3 = new Inventory();
            inventory3.setSkuCode("iphone_11");
            inventory3.setQuantity(0);

            inventoryRepo.save(inventory2);
            inventoryRepo.save(inventory);
            inventoryRepo.save(inventory3);
        };
    }
}