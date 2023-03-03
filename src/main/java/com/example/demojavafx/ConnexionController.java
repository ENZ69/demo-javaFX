package com.example.demojavafx;

import com.example.demojavafx.tools.Notification;
import com.example.demojavafx.tools.Outils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ConnexionController {

    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
    private IUser userDao = new UserImpl();
    @FXML
    private Button connexionBtn;

    @FXML
    private TextField emailTfd;

    @FXML
    private PasswordField passwordTfd;

    @FXML
    void getLogin(ActionEvent event) throws IOException {
        String email = emailTfd.getText();
        String password = passwordTfd.getText();
        if(email == "" || password == ""){
            Notification.NotifError("Erreur","Champs obligatoires");
        }else{
            User user = userDao.seConnecter(email, password);
            if(user == null){
                Notification.NotifError("Erreur","Email ou Mot de passe incorrect!");
            }else {
                /*Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/accueil.fxml"));
                Scene scene = new Scene(parent);
                Stage currentStage = (Stage) connexionBtn.getScene().getWindow();//ici je reccupere la fenetre ouverte
                Stage homeStage = new Stage();
                homeStage.setTitle("Accueil");
                homeStage.setScene(parent.getScene());
                currentStage.close();
                homeStage.show();*/
                try {
                    Notification.NotifSuccess("Succès","Connexion réussie");
                    Outils.load(event, "Accueil", "/Fxml/accueil.fxml");
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
