package ru.nn.dvm.core.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TgUser {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true, nullable = false)
    private Long telegramId;

    private String userName;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tgUser")
    private Target target;

    public TgUser(Long telegramId, String userName) {
        this.telegramId = telegramId;
        this.userName = userName;
    }

    public TgUser() {

    }

}
