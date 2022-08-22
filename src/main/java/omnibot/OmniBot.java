package omnibot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.logging.Logger;

public class OmniBot extends TelegramLongPollingBot {

    private final Logger logger = Logger.getLogger("OmniBotLogger");

    @Override
    public String getBotUsername() {
        return "BOT_NAME";
    }

    @Override
    public String getBotToken() {
        return "TOKEN";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            String textFromMessage = update.getMessage().getText();

            logger.info("Received update - " + update);

            if (textFromMessage.equalsIgnoreCase("/start")) {
                String messageText = "Привет!\nВы можете спросить у меня текущий курс валюты или погоду.\n" +
                        "Для этого просто введите код валюты или \"Погода\"";
                message.setText(messageText);
                message.setReplyMarkup(Keyboard.setReplyKeyboard("start"));

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (textFromMessage.equalsIgnoreCase("Валюта")) {
                String messageText = "Введите код валюты";
                message.setText(messageText);
                message.setReplyMarkup(Keyboard.setReplyKeyboard("currency"));

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }  else if (textFromMessage.equalsIgnoreCase("Погода")) {
                String messageText = Weather.getWeatherDegree("Saint-Petersburg");
                message.setText(messageText);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else if (textFromMessage.length() == 3) {
                String messageText = Currency.getCurrencyRate(textFromMessage);
                message.setText(messageText);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            } else {
                String messageText = "Не знаю такую команду";
                message.setText(messageText);

                try {
                    execute(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
