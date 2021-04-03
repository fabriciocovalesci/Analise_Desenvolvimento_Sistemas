package patzer.gabriel.controller;

import javafx.collections.ObservableArray;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import patzer.gabriel.model.Produto;
import patzer.gabriel.model.database.DAO_Produto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ctrlBaseCRUD {

    private DAO_Produto dao_produto;

    public ctrlBaseCRUD(){
        dao_produto = new DAO_Produto();
    }

    @FXML
    private Accordion accordion;

    @FXML
    private TitledPane paneListar;

    @FXML
    private Button btnAtualizar;

    @FXML
    private TextArea txtListagem;

    @FXML
    private TextField txtNome;

    @FXML
    private Button btnBuscarPorNome;

    @FXML
    private Label labId;

    @FXML
    private Label labNome;

    @FXML
    private Label labDescricao;

    @FXML
    private Label labPreco;

    @FXML
    private Label labQuantidade;

    @FXML
    private TextField txtId;

    @FXML
    private Button btnBuscarId;

    @FXML
    private Label labId1;

    @FXML
    private Label labNome1;

    @FXML
    private Label labDescricao1;

    @FXML
    private Label labPreco1;

    @FXML
    private Label labQuantidade1;

    @FXML
    private TextField txNovoNome;

    @FXML
    private TextField txNovoDescricao;

    @FXML
    private TextField txNovoPreco;

    @FXML
    private TextField txNovoQuantidade;

    @FXML
    private Button btnInserir;


    @FXML
    void atualizarListagem(ActionEvent event) {
        ArrayList<Produto> produtos = dao_produto.visualizar();
        txtListagem.setText("");
        for(Produto produto: produtos){
            txtListagem.appendText(produto.toString());
            txtListagem.appendText("\n");
        }
    }

    @FXML
    void buscarPorId(ActionEvent event) {
        Produto produto = dao_produto.visualizar(Integer.valueOf(txtId.getText()));
        if (produto != null) {
            labId.setText(produto.get_id().toString());
            labId1.setText(produto.get_id().toString());
            labNome.setText(produto.getNome());
            labNome1.setText(produto.getNome());
            labDescricao.setText(produto.getDescricao());
            labDescricao1.setText(produto.getDescricao());
            labPreco.setText(String.valueOf(produto.getPreco()));
            labPreco1.setText(String.valueOf(produto.getPreco()));
            labQuantidade.setText(String.valueOf(produto.getQuantidade()));
            labQuantidade1.setText(String.valueOf(produto.getQuantidade()));
        } else {
            labDescricao.setText("");
            labDescricao1.setText("");
            labId.setText("");
            labId1.setText("");
            labNome.setText("Produto não encontrado!");
            labNome1.setText("Produto não encontrado!");
            labPreco.setText("");
            labPreco1.setText("");
            labQuantidade.setText("");
            labQuantidade1.setText("");
        }
    }

    @FXML
    void buscarPorNome(ActionEvent event) {
        Produto produto = dao_produto.visualizar(txtNome.getText());
        if (produto != null) {
            labId.setText(produto.get_id().toString());
            labId1.setText(produto.get_id().toString());
            labNome.setText(produto.getNome());
            labNome1.setText(produto.getNome());
            labDescricao.setText(produto.getDescricao());
            labDescricao1.setText(produto.getDescricao());
            labPreco.setText(String.valueOf(produto.getPreco()));
            labPreco1.setText(String.valueOf(produto.getPreco()));
            labQuantidade.setText(String.valueOf(produto.getQuantidade()));
            labQuantidade1.setText(String.valueOf(produto.getQuantidade()));
        } else {
            labDescricao.setText("");
            labDescricao1.setText("");
            labId.setText("");
            labId1.setText("");
            labNome.setText("Produto não encontrado!");
            labNome1.setText("Produto não encontrado!");
            labPreco.setText("");
            labPreco1.setText("");
            labQuantidade.setText("");
            labQuantidade1.setText("");
        }

    }

    // TODO mensagens/alertas de erro e de confirmação
    @FXML
    void excluirPorID(ActionEvent event) {
        if (labId.getText() != "") {
            dao_produto.excluir(Integer.valueOf(labId.getText()));
        }
    }

    @FXML
    void excluirPorNome(ActionEvent event) {
        if (labNome.getText() != "") {
            dao_produto.excluir(labNome.getText());
        }
    }

    @FXML
    void inserirProduto(ActionEvent event) {
        // TODO sanitização das caixas de texto
        // cuidado: valores double são divididos com . ao invés de ,
        Produto produto = new Produto(txNovoNome.getText(), txNovoDescricao.getText(),
                Double.valueOf(txNovoPreco.getText()), Integer.valueOf(txNovoQuantidade.getText()));
        dao_produto.inserir(produto);
    }


    @FXML
    void initialize(){
        btnAtualizar.fire();
        accordion.setExpandedPane(paneListar);
    }
}
