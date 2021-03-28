package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Controller {


    @FXML
    private MenuItem SubMenuadminLoginHome;

    @FXML
    private MenuItem SubMenuExitAdminHome;

    @FXML
    private MenuItem SubMenuFuncLoginHome;

    @FXML
    private MenuItem SubMenuExitFuncHome;

    @FXML
    private Button btnAdminHome;

    @FXML
    private Button btnFuncHome;

    public void BtnAdminHome(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        URL url = new File("src/main/java/View/loginAdmin.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
//         = FXMLLoader.load(getClass().getResource("../View/loginAdmin.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Login - Admin");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    public void SubMenuLoginAdmin(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        URL url = new File("src/main/java/View/loginAdmin.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setTitle("Login - Admin");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();
    }

    public void SairApp(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void SairAppFunc(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void BtnFuncHome(ActionEvent actionEvent) throws IOException {
        Parent root = null;
        URL url = new File("src/main/java/View/loginFunc.fxml").toURI().toURL();
        root = FXMLLoader.load(url);
        Stage stage = new Stage();
        stage.setTitle("Login - Funcionários");
        stage.setScene(new Scene(root, 600, 600));
        stage.show();

    }
}
