package LikeDataBase;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlkoholList extends ArrayList<Alkohol> implements Runnable, Serializable {

    static private ArrayList<Alkohol> alko = new ArrayList<Alkohol>();
    static private ArrayList<String> Kinds_Of_Alcohol_List = new ArrayList<String>();

    public static void setAlko(ArrayList<Alkohol> alko) {
        AlkoholList.alko = alko;
    }

    public static void setKinds_Of_Alcohol_List(ArrayList<String> kinds_Of_Alcohol_List) {
        Kinds_Of_Alcohol_List = kinds_Of_Alcohol_List;
    }


    public static void deleteData(int i){

        if(get_Num_Of_DrinksForEachCategory(alko.get(i).getGroup()) > 1) {
            alko.remove(i);
        }
        if(get_Num_Of_DrinksForEachCategory(alko.get(i).getGroup()) <= 1) {
            Kinds_Of_Alcohol_List.remove(alko.get(i).getGroup());
            alko.remove(i);

        }


    }

    public synchronized static void deleteData(Alkohol alkohol, int i){
               int x = alko.indexOf(alkohol);
               alko.get(x).setNumber_of(alko.get(x).getNumber_of() - i);
               if(alko.get(x).getNumber_of() < 1){

                   deleteData(x);
               }




    }

    public static ArrayList<Alkohol> getAlko() {
        return alko;
    }

    public static void AddData(Alkohol alkohol){ //Добавление данных в список
        AddingNewCategory(alkohol.getGroup());
        alko.add(alkohol);


    }

    public AlkoholList(){   // Конструктор
        this.addAll(alko);
    }

    public static ArrayList<Alkohol> getList_Of_Concreate_Group(String s){ //Список всех напитков в данной категории катрегорий скорее всего ненужен
        ArrayList<Alkohol> concreteGroup = new ArrayList<Alkohol>();
        Pattern pattern = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for(int i=0; i < alko.size(); i++){
            if(alko.get(i).isActive()){
            matcher = pattern.matcher(alko.get(i).getGroup());
            if(matcher.find()) {
                concreteGroup.add(alko.get(i));
                }
            }
        }
        return concreteGroup;
    }

    public static int get_Num_Of_DrinksForEachCategory(String s){ //Список всех напитков в данной категории катрегорий скорее всего ненужен
        int a = 0;
        Pattern pattern = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
        Matcher matcher;
        for(int i=0; i < alko.size(); i++){
            if(alko.get(i).isActive()){
            matcher = pattern.matcher(alko.get(i).getGroup());

            if(matcher.find()){
                a++;
               }
            }
        }
        return a;
    }

    public static ArrayList<Alkohol> getList_OFAlko(){
        return alko;
    }

    public static int NumberOfCategorys(){
        return Kinds_Of_Alcohol_List.size();
    }

    public static ArrayList<String> getCategorys(){
        return Kinds_Of_Alcohol_List;
    }

    public static boolean Contains_Category(String s){
        Boolean result = false;
        Pattern pattern = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
        for(String j: Kinds_Of_Alcohol_List){

            Matcher matcher = pattern.matcher(j);
            if(matcher.find()){
                result = true;
            }
        }
        return result;
    }

    private static void AddingNewCategory(String s){
        Boolean add = true;
        Pattern pattern = Pattern.compile(s,Pattern.CASE_INSENSITIVE);
        for(String j: Kinds_Of_Alcohol_List){

            Matcher matcher = pattern.matcher(j);
            if(matcher.find()){
                add = false;
            }}
        if(add){
            Kinds_Of_Alcohol_List.add(s);

        }
    }

    private void saveAlcoList(){
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(new File("Alkojava.ser")));
            outputStream.writeObject(alko);
            outputStream.writeObject(Kinds_Of_Alcohol_List);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
          while (true){
              try {
                  saveAlcoList();
                  Thread.sleep(1000);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
    }
}
