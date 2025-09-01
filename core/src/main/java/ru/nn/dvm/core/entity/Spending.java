package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Entity
@Getter
@Service

public class Spending {

    @Id
    @GeneratedValue
    private Long id;

    private Long count;

    private String category;

    private LocalDateTime date;

    @ManyToOne
    private TgUser tgUser;

    public Spending() {

    }

    public Spending(String category) {
        this.category = category;
        this.date = LocalDateTime.now();
    }

}
