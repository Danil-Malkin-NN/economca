package ru.nn.dvm.telegram.api.service.storage;

import org.springframework.stereotype.Service;
import ru.nn.dvm.core.entity.Spending;

import java.util.HashMap;
import java.util.Map;

/**
 * В памяти хранит незавершённую трату {@link ru.nn.dvm.core.entity.Spending} по {@code telegramId} между шагами сценария.
 */
@Service
public class SaleBuffer {

    private final Map<Long, Spending> spendings = new HashMap<>();

    public Map<Long, Spending> getSpendings() {
        return spendings;
    }
}
