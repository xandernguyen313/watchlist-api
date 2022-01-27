package com.example.watchlistapi.controller;

import com.example.watchlistapi.model.WatchList;
import com.example.watchlistapi.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AppController {
    private AppService appService;

    @Autowired
    public void setAppService(AppService appService) {
        this.appService = appService;
    }



    @GetMapping("/watchlists")
    public List<WatchList> getAllWatchLists() {
        return appService.getAllWatchLists();
    }

    @PostMapping("/watchlists")
    public WatchList createWatchList(@RequestBody WatchList watchListObject) {
        return appService.createWatchList(watchListObject);
    }

    @GetMapping("/watchlists/{watchListId}")
    public WatchList getWatchList(@PathVariable Long watchListId) {
        return appService.getWatchList(watchListId);
    }
}
