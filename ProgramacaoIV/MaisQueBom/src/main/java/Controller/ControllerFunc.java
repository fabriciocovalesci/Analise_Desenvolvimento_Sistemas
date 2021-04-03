package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ControllerFunc implements Subject {

    protected List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void addSubscriber(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeSubscriber(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifySubscribers(int mesa) {
        observers.forEach(observer -> observer.notification(mesa));
    }

    // VARIAVEIS GLOBAIS
    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String salt =  "TG4QV9mJ3uE3wJMdo9lZzvxaGfIuTcKxCyC2s0XU";
    Integer idBuscaCliente;

    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    Integer produtoID;

    @FXML
    private TextField inputUserFuncionario;

    @FXML
    private PasswordField inputSenhaFuncionario;

    @FXML
    private VBox vboxFuncionario;

    @FXML
    private Button novaComanda;

    @FXML
    private ComboBox<?> comboBoxProdutos;

    @FXML
    private TextField qtdadeProduto;

    @FXML
    private Button btnExcluiProduto;

    @FXML
    private Button btnAddLineGrid;

    @FXML
    private Button salvarComanda;

    @FXML
    private TitledPane listProduto;

    @FXML
    private CheckBox checkProd1;
    @FXML
    private CheckBox checkProd2;
    @FXML
    private CheckBox checkProd3;
    @FXML
    private CheckBox checkProd4;
    @FXML
    private CheckBox checkProd5;
    @FXML
    private CheckBox checkProd6;
    @FXML
    private CheckBox checkProd7;
    @FXML
    private CheckBox checkProd8;

    @FXML
    private TextField qtadePRD1;
    @FXML
    private TextField qtadePRD2;
    @FXML
    private TextField qtadePRD3;
    @FXML
    private TextField qtadePRD4;
    @FXML
    private TextField qtadePRD5;
    @FXML
    private TextField qtadePRD6;
    @FXML
    private TextField qtadePRD7;
    @FXML
    private TextField qtadePRD8;

    @FXML
    private Label preco1;
    @FXML
    private Label preco2;
    @FXML
    private Label preco3;
    @FXML
    private Label preco4;
    @FXML
    private Label preco5;
    @FXML
    private Label preco6;
    @FXML
    private Label preco7;
    @FXML
    private Label preco8;

    @FXML
    private TextField NomeComanda;

    @FXML
    private CheckBox mesa1;
    @FXML
    private CheckBox mesa2;
    @FXML
    private CheckBox mesa3;
    @FXML
    private CheckBox mesa4;
    @FXML
    private CheckBox mesa5;
    @FXML
    private CheckBox mesa6;

    @FXML
    private Circle funcmesa1;
    @FXML
    private Circle funcmesa2;
    @FXML
    private Circle funcmesa3;
    @FXML
    private Circle funcmesa4;
    @FXML
    private Circle funcmesa5;
    @FXML
    private Circle funcmesa6;

    @FXML
    public Button inserir1;
    @FXML
    private Button inserir2;
    @FXML
    private Button inserir3;
    @FXML
    private Button inserir4;
    @FXML
    private Button inserir5;
    @FXML
    private Button inserir6;

    @FXML
    private Label quant1;
    @FXML
    private Label quant2;
    @FXML
    private Label quant3;
    @FXML
    private Label quant4;
    @FXML
    private Label quant5;
    @FXML
    private Label quant6;


    @FXML
    private Label valor1;
    @FXML
    private Label valor2;
    @FXML
    private Label valor3;
    @FXML
    private Label valor4;
    @FXML
    private Label valor5;
    @FXML
    private Label valor6;

    @FXML
    private GridPane gridFuncionario;

    @FXML
    private MenuItem ComandasAtivas;

    @FXML
    private MenuItem logoutFuncionario;

    /**
     *
     *
     *
     *  VARIAVEIS GLOBAIS
     *
     *
     * **/

    Stage stageFuncionario = new Stage();
    Stage stageFuncComanda = new Stage();
    int myid;

    int btnMesa1;
    int btnMesa2;
    int btnMesa3;
    int btnMesa4;
    int btnMesa5;
    int btnMesa6;

    public void LoginFunc(ActionEvent actionEvent)   {
        String nomeFunc = inputUserFuncionario.getText().toString();
        String senhaFunc = inputSenhaFuncionario.getText().toString();

        try {
            // busca user no banco de dados
          Usuario funcionario = usuarioDAO.buscarUsuario(nomeFunc);

            boolean readPass = Encrypt.verifyUserPassword(senhaFunc, funcionario.getSenha(), salt);
            if(readPass && nomeFunc.equals(funcionario.getNome())) {
                myid = funcionario.getId();
                System.out.println("Acesso autorizado");
                System.out.println("Nome " + funcionario.getNome() + " Senha " + funcionario.getSenha() + " Admin " + funcionario.getAdmin() + " ID " +  funcionario.getId());

                URL url = new File("src/main/java/View/funcionario.fxml").toURI().toURL();
                Parent root = FXMLLoader.load(url);
                stageFuncionario.setTitle("Funcionário");
                stageFuncionario.setScene(new Scene(root, 600, 600));
                stageFuncionario.show();

            }
        }catch (Exception e){
            System.out.println("Error ao fazer login-funcionario " + e);
        }

    }

    public double[] ValorTotalComanda(CheckBox check, Label preco, TextField qtde) {
        if (check.isSelected()) {
            String stringPc = preco.getText().toString();
            double prc = Double.parseDouble(stringPc);
            double unidade = Double.parseDouble(qtde.getText().toString());
            double valor = unidade * prc;
            return new double[] {valor, unidade};
        }
        return null;
    }

    public  int VerificaNumeroMesa(){
        int numeroMesa = 0;
        if (mesa1.isSelected()){
           return numeroMesa = 1;
        }else if(mesa2.isSelected()){
            return numeroMesa = 2;
        }else if (mesa3.isSelected()){
            return numeroMesa = 3;
        }else if(mesa4.isSelected()){
            return numeroMesa = 4;
        }else if (mesa5.isSelected()){
             return numeroMesa = 5;
         }else if (mesa6.isSelected()){
            return numeroMesa = 6;
        }
        return numeroMesa;
    }

    public void cancelarComanda(MouseEvent mouseEvent) {
        stageFuncComanda.close();
        stageFuncComanda.hide();
    }

    public void OpenCriarComanda(){
        URL urlComanda = null;
        try {
            urlComanda = new File("src/main/java/View/NovaComanda.fxml").toURI().toURL();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        }
        Parent rootComanda = null;
        try {
            rootComanda = FXMLLoader.load(urlComanda);
            stageFuncionario.setTitle("Nova Comanda");
            stageFuncionario.setScene(new Scene( rootComanda,400, 700));
            stageFuncionario.show();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public int GetEventBotton(Button insere, int numBtn) {
        if(numBtn == 1){
            int n = 1;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    OpenCriarComanda();
                }
            }
        );
            return n;
        }
        if(numBtn == 2){
            int n = 2;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            OpenCriarComanda();
                        }
                    }
            );
            return n;
        }
        if(numBtn == 3){
            int n = 3;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            OpenCriarComanda();
                        }
                    }
            );
            return n;
        }
        if(numBtn == 4){
            int n = 4;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            OpenCriarComanda();
                        }
                    }
            );
            return n;
        }
        if(numBtn == 5){
            int n = 5;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            OpenCriarComanda();
                        }
                    }
            );
            return n;
        }
        if(numBtn == 6){
            int n = 6;
            insere.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            OpenCriarComanda();
                        }
                    }
            );
            return n;
        }
       return 0;
    }

    public void InserirComanda(ActionEvent actionEvent) throws IOException {
        btnMesa1 = GetEventBotton(inserir1, 1);
        btnMesa2 = GetEventBotton(inserir2, 2);
        btnMesa3 = GetEventBotton(inserir3, 3);
        btnMesa4 = GetEventBotton(inserir4, 4);
        btnMesa5 = GetEventBotton(inserir5, 5);
        btnMesa6 = GetEventBotton(inserir6, 6);
        System.out.println(btnMesa1);
    }

    public void salvarComanda(ActionEvent actionEvent)  {

        double valorTotal = 0;
        double quantidade = 0;
        int quantProduto = 0;

        double produto1[] = ValorTotalComanda(checkProd1, preco1, qtadePRD1);
        double produto2[] = ValorTotalComanda(checkProd2, preco2, qtadePRD2);
        double produto3[] = ValorTotalComanda(checkProd3, preco3, qtadePRD3);
        double produto4[] = ValorTotalComanda(checkProd4, preco4, qtadePRD4);
        double produto5[] = ValorTotalComanda(checkProd5, preco5, qtadePRD5);
        double produto6[] = ValorTotalComanda(checkProd6, preco6, qtadePRD6);
        double produto7[] = ValorTotalComanda(checkProd7, preco7, qtadePRD7);
        double produto8[] = ValorTotalComanda(checkProd8, preco8, qtadePRD8);

        if(produto1 != null){
            System.out.println("Valor total " + produto1[0] + " quantidade " + produto1[1]);
            valorTotal += produto1[0];
            quantidade += produto1[1];
        }if(produto2 != null){
            System.out.println("Valor total " + produto2[0] + " quantidade " + produto2[1]);
            valorTotal += produto2[0];
            quantidade += produto2[1];
        }if(produto3 != null){
            System.out.println("Valor total " + produto3[0] + " quantidade " + produto3[1]);
            valorTotal += produto3[0];
            quantidade += produto3[1];
        }if(produto4 != null){
            System.out.println("Valor total " + produto4[0] + " quantidade " + produto4[1]);
            valorTotal += produto4[0];
            quantidade += produto4[1];
        }if(produto5 != null){
            System.out.println("Valor total " + produto5[0] + " quantidade " + produto5[1]);
            valorTotal += produto5[0];
            quantidade += produto5[1];
        }if(produto6 != null){
            System.out.println("Valor total " + produto6[0] + " quantidade " + produto6[1]);
            valorTotal += produto6[0];
            quantidade += produto6[1];
        }if(produto7 != null){
            System.out.println("Valor total " + produto7[0] + " quantidade " + produto7[1]);
            valorTotal += produto7[0];
            quantidade += produto7[1];
        }if(produto8 != null){
            System.out.println("Valor total " + produto8[0] + " quantidade " + produto8[1]);
            valorTotal += produto8[0];
            quantidade += produto8[1];
        }
        int nMesa = VerificaNumeroMesa();
        quantProduto = (int)quantidade;

        java.util.Date dateJava = new java.util.Date();
        Date dataSql = new Date(dateJava.getTime());

        System.out.println(quantProduto);
        System.out.println(valorTotal);

        try {
            Comanda comanda = new Comanda();
            ComandaDAO addToComanda = new ComandaDAO();
            comanda.setNome_comanda(NomeComanda.getText().toString());
            comanda.setMesa(nMesa);
            comanda.setQuantidade(quantProduto);
            comanda.setValor_total(valorTotal);
            comanda.setDataCriacao(dataSql);
            comanda.setDataFinaliza(null);
            comanda.setStatus_pagamento("Aguardando pagamento");
            comanda.setProduto_id(1);
            comanda.setUsuario_id(1);
            comanda.setPago(false);
            addToComanda.cadastraComanda(comanda);

            /*  Preenche na home principal a comanda inserida  */

//            if(btnMesa1 == 1){
//                quant1.setText(String.valueOf(quantProduto));
//                valor1.setText("R$ " + String.valueOf(valorTotal));
//
//            }
//            if(btnMesa1 == 2){
//                quant2.setText(String.valueOf(quantProduto));
//                valor2.setText(String.valueOf(valorTotal));
//            }
        }catch (Exception e){
            System.out.println("ERROR ao salvar comanda no banco de dados " + e);
        }
        stageFuncComanda.close();
    }


    public void LogoutFuncionario(ActionEvent actionEvent) {
        logoutFuncionario.setOnAction( e -> {
            Stage stage = (Stage) ((Button) e.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void ComandasAtivas(ActionEvent actionEvent) {
        vboxFuncionario.getChildren().clear();
        Button voltarHome = new Button("Voltar");
        vboxFuncionario.getChildren().add(voltarHome);

        try {
            ComandaDAO comandaDAO = new ComandaDAO();
            List<Comanda> lisComandasAtivas = comandaDAO.buscarComandasAtivas();

            Label labelNomeComanda = new Label();
            Label labelQuantidade = new Label();
            Label labelValorTotal = new Label();
            Label labelNumeroMesa = new Label();


            for (int index = 0; index < lisComandasAtivas.size(); index++) {
                VBox BoxComanda = new VBox();

                labelNomeComanda.setText(lisComandasAtivas.get(index).getNome_comanda());
                labelQuantidade.setText(String.valueOf(lisComandasAtivas.get(index).getQuantidade()));
                labelValorTotal.setText(String.valueOf(lisComandasAtivas.get(index).getValor_total()));
                labelNumeroMesa.setText(String.valueOf(lisComandasAtivas.get(index).getMesa()));

                BoxComanda.getChildren().add(labelNomeComanda);
                BoxComanda.getChildren().add(labelQuantidade);
                BoxComanda.getChildren().add(labelValorTotal);
                BoxComanda.getChildren().add(labelNumeroMesa);

                vboxFuncionario.getChildren().add(BoxComanda);
            }
        }catch (Exception e){
            System.out.println("Error ao buscar as comandas " + e);
        }
        voltarHome.setOnAction( e -> {
            vboxFuncionario.getChildren().add(gridFuncionario);
            vboxFuncionario.getChildren().remove(voltarHome);
        });

    }
}
