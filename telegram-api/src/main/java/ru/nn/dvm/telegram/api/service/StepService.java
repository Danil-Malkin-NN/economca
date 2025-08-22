package ru.nn.dvm.telegram.api.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface StepService {

	public SendMessage sendMessage(String chatId, String text);

}
