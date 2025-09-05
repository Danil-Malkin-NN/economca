package ru.nn.dvm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.entity.TgUser;
import ru.nn.dvm.core.repository.SpendingRepository;
import ru.nn.dvm.core.repository.TargetRepository;
import ru.nn.dvm.core.repository.UserRepository;

import java.util.Optional;

@Service
public class EconomService {

    public EconomService(SpendingRepository spendingRepository, TargetRepository targetRepository,
                         UserRepository userRepository, BlackCubeService blackCubeService) {
        this.spendingRepository = spendingRepository;
        this.targetRepository = targetRepository;
        this.userRepository = userRepository;
        this.blackCubeService = blackCubeService;
    }

    private final SpendingRepository spendingRepository;
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;
    private final BlackCubeService blackCubeService;

    public void registerUser(Long telegramId, String username) {
        userRepository.save(new TgUser(telegramId, username));
    }

    public void creteTarget(Long userId, Long amount) {
        TgUser tgUser = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("TgUser not found"));
        tgUser.setTarget(new Target(amount, amount, tgUser, blackCubeService.getManeyForMonth(amount)));
        userRepository.save(tgUser);
    }

    public long getAvailableMoneyForDeny(Long userId) {
        Target byUserId = targetRepository.findByTgUserId(userId);
        return blackCubeService.getManeyForDay(byUserId);
    }

    @Transactional
    public long addSpending(Long userId, Spending spending) {
        TgUser byTelegramId = userRepository.findByTelegramId(userId)
                .orElseThrow(() -> new RuntimeException("NOT FOUND"));
        Target byUserId = byTelegramId.getTarget();
        byUserId.setDayleResiduum(byUserId.getDayleResiduum() - spending.getCount());
        targetRepository.save(byUserId);
        spendingRepository.save(spending);

        return byUserId.getDayleResiduum();
    }

}
