package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

import static javafx.scene.paint.Color.DARKRED;

public class Controller {

    @FXML
    private TextField idInputSenha;

    @FXML
    private TextField idInputUser;

    @FXML
    private Button idLoginEntrar;

    @FXML
    private Tab idHome;

    @FXML
    private Label lblMessage;

    public void Login(ActionEvent actionEvent) throws IOException {
        String checkUser = idInputUser.getText().toString();
        String checkPw = idInputSenha.getText().toString();
        String user = "fabricio";
        String passwd = "12345";
        if(checkUser.equals(user) && checkPw.equals(passwd)){
            lblMessage.setText("Acesso autorizado!");
            idHome.setDisable(false);
        }
        else{
            lblMessage.setText("Usuario ou senha incorreto!");
            idHome.setDisable(true);
        }
        idInputUser.setText("");
        idInputSenha.setText("");

    }
}
