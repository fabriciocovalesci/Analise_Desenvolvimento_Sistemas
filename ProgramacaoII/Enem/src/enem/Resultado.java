
package enem;


public class Resultado implements CalculoPontuacao {
    private int notaRedacao;
    private int notaCienciasNatureza;
    private int notaCienciasHumanas;
    private int notaLinguagens;
    private int notaMatematica;
    

    public int getNotaRedacao() {
        return notaRedacao;
    }

    public void setNotaRedacao(int notaRedacao) {
        this.notaRedacao = notaRedacao;
    }

    public int getNotaCienciasNatureza() {
        return notaCienciasNatureza;
    }

    public void setNotaCienciasNatureza(int notaCienciasNatureza) {
        this.notaCienciasNatureza = notaCienciasNatureza;
    }

    public int getNotaCienciasHumanas() {
        return notaCienciasHumanas;
    }

    public void setNotaCienciasHumanas(int notaCienciasHumanas) {
        this.notaCienciasHumanas = notaCienciasHumanas;
    }

    public int getNotaLinguagens() {
        return notaLinguagens;
    }

    public void setNotaLinguagens(int notaLinguagens) {
        this.notaLinguagens = notaLinguagens;
    }

    public int getNotaMatematica() {
        return notaMatematica;
    }

    public void setNotaMatematica(int notaMatematica) {
        this.notaMatematica = notaMatematica;
    }

    @Override
    public void calculoFinal() {
        return "o";
    }
    
    public static getPontuacaoFinal(){
        
    }
    
    
    
}
