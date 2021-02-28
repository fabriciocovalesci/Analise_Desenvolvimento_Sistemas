package Model;

public class User {
    private String nome;
    private String senha;
    private Boolean admin;
    private Integer id;


    public String toString(){
        return "Usuario " + getNome() + " senha " + getSenha() + " é Admin " + getAdmin();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
