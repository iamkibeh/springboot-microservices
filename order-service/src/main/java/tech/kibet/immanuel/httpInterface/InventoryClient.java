package tech.kibet.immanuel.httpInterface;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import tech.kibet.immanuel.dto.InventoryResponse;

import java.util.List;

public interface InventoryClient {
    @GetExchange
    List<InventoryResponse> isInStock(@RequestParam("skuCode") List<String> skuCodes);
}
