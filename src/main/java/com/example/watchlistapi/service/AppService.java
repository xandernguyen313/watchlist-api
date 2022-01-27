package com.example.watchlistapi.service;

import com.example.watchlistapi.repository.SymbolRepository;
import com.example.watchlistapi.repository.WatchListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private SymbolRepository symbolRepository;
    private WatchListRepository watchListRepository;

    @Autowired
    public void setSymbolRepository(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    @Autowired
    public void setWatchListRepository(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }


}
