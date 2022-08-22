package omnibot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Keyboard {

    public static ReplyKeyboardMarkup setReplyKeyboard(String set) {

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();

        if (set.equalsIgnoreCase("start")) {
            row.add("Валюта");
            row.add("Погода");
            keyboard.add(row);
            row2.add("/start");
            keyboard.add(row2);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return replyKeyboardMarkup;
        } else if (set.equalsIgnoreCase("currency")) {
            row.add("USD");
            row.add("EUR");
            row.add("CNY");
            keyboard.add(row);
            row2.add("/start");
            keyboard.add(row2);
            replyKeyboardMarkup.setKeyboard(keyboard);
            return replyKeyboardMarkup;
        } else {
            System.out.println("Нет такой клавиатуры");
        }
        return replyKeyboardMarkup;
    }
    public static InlineKeyboardMarkup setInlineKeyboard() {

        List<InlineKeyboardButton> buttons = new ArrayList<>();

        InlineKeyboardButton buttonHello = new InlineKeyboardButton();
        buttonHello.setText("Currency");
        buttonHello.setCallbackData("currency");
        buttons.add(buttonHello);

        InlineKeyboardButton buttonWeather = new InlineKeyboardButton();
        buttonWeather.setText("Weather");
        buttonWeather.setCallbackData(String.valueOf("weather"));
        buttons.add(buttonWeather);

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        keyboardMarkup.setKeyboard(Collections.singletonList(buttons));

        return keyboardMarkup;
    }
}
