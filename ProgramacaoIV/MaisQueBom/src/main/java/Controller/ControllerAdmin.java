package Controller;

import Model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

public class ControllerAdmin {

    // VARIAVEIS GLOBAIS
    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDAO = new UsuarioDAO();
    String salt =  "TG4QV9mJ3uE3wJMdo9lZzvxaGfIuTcKxCyC2s0XU";
    Integer idBuscaCliente;

    Produto produto = new Produto();
    ProdutoDAO produtoDAO = new ProdutoDAO();
    Integer produtoID;

    @FXML
    private AnchorPane AnchorPaneLoginAdmin;

    @FXML
    private Button btnLoginEntrarAdmin;

    @FXML
    private MenuItem SubMenuAdminRedefinirSenha;

    @FXML
    private MenuItem SubMenuadminLogoutHome;

    @FXML
    private MenuItem SubMenuCadastraFunc;

    @FXML
    private MenuItem SubMenuDeletarFunc;

    @FXML
    private MenuItem SubMenuEditarFunc;

    @FXML
    private MenuItem EncerrarComanda;

    @FXML
    private MenuItem ComandasAtivas;

    @FXML
    private MenuItem cadastrarProduto;

    @FXML
    private MenuItem editarProduto;

    @FXML
    private MenuItem listarProduto;

    @FXML
    private VBox vboxAdmin;

    @FXML
    private TextField inputNameAdmin;

    @FXML
    private PasswordField inputSenhaAdmin;

    @FXML
    private Button btnLoginAdmin;

    @FXML
    private MenuItem tempoConsumo;

    @FXML
    private MenuItem ProdMenosVendido;

    @FXML
    private MenuItem ProdMaisVendido;

    @FXML
    private MenuItem faturamento;

    @FXML
    private MenuItem vendaPorGarcom;

    @FXML
    private MenuItem garcomMaiorDesem;

    @FXML
    private MenuItem garcomMenorDesem;

    @FXML
    private MenuItem relatorioDiario;

    @FXML
    private MenuItem relatorioSemanal;

    @FXML
    private MenuItem relatorioMensal;

    @FXML
    private GridPane GridPaneAdmin;

    @FXML
    private Button EncerrarComandaUm;
    @FXML
    private Button EncerrarComandaDois;
    @FXML
    private Button EncerrarComandaTres;
    @FXML
    private Button EncerrarComandaCinco;
    @FXML
    private Button EncerrarComandaQuadro;
    @FXML
    private Button EncerrarComandaSeis;

    @FXML
    private Button VerComandaUm;

    @FXML
    private Button VerComandaDois;

    @FXML
    private Button VerComandaTres;

    @FXML
    private Button VerComandaQuatro;

    @FXML
    private Button VerComandaCinco;

    @FXML
    private Button VerComandaSeis;

    @FXML
    private Circle adminMesa1;
    @FXML
    private Circle adminMesa2;
    @FXML
    private Circle adminMesa3;
    @FXML
    private Circle adminMesa4;
    @FXML
    private Circle adminMesa5;
    @FXML
    private Circle adminMesa6;

    @FXML
    private Label admMesaNum1;
    @FXML
    private Label admMesaNum2;
    @FXML
    private Label admMesaNum3;
    @FXML
    private Label admMesaNum4;
    @FXML
    private Label admMesaNum5;
    @FXML
    private Label admMesaNum6;

    @FXML
    private Label mesaQtade1;
    @FXML
    private Label mesaTotal1;
    @FXML
    private Label mesaGarcom1;

    @FXML
    private Label mesaQtade2;
    @FXML
    private Label mesaTotal2;
    @FXML
    private Label mesaGarcom2;

    @FXML
    private Label mesaQtade3;
    @FXML
    private Label mesaTotal3;
    @FXML
    private Label mesaGarcom3;

    @FXML
    private Label mesaQtade4;
    @FXML
    private Label mesaTotal4;
    @FXML
    private Label mesaGarcom4;

    @FXML
    private Label mesaQtade5;
    @FXML
    private Label mesaTotal5;
    @FXML
    private Label mesaGarcom5;

    @FXML
    private Label mesaQtade6;
    @FXML
    private Label mesaTotal6;
    @FXML
    private Label mesaGarcom6;

    @FXML
    private VBox mesa1;
    @FXML
    private VBox mesa2;
    @FXML
    private VBox mesa3;
    @FXML
    private VBox mesa4;
    @FXML
    private VBox mesa5;
    @FXML
    private VBox mesa6;


    @FXML
    private Button atualizarComandas;

    Stage stageAdmin = new Stage();

    public void LoginAdmin(ActionEvent actionEvent) throws IOException {
        String nomeAdmin = inputNameAdmin.getText().toString();
        String senhaAdmin = inputSenhaAdmin.getText().toString();
        URL url = new File("src/main/java/View/admin.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stageAdmin.setTitle("ADMIN");
        stageAdmin.setScene(new Scene(root, 600, 600));
        stageAdmin.show();

        try {
            // busca user no banco de dados
            Usuario compareUser = usuarioDAO.buscarUsuario(nomeAdmin);

            boolean readPass = Encrypt.verifyUserPassword(senhaAdmin, compareUser.getSenha(), salt);
            if(readPass && nomeAdmin.equals(compareUser.getNome()) && compareUser.getAdmin()) {
            System.out.println("Acesso autorizado");

                System.out.println("Nome " + compareUser.getNome() + " Senha " + compareUser.getSenha() + " Admin " + compareUser.getAdmin());

//                try {
//                    Parent root = null;
//
////                    root = FXMLLoader.load(getClass().getResource("../View/admin.fxml"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//                URL url = new File("src/main/java/View/admin.fxml").toURI().toURL();
//                Parent root = FXMLLoader.load(url);
//                stageAdmin.setTitle("ADMIN");
//                stageAdmin.setScene(new Scene(root, 600, 600));
//                stageAdmin.show();
//                AnchorPaneLoginAdmin.setVisible(false);
            }
        }catch (Exception e){
            System.out.println("Error ao fazer login-admin " + e);
        }
    }

    public void CadastrarFuncionario(ActionEvent actionEvent) {
        vboxAdmin.getChildren().clear();

        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("CADASTRAR NOVO FUNCIONÁRIO");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField textName = new TextField();
        TextField textSenha = new PasswordField();
        CheckBox admin = new CheckBox();

        Button btnCadastraFunc = new Button();
        btnCadastraFunc.setText("Cadastrar Funcionário");

        Button btnCancelarCadastro = new Button();
        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarCadastro.setStyle("-fx-background-color:red;");

        VBox vboxChildren = new VBox();

        vboxChildren.getChildren().add(labelTitle);
        vboxChildren.getChildren().add(new Label("Nome funcionário"));
        vboxChildren.getChildren().add(textName);
        vboxChildren.getChildren().add(new Label("Senha"));
        vboxChildren.getChildren().add(textSenha);
        vboxChildren.getChildren().add(new Label("Admin"));
        vboxChildren.getChildren().add(admin);
        vboxChildren.getChildren().add(btnCadastraFunc);
        vboxChildren.getChildren().add(btnCancelarCadastro);
        vboxAdmin.getChildren().add(vboxChildren);

        try {
            btnCadastraFunc.setOnAction(value -> {

                String nomeUser = textName.getText().toString();
                String senha = textSenha.getText().toString();
                Boolean adminCheck = admin.isSelected();

                // gera senha em Base64
                String myPassSegure = Encrypt.generateSecurePassword(senha, salt);

                usuario.setNome(nomeUser);
                usuario.setSenha(myPassSegure);
                usuario.setAdmin(adminCheck);
                usuarioDAO.cadastraUsuario(usuario);

                textName.clear();
                textSenha.clear();
                admin.setSelected(false);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cadastrado");
                String s = "Funcionário " + usuario.getNome() + " cadastrado com sucesso !";
                alert.setContentText(s);
                alert.showAndWait();
            });
        }catch (Exception e){
            System.out.println("ERROR " + e);
        }

        btnCancelarCadastro.setOnAction( cancelar -> {
            vboxChildren.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
        });


    }

    public void DeletarFunc(ActionEvent actionEvent) {
    }

    public void EditarFuncionario(ActionEvent actionEvent) {
        vboxAdmin.getChildren().clear();
        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("ATUALIZAR CADASTRO - FUNCIONÁRIO");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField textName = new TextField();
        TextField textBusca = new TextField();
        TextField textSenha = new PasswordField();
        CheckBox admin = new CheckBox();

        Button btnEditarFunc = new Button();
        btnEditarFunc.setText("Atualizar Cadastro");

        Button btnBuscaFunc = new Button();
        btnBuscaFunc.setText("Buscar Funcionãrio");

        Button btnCancelarCadastro = new Button();
        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarCadastro.setStyle("-fx-background-color:red;");

        vboxAdmin.getChildren().add(labelTitle);
        vboxAdmin.getChildren().add(new Label("Buscar funcionário para atualizar"));
        vboxAdmin.getChildren().add(textBusca);
        vboxAdmin.getChildren().add(btnBuscaFunc);
        vboxAdmin.getChildren().add(new Label("Nome funcionário"));
        vboxAdmin.getChildren().add(textName);
        vboxAdmin.getChildren().add(new Label("Senha"));
        vboxAdmin.getChildren().add(textSenha);
        vboxAdmin.getChildren().add(new Label("Admin"));
        vboxAdmin.getChildren().add(admin);
        vboxAdmin.getChildren().add(btnEditarFunc);
        vboxAdmin.getChildren().add(btnCancelarCadastro);

        try {
            btnBuscaFunc.setOnAction( buscaF -> {
                Usuario buscaFunc = usuarioDAO.buscarUsuario(textBusca.getText().toString());
                textName.setText(buscaFunc.getNome());
                textSenha.setText(buscaFunc.getSenha());
                admin.setSelected(buscaFunc.getAdmin());
                idBuscaCliente = buscaFunc.getId();

            });

        }catch (Exception e){
            System.out.println("Erro ao buscar funcionario " + e);
        }
        try {
            btnEditarFunc.setOnAction( update -> {

                if(admin.isSelected()){
                    usuario.setAdmin(true);
                }else {
                    usuario.setAdmin(false);
                }

            usuario.setNome(textName.getText().toString());
            // gera senha em Base64
            String myPassSegureUpdate = Encrypt.generateSecurePassword(textSenha.getText().toString(), salt);
            usuario.setSenha(myPassSegureUpdate);
            usuario.setId(idBuscaCliente);
//            usuario.setAdmin(admin.isSelected());
            usuarioDAO.atualizarUsuario(usuario);

            textName.clear();
            textSenha.clear();
            admin.setSelected(false);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atualizado");
            String s = "Funcionário " + usuario.getNome() + " atualizado com sucesso !";
            alert.setContentText(s);
            alert.showAndWait();
            vboxAdmin.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
            });
        }catch (Exception e){
            System.out.println("Erro ao atualizar funcionario " + e);
        }
        btnCancelarCadastro.setOnAction( cancelar -> {
            vboxAdmin.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
        });
    }

    public void CadastrarProduto(ActionEvent actionEvent) {
        vboxAdmin.getChildren().clear();
        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("CADASTRAR PRODUTO");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField nomeProduto = new TextField();
        TextField custoProduto = new TextField();
        TextField precoProduto = new TextField();

//        custoProduto.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
//                    custoProduto.setText(oldValue);
//                }
//            }
//        });
//
//        precoProduto.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
//                    precoProduto.setText(oldValue);
//                }
//            }
//        });

        Button btnCadastraProduto = new Button();
        btnCadastraProduto.setText("Cadastrar Produto");

        Button btnCancelarCadastro = new Button();
        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarCadastro.setStyle("-fx-background-color:red;");

        vboxAdmin.getChildren().add(labelTitle);
        vboxAdmin.getChildren().add(new Label("Nome produto"));
        vboxAdmin.getChildren().add(nomeProduto);
        vboxAdmin.getChildren().add(new Label("Custo do produto"));
        vboxAdmin.getChildren().add(custoProduto);
        vboxAdmin.getChildren().add(new Label("Preço final"));
        vboxAdmin.getChildren().add(precoProduto);
        vboxAdmin.getChildren().add(btnCadastraProduto);
        vboxAdmin.getChildren().add(btnCancelarCadastro);

        try {
            btnCadastraProduto.setOnAction( cadastraP -> {

            produto.setNome(nomeProduto.getText().toString());
                Double custoProd = Double.parseDouble(custoProduto.getText());
                Double precoProd = Double.parseDouble(precoProduto.getText());

            produto.setCusto(custoProd);
            produto.setPreco(precoProd);
            produtoDAO.cadastraProduto(produto);

            nomeProduto.clear();
            custoProduto.clear();
            precoProduto.clear();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cadastrado Produto");
            String s = "Produto " + produto.getNome() + " cadastrado com sucesso !";
            alert.setContentText(s);
            alert.showAndWait();
            vboxAdmin.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
            });

        }catch (Exception e){
            System.out.println("Erro ao cadastrar produto " + e);
        }
        btnCancelarCadastro.setOnAction( cancelar -> {
            vboxAdmin.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
        });
    }

    public void EditarProduto(ActionEvent actionEvent) {
        vboxAdmin.getChildren().clear();
        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("ATUALIZAR PRODUTO");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField textBuscaProduto = new TextField();
        TextField nomeProduto = new TextField();
        TextField custoProduto = new TextField();
        TextField precoProduto = new TextField();

        Button btnBuscarProduto = new Button();
        btnBuscarProduto.setText("Buscar Produto");

        Button btnEditarProduto = new Button();
        btnEditarProduto.setText("Atualizar Produto");

        Button btnCancelarCadastro = new Button();
        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarCadastro.setStyle("-fx-background-color:red;");

        vboxAdmin.getChildren().add(labelTitle);
        vboxAdmin.getChildren().add(new Label("Buscar Produto para Atualizar"));
        vboxAdmin.getChildren().add(textBuscaProduto);
        vboxAdmin.getChildren().add(btnBuscarProduto);
        vboxAdmin.getChildren().add(new Label("Nome produto"));
        vboxAdmin.getChildren().add(nomeProduto);
        vboxAdmin.getChildren().add(new Label("Custo do produto"));
        vboxAdmin.getChildren().add(custoProduto);
        vboxAdmin.getChildren().add(new Label("Preço final"));
        vboxAdmin.getChildren().add(precoProduto);
        vboxAdmin.getChildren().add(btnEditarProduto);
        vboxAdmin.getChildren().add(btnCancelarCadastro);

        try {
            btnBuscarProduto.setOnAction( buscarProd -> {
                Produto produto = produtoDAO.buscarProduto(textBuscaProduto.getText().toString());
                nomeProduto.setText(produto.getNome());
                custoProduto.setText(produto.getCusto().toString());
                precoProduto.setText(produto.getPreco().toString());
                produtoID = produto.getId();
            });
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Buscar Produto");
            String s = "Produto " + textBuscaProduto.getText().toString() + " não encontrado no banco de dados !";
            alert.setContentText(s);
            alert.showAndWait();
            System.out.println("Error ao buscar produto " + e);
        }

        try {
            btnEditarProduto.setOnAction( updateProd -> {
                produto.setNome(nomeProduto.getText().toString());
                produto.setCusto(Double.parseDouble(custoProduto.getText()));
                produto.setPreco(Double.parseDouble(precoProduto.getText()));
                produto.setId(produtoID);
                produtoDAO.atualizarProduto(produto);

                nomeProduto.clear();
                custoProduto.clear();
                precoProduto.clear();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Atualizar Produto");
                String s = "Produto " + produto.getNome() + " atualizado com sucesso !";
                alert.setContentText(s);
                alert.showAndWait();
                vboxAdmin.getChildren().clear();
                vboxAdmin.getChildren().add(GridPaneAdmin);
            });

        }catch (Exception e){
            System.out.println("Error ao atualizar produto " + e);
        }
        btnCancelarCadastro.setOnAction( cancelar -> {
            vboxAdmin.getChildren().clear();
            vboxAdmin.getChildren().add(GridPaneAdmin);
        });
    }

    public void LogoutAdmin(ActionEvent actionEvent) {
         stageAdmin.close();
         stageAdmin.hide();
         System.exit(0);
    }

    public void ListarProdutos(ActionEvent actionEvent) {
        vboxAdmin.getChildren().clear();
        try {
            List<Produto> produtos = produtoDAO.buscaAllProduto();

                TableView table = new TableView();
                final Label label = new Label("Lista de Produtos");
                label.setFont(new Font("Arial", 20));

                TableColumn<Produto, String> Nomeproduto = new TableColumn("Produto");
                Nomeproduto.setCellValueFactory(new PropertyValueFactory<>("nome"));
                Nomeproduto.setMaxWidth(350.0);
                Nomeproduto.setMinWidth(200.0);
                TableColumn<Produto, Double>  custoProduto = new TableColumn("Custo produto");
                custoProduto.setCellValueFactory(new PropertyValueFactory<>("custo"));
                custoProduto.setMaxWidth(250.0);
                custoProduto.setMinWidth(150.0);
                TableColumn<Produto, Double>  precoProduto = new TableColumn("Preço final");
                precoProduto.setCellValueFactory(new PropertyValueFactory<>("preco"));
                precoProduto.setMaxWidth(250.0);
                precoProduto.setMinWidth(150.0);

                table.getColumns().addAll(Nomeproduto, custoProduto, precoProduto);

                for (int i = 0; i < produtos.size() ; i++) {
                    table.getItems().add(produtos.get(i));
                }

                Button btnVoltar = new Button();
                btnVoltar.setText("Voltar Home");
                vboxAdmin.getChildren().add(btnVoltar);

                try {
                    btnVoltar.setOnAction( voltarHome -> {
                        vboxAdmin.getChildren().clear();
                        vboxAdmin.getChildren().add(GridPaneAdmin);
                    });
                }catch (Exception e){
                    System.out.println("ERROR botao voltar " + e);
                }
                vboxAdmin.setSpacing(5);
                vboxAdmin.setPadding(new Insets(10, 0, 0, 10));
                vboxAdmin.getChildren().addAll(label, table);

        }catch (Exception e){
        System.out.println("Error ao listar produtos " + e);
        }
    }

    public void AtualuzarComandas(ActionEvent actionEvent) {

        ComandaDAO AdminComanda = new ComandaDAO();
        List<Comanda> listComandas = AdminComanda.buscaAllComanda();

        for (int index = 0; index < listComandas.size(); index++) {
            if (listComandas.get(index).getMesa() == 1 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade1.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal1.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa1.setStyle("-fx-fill: RED;");
                admMesaNum1.setStyle("-fx-fill: #FFFFFF;");
            }
            if (listComandas.get(index).getMesa() == 2 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade2.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal2.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa2.setStyle("-fx-fill: RED;");
                admMesaNum2.setStyle("-fx-fill: #FFFFFF;");
            }
            if (listComandas.get(index).getMesa() == 3 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade3.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal3.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa3.setStyle("-fx-fill: RED;");
                admMesaNum3.setStyle("-fx-fill: #FFFFFF;");
            }
            if (listComandas.get(index).getMesa() == 4 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade4.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal4.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa4.setStyle("-fx-fill: RED;");
                admMesaNum4.setStyle("-fx-fill: #FFFFFF;");
            }
            if (listComandas.get(index).getMesa() == 5 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade5.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal5.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa5.setStyle("-fx-fill: RED;");
                admMesaNum5.setStyle("-fx-fill: #FFFFFF;");
            }
            if (listComandas.get(index).getMesa() == 6 && listComandas.get(index).isPago() == false && listComandas.get(index).getDataFinaliza() == null){
                mesaQtade6.setText(String.valueOf(listComandas.get(index).getQuantidade()));
                mesaTotal6.setText("R$ " + String.valueOf(listComandas.get(index).getValor_total()));
                adminMesa6.setStyle("-fx-fill: RED;");
                admMesaNum6.setStyle("-fx-fill: #FFFFFF;");
            }
        }

    }

    public void DialogComandaVazia(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Comanda !");
        alert.setHeaderText("Comanda vazia !!");
        alert.setContentText("Adicione produtos para finalizar o pedido");
        alert.showAndWait();
    }

    public void EncerrarComandaUm(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(1);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 1");
        String s = "Comanda: " + encerrar.getNome_comanda()+"\nQuantidade: " + encerrar.getQuantidade()+"\nValor Total: R$ "+encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Date dataJava = new Date();
                java.sql.Date dateFinal = new java.sql.Date(dataJava.getTime());
                Comanda encerrarUpdate = new Comanda();

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dateFinal);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade1.setText("");
                mesaTotal1.setText("");
                adminMesa1.setStyle("-fx-fill: #1e90ff;");
                admMesaNum1.setStyle("-fx-fill: #000000;");
            }catch (Exception e){
                System.out.println("ERROR ao encerrar a comanda 1" + e);
            }
        }if((result.isPresent()) && (result.get() == ButtonType.CANCEL)){
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("ERROR " + e);
        }
    }

    public void EncerrarComandaDois(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(2);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 2");
        String s = "Comanda: " + encerrar.getNome_comanda()+"\nQuantidade: " + encerrar.getQuantidade()+"\nValor Total: R$ "+encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Comanda encerrarUpdate = new Comanda();

                java.util.Date dateJava = new java.util.Date();
                java.sql.Date dataSql = new java.sql.Date(dateJava.getTime());

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dataSql);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade2.setText("");
                mesaTotal2.setText("");
                adminMesa2.setStyle("-fx-fill: #1e90ff;");
                admMesaNum2.setStyle("-fx-fill: #000000;");
            }catch (Exception e){
                System.out.println("ERROR ao encerrar a comanda 2" + e);
            }
        }if((result.isPresent()) && (result.get() == ButtonType.CANCEL)){
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("ERROR " + e);
        }
    }

    public void EncerrarComandaTres(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(3);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 3");
        String s = "Comanda: " + encerrar.getNome_comanda()+"\nQuantidade: " + encerrar.getQuantidade()+"\nValor Total: R$ "+encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Date dataJava = new Date();
                java.sql.Date dateFinal = new java.sql.Date(dataJava.getTime());
                Comanda encerrarUpdate = new Comanda();

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dateFinal);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade3.setText("");
                mesaTotal3.setText("");
                adminMesa3.setStyle("-fx-fill: #1e90ff;");
                admMesaNum3.setStyle("-fx-fill: #000000;");
            }catch (Exception e){
                System.out.println("ERROR ao encerrar a comanda 3" + e);
            }
        }if((result.isPresent()) && (result.get() == ButtonType.CANCEL)){
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("ERROR " + e);
        }
    }

    public void EncerrarComandaQuatro(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(4);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 4");
        String s = "Comanda: " + encerrar.getNome_comanda()+"\nQuantidade: " + encerrar.getQuantidade()+"\nValor Total: R$ "+encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Date dataJava = new Date();
                java.sql.Date dateFinal = new java.sql.Date(dataJava.getTime());
                Comanda encerrarUpdate = new Comanda();

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dateFinal);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade4.setText("");
                mesaTotal4.setText("");
                adminMesa4.setStyle("-fx-fill: #1e90ff;");
                admMesaNum4.setStyle("-fx-fill: #000000;");
            }catch (Exception e){
                System.out.println("ERROR ao encerrar a comanda 4" + e);
            }
        }if((result.isPresent()) && (result.get() == ButtonType.CANCEL)){
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("Error " + e);
        }
    }

    public void EncerrarComandaCinco(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(3);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 5");
        String s = "Comanda: " + encerrar.getNome_comanda()+"\nQuantidade: " + encerrar.getQuantidade()+"\nValor Total: R$ "+encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Date dataJava = new Date();
                java.sql.Date dateFinal = new java.sql.Date(dataJava.getTime());
                Comanda encerrarUpdate = new Comanda();

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dateFinal);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade5.setText("");
                mesaTotal5.setText("");
                adminMesa5.setStyle("-fx-fill: #1e90ff;");
                admMesaNum5.setStyle("-fx-fill: #000000;");
            }catch (Exception e){
                System.out.println("ERROR ao encerrar a comanda 5" + e);
            }
        }if((result.isPresent()) && (result.get() == ButtonType.CANCEL)){
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("ERROR " + e);
        }
    }

    public void EncerrarComandaSeis(ActionEvent actionEvent) {
        try {
        ComandaDAO comandaDAO = new ComandaDAO();
        Comanda encerrar = comandaDAO.buscarMesa(6);

        System.out.println("numero mesa " + encerrar.getMesa());
        System.out.println("nome " + encerrar.getNome_comanda());
        System.out.println("quantidade " + encerrar.getQuantidade());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comanda 6");
        String s = "Comanda: " + encerrar.getNome_comanda() + "\nQuantidade: " + encerrar.getQuantidade() + "\nValor Total: R$ " + encerrar.getValor_total();
        alert.setContentText(s);

        Optional<ButtonType> result = alert.showAndWait();

        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            try {
                Date dataJava = new Date();
                java.sql.Date dateFinal = new java.sql.Date(dataJava.getTime());
                Comanda encerrarUpdate = new Comanda();

                encerrarUpdate.setStatus_pagamento("Pago");
                encerrarUpdate.setDataFinaliza(dateFinal);
                encerrarUpdate.setPago(true);
                encerrarUpdate.setId(encerrar.getId());

                comandaDAO.encerrarComanda(encerrarUpdate);

                mesaQtade6.setText("");
                mesaTotal6.setText("");
                adminMesa6.setStyle("-fx-fill: #1e90ff;");
                admMesaNum6.setStyle("-fx-fill: #000000;");
            } catch (Exception e) {
                System.out.println("ERROR ao encerrar a comanda 6" + e);
            }
        }
        if ((result.isPresent()) && (result.get() == ButtonType.CANCEL)) {
            alert.close();
        }
        }catch (Exception e){
            DialogComandaVazia();
            System.out.println("ERROR " + e);
        }
    }


}
