package Bot;

import Gui.StartProgram;
import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;


public class BotStart implements Runnable{
    String BotName = "";
    String BotToken = "";
    StartProgram program;

    public BotStart(StartProgram program){
        this.program = program;
    }

    private void StartBOT(){
        ApiContextInitializer.init();
        System.out.println(Thread.currentThread().getName());
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try{
            telegramBotsApi.registerBot(new Bot(BotName,BotToken,program));
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        System.out.println("   START    ");
          StartBOT();

    }
}