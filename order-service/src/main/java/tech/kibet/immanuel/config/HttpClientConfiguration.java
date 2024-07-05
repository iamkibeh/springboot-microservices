package tech.kibet.immanuel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import tech.kibet.immanuel.httpInterface.InventoryClient;

@Configuration
public class HttpClientConfiguration {

    private final LoadBalancedExchangeFilterFunction filterFunction;

    public HttpClientConfiguration(LoadBalancedExchangeFilterFunction filterFunction) {
        this.filterFunction = filterFunction;
    }

    @Bean
    @LoadBalanced
    InventoryClient inventoryClient(WebClient.Builder builder){
//        var wca = WebClientAdapter.create(builder.baseUrl("http://localhost:8083/api/v1/inventory").build());
//        return HttpServiceProxyFactory.builderFor(wca)
//                .build()
//                .createClient(InventoryClient.class);
//

        WebClient webClient = builder
                .baseUrl("http://inventory-service/api/v1/inventory")
                .filter(filterFunction)
                .build();

        HttpServiceProxyFactory httpServiceProxyFactory =
                HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
                        .build();

        return httpServiceProxyFactory.createClient(InventoryClient.class);
    }
}
