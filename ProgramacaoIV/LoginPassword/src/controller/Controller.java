package controller;

import Model.Cliente;
import Model.ClienteDAO;
import Model.User;
import Model.UserDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class Controller {

    // VARIAVEIS GLOBAIS
    User user = new User();
    Cliente cliente = new Cliente();
    UserDAO db = new UserDAO();
    ClienteDAO clienteDAO = new ClienteDAO();
    String salt =  "TG4QV9mJ3uE3wJMdo9lZzvxaGfIuTcKxCyC2s0XU";
    Integer idBuscaCliente;


    @FXML
    private Tab idPaneLogin;

    @FXML
    private TextField idInputUser;

    @FXML
    private PasswordField idInputSenha;

    @FXML
    private Button idBotaoEntrar;

    @FXML
    private Button idCadastrarLogin;

    @FXML
    private Label idLabelLogin;

    @FXML
    private CheckBox idCheck;

    @FXML
    private Label showpass;

    @FXML
    private Tab idPaneCadastro;

    @FXML
    private TextField idNomeCadastro;

    @FXML
    private TextField idSenhaCadastro;

    @FXML
    private TextField idConfirmSenhaCadastro;

    @FXML
    private Button idBotaoCadastCadatrar;

    @FXML
    private Label idLabelCadastrar;

    @FXML
    private Tab idPaneHome;

    @FXML
    private Button idLogout;

    @FXML
    private Button BtnCadasCliente;

    @FXML
    private Button BtnEditCliente;

    @FXML
    private Button BtnDelCliente;

    @FXML
    private Button BtnBuscaCliente;

    @FXML
    private TextField inoutBuscaCliente;

    @FXML
    private TextField inputDelCliente;

    @FXML
    private Tab idTabCadastraCliente;

    @FXML
    private TextField inputNomeCadastro;

    @FXML
    private TextField inputEmailCadastro;

    @FXML
    private TextField inputTelefoneCadastro;

    @FXML
    private TextField inputEnderecoCadastro;

    @FXML
    private DatePicker inputDataCadastro;

    @FXML
    private TextField inputCpfCadastro;

    @FXML
    private Button BtnNewCadastro;

    @FXML
    private Tab idTabEditaCliente;


    @FXML
    private Button BtnBuscaUpdate;

    @FXML
    private TextField inputBuscaUpdate;

    @FXML
    private TextField inputNomeUpdate;

    @FXML
    private TextField inputEnderecoUpdate;

    @FXML
    private TextField inputEmailUpdate;

    @FXML
    private TextField inputTelefoneUpdate;

    @FXML
    private TextField inputCpfUpdate;

    @FXML
    private DatePicker inputDataUpdate;

    @FXML
    private Button BtnAtualizarUpdate;

    @FXML
    private Label labelVbox;

    @FXML
    private VBox stackVBOX;

    @FXML
    private Label labelInfoDeletar;

    @FXML
    private Label labelBuscaCliente;

    @FXML
    private RadioButton checkAdmin;

    @FXML
    private Tab TabPaneAdmin;

    @FXML
    private Button BtnAdmCadastraFunc;

    @FXML
    private TitledPane TitlePaneFunc;

    @FXML
    private VBox vBoxInTitled;

    @FXML
    private Button AdmBtnExcFunc;

    @FXML
    private TextField AdmInputExcFunc;

    @FXML
    private Label nameFuncAdm;

    @FXML
    private AnchorPane TabPaneCliente;

    @FXML
    private Label nameFuncCadastro;

    @FXML
    private Label nameFuncHome;

    @FXML
    private Label nameFuncCadCliente;

    @FXML
    private Label nameAreaClient;

    @FXML
    private Label labelSaidaDel;

    @FXML
    private MenuItem MyCadastrarCliente;

    @FXML
    private MenuItem AtualizarMeuPerfil;

    @FXML
    private MenuItem excluirMeuPerfil;

    @FXML
    private VBox vboxCliente;


    public void PreencheLabelFunc(String nome, Boolean admin){
        if (admin){
            nameFuncAdm.setText("Admin: " + nome);
            nameFuncHome.setText("Funcionário " + nome);
            nameFuncCadastro.setText("Funcionário " + nome);
            nameFuncCadCliente.setText("Funcionário " + nome);
        }else {
            nameFuncHome.setText("Funcionário " + nome);
            nameFuncCadastro.setText("Funcionário " + nome);
            nameFuncCadCliente.setText("Funcionário " + nome);
        }
    }


    public void Login(ActionEvent actionEvent) {
        idLabelLogin.setText("");
        String getUsername = idInputUser.getText().toString();
        String getSenha = idInputSenha.getText().toString();

        // gera senha em Base64
        System.out.println("Salt " + salt);

        try{
            // busca user no banco de dados
            User compareUser = db.buscar(getUsername);
            boolean readPass = EncryptUser.verifyUserPassword(getSenha, compareUser.getSenha(), salt);

            // User ADMIN
            if(readPass && getUsername.equals(compareUser.getNome()) && compareUser.getAdmin()){
                System.out.println("Admin " + compareUser.getAdmin());
                idLabelLogin.setText("Acesso autorizado !");
                System.out.println("Usuario  " + compareUser.getNome() + " Senha " + compareUser.getSenha() +
                        " é ADMIN " + compareUser.getAdmin());
                idPaneHome.setDisable(false);
                idTabCadastraCliente.setDisable(false);
                idTabEditaCliente.setDisable(false);
                idPaneCadastro.setDisable(false);
                TabPaneAdmin.setDisable(false);

                PreencheLabelFunc(compareUser.getNome(), compareUser.getAdmin());
                idPaneHome.getTabPane().getSelectionModel().select(
                        idPaneHome.getTabPane().getTabs().size() - 5);

            // User não é admin
            }else if(readPass && getUsername.equals(compareUser.getNome()) && !compareUser.getAdmin()){
                System.out.println("Admin " + compareUser.getAdmin());
                idLabelLogin.setText("Acesso autorizado !");
                System.out.println("Usuario  " + compareUser.getNome() + " Senha " + compareUser.getSenha() +
                        " é ADMIN " + compareUser.getAdmin());
                idPaneHome.setDisable(false);
                idTabCadastraCliente.setDisable(false);
                idTabEditaCliente.setDisable(false);
                idPaneCadastro.setDisable(true);
                TabPaneAdmin.setDisable(true);
                PreencheLabelFunc(compareUser.getNome(), compareUser.getAdmin());
                idPaneHome.getTabPane().getSelectionModel().select(
                        idPaneHome.getTabPane().getTabs().size() - 5);
            }
        }catch (Exception e){
            idLabelLogin.setText("Usuario ou senha incorreto!");
            idPaneHome.setDisable(true);
            System.out.println("ERROR " + e);
        }
        idInputUser.setText("");
        idInputSenha.setText("");
    }

    public void CadastrarUser(ActionEvent actionEvent) {
        idLabelCadastrar.setText("");
        String nomeUser = idNomeCadastro.getText().toString();
        String senha = idSenhaCadastro.getText().toString();
        String confirmSenha = idConfirmSenhaCadastro.getText().toString();
        Boolean admin = checkAdmin.isSelected();

        // gera senha em Base64
        String myPassSegure = EncryptUser.generateSecurePassword(senha, salt);
        String myPassSegureConfirm = EncryptUser.generateSecurePassword(confirmSenha, salt);

        try {
            if(!nomeUser.isEmpty() && senha.equals(confirmSenha)){

                idNomeCadastro.setText("");
                idSenhaCadastro.setText("");
                idConfirmSenhaCadastro.setText("");

                user.setNome(nomeUser);
                user.setSenha(myPassSegure);
                user.setAdmin(admin);
                db.inserir(user);

                idLabelCadastrar.setText("Usuario Cadastrado com sucesso !!");
                System.out.println("Usuario " + user.getNome() +  " Senha " + user.getSenha() + " é ADMIN " + user.getAdmin() +
                        "\n Cadastrado com sucesso !!");
                System.out.println("Salt " + salt);
                idPaneLogin.getTabPane().getSelectionModel().select(
                        idPaneLogin.getTabPane().getTabs().size() - 2);
                idLabelCadastrar.setText("");
            }
        }catch (Exception e){
            if(!myPassSegure.equals(myPassSegureConfirm)){
                idLabelCadastrar.setText("As senha devem ser IGUAIS");
            }
            if(nomeUser.isEmpty()){
                idLabelCadastrar.setText("Nome do usuario nao pode ser vazio !");
            }
            System.out.println("ERROR AO CADASTRAR " + e);
        }
        idPaneLogin.getTabPane().getSelectionModel().select(
                idPaneLogin.getTabPane().getTabs().size() - 5);
        idLabelCadastrar.setText("");
    }

    public void showPass(ActionEvent actionEvent) {
        if(idCheck.isSelected()){
            showpass.setText(idInputSenha.getText().toString());
        }else{
            showpass.setText("");
        }
    }

    public void Logout(ActionEvent actionEvent) {
        System.out.println("Saindo do sistema....");
        idPaneHome.setDisable(true);
        idTabCadastraCliente.setDisable(true);
        idTabEditaCliente.setDisable(true);
        TabPaneAdmin.setDisable(true);
        idPaneCadastro.setDisable(true);
        idPaneLogin.getTabPane().getSelectionModel().select(
                idPaneLogin.getTabPane().getTabs().size() -7);

        stackVBOX.getChildren().clear();
        labelInfoDeletar.setText("");
        labelBuscaCliente.setText("");
    }

    public void goCadastroC(ActionEvent actionEvent) {
        stackVBOX.getChildren().clear();
        labelInfoDeletar.setText("");
        labelBuscaCliente.setText("");
        System.out.println("Acessando pagina de cadastro");
        idTabCadastraCliente.getTabPane().getSelectionModel().select(
                idTabCadastraCliente.getTabPane().getTabs().size() - 4 );
    }

    public void CadastrarNewCliente(ActionEvent actionEvent) {

        cliente.setNome(inputNomeCadastro.getText().toString());
        cliente.setEmail(inputEmailCadastro.getText().toString());
        cliente.setTelefone(inputTelefoneCadastro.getText().toString());
        cliente.setEndereco(inputEnderecoCadastro.getText().toString());
        cliente.setDataNascimento(inputDataCadastro.getValue().toString());
        cliente.setCpf(inputCpfCadastro.getText().toString());
        clienteDAO.inserirCliente(cliente);

        inputNomeCadastro.clear();
        inputEmailCadastro.clear();
        inputTelefoneCadastro.clear();
        inputEnderecoCadastro.clear();
        inputDataCadastro.setValue(null);
        inputCpfCadastro.clear();

        idPaneHome.getTabPane().getSelectionModel().select(
                idPaneHome.getTabPane().getTabs().size() -5);

    }

    public void goEditarCliente(ActionEvent actionEvent) {
        stackVBOX.getChildren().clear();
        labelInfoDeletar.setText("");
        labelBuscaCliente.setText("");
        idTabEditaCliente.getTabPane().getSelectionModel().select(
                idTabEditaCliente.getTabPane().getTabs().size() -3);
    }

    public void BuscarClienteAtualizar(ActionEvent actionEvent) {
        try {
        cliente = clienteDAO.buscarCliente(inputBuscaUpdate.getText().toString());
        inputNomeUpdate.setText(cliente.getNome());
        inputEmailUpdate.setText(cliente.getEmail());
        inputTelefoneUpdate.setText(cliente.getTelefone());
        inputEnderecoUpdate.setText(cliente.getEndereco());
        inputDataUpdate.setValue(LocalDate.parse(cliente.getDataNascimento()));
        inputCpfUpdate.setText(cliente.getCpf());
        idBuscaCliente = cliente.getId();
        inputBuscaUpdate.clear();
        labelBuscaCliente.setText("");
    }catch (Exception e){
            labelBuscaCliente.setText("Cliente " + inputBuscaUpdate.getText().toString() + " não encontrado !");
            System.out.println("ERRO AO BUSCAR CLIENTE NA ATUALIZAÇÃO " + e);
        }
    }

    public void UpdateCliente(ActionEvent actionEvent) {
        cliente.setNome(inputNomeUpdate.getText().toString());
        cliente.setEmail(inputEmailUpdate.getText().toString());
        cliente.setTelefone(inputTelefoneUpdate.getText().toString());
        cliente.setEndereco(inputEnderecoUpdate.getText().toString());
        cliente.setDataNascimento(inputDataUpdate.getValue().toString());
        cliente.setCpf(inputCpfUpdate.getText().toString());
        cliente.setId(idBuscaCliente);
        clienteDAO.atualizarCliente(cliente);

//        System.out.println(cliente.getId());

        inputNomeUpdate.clear();
        inputEmailUpdate.clear();
        inputTelefoneUpdate.clear();
        inputEnderecoUpdate.clear();
        inputDataUpdate.setValue(null);
        inputCpfUpdate.clear();

        idPaneHome.getTabPane().getSelectionModel().select(
                idPaneHome.getTabPane().getTabs().size() -5);
    }

    public void findCliente(ActionEvent actionEvent) throws IOException {
        List<Cliente> cliente = clienteDAO.buscaAllClientes();
        for (int i=0; i< cliente.size(); i++){
            stackVBOX.getChildren().add(new Label(cliente.get(i).getNome()));
        }
    }

    public void CancelarUpdate(ActionEvent actionEvent) {

        inputNomeUpdate.clear();
        inputEmailUpdate.clear();
        inputTelefoneUpdate.clear();
        inputEnderecoUpdate.clear();
        inputDataUpdate.setValue(null);
        inputCpfUpdate.clear();
        labelBuscaCliente.setText("");

        idPaneHome.getTabPane().getSelectionModel().select(
                idPaneHome.getTabPane().getTabs().size() -5);

    }

    public void SairAPP(ActionEvent actionEvent) {
        stackVBOX.getChildren().clear();
        labelInfoDeletar.setText("");
        labelBuscaCliente.setText("");
        System.exit(0);
    }

    public void CancelarCadastro(ActionEvent actionEvent) {
        inputNomeCadastro.clear();
        inputEmailCadastro.clear();
        inputTelefoneCadastro.clear();
        inputEnderecoCadastro.clear();
        inputDataCadastro.setValue(null);
        inputCpfCadastro.clear();

        idPaneHome.getTabPane().getSelectionModel().select(
                idPaneHome.getTabPane().getTabs().size() -5);
    }

    public void DeletarCliente(ActionEvent actionEvent) {
        try {
            cliente =  clienteDAO.buscarCliente(inputDelCliente.getText().toString());
            labelInfoDeletar.setText("Cliente " + cliente.getNome() + " foi deletado !");
            inputDelCliente.setText("");
            clienteDAO.deletarCliente(cliente.getId());
        }catch (Exception e){
            labelInfoDeletar.setText("CLIENTE NÃO ENCONTRADO !!");
            inputDelCliente.setText("");
            System.out.println("ERROR AO DELETAR " + e);
        }
    }

    public void OpenTitledPane(MouseEvent mouseEvent) {
        if(TitlePaneFunc.isExpanded()){
            try {
                List<User> funcionario = db.buscaAll();
                for (int i=0; i< funcionario.size(); i++){
                    System.out.println(funcionario.get(i).getNome());
                    vBoxInTitled.getChildren().add(new Label(funcionario.get(i).getNome() +
                            " --- Admin --- " +  (funcionario.get(i).getAdmin())));
                }
            }catch (Exception e){
                System.out.println("OCORREU ALGUM ERRO AO COLAPSAR " + e);
            }
        }else {
            vBoxInTitled.getChildren().clear();
            System.out.println("To aqui");
        }
    }


    public void CadastraFunc(ActionEvent actionEvent) {
        TabPaneAdmin.getTabPane().getSelectionModel().select(
                TabPaneAdmin.getTabPane().getTabs().size() -6);

    }

    public void DeletarFuncionario(ActionEvent actionEvent) {
        try {
            User funcioanrio = db.buscar(AdmInputExcFunc.getText().toString());
            labelSaidaDel.setText("Funcionário " + funcioanrio.getNome() + " DELETADO ! ");
            db.deletar(funcioanrio.getId());
        }catch (Exception e){
            labelSaidaDel.setText("Funcionário não encontrado");
        }
    }


    /***
     *
     *  PARTE DO CLIENTE
     *  Pane que o cliente faz seu cadastro, editar, busca dados e  exclui seu perfil do banco de dados.
     *  Foi criado um Vbox para add os inputs, label e botoes dinamicamente, utilizado eventos dos botoes.
     *
     * ***/

    public void MyCadastrarCliente(ActionEvent actionEvent) {
        vboxCliente.getChildren().clear();
        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("CADASTRAR INFORMAÇÕES PESSOAIS");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField textName = new TextField();
        TextField textEmail = new TextField();
        TextField textTelefone = new TextField();
        TextField textEndereco = new TextField();
        DatePicker textDataNasc = new DatePicker();
        TextField textCPF = new TextField();

        Button btnCadastraCliente = new Button();
        btnCadastraCliente.setText("Cadastrar Cliente");

        Button btnCancelarCadastro = new Button();
        btnCancelarCadastro.setText("Cancelar");
        btnCancelarCadastro.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarCadastro.setStyle("-fx-background-color:red;");

        vboxCliente.getChildren().add(labelTitle);
        vboxCliente.getChildren().add(new Label("Nome"));
        vboxCliente.getChildren().add(textName);
        vboxCliente.getChildren().add(new Label("E-mail"));
        vboxCliente.getChildren().add(textEmail);
        vboxCliente.getChildren().add(new Label("Telefone"));
        vboxCliente.getChildren().add(textTelefone);
        vboxCliente.getChildren().add(new Label("Endereço"));
        vboxCliente.getChildren().add(textEndereco);
        vboxCliente.getChildren().add(new Label("Data Nascimento"));
        vboxCliente.getChildren().add(textDataNasc);
        vboxCliente.getChildren().add(new Label("CPF"));
        vboxCliente.getChildren().add(textCPF);
        vboxCliente.getChildren().add(btnCadastraCliente);

        btnCadastraCliente.setOnAction( value -> {
            try {
                cliente.setNome(textName.getText().toString());
                cliente.setEmail(textEmail.getText().toString());
                cliente.setTelefone(textTelefone.getText().toString());
                cliente.setEndereco(textEndereco.getText().toString());
                cliente.setDataNascimento(textDataNasc.getValue().toString());
                cliente.setCpf(textCPF.getText().toString());
                clienteDAO.inserirCliente(cliente);

                textName.clear();
                textCPF.clear();
                textEmail.clear();
                textDataNasc.setValue(null);
                textEndereco.clear();
                textTelefone.clear();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Cadastrado");
                String s = "Cliente " + cliente.getNome() + " cadastrado com sucesso !";
                alert.setContentText(s);
                alert.showAndWait();
            }catch (Exception e){
                System.out.println("Erro ao cadastrar cliente " + e);
            }
        });
        vboxCliente.getChildren().add(btnCancelarCadastro);
        btnCancelarCadastro.setOnAction( cancelar -> {
            vboxCliente.getChildren().clear();
        });

    }

    public void AtualizarMeuPerfil(ActionEvent actionEvent) {
        vboxCliente.getChildren().clear();

        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#428af5", 0.8));
        labelTitle.setText("ATUALIZAR INFORMAÇÕES PESSOAIS");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");

        TextField buscaCliente = new TextField();
        Button btnBuscaCliente = new Button("Buscar Cliente");

        Button btnCancelarUpdate = new Button();
        btnCancelarUpdate.setText("Cancelar");
        btnCancelarUpdate.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarUpdate.setStyle("-fx-background-color:red;");

        vboxCliente.getChildren().add(buscaCliente);
        vboxCliente.getChildren().add(btnBuscaCliente);

        btnBuscaCliente.setOnAction( event -> {
            try {
                cliente = clienteDAO.buscarCliente(buscaCliente.getText().toString());

                TextField textName = new TextField();
                TextField textEmail = new TextField();
                TextField textTelefone = new TextField();
                TextField textEndereco = new TextField();
                DatePicker textDataNasc = new DatePicker();
                TextField textCPF = new TextField();

                textName.setText(cliente.getNome());
                textEmail.setText(cliente.getEmail());
                textTelefone.setText(cliente.getTelefone());
                textEndereco.setText(cliente.getEndereco());
                textDataNasc.setValue(LocalDate.parse(cliente.getDataNascimento()));
                textCPF.setText(cliente.getCpf());

                Button btnUpdateCliente = new Button();
                btnUpdateCliente.setText("Atualizar Cadastro");

                vboxCliente.getChildren().add(labelTitle);
                vboxCliente.getChildren().add(new Label("Nome"));
                vboxCliente.getChildren().add(textName);
                vboxCliente.getChildren().add(new Label("E-mail"));
                vboxCliente.getChildren().add(textEmail);
                vboxCliente.getChildren().add(new Label("Telefone"));
                vboxCliente.getChildren().add(textTelefone);
                vboxCliente.getChildren().add(new Label("Endereço"));
                vboxCliente.getChildren().add(textEndereco);
                vboxCliente.getChildren().add(new Label("Data Nascimento"));
                vboxCliente.getChildren().add(textDataNasc);
                vboxCliente.getChildren().add(new Label("CPF"));
                vboxCliente.getChildren().add(textCPF);
                vboxCliente.getChildren().add(btnUpdateCliente);

                btnUpdateCliente.setOnAction( value -> {
                    try {
                        cliente.setNome(textName.getText().toString());
                        cliente.setEmail(textEmail.getText().toString());
                        cliente.setTelefone(textTelefone.getText().toString());
                        cliente.setDataNascimento(textDataNasc.getValue().toString());
                        cliente.setCpf(textCPF.getText().toString());
                        cliente.setEndereco(textEndereco.getText().toString());
                        clienteDAO.atualizarCliente(cliente);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Atualização ok");
                        String s = "Seus dados foram atualizados com sucesso !";
                        alert.setContentText(s);
                        vboxCliente.getChildren().clear();
                        alert.showAndWait();

                    }catch (Exception e){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Alert");
                        String s = "Erro ao Atualizar cliente";
                        alert.setContentText(s);
                        alert.showAndWait();
                    }
                });
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Alert");
                String s = "Cliente " + buscaCliente.getText().toString() + " encontrado, tente novamente !";
                alert.setContentText(s);
                alert.showAndWait();
            }
        });
        vboxCliente.getChildren().add(btnCancelarUpdate);
        btnCancelarUpdate.setOnAction( cancelarUpdate -> {
            vboxCliente.getChildren().clear();
        });
    }

    public void ExcluirPerfil(ActionEvent actionEvent) {
        vboxCliente.getChildren().clear();

        Label labelTitle = new Label();
        labelTitle.setTextFill(Color.web("#db0d0d", 0.8));
        labelTitle.setText("EXCLUIR PERFIL PESSOAIS");
        labelTitle.setAlignment(Pos.CENTER);
        labelTitle.setStyle("-fx-font-size:17px;-fx-font-weight: bold;");
        vboxCliente.getChildren().add(labelTitle);

        TextField buscaCliente = new TextField();
        Button btnBuscaCliente = new Button("Buscar meu Perfil");

        Button btnCancelarExclusao = new Button();
        btnCancelarExclusao.setText("Cancelar");
        btnCancelarExclusao.setTextFill(Color.web("#ffffff", 0.8));
        btnCancelarExclusao.setStyle("-fx-background-color:red;");

        vboxCliente.getChildren().add(buscaCliente);
        vboxCliente.getChildren().add(btnBuscaCliente);

        btnBuscaCliente.setOnAction( event -> {
            try {
                cliente = clienteDAO.buscarCliente(buscaCliente.getText().toString());

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Excluir ?");
                String s = "Tem certeza que deseja EXCLUIR sua conta " + cliente.getNome() + " ?";
                alert.setContentText(s);
                alert.showAndWait().filter(ButtonType.OK::equals).ifPresent( b -> {
                    clienteDAO.deletarCliente(cliente.getId());
                    vboxCliente.getChildren().clear();
                });
            } catch (Exception e) {
                System.out.println("ERRO ao excluir " + e);
            }
    });
        vboxCliente.getChildren().add(btnCancelarExclusao);
        btnCancelarExclusao.setOnAction(cancelarExcluir -> {
            vboxCliente.getChildren().clear();
        });
    }
}

