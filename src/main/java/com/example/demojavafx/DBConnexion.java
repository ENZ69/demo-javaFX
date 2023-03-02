package com.example.demojavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnexion {
        private Connection cnx;
        //pour les requets prepare
        private PreparedStatement pstm;
        //pour les select(select)
        private ResultSet rs;
        //pour les requetes de mis a jour (Inseert ,delete,Update)
        private int ok;

        //Methode d'ouverture de la connexion

        private Connection getConnenxion(){
            //parametre de connnection
            String url="jdbc:mysql://localhost:3306/db_javaFX";
            String User="root";
            String passwords="";

            try {
                //cahrgement dupilote
                Class.forName("com.mysql.jdbc.Driver");
                //Ouverture de la connection
                cnx = DriverManager.getConnection(url,User,passwords);

            }catch (Exception ex){
                ex.printStackTrace();
            }
            return cnx;
        }

        public void initPrepar(String sql){
            try {
                getConnenxion();
                pstm = cnx.prepareStatement(sql);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //Méthode qui permet d'executer les requêtes select
        public ResultSet executeSelect(){
            rs = null;
            try{
                rs = pstm.executeQuery();
            }catch (Exception e){
                e.printStackTrace();
            }
            return rs;
        }

        //Méthode qui execute les requêtes de MAJ (insert, update, delete)
        public int executeMaj(){
            try{
                ok = pstm.executeUpdate();
            }catch (Exception e){
                e.printStackTrace();
            }
            return ok;
        }

        //Méthode pour fermer la connexion à la BD
        public void closeConnexion(){
            try{
                if(cnx != null)
                    cnx.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public PreparedStatement getPstm(){
            return pstm;
        }
    }

