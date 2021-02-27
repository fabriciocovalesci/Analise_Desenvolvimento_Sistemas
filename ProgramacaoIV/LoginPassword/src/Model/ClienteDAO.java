package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends Connect {

    // cria a tabela se nao existir
    public ClienteDAO(){
        connect();
        try{
            PreparedStatement stm = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Cliente (" +
                            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT," +
                            "email TEXT," +
                            "telefone TEXT," +
                            "endereco TEXT," +
                            "dataNascimento TEXT," +
                            "cpf TEXT);");
            stm.executeUpdate();
            System.out.println("Criado tabela Cliente");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // insere novo dados
    public void inserirCliente(Cliente c){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Cliente " +
                            "(nome , email, telefone, endereco, dataNascimento, cpf)" +
                            "VALUES (?, ?, ?, ?, ?, ?);"
            );
            stm.setString(1, c.getNome());
            stm.setString(2, c.getEmail());
            stm.setString(3, c.getTelefone());
            stm.setString(4, c.getEndereco());
            stm.setString(5, c.getDataNascimento());
            stm.setString(6, c.getCpf());
            stm.executeUpdate();
            System.out.println("Cliente inserido com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // exclui um cliente
    public void deletarCliente(int id){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Cliente WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Cliente deletado com sucesso !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // atualiza cliente
    public void atualizarCliente(Cliente c){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Cliente SET nome = ? , email = ?, telefone = ?, endereco = ?, dataNascimento = ?, cpf = ?  WHERE ID = ?");
            stm.setString(1, c.getNome());
            stm.setString(2, c.getEmail());
            stm.setString(3, c.getTelefone());
            stm.setString(4, c.getEndereco());
            stm.setString(5, c.getDataNascimento());
            stm.setString(6, c.getCpf());
            stm.setInt(7, c.getId());
            stm.executeUpdate();
            System.out.println("Cliente atualizado com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca cliente pelo nome
    public Cliente buscarCliente(String nomeCliente){
        connect();
        Cliente result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Cliente WHERE nome = ?");
            stm.setString(1, nomeCliente);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new Cliente();
                result.setNome(rs.getString("nome"));
                result.setEmail(rs.getString("email"));
                result.setTelefone(rs.getString("telefone"));
                result.setEndereco(rs.getString("endereco"));
                result.setDataNascimento(rs.getString("dataNascimento"));
                result.setCpf(rs.getString("cpf"));
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

    // busca todos cliente
    public List<Cliente> buscaAllClientes(){
        connect();
        ArrayList<Cliente> clientes = new ArrayList<>();
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Cliente");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Cliente cliente = new Cliente();
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setDataNascimento(rs.getString("dataNascimento"));
                cliente.setCpf(rs.getString("cpf"));
                clientes.add(cliente);
            }
            System.out.println("Consulta de buscar todos clientes realizada !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
        return clientes;

    }

}
