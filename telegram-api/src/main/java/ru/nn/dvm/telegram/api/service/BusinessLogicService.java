package ru.nn.dvm.telegram.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.nn.dvm.core.service.EconomService;
import ru.nn.dvm.telegram.api.service.steps.StepService;

@Service
@RequiredArgsConstructor
public class BusinessLogicService {

	private final EconomService economService;
	private final StepAggregator aggregator;

	public SendMessage processAction(Update update) {

		StepService service = aggregator.getStepProcessor(update);

		SendMessage message = service.process(update);


		return message;
	}
}
