package ge.davab.subscriptionservice.subscription.controller.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriBuilder {

    private final UriComponentsBuilder uriComponentsBuilder =
            UriComponentsBuilder.newInstance();

    public UriBuilder(HttpServletRequest httpRequest) {
        uriComponentsBuilder
                .scheme(httpRequest.getScheme())
                .host(httpRequest.getServerName())
                .port(httpRequest.getServerPort())
                .pathSegment(httpRequest.getRequestURI().split("/"));
    }

    public UriBuilder setPathSegment(String segment) {
        uriComponentsBuilder
                .pathSegment(segment);
        return this;
    }

    public UriBuilder setPathVariable(String name, String value) {
        uriComponentsBuilder
                .queryParam(name, value);
        return this;
    }

    public URI build() {
        return uriComponentsBuilder.build().toUri();
    }
}
