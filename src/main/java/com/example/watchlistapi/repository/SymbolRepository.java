package com.example.watchlistapi.repository;

import com.example.watchlistapi.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {

    Symbol findByTickerIgnoreCase(String tickerName);
    List<Symbol> findByWatchListsId(Long watchListId);
}
