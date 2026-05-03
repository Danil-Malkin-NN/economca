package ru.nn.dvm.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nn.dvm.core.entity.Notification;

import java.util.List;

/**
 * Доступ к {@link Notification}, в том числе с заданным временем ежедневного уведомления.
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByDayliMoneyRecalculateNotificationTimeNotNull();

}
