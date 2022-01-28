package com.example.watchlistapi.controller;

import com.example.watchlistapi.model.Symbol;
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

    @DeleteMapping("/watchlists/{watchListId}")
    public WatchList deleteWatchList(@PathVariable Long watchListId) {
        return appService.deleteWatchlist(watchListId);
    }

    @PutMapping("/watchlists/{watchListId}")
    public WatchList updateWatchList(@PathVariable Long watchListId, @RequestBody WatchList watchListObject) {
        return appService.updateWatchList(watchListId, watchListObject);
    }

    @PostMapping("/watchlists/{watchListId}/symbols")
    public Symbol createSymbol(@PathVariable Long watchListId, @RequestBody Symbol symbolObject) {
        return appService.createSymbol(watchListId, symbolObject);
    }

    @GetMapping("/watchlists/{watchListId}/symbols/{symbolId}")
    public Symbol getSymbol(@PathVariable Long watchListId, @PathVariable Long symbolId) {
        return appService.getSymbol(watchListId, symbolId);
    }

    @GetMapping("/watchlists/{watchListId}/symbols")
    public List<Symbol> getAllSymbols(@PathVariable Long watchListId) {
        return appService.getAllSymbols(watchListId);
    }
}
