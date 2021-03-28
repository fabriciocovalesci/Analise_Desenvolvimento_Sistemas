package Model;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class criarBanco extends conexaoSQL {

    // cria a tabela se nao existir
    public criarBanco(){
        connect();
        try{
            PreparedStatement stm1 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Produto (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT," +
                            "custo REAL," +
                            "preco REAL);");
            stm1.executeUpdate();
            System.out.println("Criado tabela Produto");

            PreparedStatement stm2 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Usuario (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome TEXT," +
                            "senha TEXT," +
                            "admin BOOLEAN);");
            stm2.executeUpdate();
            System.out.println("Criado tabela Usuario");

            PreparedStatement stm3 = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS Comanda (" +
                            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nome_comanda TEXT," +
                            "mesa INTEGER," +
                            "quantidade INTEGER," +
                            "valor_total REAL," +
                            "dataCriacao DATE," +
                            "dataFinaliza DATE," +
                            "status_pagamento TEXT," +
                            "pago BOOLEAN," +
                            "produto_id INTEGER," +
                            "usuario_id INTEGER," +
                            "FOREIGN KEY(produto_id) REFERENCES Produto(id)," +
                            "FOREIGN KEY(usuario_id) REFERENCES Usuario(id));");
            stm3.executeUpdate();
            System.out.println("Criado tabela Comanda");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }
}

