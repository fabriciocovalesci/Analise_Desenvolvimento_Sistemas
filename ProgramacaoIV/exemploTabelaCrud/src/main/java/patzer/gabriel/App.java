package patzer.gabriel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import patzer.gabriel.model.database.DAO_Produto;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        // altere accordion para 'true' ou 'false' para selecionar qual versão do programa usar
        boolean accordion = false;
        if (accordion) {
            scene = new Scene(loadFXML("baseCRUD"));
            stage.setTitle("Exemplo de DAO SQLite e Accordion \"Pane\"");
        }else {
            scene = new Scene(loadFXML("tabelas"));
            stage.setTitle("Exemplo de ações CRUD em tabela");
        }
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}