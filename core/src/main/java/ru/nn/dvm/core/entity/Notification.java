package ru.nn.dvm.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

/**
 * Настройки уведомления пользователя (время ежедневного напоминания о пересчёте дневного лимита).
 */
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
