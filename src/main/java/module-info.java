module com.example.monopoly {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
	requires javafx.graphics;
	requires javafx.base;
	requires java.desktop;

    opens com.example.monopoly to javafx.fxml;
    exports com.example.monopoly;
}