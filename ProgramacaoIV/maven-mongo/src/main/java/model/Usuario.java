package model;

import model.database.DAO_Usuario;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class Usuario {
    private ObjectId id;
    private String nome;
    private String email;
    private String senha;
    private byte[] salt;

    @Override
    public String toString() {
        return "Usuário{" + id.toString() + ": " +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", salt='" + salt.toString() + '\'' +
                '}';
    }

    public Usuario() {
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha, byte[] salt) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.salt = salt;
    }

    // Padrão ACTIVE RECORD
    // utiliza o modelo para realizar as operações de persistência
    // segue o ponto de vista que o objeto deve entender como ele deve ser salvo/excluído/etc
    // em projetos maiores ou para realizar testes pode ser uma má ideia. pode ser usado junto com dao.
    public void salvar (){
        DAO_Usuario du = DAO_Usuario.getInstance();
        du.create(this);
    }

    public void atualizar (){
        DAO_Usuario du = DAO_Usuario.getInstance();
        du.update(this);
    }

    public void excluir (){
        DAO_Usuario du = DAO_Usuario.getInstance();
        du.remove(this);
    }

    public static Usuario buscar_email(String email) {
        DAO_Usuario du = DAO_Usuario.getInstance();
        return du.read(email);
    }

    public static ArrayList<Usuario> buscar_todos(){
        DAO_Usuario du = DAO_Usuario.getInstance();
        return du.readAll();
    }
}
