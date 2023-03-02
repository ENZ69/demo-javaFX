package com.example.demojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EtudiantImpl implements IEtudiant{
    DBConnexion db = new DBConnexion();
    private int ok;
    private ResultSet rs;
    @Override
    public int addEtudiant(Etudiants e) {
        String sql = "INSERT INTO etudiant VALUES(NULL, ?, ?, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, e.getNom());
            db.getPstm().setString(2, e.getPrenom());
            db.getPstm().setString(3, e.getSexe());
            db.getPstm().setString(4, e.getClasse());
            db.getPstm().setString(5, e.getTel());
            ok = db.executeMaj();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public ObservableList<Etudiants> list() {
        String sql = "SELECT * FROM etudiant";
        ObservableList<Etudiants> etudiants = FXCollections.observableArrayList();
        try {
            db.initPrepar(sql);
            rs = db.executeSelect();
            while(rs.next()){
                Etudiants etudiant = new Etudiants();
                etudiant.setId(rs.getInt(1));
                etudiant.setNom(rs.getString(2));
                etudiant.setPrenom(rs.getString(3));
                etudiant.setSexe(rs.getString(4));
                etudiant.setClasse(rs.getString(5));
                etudiant.setTel(rs.getString(6));
                etudiants.add(etudiant);
            }
    } catch (Exception e) {
            e.printStackTrace();
        }
        return etudiants;
    }

    @Override
    public int update(Etudiants e) {
        String sql = "UPDATE etudiant SET nom = ?, prenom = ?, sexe = ?, classe = ?, tel = ? WHERE id = ?";
        try{
            db.initPrepar(sql);
            db.getPstm().setString(1, e.getNom());
            db.getPstm().setString(2, e.getPrenom());
            db.getPstm().setString(3, e.getSexe());
            db.getPstm().setString(4, e.getClasse());
            db.getPstm().setString(5, e.getTel());
            db.getPstm().setInt(6, e.getId());
            ok = db.executeMaj();
            db.closeConnexion();
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ok;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id =?";
        try{
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            ok = db.executeMaj();
            db.closeConnexion();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ok;
    }

}
