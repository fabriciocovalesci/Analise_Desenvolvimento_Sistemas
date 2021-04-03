package patzer.gabriel.model.database;
import patzer.gabriel.App;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    public static Connection connection;

    public Connection conectar(){
        if (connection != null) {
            return connection;
        }
        else{
            try {
                // o banco precisa estar criado dentro das fontes/recursos por causa do frame maven
                connection = DriverManager.getConnection("jdbc:sqlite::resource:patzer/gabriel/estoque.db");
                System.out.println("Conexão ao banco estabelecida.");
            } catch (SQLException e) {
                System.err.println("Não foi possível conectar ao banco de dados.");
            }
            return connection;
        }
    }

    public void desconectar(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Falha ao encerrar conexão com o banco.");
            }
            connection = null;
        }
    }
}
