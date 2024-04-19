package org.example.cloud.handler.predicate;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyPredicateRoutePredicateFactory extends AbstractRoutePredicateFactory<MyPredicateRoutePredicateFactory.Config> {
    public static final String USERTYPE_KEY = "userType";
    public MyPredicateRoutePredicateFactory() {
        super(MyPredicateRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(USERTYPE_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(MyPredicateRoutePredicateFactory.Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (userType == null) {
                    return false;
                }
                if (userType.equalsIgnoreCase(config.getUserType())) {
                    return true;
                }
                return false;
            }
        };
    }

    @Validated
    public static class Config {
        @Getter
        @Setter
        @NotEmpty
        private String userType;
    }
}
