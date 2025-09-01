package ru.nn.dvm.economca.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nn.dvm.economca.entity.Spending;
import ru.nn.dvm.economca.entity.Target;
import ru.nn.dvm.economca.entity.TgUser;
import ru.nn.dvm.economca.repository.SpendingRepository;
import ru.nn.dvm.economca.repository.TargetRepository;
import ru.nn.dvm.economca.repository.UserRepository;

import java.time.LocalDateTime;

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
        tgUser.setTarget(new Target(amount, amount));
        userRepository.save(tgUser);
    }

    public int getAvailableMoneyForDeny(Long userId) {
        Target byUserId = targetRepository.findByTgUserId(userId);
        return blackCubeService.getManeyForDay(byUserId);

    }


    @Transactional
    public long addSpending(Long userId, long amount) {
        Target byUserId = targetRepository.findByTgUserId(userId);
        byUserId.setResiduum(byUserId.getResiduum() - amount);
        targetRepository.save(byUserId);
        spendingRepository.save(new Spending(amount, LocalDateTime.now()));

        return getAvailableMoneyForDeny(userId);
    }

}
