package Model;

import java.util.Date;

public class Comanda {

    private int id;
    private String nome_comanda;
    private int mesa;
    private int quantidade;
    private Double valor_total;
    private String status_pagamento;
    private Date dataCriacao;
    private Date dataFinaliza;
    private int produto_id;
    private int usuario_id;

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    private boolean pago;


    public String toString(){
        return "Comanda " + getNome_comanda() + " \nmesa "+ getMesa() + " \nquantidade" + getQuantidade() +
                " \nvalor total " + getValor_total() + " \nstatus pagamento " + getStatus_pagamento() +
            " \ndata criacao " + getDataCriacao() + " \nData finalizada " + getDataFinaliza() +
                " \nproduto id " + getProduto_id() + " \nusuario id " + getUsuario_id();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_comanda() {
        return nome_comanda;
    }

    public void setNome_comanda(String nome_comanda) {
        this.nome_comanda = nome_comanda;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor_total() {
        return valor_total;
    }

    public void setValor_total(Double valor_total) {
        this.valor_total = valor_total;
    }

    public String getStatus_pagamento() {
        return status_pagamento;
    }

    public void setStatus_pagamento(String status_pagamento) {
        this.status_pagamento = status_pagamento;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public java.sql.Date getDataFinaliza() {
        return (java.sql.Date) dataFinaliza;
    }

    public void setDataFinaliza(Date dataFinaliza) {
        this.dataFinaliza = dataFinaliza;
    }

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
}
