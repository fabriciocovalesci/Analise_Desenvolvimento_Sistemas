package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaDAO extends conexaoSQL{

    // insere novo dados
    public void cadastraComanda(Comanda comanda){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO Comanda " +
                            "(nome_comanda , mesa, quantidade, valor_total, status_pagamento, dataCriacao, " +
                            "dataFinaliza, produto_id, usuario_id, pago)" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            stm.setString(1, comanda.getNome_comanda());
            stm.setInt(2, comanda.getMesa());
            stm.setInt(3, comanda.getQuantidade());
            stm.setDouble(4, comanda.getValor_total());
            stm.setString(5, comanda.getStatus_pagamento());
            stm.setDate(6, (Date) comanda.getDataCriacao());
            stm.setDate(7,  comanda.getDataFinaliza());
            stm.setInt(8, comanda.getProduto_id());
            stm.setInt(9, comanda.getUsuario_id());
            stm.setBoolean(10, comanda.isPago());
            stm.executeUpdate();
            System.out.println("Comanda inserido com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // exclui um comanda
    public void deletarComanda(int id){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Comanda WHERE id = ?");
            stm.setInt(1, id);
            stm.executeUpdate();
            System.out.println("Comanda deletado com sucesso !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }

    }

    // atualiza comanda
    public void atualizarComanda(Comanda comanda){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Comanda SET nome_comanda = ?, mesa = ?, quantidade = ?, valor_total = ?, status_pagamento = ?, dataFinaliza = ?  WHERE ID = ?");
            stm.setString(1, comanda.getNome_comanda());
            stm.setInt(2, comanda.getMesa());
            stm.setInt(3, comanda.getQuantidade());
            stm.setDouble(4,  comanda.getValor_total());
            stm.setString(4,  comanda.getStatus_pagamento());
            stm.setDate(5,comanda.getDataFinaliza());
            stm.setInt(6, comanda.getId());
            stm.executeUpdate();
            System.out.println("Comanda atualizado com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca comandas ativas para o funcionario consultar
    public List<Comanda> buscarComandasAtivas(){
        connect();
        ArrayList<Comanda> comandasAtivas = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * from Comanda WHERE pago = false");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Comanda comanda = new Comanda();
                comanda.setNome_comanda(rs.getString("nome_comanda"));
                comanda.setMesa(rs.getInt("mesa"));
                comanda.setQuantidade(rs.getInt("quantidade"));
                comanda.setValor_total(rs.getDouble("valor_total"));
                comanda.setStatus_pagamento(rs.getString("status_pagamento"));
                comanda.setDataCriacao(rs.getDate("dataCriacao"));
                comanda.setDataFinaliza(rs.getDate("dataFinaliza"));
                comanda.setProduto_id(rs.getInt("produto_id"));
                comanda.setUsuario_id(rs.getInt("usuario_id"));
                comanda.setPago(rs.getBoolean("pago"));
                comandasAtivas.add(comanda);
            }
            System.out.println("Consulta de buscar todos as comandas ativas realizada !");
        }catch (SQLException e){
            System.out.println("Error ao buscar comandas ativas no banco de dados " + e.getMessage());
        }finally {
            desconnect();
        }
        return comandasAtivas;
    }

    // atualiza comanda
    public void encerrarComanda(Comanda comanda){
        connect();
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "UPDATE Comanda SET  status_pagamento = ?, dataFinaliza = ?, pago = ?  WHERE ID = ?");
            stm.setString(1,  comanda.getStatus_pagamento());
            stm.setDate(2,comanda.getDataFinaliza());
            stm.setBoolean(3, comanda.isPago());
            stm.setInt(4, comanda.getId());
            stm.executeUpdate();
            System.out.println("Comanda encerrada com sucesso !!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
    }

    // busca comanda pelo nome
    public Comanda buscarComanda(String nomeComanda){
        connect();
        Comanda result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Comanda WHERE nome = ?");
            stm.setString(1, nomeComanda);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new Comanda();
                result.setNome_comanda(rs.getString("nome_comanda"));
                result.setMesa(rs.getInt("mesa"));
                result.setQuantidade(rs.getInt("quantidade"));
                result.setValor_total(rs.getDouble("valor_total"));
                result.setStatus_pagamento(rs.getString("status_pagamento"));
                result.setDataCriacao(rs.getDate("dataCriacao"));
                result.setDataFinaliza(rs.getDate("dataFinaliza"));
                result.setProduto_id(rs.getInt("produto_id"));
                result.setUsuario_id(rs.getInt("usuario_id"));
                result.setPago(rs.getBoolean("pago"));
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

    // busca comanda pelo nome
    public Comanda buscarMesa(int Nummesa){
        connect();
        Comanda result = null;
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Comanda WHERE mesa = ?");
            stm.setInt(1, Nummesa);
            ResultSet rs = stm.executeQuery();

            if(rs.next()){
                result = new Comanda();
                result.setNome_comanda(rs.getString("nome_comanda"));
                result.setMesa(rs.getInt("mesa"));
                result.setQuantidade(rs.getInt("quantidade"));
                result.setValor_total(rs.getDouble("valor_total"));
                result.setStatus_pagamento(rs.getString("status_pagamento"));
                result.setDataCriacao(rs.getDate("dataCriacao"));
                result.setDataFinaliza(rs.getDate("dataFinaliza"));
                result.setProduto_id(rs.getInt("produto_id"));
                result.setUsuario_id(rs.getInt("usuario_id"));
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

    // busca todas comandas
    public List<Comanda> buscaAllComanda(){
        connect();
        ArrayList<Comanda> comandas = new ArrayList<>();
        try{
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Comanda");
            ResultSet rs = stm.executeQuery();

            while (rs.next()){
                Comanda comanda = new Comanda();
                comanda.setNome_comanda(rs.getString("nome_comanda"));
                comanda.setMesa(rs.getInt("mesa"));
                comanda.setQuantidade(rs.getInt("quantidade"));
                comanda.setValor_total(rs.getDouble("valor_total"));
                comanda.setStatus_pagamento(rs.getString("status_pagamento"));
                comanda.setDataCriacao(rs.getDate("dataCriacao"));
                comanda.setDataFinaliza(rs.getDate("dataFinaliza"));
                comanda.setProduto_id(rs.getInt("produto_id"));
                comanda.setUsuario_id(rs.getInt("usuario_id"));
                comanda.setPago(rs.getBoolean("pago"));
                comandas.add(comanda);
            }
            System.out.println("Consulta de buscar todos as comandas realizada !");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally {
            desconnect();
        }
        return comandas;
    }
}
