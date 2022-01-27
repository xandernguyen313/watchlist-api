package com.example.watchlistapi.repository;

import com.example.watchlistapi.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepository extends JpaRepository<WatchList, Long> {
    WatchList findByUserIdAndNameIgnoreCase(Long userId, String watchListName);
}
