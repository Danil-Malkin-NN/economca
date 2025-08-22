package ru.nn.dvm.core.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nn.dvm.core.entity.Spending;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.entity.User;
import ru.nn.dvm.core.repository.SpendingRepository;
import ru.nn.dvm.core.repository.TargetRepository;
import ru.nn.dvm.core.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.YearMonth;

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
        userRepository.save(new User(telegramId, username));
    }

    public void creteTarget(Long userId, Long amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTarget(new Target(amount, amount));
        userRepository.save(user);
    }

    public int getAvailableMoneyForDeny(Long userId) {
        Target byUserId = targetRepository.findByUserId(userId);
        return blackCubeService.getManeyForDay(byUserId);

    }


    @Transactional
    public long addSpending(Long userId, long amount) {
        Target byUserId = targetRepository.findByUserId(userId);
        byUserId.setResiduum(byUserId.getResiduum() - amount);
        targetRepository.save(byUserId);
        spendingRepository.save(new Spending(amount, LocalDateTime.now()));

        return getAvailableMoneyForDeny(userId);
    }

}
