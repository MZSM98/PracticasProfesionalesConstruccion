package logica.interfaces;

import accesoadatos.dto.ProyectoDTO;
import java.sql.SQLException;
import java.io.IOException;

public interface InterfazProyectoDAO {
    
    boolean insertarProyecto(ProyectoDTO proyecto) throws SQLException, IOException;
    
    boolean eliminarProyecto(String tituloProyecto) throws SQLException, IOException;
    
    boolean editarProyecto(ProyectoDTO proyecto) throws SQLException, IOException;
    
    ProyectoDTO buscarProyecto(String tituloProyecto) throws SQLException, IOException;
    
}
