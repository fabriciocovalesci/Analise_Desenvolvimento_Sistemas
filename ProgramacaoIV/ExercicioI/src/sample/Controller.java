package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {


    @FXML
    private Button idBotao;

    @FXML
    private Label idLabel;

    @FXML
    private Label idLabel3;

    @FXML
    private Button idBotao3;

    @FXML
    private Label idLabel4Hide;

    @FXML
    private Button idBotao6;

    @FXML
    private TextField idInput;

    @FXML
    private Label idLabel6;

    @FXML
    private Button idAparecer;

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
}
