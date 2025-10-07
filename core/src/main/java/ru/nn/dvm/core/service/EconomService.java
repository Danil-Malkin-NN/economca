package ru.nn.dvm.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nn.dvm.core.entity.Notification;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.entity.TgUser;
import ru.nn.dvm.core.repository.NotificationRepository;
import ru.nn.dvm.core.repository.SpendingRepository;
import ru.nn.dvm.core.repository.TargetRepository;
import ru.nn.dvm.core.repository.UserRepository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class EconomService {

    private final SpendingRepository spendingRepository;
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;
    private final BlackCubeService blackCubeService;
    private final NotificationRepository notificationRepository;

    public void registerUser(Long telegramId, String username) {
        userRepository.save(new TgUser(telegramId, username));
    }

    public Target getTarget(Long telegramId) {
        return targetRepository.findByTgUser_TelegramId(telegramId);
    }

    @Transactional
    public void creteTarget(Long userId, Long amount) {
        TgUser tgUser = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("TgUser not found"));
        tgUser.setTarget(new Target(amount, amount, tgUser, blackCubeService.getManeyForMonth(amount)));
        userRepository.save(tgUser);

        Notification defaultNotification = new Notification(LocalTime.of(9, 0), tgUser);
        notificationRepository.save(defaultNotification);
    }

    public long getAvailableMoneyForDeny(Long userId) {
        Target byUserId = targetRepository.findByTgUser_TelegramId(userId);
        return blackCubeService.getMoneyForDay(byUserId);
    }

    @Transactional
    public long addSpending(Long userId, Spending spending) {
        TgUser byTelegramId = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("NOT FOUND"));
        Target byUserId = byTelegramId.getTarget();
        byUserId.setDayleResiduum(byUserId.getDayleResiduum() - spending.getCount());
        byUserId.setResiduum(byUserId.getResiduum() - spending.getCount());
        targetRepository.save(byUserId);
        spendingRepository.save(spending);

        return byUserId.getDayleResiduum();
    }

}
