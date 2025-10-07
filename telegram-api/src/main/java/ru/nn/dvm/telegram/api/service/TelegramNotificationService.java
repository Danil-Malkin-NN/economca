package ru.nn.dvm.telegram.api.service;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import ru.nn.dvm.core.entity.Notification;
import ru.nn.dvm.core.repository.NotificationRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class TelegramNotificationService {

    private TaskScheduler taskScheduler;
    private NotificationRepository notificationRepository;

    public void addNotificationTask() {
        List<Notification> byDayliMoneyRecalculateNotificationTimeNotNull =
                notificationRepository.findByDayliMoneyRecalculateNotificationTimeNotNull();

    }

    private void scheduleTask(String taskName, LocalTime time, Runnable task) {
        Date executionTime = Date.from(LocalDateTime.of(LocalDate.now(), time)
                                               .atZone(ZoneId.systemDefault())
                                               .toInstant());

        taskScheduler.schedule(task, executionTime);

    }
}
