package com.example.demojavafx;

import com.example.demojavafx.tools.Notification;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import com.example.demojavafx.Etudiants;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccueilController implements Initializable {

    private IEtudiant et = new EtudiantImpl();
    @FXML
    private Button DeleteBtn;
    @FXML
    private Button EmptyBtn;
    @FXML
    private Button SaveBtn;
    @FXML
    private Button UpdateBtn;
    @FXML
    private TextField classeTfd;
    @FXML
    private TextField nomTfd;
    @FXML
    private TextField prenomTfd;
    @FXML
    private ComboBox<String> sexeCbx;
    @FXML
    private TableView<Etudiants> tableauTb;
    @FXML
    private TextField telephoneTfd;
    @FXML
    private TableColumn<Etudiants, Integer> idClmn;
    @FXML
    private TableColumn<Etudiants, String> nomClmn;
    @FXML
    private TableColumn<Etudiants, String> prenomClmn;
    @FXML
    private TableColumn<Etudiants, String> sexeClmn;
    @FXML
    private TableColumn<Etudiants, String> classeClmn;
    @FXML
    private TableColumn<Etudiants, String> TelClmn;

    int id = 0;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList("Femme", "Homme");
        sexeCbx.setItems(list);
        afficheEtudiants();
    }

    public void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /*public void showSuccessDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }*/

    @FXML
    void addValues(ActionEvent event) throws IOException {
        String nom = nomTfd.getText();
        String prenom = prenomTfd.getText();
        String sexe = sexeCbx.getSelectionModel().getSelectedItem().toString();
        String classe = classeTfd.getText();
        String tel = telephoneTfd.getText();
        Etudiants e = new Etudiants(nom, prenom, sexe, classe, tel);
        if(et.addEtudiant(e) == 1){
            afficheEtudiants();
            Notification.NotifSuccess("Succès","Etudiant enregistré avec succès");
            nomTfd.setText("");
            prenomTfd.setText("");
            sexeCbx.getSelectionModel().clearSelection();
            classeTfd.setText("");
            telephoneTfd.setText("");
        }else{
            Notification.NotifError("Erreur","Impossible d'ajouter' l'étudiant");
        }

    }

    @FXML
    void videChamp(ActionEvent event) {
        nomTfd.setText("");
        prenomTfd.setText("");
        sexeCbx.getSelectionModel().clearSelection();
        classeTfd.setText("");
        telephoneTfd.setText("");
    }

    public void afficheEtudiants(){
        ObservableList<Etudiants> listeEtudiants = et.list();
        tableauTb.setItems(listeEtudiants);
        idClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, Integer>("id"));
        nomClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, String>("nom"));
        prenomClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, String>("prenom"));
        sexeClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, String>("sexe"));
        classeClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, String>("classe"));
        TelClmn.setCellValueFactory(new PropertyValueFactory<Etudiants, String>("tel"));
    }

    @FXML
    void afficheInfoFormulaire(MouseEvent event) {
        Etudiants etudiant = tableauTb.getSelectionModel().getSelectedItem();
        id = etudiant.getId();
        nomTfd.setText(etudiant.getNom());
        prenomTfd.setText(etudiant.getPrenom());
        sexeCbx.setValue(etudiant.getSexe());
        classeTfd.setText(etudiant.getClasse());
        telephoneTfd.setText(etudiant.getTel());
        SaveBtn.setDisable(true);
    }

    @FXML
    void updateValues(ActionEvent event) {
        String nom = nomTfd.getText();
        String prenom = prenomTfd.getText();
        String sexe = sexeCbx.getSelectionModel().getSelectedItem().toString();
        String classe = classeTfd.getText();
        String tel = telephoneTfd.getText();
        Etudiants e = new Etudiants(nom, prenom, sexe, classe, tel);
        e.setId(id);
        if(et.update(e) == 1){
            afficheEtudiants();
            Notification.NotifSuccess("Succès","Etudiant modifié avec succès");
            nomTfd.setText("");
            prenomTfd.setText("");
            sexeCbx.getSelectionModel().clearSelection();
            classeTfd.setText("");
            telephoneTfd.setText("");
        }else{
            Notification.NotifError("Erreur","Impossible de modifier l'étudiant");
        }

    }

    @FXML
    void DeleteValues(ActionEvent event) {
       if(et.delete(id) == 1) {
           Notification.NotifSuccess("Succès","Etudiant supprimé");
           afficheEtudiants();
           nomTfd.setText("");
           prenomTfd.setText("");
           sexeCbx.getSelectionModel().clearSelection();
           classeTfd.setText("");
           telephoneTfd.setText("");
       }else{
           Notification.NotifError("Erreur","Suppression échouée!");
       }

    }

}
