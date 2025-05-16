package logica.dao;

import logica.interfaces.InterfazProyectoDAO;
import accesoadatos.dto.ProyectoDTO;
import accesoadatos.ConexionBD;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProyectoDAO implements InterfazProyectoDAO {

    Connection conexionBD;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoDeOperacion;

    @Override
    public boolean insertarProyecto(ProyectoDTO proyecto) throws SQLException, IOException {
        String insertarSQL = "INSERT INTO proyecto (titulo, periodoEscolar, descripcion, rfcMoral) VALUES (?, ?, ?, ?)";
        boolean insercionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(insertarSQL);
            declaracionPreparada.setString(1, proyecto.getTitulo());
            declaracionPreparada.setString(2, proyecto.getPeriodoEscolar());
            declaracionPreparada.setString(3, proyecto.getDescripcion());
            declaracionPreparada.setString(4, proyecto.getRfcMoral());
            declaracionPreparada.executeUpdate();
            insercionExitosa = true;
        } finally {
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return insercionExitosa;
    }

    @Override
    public boolean eliminarProyecto(int proyectoID) throws SQLException, IOException {
        String eliminarSQL = "DELETE FROM proyecto WHERE proyectoID = ?";
        boolean eliminacionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(eliminarSQL);
            declaracionPreparada.setInt(1, proyectoID);
            declaracionPreparada.executeUpdate();
            eliminacionExitosa = true;
        } finally {
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return eliminacionExitosa;
    }

    @Override
    public boolean editarProyecto(ProyectoDTO proyecto) throws SQLException, IOException {
        String actualizarSQL = "UPDATE proyecto SET titulo = ?, periodoEscolar = ?, descripcion = ?, rfcMoral = ? WHERE proyectoID = ?";
        boolean actualizacionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(actualizarSQL);
            declaracionPreparada.setString(1, proyecto.getTitulo());
            declaracionPreparada.setString(2, proyecto.getPeriodoEscolar());
            declaracionPreparada.setString(3, proyecto.getDescripcion());
            declaracionPreparada.setString(4, proyecto.getRfcMoral());
            declaracionPreparada.setInt(5, proyecto.getProyectoID());
            declaracionPreparada.executeUpdate();
            actualizacionExitosa = true;
        } finally {
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return actualizacionExitosa;
    }

    @Override
    public ProyectoDTO buscarProyectoPorNombre(String titulo) throws SQLException, IOException {
        String consultaSQL = "SELECT proyectoID, titulo, periodoEscolar, descripcion, rfcMoral FROM proyecto WHERE titulo = ?";
        ProyectoDTO proyecto = null;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(consultaSQL);
            declaracionPreparada.setString(1, titulo);
            resultadoDeOperacion = declaracionPreparada.executeQuery();

            if (resultadoDeOperacion.next()) {
                proyecto = new ProyectoDTO();
                proyecto.setProyectoID(resultadoDeOperacion.getInt("proyectoID"));
                proyecto.setTitulo(resultadoDeOperacion.getString("titulo"));
                proyecto.setPeriodoEscolar(resultadoDeOperacion.getString("periodoEscolar"));
                proyecto.setDescripcion(resultadoDeOperacion.getString("descripcion"));
                proyecto.setRfcMoral(resultadoDeOperacion.getString("rfcMoral"));
            }
        } finally {
            if (resultadoDeOperacion != null) resultadoDeOperacion.close();
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return proyecto;
    }
}