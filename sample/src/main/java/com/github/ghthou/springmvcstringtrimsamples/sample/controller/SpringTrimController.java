package com.github.ghthou.springmvcstringtrimsamples.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.ghthou.springmvcstringtrimsamples.sample.domain.User;

@RestController
public class SpringTrimController {

    @GetMapping("/url")
    public String urlParam(String name) {
        return name;
    }

    @PostMapping("/form")
    public User formParam(User u) {
        return u;
    }

    @PostMapping(value = "/body")
    public User bodyParam(@RequestBody User u) {
        return u;
    }
}
