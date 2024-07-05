package tech.kibet.immanuel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.kibet.immanuel.dto.InventoryResponse;
import tech.kibet.immanuel.dto.OrderLineItemDto;
import tech.kibet.immanuel.dto.OrderRequest;
import tech.kibet.immanuel.httpInterface.InventoryClient;
import tech.kibet.immanuel.model.Order;
import tech.kibet.immanuel.model.OrderLineItem;
import tech.kibet.immanuel.repository.OrderRepo;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepo orderRepo;
    public final InventoryClient inventoryClient;
    public final EventService eventService;

    public void placeOrder(OrderRequest orderRequest) {
        List<String> skuCodes = orderRequest.getOrderLineItemDtoList().stream().map(OrderLineItemDto::getSkuCode).toList();
//        make http request call to inventory service and place order if product is in stock
        boolean allProductsInStock = inventoryClient.isInStock(skuCodes).stream().allMatch(InventoryResponse::getIsInStock);

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItemDtoList().stream().map(this::mapDtoToOrderLineItems).toList();
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderLineItems)
                .build();
        if (allProductsInStock) {
            orderRepo.save(order);
            eventService.sendNotification(order.getOrderNumber());
        } else {
            throw new IllegalArgumentException("Oops! It seems the product you're trying to order is currently out of stock. Please try again later.");
        }
    }

    private OrderLineItem mapDtoToOrderLineItems(OrderLineItemDto orderLineItemDto) {
        return OrderLineItem.builder()
                .id(orderLineItemDto.getId())
                .skuCode(orderLineItemDto.getSkuCode())
                .price(orderLineItemDto.getPrice())
                .quantity(orderLineItemDto.getQuantity())
                .build();
    }
}
