package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

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
    private Object Node;

    public void Entrar(ActionEvent actionEvent) throws IOException {
        String checkUser = idUser.getText().toString();
        String checkPw = idPass.getText().toString();
        String user = "fabricio";
        String passwd = "12345";
        if(checkUser.equals(user) && checkPw.equals(passwd)){
            lblMessage.setText("Acesso autorizado!");
            lblMessage.setTextFill(Color.GREEN);
            Parent home_page = FXMLLoader.load(getClass().getResource("home.fxml"));
            Scene home_scene = new Scene(home_page);
            Stage stage_app = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage_app.setScene(home_scene);
            stage_app.setTitle("Home");
            stage_app.show();
        }
        else{
            lblMessage.setText("Usuario ou senha incorreto!");
            lblMessage.setTextFill(Color.RED);
        }
        idUser.setText("");
        idPass.setText("");
    }


    @FXML
    void Cadastrar(ActionEvent event) throws IOException {
        Parent cadastro_page = FXMLLoader.load(getClass().getResource("cadastro.fxml"));
        Scene cadastro_scene = new Scene(cadastro_page);
        Stage stage_app_cadastro = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage_app_cadastro.setScene(cadastro_scene);
        stage_app_cadastro.setTitle("Cadastro");
        stage_app_cadastro.show();

    }
    
    //******************* Tela de Cadastro **********************************************

    @FXML
    private TextField idNomeCadastro;

    @FXML
    private TextField idEmailCadastro;

    @FXML
    private TextField idEndCadastro;

    @FXML
    private TextField idCepCadastro;

    @FXML
    private TextField idTelefoneCadastro;

    @FXML
    private Button idCadCadastro;

    @FXML
    private Button idCancelarCadastro;

    @FXML
    private TextField idSenhaCadastro;

    @FXML
    private Label idLabelMSG;

    @FXML
    public void CadastroUser(ActionEvent actionEvent) {
        String nome = idNomeCadastro.getText().toString();
        String senha = idSenhaCadastro.getText().toString();
        String email = idEmailCadastro.getText().toString();
        String telefone = idTelefoneCadastro.getText().toString();
        String cep = idCepCadastro.getText().toString();
        String endereco = idEndCadastro.getText().toString();

        // verifica se todos os campos foram preenchidos
        if(nome.isEmpty()|| senha.isEmpty() || email.isEmpty() || telefone.isEmpty() || cep.isEmpty() || endereco.isEmpty()){
            idLabelMSG.setText("Todos os campos devem ser preenchidos !!");
            idLabelMSG.setTextFill(Color.RED);
        }else {
            idLabelMSG.setText("Usuario cadastrado !!");
            idLabelMSG.setTextFill(Color.GREEN);
        }

    }

    public void CancelarCadastro(ActionEvent actionEvent) {
        Stage stage = (Stage) idCancelarCadastro.getScene().getWindow();
        stage.close();
    }


    //******************* FIM Tela de Cadastro **********************************************
}
