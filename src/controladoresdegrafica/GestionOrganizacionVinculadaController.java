package controladoresdegrafica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import accesoadatos.dto.OrganizacionVinculadaDTO;
import logica.dao.OrganizacionVinculadaDAO;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionOrganizacionVinculadaController implements Initializable {
    
    private static final Logger LOG = Logger.getLogger(GestionOrganizacionVinculadaController.class);
    
    @FXML
    private Button botonSalir;
        
    @FXML
    private TableView<OrganizacionVinculadaDTO> tableOrganizacionesVinculadas;
    
    @FXML
    private TableColumn<OrganizacionVinculadaDTO, String> columnRFC;
    
    @FXML
    private TableColumn<OrganizacionVinculadaDTO, String> columnNombre;
    
    @FXML
    private TableColumn<OrganizacionVinculadaDTO, String> columnTelefono;
    
    @FXML
    private TableColumn<OrganizacionVinculadaDTO, String> columnDireccion;
    
    @FXML
    private TableColumn<OrganizacionVinculadaDTO, String> columnEstado;
    
    private OrganizacionVinculadaDAO organizacionVinculadaDAO;
    private ObservableList<OrganizacionVinculadaDTO> listaOrganizacionesVinculadas;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        organizacionVinculadaDAO = new OrganizacionVinculadaDAO();
        configurarColumnas();
        cargarOrganizacionesVinculadas();
    }
    
    private void configurarColumnas() {
        columnRFC.setCellValueFactory(new PropertyValueFactory<>("rfcMoral"));
        columnNombre.setCellValueFactory(new PropertyValueFactory<>("nombreOV"));
        columnTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoOV"));
        columnDireccion.setCellValueFactory(new PropertyValueFactory<>("direccionOV"));
        columnEstado.setCellValueFactory(new PropertyValueFactory<>("estadoOV"));
    }
    
    private void cargarOrganizacionesVinculadas() {
        try {
            
            List<OrganizacionVinculadaDTO> organizaciones = organizacionVinculadaDAO.listarOrganizacionesVinculadas();
            listaOrganizacionesVinculadas = FXCollections.observableArrayList(organizaciones);
            tableOrganizacionesVinculadas.setItems(listaOrganizacionesVinculadas);
            
        } catch (SQLException e) {
            
            LOG.error("Error al cargar las organizaciones vinculadas: " + e.getMessage());
            mostrarAlerta("Error", "No se pudieron cargar las organizaciones vinculadas: " + e.getMessage(), Alert.AlertType.ERROR);
            
        } catch (IOException e){
            LOG.error("No se lograron cargar los registros",e);
            mostrarAlerta("Error", "No se pudieron cargar las organizaciones vinculadas: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
        
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
            
            cargarOrganizacionesVinculadas();

        } catch (IOException e) {
            LOG.error("Error al cargar la ventana de registro OV: " + e.getMessage());
            mostrarAlerta ("Error", "Ha ocurrido un error, intentelo más tarde" + e.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    
    @FXML
    private void editarOV(ActionEvent event) {
        
        OrganizacionVinculadaDTO organizacionSeleccionada = tableOrganizacionesVinculadas.getSelectionModel().getSelectedItem();
        
        if (organizacionSeleccionada == null) {
            mostrarAlerta("Aviso", "Por favor, seleccione una organización para editar", Alert.AlertType.WARNING);
            return;
        }
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/grafica/FXMLRegistroOV.fxml"));
            Parent root = loader.load();
            
            RegistroOrganizacionVinculadaController controlador = loader.getController();
            controlador.setModoEdicion(true);
            controlador.setOrganizacion(organizacionSeleccionada);
            
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Editar Organización");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
            cargarOrganizacionesVinculadas();
            
        } catch (IOException ex) {
            LOG.error("Error al cargar la ventana de edición OV: " + ex.getMessage());
            mostrarAlerta("Error", "No se pudo abrir la ventana de edición: " + ex.getMessage(), Alert.AlertType.ERROR);
        }
        
    }
    
    @FXML
    private void cambiarEstadoOV(ActionEvent event) {
        
        OrganizacionVinculadaDTO organizacionSeleccionada = tableOrganizacionesVinculadas.getSelectionModel().getSelectedItem();
        
        if (organizacionSeleccionada == null) {
            mostrarAlerta("Aviso", "Por favor, seleccione una organización para cambiar su estado", Alert.AlertType.WARNING);
            return;
        }
        
        String estadoActual = organizacionSeleccionada.getEstadoOV();
        String nuevoEstado = "activo".equals(estadoActual) ? "inactivo" : "activo";
        
        organizacionSeleccionada.setEstadoOV(nuevoEstado);
        
        try {
            
            boolean actualizacionExitosa = organizacionVinculadaDAO.editarOrganizacionVinculada(organizacionSeleccionada);
            
            if (actualizacionExitosa) {
                
                mostrarAlerta("Éxito", "Estado de la organización cambiado a: " + nuevoEstado, Alert.AlertType.INFORMATION);
                cargarOrganizacionesVinculadas(); // Recargar la tabla para reflejar el cambio
            } else {
                
                mostrarAlerta("Error", "No se pudo cambiar el estado de la organización", Alert.AlertType.ERROR);
            }
        } catch (SQLException e) {
            
            LOG.error("Error de conexión con la base de datos: " + e.getMessage());
            mostrarAlerta("Error", "Error al conectar con la base de datos: " + e.getMessage(), Alert.AlertType.ERROR);
        } catch (IOException e){
            
            LOG.error("Error al cambiar estado de la organización: " + e.getMessage());
            mostrarAlerta("Error", "Error al actualizar el registro" + e.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    @FXML
    private void salirAMenuPrincipal(ActionEvent event) {
        Stage ventanaActual = (Stage) botonSalir.getScene().getWindow();
        ventanaActual.close();
    }
    
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}