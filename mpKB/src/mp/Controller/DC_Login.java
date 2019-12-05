    package mp.Controller;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mp.Model.User;

   public class DC_Login {
       
    protected User user;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField username;

    @FXML
    private void logBtn(ActionEvent event) throws IOException{
        user = new User();
        //KeyListener key;  
            if(user.login(username.getText(), password.getText())){
                Parent root = FXMLLoader.load(getClass().getResource("FXML_InterfaceMP.fxml"));
                Scene tableViewScene = new Scene(root);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setResizable(true);
                window.setScene(tableViewScene);
                window.show();
                }
            else{
                errorLabel.setText("Incorrect Password or Username!");
            }           

    }

    @FXML
    private void registerWindow(MouseEvent event) throws Exception{
            Parent root = FXMLLoader.load(getClass().getResource("FXML_Register.fxml"));
            Scene tableViewScene = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

    }
}
