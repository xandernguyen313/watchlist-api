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

    @Column(unique = true)
    private String ticker;

    @Column
    private String companyName;

    @ManyToMany(mappedBy = "symbols")
    private Set<WatchList> watchLists;

    public Symbol() {
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
