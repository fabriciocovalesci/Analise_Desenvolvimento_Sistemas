package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {


    @FXML
    private Tab tab1;

    @FXML
    private Button idBtn1;

    @FXML
    private Button idBtn4;

    @FXML
    private Button idbtn6;

    @FXML
    private Tab tab3;

    @FXML
    private Button idBotao;

    @FXML
    private Label idLabel;

    @FXML
    private Tab tab4;

    @FXML
    private Label idLabel3;

    @FXML
    private Button idBotao3;

    @FXML
    private Label idLabel4Hide;

    @FXML
    private Button idAparecer;

    @FXML
    private Tab tab6;

    @FXML
    private Button idBotao6;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel6;

    @FXML
    private Button idBotaoLimpar;

    @FXML
    private Button idfechar;

    //  ****************  FUNÇÕES  ****************************************
    @FXML
    public void aperte(ActionEvent actionEvent) {
        idLabel.setText("Não estou mais kkkkk !!!");
    }

    @FXML
    void toogle(ActionEvent event) {
        idLabel3.setVisible(false);
        idLabel4Hide.setVisible(false);
    }

    @FXML
    public void aparecer(ActionEvent actionEvent) {
        idLabel3.setVisible(true);
        idLabel4Hide.setVisible(true);
    }

    @FXML
    public void getName(ActionEvent actionEvent) {
        idLabel6.setText(idInput.getText());
    }

    @FXML
    public void clearName(ActionEvent actionEvent) {
        idLabel6.setText("");
        idInput.setText("");

    }

    public void Fechar(ActionEvent actionEvent) {
        Stage stage = (Stage) idfechar.getScene().getWindow();
        stage.close();
    }
}
