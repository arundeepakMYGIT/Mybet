package com.mybet.java.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "MARKET_TYPES")
public class MarketType {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marketTypeSeq")
    @SequenceGenerator(name = "marketTypeSeq", sequenceName = "market_type_id_seq", allocationSize = 1)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "NUMBER_OF_OUTCOMES")
    private int numberOfOutcomes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfOutcomes() {
        return numberOfOutcomes;
    }

    public void setNumberOfOutcomes(int numberOfOutcomes) {
        this.numberOfOutcomes = numberOfOutcomes;
    }
}
