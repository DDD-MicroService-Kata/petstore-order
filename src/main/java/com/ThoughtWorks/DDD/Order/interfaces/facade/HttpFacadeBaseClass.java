package com.ThoughtWorks.DDD.Order.interfaces.facade;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public class HttpFacadeBaseClass {
    ResponseEntity buildResponseEntity(URI location, HttpStatus noContent) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(headers, noContent);
    }
}
