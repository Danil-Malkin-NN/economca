package ru.nn.dvm.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.repository.TargetRepository;

import java.util.List;

@EnableScheduling
@Service
@RequiredArgsConstructor
public class RecalculateDayMoney {

    private final TargetRepository targetRepository;
    private final BlackCubeService blackCubeService;

    @Transactional
    @Scheduled(cron = "0 0 0 * * *")
    public void enabledSchedule() {
        recalculateDayMoney();
    }

//    Добавить отправку оповещений о новых лимитах
    public void recalculateDayMoney() {
        List<Target> all = targetRepository.findAll();
        for (Target target : all) {
            Long moneyForDay = blackCubeService.getMoneyForDay(target);
            target.setDayleResiduum(moneyForDay);
        }
        targetRepository.saveAll(all);

    }

}
