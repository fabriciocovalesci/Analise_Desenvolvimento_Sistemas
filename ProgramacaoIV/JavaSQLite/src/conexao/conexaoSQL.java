package conexao;

import java.sql.*;

public class conexaoSQL {

    private Connection conexao;

    public boolean conectar(){

        try{
            String url = "jdbc:sqlite:banco_dados/banco_sqlite.db";
            this.conexao = DriverManager.getConnection(url);

        }catch (Exception e){
            System.out.println("Erro ao conectar " + e.getMessage());
            return false;
        }
        System.out.println("Conecxão com sucesso");
        return true;
    }

    public boolean desconectar(){
        try {
            if(this.conexao.isClosed() == false){
                this.conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao desconecatar " + e.getMessage());
            return false;
        }
        System.out.println("Desconectou com sucesso");
        return true;
    }

    // criar sql java
    public Statement criarStatement(){
        try {
            return this.conexao.createStatement();
        }catch (SQLException e){
            return null;
        }
    }

    public PreparedStatement criarPreparedStatement(String sql){
        try {
            return this.conexao.prepareStatement(sql);
        }catch (SQLException e){
            return null;
        }
    }

    public Connection getConexao(){
        return this.conexao;
    }
}
