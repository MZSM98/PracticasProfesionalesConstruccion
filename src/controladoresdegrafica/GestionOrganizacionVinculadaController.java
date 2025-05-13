package controladoresdegrafica;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GestionOrganizacionVinculadaController{
    
    private static final Logger LOG = LogManager.getLogger(GestionOrganizacionVinculadaController.class);
    
    @FXML
    private Button botonSalir;    
        
    @FXML
    private void abrirRegistroOV(ActionEvent event) {
        
        try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/grafica/FXMLRegistroOV.fxml"));
                Parent root = loader.load();            
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Registro de Organizaciones");
                stage.setScene(new Scene(root));
                stage.showAndWait();

            } catch (IOException ex) {
            LOG.error("Error al cargar la ventana de registro OV: " + ex.getMessage());
        }
    }
    
    @FXML
    private void editarOV(ActionEvent event) {
        // TODO implementación del botón Editar
    }
    
    @FXML
    private void salirAMenuPrincipal(ActionEvent event) {
        Stage ventanaActual = (Stage) botonSalir.getScene().getWindow();
        ventanaActual.close();
    }
}