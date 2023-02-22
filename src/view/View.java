package view;

import controller.Controller;
import model.Estudiante;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class View extends JFrame{
    private JPanel principal;
    private JTextField textField1;
    private JButton agregarButton;
    private JTable table;
    private JScrollPane scrollPane;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton actualizarButton;
    private JButton imprimirButton;
    private JButton resetButton;
    private JButton borrarButton;
    private JButton salirButton;
    private DefaultTableModel defaultTableModel;
    private Estudiante actual;

    public View() throws HeadlessException {
        super("CRUD ESTUDIANTES");
        setSize(1000,1000);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addComponents(getContentPane());
        setVisible(true);
    }

    private void addComponents(Container contentPane) {
        Object [] columns={"ID","NOMBRE","APELLIDO","DIRECCION","COD-POS","TELEFONO"};
        defaultTableModel=new DefaultTableModel();
        defaultTableModel.setColumnIdentifiers(columns);
        table.setModel(defaultTableModel);
        contentPane.add(principal);
    }

    public void agregaTabla(Object[] row) {
        defaultTableModel.addRow(row);
    }

    public void addActionListener(ActionListener actionListener) {
        agregarButton.addActionListener(actionListener);
        resetButton.addActionListener(actionListener);
        borrarButton.addActionListener(actionListener);
        imprimirButton.addActionListener(actionListener);
        salirButton.addActionListener(actionListener);
        actualizarButton.addActionListener(actionListener);
    }

    public void limpiarCampos() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
    }

    public int showYesNoMessage(String s) {
        return JOptionPane.showConfirmDialog(this,s,"Sistema base de datos",JOptionPane.YES_NO_OPTION);
    }

    public void salir() {
        System.exit(0);
    }

    public void addActionTable(MouseListener tableActionListener) {
        table.addMouseListener(tableActionListener);
    }

    public JTable getTable() {
        return table;
    }

    public String getTextField1() {
        return textField1.getText();
    }

    public void setTextField1(String s) {
        textField1.setText(s);
    }

    public String getTextField2() {
        return textField2.getText();
    }

    public void setTextField2(String s) {
        textField2.setText(s);
    }

    public String getTextField3() {
        return textField3.getText();
    }

    public void setTextField3(String s) {
        textField3.setText(s);
    }

    public String getTextField4() {
        return textField4.getText();
    }

    public void setTextField4(String s) {
        textField4.setText(s);
    }
    public String getTextField5() {
        return textField5.getText();
    }

    public void setTextField5(String s) {
        textField5.setText(s);
    }

    public String getTextField6() {
        return textField6.getText();
    }

    public void setTextField6(String s) {
        textField6.setText(s);
    }

    public void showMessage(String s) {
        JOptionPane.showMessageDialog(this,s,"Sistema base de datos",JOptionPane.INFORMATION_MESSAGE);
    }

    public void errorMessage(String s) {
        JOptionPane.showMessageDialog(this,s,"Error",JOptionPane.ERROR_MESSAGE);
    }

    public void eliminarFila() {
        defaultTableModel.removeRow(table.getSelectedRow());
    }
}
