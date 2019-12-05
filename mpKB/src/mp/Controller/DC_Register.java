package mp.Controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import mp.Model.User;

public class DC_Register {
    
    private User userReg;
    
    @FXML
    private TextField txtField;
    
    @FXML
    private Label errorLabel;
    
    @FXML
    private PasswordField passField;
    
    @FXML
    private PasswordField confPass;
    
    @FXML
    private void loginWindow(MouseEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXML_Login.fxml"));
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
              
    }
    
    @FXML
    private void registerBtt(ActionEvent event) throws IOException{
        userReg = new User();
            if(userReg.verifyUser(txtField.getText()) && passField.getText().equals(confPass.getText())) {
                userReg.setUsername(txtField.getText());
                userReg.setPassword(passField.getText());

                userReg.register();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registration went successfully!");
                alert.getDialogPane().setGraphic(new ImageView("mp/122315-solid-media-elements/KB_Festeiro.png"));
                alert.setHeaderText("Thanks for choosing our service! We're grateful you belive us!");
                alert.setContentText("(YOU'RE GONNA BE SENT BACK TO LOGIN WINDOW!)");
                alert.showAndWait();
                
                Parent root = FXMLLoader.load(getClass().getResource("FXML_Login.fxml"));
                Scene tableViewScene = new Scene(root);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(tableViewScene);
                window.show();
            }
            else{
                if(!userReg.verifyUser(txtField.getText())){
                    errorLabel.setText("USERNAME ALREADY TAKEN!");
                } else{
                    errorLabel.setText("PASSWORDS DOESN'T MATCH!"); 
                }  
            }
 
    }
    
}
