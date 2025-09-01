package ru.nn.dvm.telegram.api.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

@Service
public class NextStepService {

    private final Map<Long, Queue<String>> nextStep = new HashMap<>();

    public void setNextStep(Long id, String service) {
        if (nextStep.containsKey(id)) {
            nextStep.get(id)
                    .add(service);
        }

        LinkedList<String> que = new LinkedList<>();
        que.add(service);
        nextStep.put(id, que);
    }

    public boolean containsKey(Long telegramId) {
        Queue<String> strings = nextStep.get(telegramId);
        if(strings == null) {
            return false;
        }
        return !strings.isEmpty();
    }

    public String get(Long aLong) {
        return nextStep.get(aLong)
                .peek();
    }
}
