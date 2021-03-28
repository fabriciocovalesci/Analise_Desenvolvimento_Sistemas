package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends conexaoSQL {

    // insere novo dados
    public void cadastraUsuario(Usuario usuario){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Usuario " +
                            "(nome , senha, admin)" +
                            "VALUES (?, ?, ?);"
            );
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getSenha());
            stm.setBoolean(3, usuario.getAdmin());
            stm.executeUpdate();
            System.out.println("Usuario inserido com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // exclui um usuario
    public void deletarUsuario(int id){
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

    // atualiza usuario
    public void atualizarUsuario(Usuario usuario){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Usuario SET nome = ? , senha = ?, admin = ?  WHERE ID = ?");
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getSenha());
            stm.setBoolean(3, usuario.getAdmin());
            stm.setInt(4,  usuario.getId());
            stm.executeUpdate();
            System.out.println("Usuario atualizado com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca usuario pelo nome
    public Usuario buscarUsuario(String nomeUsuario){
        connect();
        Usuario result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Usuario WHERE nome = ?");
            stm.setString(1, nomeUsuario);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new Usuario();
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

    // busca todos usuarios
    public List<Usuario> buscaAllUsuario(){
        connect();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Usuario");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Usuario usuario = new Usuario();
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAdmin(rs.getBoolean("admin"));
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
