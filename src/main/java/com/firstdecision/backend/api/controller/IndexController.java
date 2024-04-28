package com.firstdecision.backend.api.controller;

import com.firstdecision.backend.domain.dto.IndexDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public IndexDTO get(){
        return new IndexDTO("API", "1.1.0");
    }
}
