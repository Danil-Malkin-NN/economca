package ru.nn.dvm.core.service;

import org.junit.jupiter.api.Test;
import ru.nn.dvm.core.entity.Target;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackCubeServiceTest {

    BlackCubeService blackCubeService = new BlackCubeService();

    @Test
    public void test() {

        LocalDate fixedDate = LocalDate.of(2024, 3, 10);
        Clock fixedClock = Clock.fixed(fixedDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                                       ZoneId.systemDefault());

        BlackCubeService blackCubeService = new BlackCubeService(fixedClock);


        Target target = new Target();
        target.setResiduum(310); // например

        long result = blackCubeService.getMoneyForDay(target);

        // В марте 31 день, сейчас 10 число → осталось 21 день
        // 310 / 21 ≈ 14
        assertThat(result).isEqualTo(14L);

    }

}