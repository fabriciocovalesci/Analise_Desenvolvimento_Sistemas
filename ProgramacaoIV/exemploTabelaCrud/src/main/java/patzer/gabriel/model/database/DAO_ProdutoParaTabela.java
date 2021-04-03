package patzer.gabriel.model.database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import patzer.gabriel.model.ProdutoParaTabela;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class DAO_ProdutoParaTabela extends DBUtil {
    public DAO_ProdutoParaTabela(){
        conectar();
        try {

            PreparedStatement stm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Produtos(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "descricao TEXT," +
                    "preco REAL," +
                    "quantidade INTEGER);");
            stm.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao criar tabela de prdutos.");
            e.printStackTrace();
        }
    }

    public ObservableList<ProdutoParaTabela> listar(){
        conectar();
        ObservableList<ProdutoParaTabela> observableList = FXCollections.observableArrayList();

        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Produtos;");
            resultSet = statement.executeQuery();

            if (resultSet.next() == false) {
                System.out.println("Nenhum dado encontrado.");
            }else{
                do{
                    int id = resultSet.getInt("_id");
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");
                    int quantidade = resultSet.getInt("quantidade");
                    ProdutoParaTabela produto = new ProdutoParaTabela(id,nome,preco,quantidade);
                    observableList.add(produto);
                }while(resultSet.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Não foi possível realizar a consulta no banco.");
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            desconectar();
        }
        return observableList;
    }

    public void excluir(ProdutoParaTabela p) {
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM Produtos WHERE _id=?;");
            statement.setInt(1,p.get_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao excluir elemento no banco.");
        }finally {
            desconectar();
        }
    }

    public void atualizar(ProdutoParaTabela p) {
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE Produtos" +
                    " SET nome=?, preco=?, quantidade=?" +
                    " WHERE _id=?;");
            statement.setString(1,p.getNome());
            statement.setDouble(2,p.getPreco());
            statement.setInt(3,p.getQuantidade());
            statement.setInt(4,p.get_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao atualizar elemento no banco.");
        }finally {
            desconectar();
        }
    }

    public int inserir(ProdutoParaTabela p) {
        conectar();
        int ret = -1;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO Produtos(_id,nome,preco,quantidade)" +
                    " VALUES (?,?,?,?);");
            statement.setNull(1, Types.INTEGER);
            statement.setString(2,p.getNome());
            statement.setDouble(3,p.getPreco());
            statement.setInt(4,p.getQuantidade());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next())
                ret = rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Falha ao inserir elemento no banco.");
            e.printStackTrace();
        }finally {
            desconectar();
        }
        return ret;
    }
}
