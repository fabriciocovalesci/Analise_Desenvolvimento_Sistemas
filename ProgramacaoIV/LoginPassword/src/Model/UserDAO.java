package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Connect{

    // cria a tabela se nao existir
    public UserDAO(){
        connect();
        try{
            PreparedStatement stm = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Usuario (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome TEXT," +
                    "senha TEXT," +
                            "admin BOOLEAN);");
            stm.executeUpdate();
            System.out.println("Criado tabela Usuario");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // insere novo dados
    public void inserir(User u){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Usuario " +
                            "(nome , senha, admin)" +
                            "VALUES (?, ?, ?);"
            );
            stm.setString(1, u.getNome());
            stm.setString(2,u.getSenha());
            stm.setBoolean(3, u.getAdmin());
            stm.executeUpdate();
            System.out.println("Usuario inserido com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // exclui um user
    public void deletar(int id){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Usuario WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Usuario deletado com sucesso !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // atualiza cliente
    public void atualizar(User u){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Usuario " +
                            "SET(nome = ?, senha = ?, admin = ?)" +
                            "WHERE Id = ?");
            stm.setString(1, u.getNome());
            stm.setString(2,u.getSenha());
            stm.setBoolean(3, u.getAdmin());
            stm.setInt(4, u.getId());
            stm.executeUpdate();
            System.out.println("Usuario atualizado com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca pelo id
    public User buscar(String nomeUser){
        connect();
        User result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Usuario WHERE nome = ?");
            stm.setString(1, nomeUser);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new User();
                result.setNome(rs.getString("nome"));
                result.setSenha(rs.getString("senha"));
                result.setAdmin(rs.getBoolean("admin"));
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

    // busca todos
    public List<User> buscaAll(){
        connect();
        ArrayList<User> usuarios = new ArrayList<>();
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Usuario");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
               User usuario = new User();
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAdmin(rs.getBoolean("admin"));
                usuario.setId(rs.getInt("ID"));
                usuarios.add(usuario);
            }
            System.out.println("Consulta de buscar todos usuarios realizada !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
        return usuarios;

    }
}
