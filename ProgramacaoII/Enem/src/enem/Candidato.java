/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enem;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;


public class Candidato extends Resultado {
    private final String primeiroNome;
    private final String ultimoNome;
    private Date dataNascimento ;
    private float rendaFamiliar;
    private  Resultado resultadoEnem;

    public Candidato(String primeiroNome, String ultimoNome)//, Resultado resultadoEnem) {{
            {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        //this.resultadoEnem = resultadoEnem;
    }
    
    Scanner in = new Scanner(System.in);

 
    
    public void CadastrarCandidatos(){
         ArrayList<String> cadastroCandidatos = new ArrayList<>();
         for (int i=0; i<5; i++){
          in.next("Nome ->");
            cadastroCandidatos.add(this.primeiroNome);
           in.next("Sobrenome ->");
            cadastroCandidatos.add(this.ultimoNome);
           in.next();
   }
    }
    
    public void CadastroResultado(){
         ArrayList<String> cadastroResultados = new ArrayList<>();
         
    }


    public String getNome() {
        return primeiroNome;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public Resultado getResultadoEnem() {
        return resultadoEnem;
    }

    @Override
    public String toString() {
        return "Candidato{" + "primeiroNome=" + primeiroNome + ", ultimoNome=" + ultimoNome + ", dataNascimento=" + dataNascimento + ", rendaFamiliar=" + rendaFamiliar + ", resultadoEnem=" + resultadoEnem + '}';
    }
    
}
