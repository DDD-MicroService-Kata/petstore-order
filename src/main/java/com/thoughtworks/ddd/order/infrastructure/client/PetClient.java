package com.thoughtworks.ddd.order.infrastructure.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="pet")
public interface PetClient {
    @RequestMapping(value = "/api/pets/status", method = RequestMethod.PUT)
    void changeStatus(@RequestBody PetStatusChanged petStatus);
}