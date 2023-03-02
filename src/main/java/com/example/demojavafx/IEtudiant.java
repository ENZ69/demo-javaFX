package com.example.demojavafx;

import javafx.collections.ObservableList;

import java.util.List;

public interface IEtudiant {

    public int addEtudiant(Etudiants e);
    public ObservableList<Etudiants> list();
    public int update(Etudiants e);
    public int delete(int id);

}
