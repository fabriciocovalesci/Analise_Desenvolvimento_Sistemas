/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifrs.sertao.prov.base;

/**
 *
 * @author 146188
 */
public class Medicamento extends Produto {
    private String principio_ativo;
    private boolean controlado;
    private String lote;
    private String categoria;

    public Medicamento(String principio_ativo, boolean controlado, String lote, int codigo) {
        super(codigo);
        this.principio_ativo = principio_ativo;
        this.controlado = controlado;
        this.lote = lote;
    }

  

    public String getPrincipio_ativo() {
        return principio_ativo;
    }

    public boolean isControlado() {
        return controlado;
    }

    public String getLote() {
        return lote;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setPrincipio_ativo(String principio_ativo) {
        this.principio_ativo = principio_ativo;
    }

    public void setControlado(boolean controlado) {
        this.controlado = controlado;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
   
    
}
