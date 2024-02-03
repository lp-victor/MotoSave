module motosave.motosavefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires jakarta.persistence;
    requires org.hibernate.orm.core; // Para que cargue las dependencias del hibernate.

    opens motosave.motosavefx to javafx.fxml;
    exports motosave.motosavefx;
    exports motosave.motosavefx.controlador;
    opens motosave.motosavefx.controlador to javafx.fxml;

    opens motosave.Modelos to org.hibernate.orm.core; // Para que habrá el controlador el JavaFX.
}