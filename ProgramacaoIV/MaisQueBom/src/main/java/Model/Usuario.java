package Model;

public class Usuario {

    private int id;
    private String nome;
    private String senha;
    private Boolean admin;

    public String toString(){
        return "Usuario " + getNome() + " \nSenha "+ getSenha() + " \nAdmin" + getAdmin() + " \nID " + getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
