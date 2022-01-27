package com.example.watchlistapi.repository;

import com.example.watchlistapi.model.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SymbolRepository extends JpaRepository<Symbol, Long> {
}
