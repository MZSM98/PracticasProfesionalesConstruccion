package logica.interfaces;

import accesoadatos.dto.ProyectoDTO;
import java.io.IOException;
import java.sql.SQLException;

public interface InterfazProyectoDAO {
    
    boolean insertarProyecto(ProyectoDTO proyecto) throws SQLException, IOException;
    boolean eliminarProyecto(int proyectoID) throws SQLException, IOException;
    boolean editarProyecto(ProyectoDTO proyecto) throws SQLException, IOException;
    ProyectoDTO buscarProyectoPorNombre(String titulo) throws SQLException, IOException;    
}