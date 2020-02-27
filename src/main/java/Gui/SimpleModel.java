package Gui;

import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleModel extends AbstractTableModel {
      private static int position = 0;
      ArrayList<Alkohol> alko;
    File file;
    JTextArea textArea;
    BufferedImage image;
    public void removeRow(int row) {
        AlkoholList.deleteData(row);
    }

    public SimpleModel(){
       alko = new AlkoholList();
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        super.setValueAt(aValue, rowIndex, columnIndex);
        switch (columnIndex){
            case 0:  setNameOnList(aValue,rowIndex); break;
            case 1:  setKindToList(aValue,rowIndex);break;
            case 2:  setAhuzimOnList(aValue,rowIndex);break;
            case 3:  setPriceOnList(aValue,rowIndex);break;
            case 4:  setNumber_OfOnList(aValue,rowIndex);break;
            case 5: ;break;
            case 6: ;break;
            case 7: setActive_Is(aValue,rowIndex);break;


        }
    }



    // Количество строк
    @Override
    public int getRowCount() {
        return alko.size();
    }
    // Количество столбцов
    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "שם";
            case 1: return "סוג";
            case 2: return "ליתר";
            case 3: return "המחיר";
            case 4: return "כמות";
            case 5: return "מידה";
            case 6: return "תמונה";
            case 7: return "לעלות לחנות";
            default: return "A";
        }
    }

    // Тип хранимых в столбцах данных
    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return Double.class;
            case 3: return Double.class;
            case 4: return Integer.class;
            case 5: return JButton.class;
            case 6: return JButton.class;
            case 7: return Boolean.class;

            default: return Object.class;
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    // Функция определения данных ячейки
    @Override
    public Object getValueAt(int row, int column)
    {
         alko.get(row);

        switch (column) {
            case 0: return alko.get(row).getName();
            case 1: return alko.get(row).getGroup();
            case 2: return alko.get(row).getLitres();
            case 3: return alko.get(row).getPrice();
            case 4: return alko.get(row).getNumber_of();
            case 5: JButton  buton = new JButton("הוסף מידה");

                if (AlkoholList.getAlko().get(row).getA_litleInfo() == null || AlkoholList.getAlko().get(row).getA_litleInfo().equals("")) {
                    buton.setText("הוסף מידה");
                } else {
                    buton.setText("לשנות");
                }

                buton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textArea = new JTextArea();
                    textArea.setLineWrap(true);
                    textArea.setWrapStyleWord(true);

                    switch (JOptionPane.showConfirmDialog(JOptionPane.getFrameForComponent(buton),
                            textArea)){
                        case JOptionPane.OK_OPTION:
                            AlkoholList.getAlko().get(row).setA_litleInfo(textArea.getText());



                            break;
                    }
                }
            });
             return buton;
            case 6: JButton  btn = new JButton("הוסף תמונה");
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fileOpen = new JFileChooser();
                        int ret = fileOpen.showDialog(null,"תבחר תמונה");
                        if(ret == JFileChooser.APPROVE_OPTION){
                            file = fileOpen.getSelectedFile();

                        }

                        try {
                           // image = ImageIO.read(file);
                           AlkoholList.getAlko().get(row).setImage(file);
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }
                    }
                });
                return btn;
            case 7: return alko.get(row).isActive();
        }
        return "Не определена";
    }
    private void setNameOnList(Object value,int position){
         alko.get(position).setName(value.toString());
    }
    private void setAhuzimOnList(Object value,int position){
         if(Is_ADoubleNumber(value.toString())){
         alko.get(position).setAhuzim(Double.parseDouble(value.toString()));
         }
    }
    private void setPriceOnList(Object value,int position){
        if(Is_ADoubleNumber(value.toString())){
            alko.get(position).setPrice(Double.parseDouble(value.toString()));
        }
    }
    private void setKindToList(Object value,int position){

            alko.get(position).setGroup(value.toString());


    }
    private void setNumber_OfOnList(Object value,int position){
        if(Is_ANumber(value.toString())){
            alko.get(position).setNumber_of(Integer.parseInt(value.toString()));
        }
    }
    private void setActive_Is(Object value,int position){

            alko.get(position).setActive((Boolean) value);

    }
    private boolean Is_ANumber(String string){
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }
    private boolean Is_ADoubleNumber(String string){
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {

            return false;
        }
    }
}
