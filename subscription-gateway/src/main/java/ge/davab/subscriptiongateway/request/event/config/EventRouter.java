package ge.davab.subscriptiongateway.request.event.config;

import ge.davab.subscriptiongateway.request.event.handler.EventRouteHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class EventRouter {

    @Bean
    public RouterFunction<ServerResponse> getEventsRoute(EventRouteHandler eventRouteHandler) {
        return RouterFunctions.route(
                GET("/api/events"),
                eventRouteHandler::getEvents
        );
    }

}
