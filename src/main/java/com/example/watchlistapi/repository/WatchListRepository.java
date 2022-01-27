package com.example.watchlistapi.repository;

import com.example.watchlistapi.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchListRepository extends JpaRepository<WatchList, Long> {

}
