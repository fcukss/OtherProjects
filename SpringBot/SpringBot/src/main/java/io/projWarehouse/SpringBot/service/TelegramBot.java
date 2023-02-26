package io.projWarehouse.SpringBot.service;

import io.projWarehouse.SpringBot.config.BotConfig;

import io.projWarehouse.SpringBot.model.User;
import io.projWarehouse.SpringBot.model.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    @Autowired
    ScopeService service = new ScopeService();
    @Autowired
    UserService userService = new UserService();

    final BotConfig config;

    static final String HELP_TEXT = "This bot is created for demo";

    public TelegramBot(BotConfig config) {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "register user"));
        listOfCommands.add(new BotCommand("/help", "info"));
        try {
            this.execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error("Error in list of commands" + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            switch (massageText) {
                case "/start":
                    userService.registerUser(update.getMessage());
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "all":
                    sendMessage(update, service.listOfAll().toString());
                    break;
                case "free":
                    sendMessage(update, service.listOfFreeScopes().toString());
                    break;
                case "/help":
                    sendMessage(chatId, HELP_TEXT);
                    break;
                default:
                    sendMessage(chatId, "not support");
            }
        }
    }



    private void startCommandReceived(long chatId, String name) {
        String answer = "Hello " + name + " !\n";
        String mess  = "What you like to see in database";
        log.info("Replied to user " + name);
        sendMessage(chatId, answer+mess);
    }


    private void sendMessage(long chatId, String textToSend) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        message.setReplyMarkup(addKeyboard());

        try {
            execute(message);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }

    }
    public void sendMessage(Update update, String text){
        try {
            if (update.hasMessage()) execute(
                    SendMessage.builder()
                            .chatId(update
                                    .getMessage()
                                    .getChatId())
                            .text(text)
                            .build()
            );
            else execute(
                    SendMessage.builder()
                            .chatId(update
                                    .getCallbackQuery()
                                    .getFrom()
                                    .getId())
                            .text(text)
                            .build()
            );
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    private ReplyKeyboard addKeyboard(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("free");
        row.add("sold");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("all");
        row.add("reserve");
        row.add("who sold");

        keyboardRows.add(row);

        keyboardMarkup.setKeyboard(keyboardRows);

        return keyboardMarkup;
    }

}
