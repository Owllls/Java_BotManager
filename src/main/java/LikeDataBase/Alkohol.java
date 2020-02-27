package LikeDataBase;

import java.awt.*;
import java.io.File;
import java.io.Serializable;

public class Alkohol implements Serializable {
      private String name = "";
      private double Litres = 0;
      private String A_litleInfo = "";


      private String Group = "";
      private int number_of = 0;
      private double price = 0;
      private File image = null;
      boolean Active = false;
    public boolean isActive() {
        return Active;
    }

    public void setActive(boolean active) {
        Active = active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAhuzim(double ahuzim) {
        Litres = ahuzim;
    }

    public void setNumber_of(int number_of) {
        this.number_of = number_of;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setA_litleInfo(String a_litleInfo) {
        A_litleInfo = a_litleInfo;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }


    public String getName() {
        return name;
    }

    public double getLitres() {
        return Litres;
    }

    public String getA_litleInfo() {
        return A_litleInfo;
    }

    public int getNumber_of() {
        return number_of;
    }

    public double getPrice() {
        return price;
    }

    public File getImage() {
        return image;
    }


     public Alkohol(){

     }
     public Alkohol(String name){
         this.name = name;
     }

     public Alkohol(String name,int price){
         this.name = name;
         this.price = price;
     }

     public Alkohol(String name,int price,String A_litleInfo){
         this.name = name;
         this.price = price;
         this.A_litleInfo = A_litleInfo;
     }
     public Alkohol(String name,int price,int number_of ){
         this.name = name;
         this.price = price;
         this.number_of = number_of;
     }
     public Alkohol(String name,int price,int number_of,String A_litleInfo ){
         this.name = name;
         this.price = price;
         this.number_of = number_of;
         this.A_litleInfo = A_litleInfo;
     }
     public Alkohol(String name,int price,int number_of,int Litres){
         this.name = name;
         this.price = price;
         this.number_of = number_of;
         this.Litres = Litres;
     }
    public Alkohol(String name,int price,int number_of,int Litres, String Group){
        this.Group = Group;
        this.Litres = Litres;
        this.name = name;
        this.price = price;
        this.number_of = number_of;

    }

    public Alkohol(String name,int price,int number_of,int Litres,String A_litleInfo, String Group){
        this.Group = Group;
        this.Litres = Litres;
        this.name = name;
        this.price = price;
        this.number_of = number_of;
        this.A_litleInfo = A_litleInfo;
    }

     public Alkohol(String name,int price,int number_of,int Litres,String A_litleInfo,File image){
         this.name = name;
         this.price = price;
         this.number_of = number_of;
         this.A_litleInfo = A_litleInfo;
         this.Litres = Litres;
         this.image = image;
     }
}
