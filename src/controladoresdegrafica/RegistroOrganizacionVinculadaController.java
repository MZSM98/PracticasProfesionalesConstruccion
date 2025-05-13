
package controladoresdegrafica;

import logica.dao.OrganizacionVinculadaDAO;
import accesoadatos.dto.OrganizacionVinculadaDTO;
import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class RegistroOrganizacionVinculadaController {    
    
    private static final Logger LOG = Logger.getLogger(GestionOrganizacionVinculadaController.class);
    
    @FXML
    private TextField textRfcOV, textNombreOV, textTelefonoOV, textDireccionOV;
    
    @FXML 
    private Button botonCancelarRegistroOV;
    
    private OrganizacionVinculadaDAO organizacionVinculadaDAO;
    private OrganizacionVinculadaDTO organizacionVinculadaDTO;
    
    
    @FXML
    private void registrarOrganizacionVinculada(ActionEvent evento) {
        
        organizacionVinculadaDTO = new OrganizacionVinculadaDTO();
        organizacionVinculadaDAO = new OrganizacionVinculadaDAO();
        
        organizacionVinculadaDTO.setRfcMoral(textRfcOV.getText());
        organizacionVinculadaDTO.setNombreOV(textNombreOV.getText());
        organizacionVinculadaDTO.setTelefonoOV(textTelefonoOV.getText());
        organizacionVinculadaDTO.setDireccionOV(textDireccionOV.getText());
        
        if (validarCamposVacios() != false) {
            try {
                
                organizacionVinculadaDAO.insertarOrganizacionVinculada(organizacionVinculadaDTO);
                mostrarAlerta("Éxito", "Organización Registrada", Alert.AlertType.INFORMATION);
                limpiarCampos();
                
            } catch (SQLException e) {
                LOG.error("Error con la conexion de base de datos", e);
                mostrarAlerta("Error", "Error de conexión con la base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
            } catch (IOException e) {
                LOG.error("Error al registrar la organización",e);
                mostrarAlerta("Error", "Error al registrar la organización: " + e.getMessage(), Alert.AlertType.ERROR);
            } 
        }
    }
    
    @FXML
    private void cancelarRegistroOrganizacionVinculada(ActionEvent evento) {
        Stage stage = (Stage) botonCancelarRegistroOV.getScene().getWindow();
        stage.close();
    }
    
    private void limpiarCampos() {
        textRfcOV.setText("");
        textNombreOV.setText("");
        textTelefonoOV.setText("");
        textDireccionOV.setText("");
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    
    private boolean validarCamposVacios() {
        
        if (textRfcOV.getText().isEmpty() || textNombreOV.getText().isEmpty() ||
            textTelefonoOV.getText().isEmpty() || textDireccionOV.getText().isEmpty()) {
            
            mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }
}