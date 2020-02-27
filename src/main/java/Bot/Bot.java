package Bot;
import Gui.StartProgram;
import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;
import LikeDataBase.UsersList;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;

public class Bot extends TelegramLongPollingBot{
    static int CountOFUsers = 0;

    HashMap<Long,KorZina> korzinI = new HashMap<Long, KorZina>();
    HashMap<Long,Zakaz> zakazI = new HashMap<Long, Zakaz>();
    KorZina korZina;
    String BotName ;
    String BotToken ;
    ArrayList<Alkohol> listOfConcreateAlko;
    String mida = "אנכנו ככה וככה";
    StartProgram program;
    int lastSostoyanie;
    Message lastMes;
    Zakaz zakaz;
    int sostoyanie = 0;

    public Bot(String name, String Token, StartProgram program){
        this.program = program;
        this.BotName = name;
        this.BotToken = Token;


    }



    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        System.out.print(" ChatId   "  + message.getChatId());
        System.out.print(message.getChat().getUserName());

        if(message != null && message.hasText()){
            switch (message.getText()){
                case  "/start":
                    SetUser(message);
                    settingMessage(message,0);
                    break;
                case "כניסה \uD83D\uDE42":
                    sostoyanie = 2;
                    settingMessage(message,sostoyanie);
                    System.out.println("All works");
                    break;
                case "הרשמה למערכת \uD83D\uDD8B":
                    sostoyanie = 3;
                    settingMessage(message,sostoyanie);
                    break;
                case "עגלה \uD83D\uDED2":
                    sostoyanie = 4;
                    OpeningKorzina(message,sostoyanie);
                    break;
                case "תפריט  \uD83E\uDD43":
                    sostoyanie = 1;
                    openZakaz(message);
                    settingMessage(message,sostoyanie);
                    break;
                case "דף הבית":
                    sostoyanie = 0;
                    settingMessage(message,sostoyanie);
                    break;
                case "איפוס הזמנה ❌":
                    sostoyanie = 7;
                    ipusAzmana(message);
                    settingMessage(message,sostoyanie);
                    break;
                case "מידע על..":
                    sostoyanie = 10;
                    sendingPhoto(message,sostoyanie);
                    sendingMessage(message,sostoyanie);
                    break;
                case "תיקון \uD83D\uDE05":
                    sostoyanie = 8;
                    OpeningKorzina(message,sostoyanie);
                    break;
                case "Add +1":
                    sostoyanie = 11;
                    AddingOne(sostoyanie,message);
                    break;
                case "Delete -1":
                    sostoyanie = 12;
                    MinusOne(sostoyanie,message);
                    break;
                case "להוסיף לסל \uD83D\uDECD":
                    dobavlyemZakaz_V_Korzinu(message);
                    sostoyanie = 0;
                    settingMessage(message,sostoyanie);
                    break;
                case "להזמין \uD83D\uDE97":
                    sostoyanie = 3;
                    zavershaemZakaz(message);
                    settingMessage(message,sostoyanie);
                    break;

            }
            if(chooseIsMessage_aCategory(message.getText())){
                sostoyanie = 5;
                zapominaemGrupu(message);
                settingMessage(message,sostoyanie);


            }
            if(Is_ANumber(message.getText())){
                sostoyanie = 6;
                SetZakaz(Integer.parseInt(message.getText()), message);
                OpeningKorzina(message,sostoyanie);
            }

        }
    }
    private void SetUser(Message message){
        UsersList user = new UsersList();
        user.setChatID(message.getChat().getId());
        if(message.getChat().getUserName() != null){
            user.setUsername(message.getChat().getUserName());
        }
        korZina =  new KorZina(message);
        korZina.setUser(user);
        korZina.setTextArea(program);
        korzinI.put(message.getChat().getId(),korZina);
    }
    private void SetZakaz(int i,Message message){
        if(lastSostoyanie == 8){
            sostoyanie = 9;
        }else {
            zakazI.get(message.getChat().getId()).dobavitNapitok(listOfConcreateAlko.get(i));
        }
    }
    private void openZakaz(Message message){
        zakaz = new Zakaz();
        zakazI.put(message.getChat().getId(),zakaz);
    }

    private void dobavlyemZakaz_V_Korzinu(Message message){
        if(zakazI.get(message.getChat().getId()) != null) {
            korzinI.get(message.getChat().getId()).dobavitZakaz(zakazI.get(message.getChat().getId()),message);
        }
        zakazI.remove(message.getChat().getId());
    }
    private void zavershaemZakaz(Message message){
        KorZina korZina = korzinI.get(message.getChat().getId());
        korZina.DALAEMZAKAZ();
    }
    private void AddingOne(int i,Message message){
        if(lastSostoyanie == 9){
            korzinI.get(message.getChat().getId()).AddItem((Integer.parseInt(lastMes.getText())));
            sostoyanie = 9;
            message = lastMes;
            OpeningKorzina(message,sostoyanie);
        } else {
        zakazI.get(message.getChat().getId()).PlusOne();
            sendingMessage(message, sostoyanie);}
    }
    private void MinusOne(int i,Message message){
        if(lastSostoyanie == 9){
            korzinI.get(message.getChat().getId()).DeleteItem((Integer.parseInt(lastMes.getText())));
            sostoyanie = 9;
            message = lastMes;
            OpeningKorzina(message,sostoyanie);
        } else {
        zakazI.get(message.getChat().getId()).MinusOne();
            sendingMessage(message, sostoyanie);
        }
    }
    private void ipusAzmana(Message message){
        korzinI.get(message.getChat().getId()).IpusAzmana();
    }
    private void zapominaemGrupu(Message message){
     listOfConcreateAlko = AlkoholList.getList_Of_Concreate_Group(message.getText());
    }

      private void settingMessage(Message message,int sostoyanie){
        ModelOfMessage model = new ModelOfMessage(sostoyanie,message);
        model.setChatId(message.getChatId().toString());
          model.enableMarkdown(true);
          model.setReplyToMessageId(message.getMessageId());
          lastSostoyanie = sostoyanie;
          lastMes = message;
          try {

              execute(model);
          } catch (TelegramApiException e) {
              e.printStackTrace();
              System.out.println("Что-то пошло не так! ");
          }
      }

    private void OpeningKorzina(Message message,int sostoyanie){
        ModelOfMessage model = new ModelOfMessage(sostoyanie,message,korzinI.get(message.getChat().getId()));
        model.setChatId(message.getChatId().toString());
        model.enableMarkdown(true);
        model.setReplyToMessageId(message.getMessageId());
        lastSostoyanie = sostoyanie;
        lastMes = message;
        try {
            execute(model);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Корзина не хочет открываться ");
        }
    }

    private void sendingMessage(Message message,int sostoyanie){
        ModelOfMessage model = new ModelOfMessage(sostoyanie,message, zakazI.get(message.getChat().getId()));
        model.setChatId(message.getChatId().toString());
        model.enableMarkdown(true);
        model.setReplyToMessageId(message.getMessageId());
        lastSostoyanie = sostoyanie;
        lastMes = message;
        try {

            execute(model);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("Что-то пошло не так! ");
        }
    }
    private void sendingPhoto(Message message,int sostoyanie){
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(message.getChatId().toString());
        if(zakaz.getNapitok().getImage() != null){
            sendPhoto.setPhoto(zakazI.get(message.getChatId()).napitok.getImage());

        try {

            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            System.out.println("photo not sending ");
         }
        }
    }

    private boolean Is_ANumber(String string){

        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }

      private boolean chooseIsMessage_aCategory(String a){
        boolean result = AlkoholList.Contains_Category(a);

        return result;
      }
    @Override
    public String getBotUsername() {
        return BotName;
    }

    @Override
    public String getBotToken() {
        return BotToken;
    }


}

