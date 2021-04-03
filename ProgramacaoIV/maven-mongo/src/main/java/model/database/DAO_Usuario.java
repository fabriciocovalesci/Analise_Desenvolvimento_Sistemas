package model.database;

import com.mongodb.MongoClientURI;
import com.mongodb.client.*;
import com.mongodb.client.model.Projections;
import model.Usuario;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DAO_Usuario{

    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection<Usuario> collection;
    private static DAO_Usuario instance;

    // padrão SINGLETON
    // garante que apenas uma instância seja gerada de determinado objeto
    // isso é feito escondendo o construtor e armazenando a instância criada
    public static DAO_Usuario getInstance() {
        if (instance == null) {
            instance = new DAO_Usuario();
        }
        return instance;
    }
    private DAO_Usuario(){
        ConnectionString connString = new ConnectionString(
//                "mongodb+srv://aluno:aluno@ads-sertao.c0gyl.mongodb.net/test?w=majority"
                "localhost:27017"
        );
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connString)
                .codecRegistry(pojoCodecRegistry)
                .retryWrites(true)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("test");
        collection = database.getCollection("pessoas", Usuario.class);
    }

    public void create(Usuario usuario) {
        collection.insertOne(usuario);
    }

    public void update(Usuario usuario) {
        collection.replaceOne(eq("_id", usuario.getId()), usuario);
    }

    public void remove(ObjectId id) {
        collection.deleteOne(eq("_id", id));
    }

    public void remove (String nome){
        collection.deleteOne(eq("nome", nome));
    }

    public void remove (Usuario usuario){
        remove(usuario.getId());
    }

    public ArrayList<Usuario> readAll(){
        ArrayList<Usuario> lista = new ArrayList<>();
        collection.find().forEach((obj) -> lista.add(obj));
        return lista;
    }

    public Usuario read(ObjectId id){
        Usuario user = null;
        MongoCursor<Usuario> iterable = collection.find(eq("_id", id)).iterator();
        if (iterable.hasNext()){
            user = iterable.next();
        }
        return user;
    }

    public Usuario read(String email){
        Usuario user = null;
        MongoCursor<Usuario> iterable = collection.find(eq("email", email)).iterator();
        if (iterable.hasNext()){
            user = iterable.next();
        }
        return user;
    }
}
