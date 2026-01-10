package com.jsrdev.medapi.infrastructure.rest.controller;

import com.jsrdev.medapi.infrastructure.rest.dto.CreatePhysicianRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/physicians")
public class PhysicianController {
    @PostMapping
    public void create(@RequestBody CreatePhysicianRequest createPhysicianRequest) {
        System.out.println(createPhysicianRequest);
    }
}
