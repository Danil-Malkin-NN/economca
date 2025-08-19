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

@Service
public class EconomService {

    public EconomService(SpendingRepository spendingRepository, TargetRepository targetRepository,
                         UserRepository userRepository) {
        this.spendingRepository = spendingRepository;
        this.targetRepository = targetRepository;
        this.userRepository = userRepository;
    }

    private final SpendingRepository spendingRepository;
    private final TargetRepository targetRepository;
    private final UserRepository userRepository;

    public void registerUser(String telegramId) {
        userRepository.save(new User(telegramId));
    }

    public void creteTarget(Long userId, Long amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setTarget(new Target(amount, amount));
        userRepository.save(user);
    }

    public int getAvailableMoneyForDeny(Long userId) {
        Target byUserId = targetRepository.findByUserId(userId);
        long dayOfMonth = LocalDateTime.now()
                .getDayOfMonth();
        return BigDecimal.valueOf(byUserId.getResiduum())
                .divide(BigDecimal.valueOf(dayOfMonth))
                .intValue();

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
