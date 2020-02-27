package Gui;

import Bot.*;
import Fonts.FontsList;
import LikeDataBase.Alkohol;
import LikeDataBase.AlkoholList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;

public class StartProgram {


    StartProgram s;
    AlcoPane alcoPane;
    Thread thread;
    JTextField Name;
    JTextField Prochent;
    JTextField Kind_Of;
    JTextField Number_of;
    JTextField Alitle_Info;
    JTextField Price;
    JTextPane TextFrom_Bot;
    ////////////////////////////////////Тут заканчиваем код панельки ///////////////////////////////////////////////////////////





    public static void main(String[] args) {
        StartProgram s = new StartProgram();
        s.BuildGui();

    }
    public void StartBot(){
          thread = new Thread(new BotStart(this));
          thread.start();
    }

    private void BuildGui(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BorderLayout layout = new BorderLayout();


        JPanel background = new JPanel(layout);
        JPanel NorthBackground = new JPanel();
        BoxLayout layout1 = new BoxLayout(NorthBackground,BoxLayout.X_AXIS);
        NorthBackground.setLayout(layout1);

        background.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        Box buttonBox = new Box(BoxLayout.Y_AXIS);

        //Тут создаем меню бар
        JMenuBar menuBar = new JMenuBar();

        JMenu filemenu = new JMenu("הגדרות");
        JMenuItem save = new JMenuItem("Save");

        save.addActionListener(new MySaveListener());
        JMenuItem load = new JMenuItem("load File");

        load.addActionListener(new MyLoadLostener());
        filemenu.add(save);
        filemenu.add(load);
        menuBar.add(filemenu);

        JButton start = new JButton("Start Bot");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StartBot();
            }
        });
        JButton stop = new JButton("Stop Bot");
        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RedTextFromBot("popopoo");

            }
        });
        JButton Change = new JButton("Change");
        Change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BlueTextFromBot(" RED IT`s red. Very-very red ! ");
            }
        });


        //Делаем вверхнюю панель для ввода новых данных в таблицу/////////////////////////////////////////////////////
        JButton AddKindOf = new JButton("הוסף");

        Kind_Of = new JTextField("סוג");
        Kind_Of.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(Kind_Of.getText().equals("סוג")){
                    Kind_Of.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Kind_Of.getText().equals("")){
                    Kind_Of.setText("סוג");
                }
            }
        });
        Name = new JTextField("שם",1);
        Name.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(Name.getText().equals("שם")){
                    Name.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Name.getText().equals("")){
                    Name.setText("שם");
                }
            }

        });
        Prochent = new JTextField("אחוזים",1);
        Prochent.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Prochent.setBackground(Color.white);
                if(Prochent.getText().equals("אחוזים") || Prochent.getText().equals("רק מספר")){
                    Prochent.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Prochent.getText().equals("")){
                    Prochent.setText("אחוזים");
                }
            }

        });
        Alitle_Info = new JTextField("Hello",1);
        Price = new JTextField("המחיר",1);
        Price.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Price.setBackground(Color.white);
                if(Price.getText().equals("המחיר") || Price.getText().equals("רק מספר")){

                    Price.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Price.getText().equals("")){
                    Price.setText("המחיר");
                }
            }

        });
        Number_of = new JTextField("כמות יחידות",1);
        Number_of.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Number_of.setBackground(Color.white);
                if(Number_of.getText().equals("כמות יחידות") || Number_of.getText().equals("רק מספר")){

                    Number_of.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Number_of.getText().equals("")){
                    Number_of.setText("כמות יחידות");
                }
            }

        });

        NorthBackground.add(Name);
        NorthBackground.add(new JLabel("  שם  "));
        NorthBackground.add(Kind_Of);
        NorthBackground.add(new JLabel(" סוג "));
        NorthBackground.add(Prochent);
        NorthBackground.add(new JLabel("  ליטר  "));
        NorthBackground.add(Price);
        NorthBackground.add(new JLabel("  המחיר  "));
        NorthBackground.add(Number_of);
        NorthBackground.add(new JLabel(" כמות וחידות "));
        NorthBackground.add(AddKindOf);


         AddKindOf.addActionListener(new MyAddItemListener());


        TextFrom_Bot = new JTextPane();

        TextFrom_Bot.setSize(300,800);
        TextFrom_Bot.setPreferredSize(new Dimension(300,500));




        Box contents = new Box(BoxLayout.Y_AXIS);
        alcoPane = new AlcoPane();

        contents.add(alcoPane);
        contents.add(new JScrollPane(TextFrom_Bot));
        buttonBox.add(start);
        buttonBox.add(stop);
        buttonBox.add(Change);


        background.add(BorderLayout.EAST, buttonBox);
        background.add(BorderLayout.NORTH,NorthBackground);
        background.add(BorderLayout.CENTER,contents);
        background.setSize(400,400);

        frame.getContentPane().add(background);
        frame.setJMenuBar(menuBar);
        frame.setBounds(50,50,300,300);
        frame.setSize(1500,900);


        frame.setVisible(true);
    }
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public synchronized void RedTextFromBot(String string){

        FontsList font = new FontsList(TextFrom_Bot.getInputAttributes(),TextFrom_Bot.getStyledDocument(),TextFrom_Bot);
        font.PrintWhite(string);
    }
    public synchronized void BlueTextFromBot(String string){

        FontsList font = new FontsList(TextFrom_Bot.getInputAttributes(),TextFrom_Bot.getStyledDocument(),TextFrom_Bot);
        font.PrintRed(string);
    }
    public static synchronized void Start(){

        System.out.println("метод старт в старт программ вот так " + Thread.currentThread().getName());
    }

    public void refreshTable(){
        alcoPane.refresh();
    }

    ////////////////////////////////Слушатель который добавляет новый элемнт ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    class MyAddItemListener implements ActionListener{
        boolean Add_or_NO;

        @Override
        public void actionPerformed(ActionEvent e) {

            Alkohol alkohol = new Alkohol();
            Add_or_NO = true;
            if(Name.getText() != null){
                alkohol.setName(Name.getText());
            }
            if(Price.getText() != null) {
                if (Is_ANumber(Price.getText())) {
                    alkohol.setPrice(Double.parseDouble(Price.getText()));
                }
            }
            if(Prochent.getText() != null){
                if(Is_ANumberTwo(Prochent.getText())){
                        alkohol.setAhuzim(Double.parseDouble(Prochent.getText()));
                    }
            }
            if(Number_of.getText() != null){
                if(Is_ANumberThree(Number_of.getText())){
                    alkohol.setNumber_of(Integer.parseInt(Number_of.getText()));
                }
            }
            if(Kind_Of.getText() != null){
                alkohol.setGroup(Kind_Of.getText());
            }
             System.out.println(Add_or_NO);
            if(Add_or_NO == true) {
                Prochent.setText("");
                Name.setText("");
                Price.setText("");
                Number_of.setText("");

                AlkoholList.AddData(alkohol);
                alcoPane.refresh();

            }

        }

        private boolean Is_ANumber(String string){
            try {
                Double.parseDouble(string);
                return true;
            } catch (NumberFormatException e) {
                Price.setBackground(Color.red);
                Price.setText("רק מספר");
                Add_or_NO = false;
                return false;
            }
        }
        private boolean Is_ANumberTwo(String string){
            try {
                Double.parseDouble(string);
                return true;
            } catch (NumberFormatException e) {

                Prochent.setBackground(Color.red);
                Prochent.setText("רק מספר");


                Add_or_NO = false;
                return false;
            }
        }
        private boolean Is_ANumberThree(String string){
            try {
                Integer.parseInt(string);
                return true;
            } catch (NumberFormatException e) {
                Number_of.setBackground(Color.red);
                Number_of.setText("רק מספר");
                Add_or_NO = false;
                return false;
            }
        }

    }
    class MySaveListener implements ActionListener{
        File file;

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("לשמור תמונה מצב");
            fileChooser.setCurrentDirectory(new File(GetExecutionPath()));
            int retrival = fileChooser.showSaveDialog(null);

            if (retrival == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();




            }else {
                file = new File(GetExecutionPath());
            }


            try{
                FileOutputStream fileStream = new FileOutputStream(file);
                ObjectOutputStream os = new ObjectOutputStream(fileStream);
                os.writeObject(AlkoholList.getAlko());
                os.writeObject(AlkoholList.getCategorys());
                os.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }



        }
     class MyLoadLostener implements ActionListener{
              File file;
         ArrayList<Alkohol> a;
         @Override
         public void actionPerformed(ActionEvent e) {
             JFileChooser fileOpen = new JFileChooser();
             int ret = fileOpen.showDialog(null,"Find FIle");
             fileOpen.setCurrentDirectory(new File (GetExecutionPath()));
             if(ret == JFileChooser.APPROVE_OPTION){
                 file = fileOpen.getSelectedFile();

             }

             try {
                 FileInputStream fileIn = new FileInputStream(file);
                 ObjectInputStream is = new ObjectInputStream(fileIn);
                 AlkoholList.setAlko((ArrayList<Alkohol>)is.readObject());
                 AlkoholList.setKinds_Of_Alcohol_List((ArrayList<String>)is.readObject());
                 is.close();
             }catch (Exception ex){
                 ex.printStackTrace();
             }
             alcoPane.refresh();
         }
     }
    private  String GetExecutionPath(){
        String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        absolutePath = absolutePath.replaceAll("%20"," "); // Surely need to do this here
        return absolutePath;
    }

}


