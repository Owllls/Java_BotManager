package Bot;

import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyKeyBoards extends ArrayList<KeyboardRow> {
    KeyboardRow keyboardFR_Row;
    KeyboardRow keyboardSD_Row;
    KeyboardRow keyboardTR_Row;
    KorZina korZina;

    public MyKeyBoards(int sostoyanie, Message message, KorZina korZina){
        this.korZina = korZina;
        keyboardFR_Row = new KeyboardRow();
        keyboardSD_Row = new KeyboardRow();
        keyboardTR_Row = new KeyboardRow();
        switch (sostoyanie){
            case 0 : setDefaltKeyboard(); break;
            case 1 : setChoosingKeyboard(); break;
            case 2 : setInfoKeyboard(); break;
            case 3 : setAfterByingKeyboard();break;
            case 4 : setByingKeyboard();break;
            case 5 : setNumbersKeyBoard(message);break;
            case 6 : setChange();break;
            case 7 : setDefaltKeyboard();break;
            case 8 : setChoosingKeyboardForKorzina();break;
            case 9 : setDefAdding();break;
            case 10 :setAdding();break;
            case 11 :setAdding();break;

            case 12 :setAdding();break;
            default: setDefaltKeyboard(); break;
        }
        this.add(keyboardFR_Row);
        this.add(keyboardSD_Row);
        if(keyboardTR_Row.isEmpty()!= true){
            this.add(keyboardTR_Row);
        }
    }

    private void setNumbersKeyBoard(Message mesFrom){
        int a = AlkoholList.get_Num_Of_DrinksForEachCategory(mesFrom.getText());
        if(a < 10){
            for(int i = 0; i<=10;i++){
                if(i<=4){

                    if(i < a) {
                        keyboardFR_Row.add(new KeyboardButton(String.valueOf(i)));
                    }
                }
                if(i>4){
                    if(i < a) {

                        keyboardSD_Row.add(new KeyboardButton(String.valueOf(i)));
                    }
                }


            }
        } else {
            //Дописать метод, который будет делать клавву для ситуаций, когда позиций больше, чем 10

        }

        keyboardTR_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardTR_Row.add(new KeyboardButton("דף הבית"));

    }
    private void setAfterByingKeyboard(){ //По умолчанию  самая обычная
        keyboardFR_Row.add(new KeyboardButton("כניסה \uD83D\uDE42"));
        keyboardSD_Row.add(new KeyboardButton("תפריט  \uD83E\uDD43"));
        keyboardSD_Row.add(new KeyboardButton("הרשמה למערכת \uD83D\uDD8B"));
    }
    private void setChosingAlko(String mesFrom){


        keyboardTR_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardTR_Row.add(new KeyboardButton("דף הבית"));

    }
    private void setDefaltKeyboard(){ //По умолчанию  самая обычная
        keyboardFR_Row.add(new KeyboardButton("כניסה \uD83D\uDE42"));
        keyboardSD_Row.add(new KeyboardButton("תפריט  \uD83E\uDD43"));
        keyboardSD_Row.add(new KeyboardButton("הרשמה למערכת \uD83D\uDD8B"));
        keyboardSD_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
    }
    private void setInfoKeyboard(){ //С информацией
        keyboardFR_Row.add(new KeyboardButton("דף הבית"));
        keyboardSD_Row.add(new KeyboardButton("  תפריט  \uD83E\uDD43"));
        keyboardSD_Row.add(new KeyboardButton("הרשמה למערכת \uD83D\uDD8B"));
    }
    private void setByingKeyboard(){ //С информацией
        keyboardFR_Row.add(new KeyboardButton("דף הבית"));
        keyboardSD_Row.add(new KeyboardButton("איפוס הזמנה ❌"));
        keyboardSD_Row.add(new KeyboardButton("תיקון \uD83D\uDE05"));
        keyboardTR_Row.add(new KeyboardButton("הרשמה למערכת \uD83D\uDD8B"));
        keyboardTR_Row.add(new KeyboardButton( "להזמין \uD83D\uDE97"));
    }
    private void setChange(){

        keyboardFR_Row.add(new KeyboardButton("Add +1"));
        keyboardFR_Row.add(new KeyboardButton("מידע על.."));
        keyboardFR_Row.add(new KeyboardButton("Delete -1"));
        keyboardSD_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardSD_Row.add(new KeyboardButton("דף הבית"));
    }
    private void setDefAdding(){

        keyboardFR_Row.add(new KeyboardButton("Add +1"));
        keyboardFR_Row.add(new KeyboardButton("Delete -1"));
        keyboardSD_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardSD_Row.add(new KeyboardButton("דף הבית"));
    }
    private void setAdding(){

        keyboardFR_Row.add(new KeyboardButton("Add +1"));
        keyboardFR_Row.add(new KeyboardButton( "מידע על.."));
        keyboardFR_Row.add(new KeyboardButton("Delete -1"));
        keyboardSD_Row.add(new KeyboardButton("להוסיף לסל \uD83D\uDECD"));
        keyboardTR_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardTR_Row.add(new KeyboardButton("דף הבית"));
    }


    private void setChoosingKeyboardForKorzina() { //
        int a = korZina.getZakaziu().size();
        if (a < 10) {
            for (int i = 0; i <= 10; i++) {
                if (i <= 4) {

                    if (i < a) {
                        keyboardFR_Row.add(new KeyboardButton(String.valueOf(i)));
                    }
                }
                if (i > 4) {
                    if (i < a) {

                        keyboardSD_Row.add(new KeyboardButton(String.valueOf(i)));
                    }
                }


            }
        } else {
            //Дописать метод, который будет делать клавву для ситуаций, когда позиций больше, чем 10

        }
    }
    private void setChoosingKeyboard(){ //
        int a = AlkoholList.NumberOfCategorys();
        if(a < 10){
           for(int i = 0; i<=10;i++){
               if(i<=4){
                   if(i < a) {
                       keyboardFR_Row.add(new KeyboardButton(AlkoholList.getCategorys().get(i)));
                   }
               }
               if(i>4){
                   if(i < a) {

                       keyboardSD_Row.add(new KeyboardButton(AlkoholList.getCategorys().get(i)));
                   }
               }


           }
        } else {
              //Дописать метод, который будет делать клавву для ситуаций, когда позиций больше, чем 10

        }

        keyboardTR_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
        keyboardTR_Row.add(new KeyboardButton("דף הבית"));
    }
    private void setConfimKeyboard(){
        keyboardFR_Row.add(new KeyboardButton("כניסה \uD83D\uDE42"));
        keyboardSD_Row.add(new KeyboardButton("תפריט  \uD83E\uDD43"));
        keyboardSD_Row.add(new KeyboardButton("להרשמ"));
        keyboardSD_Row.add(new KeyboardButton("עגלה \uD83D\uDED2"));
    }
}
