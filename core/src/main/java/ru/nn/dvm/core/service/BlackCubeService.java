package ru.nn.dvm.core.service;

import org.springframework.stereotype.Service;
import ru.nn.dvm.core.entity.Target;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.YearMonth;

@Service
public class BlackCubeService {

    private final Clock clock;

    public BlackCubeService(Clock clock) {
        this.clock = clock;
    }

    public BlackCubeService() {
        clock = Clock.systemDefaultZone(); // текущая зона сервера
    }

    public int getManeyForDay(Target byUserId) {

        LocalDateTime now = LocalDateTime.now(clock);
        YearMonth yearMonth = YearMonth.from(now);
        int daysInMonth = yearMonth.lengthOfMonth();

        long days = daysInMonth - now
                .getDayOfMonth();
        if (days == 0) {
            return (int) byUserId.getResiduum();
        }
        return BigDecimal.valueOf(byUserId.getResiduum())
                .divide(BigDecimal.valueOf(days), 0, RoundingMode.DOWN)
                .intValue();
    }
}
