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
public interface VendaRestrita extends Produto {
    
       private int[] CPFCliente;
    
    private String Cliente;
    
    public Venda_Restrita(int codigo) {
        super(codigo);
    }
       

    public String getCliente() {
        return Cliente;
    }

    public int [] getCPFCliente() {
        return CPFCliente;
    }
    
}
