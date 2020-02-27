package Bot;

import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;

import java.util.ArrayList;

public class Zakaz {


    Alkohol napitok = new Alkohol();
    int number_of_Items = 0;
    double price;



    public void dobavitNapitok(Alkohol napitok) {
       this.napitok = napitok;
    }





   public void PlusOne(){
       number_of_Items++;

   }
    public void MinusOne(){

        number_of_Items--;
    }
    public int getNumber_of_Items() {
        return number_of_Items;
    }

    public double getPrice() {
        return price;
    }



    public void setNumber_of_Items(int number_of_Items) {
        this.number_of_Items = number_of_Items;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Alkohol getNapitok() {
        return napitok;
    }

    public void setNapitok(Alkohol napitok) {
        this.napitok = napitok;
    }

}
