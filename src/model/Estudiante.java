package model;

import sqlConnection.SQLExecutor;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Estudiante {
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String codigoPostal;
    private String telefono;

    public Estudiante() {
    }

    public Estudiante(int id, String nombre, String apellido, String direccion, String codigoPostal, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    public List<Estudiante> cargaDatos() {
        List<Estudiante> lista=new ArrayList<>();
        try{
            SQLExecutor sqlExecutor=new SQLExecutor();
            ResultSet rs=sqlExecutor.ejecutaQuery("SELECT * FROM ESTUDIANTE");
            while(rs.next()){
                lista.add(new Estudiante(
                   rs.getInt("id"),
                   rs.getString("nombre"),
                   rs.getString("apellido"),
                   rs.getString("direccion"),
                   rs.getString("cod_pos"),
                   rs.getString("telefono")
                ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    public void insertaEstudiante() {
        SQLExecutor sqlExecutor=new SQLExecutor();
        String [] valores=new String[7];
        valores[0]="insert into estudiante (id,nombre,apellido,direccion,cod_pos,telefono) values (?,?,?,?,?,?)";
        valores[1]=String.valueOf(getId());
        valores[2]=getNombre();
        valores[3]=getApellido();
        valores[4]=getDireccion();
        valores[5]=getCodigoPostal();
        valores[6]=getTelefono();
        sqlExecutor.prepareStatement(valores);
    }

    public void eliminaEstudiante() {
        SQLExecutor sqlExecutor=new SQLExecutor();
        String valores[] =new String[2];
        valores[0]="DELETE FROM ESTUDIANTE WHERE id=?";
        valores[1]=Integer.toString(getId());
        sqlExecutor.prepareStatement(valores);
    }

    public void actualizarEstudiante() {
        SQLExecutor sqlExecutor=new SQLExecutor();
        String [] valores=new String[8];
        valores[0]="update estudiante set id=?,nombre=?,apellido=?,direccion=?,cod_pos=?,telefono=? where id=?";
        valores[1]=String.valueOf(getId());
        valores[2]=getNombre();
        valores[3]=getApellido();
        valores[4]=getDireccion();
        valores[5]=getCodigoPostal();
        valores[6]=getTelefono();
        valores[7]=String.valueOf(getId());
        sqlExecutor.prepareStatement(valores);
    }
}
