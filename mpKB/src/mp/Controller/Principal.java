package mp.Controller;

import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application {
    

    @Override
    public void start(Stage stage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("FXML_Login.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("KB - Login");
            
            stage.getIcons().add(new Image("mp/122315-solid-media-elements/KB.png"));
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setResizable(false);
            stage.show();
            
    }

    public static void main(String[] args) {
        launch(args);
        
    }
    
}
