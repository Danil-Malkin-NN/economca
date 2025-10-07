package ru.nn.dvm.telegram.api.service.factory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nn.dvm.core.entity.Notification;
import ru.nn.dvm.core.entity.TgUser;

@Service
@RequiredArgsConstructor
public class NotificationTaskFactory {


    public Runnable createNotificationTask(final Notification notification) {
        return () -> {
//            Найти пользователя, найти чат где он пишет нам, сформировать сообщение пользователю о перерасчёте.
            TgUser tgUser = notification.getTgUser();


        };
    }

}
