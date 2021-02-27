package com.company;

import conexao.conexaoSQL;
import conexao.criarBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
	// write your code here
        conexaoSQL conectaSQL = new conexaoSQL();
        criarBanco criaTabelas = new criarBanco(conectaSQL);

        criaTabelas.criarTabelaPessoa();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("Luis");
        pessoa.setIdade(29);

        conectaSQL.conectar();

        String insertsql = "INSERT INTO tbl_Pessoa("
                + "id,"
                + "nome,"
                + "idade"
                + ") VALUES(?,?,?)"
                + ";";

        PreparedStatement preparedStatement = conectaSQL.criarPreparedStatement(insertsql);

        try {
            preparedStatement.setInt(1, pessoa.getId());
            preparedStatement.setString(2, pessoa.getNome());
            preparedStatement.setInt(3, pessoa.getIdade());

            int resultado = preparedStatement.executeUpdate();
            if(resultado == 1){
                System.out.println("Pessoa inserida");
            }else{
                System.out.println("Pessoa nao inserida");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Pessoa nao inserida cacth");
        }finally {
            if(preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            conectaSQL.desconectar();
        }
    }
}
