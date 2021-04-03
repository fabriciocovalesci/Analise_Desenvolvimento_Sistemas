package patzer.gabriel.model.database;
import patzer.gabriel.model.Produto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class DAO_Produto extends DBUtil {
    private final boolean LIMPAR_TABELA = false;
    public DAO_Produto(){
        conectar();
        try {
            if (LIMPAR_TABELA){
                PreparedStatement stm = connection.prepareStatement("DROP TABLE IF EXISTS Produtos;");
                stm.executeUpdate();
                stm.close();
            }
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Produtos(" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "descricao TEXT," +
                    "preco REAL," +
                    "quantidade INTEGER);");
            stm.executeUpdate();
            if (LIMPAR_TABELA) {
                inserir(new Produto("Lápis","HB",0.75, 10));
                inserir(new Produto("Caneta","Azul",2.25, 30));
                inserir(new Produto("Papel","500 folhas",15, 15));
                inserir(new Produto("Caderno","Capa dura",11.3, 20));
            }
        } catch (SQLException e) {
            System.err.println("Falha ao criar tabela de prdutos.");
            e.printStackTrace();
        }
    }

    // TODO Update
    // TODO encerrar os PreparedStatement e os ResultSet (chamar métodos close() )
    // CRUD Create, Read, Update and Delete
    // ABCD Add, Browse, Change and Delete
    // VEIA: Visualizar, Excluir, Inserir, Alterar

    public ArrayList<Produto> visualizar(){
        conectar();
        ArrayList<Produto> lista = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM Produtos;");
            resultSet = statement.executeQuery();

            if (resultSet.next() == false) {
                System.out.println("Nenhum dado encontrado.");
            }else{
                do{
                    Produto produto = new Produto();
                    produto.set_id(resultSet.getInt("_id"));
                    produto.setNome(resultSet.getString("nome"));
                    produto.setDescricao(resultSet.getString("descricao"));
                    produto.setPreco(resultSet.getDouble("preco"));
                    produto.setQuantidade(resultSet.getInt("quantidade"));
                    lista.add(produto);
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
        return lista;
    }

    public Produto visualizar(String nome){
        conectar();
        ResultSet resultSet = null;
        Produto produto = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Produtos WHERE nome=?;");
            statement.setString(1,nome);
            resultSet = statement.executeQuery();
            produto = new Produto(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5));
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta no banco.");
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return produto;
    }

    public void excluir(int id) {
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM Produtos WHERE _id=?;");
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao excluir elemento no banco.");
        }finally {
            desconectar();
        }
    }

    public void excluir (String nome){
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM Produtos WHERE nome=?;");
            statement.setString(1,nome);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Falha ao excluir elemento no banco.");
        }finally {
            desconectar();
        }
    }

    public void excluir (Produto produto){
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM Produtos WHERE _id=?;");
            statement.setInt(1,produto.get_id());
        } catch (SQLException e) {
            System.err.println("Falha ao excluir elemento no banco.");
        }finally {
            desconectar();
        }
    }

    public void inserir (Produto produto){
        conectar();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO" +
                    " Produtos(_id,nome,descricao,preco,quantidade)" +
                    " VALUES(?,?,?,?,?);");
            if (produto.get_id() != null){
                statement.setInt(1, produto.get_id());
            } else {
                statement.setNull(1, Types.INTEGER);
            }
            statement.setString(2, produto.getNome());
            statement.setString(3, produto.getDescricao());
            statement.setDouble(4, produto.getPreco());
            statement.setInt(5, produto.getQuantidade());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Não foi possível inserir novo produto no banco.");
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
            desconectar();
        }
    }

    public void atualizar(Produto produto) {
        conectar();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Produtos " +
                    "SET nome = ?, descricao = ?, preco = ?, quantidade = ? " +
                    "WHERE _id = ?");
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidade());
            statement.setInt(5, produto.get_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Não foi possível alterar dados do produto no banco.");
        }finally {
            desconectar();
        }
    }

    public Produto visualizar(int id) {
        conectar();
        ResultSet resultSet = null;
        Produto produto = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Produtos WHERE _id=?;");
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            produto = new Produto(resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5));
        } catch (SQLException e) {
            System.err.println("Não foi possível realizar a consulta no banco.");
            e.printStackTrace();
        } finally {
            desconectar();
        }
        return produto;

    }
}