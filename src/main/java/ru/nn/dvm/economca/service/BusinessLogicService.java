package ru.nn.dvm.economca.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.economca.service.steps.StepService;

@Service
@RequiredArgsConstructor
public class BusinessLogicService {

	private final StepAggregator aggregator;

	public SendMessage processAction(Update update) {

		StepService service = aggregator.getStepProcessor(update);

		return service.process(update);
	}
}
