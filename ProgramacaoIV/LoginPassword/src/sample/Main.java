package sample;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/sample.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 500));
        primaryStage.show();

    }


    public static void main(String[] args) {
//        ClienteDAO clienteDao = new ClienteDAO();
//        UserDAO userDAO = new UserDAO();
//        Cliente cliente = new Cliente();
//        User uu = new User();
//
//        cliente.setNome("PAulo");
//        cliente.setTelefone("9999999");
//        cliente.setEmail("lucas@gmail");
//        cliente.setEndereco("Rua alamendas");
//        cliente.setDataNascimento("12/12/1999");
//        cliente.setCpf("9900990099");
//
//
//        User u = userDAO.buscar("fabricio");
//        List<Cliente> cc = clienteDao.buscaAllClientes();
//        Cliente c1 = clienteDao.buscarCliente("Lucas");
//
//        System.out.println(c1);
//
//
//        System.exit(0);
        launch(args);

    }
}
