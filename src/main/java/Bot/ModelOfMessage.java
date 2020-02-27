 package Bot;

import LikeDataBase.Alkohol;
import Gui.SimpleModel;
import LikeDataBase.AlkoholList;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import java.util.ArrayList;

public class ModelOfMessage extends SendMessage {
    int i = 0;
    Message message;
    MyKeyBoards keyboard;
    Zakaz zakaz;
    KorZina korZina;
    public ModelOfMessage(){

    }
    public ModelOfMessage(int i,Message message){
        this.message = message;
        this.i=i;
        makeInformList(i);
        setButtons(this);
    }
    public ModelOfMessage(int i, Message message, Zakaz zakaz){
        this.zakaz = zakaz;
        this.message = message;
        this.i=i;
        makeInformList(i);
        setButtons(this);
    }
    public ModelOfMessage(int i,Message message,KorZina korZina){
        this.zakaz = zakaz;
        this.korZina = korZina;
        this.message = message;
        this.i=i;
        makeInformList(i);
        setButtons(this);
    }

    private void makeInformList(int i){
        switch (i){
            case 0 : setMessageAboutBot(); break;
            case 1 : setMessageAnd_Kind_Of_Alkohol(); break;
            case 2 : setMessageAboutBot(); break;
            case 3 : setAfterBuyMessage();break;
            case 4 : OpenKorzina(message);break;
            case 5 : setListOfConcreateGroup(message); break;
            case 6 : setByingMesage(message);break;
            case 7 : ipusAzmana();break;
            case 8 : openKorzinaManager();break;
            case 9 : getNapitokFromKorzina(message);break;
            case 10: setInfo();break;
            case 11: plus();break;
            case 12: plus();break;
            default: break;
        }
    }
    private void setMessageAboutBot(){
        this.setText(" שלום חברים , להזמנת אלכוהול בשפע בלחיצת כמה כפתורים - תהנוו \uD83C\uDF78 ");
    }
    private void ipusAzmana(){
        this.setText(" עגלה שלך רקה! ");
    }
    private void setInfo(){
        if(zakaz.getNapitok().getA_litleInfo() != null && zakaz.getNapitok().getA_litleInfo() != ""){
            this.setText(zakaz.getNapitok().getA_litleInfo());
        }

    }
    private void plus(){
        StringBuilder mesTo = new StringBuilder();

        mesTo.append("בחירתך: " +  "\"" + zakaz.getNapitok().getName() + "\" " + "מחיר: " + " " + zakaz.getNapitok().getPrice() + "₪");
        mesTo.append("\n");
        mesTo.append("\n");
        mesTo.append(" תבחר כמות יחידות" );
        mesTo.append("\n");
        mesTo.append("\n");
        mesTo.append("  הכמות היא  -  "  + zakaz.number_of_Items);
        mesTo.append("\n");
        mesTo.append("\n");
        mesTo.append("  מחיר כולל - " + (zakaz.number_of_Items * zakaz.getNapitok().getPrice()) + "₪");
        this.setText(mesTo.toString());
    }
    private void openKorzinaManager(){
        StringBuilder mesTo = new StringBuilder();
        ArrayList<Zakaz> zakazI = korZina.getZakaziu();

        for(int i = 0; i < zakazI.size(); i ++){
            mesTo.append(i + "  " + zakazI.get(i).getNapitok().getName() + " N " +  zakazI.get(i).getNumber_of_Items() + " סכום " + (zakazI.get(i).getNumber_of_Items() * zakazI.get(i).getNapitok().getPrice()) + "\n" );

        }
        this.setText(mesTo.toString());
    }
    private void setAfterBuyMessage(){
        this.setText("  \uD83D\uDC4C תודה רבה על הזמנתך השלים יצור איתכם קשר בכמה דקות הכרובות ");
    }

    private void setListOfConcreateGroup(Message mesFrom){
        this.setText("תלחץ על מספר של מוצאר שאתה רוצה להזמין ");
        StringBuilder mesTo = new StringBuilder();

        ArrayList<Alkohol> listOfConcreateAlko = AlkoholList.getList_Of_Concreate_Group(mesFrom.getText());
        putAndPost.put(listOfConcreateAlko);
        for(int i=0; i < listOfConcreateAlko.size(); i++){

            if(listOfConcreateAlko.get(i).isActive()) {
                mesTo.append(i +")" + " " + "\""+ listOfConcreateAlko.get(i).getName() + "\"" +" : " + Math.round(listOfConcreateAlko.get(i).getPrice()) + " ש\"ח  , " +  "מלאי : " + (listOfConcreateAlko.get(i).getNumber_of()) + "\n");
                mesTo.append("\n");

            }
        }
        if(mesTo.equals("")){
            this.setText("So sorry, its Sold Out Today ... ");
        }
        this.setText(mesTo.toString());

    }

    private void setMessageAnd_Kind_Of_Alkohol(){
        StringBuilder mesTo = new StringBuilder();

        ArrayList<String> listOfCategories = AlkoholList.getCategorys();

        for(int i = 0; i < listOfCategories.size();i++){
            mesTo.append(listOfCategories.get(i) + ":" + " \n\n" );
                 int num = AlkoholList.get_Num_Of_DrinksForEachCategory(listOfCategories.get(i));
                 ArrayList <Alkohol> listOfConcreateGroup = AlkoholList.getList_Of_Concreate_Group(listOfCategories.get(i));
                  for(int j = 0; j < num; j++){
                    if (listOfConcreateGroup.get(j).isActive()) {


                        mesTo.append(("     " + listOfConcreateGroup.get(j).getName() +  " " + listOfConcreateGroup.get(j).getPrice() + "₪   | " +  listOfConcreateGroup.get(j).getLitres() + " ליטר "  + "\n"));
                        mesTo.append("\n");

                    }
                  }
        }
       this.setText(mesTo.toString());
    }
     private void setByingMesage(Message messageFrom){
         StringBuilder mesTo = new StringBuilder();
         Alkohol drink =  putAndPost.take().get(Integer.parseInt(messageFrom.getText()));
         mesTo.append("בחירתך: " +  "\"" + drink.getName() + "\" " + "מחיר: " + " " + drink.getPrice() + "₪");
         mesTo.append("\n");
         mesTo.append("\n");
         mesTo.append(" תבחר כמות יחידות" );
         mesTo.append("\n");
         mesTo.append("\n");
         mesTo.append("  הכמות היא  -  "  + 0);
         this.setText(mesTo.toString());

    }
    private void OpenKorzina(Message messageFrom){
        StringBuilder mesTo = new StringBuilder();
        ArrayList<Zakaz> zakaziu = korZina.getZakaziu();

        for(int i = 0; i < zakaziu.size(); i++){
           mesTo.append(i + " "  + zakaziu.get(i).getNapitok().getName() + "  " + zakaziu.get(i).number_of_Items + "   "  + (zakaziu.get(i).getNapitok().getPrice() * zakaziu.get(i).number_of_Items) + "\n");

        }
        this.setText(mesTo.toString());

    }
    private void getNapitokFromKorzina(Message messageFrom){
         StringBuilder mesTo = new StringBuilder();
         ArrayList<Zakaz> zakaziu = korZina.getZakaziu();
         int i =  Integer.parseInt(messageFrom.getText());
         mesTo.append("  "  + zakaziu.get(i).getNapitok().getName() + "  " + zakaziu.get(i).number_of_Items + "   "  + (zakaziu.get(i).getNapitok().getPrice() * zakaziu.get(i).number_of_Items) + "\n");


        this.setText(mesTo.toString());

    }
    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        keyboard = new MyKeyBoards(i, message,korZina);
        replyKeyboardMarkup.setKeyboard(keyboard);


    }
    static class putAndPost {
        static ArrayList<Alkohol> listOfConcreateAlko;


        private static void put(ArrayList<Alkohol> listOfAlko) {
             listOfConcreateAlko = listOfAlko;
        }

        private static ArrayList<Alkohol> take() {

            return listOfConcreateAlko;
        }

    }
}
