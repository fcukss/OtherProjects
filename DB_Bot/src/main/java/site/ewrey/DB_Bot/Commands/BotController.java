package site.ewrey.DB_Bot.Commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import site.ewrey.DB_Bot.JPA.UserService;


public class BotController extends TelegramLongPollingBot {

    @Autowired
    UserService service = new UserService();
    @Override
    public String getBotUsername() {
        return "warehouse";
    }
    @Override
    public String getBotToken() {
        return "6179339795:AAGnoMapJtGLW2F09ONF9GW-Bx4U0CppGdg";
    }
    @Async
    @Override
    public void onUpdateReceived(Update update) {
 //      long chatId = update.getMessage().getChatId();
        boolean hasText = update.hasMessage() && update.getMessage().hasText();
        String command = update.getMessage().getText();

            if (hasText && command.equals("/all")){
            sendMessage(update, service.listOfAll().toString());
        }
        if (hasText && command.equals("/free")){
            sendMessage(update, service.listOfFreeScopes().toString());
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




}
