package com.example.watchlistapi.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="symbols")
public class Symbol {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String ticker;

    @Column(nullable = false)
    private String companyName;

    @ManyToMany(mappedBy = "symbols")
    private Set<WatchList> watchLists;

    public Symbol() {
    }

    public Symbol(long id, String ticker, String companyName) {
        this.id = id;
        this.ticker = ticker;
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Set<WatchList> getWatchLists() {
        return watchLists;
    }

    public void setWatchLists(Set<WatchList> watchLists) {
        this.watchLists = watchLists;
    }
}
