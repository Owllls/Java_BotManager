package Gui;

import LikeDataBase.Alkohol;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public  class  AlcoPane extends JPanel {
    private SimpleModel model = new SimpleModel();

    private JTable alcoTable;
    private JScrollPane scrollPane;
    private SimpleModel simpleModel;

    private void init() {
        alcoTable.setModel(new SimpleModel());
        alcoTable.getInputMap(JComponent.WHEN_FOCUSED).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "del");
        JableButtonRenderer buttonRenderer = new JableButtonRenderer();
        alcoTable.setFillsViewportHeight(true);
        alcoTable.getColumn("מידה").setCellRenderer(buttonRenderer);
        System.out.println(alcoTable.getColumn("מידה").getCellRenderer());
        alcoTable.getColumn("תמונה").setCellRenderer(buttonRenderer);
        System.out.println("not work");
        alcoTable.getActionMap().put("del", actDeleteRow);


        alcoTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = alcoTable.getColumnModel().getColumnIndexAtX(e.getX());
                int row    = e.getY()/alcoTable.getRowHeight();

                if (row < alcoTable.getRowCount() && row >= 0 && column < alcoTable.getColumnCount() && column >= 0) {
                    Object value = alcoTable.getValueAt(row, column);
                    if (value instanceof JButton) {
                        ((JButton) value).doClick();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setLayout(new BorderLayout());
        scrollPane = new JScrollPane(alcoTable);

        scrollPane.setSize(new Dimension(2000, 2000));
        add(BorderLayout.CENTER, scrollPane);

        System.out.println("Метод init " + Thread.currentThread().getName());

    }
    private void reinit() {
        simpleModel = new SimpleModel();
        alcoTable.setModel(simpleModel);
        JableButtonRenderer buttonRenderer = new JableButtonRenderer();
        alcoTable.getColumn("מידה").setCellRenderer(buttonRenderer);
        alcoTable.getColumn("תמונה").setCellRenderer(buttonRenderer);
        System.out.println(alcoTable.getColumn("מידה").getCellRenderer());

    }

    public void refresh() {
        this.repaint();

        scrollPane.repaint();
        reinit();


        System.out.println(alcoTable.getColumn("מידה").getCellRenderer());

    }

    private Action actDeleteRow = new AbstractAction() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == alcoTable) {

                int r = alcoTable.getSelectedRow();
                if (r >= 0) {

                    simpleModel.removeRow(r);
                    refresh();
                }
            }
        }
    };

    public AlcoPane() {
        alcoTable = new JTable();
        init();

    }

    class JableButtonRenderer implements TableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JButton button = (JButton) value;

            button.setBounds(new Rectangle(2,2));
            button.setMargin(new Insets(0,5,0,15));
            return button;
        }
    }
}