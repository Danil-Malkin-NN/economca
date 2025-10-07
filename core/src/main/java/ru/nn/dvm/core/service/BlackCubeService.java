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

    public Long getMoneyForDay(Target byUserId) {
        return getManeyForMonth(byUserId.getResiduum());
    }

    private long getDays() {
        LocalDateTime now = LocalDateTime.now(clock);
        YearMonth yearMonth = YearMonth.from(now);
        int daysInMonth = yearMonth.lengthOfMonth();

        long days = daysInMonth - now
                .getDayOfMonth();
        return days;
    }

    public long getManeyForMonth(Long targetCount) {
        long days = getDays();
        if(days == 0) {
            return targetCount;
        }

        return BigDecimal.valueOf(targetCount)
                .divide(BigDecimal.valueOf(days), 0, RoundingMode.DOWN)
                .intValue();

    }
}
