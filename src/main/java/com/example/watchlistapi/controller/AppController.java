package com.example.watchlistapi.controller;

import com.example.watchlistapi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class AppController {
    private AppService appService;

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }



}
