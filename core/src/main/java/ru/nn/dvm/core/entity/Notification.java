package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
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
