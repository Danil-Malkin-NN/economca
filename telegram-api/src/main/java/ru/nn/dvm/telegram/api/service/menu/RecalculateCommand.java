package ru.nn.dvm.telegram.api.service.menu;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.core.entity.Target;
import ru.nn.dvm.core.entity.TgUser;
import ru.nn.dvm.core.repository.UserRepository;
import ru.nn.dvm.core.service.BlackCubeService;
import ru.nn.dvm.telegram.api.service.steps.StepService;

@Service("/recalculate")
@RequiredArgsConstructor
public class RecalculateCommand implements StepService {

    private final BlackCubeService blackCubeService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public SendMessage process(Update update) {

        TgUser byTelegramId = userRepository.findByTelegramId(update.getMessage()
                                                                      .getFrom()
                                                                      .getId())
                .orElseThrow(() -> new NotFoundException());

        Target target = byTelegramId.getTarget();

        Long moneyForDay = blackCubeService.getMoneyForDay(target);
        target.setDayleResiduum(moneyForDay);

        return new SendMessage(update.getMessage()
                                       .getChatId()
                                       .toString(), "Дневной остаток %d".formatted(moneyForDay));
    }

}
