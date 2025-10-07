package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue
    private Long id;

    private LocalTime dayliMoneyRecalculateNotificationTime;

    @OneToOne
    private TgUser tgUser;


    public Notification(LocalTime  dayliMoneyRecalculateNotification, TgUser tgUser) {
        this.dayliMoneyRecalculateNotificationTime = dayliMoneyRecalculateNotification;
        this.tgUser = tgUser;
    }
}
