module patzer.gabriel {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens patzer.gabriel to javafx.fxml;
    opens patzer.gabriel.controller to javafx.fxml;
    opens patzer.gabriel.model to javafx.base;
    exports patzer.gabriel;
}