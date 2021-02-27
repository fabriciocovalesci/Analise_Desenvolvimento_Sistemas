package conexao;

import java.sql.SQLException;
import java.sql.Statement;

public class criarBanco {

    private final conexaoSQL conexaoSQL;

    public criarBanco(conexaoSQL pconexaoSQL) {
        this.conexaoSQL = pconexaoSQL;
    }

    public void criarTabelaPessoa(){

        String sql = "CREATE TABLE IF NOT EXISTS tbl_Pessoa"
                + "("
                + "id integer PRIMARY KEY,"
                + "nome text NOT NULL,"
                + "idade integer NOT NULL"
                + ")";

        // executar tabela
        boolean conectou = false;
        try {
            conectou = this.conexaoSQL.conectar();
            Statement stmt = this.conexaoSQL.criarStatement();
            stmt.execute(sql);
            System.out.println("Tabela pessoa criada");
        }catch (SQLException e){
            System.out.println(e.getMessage());

        }finally {
            if(conectou){
                this.conexaoSQL.desconectar();
            }
        }
    }
}
