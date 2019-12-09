module com.mycompany.tworld {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.tworld to javafx.fxml;
    exports com.mycompany.tworld;
}