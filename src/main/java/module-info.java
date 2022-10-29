module com.example.dnd_personage {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;

    opens com.example.dnd_personage to javafx.fxml;
    exports com.example.dnd_personage;
    exports com.example.dnd_personage.visitor;
    opens com.example.dnd_personage.visitor to javafx.fxml;
}