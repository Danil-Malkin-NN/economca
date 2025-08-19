package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

@Entity
public class Spending {

    @Id
    @GeneratedValue
    private Long id;

    private Long count;

    private LocalDateTime date;

    @ManyToOne
    private User user;

    public Spending() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Spending(Long count, LocalDateTime date) {
        this.count = count;
        this.date = date;
    }

}
