package patzer.gabriel.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import patzer.gabriel.model.Produto;
import patzer.gabriel.model.ProdutoParaTabela;
import patzer.gabriel.model.database.DAO_ProdutoParaTabela;

public class ctrlTabelas {
    private DAO_ProdutoParaTabela dao_produtoParaTabela;
    private ObservableList<ProdutoParaTabela> observableList;

    public ctrlTabelas() {
        dao_produtoParaTabela = new DAO_ProdutoParaTabela();
        observableList = dao_produtoParaTabela.listar();
    }

    @FXML
    private TableView<ProdutoParaTabela> tabProdutos;

    @FXML
    private TabPane tabView;

    @FXML
    private TableColumn<ProdutoParaTabela, Integer> colID;

    @FXML
    private TableColumn<ProdutoParaTabela, String> colNome;

    @FXML
    private TableColumn<ProdutoParaTabela, Double> colPreco;

    @FXML
    private TableColumn<ProdutoParaTabela, Integer> colQuantidade;

    @FXML
    private TextField txNome;

    @FXML
    private TextArea txDesc;

    @FXML
    private TextField txPreco;

    @FXML
    private TextField txQtd;

    @FXML
    void inserir(ActionEvent event) {
        //TODO captar e criar produto depois atualizar tabela
        ProdutoParaTabela p = new ProdutoParaTabela(-1, txNome.getText(), Double.parseDouble(txPreco.getText()), Integer.parseInt(txQtd.getText()));
        int id = dao_produtoParaTabela.inserir(p); // Ao inserir o produto novo direto na tabela ele não possui ID do banco
        if (id > -1) {
            p.set_id(id);
            observableList.add(p);
        }
        tabView.getSelectionModel().select(0);
    }

    @FXML
    void atualizarTabela(ActionEvent event) {
        tabView.getSelectionModel().select(1);
    }

    @FXML
    void proxCampo(ActionEvent event) {
        //TODO focus
    }

    /**
     * Além de alterar o banco é necessário alterar a ObservableList, pois ela é quem atualiza a tabela
     */
    @FXML
    void remover(ActionEvent event) {
        ProdutoParaTabela p = tabProdutos.getSelectionModel().getSelectedItem();
        dao_produtoParaTabela.excluir(p);
        observableList.remove(p);
    }


    public void initialize() {
        // Associa os elementos do banco à tabela
        colID.setCellValueFactory(new PropertyValueFactory<>("_id"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colQuantidade.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // Cria converters para permitir digitar o texto na célula
        StringConverter<Double> stringConverter = new StringConverter<Double>() {
            @Override
            public String toString(Double aDouble) {
                return Double.toString(aDouble);
            }

            @Override
            public Double fromString(String s) {
                return Double.parseDouble(s);
            }
        };
        StringConverter<Integer> stringConverterInt = new StringConverter<Integer>() {
            @Override
            public String toString(Integer integer) {
                return Integer.toString(integer);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        };

        // Transforma a célula em editável, utilizando o converter quando não for String
        colPreco.setCellFactory(TextFieldTableCell.forTableColumn(stringConverter));
        colQuantidade.setCellFactory(TextFieldTableCell.forTableColumn(stringConverterInt));
        colNome.setCellFactory(TextFieldTableCell.<ProdutoParaTabela>forTableColumn());//produtoStringCellDataFeatures -> produtoStringCellDataFeatures.getValue().nomeProperty());

        // Cria a operação que será realizada ao confirmar edição. Também é possível criar operações quando for cancelada a edição
        colNome.setOnEditCommit((TableColumn.CellEditEvent<ProdutoParaTabela, String> event) -> {
            TablePosition<ProdutoParaTabela, String> pos = event.getTablePosition();
            String nomeNovo = event.getNewValue();
            int row = pos.getRow();
            ProdutoParaTabela produto = event.getTableView().getItems().get(row);
            produto.setNome(nomeNovo);
            dao_produtoParaTabela.atualizar(produto);
        });
        colPreco.setOnEditCommit((TableColumn.CellEditEvent<ProdutoParaTabela, Double> event) -> {
            TablePosition<ProdutoParaTabela, Double> pos = event.getTablePosition();
            Double precoNovo = event.getNewValue();
            int row = pos.getRow();
            ProdutoParaTabela produto = event.getTableView().getItems().get(row);
            produto.setPreco(precoNovo);
            dao_produtoParaTabela.atualizar(produto);
        });
        colQuantidade.setOnEditCommit((TableColumn.CellEditEvent<ProdutoParaTabela, Integer> event) -> {
            TablePosition<ProdutoParaTabela, Integer> pos = event.getTablePosition();
            Integer qtdNovo = event.getNewValue();
            int row = pos.getRow();
            ProdutoParaTabela produto = event.getTableView().getItems().get(row);
            produto.setQuantidade(qtdNovo);
            dao_produtoParaTabela.atualizar(produto);
        });

        // Adiciona a lista à tabela, tornando os elementos visíveis
        // ao executar operações na tabela não é necessário atualizar a tabela, devido à lista ser do tipo Observable
        tabProdutos.setItems(observableList);

    }
}
