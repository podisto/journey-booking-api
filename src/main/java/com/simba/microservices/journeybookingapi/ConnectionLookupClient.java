package com.simba.microservices.journeybookingapi;

import com.simba.microservices.dto.Connections;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "connections", fallback = ConnectionLookupClient.ConnectionLookupFallback.class)
public interface ConnectionLookupClient {

    @GetMapping(value = "/connections", produces = MediaType.APPLICATION_JSON_VALUE)
    Connections getConnections(@RequestParam("from") String from, @RequestParam("to") String to);

    class ConnectionLookupFallback implements ConnectionLookupClient {
        @Override
        public Connections getConnections(String from, String to) {
            return new Connections();
        }
    }
}
