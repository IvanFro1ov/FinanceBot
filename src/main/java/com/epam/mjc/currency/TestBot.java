package com.epam.mjc.currency;

import com.epam.mjc.currency.entity.Currency;
import com.epam.mjc.currency.service.CurrencyConversionService;
import com.epam.mjc.currency.service.CurrencyModeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TestBot extends TelegramLongPollingBot {

  private final CurrencyModeService currencyModeService = CurrencyModeService.getInstance();
  private final CurrencyConversionService currencyConversionService =
      CurrencyConversionService.getInstance();

  @Override
  @SneakyThrows
  public void onUpdateReceived(Update update) {
    if (update.hasCallbackQuery()) {
      handleCallback(update.getCallbackQuery());
    } else if (update.hasMessage()) {
      handleMessage(update.getMessage());
    }
  }

  

  private Optional<Double> parseDouble(String messageText) {
    try {
      return Optional.of(Double.parseDouble(messageText));
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  private String getCurrencyButton(Currency saved, Currency current) {
    return saved == current ? current + " ✅" : current.name();
  }

  @Override
  public String getBotUsername() {
    return "@GandalfWizardbot";
  }

  @Override
  public String getBotToken() {
    return "5472585973:AAGn_FD2FPwVNULLEMBXIdFXhjh9RebziWU";
  }

  @SneakyThrows
  public static void main(String[] args) {
    TestBot bot = new TestBot();
    TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
    telegramBotsApi.registerBot(bot);
  }
}
