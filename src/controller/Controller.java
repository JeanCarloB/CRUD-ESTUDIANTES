package controller;

import com.mysql.cj.protocol.Message;
import model.Estudiante;
import sqlConnection.SQLExecutor;
import view.View;

import javax.swing.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;

public class Controller {
    private Estudiante modelo;
    private View vista;

    public Controller() {
        modelo=new Estudiante();
        vista=new View();
        vista.addActionListener(new ActionClass());
        vista.addActionTable(new TableActionListener());
        List<Estudiante> lista=modelo.cargaDatos();
        for(Estudiante estudiante:lista){
            Object[] row=new Object[6];
            row[0]=estudiante.getId();
            row[1]=estudiante.getNombre();
            row[2]=estudiante.getApellido();
            row[3]=estudiante.getDireccion();
            row[4]=estudiante.getCodigoPostal();
            row[5]=estudiante.getTelefono();
            vista.agregaTabla(row);
        }
    }

    private class ActionClass implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String command=e.getActionCommand();
            switch (command){
                case "Reset":
                    vista.limpiarCampos();
                    break;
                case "Salir":
                    if(vista.showYesNoMessage("Esta seguro que desea salir?")== JOptionPane.YES_OPTION){
                        vista.salir();
                    }
                    break;
                case "Agregar":
                    cargaEstudiante();
                    modelo.insertaEstudiante();
                    vista.showMessage("Estudiante insertado exitosamente!");
                    Object[] row=new Object[6];
                    row[0]=modelo.getId();
                    row[1]=modelo.getNombre();
                    row[2]=modelo.getApellido();
                    row[3]=modelo.getDireccion();
                    row[4]=modelo.getCodigoPostal();
                    row[5]=modelo.getTelefono();
                    vista.agregaTabla(row);
                    vista.limpiarCampos();
                    break;
                case "Actualizar":
                    cargaEstudiante();
                    modelo.actualizarEstudiante();
                    vista.showMessage("Estudiante actualizado de manera exitosa");
                    actualizaFila();
                    vista.limpiarCampos();
                    break;
                case "Borrar":
                    cargaEstudiante();
                    if(vista.showYesNoMessage("Desea eliminar estudiante")==JOptionPane.YES_OPTION){
                        modelo.eliminaEstudiante();
                    }
                    vista.showMessage("Eliminado de manera exitosa");
                    eliminarFila();
                    vista.limpiarCampos();
                    break;
                case "Imprimir":
                    imprimirTabla();
                    break;
            }
        }
    }

    private void eliminarFila() {
        vista.eliminarFila();
    }

    private void actualizaFila() {
        JTable tabla=vista.getTable();
        int i=tabla.getSelectedRow();
        if(i>=0){
            tabla.getModel().setValueAt(vista.getTextField1(),i,0);
            tabla.getModel().setValueAt(vista.getTextField2(),i,1);
            tabla.getModel().setValueAt(vista.getTextField3(),i,2);
            tabla.getModel().setValueAt(vista.getTextField4(),i,3);
            tabla.getModel().setValueAt(vista.getTextField5(),i,4);
            tabla.getModel().setValueAt(vista.getTextField6(),i,5);
        }
    }

    private void imprimirTabla() {
        JTable tabla=vista.getTable();
        MessageFormat header=new MessageFormat("BASE DATOS ESTUDIANTES");
        MessageFormat footer=new MessageFormat("PAGINA {0,number,integer}");
        try{
            tabla.print(JTable.PrintMode.FIT_WIDTH,header,footer);
        }catch(PrinterException e){
            vista.errorMessage("No printer found: "+e.getMessage());
        }
    }

    private void cargaEstudiante() {
        modelo.setId(Integer.parseInt(vista.getTextField1()));
        modelo.setNombre(vista.getTextField2());
        modelo.setApellido(vista.getTextField3());
        modelo.setDireccion(vista.getTextField4());
        modelo.setCodigoPostal(vista.getTextField5());
        modelo.setTelefono(vista.getTextField6());
    }


    private class TableActionListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        JTable jTable=vista.getTable();
        int i=jTable.getSelectedRow();
        vista.setTextField1(jTable.getModel().getValueAt(i,0).toString());
        vista.setTextField2(jTable.getModel().getValueAt(i,1).toString());
        vista.setTextField3(jTable.getModel().getValueAt(i,2).toString());
        vista.setTextField4(jTable.getModel().getValueAt(i,3).toString());
        vista.setTextField5(jTable.getModel().getValueAt(i,4).toString());
        vista.setTextField6(jTable.getModel().getValueAt(i,5).toString());
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
    }
}
