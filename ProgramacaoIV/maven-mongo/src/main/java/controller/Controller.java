package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.PasswordUtils;
import model.Usuario;
import model.database.DAO_Usuario;

public class Controller {

    private boolean usuarioLogado = false;
    private Usuario usuario;

    @FXML
    private TabPane pane;
    @FXML
    private Tab tabVisualizar;
    @FXML
    private Tab tabExcluir;
    @FXML
    private Tab tabInserir;
    @FXML
    private Tab tabAtualizar;
    @FXML
    private Tab tabLogin;

    // Login Screen
    @FXML
    private TextField tfLoginEmail;
    @FXML
    private TextField tfLoginSenha;
    @FXML
    private Button btLogin;
    @FXML
    void realizarLogInOff(ActionEvent event) {
       DAO_Usuario du = DAO_Usuario.getInstance();
       Usuario usuario = du.read(tfLoginEmail.getText());
       if (usuario != null){
           String storedPassword = usuario.getSenha();
           byte[] salt = usuario.getSalt();
           String testPassword = PasswordUtils.getSecurePassword(tfLoginSenha.getText(),salt);
           if (storedPassword.compareTo(testPassword) == 0){
               // ok, realizar login
               this.usuario=usuario;
               toggleLogInOut();
           }else {
               // fail senha
               Toast.makeText((Stage) ((Button)(event.getSource())).getScene().getWindow(),"Não foi possível realizar o login");
           }
       } else{
           // fail user
           Toast.makeText((Stage) ((Button)(event.getSource())).getScene().getWindow(),"Não foi possível realizar o login");
       }

    }

    private void toggleLogInOut() {
        usuarioLogado = !usuarioLogado;
        if (usuarioLogado) {
            Toast.makeText((Stage) pane.getScene().getWindow(),"Login realizado com sucesso");
            btLogin.setText("Logout");
            tfLoginEmail.setDisable(true);
            tfLoginSenha.setDisable(true);
            tabAtualizar.setDisable(false);
            tabExcluir.setDisable(false);
            tabVisualizar.setDisable(false);
            tabInserir.setDisable(false);
        } else {
            Toast.makeText((Stage) pane.getScene().getWindow(),"Logout realizado com sucesso");
            btLogin.setText("Login");
            tfLoginEmail.setDisable(false);
            tfLoginSenha.setDisable(false);
            tabAtualizar.setDisable(true);
            tabExcluir.setDisable(true);
            tabVisualizar.setDisable(true);
            tabInserir.setDisable(true);
        }
    }

    @FXML
    void loginEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER)
            btLogin.fire();
    }

}
