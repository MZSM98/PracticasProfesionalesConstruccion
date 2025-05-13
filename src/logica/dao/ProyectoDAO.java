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
        String insertarSQL = "INSERT INTO proyecto (tituloProyecto, descripcionProyecto, fechaInicioProyecto, fechaFinalProyecto, periodoEscolar) VALUES (?, ?, ?, ?, ?)";
        boolean insercionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(insertarSQL);
            declaracionPreparada.setString(1, proyecto.getTituloProyecto());
            declaracionPreparada.setString(2, proyecto.getDescripcionProyecto());
            declaracionPreparada.setDate(3, proyecto.getFechaInicioProyecto());
            declaracionPreparada.setDate(4, proyecto.getFechaFinalProyecto());
            declaracionPreparada.setString(5, proyecto.getPeriodoEscolar());
            declaracionPreparada.executeUpdate();
            insercionExitosa = true;
        } finally {
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return insercionExitosa;
    }

    @Override
    public boolean eliminarProyecto(String tituloProyecto) throws SQLException, IOException {
        String eliminarSQL = "DELETE FROM proyecto WHERE tituloProyecto = ?";
        boolean eliminacionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(eliminarSQL);
            declaracionPreparada.setString(1, tituloProyecto);
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
        String actualizarSQL = "UPDATE proyecto SET descripcionProyecto = ?, fechaInicioProyecto = ?, fechaFinalProyecto = ?, periodoEscolar = ? WHERE tituloProyecto = ?";
        boolean actualizacionExitosa = false;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(actualizarSQL);
            declaracionPreparada.setString(1, proyecto.getDescripcionProyecto());
            declaracionPreparada.setDate(2, proyecto.getFechaInicioProyecto());
            declaracionPreparada.setDate(3, proyecto.getFechaFinalProyecto());
            declaracionPreparada.setString(4, proyecto.getPeriodoEscolar());
            declaracionPreparada.setString(5, proyecto.getTituloProyecto());
            declaracionPreparada.executeUpdate();
            actualizacionExitosa = true;
        } finally {
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return actualizacionExitosa;
    }

    @Override
    public ProyectoDTO buscarProyecto(String tituloProyecto) throws SQLException, IOException {
        String consultaSQL = "SELECT tituloProyecto, descripcionProyecto, fechaInicioProyecto, fechaFinalProyecto, periodoEscolar FROM proyecto WHERE tituloProyecto = ?";
        ProyectoDTO proyecto = null;

        try {
            conexionBD = new ConexionBD().getConexionBD();
            declaracionPreparada = conexionBD.prepareStatement(consultaSQL);
            declaracionPreparada.setString(1, tituloProyecto);
            resultadoDeOperacion = declaracionPreparada.executeQuery();

            if (resultadoDeOperacion.next()) {
                proyecto = new ProyectoDTO();
                proyecto.setTituloProyecto(resultadoDeOperacion.getString("tituloProyecto"));
                proyecto.setDescripcionProyecto(resultadoDeOperacion.getString("descripcionProyecto"));
                proyecto.setFechaInicioProyecto(resultadoDeOperacion.getDate("fechaInicioProyecto"));
                proyecto.setFechaFinalProyecto(resultadoDeOperacion.getDate("fechaFinalProyecto"));
                proyecto.setPeriodoEscolar(resultadoDeOperacion.getString("periodoEscolar"));
            }
        } finally {
            if (resultadoDeOperacion != null) resultadoDeOperacion.close();
            if (declaracionPreparada != null) declaracionPreparada.close();
            if (conexionBD != null) conexionBD.close();
        }
        return proyecto;
    }
}
