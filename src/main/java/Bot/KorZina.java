package Bot;

import Gui.StartProgram;
import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;
import LikeDataBase.UsersList;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;

public class KorZina {

    private ArrayList<Zakaz> zakaziu = new ArrayList<Zakaz>();
    private String Adrees;
    private UsersList user;
    private StartProgram textArea;
    private String Information;
    private Message message;

    public UsersList getUser() {
        return user;
    }

    public void setUser(UsersList user) {
        this.user = user;
    }




    public KorZina(Message message){
        this.message = message;
    }


    public void setTextArea(StartProgram program){
        this.textArea = program;
    }


    public void dobavitZakaz(Zakaz zakaz, Message message){
        if(user.getUsername() != null && user.getUsername() != "") {
            textArea.RedTextFromBot(" \"Client\" " + user.getUsername() + "  has added " + zakaz.getNapitok().getName() + " In amount " + zakaz.getNumber_of_Items() + "\n" + "   to his buylist ");
        } else {

        }
        zakaziu.add(zakaz);
        AlkoholList.deleteData(zakaz.napitok,zakaz.number_of_Items);
        textArea.refreshTable();
    }

    public void OtmenitZakaz(){
        for(int i = 0; i<= zakaziu.size(); i++){
            Alkohol alkohol = zakaziu.get(i).getNapitok();
            alkohol.setNumber_of(zakaziu.get(i).getNapitok().getNumber_of());
            AlkoholList.AddData(alkohol);
        }
    }
    public void AddItem(int x){

        zakaziu.get(x).PlusOne();

    }
    public void DeleteItem(int x){

        zakaziu.get(x).MinusOne();
        if(zakaziu.get(x).getNumber_of_Items() <= 0){
            zakaziu.remove(x);
        }

    }
    public void DALAEMZAKAZ(){

        for(int j = 0; j < zakaziu.size();j++){
            textArea.BlueTextFromBot( ".. " + zakaziu.get(j).getNapitok().getName() + "  in count: " + zakaziu.get(j).getNumber_of_Items());


        }
        if (user.getUsername() != null && user.getUsername() != ""){
            textArea.BlueTextFromBot("User - " + user.getUsername() + " Have bought " + " \n");
        }else {
            textArea.BlueTextFromBot("User - " + user.getChatID() + " Have bought " + " \n");
        }
        user.addUser();

    }
    public ArrayList<Zakaz> getZakaziu() {
        return zakaziu;
    }

    public void setZakaziu(ArrayList<Zakaz> zakaziu) {
        this.zakaziu = zakaziu;
    }
    public void IpusAzmana() {
       zakaziu = null;
       zakaziu = new ArrayList<Zakaz>();
    }
}
