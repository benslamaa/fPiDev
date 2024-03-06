package com.example.fpidev.interfaces;

import com.example.fpidev.LoginController;
import com.example.fpidev.entities.Encryptor;
import com.example.fpidev.entities.User;
import com.example.fpidev.utils.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServiceUser implements IService <User> {
    Encryptor encryptor = new Encryptor();

    byte[] encryptionKey = {65, 12, 12, 12, 12, 12, 12, 12, 12,
            12, 12, 12, 12, 12, 12, 12};
    private Connection cnx;
    private Statement st;
    private PreparedStatement pst;

    public ServiceUser() {
        cnx = DBConnection.getConnection();
    }

    public void changeScreen(ActionEvent event, String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(LoginController.class.getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajouter(User user) throws SQLException, NoSuchAlgorithmException {
        String hashedPassword = encryptor.encryptString(user.getMdp());
        String req = "INSERT INTO `users`(`pseudo`, `nom`, `prenom`, `email`, `mdp`, `role`, `cin`, `numtel`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, user.getPseudo());
        ps.setString(2, user.getNom());
        ps.setString(3, user.getPrenom());
        ps.setString(4, user.getEmail());
        ps.setString(5, hashedPassword );
        ps.setString(6, user.getRole());
        ps.setInt(7, user.getCin());
        ps.setInt(8, user.getNumtel());
        ps.executeUpdate();
        System.out.println("Personne Ajoutée");
    }


    @Override
    public void supprimer(User user) throws SQLException {

    }

    @Override
    public void modifier(User user) throws SQLException {

    }

    @Override
    public List<User> afficher() {
        return null;
    }

    public boolean pseudoExiste(String pseudo) throws SQLException {
        String req = "SELECT * FROM `users` WHERE pseudo=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }

    public boolean utilisateurLoggedIn(String pseudo, String mdp) throws SQLException, InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String req = "SELECT mdp FROM `users` WHERE pseudo=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            String mdpCrypteDB = rs.getString("mdp");
            String mdpCrypteInput = encryptor.encryptString(mdp);
            return mdpCrypteInput.equals(mdpCrypteDB);
        } else {
            return false;
        }
    }
    public User afficherParPseudo(String pseudo) throws SQLException {
        User u = new User();
        String req = "SELECT * FROM `users` WHERE pseudo=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, pseudo);
        ResultSet rs = ps.executeQuery(); // Remove req from executeQuery
        while (rs.next()){
            u = new User(rs.getString("pseudo"),rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getString("mdp"), rs.getString("role"),rs.getInt("cin"),rs.getInt("numtel"));
        }
        return u;
    }
    public List<User> afficherParRole(String role){
        List<User> utilisateurs = new ArrayList<>() ; // Initialize the ObservableList
        String req = "SELECT * FROM `users` WHERE `role`=?";
        try {
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, role);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                User u = new User();
                u.setPseudo(rs.getString("PSEUDO"));
                u.setCin(rs.getInt("CIN"));
                u.setNom(rs.getString("NOM"));
                u.setPrenom(rs.getString("PRENOM"));
                u.setNumtel(rs.getInt("NUMTEL"));
                u.setEmail(rs.getString("EMAIL"));
                u.setMdp(rs.getString("MDP"));
                u.setRole(rs.getString("ROLE"));
                utilisateurs.add(u);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return utilisateurs;
    }

    public void modifierMdp(User user, String mdp) throws SQLException {
        String req="UPDATE `users` SET `mdp`=? WHERE pseudo=?";
        PreparedStatement ps= cnx.prepareStatement(req);
        ps.setString(1, mdp);
        ps.setString(2, user.getPseudo());
        ps.executeUpdate();
        System.out.println("Personne modifie");
    }

}

