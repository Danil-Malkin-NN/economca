package ru.nn.dvm.core.service.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.repository.TargetRepository;

import java.util.List;

@Slf4j
@Service
public class MonthUpdate {

    private final TargetRepository targetRepository;

    public MonthUpdate(TargetRepository targetRepository) {this.targetRepository = targetRepository;}

    @Scheduled(cron = "0 10 0 1 * *")
    public void monthUpdate() {
        List<Target> all = targetRepository.findAll();
        log.info("Обновляем счётчик денег на целевой");
        for (Target target : all) {
            target.setResiduum(target.getTarget());
        }

    }

}
