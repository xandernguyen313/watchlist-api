package com.example.watchlistapi.service;

import com.example.watchlistapi.exception.InformationExistException;
import com.example.watchlistapi.exception.NotFoundException;
import com.example.watchlistapi.model.WatchList;

import com.example.watchlistapi.repository.SymbolRepository;
import com.example.watchlistapi.repository.WatchListRepository;
import com.example.watchlistapi.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public MyUserDetails getUserDetails() {
        return (MyUserDetails)SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
    }

    public List<WatchList> getAllWatchLists() {
        MyUserDetails userDetails = getUserDetails();
        List<WatchList> watchLists = watchListRepository.findByUserId(userDetails.getUser().getId());
        if(watchLists.isEmpty()) {
            throw new NotFoundException("There are no watchlists associated with " + userDetails.getUser().getEmailAddress());
        } else {
            return watchLists;
        }
    }

    public WatchList createWatchList(WatchList watchListObject) {
        MyUserDetails userDetails = getUserDetails();
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


    public WatchList getWatchList(Long watchListId) {
        MyUserDetails userDetails = getUserDetails();
        WatchList watchList = watchListRepository.findByUserIdAndId(userDetails.getUser().getId(), watchListId);
        if(watchList == null) {
            throw new NotFoundException("The watchlist with the id of " + watchListId + " does not exist");
        } else {
            return watchList;
        }
    }

    public WatchList deleteWatchlist(Long watchListId){
        MyUserDetails userDetails = getUserDetails();
        WatchList watchList = watchListRepository.findByUserIdAndId(userDetails.getUser().getId(), watchListId);
        if(watchList == null){
            throw new NotFoundException("The watchlist with the id of " + watchListId + " does not exist");
        } else {
            watchListRepository.deleteById(watchListId);
            return watchList;
        }
    }

    public WatchList updateWatchList(Long watchListId, WatchList watchListObject) {
        MyUserDetails userDetails = getUserDetails();
        WatchList watchList = watchListRepository.findByUserIdAndId(userDetails.getUser().getId(), watchListId);
        if(watchList == null) {
            throw new NotFoundException("The watchlist with the id of " + watchListId + " does not exist");
        } else {
            watchList.setName(watchListObject.getName());
            return watchListRepository.save(watchList);
        }
    }


}
