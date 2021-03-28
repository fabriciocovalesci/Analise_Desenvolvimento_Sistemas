package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends conexaoSQL{

    // insere novo dados
    public void cadastraProduto(Produto produto){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Produto " +
                            "(nome , custo, preco)" +
                            "VALUES (?, ?, ?);"
            );
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getCusto());
            stm.setDouble(3, produto.getPreco());
            stm.executeUpdate();
            System.out.println("Produto inserido com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // exclui um produto
    public void deletarProduto(int id){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Produto WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Produto deletado com sucesso !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // atualiza produto
    public void atualizarProduto(Produto produto){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Produto SET nome = ? , custo = ?, preco = ?  WHERE ID = ?");
            stm.setString(1, produto.getNome());
            stm.setDouble(2, produto.getCusto());
            stm.setDouble(3, produto.getPreco());
            stm.setInt(4,  produto.getId());
            stm.executeUpdate();
            System.out.println("Produto atualizado com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca produto pelo nome
    public Produto buscarProduto(String nomeProduto){
        connect();
        Produto result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Produto WHERE nome = ?");
            stm.setString(1, nomeProduto);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new Produto();
                result.setNome(rs.getString("nome"));
                result.setCusto(rs.getDouble("custo"));
                result.setPreco(rs.getDouble("preco"));
                result.setId(rs.getInt("ID"));
            }
            System.out.println("Consulta realizada !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
        return result;
    }

    // busca todos produtos
    public List<Produto> buscaAllProduto(){
        connect();
        ArrayList<Produto> produtos = new ArrayList<>();
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Produto");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setCusto(rs.getDouble("custo"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
            System.out.println("Consulta de buscar todos produtos realizada !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
        return produtos;
    }
}
