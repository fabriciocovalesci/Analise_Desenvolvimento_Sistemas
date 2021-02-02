package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class Controller {

    @FXML
    private TextField idUser;

    @FXML
    private PasswordField idPass;

    @FXML
    private Button idEntrar;

    @FXML
    private Button idCadastro;

    @FXML
    private Label lblMessage;

    public void Entrar(ActionEvent actionEvent) {
        String checkUser = idUser.getText().toString();
        String checkPw = idPass.getText().toString();
        String user = "fabricio";
        String passwd = "12345";
        if(checkUser.equals(user) && checkPw.equals(passwd)){
            lblMessage.setText("Acesso autorizado!");
            lblMessage.setTextFill(Color.GREEN);
        }
        else{
            lblMessage.setText("Usuario ou senha incorreto!");
            lblMessage.setTextFill(Color.RED);
        }
        idUser.setText("");
        idPass.setText("");
    }


    public void Cadastrar(ActionEvent actionEvent) {
    }
}
