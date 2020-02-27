package Fonts;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

public class FontsList {

    private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    private StyledDocument doc;
    private MutableAttributeSet mas;
    private Highlighter.HighlightPainter cyanPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.cyan);
    private Highlighter.HighlightPainter redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
    private Highlighter.HighlightPainter whitePainter = new DefaultHighlighter.DefaultHighlightPainter(Color.white);
    private JTextPane pane;

    static int lines;




    public FontsList(MutableAttributeSet a, StyledDocument doc, JTextPane pane){
        this.mas = a;
        this.doc = doc;
        this.pane = pane;
        lines = 0;

    }



    public void PrintGreen(String string){}
    public void PrintRed(String string){
        StyleConstants.setItalic(mas,true);
        StyleConstants.setBold(mas,true);
        StyleConstants.setFontSize(mas,18);
        StyleConstants.setFontFamily(mas,Font.DIALOG);


        int number = string.toCharArray().length;

        try {

            doc.insertString(0,string,mas);
            doc.insertString(0, "\n" , mas);

        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        try {


            pane.getHighlighter().addHighlight(1,  number + 1, redPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        lines += number;
    }

    public void PrintWhite(String string){



            int number = string.toCharArray().length;
            StyleConstants.setItalic(mas,true);
            StyleConstants.setBold(mas,true);
            StyleConstants.setFontSize(mas,18);
            StyleConstants.setFontFamily(mas, "SimSun");

            try {

                doc.insertString(0, string , mas);
                doc.insertString(0, "\n" , mas);

            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        try {


            pane.getHighlighter().addHighlight(1, number + 1 , cyanPainter);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        lines += number;
    }
}
