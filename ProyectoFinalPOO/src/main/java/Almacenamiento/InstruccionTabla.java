package Almacenamiento;

import UserInterface.PanelInicio;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InstruccionTabla {
    ConexionBD conexionBD;
    PanelInicio panelinicioauxiliar;
    public InstruccionTabla(PanelInicio panelInicio){
        this.conexionBD = new ConexionBD();
        this.panelinicioauxiliar = panelInicio;
    }









    public void agregarRandom(int idUsuario, int idCarta) {

        String consulta = "SELECT * FROM proyectoPokemon.pokemon WHERE id = '" + idCarta + "';";
        String resultado = "";
        String dueñoDeCarta = "";
        try {
            ResultSet rs = conexionBD.conexion.createStatement().executeQuery(consulta);
            resultado = rs.toString();
            while (rs.next()) {
                dueñoDeCarta = rs.getString("id_usuario");
            }
        } catch (SQLException e) {

        }
        if (!dueñoDeCarta.equals(null)) {
            String query = "UPDATE proyectoPokemon.pokemon SET id_usuario = '" + idUsuario + "' WHERE (id = '" + idCarta + "');";
            try {
                PreparedStatement stmt = conexionBD.conexion.prepareStatement(query);
                stmt.execute();
            } catch (SQLException e) {
            }
        }
    }


    public String retornarID(String nick, String password) throws SQLException {
        System.out.println("Entramos a retornarID");
        String id_usuarioString="";
        System.out.println(nick);
        System.out.println(password);
        String resultado="";
        String consulta = "SELECT id_usuario from usuario where (nombre,contrasenia) = ('"+nick+"','"+password+"')";
        try {
            System.out.println("entramos al try del metodo retornar id");
            ResultSet rs = conexionBD.conexion.createStatement().executeQuery(consulta);
            resultado = rs.toString();
            while (rs.next()) {
                id_usuarioString = rs.getString("id_usuario");
                System.out.println("Estas es mi id: "+id_usuarioString);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Estas es mi id FINAL: "+id_usuarioString);
        return id_usuarioString;
    }
}
