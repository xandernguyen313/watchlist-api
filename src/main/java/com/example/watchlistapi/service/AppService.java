package com.example.watchlistapi.service;

import com.example.watchlistapi.exception.InformationExistException;
import com.example.watchlistapi.model.WatchList;

import com.example.watchlistapi.repository.SymbolRepository;
import com.example.watchlistapi.repository.WatchListRepository;
import com.example.watchlistapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public WatchList createWatchList(WatchList watchListObject) {
        MyUserDetails userDetails = (MyUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        WatchList watchList = watchListRepository
                .findByUserIdAndNameIgnoreCase(userDetails.getUser().getId(), watchListObject.getName());
        if(watchList != null) {
            throw new InformationExistException("The watchlist with the name "
                    + watchListObject.getName() + " already exists");
        } else {
            watchListObject.setUser(userDetails.getUser());
            return watchListRepository.save(watchListObject);
        }
    }
}
