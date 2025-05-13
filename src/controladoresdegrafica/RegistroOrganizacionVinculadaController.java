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
    
    @FXML
    private Button botonRegistrarOrganizacionVinculada;
    
    private OrganizacionVinculadaDAO organizacionVinculadaDAO;
    private OrganizacionVinculadaDTO organizacionVinculadaDTO;
    
    private boolean modoEdicion = false;
    
    public void initialize() {
        organizacionVinculadaDAO = new OrganizacionVinculadaDAO();
    }
    
    public void cambiarAModoEdicion(boolean modoEdicion) {
        this.modoEdicion = modoEdicion;
        if (modoEdicion) {
            botonRegistrarOrganizacionVinculada.setText("Actualizar");
        }
    }
    
    public void llenarCamposEditablesOrganizacionVinculada(OrganizacionVinculadaDTO organizacion) {
        
        this.organizacionVinculadaDTO = organizacion;
        
        textRfcOV.setText(organizacion.getRfcMoral());
        textNombreOV.setText(organizacion.getNombreOV());
        textTelefonoOV.setText(organizacion.getTelefonoOV());
        textDireccionOV.setText(organizacion.getDireccionOV());
        
        textRfcOV.setDisable(modoEdicion);
        
    }
    
    @FXML
    private void registrarOrganizacionVinculada(ActionEvent evento) {
        
        if (validarCamposVacios() == false) {
            return;
        }
        
        if (modoEdicion) {
            
            organizacionVinculadaDTO.setNombreOV(textNombreOV.getText());
            organizacionVinculadaDTO.setTelefonoOV(textTelefonoOV.getText());
            organizacionVinculadaDTO.setDireccionOV(textDireccionOV.getText());
            
            try {
                boolean actualizacionExitosa = organizacionVinculadaDAO.editarOrganizacionVinculada(organizacionVinculadaDTO);
                
                if (actualizacionExitosa) {
                    mostrarAlerta("Éxito", "Organización actualizada correctamente", Alert.AlertType.INFORMATION);
                    Stage stage = (Stage) botonCancelarRegistroOV.getScene().getWindow();
                    stage.close();
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar la organización", Alert.AlertType.ERROR);
                }
                
            } catch (SQLException e) {
                LOG.error("Error con la conexión de base de datos", e);
                mostrarAlerta("Error", "Error de conexión con la base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
            } catch (IOException e) {
                LOG.error("Error al actualizar la organización", e);
                mostrarAlerta("Error", "Error al actualizar la organización: " + e.getMessage(), Alert.AlertType.ERROR);
            }
            
        } else {
            
            organizacionVinculadaDTO = new OrganizacionVinculadaDTO();
            
            organizacionVinculadaDTO.setRfcMoral(textRfcOV.getText());
            organizacionVinculadaDTO.setNombreOV(textNombreOV.getText());
            organizacionVinculadaDTO.setTelefonoOV(textTelefonoOV.getText());
            organizacionVinculadaDTO.setDireccionOV(textDireccionOV.getText());
            
            try {
                organizacionVinculadaDAO.insertarOrganizacionVinculada(organizacionVinculadaDTO);
                mostrarAlerta("Éxito", "Organización Registrada", Alert.AlertType.INFORMATION);
                limpiarCampos();
                
            } catch (SQLException e) {
                LOG.error("Error con la conexion de base de datos", e);
                mostrarAlerta("Error", "Error de conexión con la base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
                
            } catch (IOException e) {
                LOG.error("Error al registrar la organización", e);
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