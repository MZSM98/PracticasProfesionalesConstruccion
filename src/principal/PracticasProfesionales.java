
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class PracticasProfesionales extends Application {
    private static final Logger LOG = Logger.getLogger(PracticasProfesionales.class);
    
    @Override
    public void start(Stage stage) throws Exception {
        
        PropertyConfigurator.configure(getClass().getClassLoader().getResource("log4j.properties"));
        LOG.info("Se inició correctamente el log");
        
        Parent root = FXMLLoader.load(getClass().getResource("/grafica/FXMLMenuPrincipalCoordinador.fxml"));
               
        Scene scene = new Scene(root);
        
        stage.setTitle("Menu Principal");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}